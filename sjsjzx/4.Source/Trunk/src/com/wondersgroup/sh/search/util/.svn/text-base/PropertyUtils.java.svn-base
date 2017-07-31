/*
 * $HeadURL: https://springside.googlecode.com/svn/springside3/trunk/modules/core/src/main/java/org/springside/modules/utils/PropertyUtils.java $
 * $Id: PropertyUtils.java 1075 2010-05-12 15:04:41Z calvinxiu $
 * Copyright (c) 2010 by Ericsson, all rights reserved.
 */

package com.wondersgroup.sh.search.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Properties Util函数.
 * 
 * @author calvin
 */
public class PropertyUtils {

    /** 配置文件拥有者 */
    private static Properties p = new Properties();

    /**
     * 类初始化
     */
    static {
        try {
			p.load(new BufferedInputStream(PropertyUtils.class.getResourceAsStream("/application.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * 获得配置属性值

     * 
     * @param key
     *            配置属性

     * @return 配置属性值

     */
    public static String getProperty(String key) {
        return p.getProperty(key);
    }

    /**
     * 获得配置属性值

     * 
     * @param key
     *            配置属性

     * @param defaultValue
     *            默认值

     * @return 配置属性值

     */
    public static String getProperty(String key, String defaultValue) {
        return p.getProperty(key, defaultValue);
    }
}
