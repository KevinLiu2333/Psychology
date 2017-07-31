package com.wondersgroup.sh.search.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.PerFieldAnalyzerWrapper;

import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;
import com.wondersgroup.sh.search.analysis.AnalyzerFactory;
import com.wondersgroup.sh.search.lucene.config.FieldInfo;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;

public class AnalyzerHelper {
	private static Map<String, Analyzer> analyzers = new HashMap<String, Analyzer>();
	private static AnalyzerHelper instance = new AnalyzerHelper();
	
	private AnalyzerHelper() {
	}
	
	public static AnalyzerHelper getInstance() {
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public synchronized Analyzer getAnalyzer(String className) {
		Analyzer analyzer = null;
		if( analyzers.containsKey(className) ) {
			analyzer = (Analyzer)analyzers.get(className);
		}
		else {
			try {
				Class<AnalyzerFactory> analyzerFactoryClazz = ReflectHelper.classForName(className);
				analyzer = analyzerFactoryClazz.newInstance().create();
				analyzers.put(className, analyzer);
			}
			catch(Exception ex) {
				throw new WdisException(ErrorCode.CREATE_ANALYZER_ERROR, "创建分词器错误：" + className, ex);
			}
		}
		return analyzer;
	}
	
	public synchronized Analyzer createAnalyzer(IndexInfo indexInfo) {
		Analyzer defaultAnalyzer = this.getAnalyzer(indexInfo.getAnalyzerClass());
		PerFieldAnalyzerWrapper analyzer = new PerFieldAnalyzerWrapper(defaultAnalyzer);
		for( FieldInfo field : indexInfo.getDocument().getFields() ) {
			if( StringUtils.isNotBlank(field.getAnalyzerClass()) ) {
				analyzer.addAnalyzer(field.getName(), this.getAnalyzer(field.getAnalyzerClass()));
			}
		}
		return analyzer;
	}
}
