/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

/**
 * Bridge to use a TwoWayStringBridge as a TwoWayFieldBridge
 *
 */
public class TwoWayString2FieldBridgeAdaptor extends String2FieldBridgeAdaptor implements TwoWayFieldBridge {
	private final TwoWayStringBridge stringBridge;

	public TwoWayString2FieldBridgeAdaptor(TwoWayStringBridge stringBridge) {
		super( stringBridge );
		this.stringBridge = stringBridge;
	}

	public String objectToString(Object object) {
		return stringBridge.objectToString( object );
	}

	public Object get(String name, Document document) {
		Field field = document.getField( name );
		if (field == null) {
			return stringBridge.stringToObject( null );
		}
		else {
			return stringBridge.stringToObject( field.stringValue() );
		}
	}
}
