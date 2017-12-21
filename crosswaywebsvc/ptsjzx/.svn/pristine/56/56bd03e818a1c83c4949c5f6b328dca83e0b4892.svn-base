package com.wonders.jkfw.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

/**
 * 数据接口服务申请记录信息.
 * 数据接口和API服务等的申请共用一个entity.
 */
@Table("T_USER_SERVICE")
public class UserService {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	@Column("USER_SERVICE_ID")
	private String userServiceId;
	/**用户主键**/
	@Column("USER_ID")
	private String userId;
	/**服务主键**/
	@Column("SERVICE_ID")
	private String serviceId;
	/**api服务申请信息**/
	@Column("APPLY_INFO")
	private String applyInfo;
	/**接口服务链接**/
	@Column("SESSION_KEY")
	private String sessionKey;
	/**接口服务名称**/
	@Column("SERVICE_NAME")
	private String serviceName;
	/**申请者**/
	@Column("USER_NAME")
	private String userName;
	/**来源部门**/
	@Column("FROM_DEPARTMENT")
	private String fromDepartment;
	/**数据接口类型(api服务申请|文档下载...)**/
	@Column("TYPE")
	private String type;
	/**归属库**/
	@Column("DATABASE")
	private String database;
	/**接口服务状态(0:待审核,2:已过期,3:退回修改,4:已审核,5:已开通)**/
	@Column("STATUS")
	private String status;
	/**有效时间**/
	@Column("ACTIVE_TIME")
	private int activeTime;
	/**申请时间**/
	@Column("START_TIME")
	private Date startTime;
	/**有效期截止时间**/
	@Column("END_TIME")
	private Date endTime;
	/**审核时间**/
	@Column("AUDIT_TIME")
	private Date auditTime;
	/**审核意见**/
	@Column("AUDIT_MEMO")
	private String auditMemo;
	/**流转状态**/
	@Column("FLOW_STATUS")
	private String flowStatus;
	/**申请单位**/
	@Column("APPLY_UNIT")
	private String applyUnit;
	/**申请原因**/
	@Column("APPLY_REASON")
	private String applyReason;
	/**功能描述**/
	@Column("FUNCTION_MEMO")
	private String functionMemo;
	/**审核者ID**/
	@Column("CHECKED_USER_ID")
	private String checkedUserId;
	
	public String initUUID() {
		if (Strings.isEmpty(userServiceId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.userServiceId;
	}
	
	public String getUserServiceId() {
		return userServiceId;
	}
	public void setUserServiceId(String userServiceId) {
		this.userServiceId = userServiceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public int getActiveTime() {
		return activeTime;
	}
	public void setActiveTime(int activeTime) {
		this.activeTime = activeTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getFromDepartment() {
		return fromDepartment;
	}
	public void setFromDepartment(String fromDepartment) {
		this.fromDepartment = fromDepartment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlowStatus() {
		return flowStatus;
	}
	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getAuditMemo() {
		return auditMemo;
	}
	public void setAuditMemo(String auditMemo) {
		this.auditMemo = auditMemo;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	public String getFunctionMemo() {
		return functionMemo;
	}
	public void setFunctionMemo(String functionMemo) {
		this.functionMemo = functionMemo;
	}
	public String getApplyUnit() {
		return applyUnit;
	}
	public void setApplyUnit(String applyUnit) {
		this.applyUnit = applyUnit;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
}
