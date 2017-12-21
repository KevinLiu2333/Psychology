package com.wonders.zx.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.util.NutMap;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean
@At("/cx")
public class CxAt {
	
	@Inject
	private Dao dao;
	
	/**
	 * 进入人口基础数据查询列表页面.
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zx.cx.people-info")
	public Map<String, Object> toPeopleData()throws Exception{
		return null;
	}
	/**
	 * 进入房屋数据基础查询列表页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.building-info")
	public Map<String, Object> toBuildingData()throws Exception{
		return null;
	}
	
	/**
	 * 进入法人数据基础查询列表页面.
	 * @return
	 * @throws Exception 
	 */
	@At
	@Ok("jsp:jsp.zx.cx.corporation-info")
	public Map<String, Object> toCorporationData()throws Exception{
		return null;
	}
	
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch1")
	public Map<String, Object> toOneKeySearch1()throws Exception{
		
		return null;
	}
	
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch2")
	public Map<String, Object> toOneKeySearch2()throws Exception{
		
		return null;
	}
	
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch3")
	public Map<String, Object> toOneKeySearch3()throws Exception{
		
		return null;
	}
	
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch4")
	public Map<String, Object> toOneKeySearch4()throws Exception{
		
		return null;
	}
	
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch5")
	public Map<String, Object> toOneKeySearch5()throws Exception{
		
		return null;
	}
	
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch6")
	public Map<String, Object> toOneKeySearch6()throws Exception{
		
		return null;
	}
	
	/**
	 * 一键检索list页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearche-list")
	public Map<String, Object> onekeysearcheList()throws Exception{
		return null;
	}
	
	/**
	 * 进入全文检索首页.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.fulltextretrieval")
	public Map<String, Object> toFulltextRetrieval()throws Exception{
		return null;
	}
	/**
	 * 全文检索list页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.fulltextList")
	public Map<String, Object> fulltextRetrievalList()throws Exception{
		return null;
	}
	/**
	 * 查询法人信息list页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.frinfo-list")
	public Map<String, Object> toFrInfoList()throws Exception{
		return null;
	}
	
	/**
	 * 查询房屋信息list页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.building-list")
	public Map<String, Object> toBuildingList()throws Exception{
		return null;
	}
	
	/**
	 * 查询普陀新闻list页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.news-list")
	public Map<String, Object> toNewsList()throws Exception{
		return null;
	}
	/**
	 * 进入一键检索页面.
	 * @return
	 * @throws Exception
	 */
	@At
	@Ok("jsp:jsp.zx.cx.onekeysearch")
	public Map<String, Object> toOneKeySearch()throws Exception{
		
		return null;
	}
	/**
	 * 法人查询界面
	 * @return
	 */
	@At
	@Ok("jsp:jsp.zx.cx.farenResult")
	public Map<String, Object> toFarenResult(String busId,String dreamformId){
		Map<String, Object> result=new HashMap<String, Object>();
		result.put("busId", busId);
		result.put("dreamformId", dreamformId);
		return result;
	}
	@At
	@Ok("json")
	public Object getCorpOrgCode(String corpid){
		NutMap re=new NutMap();
		String sql="select ORGAN_CODE from CORP_INFO where CORP_INFO_ID='"+corpid+"'";
		Sql sqls = Sqls.create(sql);
		sqls.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection connection, ResultSet resultset, Sql sql)
					throws SQLException {
				while (resultset.next()) {
					return resultset.getString("ORGAN_CODE");
					
				}
				return null;
			}
		});
		dao.execute(sqls);
		String result=(String) sqls.getResult();
		re.put("result", result);
		return re;
	}
}
