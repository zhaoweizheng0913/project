package speedchina.addresselement.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * StandardAddress entity
 * @author 11852
 */
@Data
@Table(name = "sp_standard_address_new")
public class StandardAddress {

    /**
     * 主键id
     */
    private String id;

    /**
     * 标准地址名称
     */
    private String standardAddressName;

    /**
     * 标准地址路径
     */
    private String standardAddressPath;

    /**
     * 标准地址类型
     */
    private Integer standardAddressType;

    /**
     * 行政区划id
     */
    private String administrativeRegionId;

    /**
     * 组织机构id
     */
    private String organizationId;

    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     * 市id
     */
    private String cityId;

    /**
     * 区县id
     */
    private String countyId;

    /**
     * 乡镇街道id
     */
    private String townId;

    /**
     * 社区居村委会id
     */
    private String communityId;

    /**
     * 村社区id
     */
    private String villageCommunityId;

    /**
     * 街路巷id
     */
    private String streetId;

    /**
     * 小区id
     */
    private String housingEstateId;

    /**
     * 自然村id
     */
    private String naturalVillageId;

    /**
     * 组id
     */
    private String groupId;

    /**
     * 门牌号id
     */
    private String houseNumberId;

    /**
     * 建筑物（楼幢号）id
     */
    private String buildingId;

    /**
     * 单元id
     */
    private String unitId;

    /**
     * 户室id
     */
    private String roomId;

    /**
     * 坐标
     */
    private String location;

    /**
     * 状态
     */
    private Integer status;




}
