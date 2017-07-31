package com.wondersgroup.sh.search.test;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import com.wondersgroup.sh.search.SearchPortal;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.lucene.LuceneQuery;

public class BatchSearchTester extends SearchPortalTester {
	private static final Logger logger = Logger.getLogger(BatchSearchTester.class);

	private String[] functionNames;
	private List<SearchResultSet> resultSets = new ArrayList<SearchResultSet>();
	
	public BatchSearchTester(SearchPortal portal, String queryString) {
		super(portal, queryString);
	}
	
	protected void doSearch() {
		try {
			for(int i = 0; i < functionNames.length; i++) {
				logger.debug("查询，" + queryString);
				LuceneQuery query = portal.createLuceneQuery(queryString, 1, 10, null, null);	
				SearchResultSet resultSet = portal.search(query);
				this.resultSets.add(resultSet);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String getRunInfo() {
		return "查询，" + super.getRunInfo();
	}

	public List<SearchResultSet> getResultSets() {
		return resultSets;
	}

	public String[] getFunctionNames() {
		return functionNames;
	}

	public void setFunctionNames(String[] functionNames) {
		this.functionNames = functionNames;
	}
}
