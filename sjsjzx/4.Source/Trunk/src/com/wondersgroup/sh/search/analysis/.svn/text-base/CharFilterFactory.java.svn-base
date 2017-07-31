package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.analysis.CharStream;

public interface CharFilterFactory {
  public void init(Map<String,String> args);
  public Map<String,String> getArgs();
  public CharStream create(CharStream input);
}
