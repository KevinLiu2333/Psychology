package com.wonders.search.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.util.PropertyUtils;
import com.wondersgroup.sh.search.lucene.DefaultLuceneIndexer;
import com.wondersgroup.sh.search.lucene.LuceneIndexer;
import com.wondersgroup.sh.search.lucene.config.DBFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.FileFetcherInfo;
import com.wondersgroup.sh.search.lucene.config.IndexInfo;
import com.wondersgroup.sh.search.lucene.config.LuceneConfiguration;
import com.wondersgroup.sh.search.web.WebApplicationContextUtils;

@At("/search/index")
@IocBean
public class IndexAt {
	private Log log = Logs.get();
	/**
	 * 进入检索界面.
	 * @return
	 */
	@At
	@Ok ("jsp:jsp.search.index.indexer")
	public Map<String,Object> toIndex(ServletContext sc){
		Map<String,Object> result = new HashMap<String, Object>();
		LuceneConfiguration conf= WebApplicationContextUtils.getConfiguration(sc);
		result.put("conf", conf);
		if( conf.getFetchers() instanceof FileFetcherInfo){
			result.put("fetcherType", "fileFetcherType");
		}else if(conf.getFetchers() instanceof DBFetcherInfo){
			result.put("fetcherType", "dBFetcherType");
		}
		return result;
	}
	@At
	@Ok("jsp:jsp.search.index.index_help")
	public void index_help(){
	}
	/**
	 * 开始索引.
	 * @return
	 */
	@At
	@Ok ("json")
	public Map<String,Object> startIndex(ServletContext sc,@Param("indexType")String indexType){
		Map<String,Object> result = new HashMap<String, Object>();
		try {
			if(Strings.isBlank(indexType)){
				indexType = PropertyUtils.getProperty("indexType.incrementIndex");
			}
			if(indexType.equals(PropertyUtils.getProperty("indexType.fullIndex"))){
				fullIndexer(sc);
			}
			else if(indexType.equals(PropertyUtils.getProperty("indexType.incrementIndex")))
			{
				incrementIndexer(sc);
			}
			result.put("result", "1");
		}catch(Exception e){
			result.put("result", "0");
			e.printStackTrace();
			log.error(e);
		}
		return result;
	}
	/**
	 * 全索引索引器.
	 * @param servletContext
	 */
	public void fullIndexer(ServletContext sc) throws Exception{
		LuceneConfiguration conf= WebApplicationContextUtils.getConfiguration(sc);
		List<IndexInfo> iis = conf.getIndexes();
		for(IndexInfo ii : iis) {
			LuceneIndexer indexer = new DefaultLuceneIndexer(conf, ii.getId());
			indexer.fullIndex();
		}
	}
	/**
	 * 增量索引索引器.
	 * @param servletContext
	 */
	public void incrementIndexer(ServletContext sc) throws Exception{
		LuceneConfiguration conf= WebApplicationContextUtils.getConfiguration(sc);
		List<IndexInfo> iis = conf.getIndexes();
		for(IndexInfo ii : iis) {
			LuceneIndexer indexer = new DefaultLuceneIndexer(conf, ii.getId());
			indexer.incrementIndex();
		}
	}
}
