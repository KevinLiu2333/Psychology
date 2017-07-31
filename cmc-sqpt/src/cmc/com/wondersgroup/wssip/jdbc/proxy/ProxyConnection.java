/** 
 * @(#)ProxyConnection.java 2015-6-10
 * 
 * Copyright (c) 1995-2015 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.wssip.jdbc.proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;

/**
 * @author xieguoking
 * @version $Revision$ 2015-6-10
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class ProxyConnection implements Connection {

	public class CheckTask extends TimerTask {
		private Connection conn;
		private CheckTask(Connection conn,Timer timer) {
			this.conn = conn;
		}
		/** 
		 * @see java.util.TimerTask#run()
		 */
		@Override
		public void run() {
			try {
				if(!conn.isClosed()){
					logger.error("connection ["+conn.toString()+" must close.");
				}
			}
			catch (SQLException e) {
				logger.fatal("",e);
			}finally{
				//timer.cancel();
			}
		}
	}

	private Log logger = LogFactory.getLog(getClass());

	private Connection conn;

	public ProxyConnection(Connection conn) {
		this.conn = conn;		
		Timer timer= new Timer();
		TimerTask task =new CheckTask(conn,timer);
		timer.schedule(task, 30*1000);		
	}

	/**
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return conn.unwrap(iface);
	}

	/**
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return conn.isWrapperFor(iface);
	}

	/**
	 * @see java.sql.Connection#createStatement()
	 */
	@Override
	public Statement createStatement() throws SQLException {

		return conn.createStatement();
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".prepareStatement(" + sql + ")");
		}
		return conn.prepareStatement(sql);
	}

	/**
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {

		return conn.prepareCall(sql);
	}

	/**
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	@Override
	public String nativeSQL(String sql) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".nativeSQL(" + sql + ")");
		}
		return conn.nativeSQL(sql);
	}

	/**
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {

		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".setAutoCommit(" + autoCommit + ")");
		}
		conn.setAutoCommit(autoCommit);
	}

	/**
	 * @see java.sql.Connection#getAutoCommit()
	 */
	@Override
	public boolean getAutoCommit() throws SQLException {
		return conn.getAutoCommit();
	}

	/**
	 * @see java.sql.Connection#commit()
	 */
	@Override
	public void commit() throws SQLException {

		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".commit()");
		}

		conn.commit();

	}

	/**
	 * @see java.sql.Connection#rollback()
	 */
	@Override
	public void rollback() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".rollback()");
		}
		conn.rollback();
	}

	/**
	 * @see java.sql.Connection#close()
	 */
	@Override
	public void close() throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".close()");
		}
		conn.close();
	}

	/**
	 * @see java.sql.Connection#isClosed()
	 */
	@Override
	public boolean isClosed() throws SQLException {

		return conn.isClosed();
	}

	/**
	 * @see java.sql.Connection#getMetaData()
	 */
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {

		return conn.getMetaData();
	}

	/**
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {

		conn.setReadOnly(readOnly);

	}

	/**
	 * @see java.sql.Connection#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() throws SQLException {

		return conn.isReadOnly();
	}

	/**
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	@Override
	public void setCatalog(String catalog) throws SQLException {

		conn.setCatalog(catalog);

	}

	/**
	 * @see java.sql.Connection#getCatalog()
	 */
	@Override
	public String getCatalog() throws SQLException {

		return conn.getCatalog();
	}

	/**
	 * @see java.sql.Connection#setTransactionIsolation(int)
	 */
	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		conn.setTransactionIsolation(level);

	}

	/**
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	@Override
	public int getTransactionIsolation() throws SQLException {

		return conn.getTransactionIsolation();
	}

	/**
	 * @see java.sql.Connection#getWarnings()
	 */
	@Override
	public SQLWarning getWarnings() throws SQLException {
		return conn.getWarnings();
	}

	/**
	 * @see java.sql.Connection#clearWarnings()
	 */
	@Override
	public void clearWarnings() throws SQLException {
		conn.clearWarnings();
	}

	/**
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return conn.createStatement(resultSetType, resultSetConcurrency);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {

		return conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	/**
	 * @see java.sql.Connection#getTypeMap()
	 */
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return conn.getTypeMap();
	}

	/**
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {

		conn.setTypeMap(map);

	}

	/**
	 * @see java.sql.Connection#setHoldability(int)
	 */
	@Override
	public void setHoldability(int holdability) throws SQLException {

		conn.setHoldability(holdability);

	}

	/**
	 * @see java.sql.Connection#getHoldability()
	 */
	@Override
	public int getHoldability() throws SQLException {

		return conn.getHoldability();
	}

	/**
	 * @see java.sql.Connection#setSavepoint()
	 */
	@Override
	public Savepoint setSavepoint() throws SQLException {

		return conn.setSavepoint();
	}

	/**
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return conn.setSavepoint(name);
	}

	/**
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".close(" + savepoint + ")");
		}
		conn.rollback(savepoint);
	}

	/**
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		conn.releaseSavepoint(savepoint);
	}

	/**
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {

		return conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".prepareStatement(" + sql + "," + autoGeneratedKeys + ")");
		}
		return conn.prepareStatement(sql, autoGeneratedKeys);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".prepareStatement(" + sql + ",?)");
		}
		return conn.prepareStatement(sql, columnIndexes);
	}

	/**
	 * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
	 */
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		if (logger.isDebugEnabled()) {
			logger.debug(conn + ".prepareStatement(" + sql + ",?2)");
		}
		return conn.prepareStatement(sql, columnNames);
	}

	/**
	 * @see java.sql.Connection#createClob()
	 */
	@Override
	public Clob createClob() throws SQLException {
		return conn.createClob();
	}

	/**
	 * @see java.sql.Connection#createBlob()
	 */
	@Override
	public Blob createBlob() throws SQLException {
		return conn.createBlob();
	}

	/**
	 * @see java.sql.Connection#createNClob()
	 */
	@Override
	public NClob createNClob() throws SQLException {
		return conn.createNClob();
	}

	/**
	 * @see java.sql.Connection#createSQLXML()
	 */
	@Override
	public SQLXML createSQLXML() throws SQLException {
		return conn.createSQLXML();
	}

	/**
	 * @see java.sql.Connection#isValid(int)
	 */
	@Override
	public boolean isValid(int timeout) throws SQLException {
		return conn.isValid(timeout);
	}

	/**
	 * @see java.sql.Connection#setClientInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		conn.setClientInfo(name, value);
	}

	/**
	 * @see java.sql.Connection#setClientInfo(java.util.Properties)
	 */
	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		conn.setClientInfo(properties);
	}

	/**
	 * @see java.sql.Connection#getClientInfo(java.lang.String)
	 */
	@Override
	public String getClientInfo(String name) throws SQLException {

		return conn.getClientInfo(name);
	}

	/**
	 * @see java.sql.Connection#getClientInfo()
	 */
	@Override
	public Properties getClientInfo() throws SQLException {
		return conn.getClientInfo();
	}

	/**
	 * @see java.sql.Connection#createArrayOf(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {

		return conn.createArrayOf(typeName, elements);
	}

	/**
	 * @see java.sql.Connection#createStruct(java.lang.String, java.lang.Object[])
	 */
	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {

		return conn.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String s) throws SQLException {

	}

	@Override
	public String getSchema() throws SQLException {
		return null;
	}

	@Override
	public void abort(Executor executor) throws SQLException {

	}

	@Override
	public void setNetworkTimeout(Executor executor, int i) throws SQLException {

	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return 0;
	}

}