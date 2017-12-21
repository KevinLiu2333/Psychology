package com.wonders.admin.at;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hslf.util.SystemTimeUtils;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.wonders.admin.entity.TbLog_bacrecman;
import com.wonders.api.entity.ApiService;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/admin/")
@IocBean
public class AdminAt {
	@Inject
	private Dao dao;
	@Inject
	private Dao dao1;
	
	
	
	
	//备份恢复管理
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.admin.bacrecman")
	public Map<String, Object> toBacRecMan(HttpServletRequest request,HttpSession session,String type,String xm){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		String condition = "where 1=1 ";
		//boolean queryflag = false; // 无条件时不查询
		if (!Strings.isEmpty(xm)){
			String str = xm.replaceAll(" ", "");
			condition += " and OPERATE_USER = '" + str + "' ";
			//queryflag = true;
		}
		if (!Strings.isEmpty(type)){
			String str = type.replaceAll(" ", "");
			condition += " and OPERATE_TYPE = '" + str + "' ";
			//queryflag = true;
		}
		//if(queryflag){
		Sql sql = Sqls.create("SELECT * FROM LOG_BACRECMAN "
				+ condition + " order by OPERATE_DATE desc ");
		sql.setPager(pager);
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				List<Object> result = new ArrayList<Object>();
				while (rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("type",			rs.getString("OPERATE_TYPE"));
					map.put("date",			rs.getDate("OPERATE_DATE"));
					map.put("remark",		rs.getString("OPERATE_REMARK"));
					map.put("user",			rs.getString("OPERATE_USER"));
					map.put("dept_from",	rs.getString("OPERATE_DEPT_FROM"));
					map.put("dept_to",		rs.getString("OPERATE_DEPT_TO"));
					map.put("operate_size",	rs.getString("OPERATE_SIZE"));
					map.put("size",			rs.getString("OPERATE_SIZE"));
					map.put("id",			rs.getString("ID"));
					result.add(map);
					}
					return result;
				}
		});
		dao1.execute(sql);
		List<Object> list =(List<Object>) sql.getResult();
		result.put("list", list);
		sql = Sqls.create("select sum(operate_size) from log_bacrecman "
				+ "where operate_dept_from = 'T_GA_FWJBXX' or operate_dept_from = 'T_GA_RHFL_CJ' "
				+ "or operate_dept_from = 'T_GA_RJBXX'");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
				if(rs.next())
					return rs.getInt(1);
				return 0;
			}
		});
		dao1.execute(sql);
		result.put("count", sql.getResult());
		sql = Sqls.create("select count(1) from ( SELECT * FROM LOG_BACRECMAN "
				+ condition + ") ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql) throws SQLException {
				if(rs.next())
					return rs.getInt(1);
				return 0;
			}
		});
		dao1.execute(sql);
		pager.setRecordCount((Integer) sql.getResult());
		//}
		result.put("xm", xm);
		result.put("type", type);
		result.put("pager", pager);
		return result;
	}
	//备份恢复管理-详细
	@SuppressWarnings("unchecked")
	@At
	@Ok("jsp:jsp.admin.bacrecman_list")
	public Map<String, Object> toBacRecManList(HttpServletRequest request,HttpSession session,String id){
		Map<String, Object> result = new HashMap<String, Object>();
		Sql sql = Sqls.create("SELECT * FROM LOG_BACRECMAN where id = '" + id + "' ");
		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection connection, ResultSet rs, Sql sql)
					throws SQLException {
				if (rs.next()){
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("type",		rs.getString("OPERATE_TYPE"));
					map.put("date",		rs.getDate("OPERATE_DATE"));
					map.put("remark",	rs.getString("OPERATE_REMARK"));
					map.put("user",		rs.getString("OPERATE_USER"));
					map.put("dept_from",rs.getString("OPERATE_DEPT_FROM"));
					map.put("dept_to",	rs.getString("OPERATE_DEPT_TO"));
					map.put("size",		rs.getString("OPERATE_SIZE"));
					map.put("id",		rs.getString("ID"));
					return map;
					}
					return null;
				}
		});
		dao1.execute(sql);
		Map<String, Object> map = (Map<String, Object>) sql.getResult();
		result.put("map", map);
		return result;
	}
	//备份恢复管理-新增
	@At
	@Ok("jsp:jsp.admin.bacrecman_add")
	public Map<String, Object> toBacRecManAdd(HttpServletRequest request, HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		return null;
	}
	//备份恢复管理-新增成功
	@At
	@Ok("jsp:jsp.admin.bacrecman_success")
	public Map<String, Object> toBacRecManAddSuccess(HttpServletRequest request, HttpSession session, String type, 
			Date date, String remark, String user, String dept_from, String size, String dept_to){
		Map<String, Object> result = new HashMap<String, Object>();
		TbLog_bacrecman bacrecman = new TbLog_bacrecman();
		bacrecman.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		bacrecman.setType(type);
		bacrecman.setDate(date);
		bacrecman.setRemark(remark);
		bacrecman.setUser(user);
		bacrecman.setDept_from(dept_from);
		bacrecman.setSize(size);
		bacrecman.setDept_to(dept_to);
		bacrecman.setWrite_time(new Date());
		dao1.insert(bacrecman);
		return result;
	}

	
	
	
			
			
			
}
