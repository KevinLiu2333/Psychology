package com.wonders.api.at;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.api.entity.ApiService;
import com.wonders.api.entity.ApiServiceUser;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;

@At("/api")
@IocBean
public class ApiAt {
	@Inject
	private Dao dao;

	@At
	@Ok("jsp:jsp.api.api_list")
	public Map<String, Object> toApiList(HttpServletRequest request,
			String servicename) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.where().and("VALID", "=", "1");
		if (servicename != null && !servicename.equals("")) {
			cri.where().and("SERVICE_NAME", "like", "%" + servicename + "%");
		}
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> services = dao.query(ApiService.class, cri, pager);
		pager.setRecordCount(dao.count(ApiService.class, cri));
		result.put("servicename", servicename);
		result.put("services", services);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.api.api_edit")
	public Map<String, Object> toApiEdit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(id)) {
			ApiService service = dao.fetch(ApiService.class,
					Cnd.where("id", "=", id));
			result.put("service", service);
		}
		return result;
	}

	@At
	@Ok("jsp:jsp.api.success")
	public void saveApi(@Param("::service.") ApiService service) {
		if (service != null) {
			if (Strings.isEmpty(service.getId())) {
				service.setValid("1");
				service.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				dao.insert(service);
			} else {
				dao.update(service);
			}
		}
	}

	@At
	@Ok("jsp:jsp.api.api_info")
	public Map<String, Object> toApiInfo(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (!Strings.isEmpty(id)) {
			ApiService service = dao.fetch(ApiService.class,
					Cnd.where("id", "=", id));
			result.put("service", service);
		}
		return result;
	}

	@At
	public void deleteApi(String id) {
		if (!Strings.isEmpty(id)) {
			ApiService service = dao.fetch(ApiService.class,
					Cnd.where("id", "=", id));
			service.setValid("0");
			dao.update(service);
		}
	}

	@At
	@Ok("jsp:jsp.api.apply_list")
	public Map<String, Object> toApplyList(HttpServletRequest request,
			String servicename, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		cri.where().and("VALID", "=", "1");
		if (servicename != null && !servicename.equals("")) {
			cri.where().and("SERVICE_NAME", "like", "%" + servicename + "%");
		}
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> services = dao.query(ApiService.class, cri, pager);
		for (ApiService service : services) {
			Criteria cri2 = Cnd.cri();
			cri2.where().and("SERVICE_ID", "=", service.getId());
			User user = (User) session
					.getAttribute(SystemConstants.SYSTEM_USER);
			cri2.where().and("USER_ID", "=", user.getUserId());
			cri2.getOrderBy().desc("APPLY_DATE");
			List<ApiServiceUser> list = dao.query(ApiServiceUser.class, cri2,
					dao.createPager(1, 1));
			if (list.size() == 0) {
				service.setServiceUser(null);
			} else {
				service.setServiceUser(list.get(0));
			}
		}
		pager.setRecordCount(dao.count(ApiService.class, cri));
		result.put("nowdate", new Date());
		result.put("servicename", servicename);
		result.put("services", services);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.api.apply1")
	public Map<String, Object> toApply1(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("id", id);
		return result;
	}

	@At
	public View xieyi() throws UnsupportedEncodingException {
		Map<String, Object> result = new HashMap<String, Object>();
		return new ViewWrapper(new JspView("jsp.api.xieyi"), result);
	}

	@At
	public View toApply2(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ApiService service = dao.fetch(ApiService.class,
				Cnd.where("ID", "=", id));
		result.put("service", service);
		return new ViewWrapper(new JspView("jsp.api.apply2"), result);
	}

	@At
	@Ok("jsp:jsp.api.applay_success")
	public void saveApply(@Param("::serviceuser.") ApiServiceUser serviceuser,
			HttpSession session) {
		if (Strings.isEmpty(serviceuser.getId())) {
			serviceuser.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		serviceuser.setDeptId(user.getDept());
		serviceuser.setUserId(user.getUserId());
		serviceuser.setState("1");
		serviceuser.setApplyDate(new Date());
		serviceuser.setUserDispalyname(user.getDisplayName());
		dao.insert(serviceuser);
	}

	@At
	@Ok("jsp:jsp.api.audit_list")
	public Map<String, Object> toAuditList(HttpServletRequest request,
			String state) {
		Map<String, Object> result = new HashMap<String, Object>();
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri = Cnd.cri();
		if (!Strings.isEmpty(state)) {
			cri.where().and("STATE", "=", state);
		}
		cri.getOrderBy().desc("APPLY_DATE");
		List<ApiServiceUser> apiServiceUsers = dao.query(ApiServiceUser.class,
				cri, pager);
		pager.setRecordCount(dao.count(ApiServiceUser.class, cri));
		result.put("state", state);
		result.put("apiserviceusers", apiServiceUsers);
		result.put("pager", pager);
		return result;
	}

	@At
	@Ok("jsp:jsp.api.audit")
	public Map<String, Object> toAudit(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		ApiServiceUser serviceUser=dao.fetch(ApiServiceUser.class,Cnd.where("ID","=",id));
		result.put("serviceuser", serviceUser);
		return result;
	}
	@At
	@Ok("jsp:jsp.api.success")
	public void saveAudit(HttpSession session ,String id,String state,String auditopinion){
		ApiServiceUser serviceUser=dao.fetch(ApiServiceUser.class, Cnd.where("ID","=",id));
		if(serviceUser!=null){
			Date date=new Date();
			serviceUser.setState(state);
			serviceUser.setCheckedDate(date);
			serviceUser.setAuditOpinion(auditopinion);
			User user=(User) session.getAttribute(SystemConstants.SYSTEM_USER);
			serviceUser.setAuditUser(user.getUserId());
			if(state.equals("2")){//通过
				serviceUser.setKey(UUID.randomUUID().toString().replaceAll("-", ""));
				serviceUser.setBeginDate(date);
				int applydays=Integer.parseInt(serviceUser.getApplyDay());
				Calendar   calendar   =   new   GregorianCalendar(); 
				calendar.setTime(date); 
				calendar.add(Calendar.DATE,applydays);
				serviceUser.setEndDate(calendar.getTime());
			}
			dao.update(serviceUser);
		}
	}
	@At
	@Ok("jsp:jsp.api.apply_result")
	public Map<String, Object> toApplyResult(String id){
		Map<String, Object> result = new HashMap<String, Object>();
		ApiServiceUser serviceUser=dao.fetch(ApiServiceUser.class,Cnd.where("ID","=",id));
		result.put("serviceUser", serviceUser);
		return result;
	}
	@At
	@Ok("jsp:jsp.api.call_count")
	public Map<String, Object> toCallCount(HttpSession session,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		User user=(User) session.getAttribute(SystemConstants.SYSTEM_USER);
		String condition=null;
		if(user.getAdmin()!=null&&user.getAdmin().equals("1")){
			condition="";
		}else {
			condition=" and USER_ID='"+user.getUserId()+"'";
		}
		
		Pager pager = ConUtils.makePaginationPager(request);
		Criteria cri=Cnd.cri();
		cri.getOrderBy().asc("SERVICE_NAME");
		List<ApiService> list = dao.query(ApiService.class,cri ,pager);
		for(ApiService service:list){
			String calltime="select count(1) times from API_LOG_WS where CALL_SERVICE_ID='"+service.getId()+"'"+condition;
			String callresult="select sum(CALL_RESULT) callresult from API_LOG_WS where CALL_SERVICE_ID='"+service.getId()+"'"+condition;
			Sql calltimeSql=Sqls.create(calltime);
			Sql callresultsSql=Sqls.create(callresult);
			calltimeSql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
						throws SQLException {
					while (rs.next()) {
						return rs.getInt("times");
					}
					return null;
				}
			});
			callresultsSql.setCallback(new SqlCallback() {
				
				@Override
				public Object invoke(Connection arg0, ResultSet rs, Sql arg2)
						throws SQLException {
					while (rs.next()) {
						return rs.getLong("callresult");
					}
					return null;
				}
			});
			dao.execute(callresultsSql,calltimeSql);
			service.setCalltime((Integer) calltimeSql.getResult());
			service.setCallresult((Long)callresultsSql.getResult());
		}
		pager.setRecordCount(dao.count(ApiService.class,cri));
		result.put("list", list);
		result.put("pager", pager);
		return result;
	}
}