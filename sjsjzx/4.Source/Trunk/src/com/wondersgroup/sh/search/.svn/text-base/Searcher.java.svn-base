/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 全文检索接口。
 * @author kchen
 *
 */
public interface Searcher extends Remote {
	/**
	 * 执行全文检索。
	 * 
	 * @param query 查询输入 
	 * @return SearchResultSet 查询结果 
	 */
	public SearchResultSet search(Query query) throws RemoteException;   
}
