package com.wondersgroup.sh.search.client.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public abstract class AbstractSearchRunner implements Runnable {
	private static final Logger logger = Logger.getLogger(AbstractSearchRunner.class);

	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	protected String queryString;
	protected double elapsedTime;
	protected boolean completed;
	protected Date beginTime;
	protected Date endTime;
	
	public AbstractSearchRunner(String queryString) {
		this.queryString = queryString;
	}

	public void run() {
		if( logger.isDebugEnabled() )
			logger.debug("启动查询线程。");
		
		this.beginTime = new Date();
		long beign = System.currentTimeMillis();
		this.doSearch();
		this.endTime = new Date();
		elapsedTime = ((double)(System.currentTimeMillis()-beign))/1000;
		completed = true;
	}
	
	abstract protected void doSearch();
	
	public String getRunInfo() {
		return "用时=" + elapsedTime + "秒[" + getBeginTimeString() + "-" + getEndTimeString() + "]";
	}
	
	public String getBeginTimeString() {
		return sdf.format(this.beginTime);
	}
	
	public String getEndTimeString() {
		return sdf.format(this.endTime);
	}
	
	public String getQueryString() {
		return queryString;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public boolean isCompleted() {
		return completed;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}
}
