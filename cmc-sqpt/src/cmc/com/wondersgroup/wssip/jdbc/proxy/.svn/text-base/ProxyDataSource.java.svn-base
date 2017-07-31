/** 
 * @(#)ProxyDatasource.java 2015-6-10
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

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author xieguoking
 * @version $Revision$ 2015-6-10
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class ProxyDataSource implements DataSource {

	private DataSource proxy;

	private Log logger = LogFactory.getLog(getClass());

	public ProxyDataSource(DataSource proxy) {
		if(logger.isDebugEnabled()){
			logger.debug("set proxy =>"+proxy);
		}
		this.proxy = proxy;
	}

	/**
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		final Connection conn = proxy.getConnection();
		if(logger.isDebugEnabled()){
			logger.debug("getConnection =>"+conn);
		}
		return new ProxyConnection(conn);
	}

	/**
	 * @see javax.sql.DataSource#getConnection(java.lang.String, java.lang.String)
	 */
	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		final Connection conn = proxy.getConnection(arg0, arg1);
		if(logger.isDebugEnabled()){
			logger.debug("getConnection(String,String) =>"+conn);
		}
		return conn;
	}

	/**
	 * @see javax.sql.DataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return proxy.getLogWriter();
	}

	/**
	 * @see javax.sql.DataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		int loginTimeout = proxy.getLoginTimeout();
		if(logger.isDebugEnabled()){
			logger.debug("loginTimeout =>"+loginTimeout);
		}
		return loginTimeout;
	}

	/**
	 * @see javax.sql.DataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {

		proxy.setLogWriter(arg0);
	}

	/**
	 * @see javax.sql.DataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		proxy.setLoginTimeout(arg0);
	}

}
