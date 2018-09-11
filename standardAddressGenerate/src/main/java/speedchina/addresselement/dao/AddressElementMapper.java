package speedchina.addresselement.dao;

import org.apache.ibatis.annotations.*;
import speedchina.addresselement.entity.AddressElement;
import speedchina.addresselement.entity.StandardAddress;
import speedchina.addresselement.mapper.SpeedMapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * AddressElementMapper
 * @author 11852
 */
@Mapper
public interface AddressElementMapper extends SpeedMapper<AddressElement> {


    /**
     * 获取所有地址元素（不包含市）
     * @return
     */
    @Select("SELECT * FROM address_element_processing where address_element_type != 14;")
    List<AddressElement> getAllElement();

    /**
     * 获取市
     * @return
     */
    @Select("SELECT * FROM address_element_processing where address_element_type = 14;")
    AddressElement getCity();


    /**
     * 标准地址批量保存
     * @param standardAddresses
     */
    @InsertProvider(type = ConfigDaoProvider.class, method = "insertStandardAddressList")
    void saveAll(@Param("list") Collection<StandardAddress> standardAddresses);












}
