/**
 * @(#)CacheProvider.java 2014-5-14
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

import com.wondersgroup.cmc.cache.exception.KeyNotFoundException;

/**
 * 缓存对象，缓存键和缓存对象都不能为空。
 *
 * @author xieguoking
 * @version $Revision$ 2014-5-14
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public interface CacheProvider<Key, Val> {

	/**
	 * 存入缓存对象，缓存键不能为空。
	 *
	 * @param key 缓存键
	 * @param value 缓存值
	 */
	public void putIfAbsent(Key key, Val value);

	/**
	 * 根据缓存键获取对象，如果对象未进行缓存，从CacheLoader中获取对应缓存键的值，不为空进行缓存并返回，为空抛出异常。
	 *
	 * @param type
	 * @param key
	 * @return
	 * @throws KeyNotFoundException
	 */
	public Val get(Class<Val> type, Key key) throws KeyNotFoundException;

}
