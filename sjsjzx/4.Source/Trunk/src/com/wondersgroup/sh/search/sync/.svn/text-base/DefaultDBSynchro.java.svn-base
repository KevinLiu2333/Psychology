/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.sync;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.enums.SyncTypeEnum;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The Class DefaultDBSynchro.
 */
public class DefaultDBSynchro extends DBSynchro implements ParameterizedSynchro {
	private static final Logger logger = Logger.getLogger(DefaultDBSynchro.class);
	public static String PARAM_SYNC_TABLE = "syncTable";
	public static String PARAM_CONDITION = "condition";
	public static final String DefaultSynchroTable = "wdis_search_syn";	

	private String syncTable = DefaultSynchroTable;
	private String condition;
	
	public DefaultDBSynchro() {
	}
	
	public void setParameterValues(Map parameters) {
		if ( parameters.containsKey( PARAM_SYNC_TABLE ) ) {
			syncTable = (String)parameters.get( PARAM_SYNC_TABLE );
		}
		if ( parameters.containsKey( PARAM_CONDITION ) ) {
			condition = (String)parameters.get( PARAM_CONDITION );
		}
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
				Object id = rs.getObject("id");
				Object syncId = rs.getObject("sync_id");
				String type = rs.getString("type");
				syncInfos.add(new SyncInfo(id, syncId, SyncTypeEnum.getEnum(type)));
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
}
