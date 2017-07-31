/**
 * @(#)AbstractCacheProvider.java 2014-10-15
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

package com.wondersgroup.cmc.cache.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.cache.CacheKey;
import com.wondersgroup.cmc.cache.CacheLoader;
import com.wondersgroup.cmc.cache.CacheProvider;
import com.wondersgroup.cmc.cache.exception.KeyNotFoundException;

/**
 * @author xieguoking
 * @version $Revision$ 2014-10-15
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public abstract class AbstractCacheProvider<Val> implements CacheProvider<CacheKey<String>, Val> {

	protected Log logger = LogFactory.getLog(getClass());

	public static final int INT_UN_SET = -1;

	// 从缓存服务器连续获取数据失败（因为连接异常问题）最大次数，超过这个次数将停止从缓存获取数据
	public static final int MAX_ATTEMPTS = 5;

	private static int attempts = MAX_ATTEMPTS;

	private int expire = INT_UN_SET;// expire time(second)

	private CacheLoader<CacheKey<String>, Val> cacheLoader;

	public AbstractCacheProvider(CacheLoader<CacheKey<String>, Val> cacheLoader) {
		this.cacheLoader = cacheLoader;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public void checkKey(Object key) {
		if (key == null) {
			throw new RuntimeException("CacheKey must be not null.");
		}
	}

	public void checkValue(Object value) {
		if (value == null) {
			throw new RuntimeException("CacheObj must be not null.");
		}
	}

	public Val get(Class<Val> type, CacheKey<String> cacheKey) throws KeyNotFoundException {
		checkKey(cacheKey);
		Val cacheObj = null;
		boolean cacheable = attempts > 0;// 是否
		if (cacheable) {
			try {
                if(logger.isDebugEnabled()){
                    logger.debug(String.format("fetch from cache,cacheKey:[%s]",cacheKey));
                }
				cacheObj = getFromCache(type, cacheKey);
                if(logger.isDebugEnabled() &&cacheObj!=null){
                    logger.debug(String.format("fetch from cache success,cacheKey:[%s]",cacheKey));
                }
				attempts = MAX_ATTEMPTS;
			}
			catch (Exception e) {
				cacheObj = null;
				attempts--;
				cacheable = false;
                if(logger.isDebugEnabled()){
                    logger.debug(String.format("fetch from cache fail,cacheKey:[%s]",cacheKey));
                    logger.debug("getFromCache", e);
                }

			}
		}

		if (cacheObj == null) {

            if(logger.isDebugEnabled()){
                logger.debug(String.format("fetch from repo,cacheKey:[%s]",cacheKey));
            }
			if (cacheLoader == null) {
				throw new NullPointerException("cacheLoader");
			}
			cacheObj = cacheLoader.load(cacheKey);
			if (cacheObj == null) {
				throw new KeyNotFoundException(cacheKey.toString());
			}
            if(logger.isDebugEnabled()){
                logger.debug(String.format("put it cache:%s",cacheable?"Yes":"No"));
            }
			if (cacheable) {
				putIfAbsent(cacheKey, cacheObj);
			}
		}
		return cacheObj;
	}

	/**
	 * @see com.wondersgroup.cmc.cache.CacheProvider#putIfAbsent(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void putIfAbsent(CacheKey<String> cacheKey, Val checkObj) {
		checkKey(cacheKey);
		checkValue(checkObj);
		putToCache(cacheKey, checkObj, expire);
	}

	public abstract Val getFromCache(Class<Val> type, CacheKey<String> key);

	public abstract void putToCache(CacheKey<String> cacheKey, Val checkObj, int expire);

	public static void disable() {
		attempts = 0;
	}

	public static void enable() {
		attempts = MAX_ATTEMPTS;
	}

}
