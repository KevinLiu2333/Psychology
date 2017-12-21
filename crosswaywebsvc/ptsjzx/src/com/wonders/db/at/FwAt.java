package com.wonders.db.at;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.db.entity.StatLimit;

@IocBean
@At("/fw")
public class FwAt {
	@Inject
	private Dao dao;
	
	@At
	public View open(String openId) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(openId, openId);
		return new ViewWrapper(new JspView("jsp.fw.open"), result);
		
	} 
	@At
	public View view(String openId) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(openId, openId);
		return new ViewWrapper(new JspView("jsp.fw.view_"+ openId), result);
		
	} 
	
	@At
	public View res(String rId) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(rId, rId);
		return new ViewWrapper(new JspView("jsp.fw.r_"+ rId), result);
		
	} 
	@At
	public View xieyi() throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		return new ViewWrapper(new JspView("jsp.fw.xieyi"), result);
		
	} 

	 /**
	  * 各区县人员数量 .
	  * @param request
	  * @return
	  */
	 @At
	 @Ok("json")
	 public Object usedData(HttpServletRequest request) {
	        String sqlParent =  "select CALL_METHOD as cm ,count(USER_ID) as counts ,sum(CALL_RESULT) as sums  from LOG_WS group by CALL_METHOD";
	    	Sql exeSql = Sqls.create(sqlParent);
	    	exeSql.setCallback(new SqlCallback(){
	    		public Object invoke(Connection conn,ResultSet rs,Sql sql) throws SQLException{
	    	        List<StatLimit> processList = new ArrayList<StatLimit>();
	    			while (rs.next()){
	    				StatLimit statLimit = new StatLimit();
	    				statLimit.setSums(rs.getInt("sums"));
	    				statLimit.setCounts(rs.getInt("counts"));
	    				statLimit.setCm(rs.getString("cm"));
		    			processList.add(statLimit);
	    			}
	    			return processList;
	    		}
	    	});
	    	dao.execute(exeSql);
	        Map<String, Object> result = new HashMap<String, Object>();
	        result.put("result", exeSql.getResult());
	        return result;
	}

}
