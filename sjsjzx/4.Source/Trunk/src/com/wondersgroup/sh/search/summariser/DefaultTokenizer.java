package com.wondersgroup.sh.search.summariser;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.lucene.LuceneUtils;

public class DefaultTokenizer implements ITokenizer {
	private Analyzer analyzer;

  public DefaultTokenizer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}

  public String[] tokenize(String input) {
  	try {
			return LuceneUtils.tokenize(this.analyzer, input);
		} 
  	catch (IOException e) {
  		throw new SearchException("Tokenize error!", e);
  	}
  }
}
