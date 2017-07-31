package com.wondersgroup.sh.search.sync;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.enums.SyncTypeEnum;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.TableMapper;

public class TimestampDBSynchro extends DBSynchro implements ParameterizedSynchro {
	private static final Logger logger = Logger.getLogger(TimestampDBSynchro.class);
	private static final int SYNC_HOURS = 36;
	
	private String timestampField;
	private int syncHours = SYNC_HOURS;
	
	public void clearAfterSync(List ids) {
		// do nothing
	}

	public SyncInfo[] getSyncs(IndexInfo indexInfo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List syncInfos = new ArrayList();
		
		TableMapper tableMapper = super.fetcherInfo.getTableMapper();
		String selectSql = tableMapper.constructSQL() + " and " + timestampField + " >= ?";
		String documentIdName = indexInfo.getDocument().getDocumentId().getName();
		String idColumnName = tableMapper.getFieldMapper(documentIdName).getSourceName();
		try {
			pstmt = conn.prepareStatement(selectSql);
			Calendar lastTime = Calendar.getInstance();
			lastTime.add(Calendar.HOUR_OF_DAY, -this.syncHours);
			pstmt.setDate(1, new Date(lastTime.getTime().getTime()));
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				Object syncId = rs.getObject(idColumnName);
				SyncInfo syncInfo = new SyncInfo(syncId, syncId, SyncTypeEnum.UPDATE);
				syncInfos.add(syncInfo);
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

	public void setParameterValues(Map parameters) {
		if ( parameters.containsKey( "timestampField" ) ) {
			timestampField = (String)parameters.get( "timestampField" );
		}
		if ( parameters.containsKey( "syncHours" ) ) {
			String str = (String)parameters.get( "syncHours" );
			try {
				this.syncHours = new Integer(str).intValue();
			}
			catch(Exception ex) {
				logger.error("参数syncHours错误：必须是正整数。", ex);
			}
		}
	}
}
