package com.wonders.wddc.tiles.jk;


import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import com.wonders.wddc.suite.data.entity.DBinfoBo;

public class OracleDataSource  implements IDataSource {
	
	private final String TYPE = "ORACLE";
	
	private final String DRIVER_Name = "oracle.jdbc.driver.OracleDriver";
	
	private BasicDataSource dataSource;
	private String username;
	private String password;
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String url;
	private String dataBaseName;
	
	public OracleDataSource(DBinfoBo dBinfo){
		dataSource = new BasicDataSource();
		this.username = dBinfo.getUsername();
		if(null == dBinfo.getPassword()){
			this.password = null;
		}
		this.url = dBinfo.getUrl();
		//暂时这么写
		this.dataBaseName = dBinfo.getDbEnName();
		this.dataSource.setUsername(dBinfo.getUsername());
		this.dataSource.setPassword(dBinfo.getPassword());
		this.dataSource.setUrl(dBinfo.getUrl());
		this.dataSource.setDriverClassName(DRIVER_Name);
		
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

	@Override
	public String getDataBaseName() {
		return dataBaseName;
	}
	
	

}
