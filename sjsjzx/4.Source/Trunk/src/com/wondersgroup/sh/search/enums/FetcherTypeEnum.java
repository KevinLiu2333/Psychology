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

/**
 * The Class FetcherTypeEnum.
 */
public class FetcherTypeEnum extends Enum {
	private static final long serialVersionUID = 5292452160563507492L;

	/** The Constant DB. */
	public static final FetcherTypeEnum DB = new FetcherTypeEnum("DB");
	
	/** The Constant FILE. */
	public static final FetcherTypeEnum FILE = new FetcherTypeEnum("FILE");
	
	/**
	 * Instantiates a new fetcher type enum.
	 * 
	 * @param arg0 the arg0
	 */
	private FetcherTypeEnum(String arg0) {
		super(arg0);
	}
	
	/**
	 * Gets the enum.
	 * 
	 * @param type the type
	 * 
	 * @return the enum
	 */
	public static FetcherTypeEnum getEnum(String type) {
		return (FetcherTypeEnum) getEnum(FetcherTypeEnum.class, type);
	}

	/**
	 * Gets the enum map.
	 * 
	 * @return the enum map
	 */
	public static Map getEnumMap() {
		return getEnumMap(FetcherTypeEnum.class);
	}

	/**
	 * Gets the enum list.
	 * 
	 * @return the enum list
	 */
	public static List getEnumList() {
		return getEnumList(FetcherTypeEnum.class);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public static Iterator iterator() {
		return iterator(FetcherTypeEnum.class);
	}	
}
