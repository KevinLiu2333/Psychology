/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldSelector;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.MultiReader;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanFilter;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.CachingCollector;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.grouping.GroupDocs;
import org.apache.lucene.search.grouping.SearchGroup;
import org.apache.lucene.search.grouping.TermFirstPassGroupingCollector;
import org.apache.lucene.search.grouping.TermSecondPassGroupingCollector;
import org.apache.lucene.search.grouping.TopGroups;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.similar.MoreLikeThis;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.wondersgroup.sh.search.GroupResultSet;
import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.WdisException;
import com.wondersgroup.sh.search.WdisException.ErrorCode;
import com.wondersgroup.sh.search.bridge.BridgeFactory;
import com.wondersgroup.sh.search.bridge.TwoWayFieldBridge;
import com.wondersgroup.sh.search.common.HighLighterConfig;
import com.wondersgroup.sh.search.lucene.config.FieldInfo;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.params.MoreLikeThisParams;
import com.wondersgroup.sh.search.params.WdisParams;
import com.wondersgroup.sh.search.util.AnalyzerHelper;
import com.wondersgroup.sh.search.util.StopWordHelper;

public class LuceneSearcher implements LuceneSearchable {
	private static final Logger logger = Logger.getLogger(LuceneSearcher.class);
	public static final int MAX_DOC_NUM = 1000000;
	
	private IndexInfo[] indexes;
	private Analyzer analyzer;
	private HighLighterConfig highLighterConfig;
	private FieldInfo defaultField;
	private LuceneConfiguration configuration;
	private IndexSearcher indexSearcher;
	
	public LuceneSearcher(LuceneConfiguration configuration, String[] indexIds) {
		this.init(configuration, indexIds, null);
	}

	public LuceneSearcher(LuceneConfiguration configuration, String[] indexIds, Analyzer analyzer) {
		this.init(configuration, indexIds, analyzer);
	}
	
	private void init(LuceneConfiguration configuration, String[] indexIds, Analyzer analyzer) {
		this.configuration = configuration;
		this.indexes = configuration.getIndexesWithoutAbstract(Arrays.asList(indexIds));
		IndexInfo defaultIndex = getDefaultIndex();
		this.analyzer = (analyzer != null) ? analyzer : AnalyzerHelper.getInstance().createAnalyzer(defaultIndex);
		this.defaultField = defaultIndex.getDocument().getDefaultField();
		if( defaultField == null ) {
			throw new SearchException("索引没有定义缺省字段.");
		}
		this.highLighterConfig = HighLighterConfig.createDefault();
		this.createSearcher();
	}
	
	private IndexInfo getDefaultIndex() {
		IndexInfo retIndex = null;
		for(IndexInfo index : this.indexes) {
			if( index.isDefaultIndex() ) {
				retIndex = index;
				break;
			}
		}
		return (retIndex == null) ? this.indexes[0] : retIndex; 
	}
	
	@SuppressWarnings("unchecked")
	private QueryParser createQueryParser(LuceneQuery query) {
		QueryParser queryParser = null;
		String[] queryFields = query.getQueryFields();
		if( !ArrayUtils.isEmpty(queryFields) ) {
			Map boots = new HashMap();
			for (int i = 0; i < queryFields.length; i++) {
				String field = queryFields[i];
//				///by fengjia  取index[0] 要求第一个index要包含所有field
//				///最好修改为:(以下仍然要求不同index中不存在同名field)
//				FieldInfo fieldInfo = null;
//				for(int j = 0;j < indexes.length; j++){
//					fieldInfo = this.indexes[j].getDocument().getField(field);
//					if(fieldInfo!=null){
//						break;
//					}
//				}
				FieldInfo fieldInfo = this.indexes[0].getDocument().getField(field);
				boots.put(field, fieldInfo.getBoost());
			}
			queryParser = new MultiFieldQueryParser(Constants.LUCENE_VERSION, queryFields, this.analyzer, boots);
		}
		else {
			queryParser = new QueryParser(Constants.LUCENE_VERSION, this.defaultField.getName(), this.analyzer);
		}
		
		queryParser.setDefaultOperator(this.indexes[0].getDefaultOperator());
		queryParser.setAllowLeadingWildcard(true);
		return queryParser;
	}
	
	public Query createQuery(LuceneQuery query) throws ParseException {
		QueryParser queryParser = this.createQueryParser(query);
		Query engineQuery = queryParser.parse(query.getNativeQuery());
		//BooleanFilter filter = (BooleanFilter)query.getFilter();
		//TermRangeQuery avoidTitleQuery = new TermRangeQuery(filter., lowerTerm, upperTerm, includeLower, includeUpper);
		//TermRangeFilter
		BooleanQuery booleanQuery = new BooleanQuery();
		if( !ArrayUtils.isEmpty(query.getOldQueries()) ) {	// search in result enabled
			for (int i = 0; i < query.getOldQueries().length; i++) {
				String oldQuery = query.getOldQueries()[i];
				if( StringUtils.isNotBlank(oldQuery) ) { 
					Query oldEngineQuery = queryParser.parse(oldQuery);
					booleanQuery.add(oldEngineQuery, Occur.MUST);
				}
			}
		}
		booleanQuery.add(engineQuery, Occur.MUST);
		return booleanQuery;
	}
	
	private IndexSearcher createSearcher() {
			List<IndexReader> subReaders = new ArrayList<IndexReader>();
			for (int i = 0; i < this.indexes.length; i++) {
				IndexInfo indexInfo = this.indexes[i];
				try {
					Directory dir = FSDirectory.open(new File(indexInfo.getIndexPath()));
					IndexReader subReader = IndexReader.open(dir, true);	// readonly
					subReaders.add(subReader);
				}
				catch(Throwable ex) {
					logger.error("打开索引" + indexInfo.getId() + "错误。索引目录：" + indexInfo.getIndexPath() + "。", ex);
				}
			}
			
			IndexReader indexReader = new MultiReader(subReaders.toArray(new IndexReader[subReaders.size()]), true);	// readonly
			this.indexSearcher = new IndexSearcher(indexReader);
			return this.indexSearcher;
	}
	
	/**
	 * 先检查IndexReader在打开之后索引是否修改过，如果索引已经修改过了，得到新的IndexReader。
	 * @throws CorruptIndexException
	 * @throws IOException
	 */
	public synchronized void reopen() {
		try {
			IndexReader reader = this.indexSearcher.getIndexReader();
			if( !reader.isCurrent() ) {
				IndexReader newReader = reader.reopen();
				if( reader != newReader ) {
					reader.close();
				}
				reader = newReader;	
				this.indexSearcher.close();
				this.indexSearcher = new IndexSearcher(reader);
			}
		}
		catch(Exception ex) {
			throw new WdisException(ErrorCode.REOPEN_INDEX_READER_ERROR, "重新打开IndexReader错误。", ex);
		}
	}
	
	public SearchResultSet search(com.wondersgroup.sh.search.Query inQuery) {
		LuceneQuery query = (LuceneQuery)inQuery;
		try {
			Date startTime = new Date();
			Query engineQuery = this.createQuery(query);
			String indexStr = StringUtils.join(IndexInfo.extractIds(Arrays.asList(this.indexes)).iterator(), ",");
			StringBuffer sb = new StringBuffer("查询索引：")
				.append(indexStr)
				.append("。")
				.append("查询表达式：")
				.append(engineQuery.toString());
			logger.debug(sb.toString());
			
			TopFieldCollector tfc = TopFieldCollector.create(query.getSort(), query.getFetchSize(), query.isFillFields(),  
					query.isTrackDocScores(), query.isTrackMaxScore(), query.isDocsInOrder()); 
			indexSearcher.search(engineQuery, query.getFilter(), tfc);
			TopDocs docs = tfc.topDocs(); 
			
			logger.debug("从索引" + indexStr + "中查询到" + docs.totalHits + "条记录。");
			SearchResultSet srs = this.toResultSet(docs, query);
			Date endTime = new Date();
			double qtime = ((double)endTime.getTime() - startTime.getTime()) / 1000;
			srs.setQtime(qtime);
			return srs;
		} 
		catch (Exception ex) {
			throw new LuceneSearchException("全文检索失败。您要查询的是：" + query.getNativeQuery() + "。", ex);
		}
	}
	
	public void close() {
		this.closeSearcherSilently(this.indexSearcher);
	}
	
	public Document getDoc(int docId, FieldSelector fieldSelector) throws CorruptIndexException, IOException {
		return this.indexSearcher.doc(docId, fieldSelector);
	}
	
	@SuppressWarnings("unchecked")
	private SearchResultSet toResultSet(TopDocs docs, LuceneQuery query) throws Exception {
		int start = query.getStartPos() - 1;
		int end = docs.scoreDocs.length - 1;
		if( query.getSize() > 0 && (query.getStartPos() + query.getSize() < docs.scoreDocs.length) ) {
			end = query.getStartPos() + query.getSize() - 1;
		}

		String[] queryFields = query.getQueryFields();
		if( ArrayUtils.isEmpty(queryFields) ) {
			queryFields = new String[]{this.defaultField.getName()};
		}
		
		List results = new ArrayList();
		Highlighter highlighter = this.createHighLighter(query);
		for (int i = start; i <= end; i++) {
			ScoreDoc scoreDoc = docs.scoreDocs[i];
			Document doc = indexSearcher.doc(scoreDoc.doc);
			Map fragments = new HashMap();
			for (int j = 0; j < queryFields.length; j++) {
				String field = queryFields[j];
				String content = doc.get(field);
				String fragment = this.getBestFragments(highlighter, field, content);
				fragments.put(field.toUpperCase(), fragment);
			}

			LuceneSearchResult result = this.documentToSearchResult(query, scoreDoc.score, doc, fragments, this.indexes[0], scoreDoc.doc);
			results.add(result);
		}

		SearchResultSet resultSet = new SearchResultSet(results);
		resultSet.setTotalCount(docs.totalHits);
		resultSet.setQuery(query);
		return resultSet;
	}
	
	public Highlighter createHighLighter(LuceneQuery query) throws ParseException {
		QueryParser queryParser = this.createQueryParser(query);
		Query engineQuery = queryParser.parse(query.getNativeQuery()); 
		QueryScorer scorer = new QueryScorer(engineQuery);
		scorer.setExpandMultiTermQuery(true);
		Highlighter highlighter = new Highlighter(this.highLighterConfig.getFormatter(), scorer);
		Fragmenter fragmenter = new SimpleFragmenter(this.highLighterConfig.getFragmentSize());
		highlighter.setTextFragmenter(fragmenter);
		return highlighter;
	}
	
	public String getBestFragments(Highlighter highlighter, String field, String content) {
		String fragment = "";
		try {
			TokenStream stream = this.analyzer.tokenStream(field, new StringReader(content));
			fragment = highlighter.getBestFragments(stream, content, this.highLighterConfig.getMaxFragments(), this.highLighterConfig.getSeparator());
		}
		catch(Exception e) {
		}
		return fragment;
	}
	
	private boolean isInProjections(LuceneQuery query, FieldInfo fieldInfo) {
		if( query.getProjections() == null || query.getProjections().length == 0 )
			return true;
		
		for (int i = 0; i < query.getProjections().length; i++) {
			String field = query.getProjections()[i];
			if( fieldInfo.getName().equalsIgnoreCase(field) ) 
				return true;
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	private LuceneSearchResult documentToSearchResult(LuceneQuery query, float score, Document document, Map matchFragments, IndexInfo indexInfo, int luceneDocId) {
		LuceneSearchResult result = new LuceneSearchResult(score);
		result.setMatchFragments(matchFragments);
		FieldInfo[] fieldInfos = indexInfo.getDocument().getFields();
		
		for (int i = 0; i < fieldInfos.length; i++) {
			FieldInfo fieldInfo = fieldInfos[i];
			if( !this.isInProjections(query, fieldInfo) )	
				continue;
			TwoWayFieldBridge bridge = BridgeFactory.getBridgeByField(fieldInfo);
			Object value = bridge.get(fieldInfo.getName(), document);
			result.setProperty(fieldInfo.getName(), value);
		}
		result.setProperty(Constants.LUCENE_DOCUMENT_ID, new Integer(luceneDocId));
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private GroupResultSet toGroupResultSet(Collection<SearchGroup<String>> topGroups, TopGroups groups, LuceneQuery query) throws Exception {
	  GroupResultSet groupResultSet = new GroupResultSet();
		groupResultSet.setTotalGroupedHitCount(groups.totalGroupedHitCount);
		groupResultSet.setTotalHitCount(groups.totalHitCount);
		
		List searchResultSets = new ArrayList();
	  for( GroupDocs groupDoc : groups.groups ) {
	  	TopDocs topDocs = new TopDocs(groupDoc.totalHits, groupDoc.scoreDocs, groupDoc.maxScore);
	  	SearchResultSet searchResultSet = this.toResultSet(topDocs, query);
	  	searchResultSet.setGroupValue(groupDoc.groupValue);
	  	searchResultSets.add(searchResultSet);
	  }
	  groupResultSet.setSearchResultSets(searchResultSets);
	  
	  return groupResultSet;
	}
	
	@SuppressWarnings("unchecked")
	public GroupResultSet groupSearch(LuceneQuery query, String groupField, Sort groupSort, int topNGroups, int groupOffset, 
			Filter groupFilter) throws Exception {
	  Query engineQuery = this.createQuery(query);
	  Sort myGroupSort = (groupSort == null) ? Sort.RELEVANCE : groupSort;
		TermFirstPassGroupingCollector c1 = new TermFirstPassGroupingCollector(groupField, myGroupSort, groupOffset + topNGroups); 
	  CachingCollector cachedCollector = CachingCollector.create(c1, true, 64.0); 
		this.indexSearcher.search(engineQuery, groupFilter, cachedCollector); 
	 
	  Collection<SearchGroup<String>> topGroups = c1.getTopGroups(0, true); 
	  if (topGroups == null) { // No groups matched 
	    return null; 
	  } 
	 
	  TermSecondPassGroupingCollector c2 = new TermSecondPassGroupingCollector(groupField, topGroups, myGroupSort, query.getSort(), 
	  		query.getFetchSize(), true, false, true); 
	 
	  if( cachedCollector.isCached() ) {  
	    // Cache fit within maxCacheRAMMB, so we can replay it: 
	    cachedCollector.replay(c2); 
	  } else { 
	    // Cache was too large; must re-execute query: 
	    this.indexSearcher.search(engineQuery, c2); 
	  } 
	 
	  TopGroups groups = c2.getTopGroups(0);
	  return this.toGroupResultSet(topGroups, groups, query);
	}
	
	public int numDocs() {
		IndexReader reader = this.indexSearcher.getIndexReader();
		return reader.numDocs();
	}
	
	/**
	 * 得到与某篇文档相似的文档。
	 * 
	 * @param docId 文档索引编号
	 * @param num 相似文档的数目
	 * @param fields 比较的索引字段
	 * 
	 * @return the relative documents
	 */
	@SuppressWarnings("unchecked")
	public Document[] moreLikeThis(int docId, int num, String[] fields, WdisParams params) {
		try {
			IndexReader reader = this.indexSearcher.getIndexReader();
			MoreLikeThis mlt = new MoreLikeThis(reader);
			mlt.setAnalyzer(this.analyzer);
			mlt.setFieldNames(fields);
			
      mlt.setMinTermFreq(params.getInt(MoreLikeThisParams.MIN_TERM_FREQ, MoreLikeThis.DEFAULT_MIN_TERM_FREQ));
      mlt.setMinDocFreq(params.getInt(MoreLikeThisParams.MIN_DOC_FREQ, MoreLikeThis.DEFAULT_MIN_DOC_FREQ));
      mlt.setMinWordLen(params.getInt(MoreLikeThisParams.MIN_WORD_LEN, MoreLikeThis.DEFAULT_MIN_WORD_LENGTH));
      mlt.setMaxWordLen(params.getInt(MoreLikeThisParams.MAX_WORD_LEN, MoreLikeThis.DEFAULT_MAX_WORD_LENGTH));
      mlt.setMaxQueryTerms(params.getInt(MoreLikeThisParams.MAX_QUERY_TERMS, MoreLikeThis.DEFAULT_MAX_QUERY_TERMS));
      mlt.setMaxNumTokensParsed(params.getInt(MoreLikeThisParams.MAX_NUM_TOKENS_PARSED, MoreLikeThis.DEFAULT_MAX_NUM_TOKENS_PARSED));
      mlt.setBoost(params.getBool(MoreLikeThisParams.BOOST, false ));
			String[] stopwords = StopWordHelper.getInstance().getStopWords();
			mlt.setStopWords(new HashSet(Arrays.asList(stopwords)));
			Query q = mlt.like(docId);
			
			TopDocs docs = indexSearcher.search(q, num+1);
			List retList = new ArrayList();
			for (int i = 0; i < docs.scoreDocs.length; i++) {
				ScoreDoc scoreDoc = docs.scoreDocs[i];
				if( scoreDoc.doc != docId ) {
					Document doc = indexSearcher.doc(scoreDoc.doc);
					retList.add(doc);
				}
			}
			return (Document[])retList.toArray(new Document[retList.size()]);
		} 
		catch(Exception ex) {
			throw new SearchException("检索相关文档出错。", ex);
		}
		finally {
			this.closeSearcherSilently(indexSearcher);
		}
	}
	
	private void closeReaderSilently(IndexReader indexReader) {
		try {
			if( indexReader != null ) {
				indexReader.close();
			}
		} 
		catch (IOException e) {
			logger.warn("Close index reader error!", e);
		}
	}
	
	private void closeSearcherSilently(IndexSearcher indexSearcher) {
		try {
			if( indexSearcher != null ) {
				this.closeReaderSilently(indexSearcher.getIndexReader());
				
				indexSearcher.close();
			}
		} 
		catch (IOException e) {
			logger.warn("Close index searcher error!", e);				
		}
	}
	
	public LuceneConfiguration getConfiguration() {
		return configuration;
	}

	public IndexInfo[] getIndexes() {
		return indexes;
	}

	public IndexInfo getIndex(int i) {
		return indexes[i];
	}

	public HighLighterConfig getHighLighterConfig() {
		return highLighterConfig;
	}

	public void setHighLighterConfig(HighLighterConfig highLighterConfig) {
		this.highLighterConfig = highLighterConfig;
	}

	public IndexSearcher getIndexSearcher() {
		return indexSearcher;
	}
}
