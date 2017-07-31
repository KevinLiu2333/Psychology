/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;
import com.wondersgroup.sh.search.common.Const;
import com.wondersgroup.sh.search.enums.IndexEnum;
import com.wondersgroup.sh.search.enums.ResolutionEnum;
import com.wondersgroup.sh.search.enums.StoreEnum;
import com.wondersgroup.sh.search.enums.TermVectorEnum;
import com.wondersgroup.sh.search.enums.TypeEnum;
import com.wondersgroup.sh.search.util.FileUtils;

/**
 * The Class LuceneConfiguration.
 */
public class LuceneConfiguration {
	private static final Logger logger = Logger.getLogger(LuceneConfiguration.class);

	public static final String ROOT_WEB_APPLICATION_CONFIG_ATTRIBUTE = LuceneConfiguration.class + ".ROOT";;
	public static final String LUCENE_MATCH_VERSION_PARAM = "luceneMatchVersion";
	
	private String configFileName;
	private List<IndexInfo> indexes;
	private List<FetcherInfo> fetchers;
	private List<DataSourceInfo> datasources;
	private List<TaskInfo> tasks;
	private HierarchicalConfiguration configuration;
	
	public LuceneConfiguration(String configFileName) {
		this.configFileName = configFileName;
	}

	public synchronized void refresh() throws Exception {
		this.readConfiguration();
	}

	public synchronized void readConfiguration() {
		try {
			File file = new File(this.configFileName);
			if( !file.exists() ) { // in class path	
				this.configuration = new XMLConfiguration(this.configFileName);
			}
			else {
				this.configuration = new XMLConfiguration(file);
			}
			this.readIndexConfiguration();
			this.readDataSourceConfiguration();
			this.readFetcherConfiguration();
			this.readTaskConfiguration();
		}
		catch(Exception ex) {
			throw new WdisException(ErrorCode.READ_CONFIG_ERROR, "读取配置文件错误：" + this.configFileName, ex);
		}
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("configFileName", configFileName)
			.append("indexes", indexes)
			.append("fetchers", fetchers)
			.append("datasources", datasources)
			.toString();
	}
	
	private FieldInfo readIndexField(HierarchicalConfiguration subField) {
		String name = subField.getString("[@name]");
		String index = subField.getString("[@index]", "TOKENIZED");
		String store = subField.getString("[@store]", "NO");
		String termVector = subField.getString("[@termVector]", "NO");
		float boost = subField.getFloat("[@boost]", (float)1.0);
		boolean defaultField = subField.getBoolean("[@default]", false);
		boolean documentId = subField.getBoolean("[@id]", false);
		TypeEnum type = TypeEnum.getEnum(subField.getString("[@type]", TypeEnum.STRING.getName()));
		String resolution = null;
		if( TypeEnum.DATE.equals(type) ) {
			resolution = subField.getString("[@resolution]", ResolutionEnum.SECOND.getName()); 
		}
		String fieldAnalyzer = subField.getString("[@analyzer]");
		
		FieldInfo fieldInfo = new FieldInfo(name, defaultField, StoreEnum.getEnum(store), IndexEnum.getEnum(index), 
				TermVectorEnum.getEnum(termVector), boost);
		fieldInfo.setAnalyzerClass(fieldAnalyzer);
		fieldInfo.setResolution(resolution);
		fieldInfo.setType(type);
		fieldInfo.setDocumentId(documentId);
		return fieldInfo;
	}
	
	private DocumentInfo readIndexFields(HierarchicalConfiguration subIndex) {
		List fields = subIndex.configurationsAt("fields.field");
		List<FieldInfo> fieldInfos = new ArrayList<FieldInfo>();
		DocumentInfo documentInfo = new DocumentInfo();
		for (Iterator iterator = fields.iterator(); iterator.hasNext();) {
			HierarchicalConfiguration subField = (HierarchicalConfiguration) iterator.next();
			FieldInfo fieldInfo = this.readIndexField(subField);
			fieldInfos.add(fieldInfo);
			if( fieldInfo.isDocumentId() ) {
				documentInfo.setDocumentId(fieldInfo);
			}
		}
		documentInfo.setFields((FieldInfo[])fieldInfos.toArray(new FieldInfo[fieldInfos.size()]));
		return documentInfo;
	}
	
	private IndexInfo cloneIndexInfo(String indexId, String parentId, String name, boolean isDefault, String indexPath, boolean isAbstract) throws CloneNotSupportedException {
		IndexInfo parentIndex = this.getIndex(parentId);
		IndexInfo indexInfo = (IndexInfo)parentIndex.clone();  
		indexInfo.setId(indexId);
		indexInfo.setParentId(parentId);
		indexInfo.setDefaultIndex(isDefault);
		indexInfo.setName(name);
		indexInfo.setAbstractIndex(isAbstract);
		if( StringUtils.isNotBlank(indexPath) )
			indexInfo.setIndexPath(indexPath);
		else {
			String path = parentIndex.getIndexPath() + "\\" + indexId;  
			indexInfo.setIndexPath(path);
		}
		return indexInfo;
	}
	
	@SuppressWarnings("unchecked")
	protected List<IndexInfo> readIndexConfiguration() throws CloneNotSupportedException {
		this.indexes = new ArrayList<IndexInfo>();
		List<HierarchicalConfiguration> indexNodes = this.configuration.configurationsAt("index");
		for(HierarchicalConfiguration subIndex : indexNodes) {
			String indexId = subIndex.getString("[@id]");
			String indexName = subIndex.getString("[@name]", indexId);
			boolean isDefault = subIndex.getBoolean("[@default]", false);
			String indexPath = subIndex.getString("path");
			String parentId = subIndex.getString("[@parent]");
			boolean isAbstract = subIndex.getBoolean("[@abstract]", false);
			IndexInfo indexInfo = null;
			if( StringUtils.isNotBlank(parentId) ) {
				indexInfo = this.cloneIndexInfo(indexId, parentId, indexName, isDefault, indexPath, isAbstract);
			}
			else {
				indexInfo = new IndexInfo(indexId, indexName, indexPath); 
				indexInfo.setDefaultIndex(isDefault);
				indexInfo.setAbstractIndex(isAbstract);
				
				SubnodeConfiguration analyzerNode = subIndex.configurationAt("analyzer");
				String analyzerClass = analyzerNode.getString("[@impl]", IndexInfo.DefaultAnalyzerClass);
				indexInfo.setAnalyzerClass(analyzerClass);
				
				Configuration queryParserNode = subIndex.subset("QueryParser");
				if( queryParserNode != null ) {
					String defaultOperator = queryParserNode.getString("[@defaultOperator]", IndexInfo.DefaultOperator_AND);
					indexInfo.setDefaultOperator(defaultOperator);
				}
				
				indexInfo.setDocument(this.readIndexFields(subIndex));
			}
			this.indexes.add(indexInfo);
		}
		
		return this.indexes;
	}
	
	private SynchroInfo readSynchroConfiguation(HierarchicalConfiguration parentNode) {
		List synchroNodes = parentNode.configurationsAt("synchro");
		if( synchroNodes == null || synchroNodes.isEmpty() )
			return null;
		
		HierarchicalConfiguration synchroNode = (HierarchicalConfiguration)synchroNodes.get(0);
		String implClass = synchroNode.getString("[@impl]");
		Map paramMap = this.readParameterMap(synchroNode);
		return new SynchroInfo("", "", implClass, paramMap);		
	}
	
	private EraserInfo readEraserConfiguation(HierarchicalConfiguration parentNode) {
		List eraserNodes = parentNode.configurationsAt("eraser");
		if( eraserNodes == null || eraserNodes.isEmpty() ) 
			return null;
		
		HierarchicalConfiguration eraserNode = (HierarchicalConfiguration)eraserNodes.get(0);
		String implClass = eraserNode.getString("[@impl]");
		Map paramMap = this.readParameterMap(eraserNode);
		return new EraserInfo("", "", implClass, paramMap);		
	}
	
	private List readEntityBridges(HierarchicalConfiguration parentNode) {
		List nodes = parentNode.configurationsAt("bridges.bridge");
		if( nodes == null || nodes.isEmpty() )
			return new ArrayList();
		
		List bridges = new ArrayList();
		for (Iterator iter = nodes.iterator(); iter.hasNext();) {
			HierarchicalConfiguration subBridge = (HierarchicalConfiguration) iter.next();
			String field = subBridge.getString("[@field]");
			String implClass = subBridge.getString("[@impl]");
			Map paramMap = this.readParameterMap(subBridge);
			EntityBridgeInfo bridgeInfo = new EntityBridgeInfo(implClass, paramMap, field);
			bridges.add(bridgeInfo);
		}
		
		return bridges;
	}
	
	private Map readParameterMap(HierarchicalConfiguration parentNode) {
		List paramNodes = parentNode.configurationsAt("params.param");
		Map paramMap = new HashMap();
		for (Iterator iterator = paramNodes.iterator(); iterator.hasNext();) {
			HierarchicalConfiguration subParam = (HierarchicalConfiguration) iterator.next();
			paramMap.put(subParam.getString("[@name]"), subParam.getRoot().getValue());
		}
		return paramMap;
	}
	
	/**
	 * Read fetcher configuration.
	 * 
	 * @return the list
	 */
	protected List<FetcherInfo> readFetcherConfiguration() {
		this.fetchers = new ArrayList<FetcherInfo>();
		List fetcherNodes = this.configuration.configurationsAt("fetcher");
		for (Iterator iter = fetcherNodes.iterator(); iter.hasNext();) {
			HierarchicalConfiguration subFetcher = (HierarchicalConfiguration) iter.next();
			String id = subFetcher.getString("[@id]");
			String name = subFetcher.getString("[@name]");
			if( StringUtils.isBlank(name) )	name = id;
			String indexId = subFetcher.getString("index[@ref]");
			String type = subFetcher.getString("[@type]");
			
			SynchroInfo synchro = this.readSynchroConfiguation(subFetcher);
			EraserInfo eraser = this.readEraserConfiguation(subFetcher);
			TaskInfo task = this.readFetcherTaskConfiguration(subFetcher);
			
			FetcherInfo fetcherInfo = FetcherInfo.newInstance(id, name, type, indexId);
			fetcherInfo.setSynchro(synchro);
			fetcherInfo.setEraser(eraser);
			fetcherInfo.setTask(task);
			
			if( fetcherInfo instanceof DBFetcherInfo ) {
				DBFetcherInfo dbFetcherInfo = (DBFetcherInfo)fetcherInfo;
				
				// data source
				dbFetcherInfo.setDatasourceId(subFetcher.getString("datasource[@ref]"));
				
				// table mapper
				HierarchicalConfiguration subTable = subFetcher.configurationAt("table");
				TableMapper tableMapper = new TableMapper();
				tableMapper.setTableName(subTable.getString("[@name]"));
				tableMapper.setCondition(subTable.getString("condition"));
				tableMapper.setSelect(StringUtils.join(subTable.getStringArray("select"), ","));
				tableMapper.setOrder(subTable.getString("order"));
				
				// entity bridges
				List entityBridges= this.readEntityBridges(subTable);
				
				// field mapper
				List fieldMappers = this.readFieldMapper(subTable);
				tableMapper.setFieldMappers(fieldMappers);
				tableMapper.setEntityBridges(entityBridges);
				
				dbFetcherInfo.setTableMapper(tableMapper);
			}
			else if( fetcherInfo instanceof FileFetcherInfo ) {
				
				FileFetcherInfo fileFetcherInfo = (FileFetcherInfo)fetcherInfo;
				HierarchicalConfiguration pathNode = subFetcher.configurationAt("path");
				// data source
				fileFetcherInfo.setDatasourceId(subFetcher.getString("datasource[@ref]"));
				fileFetcherInfo.setFilePath((String)pathNode.getRoot().getValue());
			}
			
			this.fetchers.add(fetcherInfo);		
		}		

		return this.fetchers;
	}
	
	private TaskInfo readFetcherTaskConfiguration(HierarchicalConfiguration parentNode) {
		List taskNodes = parentNode.configurationsAt("task");
		if( taskNodes == null || taskNodes.isEmpty() )
			return null;
		
		HierarchicalConfiguration taskNode = (HierarchicalConfiguration)taskNodes.get(0);
		HierarchicalConfiguration classNode = taskNode.configurationAt("task-class");
		String implClass = (String)classNode.getRoot().getValue();
		Map<String, String> paramMap = this.readParameterMap(taskNode);
		return new TaskInfo("fetcher-task", "fetcher-task", implClass, paramMap);		
	}
	
	private List readFieldMapper(HierarchicalConfiguration parentNode) {
		List fieldMapperNodes = parentNode.configurationsAt("fields.field");
		if( fieldMapperNodes == null || fieldMapperNodes.isEmpty() )
			return new ArrayList();
		
		List fieldMappers = new ArrayList();
		for (Iterator iterator = fieldMapperNodes.iterator(); iterator.hasNext();) {
			HierarchicalConfiguration subMapper = (HierarchicalConfiguration) iterator.next();
			FieldMapper mapper = new FieldMapper();
			mapper.setFieldName(subMapper.getString("[@name]"));
			mapper.setSourceName(subMapper.getString("[@column]", mapper.getFieldName()));
			mapper.setDiscriminator(subMapper.getString("[@discriminator]"));
			mapper.setValue(subMapper.getString("[@value]"));

			FieldBridgeInfo fieldBridgeInfo = this.readFieldBridge(subMapper);
			mapper.setBridge(fieldBridgeInfo);
			fieldMappers.add(mapper);
		}
		
		return fieldMappers;
	}
	
	private FieldBridgeInfo readFieldBridge(HierarchicalConfiguration parentNode) {
		List bridgeNodes = parentNode.configurationsAt("bridge");
		if( bridgeNodes == null || bridgeNodes.isEmpty() ) 
			return null;
		
		HierarchicalConfiguration bridge = (HierarchicalConfiguration)bridgeNodes.get(0);
		String className = bridge.getString("[@impl]");
		Map parameters = this.readParameterMap(bridge);
		FieldBridgeInfo fieldBridgeInfo = new FieldBridgeInfo(className, parameters);
		return fieldBridgeInfo;
	}
	
	protected List<TaskInfo> readTaskConfiguration() {
		this.tasks = new ArrayList<TaskInfo>();
		List taskNodes = this.configuration.configurationsAt("task");
		for (Iterator iter = taskNodes.iterator(); iter.hasNext();) {
			HierarchicalConfiguration taskNode = (HierarchicalConfiguration) iter.next();
			TaskInfo task = this.readTaskConfiguation(taskNode);
			this.tasks.add(task);
		}
		
		return this.tasks;
	}
	
	private TaskInfo readTaskConfiguation(HierarchicalConfiguration taskNode) {
		String id = taskNode.getString("[@id]");
		HierarchicalConfiguration classNode = taskNode.configurationAt("task-class");
		String implClass = (String)classNode.getRoot().getValue();
		Map paramMap = this.readParameterMap(taskNode);
		return new TaskInfo(id, id, implClass, paramMap);		
	}
	
	protected List<DataSourceInfo> readDataSourceConfiguration() {
		this.datasources = new ArrayList<DataSourceInfo>();
		List dsNodes = this.configuration.configurationsAt("datasource");
		for (Iterator iter = dsNodes.iterator(); iter.hasNext();) {
			HierarchicalConfiguration subDs = (HierarchicalConfiguration) iter.next();
			DataSourceInfo ds = new DataSourceInfo();
			ds.setId(subDs.getString("[@id]"));
			
			List propertyNodes = subDs.configurationsAt("property");
			Properties properties = new Properties();
			for (Iterator iterator = propertyNodes.iterator(); iterator.hasNext();) {
				HierarchicalConfiguration subProperty = (HierarchicalConfiguration) iterator.next();
				properties.put(subProperty.getString("[@name]"), decrypt(subProperty.getRootNode().getValue().toString()));
			}
			ds.setProperties(properties);
			datasources.add(ds);
		}
		
		return this.datasources;
	}
	
	static String decrypt(String str) {
		if( str.startsWith("ENC(") && str.endsWith(")") ) {
			String value = str.substring("ENC(".length(), str.length() - 1);
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(Const.ENC_KEY);
			return encryptor.decrypt(value);
		}
		else
			return str;
	}
	
	static String encrypt(String str) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(Const.ENC_KEY);
		return encryptor.encrypt(str);
	}
	
	public FetcherInfo getFetcher(String fetcherId) {
		for (Iterator iter = this.fetchers.iterator(); iter.hasNext();) {
			FetcherInfo fetcher = (FetcherInfo) iter.next();
			if( fetcherId.equals(fetcher.getId()) )
				return fetcher;
		}
		return null;
	}
	
	public IndexInfo getIndex(String indexId) {
		for(IndexInfo index : this.indexes) {
			if( indexId.equals(index.getId()) ) 
				return index;
		}
		return null;
	}
	
	public IndexInfo[] getIndexes(List<String> indexIds) {
		List<IndexInfo> indexes = new ArrayList<IndexInfo>();
		for(String indexId : indexIds) {
			indexes.add(this.getIndex(indexId));
		}
		return indexes.toArray(new IndexInfo[indexes.size()]);
	}
	
	public IndexInfo[] getIndexesWithoutAbstract(List<String> indexIds) {
		List<IndexInfo> indexes = new ArrayList<IndexInfo>();
		for(String indexId : indexIds) {
			IndexInfo index = this.getIndex(indexId);
			if( !index.isAbstractIndex() ) {
				indexes.add(index);
			}
		}
		return indexes.toArray(new IndexInfo[indexes.size()]);
	}
	
	public List<IndexInfo> getIndexesNotAbstract() {
		List<IndexInfo> retList = new ArrayList<IndexInfo>();
		for(IndexInfo index : this.indexes) {
			if( !index.isAbstractIndex() )
				retList.add(index);
		}
		return retList;
	}
	
	public FetcherInfo[] getFetchersByIndex(String indexId) {
		List<FetcherInfo> retList = new ArrayList<FetcherInfo>();
		for(FetcherInfo fetcher : this.fetchers) {
			if( fetcher.getIndexId().equals(indexId) )
				retList.add(fetcher);
		}
		return retList.toArray(new FetcherInfo[retList.size()]);
	}
	
	public FetcherInfo[] getFetchers(String[] fetcherIds) {
		List<FetcherInfo> retList = new ArrayList<FetcherInfo>();
		for(String fetcherId : fetcherIds) {
			retList.add(this.getFetcher(fetcherId));
		}
		return retList.toArray(new FetcherInfo[retList.size()]);
	}
	
	public IndexInfo getDefaultIndex() {
		IndexInfo retIndex = null;
		for (Iterator iter = indexes.iterator(); iter.hasNext();) {
			IndexInfo index = (IndexInfo) iter.next();
			if( index.isDefaultIndex() ) {
				retIndex = index;
				break;
			}
		}
		
		return (retIndex == null) ? indexes.get(0) : retIndex;
	}
	
	public DataSourceInfo getDataSource(String id) {
		for (Iterator iter = this.datasources.iterator(); iter.hasNext();) {
			DataSourceInfo datasource = (DataSourceInfo) iter.next();
			if( StringUtils.isNotBlank(id)&&id.equals(datasource.getId()) )
				return datasource;
		}
		
		return null;
	}
	
	public void save() throws IOException, URISyntaxException {
		OutputStream os = null;
		try {
			File file = new File(this.configFileName);
			if( !file.exists() ) {	// in class path
				URL url = Thread.currentThread().getContextClassLoader().getResource(this.configFileName);
				file = new File(url.toURI());
			}
			FileUtils.rollOver(file.getAbsolutePath(), 10);
			os = new FileOutputStream(file);
			this.save(os);
		}
		finally {
			IOUtils.closeQuietly(os);
		}
	}
	
	void save(OutputStream os) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element rootEl = document.addElement("config")
			.addAttribute("xmlns", "http://www.wondersgroup.com")
			.addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
			.addAttribute("xsi:schemaLocation", "http://www.wondersgroup.com wdis.xsd");

		// index
		for(IndexInfo index : this.indexes) {
			index.toXml(rootEl);
		}
		
		// data source
		for(DataSourceInfo dataSource : this.datasources) {
			dataSource.toXml(rootEl);
		}
		
		// task
		for(TaskInfo task : this.tasks) {
			task.toXml(rootEl);
		}
		
		// fetcher
		for(FetcherInfo fetcher : this.fetchers) {
			fetcher.toXml(rootEl);
		}

		this.saveXmlFile(document, os);
	}
	
	private void saveXmlFile(Document document, OutputStream os) throws IOException {
		XMLWriter writer = null;
		try {
			OutputFormat outformat = OutputFormat.createPrettyPrint();
			outformat.setEncoding(Const.UTF8_ENC);
			writer = new XMLWriter(os, outformat);
			writer.write(document);
			writer.flush();
		}
		finally {
			if( writer != null) {
				try {
					writer.close();
				}
				catch(Exception ex) {
					logger.error(ex);
				}
			}
		}		
	}
	
	static Element paramToXml(Element parentEl, Map<String, String> parameters) {
		if( parameters == null || parameters.isEmpty() )
			return null;
		
		Element paramsEl = parentEl.addElement("params");
		Set<String> keys = parameters.keySet();
		for(String key : keys) {
			paramsEl.addElement("param")
				.addAttribute("name", key)
				.addText(parameters.get(key).toString());
		}
		return paramsEl;
	}	
	
	public void addDataSource(DataSourceInfo ds) {
		this.datasources.add(ds);
	}
	
	public void deleteDataSource(String id) {
		DataSourceInfo ds = this.getDataSource(id);
		if( ds != null ) {
			this.datasources.remove(ds);
		}
	}
	
	public void addFetcher(FetcherInfo fetcher) {
		this.fetchers.add(fetcher);
	}
	
	public void addIndex(IndexInfo index) {
		this.indexes.add(index);
	}
	
	public IndexInfo deleteIndex(String indexId) {
		IndexInfo index = this.getIndex(indexId);
		if( index != null ) {
			this.indexes.remove(index);
		}
		return index;
	}
	
	public FetcherInfo deleteFetcher(String fetcherId) {
		FetcherInfo fetcher = this.getFetcher(fetcherId);
		if( fetcher != null ) {
			this.fetchers.remove(fetcher);
		}
		return fetcher;
	}
	
	public List<String> getNotExistedIndex(List<String> indexIds) {
		List<String> allIds = IndexInfo.extractIds(this.getIndexes());
		return ListUtils.subtract(indexIds, allIds);
	}
	
	public String getConfigFileName() {
		return configFileName;
	}

	public HierarchicalConfiguration getConfiguration() {
		return configuration;
	}

	public List<DataSourceInfo> getDatasources() {
		return datasources;
	}

	public List<FetcherInfo> getFetchers() {
		return fetchers;
	}

	public List<IndexInfo> getIndexes() {
		return indexes;
	}

	public List<TaskInfo> getTasks() {
		return tasks;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
}
