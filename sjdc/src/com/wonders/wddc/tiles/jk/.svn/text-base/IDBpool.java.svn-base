package com.wonders.wddc.tiles.jk;

import java.sql.SQLException;
import java.util.Map;

import com.wonders.wddc.suite.data.entity.DBinfoBo;

public interface IDBpool {
	
	/**
	 * 根据提供的DBinfo创建数据源
	 * @param dBinfo
	 */
	public void createDataSource(DBinfoBo dBinfo);
	
	/**
	 * 获取所有数据源
	 * @return
	 */
	public Map<String,IDataSource> getAllDataSource();
	
	/**
	 * 根据提供的DBInfoId获取数据源
	 * @param dbInfoId
	 * @return
	 */
	public IDataSource getDataSourceById(String dataSourceId);
	
	
	
	/**
	 * 关闭数据源
	 * @param dataSource
	 * @throws SQLException 
	 */
	public  void closeDataSource(String dataSourceId) throws SQLException;
	
	/**
	 * 测试DB是否连接成功
	 * @param url
	 * @param username
	 * @param password
	 * @param type
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public  boolean testDbTest(String dataSourceId) throws SQLException, Exception;
	
			
}
