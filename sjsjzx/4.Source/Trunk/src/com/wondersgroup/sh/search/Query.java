/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search;

import java.io.Serializable;

/**
 * 查询输入的数据结构。
 * @author kchen
 *
 */
public class Query implements Serializable {
	private static final long serialVersionUID = 537542097814081642L;

	/**
	 * 从结果集中获取记录的起始位置。<br>
	 * 缺省为1
	 */
	protected int startPos = 1;

	/**
	 * 从结果集中获取记录的数量。<br>
	 * 缺省为0，代表所有。
	 */
	protected int size = 0;
	
	/**
	 * 设置从结果集中获取记录的范围。
	 * @param startPos
	 * @param size
	 */
	public void setRange( int startPos , int size ) {
		this.startPos = startPos ;
		this.size = size ;
	}	
	
	/**
	 * 设置从结果集中获取记录的范围。
	 * @param pageSize 页面大小
	 * @param pageNum 页码
	 */
	public void setPageRange( int pageSize , int pageNum ) {
		setRange(pageSize * (pageNum - 1) + 1,	pageSize * pageNum);
	}
			
	public boolean isInPage(int index) {
		return (size <= 0) || (index >= startPos - 1 && index < startPos + size - 1);  
	}
	
	public int getSize() {
		return size;
	}

	public int getStartPos() {
		return startPos;
	}
}
