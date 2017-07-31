package com.wonders.gis.db.conn;

import java.sql.*;
import java.util.*;
import com.wonders.gis.utils.XMLReader;

/** 数据库连接管理器
 * @author XiaQiuQin
 *
 */
/**
 * @author ZhangChuanGuo
 *
 */
/**
 * @author ZhangChuanGuo
 *
 */
/**
 * @author ZhangChuanGuo
 *
 */
public class DBSDEConnectionManager 
{
	private static DBSDEConnectionManager instance = null;
	private DBConnectionPool pool;
	private static int client;
	//private Properties dbProps;
	private Vector<Driver> drivers;
	private static String driver;
	private static String url;
	private static String dbuser;
	private static String dbpass;
	private static int maxConnection;
	
	/**
	 * 静态块读取配置
	 */
	static {
		XMLReader reader = null;
		try{
			reader = new XMLReader(DBSDEConnectionManager.class.getClassLoader().getResourceAsStream("config.xml"));
			driver = reader.getValueByXPath("databaseSDE/driver");
			url = reader.getValueByXPath("databaseSDE/url");
			dbuser = reader.getValueByXPath("databaseSDE/user");
			dbpass = reader.getValueByXPath("databaseSDE/pass");
			try{
				maxConnection = Integer.parseInt(reader.getValueByXPath("databaseSDE/maxConnection"));
			}catch(NumberFormatException ex){
				maxConnection = 0;
			}
		}catch(Exception ex){
			System.out.println(" Resource File Error. " + ex.getMessage());
		}
	}
	
	
	/**
	 * 构造函数
	 */
	public DBSDEConnectionManager() 
	{
		init();
	}
	
	/** 获取DBSDEConnectionManager实例
	 * @return DBSDEConnectionManager的实例
	 */
	public synchronized static DBSDEConnectionManager getInstance(){
		if(instance == null){
			instance = new DBSDEConnectionManager();
		}
		client++;
		return instance;
	}
	/**
	 * 初始化
	 */
	void init(){
		loadDriver();
		createPool();
	}
	/**
	 * 转入Driver
	 */
	private void loadDriver()
	{
		drivers = new Vector<Driver>();
		StringTokenizer st = new StringTokenizer(driver);
		while(st.hasMoreElements())
		{
			String driverClassName = st.nextToken().trim();
			try{
				Driver driver = (Driver)Class.forName(driverClassName).newInstance();
				DriverManager.registerDriver(driver);
				drivers.add(driver);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}//parse the file, load multiply driver class in 
	
	/**
	 * 创建连接池
	 */
	private void createPool()
	{
		pool = new DBConnectionPool(dbuser, dbpass, url, maxConnection);
	}
	
	/** 获取客户端数量
	 * @return 客户端数量
	 */
	public synchronized int getClientCount(){
		return (client);
	}
	
	/** 获取数据库连接
	 * @return 数据库连接
	 */
	public Connection getDBConnection()
	{
		if(pool != null)
		{
			return (pool.getDBConnection());
		}
		return(null);
	}
	
	/** 获取数据库连接
	 * @param timeout 超时设置
	 * @return 数据库连接
	 */
	public Connection getDBConnection(long timeout)
	{
		if(pool != null)
		{
			return (pool.getDBConnection(timeout));
		}
		return(null);
	}
	
	/** 释放数据库连接
	 * @param conn 数据库连接
	 */
	public void freeDBConnection(Connection conn)
	{
		if(pool != null)
		{
			pool.freeDBConnection(conn);
		}
	}
	/** 释放数据库连接
	 * 
	 */
	public synchronized  void realse()
	{
		if(--client != 0)
		{
			return;
		}
		if(pool != null)
		{
			pool.release();
			Iterator<Driver> it = drivers.iterator();
			while(it.hasNext())
			{
				Driver driver = it.next();
				try{
					DriverManager.deregisterDriver(driver);
				}catch(Exception ex){
					System.out.println("Can not deregister driver " + driver.getClass().getName());
				}
			}
		}
	}
	
}