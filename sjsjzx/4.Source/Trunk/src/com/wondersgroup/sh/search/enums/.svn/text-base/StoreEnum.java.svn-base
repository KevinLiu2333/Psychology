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

/**
 * Whether or not the value is stored in the document.
 */
public class StoreEnum extends Enum {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1210735443370456891L;

	/** does not store the value in the index. */
	public static final StoreEnum NO = new StoreEnum("NO");
	
	/** stores the value in the index. */
	public static final StoreEnum YES = new StoreEnum("YES");
	
	/** stores the value in the index in a compressed form. */
	public static final StoreEnum COMPRESS = new StoreEnum("COMPRESS");
	
	/**
	 * Instantiates a new store enum.
	 * 
	 * @param type the type
	 */
	private StoreEnum(String type) {
		super(type);
	}

	/**
	 * Lucene field store.
	 * 
	 * @return the field. store
	 */
	public Field.Store luceneFieldStore() {
		if( NO.equals(this) )
			return Field.Store.NO;
		else if( YES.equals(this) ) 
			return Field.Store.YES;
		else if( COMPRESS.equals(this) )
			return Field.Store.YES;
		else
			throw new SearchException("Unknown field store value: " + this.getName());
	}
	
	/**
	 * Gets the enum.
	 * 
	 * @param type the type
	 * 
	 * @return the enum
	 */
	public static StoreEnum getEnum(String type) {
		return (StoreEnum) getEnum(StoreEnum.class, type);
	}

	/**
	 * Gets the enum map.
	 * 
	 * @return the enum map
	 */
	public static Map getEnumMap() {
		return getEnumMap(StoreEnum.class);
	}

	/**
	 * Gets the enum list.
	 * 
	 * @return the enum list
	 */
	public static List getEnumList() {
		return getEnumList(StoreEnum.class);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public static Iterator iterator() {
		return iterator(StoreEnum.class);
	}		
}
