package com.wondersgroup.sh.search;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

public class GroupResultSet implements Serializable {
	private static final long serialVersionUID = 4756779555307328027L;
	private static final Logger logger = Logger.getLogger(GroupResultSet.class);

	private int totalGroupedHitCount;
	private int totalHitCount;
	private List searchResultSets;
	
	public GroupResultSet() {
	}

	public void logInfo() {
		logger.info("totalGroupedHitCount=" + totalGroupedHitCount);
		logger.info("totalHitCount=" + totalHitCount);
		for(int i = 0; i < searchResultSets.size(); i++) {
			SearchResultSet searchResultSet = (SearchResultSet)searchResultSets.get(i) ;
			logger.info("Group " + (i + 1) + ": " + searchResultSet.getGroupValue());
			logger.info(searchResultSet);
		}
	}
	
	public List getSearchResultSets() {
		return searchResultSets;
	}

	public void setSearchResultSets(List searchResultSets) {
		this.searchResultSets = searchResultSets;
	}

	public int getTotalGroupedHitCount() {
		return totalGroupedHitCount;
	}

	public void setTotalGroupedHitCount(int totalGroupedHitCount) {
		this.totalGroupedHitCount = totalGroupedHitCount;
	}

	public int getTotalHitCount() {
		return totalHitCount;
	}

	public void setTotalHitCount(int totalHitCount) {
		this.totalHitCount = totalHitCount;
	}
}
