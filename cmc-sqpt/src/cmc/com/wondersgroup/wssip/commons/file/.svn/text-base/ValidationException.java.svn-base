/** 
 * @(#)ValidationException.java 2008-10-30
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

package com.wondersgroup.wssip.commons.file;

/**
 * ValidationException
 * 
 * @author Yao Jianping
 * @version $Revision: 8509 $ 2008-10-30
 * @author $Date: 2008-12-09 13:17:38 +0800 (周二, 09 十二月 2008) $ by $Author: Suhl $
 * @since 1.0
 */
public class ValidationException extends Exception {
	private static final long serialVersionUID = 1L;
	private final Validator validator;

	/**
	 * Constructs an <code>Exception</code> with the specified detail message.
	 * 
	 * @param s the detail message.
	 */
	public ValidationException(String s, Validator validator) {
		super(s);
		this.validator = validator;
	}

	public ValidationException(String message, Validator validator, Throwable cause) {
		super(message, cause);
		this.validator = validator;
	}

	/**
	 * @return the validator
	 */
	public Validator getValidator() {
		return validator;
	}

}
