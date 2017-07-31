/**
 * @(#)Option.java 2014-10-16
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

package com.wondersgroup.cmc.cache.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 缓存配置项
 * @author xieguoking
 * @version $Revision$ 2014-10-16
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class Option<T> {

	private static final AtomicInteger nextId = new AtomicInteger();

	private static final ConcurrentMap<String, Boolean> names = new ConcurrentHashMap<String, Boolean>();

	/**
	 * redis/memcached服务地址
	 */
	public static final Option<String> SERVER_HOST = valueOf("cache.server.host");

	/**
	 * redis/memcached服务端口
	 */
	public static final Option<Integer> SERVER_PORT = valueOf("cache.server.port");

	/**
	 * 缓存在最近一次写入之后的失效时间（秒）
	 */
	public static final Option<Integer> EXPIRE_AFTER_WRITE_SECONDS =valueOf("cache.expire.after.write.seconds");

	/**
	 * 缓存在最近一次读取之后的失效时间（秒）
	 */
	public static final Option<Integer> EXPIRE_AFTER_ACCESS_SECONDS =valueOf("cache.expire.after.access.seconds");

	private int id;

	private String name;

	public static <T> Option<T> valueOf(String name) {
		return new Option<T>(name);
	}

	public Option(String name) {
		if (name == null) {
			throw new NullPointerException("name");
		}

		if (names.putIfAbsent(name, Boolean.TRUE) != null) {
			throw new IllegalArgumentException(String.format("'%s' is already in use", name));
		}
		this.id = nextId.incrementAndGet();
		this.name = name;
	}

	public final int id() {
		return this.id;
	}

	public final String name() {
		return this.name;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return this.name;
	}

}
