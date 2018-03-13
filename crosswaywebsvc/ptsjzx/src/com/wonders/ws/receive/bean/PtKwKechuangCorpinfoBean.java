package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

import java.util.Date;

@Table("PT_KW_KECHUANG_CORPINFO")
public class PtKwKechuangCorpinfoBean {
    /**
     * 主键ID
     **/
    @Column("ID")
    private String id;
    /**
     * 企业类型（1：企业；2：载体；3：3.第三方服务机构）
     **/
    @Column("COMPANY_TYPE")
    private String companytype;
    /**
     * 经营范围
     **/
    @Column("COMPANY_SCOPE")
    private String companyscope;
    /**
     * 联系人
     **/
    @Column("CONTACT_NAME")
    private String contactname;
    /**
     * 联系电话
     **/
    @Column("CONTACT_TEL")
    private String contacttel;
    /**
     * 企业成果
     **/
    @Column("COMPANY_ACHIEVEMENT")
    private String companyachievement;

    @Column("SELF_INSERT_DATE")
    private Date selfInsertDate;

    @Column("SELF_UPDATE_DATE")
    private Date selfUpdateDate;

    @Column("ORAGN_CODE")
    private String organCode;

    @Column("UNI_SC_ID")
    private String uniscid;

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getUniscid() {
        return uniscid;
    }

    public void setUniscid(String uniscid) {
        this.uniscid = uniscid;
    }

    public Date getSelfInsertDate() {
        return selfInsertDate;
    }

    public void setSelfInsertDate(Date selfInsertDate) {
        this.selfInsertDate = selfInsertDate;
    }

    public Date getSelfUpdateDate() {
        return selfUpdateDate;
    }

    public void setSelfUpdateDate(Date selfUpdateDate) {
        this.selfUpdateDate = selfUpdateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getCompanyscope() {
        return companyscope;
    }

    public void setCompanyscope(String companyscope) {
        this.companyscope = companyscope;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContacttel() {
        return contacttel;
    }

    public void setContacttel(String contacttel) {
        this.contacttel = contacttel;
    }

    public String getCompanyachievement() {
        return companyachievement;
    }

    public void setCompanyachievement(String companyachievement) {
        this.companyachievement = companyachievement;
    }

}
