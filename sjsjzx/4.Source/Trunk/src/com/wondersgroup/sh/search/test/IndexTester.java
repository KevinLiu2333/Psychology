/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.wondersgroup.sh.search.Indexer;
import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.IndexExecutor;

public class IndexTester {
	private static final Logger logger = Logger.getLogger(IndexTester.class);

	@Test
	public void testIndexExecutor() throws Exception {
		IndexExecutor executor = IndexExecutor.getInstance();
		executor.setConfigFilename("splitHuge.cfg.xml");
		executor.setWorkingDirName("D:/search-core/dev/bin");
		executor.runIndex("ajfieowaqjjfasowefass", null, false);
	}
	
	@Test
	public void testFullIndexes() {
		LuceneConfiguration configuration = new LuceneConfiguration("splitHugeLocal.cfg.xml");
		configuration.readConfiguration();
		List<IndexInfo> indexes = configuration.getIndexesNotAbstract();
		for(IndexInfo index : indexes) {
			Indexer indexer = new DefaultLuceneIndexer(configuration, index.getId());
			indexer.fullIndex();
		}
	}
	
	@Test
	public void testIndexes() {
		LuceneConfiguration configuration = new LuceneConfiguration("splitHugeLocal.cfg.xml");
		configuration.readConfiguration();
		String[] indexIds = {"index_0", "index_1", "index_10"};
		for(String indexId : indexIds) {
			Indexer indexer = new DefaultLuceneIndexer(configuration, indexId);
			indexer.fullIndex();
		}
	}
}
