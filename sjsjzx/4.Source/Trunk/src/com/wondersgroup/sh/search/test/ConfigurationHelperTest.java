package com.wondersgroup.sh.search.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.wondersgroup.sh.search.ConfigurationHelper;
import com.wondersgroup.sh.search.lucene.config.FetcherParameter;
import com.wondersgroup.sh.search.lucene.config.TaskInfo;

public class ConfigurationHelperTest {
	private static final Logger logger = Logger.getLogger(ConfigurationHelperTest.class);

	private ConfigurationHelper helper;
	
	@Test
	public void testSave() throws Exception {
		this.helper = ConfigurationHelper.getInstance();
		this.helper.setConfigFilename("D:/search-core/dev/src/splitHugeLocal.cfg.xml");
		this.helper.save();
	}
	
	@Test		
	//@Ignore
	public void testSaveFetcher() throws Exception {
		this.helper = ConfigurationHelper.getInstance();
		this.helper.setConfigFilename("huge.cfg.xml");
		FetcherParameter param = new FetcherParameter();
		param.setIndexId("hugeIndex");
		param.setDatasourceId("shnews");
		param.setFetcherId("abcdefg");
		param.setTableName("huge_tbl");
		param.setSelect("select * from huge_tbl");
		param.setHasSynchro(false);
		param.setSyncHours("48");
		param.setTimestampField("LAST_MODIFY_TIME");
		Map<String, String> fields = new HashMap<String, String>();
		fields.put("ID", "");
		fields.put("aaa", "123");
		fields.put("bbbb", null);
		param.setFields(fields);
		this.helper.saveFetcher(param);
		this.helper.save();
	}

	@Test
	@Ignore
	public void testDeleteFetcher() throws Exception {
		this.helper = ConfigurationHelper.getInstance();
		this.helper.setConfigFilename("splitHuge.cfg.xml");
		this.helper.deleteFetcher("huge_fetcher_17");
		this.helper.deleteIndex("fwjddssdf$12345");
		this.helper.save();
	}
	
	@Test
	@Ignore
	public void testDeleteDataSource() throws Exception {
		this.helper = ConfigurationHelper.getInstance();
		this.helper.setConfigFilename("huge.cfg.xml");
		this.helper.deleteDataSource("shnew");
		this.helper.save();
	}
	
	@Test
	public void testDeleteIndexNFile() throws Exception {
		String configFilename = null; 
		configFilename = "D:/search-core/dev/src/splitHugeLocal.cfg.xml";
		String indexId = "index_0";
		this.helper = ConfigurationHelper.getInstance();
		this.helper.setConfigFilename(configFilename);
		this.helper.deleteIndexNFile(indexId);
		//this.helper.getDataSources();
	}
	
	@Test
	public void testTask() throws Exception {
		this.helper = ConfigurationHelper.getInstance();
		this.helper.setConfigFilename("D:/search-core/dev/src/splitHugeLocal.cfg.xml");
		List<TaskInfo> tasks = this.helper.getTasks();
		logger.info(tasks);
	}
}
