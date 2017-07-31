package com.wondersgroup.sh.search.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.wondersgroup.sh.search.SearchPortal;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

public class SearchTestRunner {
	private static final Logger logger = Logger.getLogger(SearchTestRunner.class);

	String[] queryStrings;
	String[] indexIds;
  List<Thread> searchThreads = new ArrayList<Thread>();
	MultiValueMap groupSearchTesters = new MultiValueMap();

	LuceneConfiguration configuration;
	Map<String, SearchPortal> portals = new TreeMap<String, SearchPortal>();
	double intervalSeconds = 5;
	
	public SearchTestRunner(String configFilename, String[] queryStrings, String[] indexIds) {
		this.queryStrings = queryStrings;
		this.indexIds = indexIds;
		configuration = new LuceneConfiguration(configFilename);
		configuration.readConfiguration();
		
		for(String indexId : indexIds) {
			SearchPortal portal = new SearchPortal(configuration, new String[]{indexId});
			this.portals.put(indexId, portal);
		}
	}
	
	public SearchTestRunner(String configFilename, String queryStringFile, String[] indexIds, 
			Integer maxQueryStringNum) throws Exception {
		this(configFilename, readResource(queryStringFile, maxQueryStringNum == null ? 30 : maxQueryStringNum.intValue()), 
				indexIds);
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
			this.groupSearchTesters.clear();
			this.searchThreads.clear();
			
			logger.info("查询：" + queryString);
			for(String indexId : indexIds) {
				SearchPortal portal = this.portals.get(indexId);
				SingleSearchTester tester = new SingleSearchTester(portal, queryString, indexId);
	  		groupSearchTesters.put(queryString, tester);
	  		searchThreads.add(new Thread(tester));
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
	
	private static Date getMinBeginTime(Collection<SearchPortalTester> testers) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date retTime = calendar.getTime();
		for(SearchPortalTester tester : testers) {
			if( tester.getBeginTime().before(retTime) ) 
				retTime = tester.getBeginTime();
		}
		return retTime;
	}
	
	private static Date getMaxEndTime(Collection<SearchPortalTester> testers) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date retTime = calendar.getTime();
		for(SearchPortalTester tester : testers) {
			if( tester.getEndTime().after(retTime) ) 
				retTime = tester.getEndTime();
		}
		return retTime;
	}
	
	@SuppressWarnings("unchecked")
	public void printTestInfo(String queryString) {
	  Collection<SearchPortalTester> testers = this.groupSearchTesters.getCollection(queryString);
		Date beginTime = getMinBeginTime(testers);
	  Date endTime = getMaxEndTime(testers);
	  logger.info("查询，" + SearchPortalTester.sdf.format(beginTime) + "-" + 
	  		SearchPortalTester.sdf.format(endTime));
	  for(SearchPortalTester tester : testers) {
	  	logger.info(tester.getBeginTimeString() + "-" + tester.getEndTimeString());
	  }
	}
	
	@SuppressWarnings("unchecked")
	private boolean isAllSearchCompleted() {
		List<SingleSearchTester> testers = new ArrayList<SingleSearchTester>(this.groupSearchTesters.values());
		for(SingleSearchTester tester : testers) {
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
		LuceneConfiguration configuration = new LuceneConfiguration(args[0]);
		configuration.readConfiguration();
		List<IndexInfo> indexes = configuration.getIndexesNotAbstract();
		List<String> indexIds = IndexInfo.extractIds(indexes);
		SearchTestRunner runner = new SearchTestRunner(args[0], args[1], (String[])indexIds.toArray(new String[indexIds.size()]), 
				Integer.valueOf(args[2]));
		runner.run();
	}

	public double getIntervalSeconds() {
		return intervalSeconds;
	}

	public void setIntervalSeconds(double intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}
}

