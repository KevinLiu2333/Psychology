package com.wonders.wddc.tiles.jk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.wddc.suite.data.entity.DBinfoBo;

@IocBean
public class DBpool implements IDBpool{
	//@Inject 
	private DataSourceFactory dbFactory = new DataSourceFactory();
	
	private static Log log = Logs.get();
	
	//放置所有数据源
	public static Map<String,IDataSource> map = new HashMap<String,IDataSource>();
	
	@Override
	public void createDataSource(DBinfoBo dBinfo) {
		//该方法有待优化
		
		//获取id
		
		//查看该id是否在map中，不在map中就创建 一个  IDataSource dataSource
		
		//将新建的DataSource放进map中
		
		if(dBinfo != null && !(map.containsKey(dBinfo.getId()))){
			map.put(dBinfo.getId(), dbFactory.createDataSource(dBinfo));
		}
		//log.info("您已经创建过该数据源，请不要重复创建！");
	}
	
	@Override
	public Map<String, IDataSource> getAllDataSource() {
		
		return map;
	}
	
	@Override
	public IDataSource getDataSourceById(String dataSourceId) {
		
		return map.get(dataSourceId);
	}
	
	
	@Override
	public boolean testDbTest(String dataSourceId) throws Exception {
		//获取数据源
		IDataSource dataSource = map.get(dataSourceId);
		Connection conn = dataSource.getConnection();
		String sql = "";
		boolean flag = false;
		//获取数据源类型
		String TYPE = dataSource.getDataSourceType();
		if("ORACLE".equals(TYPE)){
			sql = "select 1 from dual";
		}else if("MYSQL".equals(TYPE)){
			sql = "select 1 from test";
		}else{
			log.error("数据库类型错误");
			throw new Exception("数据库类型错误");
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			if("1".equals(rs.getString(1))) flag= true ;
		}
		DBCPAssist.releaseConnection(rs, stmt, conn);
		
		return flag;
	}
	
	@Override
	public void closeDataSource(String dataSourceId) throws SQLException {
		
		map.get(dataSourceId).shutDownDataSource();
		map.remove(dataSourceId);
		
	}
	
}
