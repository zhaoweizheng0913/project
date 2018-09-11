package speedchina.addresselement.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import speedchina.addresselement.dao.AddressElementMapper;
import speedchina.addresselement.dao.OrganizationMapper;
import speedchina.addresselement.dao.StandardAddressMapper;
import speedchina.addresselement.entity.AddressElement;
import speedchina.addresselement.entity.Organization;
import speedchina.addresselement.entity.StandardAddress;
import speedchina.addresselement.tools.AddressElementTypeEnum;

import java.util.*;

/**
 * @author 11852
 */
@Service
public class AddressElementServiceImpl {

    @Autowired
    private AddressElementMapper addressElementMapper;

    @Autowired
    private StandardAddressMapper standardAddressMapper;

    @Autowired
    private OrganizationMapper organizationMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 生成标准地址方法
     */
    public void generateStandardAddress() {

        //step1:获取所有的地址元素和根元素
        List<AddressElement> addressElementList = addressElementMapper.getAllElement();
        AddressElement root = addressElementMapper.getCity();
        int count = addressElementList.size();
        logger.info("获取地址元素个数：" + count);

        //step2:生成map<parentId,set<element>>的数据格式，供step3处理
        Map<String, Set<AddressElement>> mapForBuildTree = new HashMap<String, Set<AddressElement>>();
        for (AddressElement temp : addressElementList) {
            logger.info("格式处理，剩余：" + count--);
            if (mapForBuildTree.containsKey(temp.getParentId())) {
                mapForBuildTree.get(temp.getParentId()).add(temp);
            } else {
                Set<AddressElement> tempSet = new HashSet<AddressElement>();
                tempSet.add(temp);
                mapForBuildTree.put(temp.getParentId(), tempSet);
            }
        }

        //step3:构建树 释放map对象
        logger.info("生成地址树...");
        this.BuildTree(root, mapForBuildTree);
        mapForBuildTree = null;
        logger.info("生成地址树...完成!");

        //step4:获取门牌号、楼幢号、单元号、户室号等类型元素
        Set<AddressElement> standardElementSet = new HashSet<AddressElement>();
        this.Traverse(root, standardElementSet);

        //step5:生成标准地址
        Map<String, AddressElement> addressElementMap = new HashMap<String, AddressElement>(addressElementList.size(), 1f);
        addressElementMap.put(root.getId(), root);
        for (AddressElement tempElement : addressElementList) {
            addressElementMap.put(tempElement.getId(), tempElement);
        }
        addressElementList = null;

        logger.info("生成标准地址...");
        this.generateStandardAddress(addressElementMap, standardElementSet);
        logger.info("生成标准地址...完成!");

    }

    /**
     * 递归 构建树
     *
     * @param node
     * @param nodeCollection
     */
    private void BuildTree(AddressElement node, Map<String, Set<AddressElement>> nodeCollection) {
        Set<AddressElement> children = nodeCollection.get(node.getId());
        if (null != children && children.size() > 0) {
            node.setChildren(children);
            for (AddressElement addressElement : node.getChildren()) {
                BuildTree(addressElement, nodeCollection);
            }
        }
    }

    /**
     * 获取门牌号、楼幢号、单元号、户室号等类型元素
     * @param root
     * @param standardElementSet
     */
    private void Traverse(AddressElement root, Set<AddressElement> standardElementSet) {
        if (null != root.getChildren() && root.getChildren().size() > 0) {
            for (AddressElement temp : root.getChildren()) {
                if(temp.getAddressElementType() >= AddressElementTypeEnum.MENPAIHAO.typeCode &&
                        temp.getAddressElementType()!= AddressElementTypeEnum.QITA.typeCode ){
                    standardElementSet.add(temp);
                }
                Traverse(temp, standardElementSet);
            }
        } else {
            if (root.getAddressElementType() >= AddressElementTypeEnum.MENPAIHAO.typeCode &&
                    root.getAddressElementType()!= AddressElementTypeEnum.QITA.typeCode ) {
                standardElementSet.add(root);
            }
        }
    }

    /**
     * 生成标准地址
     * @param nodeMap  key : addressElementId value : addressElement
     * @param finalElementSet 所有叶子节点元素
     */
    private void generateStandardAddress(Map<String, AddressElement> nodeMap, Set<AddressElement> finalElementSet) {

        //获取组织机构map
        List<Organization> organizationList = organizationMapper.selectAll();
        Map<String,String> organizationMap = new HashMap<String, String>();
        for (Organization organization:organizationList) {
            if(!organizationMap.containsKey(organization.getOrganizationCode())){
                organizationMap.put(organization.getOrganizationCode(),organization.getId());
            }
        }
        //生成标准地址insertList
        List<StandardAddress> insertData = new ArrayList<StandardAddress>(5001);
        int num = finalElementSet.size();

        for (AddressElement finalElement : finalElementSet) {
            logger.info("标准地址剩余:" + num--);

            StandardAddress standardAddress = new StandardAddress();
            StringBuffer standardAddressName = new StringBuffer(this.appendSuffix(finalElement));
            StringBuffer standardAddressPath = new StringBuffer(finalElement.getId());
            // 标准地址id、status、typeCode、location   注：标准地址id为该标准地址最末节点地址元素id
            standardAddress.setId(finalElement.getId());
            standardAddress.setStatus(finalElement.getStatus());
            standardAddress.setStandardAddressType(finalElement.getAddressElementType());
            if (null != finalElement.getLng() && null != finalElement.getLat()) {
                String location = "{\"lat\":"+finalElement.getLat()+",\"lng\":"+finalElement.getLng()+"}";
                standardAddress.setLocation(location);
            }

            AddressElement parentNode = nodeMap.get(finalElement.getParentId());

            //回溯生成标准地址
            int count = 0;
            while (null != parentNode && count < 25) {
                count++;
                //标准地址名称与路径
                standardAddressName.insert(0,appendSuffix(parentNode));
                standardAddressPath.insert(0,parentNode.getId() + "/");

                this.supplementId(standardAddress,parentNode,organizationMap);

                if (null != parentNode.getParentId()) {
                    parentNode = nodeMap.get(parentNode.getParentId());
                } else {
                    parentNode = null;
                }
            }

            if (count <= 20) {
                insertData.add(standardAddress);
                if (insertData.size() > 0 && insertData.size() % 5000 == 0) {
                    standardAddressMapper.insertList(insertData);
                    insertData.clear();
                    logger.info("保存标准地址..");
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                standardAddress = null;
            }
        }
        standardAddressMapper.insertList(insertData);
        //addressElementMapper.saveAll(results);
    }

    /**
     * 补充标准地址的每项id
     * @param standardAddress
     * @return
     */
    private StandardAddress supplementId(StandardAddress standardAddress,AddressElement addressElement,
                                         Map<String,String> organizationMap){
        //设置行政区划id 与 组织机构id
        if (addressElement.getAddressElementType() <= AddressElementTypeEnum.JIEDAOBANSHICHU.typeCode) {
            standardAddress.setAdministrativeRegionId(addressElement.getId());
        }
        if(organizationMap.containsKey(addressElement.getOrganizationCode())){
            standardAddress.setOrganizationId(organizationMap.get(addressElement.getOrganizationCode()));
        }
        


        return standardAddress;
    }

    /**
     * 追加后缀
     * @param addressElement
     * @return
     */
    private String appendSuffix(AddressElement addressElement) {
        int typeCode = addressElement.getAddressElementType();
        if(typeCode == AddressElementTypeEnum.MENPAIHAO.typeCode)
            return addressElement.getAddressElementName() + AddressElementTypeEnum.MENPAIHAO.suffix;
        if(typeCode == AddressElementTypeEnum.JIANZHUWU.typeCode)
            return addressElement.getAddressElementName() + AddressElementTypeEnum.JIANZHUWU.suffix;
        if(typeCode == AddressElementTypeEnum.DANYUAN.typeCode)
            return addressElement.getAddressElementName() + AddressElementTypeEnum.DANYUAN.suffix;
        if(typeCode == AddressElementTypeEnum.CENG.typeCode)
            return addressElement.getAddressElementName() + AddressElementTypeEnum.CENG.suffix;
        if(typeCode == AddressElementTypeEnum.HUSHI.typeCode)
            return addressElement.getAddressElementName() + AddressElementTypeEnum.HUSHI.suffix;
        if(typeCode == AddressElementTypeEnum.ZU.typeCode)
            return addressElement.getAddressElementName() + AddressElementTypeEnum.ZU.suffix;
        return addressElement.getAddressElementName();
    }


}
