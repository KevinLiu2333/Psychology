package com.wonders.wddc.suite.user.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("SUITE_USER_INFO")
public class UserInfoBo {

	public UserInfoBo(){}
	
	public UserInfoBo(String userId) {
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
	
	@Column("REGION_CODE")
	private String regionCode;
	
	@Column("STATUS")
	private String status;
	
	@Column("CURRENT_LOGON_DATE")
	private Date currentLogonDate;
	
	@Column("LAST_LOGON_DATE")
	private Date lastLogonDate;
	
	
	@Column("IS_ONLINE")
	private String isOnline;
	
	@Column("DEPT")
	private String dept;
	
	@Column("POSITION")
	private String position;
	
	@Column("USER_TYPE")
	private String userType;
	
	@Column("UNIT_ID")
	private String unitId;
	
	@ManyMany(target = RoleBo.class, relation = "SUITE_USER_ROLE_MAP", from = "USER_ID", to = "ROLE_ID")
	private List<RoleBo> roles;
	
	@ManyMany(target = AuthorityBo.class, relation = "SUITE_USER_AUTHORITY_MAP", from = "USER_ID", to = "NODE_ID")
	private List<AuthorityBo> authorities;

	
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
	public List<RoleBo> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleBo> roles) {
		this.roles = roles;
	}

	public List<AuthorityBo> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityBo> authorities) {
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
		UserInfoBo other = (UserInfoBo) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public void addRole(RoleBo[] roles) {
		if (this.roles == null)
			this.roles = new ArrayList<RoleBo>();
		this.roles.addAll(Arrays.asList(roles));
	}
	
	public void removeRole(RoleBo[] roles) {
		if (this.roles == null)
			this.roles = new ArrayList<RoleBo>();
		this.roles.removeAll(Arrays.asList(roles));
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}


	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	
	
}
