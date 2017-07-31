/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search;

/**
 * 全文索引接口。
 * @author kchen
 *
 */
public interface Indexer {
	/**
	 * 执行完全索引。完全索引一般先删除索引库中的所有索引，然后从源中创建所有内容的索引。
	 * @return 创建的索引记录数。
	 */
	public int fullIndex();
	
	/**
	 * 执行增量索引。根据源内容的变化情况创建或删除索引。
	 *
	 */
	public void incrementIndex();
}
