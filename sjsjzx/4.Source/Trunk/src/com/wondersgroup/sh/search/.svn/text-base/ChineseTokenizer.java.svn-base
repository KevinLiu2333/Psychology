package com.wondersgroup.sh.search;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;

import com.wondersgroup.sh.search.lucene.LuceneUtils;

public class ChineseTokenizer implements ITokenizer {
	private Analyzer analyzer;

  public ChineseTokenizer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

  public String[] tokenize(String input) {
 		try {
			return LuceneUtils.tokenize(this.analyzer, input);
		} catch (IOException e) {
			throw new SearchException("Tokenize error.", e);
		}
  }

	public Analyzer getAnalyzer() {
		return analyzer;
	}

	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
}
