package com.wonders.wddc.tiles.jk;


import com.wonders.wddc.suite.data.entity.DBinfoBo;

public class DataSourceFactory {
	
	public final static int MYSQL = 1;
	public final static int ORACLE = 2;
	public final static int SQL_SERVER = 3;
	public final static int POSTGRESQL = 4;
	
	public IDataSource createDataSource(DBinfoBo dBinfo){
		IDataSource dataSource = null;
		if(ORACLE == dBinfo.getType()){
			dataSource = new OracleDataSource(dBinfo);
		}else if(MYSQL == dBinfo.getType()){
			dataSource = new MysqlDataSource(dBinfo);
		}
		return dataSource;
		
	}

}
