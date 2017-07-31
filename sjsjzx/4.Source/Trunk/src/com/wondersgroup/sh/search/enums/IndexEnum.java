/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.enums;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.enums.Enum;
import org.apache.lucene.document.Field;

import com.wondersgroup.sh.search.SearchException;


// TODO: Auto-generated Javadoc
/**
 * Defines how an Field should be indexed.
 */
public class IndexEnum extends Enum {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7432257425323364173L;

	/** Do not index the field value. This field can thus not be searched, but one can still access its contents provided it is {@link StoreEnum stored}. */
	public static final IndexEnum NO = new IndexEnum("NO");
	
	/** Index the field's value so it can be searched. An Analyzer will be used to tokenize and possibly further normalize the text before its terms will be stored in the index. This is useful for common text. */
	public static final IndexEnum TOKENIZED = new IndexEnum("TOKENIZED");
	
	/** Index the field's value without using an Analyzer, so it can be searched. As no analyzer is used the value will be stored as a single term. This is useful for unique Ids like product numbers. */
	public static final IndexEnum UN_TOKENIZED = new IndexEnum("UN_TOKENIZED");
	
	/** Index the field's value without an Analyzer, and disable the storing of norms.  No norms means that index-time boosting and field length normalization will be disabled.  The benefit is less memory usage as norms take up one byte per indexed field for every document in the index. */
	public static final IndexEnum NO_NORMS = new IndexEnum("NO_NORMS");
	
	/**
	 * Instantiates a new index enum.
	 * 
	 * @param type the type
	 */
	private IndexEnum(String type) {
		super(type);
	}

	/**
	 * Lucene field index.
	 * 
	 * @return the field. index
	 */
	public Field.Index luceneFieldIndex() {
		if( NO.equals(this) ) 
			return Field.Index.NO;
		else if( TOKENIZED.equals(this) ) 
			return Field.Index.ANALYZED;
		else if( UN_TOKENIZED.equals(this) ) 
			return Field.Index.NOT_ANALYZED;
		else if( NO_NORMS.equals(this) ) 
			return Field.Index.NOT_ANALYZED_NO_NORMS;
		else
			throw new SearchException("Unknown field index value: " + this.getName());
	}
	
	/**
	 * Gets the enum.
	 * 
	 * @param type the type
	 * 
	 * @return the enum
	 */
	public static IndexEnum getEnum(String type) {
		return (IndexEnum) getEnum(IndexEnum.class, type);
	}

	/**
	 * Gets the enum map.
	 * 
	 * @return the enum map
	 */
	public static Map getEnumMap() {
		return getEnumMap(IndexEnum.class);
	}

	/**
	 * Gets the enum list.
	 * 
	 * @return the enum list
	 */
	public static List getEnumList() {
		return getEnumList(IndexEnum.class);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public static Iterator iterator() {
		return iterator(IndexEnum.class);
	}	
}
