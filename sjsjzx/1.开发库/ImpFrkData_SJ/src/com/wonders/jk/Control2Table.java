package com.wonders.jk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.wonders.db.ConnectionPool;

/**
 * 对增量数据的监控信息操作.
 */
public class Control2Table {
	private Connection				conn		= ConnectionPool.getInstance().getConnection();

	private static Control2Table	instance	= null;

	private static Control2Table getInstance() {
		if (instance == null) {
			instance = new Control2Table();
		}
		return instance;
	}

	public static void execute(String dbName, String ex_type, int suCount, int faCount, String suIds, String faIds) {
		List<String> sqls = getControlSql(dbName, ex_type, suCount, faCount, suIds, faIds);
		try {
			if ("update".equals(ex_type)) {
				if (suCount != 0) {
					getInstance().executeSql(sqls.get(0));
				}
				if (faCount != 0) {
					getInstance().executeSql(sqls.get(1));
				}
			}
			if ("insert".equals(ex_type)) {
				if (suCount != 0) {
					getInstance().executeSql(sqls.get(0));
				}
				if (faCount != 0) {
					getInstance().executeSql(sqls.get(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 执行交换监控类型为增加的语句.
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean executeSql(String sql) {
		boolean flag = false;
		PreparedStatement pstmt = null;
		try {
			pstmt = this.conn.prepareStatement(sql);
			if (pstmt.executeUpdate() > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			try {
				this.close(conn, pstmt);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public void close(Connection conn, PreparedStatement pstmt) throws SQLException {
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * 构造监控sql语句.
	 * 
	 * @param tableName
	 *            表名
	 * @param type
	 *            交换类型
	 * @param result
	 *            交换结果
	 * @param suCount
	 *            交换成功数量
	 * @param faCount
	 *            交换失败数量
	 * @return
	 */
	private static List<String> getControlSql(String tableName, String type, int suCount, int faCount, String suIds, String faIds) {
		List<String> sqls = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		if ("update".equals(type)) {
			// 交换成功的sql
			String suSql = "insert into T_EX_CONTROL(ID,TABLE_NAME,EX_DATE,EX_TYPE,EX_COUNT,EX_RESULT,IDS_VALUE) " + "values('" + getUUID() + "','" + tableName + "','" + dateStr
					+ "','" + type + "'," + suCount + ",'success','" + suIds + "')";
			// 交换失败的sql
			String faSql = "insert into T_EX_CONTROL(ID,TABLE_NAME,EX_DATE,EX_TYPE,EX_COUNT,EX_RESULT,IDS_VALUE) " + "values('" + getUUID() + "','" + tableName + "','" + dateStr
					+ "','" + type + "'," + faCount + ",'success','" + faIds + "')";

			sqls.add(suSql);
			sqls.add(faSql);
		}
		if ("insert".equals(type)) {
			String suSql = "insert into T_EX_CONTROL(ID,TABLE_NAME,EX_DATE,EX_TYPE,EX_COUNT,EX_RESULT,IDS_VALUE) " + "values('" + getUUID() + "','" + tableName + "','" + dateStr
					+ "','" + type + "'," + suCount + ",'success','" + suIds + "')";
			String faSql = "insert into T_EX_CONTROL(ID,TABLE_NAME,EX_DATE,EX_TYPE,EX_COUNT,EX_RESULT,IDS_VALUE) " + "values('" + getUUID() + "','" + tableName + "','" + dateStr
					+ "','" + type + "'," + faCount + ",'fail','" + faIds + "')";
			sqls.add(suSql);
			sqls.add(faSql);
		}
		return sqls;
	}

	public static String getUUID() {
		String ID = UUID.randomUUID().toString().replace("-", "");
		return ID;
	}
}
