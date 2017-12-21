package com.wonders.ws.receive.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("PT_QFB_WANGTING")
public class PtQfbWangtingBean {
	/**
	 * 业务流水号
	 **/
	@Column("ID")
	private String	id;
	/**
	 * 统一审批编码
	 **/
	@Column("SUID")
	private String	suid;
	/**
	 * 内部办件编号
	 **/
	@Column("DID")
	private String	did;
	/**
	 * 事项名称
	 **/
	@Column("AFFAIR_NAME")
	private String	affairname;
	/**
	 * 业务单位名称
	 **/
	@Column("MAIN_DEPARTMENT")
	private String	maindepartment;
	/**
	 * 所属区县名称
	 **/
	@Column("REGION")
	private String	region;
	/**
	 * 业务类型
	 **/
	@Column("TYPE")
	private String	type;
	/**
	 * 申请内容
	 **/
	@Column("APPLY_NAME")
	private String	applyname;
	/**
	 * 可选，申请者类型，取值“企业”、“事业”、“机关”、“个人”、“其他”
	 **/
	@Column("APPLICANT_TYPE")
	private String	applicanttype;
	/**
	 * 申请人或者申请单位名称
	 **/
	@Column("APPLICANT")
	private String	applicant;
	/**
	 * 申请者证件类型，取值“居民身份证”、“企业组织机构代码证”、“其他”
	 **/
	@Column("CERTIFICATE_TYPE")
	private String	certificatetype;
	/**
	 * 申请者证件号
	 **/
	@Column("CERTIFICATE_NO")
	private String	certificateno;
	/**
	 * 联系人姓名
	 **/
	@Column("CONTACT_NAME")
	private String	contactname;
	/**
	 * 联系人地址
	 **/
	@Column("CONTACT_ADDRESS")
	private String	contactaddress;
	/**
	 * 办件关联的政务大厅用户ID
	 **/
	@Column("CONTACT_POSTCODE")
	private String	contactpostcode;
	/**
	 * 可选，联系人电话
	 **/
	@Column("CONTACT_TEL")
	private String	contacttel;
	/**
	 * 联系人移动电话
	 **/
	@Column("CONTACT_MOBILE")
	private String	contactmobile;
	/**
	 * 联系人传真
	 **/
	@Column("CONTACT_FAX")
	private String	contactfax;
	/**
	 * 联系人邮箱
	 **/
	@Column("CONTACT_EMAIL")
	private String	contactemail;
	/**
	 * 业务收费金额
	 **/
	@Column("TOLL")
	private String	toll;
	/**
	 * 办结结果
	 **/
	@Column("RESULT")
	private String	result;
	/**
	 * 事项小类名称
	 **/
	@Column("RESULT_VIEW")
	private String	resultview;
	/**
	 * 反馈给公众的办理结果
	 **/
	@Column("APPLY_MEMO")
	private String	applymemo;
	/**
	 * 办件承诺时限
	 **/
	@Column("APPLY_COMMITMENT_DAYS")
	private String	applycommitmentdays;
	/**
	 * 办件承诺时限类型
	 **/
	@Column("APPLY_COMMITMENT_TYPE")
	private String	applycommitmenttype;
	/**
	 * 承诺办结时间
	 **/
	@Column("APPLY_COMMITMENT_TIME")
	private String	applycommitmenttime;
	/**
	 * 申请时间
	 **/
	@Column("APPLY_TIME")
	private String	applytime;
	/**
	 * 受理时间
	 **/
	@Column("ACCEPT_TIME")
	private String	accepttime;
	/**
	 * 办结时间
	 **/
	@Column("FINISH_TIME")
	private String	finishtime;
	/**
	 * 办理天数
	 **/
	@Column("TRANSACT_DAYS")
	private String	transactdays;
	/**
	 * 是否事项超期
	 **/
	@Column("IS_OVERTIME")
	private String	isovertime;
	/**
	 * 该事项是否属于管委会事项
	 **/
	@Column("IS_PUBLIC")
	private String	ispublic;
	/**
	 * 申请类型
	 **/
	@Column("SUBMIT_TYPE")
	private String	submittype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSuid() {
		return suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getAffairname() {
		return affairname;
	}

	public void setAffairname(String affairname) {
		this.affairname = affairname;
	}

	public String getMaindepartment() {
		return maindepartment;
	}

	public void setMaindepartment(String maindepartment) {
		this.maindepartment = maindepartment;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApplyname() {
		return applyname;
	}

	public void setApplyname(String applyname) {
		this.applyname = applyname;
	}

	public String getApplicanttype() {
		return applicanttype;
	}

	public void setApplicanttype(String applicanttype) {
		this.applicanttype = applicanttype;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getCertificateno() {
		return certificateno;
	}

	public void setCertificateno(String certificateno) {
		this.certificateno = certificateno;
	}

	public String getContactname() {
		return contactname;
	}

	public void setContactname(String contactname) {
		this.contactname = contactname;
	}

	public String getContactaddress() {
		return contactaddress;
	}

	public void setContactaddress(String contactaddress) {
		this.contactaddress = contactaddress;
	}

	public String getContactpostcode() {
		return contactpostcode;
	}

	public void setContactpostcode(String contactpostcode) {
		this.contactpostcode = contactpostcode;
	}

	public String getContacttel() {
		return contacttel;
	}

	public void setContacttel(String contacttel) {
		this.contacttel = contacttel;
	}

	public String getContactmobile() {
		return contactmobile;
	}

	public void setContactmobile(String contactmobile) {
		this.contactmobile = contactmobile;
	}

	public String getContactfax() {
		return contactfax;
	}

	public void setContactfax(String contactfax) {
		this.contactfax = contactfax;
	}

	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}

	public String getToll() {
		return toll;
	}

	public void setToll(String toll) {
		this.toll = toll;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResultview() {
		return resultview;
	}

	public void setResultview(String resultview) {
		this.resultview = resultview;
	}

	public String getApplymemo() {
		return applymemo;
	}

	public void setApplymemo(String applymemo) {
		this.applymemo = applymemo;
	}

	public String getApplycommitmentdays() {
		return applycommitmentdays;
	}

	public void setApplycommitmentdays(String applycommitmentdays) {
		this.applycommitmentdays = applycommitmentdays;
	}

	public String getApplycommitmenttype() {
		return applycommitmenttype;
	}

	public void setApplycommitmenttype(String applycommitmenttype) {
		this.applycommitmenttype = applycommitmenttype;
	}

	public String getApplycommitmenttime() {
		return applycommitmenttime;
	}

	public void setApplycommitmenttime(String applycommitmenttime) {
		this.applycommitmenttime = applycommitmenttime;
	}

	public String getApplytime() {
		return applytime;
	}

	public void setApplytime(String applytime) {
		this.applytime = applytime;
	}

	public String getAccepttime() {
		return accepttime;
	}

	public void setAccepttime(String accepttime) {
		this.accepttime = accepttime;
	}

	public String getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}

	public String getTransactdays() {
		return transactdays;
	}

	public void setTransactdays(String transactdays) {
		this.transactdays = transactdays;
	}

	public String getIsovertime() {
		return isovertime;
	}

	public void setIsovertime(String isovertime) {
		this.isovertime = isovertime;
	}

	public String getIspublic() {
		return ispublic;
	}

	public void setIspublic(String ispublic) {
		this.ispublic = ispublic;
	}

	public String getSubmittype() {
		return submittype;
	}

	public void setSubmittype(String submittype) {
		this.submittype = submittype;
	}

}
