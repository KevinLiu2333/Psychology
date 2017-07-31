package com.wondersgroup.sh.search.client.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.client.WdissConfiguration;
import com.wondersgroup.sh.search.client.WdissConfigurationHelper;

public class SearchSimulator {
	private static final Logger logger = Logger.getLogger(SearchSimulator.class);

	String[] queryStrings;
	String[] indexIds;
  List<Thread> searchThreads = new ArrayList<Thread>();
  MultiValueMap searchRunnerMap = new MultiValueMap();
	double intervalSeconds = 5;
	
	public SearchSimulator(String[] queryStrings, String[] indexIds) {
		this.queryStrings = queryStrings;
		this.indexIds = indexIds;
	}
	
	public SearchSimulator(String queryStringFile, String[] indexIds, Integer maxQueryStringNum) throws Exception {
		this(readResource(queryStringFile, maxQueryStringNum == null ? 30 : maxQueryStringNum.intValue()), indexIds);
	}

	@SuppressWarnings("unchecked")
	public static String[] readResource(String resourceFilename, int maxNum) throws IOException {
		InputStream is = null;
		String[] strs;
		try {
			is = new FileInputStream(resourceFilename);
			List<String> lines = IOUtils.readLines(is);
			int count = lines.size() > maxNum ? maxNum : lines.size();
			strs = new String[count];
			int i = 0;
			for(String line : lines) {
				if( !StringUtils.isBlank(line) && !line.startsWith("#")) {
					strs[i++] = line;
				}
				
				if( i >= count )
					break;
			}
		}
		finally {
			IOUtils.closeQuietly(is);
		}
		return strs;
	}
	
	private static void sleep(double seconds) {
		try {
			Thread.sleep((int)(seconds * 1000));
		}
		catch(InterruptedException ex) {
		}
	}
	
	public void run() {
		for(String queryString : queryStrings) {
			this.searchThreads.clear();
			
			logger.info("查询：" + queryString);
			for(String indexId : indexIds) {
				AbstractSearchRunner searchRunner = new SingleSearchRunner(queryString, indexId);
				this.searchRunnerMap.put(queryString, searchRunner);
	  		searchThreads.add(new Thread(searchRunner));
			}
		
			long beign = System.currentTimeMillis();
			
			// start threads
		  for(Thread thread : searchThreads) {
		  	thread.start();
		  }
	
		  // wait all thread completed
		  while( !isAllSearchCompleted() ) {
		  	sleep(0.1);
		  }
		  
		  double totalTime = ((double)(System.currentTimeMillis() - beign)) / 1000;
		  logger.info("共用时：" + totalTime + "秒，查询" + indexIds.length + "次，平均每次查询用时" + totalTime / indexIds.length + "秒");
		  this.printTestInfo(queryString);
		  logger.info("=====================================================================");
		}
	}
	
	private static Date getMinBeginTime(Collection<AbstractSearchRunner> testers) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date retTime = calendar.getTime();
		for(AbstractSearchRunner tester : testers) {
			if( tester.getBeginTime().before(retTime) ) 
				retTime = tester.getBeginTime();
		}
		return retTime;
	}
	
	private static Date getMaxEndTime(Collection<AbstractSearchRunner> testers) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date retTime = calendar.getTime();
		for(AbstractSearchRunner tester : testers) {
			if( tester.getEndTime().after(retTime) ) 
				retTime = tester.getEndTime();
		}
		return retTime;
	}
	
	@SuppressWarnings("unchecked")
	public void printTestInfo(String queryString) {
	  Collection<AbstractSearchRunner> testers = this.searchRunnerMap.getCollection(queryString);
		Date beginTime = getMinBeginTime(testers);
	  Date endTime = getMaxEndTime(testers);
	  logger.info("查询，" + AbstractSearchRunner.sdf.format(beginTime) + "-" + 	AbstractSearchRunner.sdf.format(endTime));
	  for(AbstractSearchRunner tester : testers) {
	  	logger.info(tester.getBeginTimeString() + "-" + tester.getEndTimeString());
	  }
	}
	
	@SuppressWarnings("unchecked")
	private boolean isAllSearchCompleted() {
		List<AbstractSearchRunner> testers = new ArrayList<AbstractSearchRunner>(this.searchRunnerMap.values());
		for(AbstractSearchRunner tester : testers) {
			if( !tester.isCompleted() )
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		/*
		Integer maxQueryStringNum = null;
		int argCount = 4;
		if( args.length >= argCount ) {
			maxQueryStringNum = Integer.valueOf(args[argCount-1]);
		}
		
		++argCount;
		Integer maxFunctionNameNum = null;
		if( args.length >= argCount ) {
			maxFunctionNameNum = Integer.valueOf(args[argCount-1]);
		}
		
		++argCount;
		Integer intervalSeconds = null;
		if( args.length >= argCount ) {
			intervalSeconds = Integer.valueOf(args[argCount-1]);
		}

		++argCount;
		String[] indexIds = null;
		if( args.length >= argCount && StringUtils.isNotBlank(args[argCount-1])) {
			indexIds = args[argCount-1].split(",");
		}
		
		SearchTestRunner runner = new SearchTestRunner(args[0], args[1], args[2], maxQueryStringNum, maxFunctionNameNum);
		if( intervalSeconds != null ) {
			runner.setIntervalSeconds(intervalSeconds);
		}
		*/
		WdissConfiguration configuration = WdissConfigurationHelper.getInstance().getConfiguration();
		List<String> indexIds = configuration.getAllIndexIds();
		SearchSimulator simulator = new SearchSimulator(args[0], (String[])indexIds.toArray(new String[indexIds.size()]), Integer.valueOf(args[1]));
		simulator.run();
	}

	public double getIntervalSeconds() {
		return intervalSeconds;
	}

	public void setIntervalSeconds(double intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}
}

