package com.klsw.apiCommon.api.model2;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "CORP_INFO_CHANGE")
public class CorpInfoChange {
    @Column(name = "CORP_INFO_ID")
    private String corpInfoId;

    @Column(name = "ORGAN_CODE")
    private String organCode;

    @Column(name = "ENTITY_ID")
    private String entityId;

    @Column(name = "CORP_NAME")
    private String corpName;

    @Column(name = "CORP_TYPE")
    private String corpType;

    @Column(name = "PERSON_NAME")
    private String personName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "AREA_CODE")
    private String areaCode;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "ESTABLISH_DATE")
    private Date establishDate;

    @Column(name = "REG_CAPITAL3")
    private BigDecimal regCapital3;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "BUSINESS_SCOPE")
    private String businessScope;

    @Column(name = "PERSON_CERT_TYPE")
    private String personCertType;

    @Column(name = "PERSON_CERT_CODE")
    private String personCertCode;

    @Column(name = "INDUSTRY_CODE")
    private String industryCode;

    @Column(name = "ORGANIZERS")
    private String organizers;

    @Column(name = "FUNDING_SRC")
    private String fundingSrc;

    @Column(name = "REG_NO")
    private String regNo;

    @Column(name = "RECEIVING_ORGAN")
    private String receivingOrgan;

    @Column(name = "REPEAL_REASON")
    private String repealReason;

    @Column(name = "REPEAL_DATE")
    private Date repealDate;

    @Column(name = "CHANGE_DATE")
    private Date changeDate;

    @Column(name = "CHANGE_ITEM")
    private String changeItem;

    @Column(name = "REPEAL_ORGAN")
    private String repealOrgan;

    @Column(name = "BRANCH_NUM")
    private BigDecimal branchNum;

    @Column(name = "REPRESENT_NUM")
    private BigDecimal representNum;

    @Column(name = "REG_UPD_DATE")
    private Date regUpdDate;

    @Column(name = "TAXPAYERS_CODE")
    private String taxpayersCode;

    @Column(name = "TAX_CODE")
    private String taxCode;

    @Column(name = "TAX_REG_DATE")
    private Date taxRegDate;

    @Column(name = "TAX_CHGE_CONTENT")
    private String taxChgeContent;

    @Column(name = "TAX_CHGE_DATE")
    private Date taxChgeDate;

    @Column(name = "TAX_REPEAL_REASON")
    private String taxRepealReason;

    @Column(name = "TAX_REPEAL_DATE")
    private Date taxRepealDate;

    @Column(name = "TAX_REPEAL_ORGAN")
    private String taxRepealOrgan;

    @Column(name = "BUSINESS_ADDRESS")
    private String businessAddress;

    @Column(name = "TAX_UPD_DATE")
    private Date taxUpdDate;

    @Column(name = "ORGANCODE_DATE")
    private Date organcodeDate;

    @Column(name = "ORGCODE_CHGDATE")
    private Date orgcodeChgdate;

    @Column(name = "ORGCODE_REPEALDATE")
    private Date orgcodeRepealdate;

    @Column(name = "QS_UPD_DATE")
    private Date qsUpdDate;

    @Column(name = "BD_RESULT")
    private String bdResult;

    @Column(name = "QYK_ID")
    private String qykId;

    @Column(name = "UPD_TIME")
    private Date updTime;

    @Column(name = "TRIM_CORP_NAME")
    private String trimCorpName;

    @Column(name = "FUNDS_CODE")
    private String fundsCode;

    @Column(name = "FUNDS_OPEN_DATE")
    private Date fundsOpenDate;

    @Column(name = "FUNDS_REPEAL_DATE")
    private Date fundsRepealDate;

    @Column(name = "FUNDS_UPD_DATE")
    private Date fundsUpdDate;

    @Column(name = "FUNDSADD_CODE")
    private String fundsaddCode;

    @Column(name = "FUNDSADD_OPEN_DATE")
    private Date fundsaddOpenDate;

    @Column(name = "FUNDSADD_REPEAL_DATE")
    private Date fundsaddRepealDate;

    @Column(name = "FUNDSADD_UPD_DATE")
    private Date fundsaddUpdDate;

    @Column(name = "SOCIAL_SECURITY_CODE")
    private String socialSecurityCode;

    @Column(name = "SOCIAL_SECURITY_OPEN_DATE")
    private Date socialSecurityOpenDate;

    @Column(name = "SOCIAL_SECURITY_REPEAL_DATE")
    private Date socialSecurityRepealDate;

    @Column(name = "SOCIAL_SECURITY_UPD_DATE")
    private Date socialSecurityUpdDate;

    @Column(name = "LK_STATUS")
    private String lkStatus;

    @Column(name = "IS_ZMQ")
    private String isZmq;

    @Column(name = "INSERT_TIME")
    private Date insertTime;

    @Column(name = "CORP_STATUS")
    private String corpStatus;

    @Column(name = "IS_GSL")
    private String isGsl;

    @Column(name = "IS_RECENTLY_ZMQ")
    private String isRecentlyZmq;

    @Column(name = "IS_WEB_SEND")
    private String isWebSend;

    @Column(name = "UNI_SC_ID")
    private String uniScId;

    @Column(name = "REG_CAPITAL")
    private BigDecimal regCapital;

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
     * @return ORGAN_CODE
     */
    public String getOrganCode() {
        return organCode;
    }

    /**
     * @param organCode
     */
    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    /**
     * @return ENTITY_ID
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * @param entityId
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    /**
     * @return CORP_NAME
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * @param corpName
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
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
     * @return PERSON_NAME
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * @param personName
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return AREA_CODE
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return ZIP
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * @return TELEPHONE
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * @return CURRENCY
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return BUSINESS_SCOPE
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * @param businessScope
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    /**
     * @return PERSON_CERT_TYPE
     */
    public String getPersonCertType() {
        return personCertType;
    }

    /**
     * @param personCertType
     */
    public void setPersonCertType(String personCertType) {
        this.personCertType = personCertType;
    }

    /**
     * @return PERSON_CERT_CODE
     */
    public String getPersonCertCode() {
        return personCertCode;
    }

    /**
     * @param personCertCode
     */
    public void setPersonCertCode(String personCertCode) {
        this.personCertCode = personCertCode;
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
     * @return ORGANIZERS
     */
    public String getOrganizers() {
        return organizers;
    }

    /**
     * @param organizers
     */
    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    /**
     * @return FUNDING_SRC
     */
    public String getFundingSrc() {
        return fundingSrc;
    }

    /**
     * @param fundingSrc
     */
    public void setFundingSrc(String fundingSrc) {
        this.fundingSrc = fundingSrc;
    }

    /**
     * @return REG_NO
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * @param regNo
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * @return RECEIVING_ORGAN
     */
    public String getReceivingOrgan() {
        return receivingOrgan;
    }

    /**
     * @param receivingOrgan
     */
    public void setReceivingOrgan(String receivingOrgan) {
        this.receivingOrgan = receivingOrgan;
    }

    /**
     * @return REPEAL_REASON
     */
    public String getRepealReason() {
        return repealReason;
    }

    /**
     * @param repealReason
     */
    public void setRepealReason(String repealReason) {
        this.repealReason = repealReason;
    }

    /**
     * @return REPEAL_DATE
     */
    public Date getRepealDate() {
        return repealDate;
    }

    /**
     * @param repealDate
     */
    public void setRepealDate(Date repealDate) {
        this.repealDate = repealDate;
    }

    /**
     * @return CHANGE_DATE
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * @param changeDate
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * @return CHANGE_ITEM
     */
    public String getChangeItem() {
        return changeItem;
    }

    /**
     * @param changeItem
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    /**
     * @return REPEAL_ORGAN
     */
    public String getRepealOrgan() {
        return repealOrgan;
    }

    /**
     * @param repealOrgan
     */
    public void setRepealOrgan(String repealOrgan) {
        this.repealOrgan = repealOrgan;
    }

    /**
     * @return BRANCH_NUM
     */
    public BigDecimal getBranchNum() {
        return branchNum;
    }

    /**
     * @param branchNum
     */
    public void setBranchNum(BigDecimal branchNum) {
        this.branchNum = branchNum;
    }

    /**
     * @return REPRESENT_NUM
     */
    public BigDecimal getRepresentNum() {
        return representNum;
    }

    /**
     * @param representNum
     */
    public void setRepresentNum(BigDecimal representNum) {
        this.representNum = representNum;
    }

    /**
     * @return REG_UPD_DATE
     */
    public Date getRegUpdDate() {
        return regUpdDate;
    }

    /**
     * @param regUpdDate
     */
    public void setRegUpdDate(Date regUpdDate) {
        this.regUpdDate = regUpdDate;
    }

    /**
     * @return TAXPAYERS_CODE
     */
    public String getTaxpayersCode() {
        return taxpayersCode;
    }

    /**
     * @param taxpayersCode
     */
    public void setTaxpayersCode(String taxpayersCode) {
        this.taxpayersCode = taxpayersCode;
    }

    /**
     * @return TAX_CODE
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * @param taxCode
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * @return TAX_REG_DATE
     */
    public Date getTaxRegDate() {
        return taxRegDate;
    }

    /**
     * @param taxRegDate
     */
    public void setTaxRegDate(Date taxRegDate) {
        this.taxRegDate = taxRegDate;
    }

    /**
     * @return TAX_CHGE_CONTENT
     */
    public String getTaxChgeContent() {
        return taxChgeContent;
    }

    /**
     * @param taxChgeContent
     */
    public void setTaxChgeContent(String taxChgeContent) {
        this.taxChgeContent = taxChgeContent;
    }

    /**
     * @return TAX_CHGE_DATE
     */
    public Date getTaxChgeDate() {
        return taxChgeDate;
    }

    /**
     * @param taxChgeDate
     */
    public void setTaxChgeDate(Date taxChgeDate) {
        this.taxChgeDate = taxChgeDate;
    }

    /**
     * @return TAX_REPEAL_REASON
     */
    public String getTaxRepealReason() {
        return taxRepealReason;
    }

    /**
     * @param taxRepealReason
     */
    public void setTaxRepealReason(String taxRepealReason) {
        this.taxRepealReason = taxRepealReason;
    }

    /**
     * @return TAX_REPEAL_DATE
     */
    public Date getTaxRepealDate() {
        return taxRepealDate;
    }

    /**
     * @param taxRepealDate
     */
    public void setTaxRepealDate(Date taxRepealDate) {
        this.taxRepealDate = taxRepealDate;
    }

    /**
     * @return TAX_REPEAL_ORGAN
     */
    public String getTaxRepealOrgan() {
        return taxRepealOrgan;
    }

    /**
     * @param taxRepealOrgan
     */
    public void setTaxRepealOrgan(String taxRepealOrgan) {
        this.taxRepealOrgan = taxRepealOrgan;
    }

    /**
     * @return BUSINESS_ADDRESS
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * @param businessAddress
     */
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    /**
     * @return TAX_UPD_DATE
     */
    public Date getTaxUpdDate() {
        return taxUpdDate;
    }

    /**
     * @param taxUpdDate
     */
    public void setTaxUpdDate(Date taxUpdDate) {
        this.taxUpdDate = taxUpdDate;
    }

    /**
     * @return ORGANCODE_DATE
     */
    public Date getOrgancodeDate() {
        return organcodeDate;
    }

    /**
     * @param organcodeDate
     */
    public void setOrgancodeDate(Date organcodeDate) {
        this.organcodeDate = organcodeDate;
    }

    /**
     * @return ORGCODE_CHGDATE
     */
    public Date getOrgcodeChgdate() {
        return orgcodeChgdate;
    }

    /**
     * @param orgcodeChgdate
     */
    public void setOrgcodeChgdate(Date orgcodeChgdate) {
        this.orgcodeChgdate = orgcodeChgdate;
    }

    /**
     * @return ORGCODE_REPEALDATE
     */
    public Date getOrgcodeRepealdate() {
        return orgcodeRepealdate;
    }

    /**
     * @param orgcodeRepealdate
     */
    public void setOrgcodeRepealdate(Date orgcodeRepealdate) {
        this.orgcodeRepealdate = orgcodeRepealdate;
    }

    /**
     * @return QS_UPD_DATE
     */
    public Date getQsUpdDate() {
        return qsUpdDate;
    }

    /**
     * @param qsUpdDate
     */
    public void setQsUpdDate(Date qsUpdDate) {
        this.qsUpdDate = qsUpdDate;
    }

    /**
     * @return BD_RESULT
     */
    public String getBdResult() {
        return bdResult;
    }

    /**
     * @param bdResult
     */
    public void setBdResult(String bdResult) {
        this.bdResult = bdResult;
    }

    /**
     * @return QYK_ID
     */
    public String getQykId() {
        return qykId;
    }

    /**
     * @param qykId
     */
    public void setQykId(String qykId) {
        this.qykId = qykId;
    }

    /**
     * @return UPD_TIME
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * @param updTime
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * @return TRIM_CORP_NAME
     */
    public String getTrimCorpName() {
        return trimCorpName;
    }

    /**
     * @param trimCorpName
     */
    public void setTrimCorpName(String trimCorpName) {
        this.trimCorpName = trimCorpName;
    }

    /**
     * @return FUNDS_CODE
     */
    public String getFundsCode() {
        return fundsCode;
    }

    /**
     * @param fundsCode
     */
    public void setFundsCode(String fundsCode) {
        this.fundsCode = fundsCode;
    }

    /**
     * @return FUNDS_OPEN_DATE
     */
    public Date getFundsOpenDate() {
        return fundsOpenDate;
    }

    /**
     * @param fundsOpenDate
     */
    public void setFundsOpenDate(Date fundsOpenDate) {
        this.fundsOpenDate = fundsOpenDate;
    }

    /**
     * @return FUNDS_REPEAL_DATE
     */
    public Date getFundsRepealDate() {
        return fundsRepealDate;
    }

    /**
     * @param fundsRepealDate
     */
    public void setFundsRepealDate(Date fundsRepealDate) {
        this.fundsRepealDate = fundsRepealDate;
    }

    /**
     * @return FUNDS_UPD_DATE
     */
    public Date getFundsUpdDate() {
        return fundsUpdDate;
    }

    /**
     * @param fundsUpdDate
     */
    public void setFundsUpdDate(Date fundsUpdDate) {
        this.fundsUpdDate = fundsUpdDate;
    }

    /**
     * @return FUNDSADD_CODE
     */
    public String getFundsaddCode() {
        return fundsaddCode;
    }

    /**
     * @param fundsaddCode
     */
    public void setFundsaddCode(String fundsaddCode) {
        this.fundsaddCode = fundsaddCode;
    }

    /**
     * @return FUNDSADD_OPEN_DATE
     */
    public Date getFundsaddOpenDate() {
        return fundsaddOpenDate;
    }

    /**
     * @param fundsaddOpenDate
     */
    public void setFundsaddOpenDate(Date fundsaddOpenDate) {
        this.fundsaddOpenDate = fundsaddOpenDate;
    }

    /**
     * @return FUNDSADD_REPEAL_DATE
     */
    public Date getFundsaddRepealDate() {
        return fundsaddRepealDate;
    }

    /**
     * @param fundsaddRepealDate
     */
    public void setFundsaddRepealDate(Date fundsaddRepealDate) {
        this.fundsaddRepealDate = fundsaddRepealDate;
    }

    /**
     * @return FUNDSADD_UPD_DATE
     */
    public Date getFundsaddUpdDate() {
        return fundsaddUpdDate;
    }

    /**
     * @param fundsaddUpdDate
     */
    public void setFundsaddUpdDate(Date fundsaddUpdDate) {
        this.fundsaddUpdDate = fundsaddUpdDate;
    }

    /**
     * @return SOCIAL_SECURITY_CODE
     */
    public String getSocialSecurityCode() {
        return socialSecurityCode;
    }

    /**
     * @param socialSecurityCode
     */
    public void setSocialSecurityCode(String socialSecurityCode) {
        this.socialSecurityCode = socialSecurityCode;
    }

    /**
     * @return SOCIAL_SECURITY_OPEN_DATE
     */
    public Date getSocialSecurityOpenDate() {
        return socialSecurityOpenDate;
    }

    /**
     * @param socialSecurityOpenDate
     */
    public void setSocialSecurityOpenDate(Date socialSecurityOpenDate) {
        this.socialSecurityOpenDate = socialSecurityOpenDate;
    }

    /**
     * @return SOCIAL_SECURITY_REPEAL_DATE
     */
    public Date getSocialSecurityRepealDate() {
        return socialSecurityRepealDate;
    }

    /**
     * @param socialSecurityRepealDate
     */
    public void setSocialSecurityRepealDate(Date socialSecurityRepealDate) {
        this.socialSecurityRepealDate = socialSecurityRepealDate;
    }

    /**
     * @return SOCIAL_SECURITY_UPD_DATE
     */
    public Date getSocialSecurityUpdDate() {
        return socialSecurityUpdDate;
    }

    /**
     * @param socialSecurityUpdDate
     */
    public void setSocialSecurityUpdDate(Date socialSecurityUpdDate) {
        this.socialSecurityUpdDate = socialSecurityUpdDate;
    }

    /**
     * @return LK_STATUS
     */
    public String getLkStatus() {
        return lkStatus;
    }

    /**
     * @param lkStatus
     */
    public void setLkStatus(String lkStatus) {
        this.lkStatus = lkStatus;
    }

    /**
     * @return IS_ZMQ
     */
    public String getIsZmq() {
        return isZmq;
    }

    /**
     * @param isZmq
     */
    public void setIsZmq(String isZmq) {
        this.isZmq = isZmq;
    }

    /**
     * @return INSERT_TIME
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * @param insertTime
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
     * @return IS_GSL
     */
    public String getIsGsl() {
        return isGsl;
    }

    /**
     * @param isGsl
     */
    public void setIsGsl(String isGsl) {
        this.isGsl = isGsl;
    }

    /**
     * @return IS_RECENTLY_ZMQ
     */
    public String getIsRecentlyZmq() {
        return isRecentlyZmq;
    }

    /**
     * @param isRecentlyZmq
     */
    public void setIsRecentlyZmq(String isRecentlyZmq) {
        this.isRecentlyZmq = isRecentlyZmq;
    }

    /**
     * @return IS_WEB_SEND
     */
    public String getIsWebSend() {
        return isWebSend;
    }

    /**
     * @param isWebSend
     */
    public void setIsWebSend(String isWebSend) {
        this.isWebSend = isWebSend;
    }

    /**
     * @return UNI_SC_ID
     */
    public String getUniScId() {
        return uniScId;
    }

    /**
     * @param uniScId
     */
    public void setUniScId(String uniScId) {
        this.uniScId = uniScId;
    }

    /**
     * @return REG_CAPITAL
     */
    public BigDecimal getRegCapital() {
        return regCapital;
    }

    /**
     * @param regCapital
     */
    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }
}