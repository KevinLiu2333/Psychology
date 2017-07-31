/** 
 * @(#)LogoutAction.java 2008-10-16
 * 
 * Copyright (c) 1995-2008 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.wssip.application.login;

import com.wondersgroup.framework.core.web.struts2.action.BaseAction;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.model.bo.UaUserInfo;

/**
 * @author Yao Jianping
 * @version $Revision: 13 $ 2008-10-16
 * @author $Date: 2010-04-15 13:19:11 +0800 (周四, 15 四月 2010) $ by $Author: Suhl $
 * @since 1.0
 */
public class LogoutAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/**
	 * @see com.wondersgroup.framework.core.web.struts2.action.BaseAction#execute()
	 */

	public String logout() throws Exception {
		UaUserInfo securityUser = (UaUserInfo)getSession().get(SessionConstants.WSSIP_OPERATOR);
		if ( securityUser!=null && securityUser.getUserid()>0 ){
			LoginUtils.Logout(securityUser, getServletRequest().getSession().getId());
		}
		getSession().clear();
		getServletRequest().getSession().invalidate();
		return LOGIN;
	}
}
