package com.wondersgroup.cmc.cache.impl;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

import com.wondersgroup.cmc.cache.CacheKey;
import com.wondersgroup.cmc.cache.CacheLoader;

/**
 * 基于MemCached缓存提供者
 * Created by xieguoking on 2015/4/3.
 */
public class MemCachedProvider<Val extends Serializable> extends AbstractCacheProvider<Val> {

    private MemcachedClient client;

    public MemCachedProvider(String host, int port, CacheLoader<CacheKey<String>, Val> cacheLoader) {
        super(cacheLoader);
        try {
            this.client = new MemcachedClient(new InetSocketAddress(host, port));
        } catch (IOException e) {
            if (logger.isFatalEnabled()) {
                logger.fatal("", e);
            }
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public Val getFromCache(Class<Val> type, CacheKey<String> key) {
        String keyStr = key.build();
        return (Val) client.get(keyStr);
    }

    @Override
    public void putToCache(CacheKey<String> cacheKey, Val checkObj, int expire) {
        String keyStr = cacheKey.build();
        client.set(keyStr, expire, checkObj);
    }

}
