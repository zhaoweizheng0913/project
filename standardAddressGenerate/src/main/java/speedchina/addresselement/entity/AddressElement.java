package speedchina.addresselement.entity;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;

/**
 * AddressElement entity
 * @author 11852
 */
@Data
@Table(name = "address_element_processing")
public class AddressElement {

    /**
     * 主键id
     */
    private String id;

    /**
     * 上级元素id
     */
    private String parentId;

    /**
     * 元素名称
     */
    private String addressElementName;

    /**
     * 类别代码
     */
    private int addressElementType;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 经度
     */
    private Double lng;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 组织机构代码
     */
    private String organizationCode;

    /**
     *
     */
    @Transient
    private Set<AddressElement> children;


}
