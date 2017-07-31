/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import com.wondersgroup.sh.search.IndexException;
import com.wondersgroup.sh.search.bridge.BridgeFactory;
import com.wondersgroup.sh.search.bridge.FieldBridge;
import com.wondersgroup.sh.search.bridge.builtin.BlobBridge;
import com.wondersgroup.sh.search.enums.SyncTypeEnum;
import com.wondersgroup.sh.search.lucene.config.DBFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.DataSourceInfo;
import com.wondersgroup.sh.search.lucene.config.EntityBridgeInfo;
import com.wondersgroup.sh.search.lucene.config.FieldInfo;
import com.wondersgroup.sh.search.lucene.config.FieldMapper;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.lucene.config.SynchroInfo;
import com.wondersgroup.sh.search.lucene.config.TableMapper;
import com.wondersgroup.sh.search.sync.DBSynchro;
import com.wondersgroup.sh.search.sync.ParameterizedSynchro;
import com.wondersgroup.sh.search.sync.SyncInfo;
import com.wondersgroup.sh.search.sync.Synchro;
import com.wondersgroup.sh.search.util.DBUtils;
import com.wondersgroup.sh.search.util.ReflectHelper;

public class DBFetcher implements Fetcher {
	private static final Logger logger = Logger.getLogger(DBFetcher.class);
	
	private DBFetcherInfo fetcherInfo;
	private IndexInfo indexInfo;
	private DataSourceInfo dsInfo;
	
	/**
	 * Instantiates a new dB fetcher.
	 * 
	 * @param configuration the configuration
	 * @param fetcherId the fetcher id
	 */
	public DBFetcher(LuceneConfiguration configuration, String fetcherId) {
		this.fetcherInfo = (DBFetcherInfo)configuration.getFetcher(fetcherId);
		this.indexInfo = configuration.getIndex(this.fetcherInfo.getIndexId());
		this.dsInfo = configuration.getDataSource(this.fetcherInfo.getDatasourceId());
	}

	public void incrementWriteIndex(IndexWriter writer) {
		SynchroInfo synchroInfo = fetcherInfo.getSynchro();
		if( synchroInfo == null ) {
			logger.warn("No synchronizer defined for fetcher " + fetcherInfo.getId());
			return;
		}

		Connection conn = null;
		try {
			conn = DBUtils.createConnection(this.dsInfo);
			Class synchroImpl = ReflectHelper.classForName(fetcherInfo.getSynchro().getImplClass());
			Synchro synchro = (Synchro)synchroImpl.newInstance();
			if( ParameterizedSynchro.class.isAssignableFrom(synchroImpl) && synchroInfo.getParams() != null) {
				((ParameterizedSynchro)synchro).setParameterValues(synchroInfo.getParams());
			}
			if( DBSynchro.class.isAssignableFrom(synchroImpl) ) { 
				((DBSynchro)synchro).setConnection(conn);
				((DBSynchro)synchro).setFetcherInfo(fetcherInfo);
			}
			
			SyncInfo[] syncInfos = synchro.getSyncs(this.indexInfo);
			if( syncInfos == null || syncInfos.length == 0 )
				return;
			
			FieldInfo idField = indexInfo.getDocument().getDocumentId();
			for (int i = 0; i < syncInfos.length; i++) {
				SyncInfo syncInfo = syncInfos[i];
				List syncIds = new ArrayList();
				syncIds.add(syncInfo.getSyncId());

				if( SyncTypeEnum.INSERT.equals(syncInfo.getType()) || SyncTypeEnum.UPDATE.equals(syncInfo.getType()) ) {
					this.writeIndex(conn, writer, syncIds);
				}
				else if( SyncTypeEnum.DELETE.equals(syncInfo.getType()) ) {
					Object syncId = syncInfo.getSyncId();
					String idStr = LuceneUtils.objectToString(idField, syncId);
					//logger.info("删除索引文档，" + idField.getName() + "=" + idStr);
					Term term = new Term(idField.getName(), idStr); 
					writer.deleteDocuments(term);
				}
				else {
					throw new LuceneIndexException("未知的同步类型：" + syncInfo.getType().getName());
				}
			}

			// clear after sync
			synchro.clearAfterSync(SyncInfo.getIds(Arrays.asList(syncInfos)));
			logger.info("共同步" + syncInfos.length + "条记录。");			
		}
		catch( Exception ex ) {
			throw new IndexException("生成索引出错。fetcher:" + fetcherInfo.getName(), ex);
		}
		finally {
			try {
				if( conn != null ) {
					conn.close();
				}
			} 
			catch (SQLException ex) {
				logger.warn("关闭数据库资源出错。", ex);
			}
		}
	}
	
	/**
	 * Write index.
	 * 
	 * @param conn the conn
	 * @param writer the writer
	 * @param ids the ids
	 * @param create the create
	 */
	private void writeIndex(Connection conn, IndexWriter writer, List ids) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			TableMapper tableMapper = this.fetcherInfo.getTableMapper();
			String documentIdName = indexInfo.getDocument().getDocumentId().getName();
			String sql = tableMapper.constructIncrementSQL(documentIdName);
			pstmt = conn.prepareStatement(sql);
			for (Iterator iter = ids.iterator(); iter.hasNext();) {
				Object id = iter.next();
				pstmt.setObject(1, id);
				rs = pstmt.executeQuery();
				ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
				Iterator rows = rsdc.iterator();
				while (rows.hasNext()) {
					DynaBean row = (DynaBean) rows.next();
					Document doc = this.newDocument(row, rsdc);
					String idStr = doc.get(documentIdName);
					Term term = new Term(documentIdName, idStr);
					//logger.debug("新增或更新索引文档，" + documentIdName + "=" + idStr);
					writer.updateDocument(term, doc);
				}
				
				rs.close();
				pstmt.clearParameters();
			}
		} 
		catch(Exception ex) {
			throw new IndexException("生成索引出错。fetcher:" + fetcherInfo.getName(), ex);
		}
		finally {
			try {
				if( rs != null ) 
					rs.close();
				if( pstmt != null )
					pstmt.close();
			}
			catch(SQLException ex) {
				logger.warn("关闭数据库资源出错。", ex);
			}			
		}
	}
	
	public int fullWriteIndex(IndexWriter writer) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = null;
		String idStr = null;
		
		try {
			String documentIdName = indexInfo.getDocument().getDocumentId().getName();
			conn = DBUtils.createConnection(this.dsInfo);
			TableMapper tableMapper = this.fetcherInfo.getTableMapper();
			sql = tableMapper.constructSQL();
			logger.info("数据库查询语句：" + sql);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetDynaClass rsdc = new ResultSetDynaClass(rs);
			Iterator rows = rsdc.iterator();
			int count = 0;
			while (rows.hasNext()) {
				DynaBean row = (DynaBean) rows.next();
				Document doc = this.newDocument(row, rsdc);
				idStr = doc.get(documentIdName);
				writer.addDocument(doc);
				count++;
				if( count % 10000000 == 0 ) 
					logger.info("创建了" + count + "条记录的索引。");
			}
			
			return count;
		}
		catch(Exception ex) {
			throw new LuceneIndexException("生成索引出错。id=" + idStr, ex);
		}
		finally {
			try {
				if( rs != null ) 
					rs.close();
				if( stmt != null )
					stmt.close();
				if( conn != null )
					conn.close();
			}
			catch(SQLException ex) {
				logger.warn("关闭数据库资源出错。", ex);
			}
		}
	}
	
	/**
	 * Gets the file type.
	 * 
	 * @param discriminator the discriminator
	 * @param row the row
	 * 
	 * @return the file type
	 */
	private String getFileType(String discriminator, DynaBean row) {
		String[] params = discriminator.split("[:]");
		String fileType = params[1];
		if( "column".equalsIgnoreCase(params[0]) ) {
			fileType = (String)row.get(params[1]);
		}					
		return fileType;
	}
	
	/**
	 * New document.
	 * 
	 * @param row the row
	 * @param rsdc the rsdc
	 * 
	 * @return the document
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	private Document newDocument(DynaBean row, ResultSetDynaClass rsdc) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Document doc = new Document();
		FieldInfo[] fields = this.indexInfo.getDocument().getFields();
		TableMapper tableMapper = this.fetcherInfo.getTableMapper();
		for (int i = 0; i < fields.length; i++) {
			FieldInfo fieldInfo = fields[i];
			FieldMapper fieldMapper = tableMapper.getFieldMapper(fieldInfo.getName());
			FieldBridge fieldBridge = null;
			Object value = null;
			if( fieldMapper == null ) {		// no field map defined, field will be blank string
				//logger.warn("No field mapper defined for field " + fieldInfo.getName() + ". The field value will be blank string.");
				fieldBridge = BridgeFactory.STRING;
			}
			else {
				if( StringUtils.isNotEmpty(fieldMapper.getValue()) ) {	// constant value
					fieldBridge = BridgeFactory.STRING;
					value = fieldMapper.getValue();
				}
				else {	
					String column = fieldMapper.getSourceName().toLowerCase();
					value = row.get(column);
					if( fieldMapper.getBridge() != null ) {		// a field bridge defined
						fieldBridge = BridgeFactory.getCustomFieldBridge(fieldMapper.getBridge());
					}
					else {	// no field bridge defined, use default bridge
						Class columnType = rsdc.getDynaProperty(column).getType();
						Map parameters = null;
						if( Blob.class.isAssignableFrom(columnType) ) {	// blob column 
							String fileType = this.getFileType(fieldMapper.getDiscriminator(), row);
							parameters = new HashMap();
							parameters.put(BlobBridge.PARAM_FILE_TYPE, fileType);
						}
						fieldBridge = BridgeFactory.getBridgeByClass(columnType, fieldInfo, parameters);
					}
				}
			}

			fieldBridge.set(fieldInfo.getName(), value, doc, fieldInfo);
		}
		
		List entityBridges = tableMapper.getEntityBridges();
		if( entityBridges != null && !entityBridges.isEmpty() ) {
			for (Iterator iter = entityBridges.iterator(); iter.hasNext();) {
				EntityBridgeInfo bridgeInfo = (EntityBridgeInfo) iter.next();
				FieldBridge bridge = BridgeFactory.getCustomFieldBridge(bridgeInfo);
				FieldInfo fieldInfo = this.indexInfo.getDocument().getField(bridgeInfo.getField());
				bridge.set(bridgeInfo.getField(), row, doc, fieldInfo);
			}
		}
		
		return doc;
	}
}
