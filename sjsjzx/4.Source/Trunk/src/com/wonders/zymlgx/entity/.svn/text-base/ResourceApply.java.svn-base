package com.wonders.zymlgx.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 资源申请信息.
 */
@Table("R_RESOURCE_APPLY")
public class ResourceApply {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("APPLY_ID")
	private String applyId;
	/**申请用户ID**/
	@Column("USER_ID")
	private String userId;
	/**申请人联系电话**/
	@Column("LINKMAN_PHONE")
	private String linkmanPhone;
	/**审核人**/
	@Column("CHECKED_PERSON")
	private String checkedPerson;
	/**审核人联系电话**/
	@Column("CHECK_LINKMAN_PHONE")
	private String checkedLinkmanPhone;
	/**申请日期**/
	@Column("APPLY_DATE")
	private Date applyDate;
	/**审核日期**/
	@Column("CHECKED_DATE")
	private Date checkedDate;
	/**资源提供单位**/
	@Column("RESOURCE_PROVIDER")
	private String resourceProvider;
	/**申请的资源类型(人口类、法人类、经济类)**/
	@Column("RESOURCE_TYPE")
	private String resourceType;
	/**申请的资源目录名称**/
	@Column("RESOURCE_NAME")
	private String resourceName;
	/**库表类型（基础库，行业库）**/
	@Column("DATA_TYPE")
	private String dataType;
	/**是否审核(1：待审核，2：已审核，3:已退回)**/
	@Column("STATUS")
	private String status;
	/**申请原因**/
	@Column("APPLY_REASON")
	private String applyReason;
	/**审核不通过原因**/
	@Column("NO_PASS_REASON")
	private String noPassReason;
	/**申请主题**/
	@Column("APPLY_TOPIC")
	private String applyTopic;
	/**是否提交(0：未提交，1：已提交)**/
	@Column("IS_SUBMIT")
	private String isSubmit;
	/**附件id**/
	@Column("FILE_IDS")
	private String fileIds;
	/**资源主键**/
	@Column("RESOURCE_ID")
	private String resourceId;
	/**申请编号**/
	@Column("APP_APPLY_NUM")
	private String appApplyNum;
	/**审核者类型(1-委办领导，2-科委)**/
	@Column("OP_USER")
	private String opUser;
	
	public String initUUID() {
		if (Strings.isEmpty(applyId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.applyId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getCheckedPerson() {
		return checkedPerson;
	}
	public void setCheckedPerson(String checkedPerson) {
		this.checkedPerson = checkedPerson;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public Date getCheckedDate() {
		return checkedDate;
	}
	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate;
	}
	public String getResourceProvider() {
		return resourceProvider;
	}
	public void setResourceProvider(String resourceProvider) {
		this.resourceProvider = resourceProvider;
	}
	
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
	public String getApplyTopic() {
		return applyTopic;
	}
	public void setApplyTopic(String applyTopic) {
		this.applyTopic = applyTopic;
	}
	public String getIsSubmit() {
		return isSubmit;
	}
	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}
	public String getFileIds() {
		return fileIds;
	}
	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getLinkmanPhone() {
		return linkmanPhone;
	}
	public void setLinkmanPhone(String linkmanPhone) {
		this.linkmanPhone = linkmanPhone;
	}
	public String getCheckedLinkmanPhone() {
		return checkedLinkmanPhone;
	}
	public void setCheckedLinkmanPhone(String checkedLinkmanPhone) {
		this.checkedLinkmanPhone = checkedLinkmanPhone;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	public String getAppApplyNum() {
		return appApplyNum;
	}
	public void setAppApplyNum(String appApplyNum) {
		this.appApplyNum = appApplyNum;
	}
	public String getOpUser() {
		return opUser;
	}
	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}
}
