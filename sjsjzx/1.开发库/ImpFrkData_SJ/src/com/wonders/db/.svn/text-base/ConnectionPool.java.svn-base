package com.wonders.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Vector;

import com.wonders.utils.PropertyUtils;

public class ConnectionPool {

	private Vector<Connection>		pool;

	private String					url;

	private String					username;

	private String					password;

	private String					driverClassName;

	/**
	 * 连接池的大小
	 */
	private int						poolSize	= 10;

	private static ConnectionPool	instance	= null;

	/**
	 * 读取设置连接池的属性文件
	 */
	private void readConfig() {
		try {
			this.driverClassName = PropertyUtils.getProperty("jdbc.driverClassName");
			this.url = PropertyUtils.getProperty("jdbc.url");
			this.username = PropertyUtils.getProperty("jdbc.user");
			this.password = PropertyUtils.getProperty("jdbc.password");
			this.poolSize = 50;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("读取属性文件出错.");
		}
	}

	private ConnectionPool() {
		init();
	}

	private void init() {
		pool = new Vector<Connection>(poolSize);
		readConfig();
		addConnection();
	}

	public synchronized void release(Connection conn) {
		pool.add(conn);

	}

	public synchronized void closePool() {
		for (int i = 0; i < pool.size(); i++) {
			try {
				((Connection) pool.get(i)).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pool.remove(i);
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public synchronized Connection getConnection() {
		if (pool.size() > 0) {
			Connection conn = pool.get(0);
			pool.remove(conn);
			return conn;
		} else {
			return null;
		}
	}

	private void addConnection() {
		Connection conn = null;
		for (int i = 0; i < poolSize; i++) {

			try {
				Class.forName(driverClassName);
				conn = java.sql.DriverManager.getConnection(url, username, password);
				pool.add(conn);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}