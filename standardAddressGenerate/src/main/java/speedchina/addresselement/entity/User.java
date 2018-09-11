package speedchina.addresselement.entity;

import java.io.Serializable;

/**
 * Created by ym on 2017/11/15.
 */
public class User implements Serializable{

    private String uid;
    private String username;
    private String password;
    private int rid;
    private String rname;
    private String zzjgid;
    private String dwdm;
    private String jwid;
    private String jyid;
    private String xm;
    private String sfzh;
    private String jh;
    private String zj;
    private String phone;
    /*移动端需要的接收机构中文名称字段*/
    private String zzjgmc;
    private String hm;
    private String bz;


    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getZj()
    {
        return zj;
    }

    public void setZj(String zj)
    {
        this.zj = zj;
    }

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public String getJwid() {
        return jwid;
    }

    public void setJwid(String jwid) {
        this.jwid = jwid;
    }

    public String getJyid() {
        return jyid;
    }

    public void setJyid(String jyid) {
        this.jyid = jyid;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getHm() {
        return hm;
    }

    public void setHm(String hm) {
        this.hm = hm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }


    public String getZzjgmc() {
        return zzjgmc;
    }

    public void setZzjgmc(String zzjgmc) {
        this.zzjgmc = zzjgmc;
    }
    //    private Set<Role> roles = new HashSet<>();

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getZzjgid() {
        return zzjgid;
    }

    public void setZzjgid(String zzjgid) {
        this.zzjgid = zzjgid;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getJh() {
        return jh;
    }

    public void setJh(String jh) {
        this.jh = jh;
    }

}
