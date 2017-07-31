/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge;

/**
 * <code>StringBridge<code> allowing a translation from the string representation back to the <code>Object</code>.
 * <code>objectToString( stringToObject( string ) )</code> and <code>stringToObject( objectToString( object ) )</code>
 * should be "idempotent". More precisely:
 * <ul>
 * <li><code>objectToString( stringToObject( string ) ).equals(string)</code>, for non <code>null</code> string.</li>
 * <li><code>stringToObject( objectToString( object ) ).equals(object)</code>, for non <code>null</code> object. </li>
 * </ul>
 * 
 * As for all Bridges implementations must be threasafe.
 * 
 */
public interface TwoWayStringBridge extends StringBridge {

	/**
	 * Convert the index string representation to an object.
	 *
	 * @param stringValue The index value.
	 * @return Takes the string representation from the Lucene index and transforms it back into the original
	 * <code>Object</code>.
	 */
	Object stringToObject(String stringValue);
}
