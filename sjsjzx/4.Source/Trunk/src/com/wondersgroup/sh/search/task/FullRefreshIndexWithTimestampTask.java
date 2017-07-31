package com.wondersgroup.sh.search.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import com.wondersgroup.sh.search.common.Const;
import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.LuceneIndexer;
import com.wondersgroup.sh.search.lucene.config.DBFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.DataSourceInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.DBUtils;

public class FullRefreshIndexWithTimestampTask implements Task, ParameterizedTask {
	private static String TIMESTAMP_FIELD = "timestampField";

	private String timestampField;
	private LuceneConfiguration configuration;
	
	public void execute(Map<String, Object> taskContext) throws Exception {
		this.configuration = (LuceneConfiguration)taskContext.get(TaskEngine.PARAM_Configuration);
		DBFetcherInfo fetcherInfo = (DBFetcherInfo)taskContext.get(TaskEngine.PARAM_Fetcher);
		if( this.needRefresh((DBFetcherInfo)fetcherInfo) ) {
			LuceneIndexer indexer = new DefaultLuceneIndexer(configuration, fetcherInfo.getIndexId());
			indexer.fullIndex(new String[]{fetcherInfo.getId()});
		}
	}

	private String constructCountSql(DBFetcherInfo fetcherInfo) {
		Calendar now = Calendar.getInstance();
		Calendar lastDay = Calendar.getInstance();
		lastDay.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATETIME_FORMAT);
		
		String sql = fetcherInfo.getTableMapper().constructSQL();
		sql = sql.substring(sql.indexOf("from"));
		return "select count(1) " + sql + " and " + this.timestampField + " >= to_date('" + sdf.format(lastDay.getTime()) + "', 'yyyymmdd hh24:mi:ss')" +
			"	and " + this.timestampField + " <= to_date('" + sdf.format(now.getTime()) + "', 'yyyymmdd hh24:mi:ss')";
	}
	
	private boolean needRefresh(DBFetcherInfo fetcherInfo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String countSql = this.constructCountSql(fetcherInfo);
			DataSourceInfo dsInfo = configuration.getDataSource(fetcherInfo.getDatasourceId());
			conn = DBUtils.createConnection(dsInfo);
			pstmt = conn.prepareStatement(countSql);
			rs = pstmt.executeQuery();
			long count = 0;
			if( rs.next() ) {
				count = rs.getLong(1);
			}
			return count > 0;
		}
		finally {
			DBUtils.closeQuietly(rs);
			DBUtils.closeQuietly(pstmt);
			DBUtils.closeQuietly(conn);
		}
	}
	
	public void setParameterValues(Map<String, String> parameters) {
		if( parameters.containsKey(TIMESTAMP_FIELD) ) {
			this.timestampField = parameters.get(TIMESTAMP_FIELD);
		}
	}

	public String getTimestampField() {
		return timestampField;
	}
}
