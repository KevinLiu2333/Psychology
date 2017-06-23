package com.klsw.website.vo;

import java.util.List;

public class Actor {
	private Integer roleId;
	private String roleName;
	private List<RoleAuthority> roleAuthorityList;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<RoleAuthority> getRoleAuthorityList() {
		return roleAuthorityList;
	}
	public void setRoleAuthorityList(List<RoleAuthority> roleAuthorityList) {
		this.roleAuthorityList = roleAuthorityList;
	}
	
}
