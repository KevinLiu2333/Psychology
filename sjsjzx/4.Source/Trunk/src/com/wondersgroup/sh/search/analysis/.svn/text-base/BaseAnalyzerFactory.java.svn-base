package com.wondersgroup.sh.search.analysis;

import java.util.Map;

import org.apache.lucene.util.Version;

import com.wondersgroup.sh.search.lucene.Constants;

public abstract class BaseAnalyzerFactory implements AnalyzerFactory {
  protected Map<String,String> args;
  protected Version luceneMatchVersion = Constants.LUCENE_VERSION;

	public Map<String, String> getArgs() {
		return this.args;
	}

	public void init(Map<String, String> args) {
    this.args = args;
	}
}
