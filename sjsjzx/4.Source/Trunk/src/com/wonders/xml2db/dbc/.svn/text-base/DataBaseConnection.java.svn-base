package com.wonders.xml2db.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.wonders.utils.PropertyUtils;

public class DataBaseConnection {
	private static final String DBDRIVER = PropertyUtils.getProperty("jdbc.driverClassName");
	private static final String DBURL = PropertyUtils.getProperty("jdbc.url");
	private static final String DBUSER = PropertyUtils.getProperty("jdbc.user");
	private static final String DBPASS = PropertyUtils.getProperty("jdbc.password");
	
	private Connection conn=null;
	public DataBaseConnection(){
		try {
			Class.forName(DBDRIVER);//加载驱动程序	
			conn=DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//取得连接
	public Connection getConnection(){
		return this.conn;
	}
	//关闭操作
	public void close(){
		if(this.conn!=null){
			try {
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
