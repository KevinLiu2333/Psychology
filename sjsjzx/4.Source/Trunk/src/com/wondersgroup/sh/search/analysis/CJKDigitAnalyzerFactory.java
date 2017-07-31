package com.wondersgroup.sh.search.analysis;

import org.apache.lucene.analysis.Analyzer;

public class CJKDigitAnalyzerFactory extends BaseAnalyzerFactory {
  public Analyzer create() {
  	return new CJKDigitAnalyzer(super.luceneMatchVersion);
  }
}
