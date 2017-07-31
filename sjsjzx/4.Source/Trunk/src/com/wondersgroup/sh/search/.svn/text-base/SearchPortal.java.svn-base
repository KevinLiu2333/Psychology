package com.wondersgroup.sh.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.apache.lucene.search.BooleanFilter;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.FilterClause;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;

import com.wondersgroup.sh.search.WdisException.ErrorCode;
import com.wondersgroup.sh.search.common.HighLighterConfig;
import com.wondersgroup.sh.search.lucene.LuceneQuery;
import com.wondersgroup.sh.search.lucene.LuceneSearcher;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.util.SearcherFactory;
import com.wondersgroup.sh.search.web.WebApplicationContextUtils;

public class SearchPortal {
	private static final Logger logger = Logger.getLogger(SearchPortal.class);

	private LuceneConfiguration configuration;
	private LuceneSearcher searcher;
	
	public SearchPortal(String configFilename) {
		this(configFilename, null);
	}
	
	public SearchPortal(String configFilename, String[] indexIds) {
		this.configuration = new LuceneConfiguration(configFilename);
		this.configuration.readConfiguration();
		initSearcher(indexIds);
	}
	
	public SearchPortal(ServletContext sc) {
		this(sc, null);
	}
	
	public SearchPortal(ServletContext sc, String[] indexIds) {
		this.configuration = WebApplicationContextUtils.getConfiguration(sc);
		initSearcher(indexIds);
	}
	
	public SearchPortal(LuceneConfiguration configuration) {
		this(configuration, null);
	}
	
	public SearchPortal(LuceneConfiguration configuration, String[] indexIds) {
		this.configuration = configuration;
		initSearcher(indexIds);
	}
	
	private void initSearcher(String[] indexIds) {
		if( indexIds != null && indexIds.length > 0 ) {
			// check indexIds
			List<String> notExistedIndexIds = this.configuration.getNotExistedIndex(Arrays.asList(indexIds));
			if( !notExistedIndexIds.isEmpty() ) {
				throw new WdisException(ErrorCode.INDEX_NOT_EXIST, "索引未在配置文件中定义：" + notExistedIndexIds);
			}
		}
		SearcherFactory searcherFactory = SearcherFactory.getInstance();
		this.searcher = searcherFactory.createLuceneSearcher(configuration, indexIds);
		this.searcher.setHighLighterConfig(new HighLighterConfig(HighLighterConfig.FRAGMENT_SIZE, HighLighterConfig.MAX_FRAGMENTS, HighLighterConfig.FRAGMENT_SEPARATOR, HighLighterConfig.DEFAULT_FORMATTER));
	}
	
	private Formatter getFormatter() {
		return new SimpleHTMLFormatter("<FONT COLOR=RED>", "</FONT>");
	}
	
	/**
	 * 普通全文检索
	 * @param query 查询条件
	 * @return SearchResultSet
	 * @throws Exception
	 */
	public SearchResultSet search(LuceneQuery query) {
		return searcher.search(query);
	}

	/**
	 * 分组全文检索。
	 * @param query 查询条件
	 * @param groupField 分组字段
	 * @param groupSort 分组排序，可以为null，按匹配度排序
	 * @param topNGroups 最多分组数
	 * @param groupOffset 分组集起始位置，从0开始
	 * @return GroupResultSet
	 * @throws Exception
	 */
	public GroupResultSet groupSearch(LuceneQuery query, String groupField, Sort groupSort, 
			int topNGroups, int groupOffset, Filter groupFilter) throws Exception {
		return this.searcher.groupSearch(query, groupField, groupSort, topNGroups, groupOffset, groupFilter);
	}
	
	/**
	 * 创建查询条件
	 * @param input 查询条件
	 * @param start 结果集起始位置, 从1开始
	 * @param size	结果集大小
	 * @param filter Lucene Filter，可以为null
	 * @param orderBys 排序字段，可以为null
	 * @return	查询条件对象
	 * @throws Exception
	 */
	public LuceneQuery createQuery(String input, int start, int size, Filter filter, Sort sort) {
		LuceneQuery query = new LuceneQuery(input);
		query.setRange(start, size);
		query.setSort(sort);
		query.setFilter(filter);
		return query;
	}
	
	public LuceneQuery createLuceneQuery(String input, int start, int size, Filter filter, String[] orderBys) {
		LuceneQuery query = new LuceneQuery(input);
		query.setRange(start, size);
		if( orderBys != null ) {
			List<SortField> sortFields = new ArrayList<SortField>();
			for(String orderBy : orderBys) {
				sortFields.add(new SortField(orderBy, SortField.STRING, true));
			}
			Sort sort = new Sort(sortFields.toArray(new SortField[sortFields.size()]));
			query.setSort(sort);
		}
		
		if( filter != null ) query.setFilter(filter);
		return query;
	}
	
	public static void main(String[] args) throws Exception {
		String[] genders = null;
		String[] indexIds = {"fileIndex"};
		String input = "feng";
		SearchPortal portal = new SearchPortal("xml/fileConfig.xml", indexIds);
		BooleanFilter filter = null;
		if( genders != null && genders.length > 0 ) {
			filter = new BooleanFilter();
			for(int i = 0; i < genders.length; i++) {
				TermRangeFilter rangeFilter = new TermRangeFilter("GENDER", genders[i], genders[i], true, true);
				filter.add(new FilterClause(rangeFilter, Occur.SHOULD));
			}
		}
		LuceneQuery query = portal.createLuceneQuery(input, 1, 10, null, null);
		long begin = System.currentTimeMillis();
		SearchResultSet searchResultSet = portal.search(query);
		double total = ((double)(System.currentTimeMillis() - begin)) / 1000;
		logger.info("用时=" + total + "秒");
		logger.info(searchResultSet);
		
		GroupResultSet groupResultSet = portal.groupSearch(query, "GENDER", null, 10, 0, null);
		if( groupResultSet != null ) groupResultSet.logInfo();
	}

	public void closeSearcher() {
		if( this.searcher != null ) {
			searcher.close();
			searcher = null;
		}
	}
	
	public LuceneConfiguration getConfiguration() {
		return configuration;
	}

	public LuceneSearcher getSearcher() {
		return searcher;
	}
}
