package com.wondersgroup.sh.search.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import com.wondersgroup.sh.search.analysis.BaseAnalyzerFactory;

public class TestAnalyzerFactory extends BaseAnalyzerFactory {

	@Override
	public Analyzer create() {
		return new StandardAnalyzer(Version.LUCENE_CURRENT);
	}

}
