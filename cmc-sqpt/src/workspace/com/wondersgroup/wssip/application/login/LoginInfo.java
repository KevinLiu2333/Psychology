/** 
 * @(#)LoginInfo.java 2008-9-5
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

import com.wondersgroup.cmc.dispatch.model.bo.AuthenticType;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author Yao Jianping
 * @version $Revision: 13 $ 2008-9-5
 * @author $Date: 2010-04-15 13:19:11 +0800 (周四, 15 四月 2010) $ by $Author: Suhl $
 * @since 1.0
 */
public class LoginInfo implements ValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8483847605854210766L;
	private String loginName;
	private String password;
	private String errorMessage;
	private String userName;
	private AuthenticType authenticType = AuthenticType.LOGINNAME;
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AuthenticType getAuthenticType() {
		return authenticType;
	}

	public void setLoginType(String loginType) {
		try{
			this.authenticType = AuthenticType.valueOf(loginType);
		} catch (Exception e) {}
	}
	
}
