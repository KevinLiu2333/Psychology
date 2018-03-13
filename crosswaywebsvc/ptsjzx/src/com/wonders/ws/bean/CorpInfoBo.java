package com.wonders.ws.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

import java.math.BigDecimal;
import java.util.Date;

@Table("CORP_INFO")
public class CorpInfoBo {
    /**
     * 数据中心法人实体序号
     */
    @Name
    @Column("CORP_INFO_ID")
    private String corpInfoId;

    /**
     * 组织机构代码
     */
    @Column("ORGAN_CODE")
    private String organCode;

    /**
     * 标识
     */
    @Column("ENTITY_ID")
    private String entityId;

    /**
     * 法人名称
     */
    @Column("CORP_NAME")
    private String corpName;

    /**
     * 法人类型
     */
    @Column("CORP_TYPE")
    private String corpType;

    /**
     * 法定代表人
     */
    @Column("PERSON_NAME")
    private String personName;

    /**
     * 经营场所
     */
    @Column("ADDRESS")
    private String address;

    /**
     * 区划
     */
    @Column("AREA_CODE")
    private String areaCode;

    /**
     * 邮编
     */
    @Column("ZIP")
    private String zip;

    /**
     * 联系电话
     */
    @Column("TELEPHONE")
    private String telephone;

    /**
     * 成立日期
     */
    @Column("ESTABLISH_DATE")
    private Date establishDate;

    /**
     * 开办资金
     */
    @Column("REG_CAPITAL")
    private BigDecimal regCapital;

    /**
     * 币种
     */
    @Column("CURRENCY")
    private String currency;

    /**
     * 业务范围
     */
    @Column("BUSINESS_SCOPE")
    private String businessScope;

    /**
     * 法定代表人证件类型
     */
    @Column("PERSON_CERT_TYPE")
    private String personCertType;

    /**
     * 法定代表人证件号
     */
    @Column("PERSON_CERT_CODE")
    private String personCertCode;

    /**
     * 行业类别
     */
    @Column("INDUSTRY_CODE")
    private String industryCode;

    /**
     * 业务主管单位
     */
    @Column("ORGANIZERS")
    private String organizers;

    /**
     * 经费来源
     */
    @Column("FUNDING_SRC")
    private String fundingSrc;

    /**
     * 注册号
     */
    @Column("REG_NO")
    private String regNo;

    /**
     * 受理机关代码
     */
    @Column("RECEIVING_ORGAN")
    private String receivingOrgan;

    /**
     * 法人注销原因
     */
    @Column("REPEAL_REASON")
    private String repealReason;

    /**
     * 法人注销日期
     */
    @Column("REPEAL_DATE")
    private Date repealDate;

    /**
     * 法人变更日期
     */
    @Column("CHANGE_DATE")
    private Date changeDate;

    /**
     * 法人变更登记事项
     */
    @Column("CHANGE_ITEM")
    private String changeItem;

    /**
     * 法人注销机关
     */
    @Column("REPEAL_ORGAN")
    private String repealOrgan;

    /**
     * 分支机构数（社会组织）
     */
    @Column("BRANCH_NUM")
    private BigDecimal branchNum;

    /**
     * 代表机构数（社会组织）
     */
    @Column("REPRESENT_NUM")
    private BigDecimal representNum;

    /**
     * 登记类业务发布时间
     */
    @Column("REG_UPD_DATE")
    private Date regUpdDate;

    /**
     * 纳税人识别号
     */
    @Column("TAXPAYERS_CODE")
    private String taxpayersCode;

    /**
     * 组合位置编码
     */
    @Column("TAX_CODE")
    private String taxCode;

    /**
     * 税务登记日期
     */
    @Column("TAX_REG_DATE")
    private Date taxRegDate;

    /**
     * 税务变更内容
     */
    @Column("TAX_CHGE_CONTENT")
    private String taxChgeContent;

    /**
     * 税务变更日期
     */
    @Column("TAX_CHGE_DATE")
    private Date taxChgeDate;

    /**
     * 税务注销原因
     */
    @Column("TAX_REPEAL_REASON")
    private String taxRepealReason;

    /**
     * 税务注销日期
     */
    @Column("TAX_REPEAL_DATE")
    private Date taxRepealDate;

    /**
     * 税务注销机关
     */
    @Column("TAX_REPEAL_ORGAN")
    private String taxRepealOrgan;

    /**
     * 实际经营地址
     */
    @Column("BUSINESS_ADDRESS")
    private String businessAddress;

    /**
     * 税务类业务发布时间
     */
    @Column("TAX_UPD_DATE")
    private Date taxUpdDate;

    /**
     * 组织机构代码赋码日期
     */
    @Column("ORGANCODE_DATE")
    private Date organcodeDate;

    /**
     * 组织机构代码变更日期
     */
    @Column("ORGCODE_CHGDATE")
    private Date orgcodeChgdate;

    /**
     * 组织机构代码注销日期
     */
    @Column("ORGCODE_REPEALDATE")
    private Date orgcodeRepealdate;

    /**
     * 质监类业务发布时间
     */
    @Column("QS_UPD_DATE")
    private Date qsUpdDate;

    /**
     * 比对结果
     */
    @Column("BD_RESULT")
    private String bdResult;

    /**
     * QYK_ID
     */
    @Column("QYK_ID")
    private String qykId;

    /**
     * 更新日期
     */
    @Column("UPD_TIME")
    private Date updTime;

    /**
     * TRIM_CORP_NAME
     */
    @Column("TRIM_CORP_NAME")
    private String trimCorpName;

    /**
     * 公积金账号
     */
    @Column("FUNDS_CODE")
    private String fundsCode;

    /**
     * 公积金开户日期
     */
    @Column("FUNDS_OPEN_DATE")
    private Date fundsOpenDate;

    /**
     * 公积金注销日期
     */
    @Column("FUNDS_REPEAL_DATE")
    private Date fundsRepealDate;

    /**
     * 公积金信息发布时间
     */
    @Column("FUNDS_UPD_DATE")
    private Date fundsUpdDate;

    /**
     * 补充公积金账号
     */
    @Column("FUNDSADD_CODE")
    private String fundsaddCode;

    /**
     * 补充公积金开户日期
     */
    @Column("FUNDSADD_OPEN_DATE")
    private Date fundsaddOpenDate;

    /**
     * 补充公积金注销日期
     */
    @Column("FUNDSADD_REPEAL_DATE")
    private Date fundsaddRepealDate;

    /**
     * 补充公积金信息发布时间
     */
    @Column("FUNDSADD_UPD_DATE")
    private Date fundsaddUpdDate;

    /**
     * 社会保险账号
     */
    @Column("SOCIAL_SECURITY_CODE")
    private String socialSecurityCode;

    /**
     * 社会保险帐号注销日期
     */
    @Column("SOCIAL_SECURITY_OPEN_DATE")
    private Date socialSecurityOpenDate;

    /**
     * 社保信息发布时间
     */
    @Column("SOCIAL_SECURITY_REPEAL_DATE")
    private Date socialSecurityRepealDate;

    /**
     * 社会保险帐号开设日期
     */
    @Column("SOCIAL_SECURITY_UPD_DATE")
    private Date socialSecurityUpdDate;

    /**
     * 离开状态
     */
    @Column("LK_STATUS")
    private String lkStatus;

    /**
     * 是否自贸区
     */
    @Column("IS_ZMQ")
    private String isZmq;

    /**
     * 插入时间
     */
    @Column("INSERT_TIME")
    private Date insertTime;

    /**
     * 法人状态
     */
    @Column("CORP_STATUS")
    private String corpStatus;

    /**
     * 是否工商联
     */
    @Column("IS_GSL")
    private String isGsl;

    /**
     * IS_RECENTLY_ZMQ
     */
    @Column("IS_RECENTLY_ZMQ")
    private String isRecentlyZmq;

    /**
     * IS_WEB_SEND
     */
    @Column("IS_WEB_SEND")
    private String isWebSend;

    /**
     * 统一社会信用代码
     */
    @Column("UNI_SC_ID")
    private String uniScId;

    /**
     * 获取数据中心法人实体序号
     *
     * @return CORP_INFO_ID - 数据中心法人实体序号
     */
    public String getCorpInfoId() {
        return corpInfoId;
    }

    /**
     * 设置数据中心法人实体序号
     *
     * @param corpInfoId 数据中心法人实体序号
     */
    public void setCorpInfoId(String corpInfoId) {
        this.corpInfoId = corpInfoId;
    }

    /**
     * 获取组织机构代码
     *
     * @return ORGAN_CODE - 组织机构代码
     */
    public String getOrganCode() {
        return organCode;
    }

    /**
     * 设置组织机构代码
     *
     * @param organCode 组织机构代码
     */
    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    /**
     * 获取标识
     *
     * @return ENTITY_ID - 标识
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * 设置标识
     *
     * @param entityId 标识
     */
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    /**
     * 获取法人名称
     *
     * @return CORP_NAME - 法人名称
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 设置法人名称
     *
     * @param corpName 法人名称
     */
    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    /**
     * 获取法人类型
     *
     * @return CORP_TYPE - 法人类型
     */
    public String getCorpType() {
        return corpType;
    }

    /**
     * 设置法人类型
     *
     * @param corpType 法人类型
     */
    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    /**
     * 获取法定代表人
     *
     * @return PERSON_NAME - 法定代表人
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * 设置法定代表人
     *
     * @param personName 法定代表人
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * 获取经营场所
     *
     * @return ADDRESS - 经营场所
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置经营场所
     *
     * @param address 经营场所
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取区划
     *
     * @return AREA_CODE - 区划
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置区划
     *
     * @param areaCode 区划
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * 获取邮编
     *
     * @return ZIP - 邮编
     */
    public String getZip() {
        return zip;
    }

    /**
     * 设置邮编
     *
     * @param zip 邮编
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     * 获取联系电话
     *
     * @return TELEPHONE - 联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置联系电话
     *
     * @param telephone 联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取成立日期
     *
     * @return ESTABLISH_DATE - 成立日期
     */
    public Date getEstablishDate() {
        return establishDate;
    }

    /**
     * 设置成立日期
     *
     * @param establishDate 成立日期
     */
    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    /**
     * 获取开办资金
     *
     * @return REG_CAPITAL - 开办资金
     */
    public BigDecimal getRegCapital() {
        return regCapital;
    }

    /**
     * 设置开办资金
     *
     * @param regCapital 开办资金
     */
    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    /**
     * 获取币种
     *
     * @return CURRENCY - 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置币种
     *
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * 获取业务范围
     *
     * @return BUSINESS_SCOPE - 业务范围
     */
    public String getBusinessScope() {
        return businessScope;
    }

    /**
     * 设置业务范围
     *
     * @param businessScope 业务范围
     */
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }

    /**
     * 获取法定代表人证件类型
     *
     * @return PERSON_CERT_TYPE - 法定代表人证件类型
     */
    public String getPersonCertType() {
        return personCertType;
    }

    /**
     * 设置法定代表人证件类型
     *
     * @param personCertType 法定代表人证件类型
     */
    public void setPersonCertType(String personCertType) {
        this.personCertType = personCertType;
    }

    /**
     * 获取法定代表人证件号
     *
     * @return PERSON_CERT_CODE - 法定代表人证件号
     */
    public String getPersonCertCode() {
        return personCertCode;
    }

    /**
     * 设置法定代表人证件号
     *
     * @param personCertCode 法定代表人证件号
     */
    public void setPersonCertCode(String personCertCode) {
        this.personCertCode = personCertCode;
    }

    /**
     * 获取行业类别
     *
     * @return INDUSTRY_CODE - 行业类别
     */
    public String getIndustryCode() {
        return industryCode;
    }

    /**
     * 设置行业类别
     *
     * @param industryCode 行业类别
     */
    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    /**
     * 获取业务主管单位
     *
     * @return ORGANIZERS - 业务主管单位
     */
    public String getOrganizers() {
        return organizers;
    }

    /**
     * 设置业务主管单位
     *
     * @param organizers 业务主管单位
     */
    public void setOrganizers(String organizers) {
        this.organizers = organizers;
    }

    /**
     * 获取经费来源
     *
     * @return FUNDING_SRC - 经费来源
     */
    public String getFundingSrc() {
        return fundingSrc;
    }

    /**
     * 设置经费来源
     *
     * @param fundingSrc 经费来源
     */
    public void setFundingSrc(String fundingSrc) {
        this.fundingSrc = fundingSrc;
    }

    /**
     * 获取注册号
     *
     * @return REG_NO - 注册号
     */
    public String getRegNo() {
        return regNo;
    }

    /**
     * 设置注册号
     *
     * @param regNo 注册号
     */
    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    /**
     * 获取受理机关代码
     *
     * @return RECEIVING_ORGAN - 受理机关代码
     */
    public String getReceivingOrgan() {
        return receivingOrgan;
    }

    /**
     * 设置受理机关代码
     *
     * @param receivingOrgan 受理机关代码
     */
    public void setReceivingOrgan(String receivingOrgan) {
        this.receivingOrgan = receivingOrgan;
    }

    /**
     * 获取法人注销原因
     *
     * @return REPEAL_REASON - 法人注销原因
     */
    public String getRepealReason() {
        return repealReason;
    }

    /**
     * 设置法人注销原因
     *
     * @param repealReason 法人注销原因
     */
    public void setRepealReason(String repealReason) {
        this.repealReason = repealReason;
    }

    /**
     * 获取法人注销日期
     *
     * @return REPEAL_DATE - 法人注销日期
     */
    public Date getRepealDate() {
        return repealDate;
    }

    /**
     * 设置法人注销日期
     *
     * @param repealDate 法人注销日期
     */
    public void setRepealDate(Date repealDate) {
        this.repealDate = repealDate;
    }

    /**
     * 获取法人变更日期
     *
     * @return CHANGE_DATE - 法人变更日期
     */
    public Date getChangeDate() {
        return changeDate;
    }

    /**
     * 设置法人变更日期
     *
     * @param changeDate 法人变更日期
     */
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    /**
     * 获取法人变更登记事项
     *
     * @return CHANGE_ITEM - 法人变更登记事项
     */
    public String getChangeItem() {
        return changeItem;
    }

    /**
     * 设置法人变更登记事项
     *
     * @param changeItem 法人变更登记事项
     */
    public void setChangeItem(String changeItem) {
        this.changeItem = changeItem;
    }

    /**
     * 获取法人注销机关
     *
     * @return REPEAL_ORGAN - 法人注销机关
     */
    public String getRepealOrgan() {
        return repealOrgan;
    }

    /**
     * 设置法人注销机关
     *
     * @param repealOrgan 法人注销机关
     */
    public void setRepealOrgan(String repealOrgan) {
        this.repealOrgan = repealOrgan;
    }

    /**
     * 获取分支机构数（社会组织）
     *
     * @return BRANCH_NUM - 分支机构数（社会组织）
     */
    public BigDecimal getBranchNum() {
        return branchNum;
    }

    /**
     * 设置分支机构数（社会组织）
     *
     * @param branchNum 分支机构数（社会组织）
     */
    public void setBranchNum(BigDecimal branchNum) {
        this.branchNum = branchNum;
    }

    /**
     * 获取代表机构数（社会组织）
     *
     * @return REPRESENT_NUM - 代表机构数（社会组织）
     */
    public BigDecimal getRepresentNum() {
        return representNum;
    }

    /**
     * 设置代表机构数（社会组织）
     *
     * @param representNum 代表机构数（社会组织）
     */
    public void setRepresentNum(BigDecimal representNum) {
        this.representNum = representNum;
    }

    /**
     * 获取登记类业务发布时间
     *
     * @return REG_UPD_DATE - 登记类业务发布时间
     */
    public Date getRegUpdDate() {
        return regUpdDate;
    }

    /**
     * 设置登记类业务发布时间
     *
     * @param regUpdDate 登记类业务发布时间
     */
    public void setRegUpdDate(Date regUpdDate) {
        this.regUpdDate = regUpdDate;
    }

    /**
     * 获取纳税人识别号
     *
     * @return TAXPAYERS_CODE - 纳税人识别号
     */
    public String getTaxpayersCode() {
        return taxpayersCode;
    }

    /**
     * 设置纳税人识别号
     *
     * @param taxpayersCode 纳税人识别号
     */
    public void setTaxpayersCode(String taxpayersCode) {
        this.taxpayersCode = taxpayersCode;
    }

    /**
     * 获取组合位置编码
     *
     * @return TAX_CODE - 组合位置编码
     */
    public String getTaxCode() {
        return taxCode;
    }

    /**
     * 设置组合位置编码
     *
     * @param taxCode 组合位置编码
     */
    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * 获取税务登记日期
     *
     * @return TAX_REG_DATE - 税务登记日期
     */
    public Date getTaxRegDate() {
        return taxRegDate;
    }

    /**
     * 设置税务登记日期
     *
     * @param taxRegDate 税务登记日期
     */
    public void setTaxRegDate(Date taxRegDate) {
        this.taxRegDate = taxRegDate;
    }

    /**
     * 获取税务变更内容
     *
     * @return TAX_CHGE_CONTENT - 税务变更内容
     */
    public String getTaxChgeContent() {
        return taxChgeContent;
    }

    /**
     * 设置税务变更内容
     *
     * @param taxChgeContent 税务变更内容
     */
    public void setTaxChgeContent(String taxChgeContent) {
        this.taxChgeContent = taxChgeContent;
    }

    /**
     * 获取税务变更日期
     *
     * @return TAX_CHGE_DATE - 税务变更日期
     */
    public Date getTaxChgeDate() {
        return taxChgeDate;
    }

    /**
     * 设置税务变更日期
     *
     * @param taxChgeDate 税务变更日期
     */
    public void setTaxChgeDate(Date taxChgeDate) {
        this.taxChgeDate = taxChgeDate;
    }

    /**
     * 获取税务注销原因
     *
     * @return TAX_REPEAL_REASON - 税务注销原因
     */
    public String getTaxRepealReason() {
        return taxRepealReason;
    }

    /**
     * 设置税务注销原因
     *
     * @param taxRepealReason 税务注销原因
     */
    public void setTaxRepealReason(String taxRepealReason) {
        this.taxRepealReason = taxRepealReason;
    }

    /**
     * 获取税务注销日期
     *
     * @return TAX_REPEAL_DATE - 税务注销日期
     */
    public Date getTaxRepealDate() {
        return taxRepealDate;
    }

    /**
     * 设置税务注销日期
     *
     * @param taxRepealDate 税务注销日期
     */
    public void setTaxRepealDate(Date taxRepealDate) {
        this.taxRepealDate = taxRepealDate;
    }

    /**
     * 获取税务注销机关
     *
     * @return TAX_REPEAL_ORGAN - 税务注销机关
     */
    public String getTaxRepealOrgan() {
        return taxRepealOrgan;
    }

    /**
     * 设置税务注销机关
     *
     * @param taxRepealOrgan 税务注销机关
     */
    public void setTaxRepealOrgan(String taxRepealOrgan) {
        this.taxRepealOrgan = taxRepealOrgan;
    }

    /**
     * 获取实际经营地址
     *
     * @return BUSINESS_ADDRESS - 实际经营地址
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * 设置实际经营地址
     *
     * @param businessAddress 实际经营地址
     */
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    /**
     * 获取税务类业务发布时间
     *
     * @return TAX_UPD_DATE - 税务类业务发布时间
     */
    public Date getTaxUpdDate() {
        return taxUpdDate;
    }

    /**
     * 设置税务类业务发布时间
     *
     * @param taxUpdDate 税务类业务发布时间
     */
    public void setTaxUpdDate(Date taxUpdDate) {
        this.taxUpdDate = taxUpdDate;
    }

    /**
     * 获取组织机构代码赋码日期
     *
     * @return ORGANCODE_DATE - 组织机构代码赋码日期
     */
    public Date getOrgancodeDate() {
        return organcodeDate;
    }

    /**
     * 设置组织机构代码赋码日期
     *
     * @param organcodeDate 组织机构代码赋码日期
     */
    public void setOrgancodeDate(Date organcodeDate) {
        this.organcodeDate = organcodeDate;
    }

    /**
     * 获取组织机构代码变更日期
     *
     * @return ORGCODE_CHGDATE - 组织机构代码变更日期
     */
    public Date getOrgcodeChgdate() {
        return orgcodeChgdate;
    }

    /**
     * 设置组织机构代码变更日期
     *
     * @param orgcodeChgdate 组织机构代码变更日期
     */
    public void setOrgcodeChgdate(Date orgcodeChgdate) {
        this.orgcodeChgdate = orgcodeChgdate;
    }

    /**
     * 获取组织机构代码注销日期
     *
     * @return ORGCODE_REPEALDATE - 组织机构代码注销日期
     */
    public Date getOrgcodeRepealdate() {
        return orgcodeRepealdate;
    }

    /**
     * 设置组织机构代码注销日期
     *
     * @param orgcodeRepealdate 组织机构代码注销日期
     */
    public void setOrgcodeRepealdate(Date orgcodeRepealdate) {
        this.orgcodeRepealdate = orgcodeRepealdate;
    }

    /**
     * 获取质监类业务发布时间
     *
     * @return QS_UPD_DATE - 质监类业务发布时间
     */
    public Date getQsUpdDate() {
        return qsUpdDate;
    }

    /**
     * 设置质监类业务发布时间
     *
     * @param qsUpdDate 质监类业务发布时间
     */
    public void setQsUpdDate(Date qsUpdDate) {
        this.qsUpdDate = qsUpdDate;
    }

    /**
     * 获取比对结果
     *
     * @return BD_RESULT - 比对结果
     */
    public String getBdResult() {
        return bdResult;
    }

    /**
     * 设置比对结果
     *
     * @param bdResult 比对结果
     */
    public void setBdResult(String bdResult) {
        this.bdResult = bdResult;
    }

    /**
     * 获取QYK_ID
     *
     * @return QYK_ID - QYK_ID
     */
    public String getQykId() {
        return qykId;
    }

    /**
     * 设置QYK_ID
     *
     * @param qykId QYK_ID
     */
    public void setQykId(String qykId) {
        this.qykId = qykId;
    }

    /**
     * 获取更新日期
     *
     * @return UPD_TIME - 更新日期
     */
    public Date getUpdTime() {
        return updTime;
    }

    /**
     * 设置更新日期
     *
     * @param updTime 更新日期
     */
    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    /**
     * 获取TRIM_CORP_NAME
     *
     * @return TRIM_CORP_NAME - TRIM_CORP_NAME
     */
    public String getTrimCorpName() {
        return trimCorpName;
    }

    /**
     * 设置TRIM_CORP_NAME
     *
     * @param trimCorpName TRIM_CORP_NAME
     */
    public void setTrimCorpName(String trimCorpName) {
        this.trimCorpName = trimCorpName;
    }

    /**
     * 获取公积金账号
     *
     * @return FUNDS_CODE - 公积金账号
     */
    public String getFundsCode() {
        return fundsCode;
    }

    /**
     * 设置公积金账号
     *
     * @param fundsCode 公积金账号
     */
    public void setFundsCode(String fundsCode) {
        this.fundsCode = fundsCode;
    }

    /**
     * 获取公积金开户日期
     *
     * @return FUNDS_OPEN_DATE - 公积金开户日期
     */
    public Date getFundsOpenDate() {
        return fundsOpenDate;
    }

    /**
     * 设置公积金开户日期
     *
     * @param fundsOpenDate 公积金开户日期
     */
    public void setFundsOpenDate(Date fundsOpenDate) {
        this.fundsOpenDate = fundsOpenDate;
    }

    /**
     * 获取公积金注销日期
     *
     * @return FUNDS_REPEAL_DATE - 公积金注销日期
     */
    public Date getFundsRepealDate() {
        return fundsRepealDate;
    }

    /**
     * 设置公积金注销日期
     *
     * @param fundsRepealDate 公积金注销日期
     */
    public void setFundsRepealDate(Date fundsRepealDate) {
        this.fundsRepealDate = fundsRepealDate;
    }

    /**
     * 获取公积金信息发布时间
     *
     * @return FUNDS_UPD_DATE - 公积金信息发布时间
     */
    public Date getFundsUpdDate() {
        return fundsUpdDate;
    }

    /**
     * 设置公积金信息发布时间
     *
     * @param fundsUpdDate 公积金信息发布时间
     */
    public void setFundsUpdDate(Date fundsUpdDate) {
        this.fundsUpdDate = fundsUpdDate;
    }

    /**
     * 获取补充公积金账号
     *
     * @return FUNDSADD_CODE - 补充公积金账号
     */
    public String getFundsaddCode() {
        return fundsaddCode;
    }

    /**
     * 设置补充公积金账号
     *
     * @param fundsaddCode 补充公积金账号
     */
    public void setFundsaddCode(String fundsaddCode) {
        this.fundsaddCode = fundsaddCode;
    }

    /**
     * 获取补充公积金开户日期
     *
     * @return FUNDSADD_OPEN_DATE - 补充公积金开户日期
     */
    public Date getFundsaddOpenDate() {
        return fundsaddOpenDate;
    }

    /**
     * 设置补充公积金开户日期
     *
     * @param fundsaddOpenDate 补充公积金开户日期
     */
    public void setFundsaddOpenDate(Date fundsaddOpenDate) {
        this.fundsaddOpenDate = fundsaddOpenDate;
    }

    /**
     * 获取补充公积金注销日期
     *
     * @return FUNDSADD_REPEAL_DATE - 补充公积金注销日期
     */
    public Date getFundsaddRepealDate() {
        return fundsaddRepealDate;
    }

    /**
     * 设置补充公积金注销日期
     *
     * @param fundsaddRepealDate 补充公积金注销日期
     */
    public void setFundsaddRepealDate(Date fundsaddRepealDate) {
        this.fundsaddRepealDate = fundsaddRepealDate;
    }

    /**
     * 获取补充公积金信息发布时间
     *
     * @return FUNDSADD_UPD_DATE - 补充公积金信息发布时间
     */
    public Date getFundsaddUpdDate() {
        return fundsaddUpdDate;
    }

    /**
     * 设置补充公积金信息发布时间
     *
     * @param fundsaddUpdDate 补充公积金信息发布时间
     */
    public void setFundsaddUpdDate(Date fundsaddUpdDate) {
        this.fundsaddUpdDate = fundsaddUpdDate;
    }

    /**
     * 获取社会保险账号
     *
     * @return SOCIAL_SECURITY_CODE - 社会保险账号
     */
    public String getSocialSecurityCode() {
        return socialSecurityCode;
    }

    /**
     * 设置社会保险账号
     *
     * @param socialSecurityCode 社会保险账号
     */
    public void setSocialSecurityCode(String socialSecurityCode) {
        this.socialSecurityCode = socialSecurityCode;
    }

    /**
     * 获取社会保险帐号注销日期
     *
     * @return SOCIAL_SECURITY_OPEN_DATE - 社会保险帐号注销日期
     */
    public Date getSocialSecurityOpenDate() {
        return socialSecurityOpenDate;
    }

    /**
     * 设置社会保险帐号注销日期
     *
     * @param socialSecurityOpenDate 社会保险帐号注销日期
     */
    public void setSocialSecurityOpenDate(Date socialSecurityOpenDate) {
        this.socialSecurityOpenDate = socialSecurityOpenDate;
    }

    /**
     * 获取社保信息发布时间
     *
     * @return SOCIAL_SECURITY_REPEAL_DATE - 社保信息发布时间
     */
    public Date getSocialSecurityRepealDate() {
        return socialSecurityRepealDate;
    }

    /**
     * 设置社保信息发布时间
     *
     * @param socialSecurityRepealDate 社保信息发布时间
     */
    public void setSocialSecurityRepealDate(Date socialSecurityRepealDate) {
        this.socialSecurityRepealDate = socialSecurityRepealDate;
    }

    /**
     * 获取社会保险帐号开设日期
     *
     * @return SOCIAL_SECURITY_UPD_DATE - 社会保险帐号开设日期
     */
    public Date getSocialSecurityUpdDate() {
        return socialSecurityUpdDate;
    }

    /**
     * 设置社会保险帐号开设日期
     *
     * @param socialSecurityUpdDate 社会保险帐号开设日期
     */
    public void setSocialSecurityUpdDate(Date socialSecurityUpdDate) {
        this.socialSecurityUpdDate = socialSecurityUpdDate;
    }

    /**
     * 获取离开状态
     *
     * @return LK_STATUS - 离开状态
     */
    public String getLkStatus() {
        return lkStatus;
    }

    /**
     * 设置离开状态
     *
     * @param lkStatus 离开状态
     */
    public void setLkStatus(String lkStatus) {
        this.lkStatus = lkStatus;
    }

    /**
     * 获取是否自贸区
     *
     * @return IS_ZMQ - 是否自贸区
     */
    public String getIsZmq() {
        return isZmq;
    }

    /**
     * 设置是否自贸区
     *
     * @param isZmq 是否自贸区
     */
    public void setIsZmq(String isZmq) {
        this.isZmq = isZmq;
    }

    /**
     * 获取插入时间
     *
     * @return INSERT_TIME - 插入时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置插入时间
     *
     * @param insertTime 插入时间
     */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 获取法人状态
     *
     * @return CORP_STATUS - 法人状态
     */
    public String getCorpStatus() {
        return corpStatus;
    }

    /**
     * 设置法人状态
     *
     * @param corpStatus 法人状态
     */
    public void setCorpStatus(String corpStatus) {
        this.corpStatus = corpStatus;
    }

    /**
     * 获取是否工商联
     *
     * @return IS_GSL - 是否工商联
     */
    public String getIsGsl() {
        return isGsl;
    }

    /**
     * 设置是否工商联
     *
     * @param isGsl 是否工商联
     */
    public void setIsGsl(String isGsl) {
        this.isGsl = isGsl;
    }

    /**
     * 获取IS_RECENTLY_ZMQ
     *
     * @return IS_RECENTLY_ZMQ - IS_RECENTLY_ZMQ
     */
    public String getIsRecentlyZmq() {
        return isRecentlyZmq;
    }

    /**
     * 设置IS_RECENTLY_ZMQ
     *
     * @param isRecentlyZmq IS_RECENTLY_ZMQ
     */
    public void setIsRecentlyZmq(String isRecentlyZmq) {
        this.isRecentlyZmq = isRecentlyZmq;
    }

    /**
     * 获取IS_WEB_SEND
     *
     * @return IS_WEB_SEND - IS_WEB_SEND
     */
    public String getIsWebSend() {
        return isWebSend;
    }

    /**
     * 设置IS_WEB_SEND
     *
     * @param isWebSend IS_WEB_SEND
     */
    public void setIsWebSend(String isWebSend) {
        this.isWebSend = isWebSend;
    }

    /**
     * 获取统一社会信用代码
     *
     * @return UNI_SC_ID - 统一社会信用代码
     */
    public String getUniScId() {
        return uniScId;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param uniScId 统一社会信用代码
     */
    public void setUniScId(String uniScId) {
        this.uniScId = uniScId;
    }
}