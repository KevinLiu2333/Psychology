package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKTokenizer;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.Reader;

public class CJKDigitLengthAnalyzerFactory extends BaseAnalyzerFactory {
  public Analyzer create() {
//	  return new IKAnalyzer();
  	return new Analyzer() {
			@Override
			public TokenStream tokenStream(String field, Reader reader) {
	      return new CJKDigitFilter(new CJKTokenizer(reader), 4);
			}
		};
  }

}
