package speedchina.addresselement.entity;

import lombok.Data;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 11852
 */
@Data
@Table(name="sp_sys_organization")
public class Organization implements Serializable {

    /**
     * 标识唯一id
     */
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
