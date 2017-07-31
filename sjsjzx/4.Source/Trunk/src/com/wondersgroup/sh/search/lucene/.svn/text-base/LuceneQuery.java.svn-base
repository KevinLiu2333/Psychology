/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Sort;

import com.wondersgroup.sh.search.Query;

public class LuceneQuery extends Query {
	private static final long serialVersionUID = -8987790367840180363L;

	private String nativeQuery;
	private String[] oldQueries;
	private Filter filter;
	private Sort sort;
	private String[] queryFields;
	private String[] projections;
	private boolean trackDocScores;
	private boolean trackMaxScore;
	private boolean docsInOrder;
	private boolean fillFields;
	
	public LuceneQuery() {
		super();
	}

	public LuceneQuery(String nativeQuery) {
		this.nativeQuery = nativeQuery;
	}

	public LuceneQuery(String nativeQuery, Filter filter, Sort sort) {
		super();
		this.nativeQuery = nativeQuery;
		this.filter = filter;
		this.sort = sort;
	}

	public int getFetchSize() {
		return (size <= 0) ? LuceneSearcher.MAX_DOC_NUM : startPos + size - 1;
	}
	
	public Sort getSort() {
		return (sort == null) ? Sort.RELEVANCE : sort;
	}
	
	public String getNativeQuery() {
		return nativeQuery;
	}

	public void setNativeQuery(String nativeQuery) {
		this.nativeQuery = nativeQuery;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public String[] getQueryFields() {
		return queryFields;
	}

	public void setQueryFields(String[] queryFields) {
		this.queryFields = queryFields;
	}

	public String[] getProjections() {
		return projections;
	}

	public void setProjections(String[] projections) {
		this.projections = projections;
	}

	public String[] getOldQueries() {
		return oldQueries;
	}

	public void setOldQueries(String[] oldQueries) {
		this.oldQueries = oldQueries;
	}

	public boolean isTrackDocScores() {
		return trackDocScores;
	}

	public void setTrackDocScores(boolean trackDocScores) {
		this.trackDocScores = trackDocScores;
	}

	public boolean isTrackMaxScore() {
		return trackMaxScore;
	}

	public void setTrackMaxScore(boolean trackMaxScore) {
		this.trackMaxScore = trackMaxScore;
	}

	public boolean isDocsInOrder() {
		return docsInOrder;
	}

	public void setDocsInOrder(boolean docsInOrder) {
		this.docsInOrder = docsInOrder;
	}
	
	public boolean isFillFields() {
		return fillFields;
	}

	public void setFillFields(boolean fillFields) {
		this.fillFields = fillFields;
	}	
}
