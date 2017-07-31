/**
 * 
 */
package com.wonders.gis.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.wonders.gis.db.conn.DBConnectionManager;

/**
 * @author XiaQiuQin
 * 数据库代理
 */
public class DbProxy {
	
	/**
	 * 构造函数
	 */
	public DbProxy(){
		
	}
	
	/** 执行sql语句
	 * @param strSql sql语句
	 * @return 影响的行数
	 */
	public static int ExcuteSql(String strSql){
		Connection dbconn = null;
        Statement sta = null;
        int recCount = 0;
		try {
			dbconn = DBConnectionManager.getInstance().getDBConnection();
			sta = dbconn.createStatement();
			recCount = sta.executeUpdate(strSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(sta!= null){
				try {
					sta.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBConnectionManager.getInstance().freeDBConnection(dbconn);
		}
		return recCount;
	}
	
	/** 获取sql语句的数据
	 * @param sql sql语句 
	 * @return 结果集
	 */
	public static List<Hashtable<String, Object>> GetData(String sql){
		List<Hashtable<String, Object>> dt = new ArrayList<Hashtable<String, Object>>();
    	Connection dbconn = null;
        Statement sta = null;
        ResultSet rs = null;
		try {
			dbconn = DBConnectionManager.getInstance().getDBConnection();
			sta = dbconn.createStatement();
			rs = sta.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			int count = md.getColumnCount();
			while(rs.next()){
				Hashtable<String, Object> map = new Hashtable<String, Object>();
				for(int i = 0; i < count; i++){
					String name = md.getColumnName(i + 1);
					String value = rs.getString(i+1);
					value = value == null ? "": value;
					map.put(name.toLowerCase(), value);
				}
				dt.add(map);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(sta!= null){
				try {
					sta.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			DBConnectionManager.getInstance().freeDBConnection(dbconn);
//			if(dbconn!= null){
//				try {
//					dbconn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
		}
        return dt;
	}
	
}
