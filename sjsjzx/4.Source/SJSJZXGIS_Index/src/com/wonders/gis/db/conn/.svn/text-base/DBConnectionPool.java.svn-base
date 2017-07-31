package com.wonders.gis.db.conn;
import java.sql.*;
import java.util.*;

/**
 * @author XiaQiuQin
 * 数据库连接池
 */
public class DBConnectionPool {
	private String dbUserName;
	private String dbPassword;
	private String connectionURL;
	private int maxConnection;
	private Vector<Connection> freeConnections;
	private int checkedOut = 0;
	
	/** 构造函数
	 * @param userName
	 * @param password
	 * @param url
	 * @param maxConn
	 */
	public DBConnectionPool(String userName, String password, String url, int maxConn) 
	{
		this.dbUserName = userName;
		this.dbPassword = password;
		this.connectionURL = url;
		this.maxConnection = maxConn;
		freeConnections = new Vector<Connection>();
	}	//initalize
	
	
	public synchronized Connection getDBConnection()
	{
		Connection conn = null;
		if(freeConnections.size() > 0)
		{
			conn = (Connection)freeConnections.get(0);
			freeConnections.remove(0);
			try{
				if(conn.isClosed()){
					checkedOut--;
					conn = getDBConnection();
				}
			}catch(SQLException ex){
				conn = getDBConnection();
			}
		}
		else if(maxConnection == 0 || checkedOut < maxConnection)
		{
			conn = newDBConnection();
		}
	
		if(conn != null)
		{
			checkedOut++ ;
		}
		return(conn);
	}// using FIFO method to get connection instance
	
	public synchronized Connection getDBConnection(long timeout)
	{
		long startTime = new java.util.Date().getTime();
		Connection conn;
		while((conn = getDBConnection()) == null)
		{
			try{
				wait(timeout);
			}
			catch(InterruptedException ex){}
			
			if(new java.util.Date().getTime() - startTime >= timeout)
			{
				return(null);
			}
		}
		return conn;
	}
	
	private Connection newDBConnection()
	{
		Connection conn = null;
		try{
			if(dbUserName == null)
			{
				conn = DriverManager.getConnection(connectionURL);
			}
			else
			{
				conn = DriverManager.getConnection(connectionURL, dbUserName, dbPassword);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		return(conn);
	}
	
	public synchronized void freeDBConnection(Connection conn)
	{
		freeConnections.add(conn);
		checkedOut--;
		notifyAll();
	}
	
	public synchronized void release()
	{
		Iterator<Connection> allConnections = freeConnections.iterator();
		while(allConnections.hasNext())
		{
			try{
				Connection conn =(Connection)allConnections.next();
				conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		freeConnections.removeAllElements();
		checkedOut = 0;
	}
}
