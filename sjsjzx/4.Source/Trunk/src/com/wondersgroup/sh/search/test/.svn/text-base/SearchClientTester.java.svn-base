package com.wondersgroup.sh.search.test;

import org.apache.log4j.Logger;

import org.junit.Test;

import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.client.SearchClient;

public class SearchClientTester {
	private static final Logger logger = Logger.getLogger(SearchClientTester.class);

	@Test
	public void testSearch() throws Exception {
		SearchClient client = new SearchClient(null);
		SearchResultSet srs = client.search("上海", 1, 10, "index_0");
		logger.info(srs);
	}
}
