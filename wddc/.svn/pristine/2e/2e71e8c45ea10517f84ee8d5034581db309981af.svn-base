package com.wonders.wddc.tiles.jk;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据源接口
 * @author wanda
 *
 */
public interface IDataSource  {
	//获取数据源对应的数据库英文名
	public String getDataBaseName();
	//获取数据源类型
	public String getDataSourceType();
	//获取数据源userName
	public String getDataSourceUserName();
	//获取数据源URL
	public String getDataSourceURL();
	//释放数据源
	public void shutDownDataSource() throws SQLException;
	//获取数据库连接
	public Connection getConnection() throws SQLException;
	
	
}
