/**
 * @(#)RedisCacheProvider.java 2014-5-14
 * <p/>
 * Copyright (c) 1995-2014 Wonders Information Co.,Ltd.
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * <p/>
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with
 * the terms of the license agreement you entered into with Wonders Group.
 * <p/>
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.cmc.cache.impl;

import java.io.Serializable;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisException;

import com.wondersgroup.cmc.cache.CacheKey;
import com.wondersgroup.cmc.cache.CacheLoader;
import com.wondersgroup.cmc.utils.SerializableUtils;

/**
 * 基于Redis的缓存提供者
 *
 * @author xieguoking
 * @author ($Date$ modification by $Author$)
 * @version $Revision$ 2014-5-14
 * @since 1.0
 */
public class RedisCacheProvider<Val extends Serializable> extends AbstractCacheProvider<Val> {

	private JedisPool jedisPool;

	public RedisCacheProvider(String host, int port, CacheLoader<CacheKey<String>, Val> cacheLoader) {
		super(cacheLoader);
		JedisPoolConfig config = new JedisPoolConfig();
		this.jedisPool = new JedisPool(config,host, port);
	}

	private void redisSet(CacheKey<String> cacheKey, Val checkObj, int expire) {
		final byte[] key = cacheKey.build().getBytes();
		
		Jedis jedis = null;  
		try {   
			jedis = this.jedisPool.getResource();  
			jedis.set(key,SerializableUtils.serialize(checkObj));  
			if (expire != INT_UN_SET) {
				jedis.expire(key, expire);
			}
		} catch (Exception e) { 
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);  
			e.printStackTrace();
			throw new JedisException(e.getMessage());
		} finally {
			//返还到连接池
			if(jedis !=	null){
				this.jedisPool.returnResource(jedis);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private Val redisGet(Class<Val> type, CacheKey<String> key) {
		Jedis jedis = null;  
		try {   
			jedis = this.jedisPool.getResource();  
			final byte[] bytes = jedis.get(key.build().getBytes());  
			if (bytes == null)
				return null;

			return (Val) SerializableUtils.unserialize(bytes);
		} catch (Exception e) { 
			//释放redis对象
			jedisPool.returnBrokenResource(jedis);  
			e.printStackTrace();
			throw new JedisException(e.getMessage());
		} finally {
			//返还到连接池
			if(jedis !=	null){
				this.jedisPool.returnResource(jedis);
			}
		}
	}


	/**
	 * @see com.wondersgroup.cmc.cache.impl.AbstractCacheProvider#putToCache(com.wondersgroup.cmc.cache.CacheKey,
	 * java.lang.Object, int)
	 */
	@Override
	public void putToCache(CacheKey<String> cacheKey, Val checkObj, int expire) {
		try {
			redisSet(cacheKey, checkObj, expire);
		} catch (JedisConnectionException jce) {
			redisSet(cacheKey, checkObj, expire);
		}
	}

	/**
	 * @see com.wondersgroup.cmc.cache.impl.AbstractCacheProvider#getFromCache(java.lang.Class,
	 * com.wondersgroup.cmc.cache.CacheKey)
	 */
	@Override
	public Val getFromCache(Class<Val> type, CacheKey<String> key) {
		try {
			return redisGet(type, key);
		} catch (JedisConnectionException jce) {
			return redisGet(type, key);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		
	}
}
