package com.wonders.tiles.authority.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("ua_role_info")
public class Role {
	
	public Role(){}
	
	public Role(String roleId){
		this.roleId = roleId;
	}

	@Name
	@Column("ROLE_ID")
	private String roleId;
	
	@Column("ROLE_DESC")
	private String roleDesc;
	
	@ManyMany(target = User.class, relation = "ua_user_role_map", from = "role_id", to = "user_id")
	private List<User> users;
	
	@ManyMany(target = Authority.class, relation = "ua_role_authority_map", from = "role_id", to = "node_id")
	private List<Authority> authorities;

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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
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
		Role other = (Role) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	public void addUser(User[] users) {
		if (this.users == null)
			this.users = new ArrayList<User>();
		this.users.addAll(Arrays.asList(users));
	}
	
	public void removeUser(User[] users) {
		if (this.users == null)
			this.users = new ArrayList<User>();
		this.users.removeAll(Arrays.asList(users));
	}
	
}
