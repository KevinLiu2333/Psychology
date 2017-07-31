/** 
 * @(#)SerializationException.java 2014年11月11日
 * 
 * Copyright (c) 1995-2014 Wonders Information Co.,Ltd. 
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

package com.wondersgroup.cmc.utils;

/**
 * 序列化异常
 * @author xieguoking
 * @version $Revision$ 2014年11月11日
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class SerializationException extends RuntimeException {

	private static final long serialVersionUID = 7278965604779014397L;

	/**
	 * @param message
	 * @param cause
	 */
	public SerializationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public SerializationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SerializationException(Throwable cause) {
		super(cause);
	}

}
