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
 * Link between a java property and a Lucene Document
 * Usually a Java property will be linked to a Document Field.
 * 
 * All implementations need to be threadsafe.
 *
 */
public interface FieldBridge {

	/**
	 * Manipulate the document to index the given value.
	 * A common implementation is to add a Field <code>name</code> to the given document following
	 * the parameters (<code>store</code>, <code>index</code>, <code>boost</code>) if the
	 * <code>value</code> is not null
	 * @param luceneOptions Contains the parameters used for adding <code>value</code> to 
	 * the Lucene <code>document</code>.
	 */
	void set(String name, Object value, Document document, LuceneOptions luceneOptions);
}
