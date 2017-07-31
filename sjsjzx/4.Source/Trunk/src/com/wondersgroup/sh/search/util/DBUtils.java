package com.wondersgroup.sh.search.util;

import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.lucene.config.DataSourceInfo;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtils {
	private static final Logger logger = Logger.getLogger(DBUtils.class);

	public static Connection createConnection(DataSourceInfo dsInfo) throws Exception {
		Properties properties = dsInfo.getProperties();
		return DBUtils.createConnection(properties.getProperty("driverClassName"), properties.getProperty("url"), 
				properties.getProperty("username"), properties.getProperty("password"));
	}

	public static Connection createConnection(String driverClassName, String url, String userName, 
			String password) throws Exception {
		Driver driver = (Driver)ReflectHelper.classForName(driverClassName).newInstance();
		DriverManager.registerDriver(driver);
		return DriverManager.getConnection(url, userName, password); 
	}
	/**
	 * 连接Mysql数据库
	 * @return
	 */
	public static Connection getMySqlConnection(DataSourceInfo dsInfo) throws Exception {
		Properties properties = dsInfo.getProperties();
		return DBUtils.getMySqlConnection(properties.getProperty("driverClassName"), properties.getProperty("url"),
				properties.getProperty("driverClassName"),properties.getProperty("driverClassName"));
	}
	/**
	 * 连接Mysql数据库
	 * @return
	 */
	public static Connection getMySqlConnection(String driverClassName, String url, String userName, 
			String password)  throws Exception{
			Connection mysqlConn = null;
			Class.forName(driverClassName);
			mysqlConn = DriverManager.getConnection(url,userName,password);
			return mysqlConn;
	}
	
	public static void closeQuietly(Connection conn) {
		if( conn != null ) {
			try {
				conn.close();
				conn = null;
			}
			catch(SQLException ex) {
				logger.error("关闭数据库连接错误。", ex);
			}
		}
	}
	
	public static void closeQuietly(Statement stmt) {
		if( stmt != null ) {
			try {
				stmt.close();
				stmt = null;
			}
			catch(SQLException ex) {
				logger.error("关闭statement错误。", ex);
			}
		}
	}
	
	public static void closeQuietly(ResultSet rs) {
		if( rs != null ) {
			try {
				rs.close();
				rs = null;
			}
			catch(SQLException ex) {
				logger.error("关闭ResultSet错误。", ex);
			}
		}
	}	
}
