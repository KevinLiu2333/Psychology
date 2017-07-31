/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.web;

import javax.servlet.ServletContext;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

/**
 * The Class WebApplicationContextUtils.
 */
public abstract class WebApplicationContextUtils {
	
	/**
	 * Gets the configuration.
	 * 
	 * @param sc the sc
	 * 
	 * @return the configuration
	 */
	public static LuceneConfiguration getConfiguration(ServletContext sc) {
		Object attr = sc.getAttribute(LuceneConfiguration.ROOT_WEB_APPLICATION_CONFIG_ATTRIBUTE);
		if (attr == null) {
			return null;
		}
		if (attr instanceof SearchException) {
			throw (SearchException) attr;
		}
		if (!(attr instanceof LuceneConfiguration)) {
			throw new IllegalStateException("Root context attribute is not of type WebApplicationContext: " + attr);
		}
		return (LuceneConfiguration) attr;
	}

	/**
	 * Gets the required configuration.
	 * 
	 * @param sc the sc
	 * 
	 * @return the required configuration
	 * 
	 * @throws IllegalStateException the illegal state exception
	 */
	public static LuceneConfiguration getRequiredConfiguration(ServletContext sc) throws IllegalStateException {
		LuceneConfiguration configuration = getConfiguration(sc);
		if (configuration == null) {
			throw new IllegalStateException("No LuceneConfiguration found: no ConfigLoaderListener registered?");
		}
		return configuration;
	}
}
