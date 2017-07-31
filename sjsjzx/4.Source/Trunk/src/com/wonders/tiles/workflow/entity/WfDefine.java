package com.wonders.tiles.workflow.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 *流程定义对象
 */
@Table("WF_DEFINE")
public class WfDefine implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column("WF_ID")
	private String wfId;

	@Column("WF_CODE")
	private String wfCode;

	@Column("WF_NAME")
	private String wfName;

	@Column("PARENT_WF_CODE")
	private String parentWfCode;

	@Column("LIMIT_TYPE")
	private String limitType;

	@Column("LIMIT_COUNT")
	private String limitCount;

	@Column("BUS_TABLE_NAME")
	private String busTableName;

	@Column("VALIDITY")
	private String validity;

	// Constructors

	/** default constructor */
	public WfDefine() {
	}

	/** minimal constructor */
	public WfDefine(String wfId) {
		this.wfId = wfId;
	}

	/** full constructor */
	public WfDefine(String wfId, String wfCode, String wfName,
			String parentWfCode, String limitType, String limitCount,
			String busTableName, String validity) {
		this.wfId = wfId;
		this.wfCode = wfCode;
		this.wfName = wfName;
		this.parentWfCode = parentWfCode;
		this.limitType = limitType;
		this.limitCount = limitCount;
		this.busTableName = busTableName;
		this.validity = validity;
	}

	// Property accessors

	public String getWfId() {
		return this.wfId;
	}

	public void setWfId(String wfId) {
		this.wfId = wfId;
	}

	public String getWfCode() {
		return this.wfCode;
	}

	public void setWfCode(String wfCode) {
		this.wfCode = wfCode;
	}

	public String getWfName() {
		return this.wfName;
	}

	public void setWfName(String wfName) {
		this.wfName = wfName;
	}

	public String getParentWfCode() {
		return this.parentWfCode;
	}

	public void setParentWfCode(String parentWfCode) {
		this.parentWfCode = parentWfCode;
	}

	public String getLimitType() {
		return this.limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	public String getLimitCount() {
		return this.limitCount;
	}

	public void setLimitCount(String limitCount) {
		this.limitCount = limitCount;
	}

	public String getBusTableName() {
		return this.busTableName;
	}

	public void setBusTableName(String busTableName) {
		this.busTableName = busTableName;
	}

	public String getValidity() {
		return this.validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

}
