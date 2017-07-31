package com.wonders.tiles.authority.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("ua_user_info")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2369803221176934787L;


	public User(){}
	
	public User(String userId) {
		this.userId = userId;
	}
	
	@Name
	@Column("USER_ID")
	private String userId;
	
	@Column("LOGON_NAME")
	private String logonName;
	
	@Column("PASSWORD")
	private String password;
	
	@Column("DISPLAY_NAME")
	private String displayName;
	
	@Column("REGION_ID")
	private String regionId;
	
	@Column("STATE")
	private String state;
	
	@Column("CURRENT_LOGON_DATE")
	private Date currentLogonDate;
	
	@Column("LAST_LOGON_DATE")
	private Date lastLogonDate;
	
	@Column("LOGON_FAIL_TIMES")
	private int logonFailTimes;
	
	@Column("LOCK_FLAG")
	private String lockFlag;
	
	@Column("LOCK_DATE")
	private Date lockDate;
	
	@Column("DEPT")
	private String dept;
	
	@Column("POSITION")
	private String position;
	//超级管理员标示
	@Column("SUPER_ADMIN")
	private String superadmin;
	//普通管理员标示
	@Column("ADMIN")
	private String admin;
	/**用户类型（0-各委办用户，1-委办领导用户，2-科委用户）**/
	@Column("TYPE")
	private String type;
	
	@ManyMany(target = Role.class, relation = "ua_user_role_map", from = "user_id", to = "role_id")
	private List<Role> roles;
	
	@ManyMany(target = Authority.class, relation = "ua_user_authority_map", from = "user_id", to = "node_id")
	private List<Authority> authorities;

	
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

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCurrentLogonDate() {
		return currentLogonDate;
	}

	public void setCurrentLogonDate(Date currentLogonDate) {
		this.currentLogonDate = currentLogonDate;
	}

	public Date getLastLogonDate() {
		return lastLogonDate;
	}

	public void setLastLogonDate(Date lastLogonDate) {
		this.lastLogonDate = lastLogonDate;
	}

	public int getLogonFailTimes() {
		return logonFailTimes;
	}

	public void setLogonFailTimes(int logonFailTimes) {
		this.logonFailTimes = logonFailTimes;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public Date getLockDate() {
		return lockDate;
	}

	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public void addRole(Role[] roles) {
		if (this.roles == null)
			this.roles = new ArrayList<Role>();
		this.roles.addAll(Arrays.asList(roles));
	}
	
	public void removeRole(Role[] roles) {
		if (this.roles == null)
			this.roles = new ArrayList<Role>();
		this.roles.removeAll(Arrays.asList(roles));
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public String getSuperadmin() {
		return superadmin;
	}

	public void setSuperadmin(String superadmin) {
		this.superadmin = superadmin;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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
	
}
