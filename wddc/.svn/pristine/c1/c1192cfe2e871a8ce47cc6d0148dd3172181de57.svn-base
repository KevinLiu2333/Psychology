package com.wonders.wddc.suite.user.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("SUITE_ROLE_INFO")
public class RoleBo {
	
	public RoleBo(){}
	
	public RoleBo(String roleId){
		this.roleId = roleId;
	}

	@Name
	@Column("ROLE_ID")
	private String roleId;
	
	@Column("ROLE_DESC")
	private String roleDesc;
	
	@Column("ROLE_MEMO")
	private String roleMemo;
	
	@ManyMany(target = UserInfoBo.class, relation = "SUITE_USER_ROLE_MAP", from = "ROLE_ID", to = "USER_ID")
	private List<UserInfoBo> users;
	
	@ManyMany(target = AuthorityBo.class, relation = "SUITE_ROLE_AUTHORITY_MAP", from = "ROLE_ID", to = "NODE_ID")
	private List<AuthorityBo> authorities;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<UserInfoBo> getUsers() {
		return users;
	}

	public void setUsers(List<UserInfoBo> users) {
		this.users = users;
	}

	public List<AuthorityBo> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityBo> authorities) {
		this.authorities = authorities;
	}

	public String getRoleMemo() {
		return roleMemo;
	}

	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		RoleBo other = (RoleBo) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	public void addUser(UserInfoBo[] users) {
		if (this.users == null)
			this.users = new ArrayList<UserInfoBo>();
		this.users.addAll(Arrays.asList(users));
	}
	
	public void removeUser(UserInfoBo[] users) {
		if (this.users == null)
			this.users = new ArrayList<UserInfoBo>();
		this.users.removeAll(Arrays.asList(users));
	}
	
}
