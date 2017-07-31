/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge.builtin;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.TwoWayStringBridge;


/**
 * Bridge for <code>URL</code>s.
 *
 */
public class UrlBridge implements TwoWayStringBridge {
	public Object stringToObject(String stringValue) {
		if ( StringUtils.isEmpty( stringValue ) ) {
			return null;
		}
		else {
			try {
				return new URL( stringValue );
			}
			catch ( MalformedURLException e ) {
				throw new SearchException( "Unable to build URL: " + stringValue, e );
			}
		}
	}

	public String objectToString(Object object) {
		return object == null ?
				null :
				object.toString();
	}
}
