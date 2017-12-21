package com.wonders.api.entity;

import java.util.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("API_LOG_WS")
public class ApiLogWebService {
	@Name
	@Prev(els = @EL("$me.initUUIDwsId()"))
	@Column("ID")
	private String id;

	@Column("DEPT_ID")
	private String deptId;

	@Column("CALL_SERVICE_ID")
	private String callServiceId;

	@Column("CALL_DATE")
	private Date callDate;

	@Column("CALL_RESULT")
	private long callResult;

	@Column("KEY")
	private String key;

	@Column("USER_ID")
	private String userId;
	@Column("USER_LOGINNAME")
	private String userLoginname;
	@Column("CALL_SERVICE_NAME")
	private String callServiceName;
	
	public String getCallServiceName() {
		return callServiceName;
	}

	public void setCallServiceName(String callServiceName) {
		this.callServiceName = callServiceName;
	}

	public String getUserLoginname() {
		return userLoginname;
	}

	public void setUserLoginname(String userLoginname) {
		this.userLoginname = userLoginname;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCallServiceId() {
		return callServiceId;
	}

	public void setCallServiceId(String callServiceId) {
		this.callServiceId = callServiceId;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public long getCallResult() {
		return callResult;
	}

	public void setCallResult(long callResult) {
		this.callResult = callResult;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String initUUIDwsId() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}
}
