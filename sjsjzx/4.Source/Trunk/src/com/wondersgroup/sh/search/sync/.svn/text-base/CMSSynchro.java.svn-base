/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.sync;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.enums.SyncTypeEnum;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;

/**
 * The Class CMSSynchro.
 */
public class CMSSynchro extends DBSynchro implements ParameterizedSynchro {
	
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(CMSSynchro.class);
	
	/** The Constant PARAM_SYNC_TABLE. */
	public static final String PARAM_SYNC_TABLE = "syncTable";
	
	/** The Constant PARAM_CONDITION. */
	public static final String PARAM_CONDITION = "condition";
	
	/** The condition. */
	private String condition;
	
	/** The sync table. */
	private String syncTable = "WDIS_CMS_SYN";
	
	private List getAttachmentIds(String articleId) {
		String sql = "select b.st_sid as attach_id from wegov_cms_article t inner join WEGOV_CMS_RESOURCE r on t.st_sid = r.st_parent_resource_id " +
				"	inner join WEGOV_CMS_BINARY_OBJECT b on r.st_sid = b.st_sid where t.st_sid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List attachmentIds = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, articleId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				attachmentIds.add("ATTACHMENT_" + rs.getString(1));
			}
		}
		catch( SQLException ex ) {
			throw new SearchException("查询同步表出错。查询语句：" + sql, ex);
		}		
		finally {
			try {
				if( rs != null )
					rs.close();
				if( pstmt != null )
					pstmt.close();
			} 
			catch (SQLException e) {
				logger.warn("关闭数据库资源出错。", e);
			}			
		}
		
		return attachmentIds;
	}
	
	private void insertSync(String syncId, String action, String catalog) {
		String sql = "INSERT INTO WDIS_CMS_SYN (id, sync_id, type, catalog, update_time) " +
				" VALUES (WDIS_CMS_SYN_SEQ.NEXTVAL, ?, ?, ?, sysdate)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, syncId);
			pstmt.setString(2, action);
			pstmt.setString(3, catalog);
			pstmt.executeUpdate();
		}
		catch( SQLException ex ) {
			throw new SearchException("插入同步表出错。删除语句：" + sql, ex);
		}
		finally {
			try {
				if( pstmt != null )
					pstmt.close();
			} 
			catch (SQLException e) {
				logger.warn("关闭数据库资源出错。", e);
			}
		}					
	}
	
	private List getChapterIds(String articleId) {
		String sql = "select c.st_sid as chapter_id " +
				"	from wegov_cms_article t inner join WEGOV_CMS_CHAPTER c on t.st_sid= c.st_article_sid where t.st_sid = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List chapterIds = new ArrayList();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, articleId);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				chapterIds.add("CHAPTER_" + rs.getString(1));
			}
		}
		catch( SQLException ex ) {
			throw new SearchException("查询同步表出错。查询语句：" + sql, ex);
		}		
		finally {
			try {
				if( rs != null )
					rs.close();
				if( pstmt != null )
					pstmt.close();
			} 
			catch (SQLException e) {
				logger.warn("关闭数据库资源出错。", e);
			}			
		}
		
		return chapterIds;
	}
	
	public SyncInfo[] getSyncs(IndexInfo indexInfo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List syncInfos = new ArrayList();
		String selectSql = "select * from " + syncTable;
		if( StringUtils.isNotBlank(condition) ) {
			selectSql += " where " + condition;
		}
		selectSql += " order by id";
		
		try {
			pstmt = conn.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Integer id = new Integer(rs.getInt("id"));
				String syncId = rs.getString("sync_id");
				String type = rs.getString("type");
				String catalog = rs.getString("catalog");
				SyncTypeEnum typeEnum = SyncTypeEnum.getEnum(type);
				
				// need refresh attachment and chapter linked to the article which deleted or updated result from change of publish status .
				if( "ARTICLE".equalsIgnoreCase(catalog) ) {
					SyncTypeEnum childTypeEnum = SyncTypeEnum.DELETE.equals(typeEnum) ? SyncTypeEnum.DELETE : SyncTypeEnum.UPDATE;
					String articleId = StringUtils.removeStart(syncId, "ARTICLE_");
					List attachIds = this.getAttachmentIds(articleId);
					for (Iterator iter = attachIds.iterator(); iter.hasNext();) {
						String attachId = (String) iter.next();
						this.insertSync(attachId, childTypeEnum.getName(), "ATTACHMENT");
					}
					
					List chapterIds = this.getChapterIds(articleId);
					for (Iterator iter = chapterIds.iterator(); iter.hasNext();) {
						String chapterId = (String) iter.next();
						this.insertSync(chapterId, childTypeEnum.getName(), "CHAPTER");
					}
				}
				
				syncInfos.add(new SyncInfo(id, syncId, typeEnum));
			}
			return (SyncInfo[])syncInfos.toArray(new SyncInfo[syncInfos.size()]);
		}
		catch( SQLException ex ) {
			throw new SearchException("查询同步表出错。查询语句：" + selectSql, ex);
		}
		finally {
			try {
				if( rs != null )
					rs.close();
				if( pstmt != null )
					pstmt.close();
			} 
			catch (SQLException e) {
				logger.warn("关闭数据库资源出错。", e);
			}
		}
	}

	public void clearAfterSync(List ids) {
		String deleteSql = "delete from " + syncTable + " where id = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(deleteSql);
			for (Iterator iter = ids.iterator(); iter.hasNext();) {
				Object id = (Object) iter.next();
				pstmt.setObject(1, id);
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}
		}
		catch( SQLException ex ) {
			throw new SearchException("删除同步表出错。删除语句：" + deleteSql, ex);
		}
		finally {
			try {
				if( pstmt != null )
					pstmt.close();
			} 
			catch (SQLException e) {
				logger.warn("关闭数据库资源出错。", e);
			}
		}			
	}

	public void setParameterValues(Map parameters) {
		if ( parameters.containsKey( PARAM_SYNC_TABLE ) ) {
			syncTable = (String)parameters.get( PARAM_SYNC_TABLE );
		}
		if ( parameters.containsKey( PARAM_CONDITION ) ) {
			condition = (String)parameters.get( PARAM_CONDITION );
		}		
	}
}
