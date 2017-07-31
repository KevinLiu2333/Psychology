/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge.builtin;

import org.apache.commons.lang.StringUtils;

/**
 * Map a short element
 *
 */
public class ShortBridge extends NumberBridge {
	public Object stringToObject(String stringValue) {
		if ( StringUtils.isEmpty( stringValue ) ) return null;
		return new Short( stringValue );
	}
}
