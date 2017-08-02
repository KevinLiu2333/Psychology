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
		//设置数据源的配置
		if("2".equals(dBinfo.getConfigType())){
			this.dataSource.setInitialSize(10);
			this.dataSource.setMaxIdle(30);
			this.dataSource.setMinIdle(5);
			this.dataSource.setMaxActive(50);
		}else if("3".equals(dBinfo.getConfigType())){
			this.dataSource.setInitialSize(30);
			this.dataSource.setMaxIdle(50);
			this.dataSource.setMinIdle(10);
			this.dataSource.setMaxActive(100);
		}else{
			this.dataSource.setInitialSize(5);
			this.dataSource.setMaxIdle(20);
			this.dataSource.setMinIdle(2);
			this.dataSource.setMaxActive(20);
		}
		
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
