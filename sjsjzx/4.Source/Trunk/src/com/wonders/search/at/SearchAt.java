package com.wonders.search.at;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanFilter;
import org.apache.lucene.search.FilterClause;
import org.apache.lucene.search.TermRangeFilter;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.log.entity.OperateLog;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.utils.PropertyUtils;
import com.wonders.utils.VelocityUtils;
import com.wondersgroup.sh.search.SearchPortal;
import com.wondersgroup.sh.search.SearchResult;
import com.wondersgroup.sh.search.SearchResultSet;
import com.wondersgroup.sh.search.lucene.LuceneQuery;

@At("/search")
@IocBean
public class SearchAt {
	
	@Inject
	private Dao dao;
	private static Log log = Logs.get();
	/**
	 * 进入搜索界面.
	 * @return
	 */
	@At
	@Ok ("jsp:jsp.search.searcher")
	public String toSearch(String input){
		return "";
	}
	
	@At
	@Ok("jsp:jsp.search.result")
	public Map<String, Object> startSearch(HttpServletRequest request,ServletContext sc,@Param("input") String input,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		User user=(User) session.getAttribute(SystemConstants.SYSTEM_USER);
		OperateLog operateLog = new OperateLog();
		operateLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		operateLog.setOperateDate(new Date());
		operateLog.setOperateUser(user.getLogonName());
		operateLog.setOperateType("全文检索");
		operateLog.setOperateResult("检索\""+input+"\"");
		dao.insert(operateLog);
		Pager pager = ConUtils.makePaginationPager(request);
		try{
			if(!Strings.isBlank(input)){
				SearchResultSet searchResultSet = searcher( sc, input ,null ,pager);
				pager.setRecordCount(searchResultSet.getTotalCount());
				String resultHtml = initPage(searchResultSet.getResultList());
				if(!Strings.isBlank(resultHtml)){
					result.put("result", 1);
				}else{
					result.put("result", 0);
				}
				result.put("resultHtml", resultHtml);
				result.put("input", input);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.error(e);
		}
		result.put("pager", pager);
		return result;
	}
	/**
	 * 搜索器.
	 * @param servletContext
	 */
	public SearchResultSet searcher(ServletContext sc,String input,String[] indexIds, Pager pager) throws Exception{
		SearchPortal portal = new SearchPortal(sc, indexIds);
		BooleanFilter filter = null;
		String[] avoidTitle = splitStr(PropertyUtils.getProperty("avoid.title"));
		filter = initTitleFilter(filter,avoidTitle);
		LuceneQuery query = portal.createLuceneQuery(input, (pager.getPageNumber()-1)*pager.getPageSize()+1, pager.getPageSize(), filter, null);
		long begin = System.currentTimeMillis();
		String[] fields = {"content","title","keywords"};
		query.setQueryFields(fields);
		query.setTrackMaxScore(true);
		SearchResultSet searchResultSet = portal.search(query);
		double total = ((double)(System.currentTimeMillis() - begin)) / 1000;
		log.info("用时=" + total + "秒");
		log.info(searchResultSet);
		return searchResultSet;
	}
	/**
	 * 分拆字符串为数组.
	 * @param str
	 * @return
	 */
	private String[] splitStr(String str){
		String[] avoidTitle = {};
		if(!Strings.isBlank(str)){
			avoidTitle = str.split(",");
		}
		return avoidTitle;
	}
	/**
	 * 标题过滤器.
	 * @param filter
	 * @return
	 */
	private BooleanFilter initTitleFilter(BooleanFilter filter,String[] avoidTitle){
		if( avoidTitle != null && avoidTitle.length > 0 ) {
			if(filter==null){
				filter = new BooleanFilter();
			}
			for(int i = 0; i < avoidTitle.length; i++) {
				TermRangeFilter rangeFilter = new TermRangeFilter("name", avoidTitle[i], avoidTitle[i], true, true);
				filter.add(new FilterClause(rangeFilter, Occur.MUST_NOT));
			}
		}
		return filter;
	}
	/**
	 * 生成页面.
	 * @param resultList
	 * @return
	 * @throws Exception
	 */
	public String initPage(List<SearchResult> resultList)throws Exception{
		String resultHtml = "";
		if(resultList!=null && resultList.size()>0){
			Map<String, Object> velocityMap = new HashMap<String, Object>();
			velocityMap.put("resultList", resultList);
			resultHtml = VelocityUtils.merge(  velocityMap,  "resultpage");
		}
		return resultHtml;
	}
}
