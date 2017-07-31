package com.wondersgroup.sh.search.client.test;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;
import com.wondersgroup.sh.search.client.SearchClient;

public class SingleSearchRunner extends AbstractSearchRunner {
	private static final Logger logger = Logger.getLogger(SingleSearchRunner.class);

	private SearchResultSet resultSet;
	private String indexId;
	
	public SingleSearchRunner(String queryString, String indexId) {
		super(queryString);
		this.indexId = indexId;
	}
	
	protected void doSearch() {
		logger.info("在索引'" + indexId + "'中查询'" + queryString + "'");
		try {
			SearchClient client = new SearchClient();
			this.resultSet = client.search(this.queryString, 1, 10, indexId);
		}
		catch(Exception ex) {
			throw new WdisException(ErrorCode.UNKNOWN, ex);
		}
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
