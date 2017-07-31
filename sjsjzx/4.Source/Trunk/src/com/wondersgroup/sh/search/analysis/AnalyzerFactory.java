package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.Analyzer;

public interface AnalyzerFactory {
  public void init(Map<String,String> args);
  
  public Map<String,String> getArgs();
  
  public Analyzer create();
}
