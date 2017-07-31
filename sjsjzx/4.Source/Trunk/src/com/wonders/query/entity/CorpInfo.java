package com.wonders.query.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("CORP_INFO")
public class CorpInfo {
	/**
	*数据中心法人实体序号
	**/
	@Column("CORP_INFO_ID")
	private String corpinfoid;
	/**
	*组织机构代码
	**/
	@Column("ORGAN_CODE")
	private String organcode;
	/**
	*标识
	**/
	@Column("ENTITY_ID")
	private String entityid;
	/**
	*法人名称
	**/
	@Column("CORP_NAME")
	private String corpname;
	/**
	*法人类型
	**/
	@Column("CORP_TYPE")
	private String corptype;
	/**
	*法定代表人
	**/
	@Column("PERSON_NAME")
	private String personname;
	/**
	*经营场所
	**/
	@Column("ADDRESS")
	private String address;
	/**
	*区划
	**/
	@Column("AREA_CODE")
	private String areacode;
	/**
	*邮编
	**/
	@Column("ZIP")
	private String zip;
	/**
	*联系电话
	**/
	@Column("TELEPHONE")
	private String telephone;
	/**
	*成立日期
	**/
	@Column("ESTABLISH_DATE")
	private Date establishdate;
	/**
	*开办资金
	**/
	@Column("REG_CAPITAL")
	private double regcapital;
	/**
	*币种
	**/
	@Column("CURRENCY")
	private String currency;
	/**
	*业务范围
	**/
	@Column("BUSINESS_SCOPE")
	private String businessscope;
	/**
	*法定代表人证件类型
	**/
	@Column("PERSON_CERT_TYPE")
	private String personcerttype;
	/**
	*法定代表人证件号
	**/
	@Column("PERSON_CERT_CODE")
	private String personcertcode;
	/**
	*行业类别
	**/
	@Column("INDUSTRY_CODE")
	private String industrycode;
	/**
	*业务主管单位
	**/
	@Column("ORGANIZERS")
	private String organizers;
	/**
	*经费来源
	**/
	@Column("FUNDING_SRC")
	private String fundingsrc;
	/**
	*注册号
	**/
	@Column("REG_NO")
	private String regno;
	/**
	*受理机关代码
	**/
	@Column("RECEIVING_ORGAN")
	private String receivingorgan;
	/**
	*法人注销原因
	**/
	@Column("REPEAL_REASON")
	private String repealreason;
	/**
	*法人注销日期
	**/
	@Column("REPEAL_DATE")
	private Date repealdate;
	/**
	*法人变更日期
	**/
	@Column("CHANGE_DATE")
	private Date changedate;
	/**
	*法人变更登记事项
	**/
	@Column("CHANGE_ITEM")
	private String changeitem;
	/**
	*法人注销机关
	**/
	@Column("REPEAL_ORGAN")
	private String repealorgan;
	/**
	*分支机构数（社会组织）
	**/
	@Column("BRANCH_NUM")
	private double branchnum;
	/**
	*代表机构数（社会组织）
	**/
	@Column("REPRESENT_NUM")
	private double representnum;
	/**
	*登记类业务发布时间
	**/
	@Column("REG_UPD_DATE")
	private Date regupddate;
	/**
	*纳税人识别号
	**/
	@Column("TAXPAYERS_CODE")
	private String taxpayerscode;
	/**
	*组合位置编码
	**/
	@Column("TAX_CODE")
	private String taxcode;
	/**
	*税务登记日期
	**/
	@Column("TAX_REG_DATE")
	private Date taxregdate;
	/**
	*税务变更内容
	**/
	@Column("TAX_CHGE_CONTENT")
	private String taxchgecontent;
	/**
	*税务变更日期
	**/
	@Column("TAX_CHGE_DATE")
	private Date taxchgedate;
	/**
	*税务注销原因
	**/
	@Column("TAX_REPEAL_REASON")
	private String taxrepealreason;
	/**
	*税务注销日期
	**/
	@Column("TAX_REPEAL_DATE")
	private Date taxrepealdate;
	/**
	*税务注销机关
	**/
	@Column("TAX_REPEAL_ORGAN")
	private String taxrepealorgan;
	/**
	*实际经营地址
	**/
	@Column("BUSINESS_ADDRESS")
	private String businessaddress;
	/**
	*税务类业务发布时间
	**/
	@Column("TAX_UPD_DATE")
	private Date taxupddate;
	/**
	*组织机构代码赋码日期
	**/
	@Column("ORGANCODE_DATE")
	private Date organcodedate;
	/**
	*组织机构代码变更日期
	**/
	@Column("ORGCODE_CHGDATE")
	private Date orgcodechgdate;
	/**
	*组织机构代码注销日期
	**/
	@Column("ORGCODE_REPEALDATE")
	private Date orgcoderepealdate;
	/**
	*质监类业务发布时间
	**/
	@Column("QS_UPD_DATE")
	private Date qsupddate;
	/**
	*比对结果
	**/
	@Column("BD_RESULT")
	private String bdresult;
	/**
	*QYK_ID
	**/
	@Column("QYK_ID")
	private String qykid;
	/**
	*更新日期
	**/
	@Column("UPD_TIME")
	private Date updtime;
	/**
	*TRIM_CORP_NAME
	**/
	@Column("TRIM_CORP_NAME")
	private String trimcorpname;
	/**
	*公积金账号
	**/
	@Column("FUNDS_CODE")
	private String fundscode;
	/**
	*公积金开户日期
	**/
	@Column("FUNDS_OPEN_DATE")
	private Date fundsopendate;
	/**
	*公积金注销日期
	**/
	@Column("FUNDS_REPEAL_DATE")
	private Date fundsrepealdate;
	/**
	*公积金信息发布时间
	**/
	@Column("FUNDS_UPD_DATE")
	private Date fundsupddate;
	/**
	*补充公积金账号
	**/
	@Column("FUNDSADD_CODE")
	private String fundsaddcode;
	/**
	*补充公积金开户日期
	**/
	@Column("FUNDSADD_OPEN_DATE")
	private Date fundsaddopendate;
	/**
	*补充公积金注销日期
	**/
	@Column("FUNDSADD_REPEAL_DATE")
	private Date fundsaddrepealdate;
	/**
	*补充公积金信息发布时间
	**/
	@Column("FUNDSADD_UPD_DATE")
	private Date fundsaddupddate;
	/**
	*社会保险账号
	**/
	@Column("SOCIAL_SECURITY_CODE")
	private String socialsecuritycode;
	/**
	*社会保险帐号注销日期
	**/
	@Column("SOCIAL_SECURITY_OPEN_DATE")
	private Date socialsecurityopendate;
	/**
	*社保信息发布时间
	**/
	@Column("SOCIAL_SECURITY_REPEAL_DATE")
	private Date socialsecurityrepealdate;
	/**
	*社会保险帐号开设日期
	**/
	@Column("SOCIAL_SECURITY_UPD_DATE")
	private Date socialsecurityupddate;
	/**
	*离开状态
	**/
	@Column("LK_STATUS")
	private String lkstatus;
	/**
	*是否自贸区
	**/
	@Column("IS_ZMQ")
	private String iszmq;
	/**
	*插入时间
	**/
	@Column("INSERT_TIME")
	private Date inserttime;
	/**
	*法人状态
	**/
	@Column("CORP_STATUS")
	private String corpstatus;
	/**
	*是否工商联
	**/
	@Column("IS_GSL")
	private String isgsl;
	/**
	*IS_RECENTLY_ZMQ
	**/
	@Column("IS_RECENTLY_ZMQ")
	private String isrecentlyzmq;
	/**
	*IS_WEB_SEND
	**/
	@Column("IS_WEB_SEND")
	private String iswebsend;
	/**
	*统一社会信用代码
	**/
	@Column("UNI_SC_ID")
	private String uniscid;
	public String getCorpinfoid() {
		return corpinfoid;
	}
	public void setCorpinfoid(String corpinfoid) {
		this.corpinfoid = corpinfoid;
	}
	public String getOrgancode() {
		return organcode;
	}
	public void setOrgancode(String organcode) {
		this.organcode = organcode;
	}
	public String getEntityid() {
		return entityid;
	}
	public void setEntityid(String entityid) {
		this.entityid = entityid;
	}
	public String getCorpname() {
		return corpname;
	}
	public void setCorpname(String corpname) {
		this.corpname = corpname;
	}
	public String getCorptype() {
		return corptype;
	}
	public void setCorptype(String corptype) {
		this.corptype = corptype;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAreacode() {
		return areacode;
	}
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getEstablishdate() {
		return establishdate;
	}
	public void setEstablishdate(Date establishdate) {
		this.establishdate = establishdate;
	}
	public double getRegcapital() {
		return regcapital;
	}
	public void setRegcapital(double regcapital) {
		this.regcapital = regcapital;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBusinessscope() {
		return businessscope;
	}
	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}
	public String getPersoncerttype() {
		return personcerttype;
	}
	public void setPersoncerttype(String personcerttype) {
		this.personcerttype = personcerttype;
	}
	public String getPersoncertcode() {
		return personcertcode;
	}
	public void setPersoncertcode(String personcertcode) {
		this.personcertcode = personcertcode;
	}
	public String getIndustrycode() {
		return industrycode;
	}
	public void setIndustrycode(String industrycode) {
		this.industrycode = industrycode;
	}
	public String getOrganizers() {
		return organizers;
	}
	public void setOrganizers(String organizers) {
		this.organizers = organizers;
	}
	public String getFundingsrc() {
		return fundingsrc;
	}
	public void setFundingsrc(String fundingsrc) {
		this.fundingsrc = fundingsrc;
	}
	public String getRegno() {
		return regno;
	}
	public void setRegno(String regno) {
		this.regno = regno;
	}
	public String getReceivingorgan() {
		return receivingorgan;
	}
	public void setReceivingorgan(String receivingorgan) {
		this.receivingorgan = receivingorgan;
	}
	public String getRepealreason() {
		return repealreason;
	}
	public void setRepealreason(String repealreason) {
		this.repealreason = repealreason;
	}
	public Date getRepealdate() {
		return repealdate;
	}
	public void setRepealdate(Date repealdate) {
		this.repealdate = repealdate;
	}
	public Date getChangedate() {
		return changedate;
	}
	public void setChangedate(Date changedate) {
		this.changedate = changedate;
	}
	public String getChangeitem() {
		return changeitem;
	}
	public void setChangeitem(String changeitem) {
		this.changeitem = changeitem;
	}
	public String getRepealorgan() {
		return repealorgan;
	}
	public void setRepealorgan(String repealorgan) {
		this.repealorgan = repealorgan;
	}
	public double getBranchnum() {
		return branchnum;
	}
	public void setBranchnum(double branchnum) {
		this.branchnum = branchnum;
	}
	public double getRepresentnum() {
		return representnum;
	}
	public void setRepresentnum(double representnum) {
		this.representnum = representnum;
	}
	public Date getRegupddate() {
		return regupddate;
	}
	public void setRegupddate(Date regupddate) {
		this.regupddate = regupddate;
	}
	public String getTaxpayerscode() {
		return taxpayerscode;
	}
	public void setTaxpayerscode(String taxpayerscode) {
		this.taxpayerscode = taxpayerscode;
	}
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public Date getTaxregdate() {
		return taxregdate;
	}
	public void setTaxregdate(Date taxregdate) {
		this.taxregdate = taxregdate;
	}
	public String getTaxchgecontent() {
		return taxchgecontent;
	}
	public void setTaxchgecontent(String taxchgecontent) {
		this.taxchgecontent = taxchgecontent;
	}
	public Date getTaxchgedate() {
		return taxchgedate;
	}
	public void setTaxchgedate(Date taxchgedate) {
		this.taxchgedate = taxchgedate;
	}
	public String getTaxrepealreason() {
		return taxrepealreason;
	}
	public void setTaxrepealreason(String taxrepealreason) {
		this.taxrepealreason = taxrepealreason;
	}
	public Date getTaxrepealdate() {
		return taxrepealdate;
	}
	public void setTaxrepealdate(Date taxrepealdate) {
		this.taxrepealdate = taxrepealdate;
	}
	public String getTaxrepealorgan() {
		return taxrepealorgan;
	}
	public void setTaxrepealorgan(String taxrepealorgan) {
		this.taxrepealorgan = taxrepealorgan;
	}
	public String getBusinessaddress() {
		return businessaddress;
	}
	public void setBusinessaddress(String businessaddress) {
		this.businessaddress = businessaddress;
	}
	public Date getTaxupddate() {
		return taxupddate;
	}
	public void setTaxupddate(Date taxupddate) {
		this.taxupddate = taxupddate;
	}
	public Date getOrgancodedate() {
		return organcodedate;
	}
	public void setOrgancodedate(Date organcodedate) {
		this.organcodedate = organcodedate;
	}
	public Date getOrgcodechgdate() {
		return orgcodechgdate;
	}
	public void setOrgcodechgdate(Date orgcodechgdate) {
		this.orgcodechgdate = orgcodechgdate;
	}
	public Date getOrgcoderepealdate() {
		return orgcoderepealdate;
	}
	public void setOrgcoderepealdate(Date orgcoderepealdate) {
		this.orgcoderepealdate = orgcoderepealdate;
	}
	public Date getQsupddate() {
		return qsupddate;
	}
	public void setQsupddate(Date qsupddate) {
		this.qsupddate = qsupddate;
	}
	public String getBdresult() {
		return bdresult;
	}
	public void setBdresult(String bdresult) {
		this.bdresult = bdresult;
	}
	public String getQykid() {
		return qykid;
	}
	public void setQykid(String qykid) {
		this.qykid = qykid;
	}
	public Date getUpdtime() {
		return updtime;
	}
	public void setUpdtime(Date updtime) {
		this.updtime = updtime;
	}
	public String getTrimcorpname() {
		return trimcorpname;
	}
	public void setTrimcorpname(String trimcorpname) {
		this.trimcorpname = trimcorpname;
	}
	public String getFundscode() {
		return fundscode;
	}
	public void setFundscode(String fundscode) {
		this.fundscode = fundscode;
	}
	public Date getFundsopendate() {
		return fundsopendate;
	}
	public void setFundsopendate(Date fundsopendate) {
		this.fundsopendate = fundsopendate;
	}
	public Date getFundsrepealdate() {
		return fundsrepealdate;
	}
	public void setFundsrepealdate(Date fundsrepealdate) {
		this.fundsrepealdate = fundsrepealdate;
	}
	public Date getFundsupddate() {
		return fundsupddate;
	}
	public void setFundsupddate(Date fundsupddate) {
		this.fundsupddate = fundsupddate;
	}
	public String getFundsaddcode() {
		return fundsaddcode;
	}
	public void setFundsaddcode(String fundsaddcode) {
		this.fundsaddcode = fundsaddcode;
	}
	public Date getFundsaddopendate() {
		return fundsaddopendate;
	}
	public void setFundsaddopendate(Date fundsaddopendate) {
		this.fundsaddopendate = fundsaddopendate;
	}
	public Date getFundsaddrepealdate() {
		return fundsaddrepealdate;
	}
	public void setFundsaddrepealdate(Date fundsaddrepealdate) {
		this.fundsaddrepealdate = fundsaddrepealdate;
	}
	public Date getFundsaddupddate() {
		return fundsaddupddate;
	}
	public void setFundsaddupddate(Date fundsaddupddate) {
		this.fundsaddupddate = fundsaddupddate;
	}
	public String getSocialsecuritycode() {
		return socialsecuritycode;
	}
	public void setSocialsecuritycode(String socialsecuritycode) {
		this.socialsecuritycode = socialsecuritycode;
	}
	public Date getSocialsecurityopendate() {
		return socialsecurityopendate;
	}
	public void setSocialsecurityopendate(Date socialsecurityopendate) {
		this.socialsecurityopendate = socialsecurityopendate;
	}
	public Date getSocialsecurityrepealdate() {
		return socialsecurityrepealdate;
	}
	public void setSocialsecurityrepealdate(Date socialsecurityrepealdate) {
		this.socialsecurityrepealdate = socialsecurityrepealdate;
	}
	public Date getSocialsecurityupddate() {
		return socialsecurityupddate;
	}
	public void setSocialsecurityupddate(Date socialsecurityupddate) {
		this.socialsecurityupddate = socialsecurityupddate;
	}
	public String getLkstatus() {
		return lkstatus;
	}
	public void setLkstatus(String lkstatus) {
		this.lkstatus = lkstatus;
	}
	public String getIszmq() {
		return iszmq;
	}
	public void setIszmq(String iszmq) {
		this.iszmq = iszmq;
	}
	public Date getInserttime() {
		return inserttime;
	}
	public void setInserttime(Date inserttime) {
		this.inserttime = inserttime;
	}
	public String getCorpstatus() {
		return corpstatus;
	}
	public void setCorpstatus(String corpstatus) {
		this.corpstatus = corpstatus;
	}
	public String getIsgsl() {
		return isgsl;
	}
	public void setIsgsl(String isgsl) {
		this.isgsl = isgsl;
	}
	public String getIsrecentlyzmq() {
		return isrecentlyzmq;
	}
	public void setIsrecentlyzmq(String isrecentlyzmq) {
		this.isrecentlyzmq = isrecentlyzmq;
	}
	public String getIswebsend() {
		return iswebsend;
	}
	public void setIswebsend(String iswebsend) {
		this.iswebsend = iswebsend;
	}
	public String getUniscid() {
		return uniscid;
	}
	public void setUniscid(String uniscid) {
		this.uniscid = uniscid;
	}
	
}
