package com.wonders.wdac.entity;

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
@Table("PF_ZY_APPLY")
public class ZyApply {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("ZY_APPLY_ID")
	private String zyApplyId;
	/**申请用户ID**/
	@Column("USER_ID")
	private String userId;
	/**申请用户ID**/
	@Column("USER_NAME")
	private String userName;
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
	/**审核原因**/
	@Column("CHECK_REASON")
	private String checkReason;
	/**申请主题**/
	@Column("APPLY_TOPIC")
	private String applyTopic;
	/**是否提交(0：未提交，1：已提交)**/
	@Column("IS_SUBMIT")
	private String isSubmit;
	/**附件id**/
	@Column("FILE_IDS")
	private String fileIds;
	
	@Column("ZY_INFO_ID")
	private String zyInfoId;
	/**申请编号**/
	@Column("APP_APPLY_NUM")
	private String appApplyNum;
	/**审核者类型(1-委办领导，2-科委)**/
	@Column("OP_USER")
	private String opUser;
	
    @Column("FW_CODE")
    private String fwCode;
	/**服务日期**/
	@Column("FW_DATE")
	private Date fwDate;
	/**是否是新的申请，新申请为1，历史的为2**/
	@Column("IS_NEW_APPLY")
	private String isNewApply;
	/**申请用户ID**/
	@Column("UNIT_ID")
	private String unitId;
	/**申请用户ID**/
	@Column("UNIT_NAME")
	private String unitName;
	
	public String initUUID() {
		if (Strings.isEmpty(zyApplyId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.zyApplyId;
	}
	
	public String getZyApplyId() {
		return zyApplyId;
	}

	public void setZyApplyId(String zyApplyId) {
		this.zyApplyId = zyApplyId;
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
	
	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
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
	
	public String getZyInfoId() {
		return zyInfoId;
	}
	public void setZyInfoId(String zyInfoId) {
		this.zyInfoId = zyInfoId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getFwCode() {
		return fwCode;
	}

	public void setFwCode(String fwCode) {
		this.fwCode = fwCode;
	}

	public Date getFwDate() {
		return fwDate;
	}

	public void setFwDate(Date fwDate) {
		this.fwDate = fwDate;
	}

	public String getIsNewApply() {
		return isNewApply;
	}

	public void setIsNewApply(String isNewApply) {
		this.isNewApply = isNewApply;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
