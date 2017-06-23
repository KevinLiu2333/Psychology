package com.klsw.website.vo;

import java.util.List;

import com.klsw.common.mall.model.BgAuthority;

public class Role {
	private Integer roleId;
	private String roleName;
	private List<List<BgAuthority>> authorityIdList;
	
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
	public List<List<BgAuthority>> getAuthorityIdList() {
		return authorityIdList;
	}
	public void setAuthorityIdList(List<List<BgAuthority>> authorityIdList) {
		this.authorityIdList = authorityIdList;
	}
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", authorityIdList=" + authorityIdList + "]";
	}
	
}
