package com.wonders.ws.bean;

import java.sql.Date;
import java.util.UUID;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.lang.Strings;

@Table("LOG_WS")
public class LogWebServiceBean {

	@Name
	@Prev(els = @EL("$me.initUUIDwsId()"))
	@Column("ID")
	private String	id;

	@Column("USER_ID")
	private String	userId;

	@Column("CALL_DATE")
	private Date	callDate;

	@Column("CALL_RESULT")
	private int		callResult;

	@Column("CALL_METHOD")
	private String	callMethod;

	public String initUUIDwsId() {
		if (Strings.isEmpty(id))
			return UUID.randomUUID().toString().replaceAll("-", "");
		return this.id;
	}

	public String getCallMethod() {
		return callMethod;
	}

	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCallDate() {
		return callDate;
	}

	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}

	public int getCallResult() {
		return callResult;
	}

	public void setCallResult(int callResult) {
		this.callResult = callResult;
	}

}
