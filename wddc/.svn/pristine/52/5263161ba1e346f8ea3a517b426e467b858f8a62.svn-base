package com.wonders.wddc.suite.user.service;

import java.util.Date;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import com.wonders.wddc.suite.user.entity.UserInfoBo;

@IocBean
public class UserInfoService {
	@Inject
	private Dao dao;
	/**
	 * 增加在线用户信息.
	 * @param logonName
	 */
	public void addOnline(String logonName){
		UserInfoBo userInfoBo = dao.fetch(UserInfoBo.class, Cnd.where("logonName", "=", logonName));
		userInfoBo.setLastLogonDate(userInfoBo.getCurrentLogonDate());
		userInfoBo.setCurrentLogonDate(new Date());
		userInfoBo.setIsOnline("1");
		dao.update(userInfoBo);
		
	}
	/**
	 * 删除在线用户信息.
	 * @param logonName
	 */
	public void removeOnline(String logonName){
		UserInfoBo userInfoBo = dao.fetch(UserInfoBo.class, Cnd.where("logonName", "=", logonName));
		userInfoBo.setIsOnline("0");
		dao.update(userInfoBo);
		
	}

}
