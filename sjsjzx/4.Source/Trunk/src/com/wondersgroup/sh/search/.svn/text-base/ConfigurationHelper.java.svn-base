package com.wondersgroup.sh.search;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.sh.search.erase.DefaultLuceneEraser;
import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.config.DBFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.DataSourceInfo;
import com.wondersgroup.sh.search.lucene.config.EraserInfo;
import com.wondersgroup.sh.search.lucene.config.FetcherInfo;
import com.wondersgroup.sh.search.lucene.config.FetcherParameter;
import com.wondersgroup.sh.search.lucene.config.FieldMapper;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.lucene.config.SynchroInfo;
import com.wondersgroup.sh.search.lucene.config.TableMapper;
import com.wondersgroup.sh.search.lucene.config.TaskInfo;
import com.wondersgroup.sh.search.sync.TimestampDBSynchro;

public class ConfigurationHelper {
	private LuceneConfiguration configuration;
	private static ConfigurationHelper instance = new ConfigurationHelper();
	
	private ConfigurationHelper() {
	}
	
	public static ConfigurationHelper getInstance() {
		return instance;
	}
	
	public FetcherInfo getFetcher(String fetcherId) {
		return this.configuration.getFetcher(fetcherId);
	}
	
	public IndexInfo getIndex(String indexId) {
		return this.configuration.getIndex(indexId);
	}
	
	public FetcherInfo[] getFetchersByIndex(String indexId) {
		return this.configuration.getFetchersByIndex(indexId);
	}
	
	public FetcherInfo[] getFetchers(String[] fetcherIds) {
		return this.configuration.getFetchers(fetcherIds);
	}
	
	public IndexInfo getDefaultIndex() {
		return this.configuration.getDefaultIndex();
	}
	
	public DataSourceInfo getDataSource(String id) {
		return this.configuration.getDataSource(id);
	}
	
	public List<DataSourceInfo> getDataSources() {
		return this.configuration.getDatasources();
	}
	
	public List<IndexInfo> getIndexes() {
		return this.configuration.getIndexes();
	}
	
	public List<FetcherInfo> getFetchers() {
		return this.configuration.getFetchers();
	}
	
	public DataSourceInfo saveDataSource(String datasourceId, String className, Properties properties) {
		DataSourceInfo ds = this.getDataSource(datasourceId);
		if( ds == null ) {
			ds = new DataSourceInfo(datasourceId, className, properties);
			this.configuration.getDatasources().add(ds);
		}
		else {
			ds.setClassName(className);
			ds.setProperties(properties);
		}
		return ds;
	}
	
	public void deleteDataSource(String datasourceId) {
		this.configuration.deleteDataSource(datasourceId);
	}
	
	public FetcherInfo saveFetcher(FetcherParameter param) {
		// First delete, then add
		this.deleteFetcher(param.getFetcherId());
		
		if( StringUtils.isNotBlank(param.getParentIndexId()) ) {
			IndexInfo index = this.getIndex(param.getIndexId());
			if( index == null ) {
				index = new IndexInfo(param.getIndexId(), param.getIndexId(), "");
				index.setParentId(param.getParentIndexId());
				this.configuration.addIndex(index);
			}
		}
		
		DBFetcherInfo fetcher = new DBFetcherInfo(param.getFetcherId(), param.getName(), param.getIndexId());
		fetcher.setDatasourceId(param.getDatasourceId());
			
		// table
		TableMapper tableMapper = new TableMapper(param.getTableName());
		tableMapper.setSelect(param.getSelect());
		tableMapper.setCondition(param.getCondition());
		List<FieldMapper> fieldMappers = new ArrayList<FieldMapper>();
		for(Entry<String, String> entry : param.getFields().entrySet()) {
			FieldMapper fieldMapper = new FieldMapper();
			fieldMapper.setFieldName(entry.getKey());
			fieldMapper.setValue(entry.getValue());
			fieldMappers.add(fieldMapper);
		}
		tableMapper.setFieldMappers(fieldMappers);
		fetcher.setTableMapper(tableMapper);

		// synchro
		if( param.isHasSynchro() ) {
			Map<String, String> synchroParams = new HashMap<String, String>();
			synchroParams.put("timestampField", param.getTimestampField());
			synchroParams.put("syncHours", param.getSyncHours());
			SynchroInfo synchro = new SynchroInfo("", "", TimestampDBSynchro.class.getName(), synchroParams);
			fetcher.setSynchro(synchro);
		}

		// eraser
		Map<String, String> eraserParams = new HashMap<String, String>();
		eraserParams.put("fields", "FUNCTION_NAME");
		eraserParams.put("values", param.getFetcherId());
		EraserInfo eraser = new EraserInfo("", "", DefaultLuceneEraser.class.getName(), eraserParams);
		fetcher.setEraser(eraser);
		
		// refresh index task
		if( param.isNeedRefresh() ) {
			Map<String, String> taskParams = new HashMap<String, String>();
			taskParams.put("timestampField", param.getUpdateTimeField());
			TaskInfo task = new TaskInfo("fetcher-task", "fetcher-task", "com.wondersgroup.sh.search.task.FullRefreshIndexWithTimestampTask", taskParams);
			fetcher.setTask(task);
		}
		
		this.configuration.addFetcher(fetcher);
		return fetcher;
	}
	
	public FetcherInfo deleteFetcher(String fetcherId) {
		return this.configuration.deleteFetcher(fetcherId);
	}
	
	public IndexInfo deleteIndex(String indexId) {
		return this.configuration.deleteIndex(indexId);
	}
	
	public IndexInfo deleteIndexNFile(String indexId) throws Exception {
		IndexInfo indexInfo = this.configuration.getIndex(indexId);
		DefaultLuceneIndexer.deleteIndex(indexInfo);
		DefaultLuceneIndexer.deleteDirectory(indexInfo);
		return indexInfo;
	}
	
	public List<TaskInfo> getTasks() {
		return this.configuration.getTasks();
	}
	
	public synchronized void save() throws IOException, URISyntaxException {
		this.configuration.save();
	}

	private synchronized void load(String configFilename) throws Exception {
		this.configuration = new LuceneConfiguration(configFilename);
		this.configuration.readConfiguration();
	}
	
	public synchronized void setConfigFilename(String configFilename) throws Exception {
		this.load(configFilename);
	}
	
	public void setConfiguration(LuceneConfiguration configuration) {
		this.configuration = configuration;
	}
}
