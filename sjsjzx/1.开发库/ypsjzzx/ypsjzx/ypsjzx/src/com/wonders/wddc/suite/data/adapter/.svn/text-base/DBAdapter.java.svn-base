package com.wonders.wddc.suite.data.adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.wddc.suite.data.entity.DBinfoBo;

public class DBAdapter {
	private static Log log = Logs.get();
	public final static int MYSQL = 1;
	public final static int ORACLE = 2;
	public final static int SQL_SERVER = 3;
	public final static int POSTGRESQL = 4;
	public final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public final static String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public final static String SQL_SERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public final static String POSTGRESQL_DRIVER = "org.postgresql.Driver";
	public final static String MYSQL_VALIDATIONQUERY = "select 1";
	public final static String ORACLE_VALIDATIONQUERY = "select 1 from dual";
	public final static String SQL_SERVER_VALIDATIONQUERY = "select 1";
	public final static String POSTGRESQL_VALIDATIONQUERY = "select version()";
	
	/**
	 * 根据dbinfo获取数据源
	 * 
	 * @param dBinfo
	 * @return
	 */
	public static BasicDataSource getDataSource(DBinfoBo dBinfo) {
		BasicDataSource dataSource = new BasicDataSource();
		switch (dBinfo.getType()) {
			case MYSQL:
				dataSource.setDriverClassName(MYSQL_DRIVER);
				break;
			case ORACLE:
				dataSource.setDriverClassName(ORACLE_DRIVER);
				break;
			case SQL_SERVER:
				dataSource.setDriverClassName(SQL_SERVER_DRIVER);
				break;
			case POSTGRESQL:
				dataSource.setDriverClassName(POSTGRESQL_DRIVER);
				break;
			default:
				break;
		}
		dataSource.setUrl(dBinfo.getUrl());
		dataSource.setUsername(dBinfo.getUsername());
		dataSource.setPassword(dBinfo.getPassword());
		return dataSource;
	}
	/**
	 * 关闭数据池
	 * @param dataSource
	 */
	public static void closeDataSource(BasicDataSource dataSource){
		if(dataSource!=null){
			try {
				dataSource.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
	
	public static void testDbTest(String url, String username, String password,
			int type) throws Exception{
		String clsname = null;
		String sql = null;
		switch (type) {
		case MYSQL:
			clsname = MYSQL_DRIVER;
			sql = MYSQL_VALIDATIONQUERY;
			break;
		case ORACLE:
			clsname = ORACLE_DRIVER;
			sql = ORACLE_VALIDATIONQUERY;
			break;
		case SQL_SERVER:
			clsname = SQL_SERVER_DRIVER;
			sql = SQL_SERVER_VALIDATIONQUERY;
			break;
		case POSTGRESQL:
			clsname = POSTGRESQL_DRIVER;
			sql = POSTGRESQL_VALIDATIONQUERY;
			break;
		default:
			log.error("数据库类型错误");
			throw new Exception("数据库类型错误");
		}
		Class.forName(clsname);
		Connection conn = DriverManager.getConnection(url, username,
				password);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			log.info(sql + " ----> " + rs.getString(1));
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}
