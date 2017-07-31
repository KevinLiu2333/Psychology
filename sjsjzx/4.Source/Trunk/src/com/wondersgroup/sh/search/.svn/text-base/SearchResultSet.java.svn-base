/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.wondersgroup.sh.search.lucene.LuceneQuery;

/**
 * 查询结果集合
 * @author Tim
 * @date 2007/08/31
 */
public class SearchResultSet implements Serializable {
	private static final long serialVersionUID = 3244486456253630699L;
	@Expose
	private List<SearchResult> resultList;
	private String[] keywords;
	private Map metadata;
	@Expose
	private int totalCount;
	private Object groupValue;
	private LuceneQuery query;
	@Expose
	private double qtime;
	
	public SearchResultSet() {
		super();
	}

	public SearchResultSet(List<SearchResult> resultList) {
		this.resultList = resultList;		
	}
	
	public SearchResultSet(List<SearchResult> resultList, String[] keywords) {
		this(resultList);
		this.keywords = keywords;
	}

	public SearchResultSet(List<SearchResult> resultList, Map metadata) {
		this(resultList);		
		this.metadata = metadata;
	}
	
	public SearchResultSet(List<SearchResult> resultList, String[] keywords, Map metadata) {
		this(resultList, keywords);
		this.metadata = metadata;
	}

	public String toString() {
		String lineSeperator = System.getProperty("line.separator");
		StringBuffer sb = new StringBuffer("totalCount=").append(totalCount).append(lineSeperator);
		for (int i = 1; i <= resultList.size(); i++) {
			sb.append("result ").append(i).append(lineSeperator).append(resultList.get(i-1));
		}
		return sb.toString();
	}
	
	/**
	 * 获取相似关键字,可以用于搜索导航
	 * @return 关键字数组
	 */
	public String[] getKeywords() {
		return keywords;
	}

	/**
	 * 获取结果集合
	 * @return List of SearchResult.
	 * 如果是分页的结果集,返回的List长度等于总结果数目,
	 * 只有在对应的页码位置才有数据,其余为null
	 */
	public List<SearchResult> getResultList() {
		return resultList;
	}	

	/**
	 * 获取元数据
	 * @param key 元数据的关键字
	 * @return 元数据
	 */
	public Object getMetadata(String key) {
		return metadata.get(key);
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public Object getGroupValue() {
		return groupValue;
	}

	public void setGroupValue(Object groupValue) {
		this.groupValue = groupValue;
	}

	public LuceneQuery getQuery() {
		return query;
	}

	public void setQuery(LuceneQuery query) {
		this.query = query;
	}

	public double getQtime() {
		return qtime;
	}

	public void setQtime(double qtime) {
		this.qtime = qtime;
	}
}
