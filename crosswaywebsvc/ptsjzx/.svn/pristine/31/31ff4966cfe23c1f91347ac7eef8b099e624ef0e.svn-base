package com.wonders.log.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("LOG_OPERATE")
public class OperateLog {
	@Name
	@Prev(els = @EL("$me.initUUIDwsId()"))
	@Column("ID")
	private String id;
	@Column("OPERATE_USER")
	private String operateUser;
	@Column("OPERATE_TYPE")
	private String operateType;
	@Column("OPERATE_DATE")
	private Date operateDate;
	@Column("OPERATE_RESULT")
	private String operateResult;
	@Column("OPERATE_DEPT")
	private String operateDept;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public Date getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}
	public String getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	public String getOperateDept() {
		return operateDept;
	}
	public void setOperateDept(String operateDept) {
		this.operateDept = operateDept;
	}
	public String initUUIDwsId() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}
}
