/**
 * @(#)ComposeCacheKey.java 2014-10-10
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

package com.wondersgroup.cmc.cache.key;

import java.io.Serializable;

import com.wondersgroup.cmc.cache.CacheKey;

/**
 * @author xieguoking
 * @version $Revision$ 2014-10-10
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public interface ComposeCacheKey<T> extends CacheKey<T> {

	/**
	 * 增加一个键
	 * @param key
	 * @param val
	 */
	ComposeCacheKey<T> add(String key,Serializable val);
}
