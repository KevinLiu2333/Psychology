package com.wondersgroup.sh.search.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;

public class StopWordHelper {
	private static final Logger logger = Logger.getLogger(StopWordHelper.class);
	private static StopWordHelper instance = new StopWordHelper();

	public static final String[] ENGLISH_STOP_WORDS = {
      "0", "1", "2", "3", "4", "5", "6", "7", "8",
      "9", "000", "$",
      "about", "after", "all", "also", "an", "and",
      "another", "any", "are", "as", "at", "be",
      "because", "been", "before", "being", "between",
      "both", "but", "by", "came", "can", "come",
      "could", "did", "do", "does", "each", "else",
      "for", "from", "get", "got", "has", "had",
      "he", "have", "her", "here", "him", "himself",
      "his", "how","if", "in", "into", "is", "it",
      "its", "just", "like", "make", "many", "me",
      "might", "more", "most", "much", "must", "my",
      "never", "now", "of", "on", "only", "or",
      "other", "our", "out", "over", "re", "said",
      "same", "see", "should", "since", "so", "some",
      "still", "such", "take", "than", "that", "the",
      "their", "them", "then", "there", "these",
      "they", "this", "those", "through", "to", "too",
      "under", "up", "use", "very", "want", "was",
      "way", "we", "well", "were", "what", "when",
      "where", "which", "while", "who", "will",
      "with", "would", "you", "your",
      "a", "b", "c", "d", "e", "f", "g", "h", "i",
      "j", "k", "l", "m", "n", "o", "p", "q", "r",
      "s", "t", "u", "v", "w", "x", "y", "z"
  };
	
	private StopWordHelper() {
	}

	public static StopWordHelper getInstance() {
		return instance;
	}
	
	public String[] getEnglishStopWords() {
		return ENGLISH_STOP_WORDS;
	}
	
	@SuppressWarnings("unchecked")
	public String[] getChineseStopWordsAsResource(String name) throws IOException {
		InputStream is = null;
		try {
			is = StopWordHelper.class.getResourceAsStream(name);
			Reader reader = new InputStreamReader(is);
			List lines = IOUtils.readLines(reader);
			String[] stopwords = (String[])lines.toArray(new String[lines.size()]);
			return stopwords;
		}
		finally {
			IOUtils.closeQuietly(is);
		}
	}

	public String[] getStopWords() throws IOException {
		return (String[])ArrayUtils.addAll(this.getEnglishStopWords(), this.getChineseStopWordsAsResource("/dic/Chinese-StopWords.txt"));
	}
	
	public static void dumpArray(Object[] arr) {
		for (int i = 0; i < arr.length; i++) {
			logger.info(arr[i]);
		}
	}
	
	public static void main(String[] args) {
		StopWordHelper helper = StopWordHelper.getInstance();
		String[] stopwords = null;
		try {
			//stopwords = helper.getChineseStopWordsAsResource("/dic/Chinese-StopWords.txt");
			stopwords = helper.getStopWords();
			dumpArray(stopwords);
		}
		catch (IOException e) {
			logger.error("Exception.", e);
		}
	}
}
