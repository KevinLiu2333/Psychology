/**
 * @(#)CacheKey.java 2014-10-10
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

package com.wondersgroup.cmc.cache;

/**
 * @author xieguoking
 * @version $Revision$ 2014-10-10
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public interface CacheKey<T> {
	/**
	 * 构建key值
	 *
	 * @return
	 */
	T build();
}
