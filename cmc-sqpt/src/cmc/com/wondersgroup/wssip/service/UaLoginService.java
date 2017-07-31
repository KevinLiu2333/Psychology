package com.wondersgroup.wssip.service;

import java.util.List;

import com.wondersgroup.wssip.model.bo.UaAuthInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;

public interface UaLoginService {
	public UaUserInfo getUser(String loginName,String password) throws Exception;
	public List<UaAuthInfo> getAuthListByUser(UaUserInfo user);
}
