package com.wonders.tiles.authority.service;

import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.EntityService;

import com.wonders.tiles.authority.entity.Authority;
import com.wonders.tiles.authority.entity.Role;
import com.wonders.tiles.authority.entity.User;

@IocBean(fields = "dao")
public class SystemService extends EntityService<Object> {

	/**
	 * 用户登录
	 * @param userName
	 * @param password
	 * @return
	 */
	public User login(String loginName, String password) {
		if (loginName == null || password == null)
			return null;
		User temp = dao().fetch(User.class, Cnd.where("logonName", "=", loginName).and("state", "=","1"));
		if (temp != null) {
			if (password.equals(temp.getPassword())) {
				dao().fetchLinks(temp, "roles|authorities");
				return temp;
			}
		}
		return null;
	}
	
	public User createUser(User user) {
		
		return null;
	}

	public void load(Role role) {
		dao().fetchLinks(role, "authorities");
	}

	public Authority getRootAuthority() {
		Authority authority = dao().fetch(Authority.class, "101");
		dao().fetchLinks(authority, "children");
		return authority;
	}
	
	/**
	 * 通过登入名称获得用户信息.
	 * @param logonName - 登入名
	 * @return
	 */
	public User getUser(String logonName){
		User temp = dao().fetch(User.class, Cnd.where("logonName", "=", logonName));
		if (temp != null) {
				dao().fetchLinks(temp, "roles|authorities");
				return temp;
			}
		return null;
	}
}
