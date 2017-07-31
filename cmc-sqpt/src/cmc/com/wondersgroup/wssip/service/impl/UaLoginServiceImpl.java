package com.wondersgroup.wssip.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.model.bo.UaAuthInfo;
import com.wondersgroup.wssip.model.bo.UaRoleInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;
import com.wondersgroup.wssip.service.UaLoginService;

public class UaLoginServiceImpl implements UaLoginService{

	@Override
	public UaUserInfo getUser(String loginName, String password) throws Exception {
		String hql = " from UaUserInfo t where t.status='1' and t.loginname=?";
		List<UaUserInfo> userList = CommonHibernateDaoUtils.find(hql, loginName);
		//List<UaUserInfo> userList = CommonHibernateDaoUtils.find(hql);
		if (userList == null || userList.size() == 0){
			throw new Exception("用户名不存在。");
		} else {
			UaUserInfo user = userList.get(0);
			if (password.equals(user.getPassword())){
				return userList.get(0);
			}
			throw new Exception("用户名或密码错误。");
		}
	}

	@Override
	public List<UaAuthInfo> getAuthListByUser(UaUserInfo user) {
		List<UaRoleInfo> roleList = user.getRoleList();
		Set<UaAuthInfo> authSet = new HashSet<UaAuthInfo>();
		for (UaRoleInfo role : roleList){
			if (!"1".equals(role.getStatus())){
				continue;
			} else {
				List<UaAuthInfo> authList = role.getAuthList();
				for (UaAuthInfo auth : authList){
					if (!"1".equals(auth.getStatus()) || auth.getParentnodeid() == null){
						continue;
					} else {
						authSet.add(auth);
					}
				}
			}
		}
		List<UaAuthInfo> authList = new ArrayList<UaAuthInfo>();
		authList.addAll(authSet);
		Collections.sort(authList,new Comparator<UaAuthInfo>(){
			@Override
			public int compare(UaAuthInfo o1, UaAuthInfo o2) {
				return o1.getNodeorder().compareTo(o2.getNodeorder());
			}	
		});
		return authList;
	}

}
