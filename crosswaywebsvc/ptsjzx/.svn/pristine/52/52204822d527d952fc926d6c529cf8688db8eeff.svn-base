package com.wonders.api.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("T_API_APPLY_LOG")
public class ApiApply {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("FW_ID")
	private String fwId;
	@Column("USER_ID")
	private String userId;
	@Column("APPLY_INFO")
	private String applyInfo;
	@Column("CHECKED_USER_ID")
	private String checkedUserId;
	@Column("APPLY_DATE")
	private Date applyDate;
	@Column("CHECKED_DATE")
	private Date checkedDate;
	@Column("DEPARTMENT")
	private String department;
	@Column("API_TYPE")
	private String apiType;
	/**库表类型（基础库，行业库）**/
	@Column("DATA_TYPE")
	private String dataType;
	/**是否审核(1：待审核，2：已审核，3:已退回)**/
	@Column("STATUS")
	private String status;
	@Column("APPLY_REASON")
	private String applyReason;
	@Column("NO_PASS_REASON")
	private String noPassReason;
	@Column("APPLY_TOPIC")
	private String applyTopic;
	/**申请批次**/
	@Column("APPLY_BATCH")
	private String applyBatch;
	/**是否提交(0：未提交，1：已提交)**/
	@Column("IS_SUBMIT")
	private String isSubmit;
	/**附件id**/
	@Column("FILE_IDS")
	private String fileIds;
	
	public String initUUID() {
		if (Strings.isEmpty(fwId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.fwId;
	}
	
	public String getFwId() {
		return fwId;
	}
	public void setFwId(String fwId) {
		this.fwId = fwId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplyInfo() {
		return applyInfo;
	}
	public void setApplyInfo(String applyInfo) {
		this.applyInfo = applyInfo;
	}
	public String getCheckedUserId() {
		return checkedUserId;
	}
	public void setCheckedUserId(String checkedUserId) {
		this.checkedUserId = checkedUserId;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
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

	public String getApplyTopic() {
		return applyTopic;
	}
	public void setApplyTopic(String applyTopic) {
		this.applyTopic = applyTopic;
	}
	public String getApplyBatch() {
		return applyBatch;
	}
	public void setApplyBatch(String applyBatch) {
		this.applyBatch = applyBatch;
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

	public String getNoPassReason() {
		return noPassReason;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
}
