package com.wondersgroup.sh.search.test;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchPortal;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.lucene.LuceneQuery;

public class SingleSearchTester extends SearchPortalTester {
	private static final Logger logger = Logger.getLogger(SingleSearchTester.class);

	private SearchResultSet resultSet;
	private String indexId;
	
	public SingleSearchTester(SearchPortal portal, String queryString, String indexId) {
		super(portal, queryString);
		this.indexId = indexId;
	}
	
	protected void doSearch() {
		logger.info("在索引'" + indexId + "'中查询'" + queryString + "'");
		int start = (int)(Math.random() * 1000) + 1;
		LuceneQuery query = portal.createLuceneQuery(this.queryString, start, 10, null, null);	
		resultSet = portal.search(query);
	}
	
	public String getRunInfo() {
		return "单个查询，" + super.getRunInfo();
	}

	public SearchResultSet getResultSet() {
		return resultSet;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public void setResultSet(SearchResultSet resultSet) {
		this.resultSet = resultSet;
	}
}
