package speedchina.addresselement.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Organization entity
 * @author 11852
 */
@Data
@Table(name="sp_sys_organization")
public class Organization {

    /**
     * 标识唯一id
     */
    @Id
    private String id;

    /**
     * 机构编码
     */
    private String organizationCode;

    /**
     * 机构简称
     */
    private String shortName;




}
