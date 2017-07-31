package com.wondersgroup.sh.search.test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.enums.SyncTypeEnum;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.sync.DBSynchro;
import com.wondersgroup.sh.search.sync.ParameterizedSynchro;
import com.wondersgroup.sh.search.sync.SyncInfo;

public class ContentAuditSynchro extends DBSynchro implements ParameterizedSynchro {
	private static final Logger logger = Logger.getLogger(ContentAuditSynchro.class);

	private String selectSql;
	private String updateSql;
	
	public void clearAfterSync(List ids) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(updateSql);
			for (Iterator iterator = ids.iterator(); iterator.hasNext();) {
				String docId = (String) iterator.next();
				pstmt.setString(0, docId);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		}
		catch( SQLException ex ) {
			throw new SearchException("执行语句错误:" + updateSql, ex);
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

	public SyncInfo[] getSyncs(IndexInfo indexInfo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List syncInfos = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(selectSql);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Object docId = rs.getObject("doc_id");
				syncInfos.add(new SyncInfo(docId, docId, SyncTypeEnum.UPDATE));
			}
			return (SyncInfo[])syncInfos.toArray(new SyncInfo[syncInfos.size()]);
		}
		catch( SQLException ex ) {
			throw new SearchException("查询错误。查询语句：" + selectSql, ex);
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

	public void setParameterValues(Map parameters) {
		if( parameters.containsKey("selectSql") ) {
			this.selectSql = (String)parameters.get("selectSql");
		}
		if( parameters.containsKey("updateSql") ) {
			this.updateSql = (String)parameters.get("updateSql");
		}
	}
}
