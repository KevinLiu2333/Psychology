package com.wondersgroup.sh.search.test;

import java.util.List;
import java.util.SortedSet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermVectorEntryFreqSortedComparator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TermsFilter;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import com.wondersgroup.sh.search.Indexer;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.common.HighLighterConfig;
import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.KeywordTermVectorMapper;
import com.wondersgroup.sh.search.lucene.LuceneQuery;
import com.wondersgroup.sh.search.lucene.LuceneSearcher;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;

public class ContentAuditHelper {
	private static ContentAuditHelper instance = new ContentAuditHelper();
	private static String configFilename = "contentAudit-search-cfg.xml";
	private static String indexId = "contentAuditIndex";
	private LuceneConfiguration configuration;
	
	private ContentAuditHelper() {
		configuration = new LuceneConfiguration(configFilename);
	}

	public static ContentAuditHelper getInstance() {
		return instance;
	}
	
	/**
	 * Add doc into index
	 */
	public void addDocToIndex() {
		Indexer indexer = new DefaultLuceneIndexer(configuration, indexId);
		indexer.incrementIndex();
	}
	
	/**
	 * Get keyword frequency in the doc.
	 * @param docId
	 * @param keywords
	 * @return set of TermVectorEntry, ordered by keyword frequency
	 * @throws Exception
	 */
	public SortedSet getKeywordFrequency(String docId, List keywords) throws Exception {
		LuceneSearcher searcher = new LuceneSearcher(this.configuration, new String[]{indexId});
		IndexSearcher indexSearcher = searcher.getIndexSearcher();
		IndexReader indexReader = indexSearcher.getIndexReader();
		
		TermRangeQuery rangeQuery = new TermRangeQuery("doc_id", docId, docId, true, true);
		TopDocs topDocs = indexSearcher.search(rangeQuery, LuceneSearcher.MAX_DOC_NUM);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		KeywordTermVectorMapper mapper = new KeywordTermVectorMapper(keywords, new TermVectorEntryFreqSortedComparator());
		for( int i = 0; i < scoreDocs.length; i++ ) {
	    indexReader.getTermFreqVector(scoreDocs[i].doc, mapper);
		}
		return mapper.getTermVectorEntrySet();
	}
	
	public SearchResultSet search(String docId, String keyword, int startRow, int pageSize) throws Exception {
		LuceneSearcher searcher = new LuceneSearcher(this.configuration, new String[]{indexId});
		HighLighterConfig highLighterConfig = new HighLighterConfig(new SimpleHTMLFormatter("<b>", "</b>"));
		searcher.setHighLighterConfig(highLighterConfig);

		LuceneQuery query = new LuceneQuery(keyword);
		query.setQueryFields(new String[]{"content"});
		query.setRange(startRow, pageSize);
		TermsFilter filter = new TermsFilter();
		filter.addTerm(new Term("doc_id", docId));
		query.setFilter(filter);
		return searcher.search(query);
	}
}
