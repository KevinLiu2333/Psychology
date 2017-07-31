/**
 * @(#)DefaultComposeCacheKey.java 2014-10-10
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

package com.wondersgroup.cmc.cache.key;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @author xieguoking
 * @version $Revision$ 2014-10-10
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class DefaultComposeCacheKey implements ComposeCacheKey<String> {

    private String catalog;
    private SortedMap<String, Serializable> map = new TreeMap<String, Serializable>();

    public DefaultComposeCacheKey(String catalog) {
        this.catalog = catalog;
    }

    /**
     * @see com.wondersgroup.cmc.cache.CacheKey#build()
     */
    @Override
    public String build() {
        final StringBuilder sb = new StringBuilder(map.size() * 10 + catalog.length());
        final Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            final String key = it.next();
            if (sb.length() != 0) {
                sb.append("&");
            } else {
                sb.append(catalog).append("?");
            }
            sb.append(key).append("=").append(map.get(key));
        }
        return sb.toString();
    }

    /**
     * @see com.wondersgroup.cmc.cache.key.ComposeCacheKey#add(java.lang.String, java.io.Serializable)
     */
    @Override
    public DefaultComposeCacheKey add(String key, Serializable val) {
        map.put(key, val);
        return this;
    }

    public SortedMap<String, Serializable> get() {
        return map;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
