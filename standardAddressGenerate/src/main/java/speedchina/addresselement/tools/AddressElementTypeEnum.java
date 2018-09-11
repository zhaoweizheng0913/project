package speedchina.addresselement.tools;

/**
 * @author 11852
 */
public enum AddressElementTypeEnum {

    /**
     * 地址元素类型枚举类
     */
    SHENG(11,""),SHI(14,""),XIAN(15,""),QU(16,""),XIANGZHENJIEDAO(20,""),JIANZHIXIANG(21,""),JIANZHIZHEN(22,""),
    JIEDAOBANSHICHU(23,""),SHEQUJUCUNWEIHUI(30,""),SHEQU(31,""),JUWEIHUI(32,""),CUNWEIHUI(33,""),ZU(34,"组"),
    JIELUXIANGXIAOQU(40,""),JIELUXIANG(41,""),XIAOQU(42,""),ZIRANCUN(43,""),MENPAIHAO(44,"号"),JIANZHUWU(50,"幢"),
    DANYUAN(51,"单元"),CENG(52,"层"),HUSHI(53,"室"),QITA(90,"");

    /**
     * 类别代码
     */
    public Integer typeCode;

    /**
     * 后缀
     */
    public String suffix;

    AddressElementTypeEnum(Integer typeCode, String suffix) {
        this.typeCode = typeCode;
        this.suffix = suffix;
    }
}
