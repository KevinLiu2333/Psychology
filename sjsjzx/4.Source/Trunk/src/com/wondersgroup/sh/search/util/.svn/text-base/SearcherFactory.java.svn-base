package com.wondersgroup.sh.search.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.wondersgroup.sh.search.lucene.LuceneSearcher;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

public class SearcherFactory {
	private static SearcherFactory instance = new SearcherFactory();
	private static int REFRESH_HOUR = 8;
	private static String GLOBAL_SEARCHER_KEY = "#$all&*";
	private static String INDEX_JOIN_STR = "&<?";
	
	private Map<String, LuceneSearcher> searcherMap = new HashMap<String, LuceneSearcher>();
	private Map<String, Date> searcherRefreshTimeMap = new HashMap<String, Date>();
	
	private SearcherFactory() {
	}
	
	public static SearcherFactory getInstance() {
		return instance;
	}
	
	/**
	 * 检查是否到了需要重新创建LuceneSearcher的时间。
	 * @param lastRefreshTime
	 * @return
	 */
	private boolean needRefresh(Date lastRefreshTime) {
		Calendar current = Calendar.getInstance();
		Calendar refreshTime = Calendar.getInstance();
		refreshTime.set(Calendar.HOUR_OF_DAY, REFRESH_HOUR);
		
		Calendar last = Calendar.getInstance();
		last.setTime(lastRefreshTime);
		return !DateUtils.isSameDay(last, current) && current.after(refreshTime); 
	}
	
	private boolean useGlobalSearcher(LuceneConfiguration configuration, String[] indexIds) {
		return indexIds == null || indexIds.length == 0;
	}
	
	private String constructIndexKey(String[] indexIds) {
		return StringUtils.join(indexIds, INDEX_JOIN_STR);
	}
	
	/*
	public synchronized LuceneSearcher createLuceneSearcher(LuceneConfiguration configuration, String[] inIndexIds) {
		boolean isGlobal = this.useGlobalSearcher(configuration, inIndexIds);
		String key = isGlobal ? GLOBAL_SEARCHER_KEY : constructIndexKey(inIndexIds);
		List<String> list = IndexInfo.extractIds(configuration.getIndexes());
		String[] indexIds = isGlobal ? list.toArray(new String[list.size()]) : inIndexIds;
		
		LuceneSearcher searcher = searcherMap.get(key);
		Date lastRefreshTime = searcherRefreshTimeMap.get(key);
		if( searcher == null || this.needRefresh(lastRefreshTime) ) {
			if( searcher != null ) { 
				searcher.close();
				searcher = null;
			}
			searcher = new LuceneSearcher(configuration, indexIds);
			searcherMap.put(key, searcher);
			searcherRefreshTimeMap.put(key, new Date());
		}
		searcher.reopen();
		return searcher;
	}
	*/
	public LuceneSearcher createLuceneSearcher(LuceneConfiguration configuration, String[] inIndexIds) {
		boolean isGlobal = this.useGlobalSearcher(configuration, inIndexIds);
		List<String> list = IndexInfo.extractIds(configuration.getIndexes());
		String[] indexIds = isGlobal ? list.toArray(new String[list.size()]) : inIndexIds;
		return new LuceneSearcher(configuration, indexIds);
	}
	
	public synchronized void closeSearcher(String indexId) {
		List<String> keys = this.findSearchersContainIndex(indexId);
		for( String key : keys ) {
			LuceneSearcher searcher = searcherMap.get(key);
			if( searcher != null ) {
				searcher.close();
				searcher = null;
			}
			searcherMap.remove(key);
			searcherRefreshTimeMap.remove(key);
		}
	}
	
	private List<String> findSearchersContainIndex(String indexId) {
		List<String> retList = new ArrayList<String>();
		Set<String> keys = this.searcherMap.keySet();
		for( String key : keys ) {
			if( key.contains(indexId) || GLOBAL_SEARCHER_KEY.equals(key) ) {
				retList.add(key);
			}
		}
		return retList;
	}
}
