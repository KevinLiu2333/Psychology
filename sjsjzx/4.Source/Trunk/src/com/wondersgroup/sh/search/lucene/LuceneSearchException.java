/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import com.wondersgroup.sh.search.SearchException;

/**
 * The Class LuceneSearchException.
 */
public class LuceneSearchException extends SearchException {
	private static final long serialVersionUID = -1660498273275658348L;

	/**
	 * Instantiates a new lucene search exception.
	 */
	public LuceneSearchException() {
		super();
	}

	/**
	 * Instantiates a new lucene search exception.
	 * 
	 * @param s the s
	 */
	public LuceneSearchException(String s) {
		super(s);
	}

	/**
	 * Instantiates a new lucene search exception.
	 * 
	 * @param root the root
	 */
	public LuceneSearchException(Throwable root) {
		super(root);
	}

	/**
	 * Instantiates a new lucene search exception.
	 * 
	 * @param s the s
	 * @param root the root
	 */
	public LuceneSearchException(String s, Throwable root) {
		super(s, root);
	}

}
