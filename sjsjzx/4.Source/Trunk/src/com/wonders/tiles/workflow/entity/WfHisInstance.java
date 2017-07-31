package com.wonders.tiles.workflow.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

/**
 * 流程实例对象
 */
@Table("WF_HIS_INSTANCE")
public class WfHisInstance implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Name
	@Column("INSTANCE_ID")
	private String instanceId;

	@Column("BUS_ID")
	private String busId;
	
	@Column("WF_CODE")
	private String wfCode;

	@Column("CUR_NODE_ID")
	private String curNodeId;

	@Column("CUR_NODE_NAME")
	private String curNodeName;

	@Column("ROLE_ID")
	private String roleId;

	@Column("RECEVIE_DATE")
	private Date recevieDate;

	@Column("FINISH_DATE")
	private Date finishDate;

	@Column("IS_SIGN")
	private String isSign;

	@Column("SIGN_USER_ID")
	private String signUserId;

	@Column("SIGN_DATE")
	private Date signDate;

	@Column("FIN_PRE_NODE_ID")
	private String finPreNodeId;

	@Column("STATUS")
	private String status;

	@Column("VALIDITY")
	private String validity;
	
	@Column("IS_CALLBACK")
	private String isCallback="0";

	@Column("INSTANCE_NO")
	private String instanceNo;

	@Column("FINISH_USER_ID")
	private String finishUserId;

	@Column("TUDO_USER_ID")
	private String tudoUserId;
	
	@Column("DURATION_TYPE")
	private String durationType;
	
	@Column("DURATION_DATE")
	private Date durationDate;
	
	@Column("NODE_DURATION")
	private Integer nodeDuration;
	
	@Column("TOTAL_DURATION")
	private Integer totalDuration;

	@Column("OP_ALL_USER_IDS")
	private String opAllUserIds;
	
	@Column("SENDOUT_DEPT")
	private String sendoutDept;
	
	@Column("SENDOUT_FLAG")
	private String sendoutFlag;


	public Integer getNodeDuration() {
		return nodeDuration;
	}

	public void setNodeDuration(Integer nodeDuration) {
		this.nodeDuration = nodeDuration;
	}
	public String getDurationType() {
		return durationType;
	}

	public void setDurationType(String durationType) {
		this.durationType = durationType;
	}

	public Date getDurationDate() {
		return durationDate;
	}

	public void setDurationDate(Date durationDate) {
		this.durationDate = durationDate;
	}

	public Integer getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Integer totalDuration) {
		this.totalDuration = totalDuration;
	}

	public String getFinishUserId() {
		return finishUserId;
	}

	public void setFinishUserId(String finishUserId) {
		this.finishUserId = finishUserId;
	}

	public String getInstanceNo() {
		return instanceNo;
	}

	public void setInstanceNo(String instanceNo) {
		this.instanceNo = instanceNo;
	}

	public String getCurNodeId() {
		return curNodeId;
	}

	public void setCurNodeId(String curNodeId) {
		this.curNodeId = curNodeId;
	}

	public String getWfCode() {
		return wfCode;
	}

	public void setWfCode(String wfCode) {
		this.wfCode = wfCode;
	}

	public String getSignUserId() {
		return signUserId;
	}

	public void setSignUserId(String signUserId) {
		this.signUserId = signUserId;
	}

	/** default constructor */
	public WfHisInstance() {
	}

	public String getInstanceId() {
		return this.instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getBusId() {
		return this.busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}


	public String getCurNodeName() {
		return this.curNodeName;
	}

	public void setCurNodeName(String curNodeName) {
		this.curNodeName = curNodeName;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Date getRecevieDate() {
		return this.recevieDate;
	}

	public void setRecevieDate(Date recevieDate) {
		this.recevieDate = recevieDate;
	}

	public Date getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getIsSign() {
		return this.isSign;
	}

	public void setIsSign(String isSign) {
		this.isSign = isSign;
	}


	public Date getSignDate() {
		return this.signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public String getFinPreNodeId() {
		return this.finPreNodeId;
	}

	public void setFinPreNodeId(String finPreNodeId) {
		this.finPreNodeId = finPreNodeId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getIsCallback() {
		return isCallback;
	}

	public void setIsCallback(String isCallback) {
		this.isCallback = isCallback;
	}

	public String getTudoUserId() {
		return tudoUserId;
	}

	public void setTudoUserId(String tudoUserId) {
		this.tudoUserId = tudoUserId;
	}

	public String getOpAllUserIds() {
		return opAllUserIds;
	}

	public void setOpAllUserIds(String opAllUserIds) {
		this.opAllUserIds = opAllUserIds;
	}

	public String getSendoutDept() {
		return sendoutDept;
	}

	public void setSendoutDept(String sendoutDept) {
		this.sendoutDept = sendoutDept;
	}

	public String getSendoutFlag() {
		return sendoutFlag;
	}

	public void setSendoutFlag(String sendoutFlag) {
		this.sendoutFlag = sendoutFlag;
	}

}
