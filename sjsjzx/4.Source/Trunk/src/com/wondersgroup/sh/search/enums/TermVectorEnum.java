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
 * Defines the term vector storing strategy.
 */
public class TermVectorEnum extends Enum {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7843334745931105676L;
	
	/** Store term vectors. */
	public static final TermVectorEnum YES = new TermVectorEnum("YES");
	
	/** Do not store term vectors. */
	public static final TermVectorEnum NO = new TermVectorEnum("NO");
	
	/** Store the term vector + Token offset information. */
	public static final TermVectorEnum WITH_OFFSETS = new TermVectorEnum("WITH_OFFSETS");
	
	/** Store the term vector + token position information. */
	public static final TermVectorEnum WITH_POSITIONS = new TermVectorEnum("WITH_POSITIONS");
	
	/** Store the term vector + Token position and offset information. */
	public static final TermVectorEnum WITH_POSITION_OFFSETS = new TermVectorEnum("WITH_POSITION_OFFSETS");
	
	/**
	 * Instantiates a new term vector enum.
	 * 
	 * @param type the type
	 */
	private TermVectorEnum(String type) {
		super(type);
	}

	/**
	 * Lucene term vector.
	 * 
	 * @return the field. term vector
	 */
	public Field.TermVector luceneTermVector() {
		if( YES.equals(this) )
			return Field.TermVector.YES;
		else if( NO.equals(this) )
			return Field.TermVector.NO;
		else if( WITH_OFFSETS.equals(this) )
			return Field.TermVector.WITH_OFFSETS;
		else if( WITH_POSITIONS.equals(this) ) 
			return Field.TermVector.WITH_POSITIONS;
		else if( WITH_POSITION_OFFSETS.equals(this) )
			return Field.TermVector.WITH_POSITIONS_OFFSETS;
		else 
			throw new SearchException("Unknown field term vector value: " + this.getName());
	}
	
	/**
	 * Gets the enum.
	 * 
	 * @param type the type
	 * 
	 * @return the enum
	 */
	public static TermVectorEnum getEnum(String type) {
		return (TermVectorEnum) getEnum(TermVectorEnum.class, type);
	}

	/**
	 * Gets the enum map.
	 * 
	 * @return the enum map
	 */
	public static Map getEnumMap() {
		return getEnumMap(TermVectorEnum.class);
	}

	/**
	 * Gets the enum list.
	 * 
	 * @return the enum list
	 */
	public static List getEnumList() {
		return getEnumList(TermVectorEnum.class);
	}

	/**
	 * Iterator.
	 * 
	 * @return the iterator
	 */
	public static Iterator iterator() {
		return iterator(TermVectorEnum.class);
	}			
}

