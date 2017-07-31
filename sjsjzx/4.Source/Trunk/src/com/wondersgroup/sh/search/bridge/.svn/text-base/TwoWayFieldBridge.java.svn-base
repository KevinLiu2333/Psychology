/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge;

import org.apache.lucene.document.Document;

/**
 * A <code>FieldBridge</code> able to convert the index representation back into an object without losing information.
 * Any bridge expected to process a document id should implement this interface.
 *
 */
// FIXME rework the interface inheritance there are some common concepts with StringBridge
public interface TwoWayFieldBridge extends FieldBridge {
	/**
	 * Build the element object from the <code>Document</code>
	 *
	 * @param name field name
	 * @param document document
	 *
	 * @return The return value is the entity property value.
	 */
	Object get(String name, Document document);

	/**
	 * Convert the object representation to a string.
	 *
	 * @param object The object to index.
	 * @return string (index) representationT of the specified object. Must not be <code>null</code>, but
	 *         can be empty.
	 */
	String objectToString(Object object);
}
