/**
 * @(#)KeyNotFoundException.java 2014-10-15
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

package com.wondersgroup.cmc.cache.exception;

/**
 * 缓存键不存在异常
 *
 * @author xieguoking
 * @version $Revision$ 2014-10-15
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class KeyNotFoundException extends Exception {

	private static final long serialVersionUID = 0;

	private String cacheKey;

	public KeyNotFoundException(String cacheKey) {
		this.cacheKey = cacheKey;
	}

	@Override
	public String getMessage() {
		return String.format("CacheKey[%s] not found.", cacheKey);
	}

}
