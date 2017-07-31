package com.wondersgroup.wssip.local.application.login;

import java.util.List;

import org.apache.log4j.Logger;

import com.wondersgroup.framework.core.web.struts2.action.BaseAction;
import com.wondersgroup.wssip.application.LoginConstants;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.application.login.LoginInfo;
import com.wondersgroup.wssip.application.login.LoginUtils;
import com.wondersgroup.wssip.model.bo.UaAuthInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;
import com.wondersgroup.wssip.service.UaLoginService;

public class UaLoginAction extends BaseAction implements LoginConstants, SessionConstants {

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(UaLoginAction.class);
	
	private UaLoginService uaLoginService;
	
	private LoginInfo loginInfo = new LoginInfo();
	public String login(){
		String loginName = null;
		String password = null;
		loginName = this.getServletRequest().getParameter("loginName");
		password = this.getServletRequest().getParameter("password");
		UaUserInfo user = null;
		try {
			user = uaLoginService.getUser(loginName,password);
		} catch (Exception e) {
			logger.error(loginName + " 登录失败:" + e.getMessage());
			loginInfo.setErrorMessage(e.getMessage());
			return INPUT;
		}
		LoginUtils.login(user, getServletRequest());
		List<UaAuthInfo> authList = uaLoginService.getAuthListByUser(user);
		this.getServletRequest().getSession().setAttribute(SessionConstants.MENU_DEFINE_CACHE,authList);
		LoginUtils.initMenu(getServletRequest());
		LoginUtils.queryPortalLoadArray(user, getServletRequest());
		LoginUtils.queryAppids(user, getServletRequest());
		return SUCCESS;
	}
	
	public UaLoginService getUaLoginService() {
		return uaLoginService;
	}

	public void setUaLoginService(UaLoginService uaLoginService) {
		this.uaLoginService = uaLoginService;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
}
