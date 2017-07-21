package com.wonders.wddc.tiles.jk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

/**
 * DBCP帮助类  根据DBInfo的id获取数据源、关闭连接
 * @author wanda
 *
 */
@IocBean
public class DBCPAssist {
	//@Inject
	//private static DBpool dbPool;
	
	/**
	 * 获取 封装了DBCP连接池的数据源的 自定义数据源
	 * @param id
	 * @return
	 */
	/*public static IDataSource getDataSource(String id){
		return dbPool.getDataSourceById(id);
	}*/
	
	/**
	 * 创建 Statement 一次性存取较好
	 * @param conn
	 * @param toDealSql
	 * @return
	 * @throws SQLException 
	 */
	public static Statement creatStatement(Connection conn) throws SQLException{
		 
		return conn.createStatement();
		
	}
	
	/**
	 * 创建 PreparedStatement 批处理性能较好
	 * @param conn
	 * @param toDealSql
	 * @return
	 * @throws SQLException 
	 */
	public static PreparedStatement createPreparedStatement(Connection conn,String toDealSql) throws SQLException{
		return conn.prepareStatement(toDealSql);
		
	}
	
	/**
	 * 创建 ResultSet 非批处理
	 * @param conn
	 * @param toDealSql
	 * @return
	 * @throws SQLException 
	 */
	public static ResultSet getResultSet(Connection conn,String toDealSql) throws SQLException{
		return conn.createStatement().executeQuery(toDealSql);
	}
	
	public static void dealSql(Connection conn,String toDealSql) throws SQLException{
		creatStatement(conn).executeUpdate(toDealSql);
	}
	
	/**
	 * 数据库连接关闭，返回数据库连接池中
	 * @param resultSet
	 * @param pstmt
	 * @param connection
	 */
	public static void releaseConnection(ResultSet rs,  PreparedStatement pstmt,Connection conn) throws Exception {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}
	
	/**
	 * 数据库连接关闭，返回数据库连接池中
	 * @param resultSet
	 * @param pstmt
	 * @param connection
	 */
	public static void releaseConnection(ResultSet rs,  Statement  stmt,Connection conn) throws Exception {
		if (rs != null) {
			rs.close();
			//rs = null;
		}
		if (stmt != null) {
		stmt.close();
		//stmt = null;
	}
	if (conn != null) {
		conn.close();
		}
	}
}
