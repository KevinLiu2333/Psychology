package com.wondersgroup.cmc.commons.monitor.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wondersgroup.cmc.commons.monitor.model.Optstatus;
import com.wondersgroup.cmc.commons.monitor.model.Optstatuslog;
import com.wondersgroup.cmc.commons.monitor.model.UserMonitorInfo;
import com.wondersgroup.cmc.commons.monitor.service.UserMonitorService;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.model.bo.UaAuthInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;
import com.wondersgroup.wssip.util.BeanTools;
import com.wondersgroup.wssip.util.StringTools;

public class UserMonitorServiceImpl implements UserMonitorService {

	private final Logger logger = Logger.getLogger("struts2.businesscontext.log");

	private void logOptStatus(Optstatus opt) {
		Optstatuslog optlog = new Optstatuslog();
		BeanTools.copyProperties(opt, optlog);
		optlog.setLogintime(opt.getLogintime());
		optlog.setLoginstatus("0");
		optlog.setLogouttime(new Date());
		optlog.setLinehour((optlog.getLogouttime().getTime() - optlog.getLogintime().getTime()) / 1000);
		CommonHibernateDaoUtils.save(optlog);
		CommonHibernateDaoUtils.delete(Optstatus.class, opt.getOptstatusid());
	}

	@Override
	public void userLogin(UserMonitorInfo userMonitorInfo) {

		List<Optstatus> optlist = CommonHibernateDaoUtils.find("from Optstatus where operatorid=?",
				userMonitorInfo.getUserid());
		boolean blnFind = false;
		for (Optstatus opt : optlist) {
			if (opt.getLoginsession().equals(userMonitorInfo.getSessionid())) {
				blnFind = true;
			}
			else {
				// logOptStatus(opt);
			}
		}
		if (blnFind == false) {
			Optstatus opt = new Optstatus();
			opt.setLoginsession(userMonitorInfo.getSessionid());
			opt.setCounterno(userMonitorInfo.getCounterno());
			opt.setLoginstatus("1");
			opt.setLogintime(new Date());
			opt.setOperatorid(userMonitorInfo.getUserid());
			opt.setOperatorname(userMonitorInfo.getLoginName());
			opt.setPcmac(userMonitorInfo.getMacAddress());
			opt.setUserstreetcode(userMonitorInfo.getUserstreetcode());
			opt.setUserstreetname(userMonitorInfo.getUserstreetname());
			CommonHibernateDaoUtils.save(opt);
		}

		logger.info(String.format("用户登入:%s[%s][%s][%s]", userMonitorInfo.getUserName(),
				userMonitorInfo.getRemoteAddr(), userMonitorInfo.getMacAddress(), userMonitorInfo.getSessionid()));

	}

	@Override
	public void userLogout(UserMonitorInfo userMonitorInfo) {
		List<Optstatus> optlist = CommonHibernateDaoUtils.find("from Optstatus where operatorid=? and loginsession=?",
				userMonitorInfo.getUserid(), userMonitorInfo.getSessionid());
		for (Optstatus opt : optlist) {
			logOptStatus(opt);
		}
		logger.info(String.format("用户登出:%s[%s][%s][%s]", userMonitorInfo.getUserName(),
				userMonitorInfo.getRemoteAddr(), userMonitorInfo.getMacAddress(), userMonitorInfo.getSessionid()));
	}

	@Override
	public boolean checkStatus(String operatorid, String sessionid) {
		List<Optstatus> optlist = CommonHibernateDaoUtils.find("from Optstatus where operatorid=? and loginsession=?",
				operatorid, sessionid);
		if (optlist != null && optlist.size() > 0)
			return true;
		return false;
	}
	
	@Override
	public List<Map<String, String>> queryPortalLoadArray(UaUserInfo securityUser) {
    	UaUserInfo userInfo = CommonHibernateDaoUtils.load(UaUserInfo.class, Long.valueOf(securityUser.getUserid()));
    	String ext1 = userInfo.getExt1();
    	if(StringTools.hasText(ext1)){
        	ext1 = "('"+ext1.replaceAll(",", "','")+"')";
    	}else {
			return null;
		}
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		List<UaAuthInfo> roleList = CommonHibernateDaoUtils.find("from UaAuthInfo a where a.nodeid in "+ext1);
		if (roleList != null && roleList.size() > 0){
			for (UaAuthInfo role : roleList){
				Map<String,String> map = new HashMap<String,String>();
				map.put("id", role.getNodeid().toString());
				map.put("url", role.getUrl());
				map.put("title", role.getNodename());
				map.put("closable", "true");
				result.add(map);
			}
		}
		return result;
	}
	
	@Override
	public String queryAppids(UaUserInfo securityUser) {
    	UaUserInfo userInfo = CommonHibernateDaoUtils.load(UaUserInfo.class, Long.valueOf(securityUser.getUserid()));
    	String ext2 = userInfo.getExt2();
		return ext2;
	}

}
