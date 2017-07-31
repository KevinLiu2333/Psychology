package com.wondersgroup.sh.search.summariser;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.util.StopWordHelper;

public class DefaultStopWordsProvider implements IStopWordProvider {
	// This array is sorted in the constructor
	private String[] sortedStopWords = null;
	
	public DefaultStopWordsProvider() {
		sortedStopWords = getStopWords();
		Arrays.sort(sortedStopWords);
		//logger.info(Arrays.asList(sortedStopWords));
	}
	
	/**
	 * getter method which can be overridden to 
	 * supply the stop words. The array returned by this 
	 * method is sorted and then used internally
	 * 
	 * @return the array of stop words
	 */
	public String[] getStopWords() {
		try {
			return StopWordHelper.getInstance().getStopWords();
		}
		catch(Exception ex) {
			throw new SearchException("获取停用词（stop word）错误。", ex);
		}
	}
	
	public boolean isStopWord(String word) {
		if( StringUtils.isBlank(word) ) {
			return false;
		} 
		else {
			// search the sorted array for the word, converted to lowercase
			// if it is found, the index will be >= 0
			return (Arrays.binarySearch(sortedStopWords, word.toLowerCase()) >= 0);
		}
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("stopWords.size()", sortedStopWords.length).toString();
	}
}
