package com.wonders.tiles.logger.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("GZZ_OP_LOG")
public class LoggerCol {
	@Name
	@Prev(els = @EL("$me.initUUID()"))
	/**
	 * 操作日志主键
	 */
	@Column("OP_LOG_ID")
	private String opLogId;
	/**
	 * 日志分类
	 */
	@Column("LOG_CATALOG")
	private String logCatalog;
	/**
	 * 操作类型
	 */
	@Column("OP_TYPE")
	private String opType;
	/**
	 * 操作描述
	 */
	@Column("OP_CONTENT")
	private String opContent;
	/**
	 * 操作字段
	 */
	@Column("OP_COL")
	private String opCol;
	/**
	 * 业务Id
	 */
	@Column("OP_BUS_ID")
	private String opBusId;
	/**
	 * 原有值
	 */
	@Column("OLD_VALUE")
	private String oldValue;
	/**
	 * 操作后值
	 */
	@Column("NEW_VALUE")
	private String newValue;
	/**
	 * 操作人Id
	 */
	@Column("OP_USER_ID")
	private String opUserId;
	/**
	 * 操作人名称
	 */
	@Column("OP_USER_NAME")
	private String opUserName;
	/**
	 * 操作时间
	 */
	@Column("OP_TIME")
	private Date opTime;
	/**
	 * 操作状态
	 */
	@Column("OP_STATUS")
	private String opStatus;
	
	public String initUUID() {
		if (Strings.isEmpty(opLogId))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.opLogId;
	}

	public String getOpLogId() {
		return opLogId;
	}

	public void setOpLogId(String opLogId) {
		this.opLogId = opLogId;
	}

	public String getLogCatalog() {
		return logCatalog;
	}

	public void setLogCatalog(String logCatalog) {
		this.logCatalog = logCatalog;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpTpye(String opType) {
		this.opType = opType;
	}

	public String getOpContent() {
		return opContent;
	}

	public void setOpContent(String opContent) {
		this.opContent = opContent;
	}

	public String getOpCol() {
		return opCol;
	}

	public void setOpCol(String opCol) {
		this.opCol = opCol;
	}

	public String getOpBusId() {
		return opBusId;
	}

	public void setOpBusId(String opBusId) {
		this.opBusId = opBusId;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOpUserId() {
		return opUserId;
	}

	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}

	public String getOpUserName() {
		return opUserName;
	}

	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public String getOpStatus() {
		return opStatus;
	}

	public void setOpStatus(String opStatus) {
		this.opStatus = opStatus;
	}
	
	
}
