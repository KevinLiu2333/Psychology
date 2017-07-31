package com.wonders.search.task;

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.wonders.utils.PropertyUtils;
import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.LuceneIndexer;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

@IocBean
public class IndexTask {
	private Log log =Logs.get();
	
	public void fullIndex(){
		LuceneConfiguration conf = getConfiguration();
		List<IndexInfo> iis = conf.getIndexes();
		for(IndexInfo ii : iis) {
			LuceneIndexer indexer = new DefaultLuceneIndexer(conf, ii.getId());
			indexer.fullIndex();
		}
	}
	
	private LuceneConfiguration getConfiguration(){
		LuceneConfiguration configuration =null;
		long startTime = System.currentTimeMillis();
		if (log.isInfoEnabled()) {
			log.info("全文检索配置: 开始初始化");
		}
		String configFileName = PropertyUtils.getProperty("conflocation");
		if( configFileName != null ) {
			configuration = new LuceneConfiguration(configFileName);
			configuration.readConfiguration();
			if (log.isInfoEnabled()) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				log.info("全文检索配置: 初始化完成时间" + elapsedTime + "毫秒");
			}
		}
		return configuration;
	}
}
