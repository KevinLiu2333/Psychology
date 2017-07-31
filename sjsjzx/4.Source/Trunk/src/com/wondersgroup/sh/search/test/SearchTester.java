/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermEnum;
import org.apache.lucene.search.BooleanFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.FilterClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.FSDirectory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wondersgroup.sh.search.GroupResultSet;
import com.wondersgroup.sh.search.SearchPortal;
import com.wondersgroup.sh.search.SearchResult;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.common.HighLighterConfig;
import com.wondersgroup.sh.search.lucene.LuceneQuery;
import com.wondersgroup.sh.search.lucene.LuceneSearchResult;
import com.wondersgroup.sh.search.lucene.LuceneSearchable;
import com.wondersgroup.sh.search.lucene.LuceneSearcher;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.params.MapWdisParams;
import com.wondersgroup.sh.search.params.WdisParams;
import com.wondersgroup.sh.search.util.SearcherFactory;

public class SearchTester {
	private static final Logger logger = Logger.getLogger(SearchTester.class);
	
	private String indexId;
	private LuceneConfiguration configuration;
	private LuceneSearcher searcher;
	private HighLighterConfig highLighterConfig;
	
	public SearchTester() {
	}

	public SearchTester(String configFileName, String indexId) throws Exception {
		this.indexId = indexId;
		configuration = new LuceneConfiguration(configFileName);
		configuration.readConfiguration();
		searcher = new LuceneSearcher(this.configuration, new String[]{indexId});
		highLighterConfig = new HighLighterConfig(new SimpleHTMLFormatter("<b>", "</b>"));
		highLighterConfig.setMaxFragments(2);
		highLighterConfig.setFragmentSize(30);
		searcher.setHighLighterConfig(highLighterConfig);
	}

	public String testTermVector() throws Exception {
		//ConstantScoreRangeQuery rangeQuery = new ConstantScoreRangeQuery("id", "111", "1111", true, true);
		String[] keywords = {"公路", "高速", "公里", "世界", "列车", "交通"};
		LuceneSearcher searcher = new LuceneSearcher(this.configuration, new String[]{indexId});
		IndexSearcher indexSearcher = searcher.getIndexSearcher();
		logger.info("索引记录数=" + indexSearcher.maxDoc());
		IndexReader indexReader = indexSearcher.getIndexReader();
		TermEnum termEnum = indexReader.terms();
		String retStr = "";
		int count = 0;
		while( termEnum.next() ) {
			Term term = termEnum.term();
			if( "content".equalsIgnoreCase(term.field()) ) {
				String text = term.text();
				System.out.println(text);
				if( !StringUtils.isNumeric(text) ) { 
					//retStr += " OR " + text;
					++count;
				}
			}
		}
		return retStr;
		/*
		TopDocs topDocs = indexSearcher.search(rangeQuery, 10);
		ScoreDoc[] docs = topDocs.scoreDocs;
		logger.info("docs size=" + docs.length);
		for(int i = 0; i < docs.length; i++) {
			logger.info(String.valueOf(docs[i].doc));
		}
		*/
		/*
		IndexReader indexReader = indexSearcher.getIndexReader();
		KeywordTermVectorMapper mapper = new KeywordTermVectorMapper(Arrays.asList(keywords), new TermVectorEntryFreqSortedComparator());
    indexReader.getTermFreqVector(0, mapper);
    indexReader.getTermFreqVector(1, mapper);
    SortedSet sortedSet = mapper.getTermVectorEntrySet();
    for (Iterator inner = sortedSet.iterator(); inner.hasNext();) {
    	TermVectorEntry tve = (TermVectorEntry) inner.next();
      logger.info(tve);
    }
    */
    /*
		TermFreqVector tfv = indexReader.getTermFreqVector(1, "content");
		for(int i = 0; i < tfv.getTerms().length; i++) {
			logger.info(tfv.getTerms()[i] + " : " + tfv.getTermFrequencies()[i]);
		}
		*/
	}
	
	public void testSearchInResult(String input, String oldInput) throws Exception {
		LuceneQuery query = new LuceneQuery(input);
		query.setOldQueries(new String[]{oldInput});
		query.setRange(1, 0);
		query.setQueryFields(new String[]{"content"});
		query.setProjections(new String[]{"title", "path"});
		SearchResultSet results = searcher.search(query);
		logger.info("totalCount=" + results.getTotalCount());
		for (Iterator iter = results.getResultList().iterator(); iter.hasNext();) {
			SearchResult result = (SearchResult) iter.next();
			logger.info(result.getMatchFragment("content"));
		}
		logger.info(results);
	}
	
	public void testSearch(String input) throws Exception {
		LuceneQuery query = new LuceneQuery(input);
		query.setRange(1, 10);
		//query.setQueryFields(new String[]{"CONTENT"});
		//query.setProjections(new String[]{"title", "path"});
		//searcher.setProjections(new String[]{"id", "article_id", "title", "article_content", "update_time", "file_name", "memo"});
		//Filter filter = new RangeFilter("send_level", "OPEN", "OPEN", true, true);
		
		
		String startDateStr = DateTools.dateToString(new Date(109, 4, 27, 14, 0, 0), Resolution.SECOND);
		String endDateStr =  DateTools.dateToString(new Date(109, 4, 27, 18, 0, 0), Resolution.SECOND);
		Filter dateFilter = new TermRangeFilter("createTime", startDateStr, endDateStr, true, true);
		Filter centerFilter = new TermRangeFilter("permission", "T001", "T001", true, true);
		Filter affairFilter = new TermRangeFilter("permission", "0000", "0000", true, true);
		BooleanFilter filter = new BooleanFilter();
		/*
		filter.add(new FilterClause(centerFilter, BooleanClause.Occur.SHOULD));
		filter.add(new FilterClause(affairFilter, BooleanClause.Occur.SHOULD));
		filter.add(new FilterClause(dateFilter, BooleanClause.Occur.MUST));
		*/
		query.setFilter(filter);
	
		//Sort sort = new Sort(new SortField("path", true));
		//query.setSort(sort);
		
		SearchResultSet results = searcher.search(query);
		logger.info("totalCount=" + results.getTotalCount());
		
		for (Iterator iter = results.getResultList().iterator(); iter.hasNext();) {
			LuceneSearchResult result = (LuceneSearchResult) iter.next();
			//logger.info("score=" + result.getScore());
			logger.info("id=" + result.getProperty("id"));
			logger.info("matchFragment=" + result.getMatchFragment("content"));
			logger.info("content=" + result.getProperty("content"));
			logger.info("function_name=" + result.getProperty("function_name"));
		}
		//logger.info(results);
	}
	
	public void testRemoteSearch(String input) throws Exception {
		LuceneSearchable searcher = (LuceneSearchable) Naming.lookup("//localhost/file");
		LuceneQuery query = new LuceneQuery(input);
		query.setRange(1, 0);
		query.setQueryFields(new String[]{"content"});
		query.setProjections(new String[]{"title", "path"});
		searcher.search(query);
		SearchResultSet results = searcher.search(query);
		logger.info("totalCount=" + results.getTotalCount());
		for (Iterator iter = results.getResultList().iterator(); iter.hasNext();) {
			SearchResult result = (SearchResult) iter.next();
			logger.info(result.getMatchFragment("content"));
		}
		logger.info(results);
	}

	public void moreLikeThis() throws Exception {
		IndexReader reader = IndexReader.open(FSDirectory.open(new File(searcher.getIndex(0).getIndexPath())));
		int numDocs = reader.maxDoc();
		for (int i = 0; i < numDocs; i++) {
			System.out.println();
			Document doc = reader.document(i);
			System.out.println(doc.get("path"));
			WdisParams params = new MapWdisParams(new HashMap());
			Document[] docs = searcher.moreLikeThis(i, 5, new String[]{"content"}, params);
			if (docs.length == 0) {
				System.out.println(" None like this");
			}
			for (int j = 0; j < docs.length; j++) {
				Document likeThisDoc = docs[j];
				System.out.println(" -> " + likeThisDoc.get("path"));
			}
		}
	}
	 
	public void testGroupSearch(String input, String groupField) throws Exception {
		LuceneQuery query = new LuceneQuery(input);
		query.setRange(1, 10);
		GroupResultSet groupResultSet = this.searcher.groupSearch(query, groupField, Sort.RELEVANCE, 10, 0, null);
		if( groupResultSet != null ) {
			groupResultSet.logInfo();
		}
	}
	
	@SuppressWarnings("unchecked")
	private static String readInput(String filename) throws IOException {
		FileInputStream fis = null;
		String retStr = null;
		try {
			fis = new FileInputStream(filename);
			List<String> keywords = IOUtils.readLines(fis);
			boolean isFirst = true;
			for(String keyword : keywords) {
				if( isFirst ) {
					retStr = keyword;
					isFirst = false;
				}
				else {
					retStr += " OR " + keyword;
				}
			}
		}
		finally {
			IOUtils.closeQuietly(fis);
		}
		
		logger.info(retStr);
		return retStr; 
	}

	private void testReopen() throws IOException {
		LuceneConfiguration configuration = new LuceneConfiguration("fileConfig.xml");
		configuration.readConfiguration();
		
		SearcherFactory sf = SearcherFactory.getInstance();
		LuceneSearcher searcher;
		String input;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.print("输入查询关键字: ");
			input = br.readLine();
			
			LuceneQuery query = new LuceneQuery(input);
			query.setPageRange(3, 1);
			searcher = sf.createLuceneSearcher(configuration, new String[]{"test"});
			SearchResultSet result = searcher.search(query);
			logger.info(result);
		}
		while( !"exit".equalsIgnoreCase(input) );
	}
	
	private void testCombineIndex(String[] indexes, String input) {
		String configFilename = "splitHugeLocal.cfg.xml"; // "hugeNew.cfg.xml"; 
		LuceneConfiguration configuration = new LuceneConfiguration(configFilename);
		configuration.readConfiguration();

		SearcherFactory sf = SearcherFactory.getInstance();
		LuceneQuery query = new LuceneQuery(input);
		query.setPageRange(1, 2);
		LuceneSearcher searcher = sf.createLuceneSearcher(configuration, indexes);
		SearchResultSet resultSet = searcher.search(query);
		//logger.info(result);
		
		GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss")
			.setPrettyPrinting()
			.excludeFieldsWithoutExposeAnnotation();
		Gson gson = gsonBuilder.create();
		String json = gson.toJson(resultSet);
		logger.info(json);
		
		resultSet = gson.fromJson(json, SearchResultSet.class);
		for(SearchResult result : resultSet.getResultList()) {
			logger.info(result);
		}
	}
	
	private static void testPerformance() throws Exception {
		SearchTester tester = new SearchTester("huge.cfg.xml", "hugeIndex");
		String text = tester.testTermVector();
		System.out.println(text);
		tester.testGroupSearch("意大利", "GENDER");
		
		long startTime = new Date().getTime();
		long time1 = new Date().getTime();
		logger.info("启动时间:" + (new Date().getTime() - startTime) + "毫秒");
		tester.testSearch(readInput("c:/keywords.txt"));
		logger.info("查询时间1: " + (new Date().getTime() - time1) + "毫秒");
		
		long time2 = new Date().getTime();
		tester.testSearch("FUNCTION_NAME:fwjddssdf$12345 CONTENT:(上海)");
		logger.info("查询时间2: " + (new Date().getTime() - time2) + "毫秒");
		
		long time3 = new Date().getTime();
		tester.testSearch("意大利");
		logger.info("查询时间3: " + (new Date().getTime() - time3) + "毫秒");
		
		long time4 = new Date().getTime();
		tester.testSearch("意大利");
		logger.info("查询时间4: " + (new Date().getTime() - time3) + "毫秒");

		tester.testTermVector();
		tester.testSearchInResult("卢泰愚", "法律 OR 朝侨");
		tester.moreLikeThis();
		tester.testRemoteSearch("法律 公务");
		tester.searcher.close();
	}
	
	private void testSearchPortal() throws Exception {
		String configFilename= "D:/search-core/dev/src/splitHugeLocal.cfg.xml";
		String[] indexIds = {"index_0", "index_1", "index_2", "index_3", "index_4", "index_5", "index_6"
				, "index_7", "index_8", "index_9", "index_10", "index_11", "index_12", "index_13"
				, "index_14", "index_15", "index_16", "index_17", "index_18", "index_19", "index_20"
				, "index_21", "index_22", "index_23", "index_24", "index_25", "index_26", "index_27"
				,	"index_28",	"index_29"};
		SearchPortal sp = null;
		try {
			long startTime = new Date().getTime();
			sp = new SearchPortal(configFilename, indexIds);
			logger.info("open searcher time: " + (new Date().getTime() - startTime) + "毫秒");
			LuceneQuery query = new LuceneQuery("上海");
			SearchResultSet srs = sp.search(query);
			//logger.info(srs);

		/*
		String indexId = "index_82";
		ConfigurationHelper helper = ConfigurationHelper.getInstance();
		helper.setConfigFilename(configFilename);
		SearcherFactory.getInstance().closeSearcher(indexId);
		helper.deleteIndexNFile(indexId);
		*/
		}
		finally {
			if( sp != null ) {
				logger.info("close searcher.");
				sp.closeSearcher();
			}
		}
		/*
		while(true) {
			try {
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) {
			}
		}
		*/
	}
	
	public static void main(String[] args) throws Exception {
		SearchTester tester = new SearchTester();
		tester.testCombineIndex(new String[]{"index_0"}, "上海");
		//tester.testReopen();
		//tester.testSearchPortal();
	}
}
