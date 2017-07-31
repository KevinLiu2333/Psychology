package com.wondersgroup.cmc.commons.monitor.service;

import java.util.List;
import java.util.Map;

import com.wondersgroup.cmc.commons.monitor.model.UserMonitorInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;

public interface UserMonitorService {
	
	public void userLogin(UserMonitorInfo userMonitorInfo);
	
	public void userLogout(UserMonitorInfo userMonitorInfo);
	
	public boolean checkStatus(String optstatusid,String sessionid);
	
	public List<Map<String, String>> queryPortalLoadArray(UaUserInfo securityUser);
	
	public String queryAppids(UaUserInfo securityUser);
	
}
