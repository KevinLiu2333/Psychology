package com.wondersgroup.wssip.local.application.login;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.application.login.LoginUtils;
import com.wondersgroup.wssip.model.bo.UaUserInfo;

public class UaLogoutAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String logout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UaUserInfo securityUser = (UaUserInfo) session.getAttribute(SessionConstants.WSSIP_OPERATOR);
		if ( securityUser!=null && securityUser.getUserid()>0 ){
			LoginUtils.Logout(securityUser, session.getId());
		}
		removeAllAttributes(session);
		session.invalidate();
		return LOGIN;
	}
	
	private void removeAllAttributes(HttpSession session) {
		if (session == null)
			return;
		Enumeration<?> enu = session.getAttributeNames();
		while (enu.hasMoreElements()) {
			session.removeAttribute((String) enu.nextElement());
		}
	}
}
