/**
 * @(#)CacheProviderBuilder.java 2014-10-14
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

package com.wondersgroup.cmc.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wondersgroup.cmc.cache.config.Option;
import com.wondersgroup.cmc.cache.impl.MemCachedProvider;
import com.wondersgroup.cmc.cache.impl.RedisCacheProvider;

/**
 * 缓存提供者构建器
 *
 * @author xieguoking
 * @author ($Date$ modification by $Author$)
 * @version $Revision$ 2014-10-14
 * @since 1.0
 */
public class CacheProviderBuilder {

    private Log logger = LogFactory.getLog(getClass());

    public static enum Type {
        REDIS, MEMCACHED, GUAVA;
    }

    private Type privoderName;

    private Map<Option<?>, Object> options = new HashMap<Option<?>, Object>();

    private CacheProviderBuilder(Type privoderName) {
        this.privoderName = privoderName;
    }

    /**
     * 构建指定类型过的缓存器
     *
     * @param privoderName
     */
    public static CacheProviderBuilder newBuilder(Type privoderName) {
        return new CacheProviderBuilder(privoderName);
    }

    /**
     * 设置选项值
     *
     * @param option
     * @param value
     * @return
     */
    public <T> CacheProviderBuilder option(Option<T> option, T value) {
        options.put(option, value);
        return this;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public CacheProvider build(CacheLoader cacheLoader) {

        switch (privoderName) {
            case REDIS:
                final String sHost = (String) options.get(Option.SERVER_HOST);
                final int iPort;
                if (options.containsKey(Option.SERVER_PORT)) {
                    iPort = (Integer) options.get(Option.SERVER_PORT);
                } else {
                    iPort =6379;
                }

                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("redis server:%s", sHost));
                    logger.debug(String.format("redis port:%s", iPort));
                }
                final RedisCacheProvider redisCacheProvider = new RedisCacheProvider(sHost, iPort, cacheLoader);
                if (options.containsKey(Option.EXPIRE_AFTER_WRITE_SECONDS)) {
                    Integer expire = (Integer) options.get(Option.EXPIRE_AFTER_WRITE_SECONDS);
                    if (logger.isDebugEnabled()) {
                        logger.debug(String.format("redis expire:%s(ms)", expire));
                    }
                    redisCacheProvider.setExpire(expire);
                }

                return redisCacheProvider;

            case MEMCACHED:
                final String mcHost = (String) options.get(Option.SERVER_HOST);
                final int mcPort;
                if (options.containsKey(Option.SERVER_PORT)) {
                    mcPort = (Integer) options.get(Option.SERVER_PORT);

                } else {
                    mcPort = 11211;
                }
                if (logger.isDebugEnabled()) {
                    logger.debug(String.format("memcached server:%s", mcHost));
                    logger.debug(String.format("memcached port:%s", mcPort));
                }
                return new MemCachedProvider(mcHost, mcPort, cacheLoader);
            case GUAVA:
            default:
                return null;
        }

    }
}
