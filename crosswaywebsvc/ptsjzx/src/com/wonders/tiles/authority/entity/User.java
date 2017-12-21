package com.wonders.tiles.authority.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("TEST_USER_INFO")
public class User {

	@Name
	@Column("USER_ID")
	private String userId;

	@Column("LOGON_NAME")
	private String logonName;

	@Column("PASSWORD")
	private String password;

	@Column("DISPLAY_NAME")
	private String displayName;

	@Column("STATE")
	private String state;

	@Column("DEPT")
	private String dept;

	@Column("E_MAIL")
	private String eMail;

	@Column("U_KEY")
	private String uKey;

	@Column("ROLE_ID")
	private String roleId;

	@Column("ORG_ID")
	private String orgId;

	@Column("ORG_CODE")
	private String orgCode;

	@Column("CREATE_TIME")
	private Date createTime;

	@Column("UPDATE_TIME")
	private Date updateTime;

	@Column("UPDATE_USER")
	private String updateUser;

	@Column("UPDATE_DATE")
	private Date updateDate;
	// 普通管理员标示
	@Column("ADMIN")
	private String admin;
	/** 用户类型（0-各委办用户，1-委办领导用户，2-科委用户） **/
	@Column("TYPE")
	private String type;

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getuKey() {
		return uKey;
	}

	public void setuKey(String uKey) {
		this.uKey = uKey;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", logonName=" + logonName + ", password=" + password + ", displayName=" + displayName + ", state=" + state + ", dept=" + dept
				+ ", eMail=" + eMail + ", uKey=" + uKey + ", roleId=" + roleId + ", orgId=" + orgId + ", orgCode=" + orgCode + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", updateUser=" + updateUser + "]";
	}

}