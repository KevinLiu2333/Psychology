package com.wondersgroup.wssip.application.login;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.model.bo.UaUserInfo;

public class LoginListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		//Operator operator = (Operator)event.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR);
		UaUserInfo operator = (UaUserInfo)event.getSession().getAttribute(SessionConstants.WSSIP_OPERATOR);
		if ( operator!=null && operator.getUserid()>0 ){
			LoginUtils.Logout(operator, event.getSession().getId());
		}

	}

}
