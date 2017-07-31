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
 * The Class FileFieldEnum.
 */
public class FileFieldEnum extends Enum {
	private static final long serialVersionUID = -7442862561743912630L;

	public static final FileFieldEnum PATH = new FileFieldEnum("path");		//document id
	public static final FileFieldEnum REL_PATH = new FileFieldEnum("relativePath");
	public static final FileFieldEnum NAME = new FileFieldEnum("name");
	public static final FileFieldEnum TITLE = new FileFieldEnum("title");
	public static final FileFieldEnum CONTENT = new FileFieldEnum("content");
	public static final FileFieldEnum EXT = new FileFieldEnum("ext");
	public static final FileFieldEnum MODIFIED_TIME = new FileFieldEnum("modifiedTime");
	public static final FileFieldEnum LENGTH = new FileFieldEnum("length");
	public static final FileFieldEnum KEYWORDS = new FileFieldEnum("keywords");
	public static final FileFieldEnum URL = new FileFieldEnum("url");
	public static final FileFieldEnum FILETYPE = new FileFieldEnum("fileType");
	
	private FileFieldEnum(String arg0) {
		super(arg0);
	}

	public static FileFieldEnum getEnum(String type) {
		return (FileFieldEnum) getEnum(FileFieldEnum.class, type);
	}

	public static Map getEnumMap() {
		return getEnumMap(FileFieldEnum.class);
	}

	public static List getEnumList() {
		return getEnumList(FileFieldEnum.class);
	}

	public static Iterator iterator() {
		return iterator(FileFieldEnum.class);
	}		
}
