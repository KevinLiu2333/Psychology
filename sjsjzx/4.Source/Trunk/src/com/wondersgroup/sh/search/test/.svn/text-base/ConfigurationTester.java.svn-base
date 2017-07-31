package com.wondersgroup.sh.search.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.wondersgroup.sh.search.ConfigurationHelper;
import com.wondersgroup.sh.search.SearchPortal;
import com.wondersgroup.sh.search.lucene.config.DBFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.FetcherParameter;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

public class ConfigurationTester {
	private static final Logger logger = Logger.getLogger(ConfigurationTester.class);
	private String configFileName = "huge.cfg.xml";
	private LuceneConfiguration configuration;
	private ConfigurationHelper configHelper = ConfigurationHelper.getInstance();
	
	@Before 
	public void init() {
		configuration = new LuceneConfiguration(configFileName);
		configuration.readConfiguration();
	}
	
	@Test
	public void testRefresh() throws Exception {
		this.configuration.refresh();
	}
	
	@Test
	public void testIndex() {
		List<String> notExistIndexIds = configuration.getNotExistedIndex(Arrays.asList(new String[]{"ajfieowaqjjfasowefass", "aaaa", "bbb"}));
		logger.info(notExistIndexIds);
	}
	
	@Test
	public void testSearchPortal() {
		String indexId = "yongjichuguoliuxuerenyuan";
		SearchPortal portal = null;
		if( StringUtils.isNotBlank(indexId) ) {
			String[] indexIds = indexId.split(",");
			portal = new SearchPortal(configuration, indexIds);
		}
	}
	
	@Test
	public void testConstructSql() {
		DBFetcherInfo fetcherInfo = (DBFetcherInfo)configuration.getFetcher("huge_fetcher_1");
		String sql = fetcherInfo.getTableMapper().constructSQL();
		logger.info(sql);
		sql = fetcherInfo.getTableMapper().constructIncrementSQL("ID");
		logger.info(sql);
	}
	
	@Test
	public void testSaveFetcher() throws Exception {
		String sql = "select a.*, NAME || CONTENT || DIVISION as mycontent " +
				" from HUGE_TBL a " +
				" whErE ID between :begin and :end";
		
		configHelper.setConfigFilename("splitHugeLocal.cfg.xml");
		this.createFetchers("hugeIndex", 0, 100, 1000, sql);
		//this.createFetchers("hugeIndex_2", 100, 100, 250000, sql);
		//this.createFetchers("hugeIndex", 200, 100, 300000, sql);
		configHelper.save();
	}
	
	void createFetchers(String parentIndex, int startIndex, int count, int indexSize, String sql) {
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("ID", "ID");
		fields.put("NAME", "NAME");
		fields.put("CONTENT", "mycontent");
		fields.put("DIVISION", "DIVISION");
		fields.put("BIRTH_DAY", "BIRTH_DAY");
		fields.put("GENDER", "GENDER");
		fields.put("FUNCTION_NAME", "FUNCTION_NAME");	
		
		int maxRow = 100000000;
		int pageCount = 0;
		for(int i = startIndex; i < startIndex + count; i++) {
			FetcherParameter param = new FetcherParameter();
			param.setDatasourceId("shnew");
			param.setFetcherId("fetcher_" + i);
			param.setHasSynchro(false);
			param.setIndexId("index_" + i);
			param.setParentIndexId(parentIndex);
			param.setName("fetcher_" + i);
			int startRow = pageCount * indexSize + 1;
			int endRow = (pageCount + 1) * indexSize;
			if( startRow >= maxRow || endRow >= maxRow ) {
				pageCount = 0;
				startRow = pageCount * indexSize + 1;
				endRow = (pageCount + 1) * indexSize;
			}
			else 
				pageCount++;
				
			String str = sql.replace(":begin", String.valueOf(startRow));
			str = str.replace(":end", String.valueOf(endRow));
			param.setSelect(str);
			param.setFields(fields);
			configHelper.saveFetcher(param);
		}
	}
}
