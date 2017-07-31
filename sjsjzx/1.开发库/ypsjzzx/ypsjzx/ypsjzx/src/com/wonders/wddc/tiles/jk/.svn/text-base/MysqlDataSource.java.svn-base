package com.wonders.wddc.tiles.jk;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.wonders.wddc.suite.data.entity.DBinfoBo;


public class MysqlDataSource   implements IDataSource {
	
	private final String TYPE = "MySQL";
	private final String DRIVER_Name = "com.mysql.jdbc.Driver";
	private BasicDataSource dataSource;
	private String username;
	private String password;
	private String url;
	private String dataBaseName;//数据库名称
	
	
	public MysqlDataSource(DBinfoBo dBinfo){
		this.username = dBinfo.getUsername();
		if(null == dBinfo.getPassword()){
			this.password = null;
		}
		
		this.url = dBinfo.getUrl();
		//暂时这么写
		this.dataBaseName = dBinfo.getDbEnName();
		dataSource = new BasicDataSource();
		dataSource.setUsername(dBinfo.getUsername());
		dataSource.setPassword(dBinfo.getPassword());
		dataSource.setUrl(dBinfo.getUrl());
		dataSource.setDriverClassName(DRIVER_Name);
		
	}

	@Override
	public Connection getConnection() throws SQLException {
		
		return dataSource.getConnection();
	}

	@Override
	public String getDataSourceType() {
		
		return TYPE;
	}

	@Override
	public String getDataSourceURL() {
		
		return url;
	}

	@Override
	public String getDataSourceUserName() {
		
		return username;
	}

	@Override
	public void shutDownDataSource() throws SQLException {
		dataSource.close();
		
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getDataBaseName() {
		return dataBaseName;
	}

	

}
