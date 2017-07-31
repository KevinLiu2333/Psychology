/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

/**
 * Bridge to use a StringBridge as a FieldBridge.
 *
 */
public class String2FieldBridgeAdaptor implements FieldBridge {
	private final StringBridge stringBridge;

	public String2FieldBridgeAdaptor(StringBridge stringBridge) {
		this.stringBridge = stringBridge;
	}

	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		String indexedString = stringBridge.objectToString( value );
		//Do not add fields on empty strings, seems a sensible default in most situations
		//TODO if Store, probably also save empty ones
		if ( StringUtils.isNotEmpty( indexedString ) ) {
			Field field = new Field( name, indexedString, luceneOptions.getStore(), luceneOptions.getIndex(), luceneOptions.getTermVector() );
			field.setBoost( luceneOptions.getBoost().floatValue() );
			document.add( field );
		}
	}

}
