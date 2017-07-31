package com.wonders.log.at;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.wonders.api.entity.ApiLogWebService;
import com.wonders.log.entity.LoginLog;
import com.wonders.log.entity.OperateLog;
import com.wonders.pzgl.entity.DwLog;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/log")
@IocBean
public class LogAt {

	@Inject
	private Dao dao;

	@At
	@Ok("jsp:jsp.log.index")
	public Map<String, Object> toIndex() {
		Map<String, Object> result = new HashMap<String, Object>();
		return result;
	}

	@At
	@Ok("jsp:jsp.log.log_login")
	public Map<String, Object> toLoginLog(HttpServletRequest request,
			String loginName) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if(!Strings.isEmpty(loginName)){
			cri.where().and("OPERATE_USER", "=", loginName);
		}
		cri.getOrderBy().desc("OPERATE_DATE");
		List<LoginLog> list=dao.query(LoginLog.class, cri,pager);
		pager.setRecordCount(dao.count(LoginLog.class, cri));
		result.put("list", list);
		result.put("loginName", loginName);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.log.log_dw")
	public Map<String, Object> toDwLog(HttpServletRequest request,
			String user){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if(!Strings.isEmpty(user)){
			cri.where().and("OPERATE_USER","=",user);
		}
		cri.getOrderBy().desc("OPERATE_DATE");
		List<DwLog> list =dao.query(DwLog.class, cri,pager);
		pager.setRecordCount(dao.count(DwLog.class,cri));
		result.put("list", list);
		result.put("user", user);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.log.dw_log_view")
	public Map<String, Object> viewDwlog(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		DwLog log=dao.fetch(DwLog.class,Cnd.where("ID","=",id));
		result.put("log", log);
		return result;
	}
	@At
	@Ok("jsp:jsp.log.api_log")
	public Map<String, Object> toAPILog(HttpServletRequest request,
			String loginName){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if(!Strings.isEmpty(loginName)){
			cri.where().and("USER_LOGINNAME", "=", loginName);
		}
		cri.getOrderBy().desc("CALL_DATE");
		List<ApiLogWebService> list = dao.query(ApiLogWebService.class, cri ,pager);
		pager.setRecordCount(dao.count(ApiLogWebService.class, cri));
		result.put("list", list);
		result.put("loginName", loginName);
		result.put("pager", pager);
		return result;
	}
	@At
	@Ok("jsp:jsp.log.operate_log")
	public Map<String, Object> toOperateLog(HttpServletRequest request,
			String loginName){
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if(!Strings.isEmpty(loginName)){
			cri.where().and("OPERATE_USER", "=", loginName);
		}
		cri.getOrderBy().desc("OPERATE_DATE");
		List<OperateLog> list = dao.query(OperateLog.class, cri ,pager);
		pager.setRecordCount(dao.count(OperateLog.class, cri));
		result.put("list", list);
		result.put("loginName", loginName);
		result.put("pager", pager);
		return result;
	}
}
