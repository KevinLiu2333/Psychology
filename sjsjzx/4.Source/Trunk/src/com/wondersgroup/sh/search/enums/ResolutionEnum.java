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

// TODO: Auto-generated Javadoc
/**
 * Date indexing resolution.
 */
public class ResolutionEnum extends Enum {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8245466165202627115L;

	/** The Constant YEAR. */
	public static final ResolutionEnum YEAR = new ResolutionEnum("year");
	
	/** The Constant MONTH. */
	public static final ResolutionEnum MONTH = new ResolutionEnum("month");
	
	/** The Constant DAY. */
	public static final ResolutionEnum DAY = new ResolutionEnum("day");
	
	/** The Constant HOUR. */
	public static final ResolutionEnum HOUR = new ResolutionEnum("hour");
	
	/** The Constant MINUTE. */
	public static final ResolutionEnum MINUTE = new ResolutionEnum("minute");
	
	/** The Constant SECOND. */
	public static final ResolutionEnum SECOND = new ResolutionEnum("second");
	
	/** The Constant MILLISECOND. */
	public static final ResolutionEnum MILLISECOND = new ResolutionEnum("millisecond");
	
	/**
	 * Instantiates a new resolution enum.
	 * 
	 * @param type the type
	 */
	private ResolutionEnum(String type) {
		super(type);
	}

	/**
	 * Gets the enum.
	 * 
	 * @param type the type
	 * 
	 * @return the enum
	 */
	public static ResolutionEnum getEnum(String type) {
		return (ResolutionEnum) getEnum(ResolutionEnum.class, type);
	}

	/**
	 * Gets the enum map.
	 * 
	 * @return the enum map
	 */
	public static Map getEnumMap() {
		return getEnumMap(ResolutionEnum.class);
	}

	/**
	 * Gets the enum list.
	 * 
	 * @return the enum list
	 */
	public static List getEnumList() {
		return getEnumList(ResolutionEnum.class);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public static Iterator iterator() {
		return iterator(ResolutionEnum.class);
	}			
}
