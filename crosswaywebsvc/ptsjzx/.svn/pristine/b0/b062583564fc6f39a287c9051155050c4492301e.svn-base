package com.wonders.db.at;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.db.entity.TableEntity;
import com.wonders.db.service.DocService;
import com.wonders.db.service.TableService;

@IocBean
@At("/db")
public class DbAt {
	@Inject
	private Dao dao;
	@Inject
	private TableService tableService;
	@Inject
	private DocService docService;
	@At
	@Ok("jsp:jsp.db.tableinfo")
	public Map<String, Object> getTableinfo(String tablename,String from,HttpSession session) throws UnsupportedEncodingException{
		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, String>> coldate = tableService.getcoldata(tablename);
		TableEntity tableEntity=tableService.getTableInfo(tablename);
		map.put("table", tableEntity);
		map.put("from", new String(from.getBytes("iso-8859-1"),"UTF-8"));
		map.put("col", coldate);
		return map;
		
	}
	
	@At
	@Ok("jsp:jsp.db.tableinfo1")
	public Map<String, Object> getTableinfo1(String tablename,HttpSession session) throws UnsupportedEncodingException{
		Map<String, Object> map=new HashMap<String, Object>();
		List<Map<String, String>> coldate = tableService.getcoldata(tablename);
		TableEntity tableEntity=tableService.getTableInfo(tablename);
		map.put("table", tableEntity);
		map.put("col", coldate);
		return map;
		
	}
	
	@At
	@Ok("jsp:jsp.db.doc")
	public Map<String, Object> toDoc(HttpSession session)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("doc", docService.getxdb_file());
		return map;
	}
	
	@At
	@Ok("jsp:jsp.db.table")
	public Map<String, Object> toTable(HttpSession session){
		return null;
	}
	
	@At
	@Ok("json")
	public Map<String, Object> getTables(HttpSession session){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("人口", tableService.getTablenameByprefix("UA_"));
		map.put("房屋", tableService.getTablenameByprefix("WF_"));
		map.put("法人", tableService.getTablenameByprefix("T_"));
		return map;
	}
}
