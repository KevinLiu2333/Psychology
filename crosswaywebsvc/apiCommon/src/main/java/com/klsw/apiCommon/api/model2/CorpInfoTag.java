package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "CORP_INFO_TAG")
public class CorpInfoTag {
    @Column(name = "CORP_INFO_ID")
    private String corpInfoId;

    @Column(name = "CORP_TYPE")
    private String corpType;

    @Column(name = "CORP_TYPE_NAME")
    private String corpTypeName;

    @Column(name = "JDDM")
    private String jddm;

    @Column(name = "JDDM_NAME")
    private String jddmName;

    @Column(name = "CORP_STATUS")
    private String corpStatus;

    @Column(name = "CORP_STATUS_NAME")
    private String corpStatusName;

    @Column(name = "ESTABLISH_DATE")
    private Date establishDate;

    @Column(name = "INDUSTRY_CODE")
    private String industryCode;

    @Column(name = "INDUSTRY_CODE_NAME")
    private String industryCodeName;

    @Column(name = "REG_CAPITAL3")
    private BigDecimal regCapital3;

    @Column(name = "CAPITAL_RANGE")
    private String capitalRange;

    @Column(name = "IS_XJR")
    private String isXjr;

    @Column(name = "IS_GQ")
    private String isGq;

    @Column(name = "IS_LHRH")
    private String isLhrh;

    @Column(name = "IS_LICENSE")
    private String isLicense;

    @Column(name = "IS_PUNISH")
    private String isPunish;

    /**
     * @return CORP_INFO_ID
     */
    public String getCorpInfoId() {
        return corpInfoId;
    }

    /**
     * @param corpInfoId
     */
    public void setCorpInfoId(String corpInfoId) {
        this.corpInfoId = corpInfoId;
    }

    /**
     * @return CORP_TYPE
     */
    public String getCorpType() {
        return corpType;
    }

    /**
     * @param corpType
     */
    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    /**
     * @return CORP_TYPE_NAME
     */
    public String getCorpTypeName() {
        return corpTypeName;
    }

    /**
     * @param corpTypeName
     */
    public void setCorpTypeName(String corpTypeName) {
        this.corpTypeName = corpTypeName;
    }

    /**
     * @return JDDM
     */
    public String getJddm() {
        return jddm;
    }

    /**
     * @param jddm
     */
    public void setJddm(String jddm) {
        this.jddm = jddm;
    }

    /**
     * @return JDDM_NAME
     */
    public String getJddmName() {
        return jddmName;
    }

    /**
     * @param jddmName
     */
    public void setJddmName(String jddmName) {
        this.jddmName = jddmName;
    }

    /**
     * @return CORP_STATUS
     */
    public String getCorpStatus() {
        return corpStatus;
    }

    /**
     * @param corpStatus
     */
    public void setCorpStatus(String corpStatus) {
        this.corpStatus = corpStatus;
    }

    /**
     * @return CORP_STATUS_NAME
     */
    public String getCorpStatusName() {
        return corpStatusName;
    }

    /**
     * @param corpStatusName
     */
    public void setCorpStatusName(String corpStatusName) {
        this.corpStatusName = corpStatusName;
    }

    /**
     * @return ESTABLISH_DATE
     */
    public Date getEstablishDate() {
        return establishDate;
    }

    /**
     * @param establishDate
     */
    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    /**
     * @return INDUSTRY_CODE
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * @param industryCode
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    /**
     * @return INDUSTRY_CODE_NAME
     */
    public String getIndustryCodeName() {
        return industryCodeName;
    }

    /**
     * @param industryCodeName
     */
    public void setIndustryCodeName(String industryCodeName) {
        this.industryCodeName = industryCodeName;
    }

    /**
     * @return REG_CAPITAL3
     */
    public BigDecimal getRegCapital3() {
        return regCapital3;
    }

    /**
     * @param regCapital3
     */
    public void setRegCapital3(BigDecimal regCapital3) {
        this.regCapital3 = regCapital3;
    }

    /**
     * @return CAPITAL_RANGE
     */
    public String getCapitalRange() {
        return capitalRange;
    }

    /**
     * @param capitalRange
     */
    public void setCapitalRange(String capitalRange) {
        this.capitalRange = capitalRange;
    }

    /**
     * @return IS_XJR
     */
    public String getIsXjr() {
        return isXjr;
    }

    /**
     * @param isXjr
     */
    public void setIsXjr(String isXjr) {
        this.isXjr = isXjr;
    }

    /**
     * @return IS_GQ
     */
    public String getIsGq() {
        return isGq;
    }

    /**
     * @param isGq
     */
    public void setIsGq(String isGq) {
        this.isGq = isGq;
    }

    /**
     * @return IS_LHRH
     */
    public String getIsLhrh() {
        return isLhrh;
    }

    /**
     * @param isLhrh
     */
    public void setIsLhrh(String isLhrh) {
        this.isLhrh = isLhrh;
    }

    /**
     * @return IS_LICENSE
     */
    public String getIsLicense() {
        return isLicense;
    }

    /**
     * @param isLicense
     */
    public void setIsLicense(String isLicense) {
        this.isLicense = isLicense;
    }

    /**
     * @return IS_PUNISH
     */
    public String getIsPunish() {
        return isPunish;
    }

    /**
     * @param isPunish
     */
    public void setIsPunish(String isPunish) {
        this.isPunish = isPunish;
    }
}