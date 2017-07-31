package com.wonders.jkfw.at;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.Constants;
import com.wonders.db.entity.Xdb_files;
import com.wonders.jkfw.entity.UserService;
import com.wonders.jkfw.entity.Webservice;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.utils.DateUtils;

@At("/jkfw")
@IocBean(fields = "dao")
public class JkfwAt {
	private Dao dao;
	
	/**
	 * 数据接口服务list页面.
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_list")
	public Object toJkfwList(HttpServletRequest request){
		//1.适配页面的查询参数
		Criteria cri =  ConUtils.makeCri(request);
		//2.适配页面的分页参数
		Pager pager =ConUtils.makePaginationPager(request);
		//设置排序参数
		ConUtils.makePaginationOrder(request, cri, "createTime", "desc");
		//结果查询
		List<Webservice> list = dao.query(Webservice.class, cri, pager);
		pager.setRecordCount(dao.count(Webservice.class,cri));
		Map<String, Object> result = new HashMap<String, Object>();
		//数据对象
		result.put("rows", list);
		//分页信息
        result.put("pager",pager);
		return result;
	}
	/**
	 * 数据服务接口:新增或修改页面.
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_edit")
	public Map<String, Object> toServiceEdit(@Param("serviceId") String serviceId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!Strings.isEmpty(serviceId)){
			Webservice service = dao.fetch(Webservice.class, serviceId);
			map.put("service", service);
		}else{ 
			return null;
		}
		//准备下拉框doc数据
		List<Xdb_files> fileList=dao.query(Xdb_files.class, null);
		map.put("fileList", fileList);
		return map;
	}
	/**
	 * 接口服务的修改和保存操作.
	 * @param service
	 * @param request
	 */
	@At
	@Ok("jsp:jsp.jkfw.success")
	public View saveService(@Param("::service.") Webservice service){
		if(service != null){
			if(Strings.isEmpty(service.getServiceId())){ 
				service.setCreateTime(DateUtils.getCurrDate()); 
				dao.insert(service);
			}else{ 
				service.setCreateTime(dao.fetch(Webservice.class, service.getServiceId()).getCreateTime());
				dao.update(service);
				//更新服务基本信息时也同步更新已经申请或已经使用的服务信息
				List<UserService> serviceList = dao.query(UserService.class, Cnd.where("serviceId","=",service.getServiceId()));
				for(UserService webservice : serviceList){
					webservice.setServiceName(service.getServiceName());
					webservice.setSessionKey(service.getServiceUrl());
					webservice.setFromDepartment(service.getFromDepartment()); 
					webservice.setFunctionMemo(service.getFunctionMemo());
					webservice.setType(service.getType());
					dao.update(webservice);
				}
			} 
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("service", service);
		return new ViewWrapper(new JspView("jsp.jkfw.success"), result);
	}
	
	/**
	 * 删除数据接口服务.
	 * @param serviceId
	 */
	@At
	@Ok("json")
	public void delService(@Param("serviceId") String serviceId){
		if(!Strings.isEmpty(serviceId)){
			Webservice service = dao.fetch(Webservice.class, serviceId);
			if(service != null){
				dao.delete(service);
			}
		}
	}
	
	/**
	 * 接口服务查看.
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_view")
	public Map<String, Object> serviceView(String serviceId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!Strings.isEmpty(serviceId)){
			Webservice webservice = dao.fetch(Webservice.class, serviceId);
			map.put("webservice", webservice);
		}else{
			return null;
		}
		return map;
	}
	
	/**
	 * 接口服务申请list页面.
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_apply_list")
	public Object toJkfwApplyList(HttpServletRequest request, HttpSession session){
		//1.适配页面的查询参数
		Criteria cri =  ConUtils.makeCri(request);
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		List<UserService> serviceList = dao.query(UserService.class,  Cnd.where("userId", "=", user.getUserId()));
		for(int i=0;i<serviceList.size();i++){ 
			//过滤已经申请且在有效期内的数据
			if(serviceList.get(i).getEndTime().getTime() > DateUtils.getCurrDate().getTime()){
				cri.where().and("serviceId", "<>", serviceList.get(i).getServiceId());
			}
		} 
		this.checkUsable();
		//3.适配页面的分页参数
		Pager pager =ConUtils.makePaginationPager(request);
		//设置排序参数
		ConUtils.makePaginationOrder(request, cri, "createTime", "asc");
		//结果查询
		List<Webservice> list = dao.query(Webservice.class, cri, pager);
		pager.setRecordCount(dao.count(Webservice.class,cri));
		Map<String, Object> result = new HashMap<String, Object>();
		//数据对象
		result.put("rows", list);
		//分页信息
        result.put("pager",pager);
        return result;
	}
	
	/**
	 * 退回修改或重新修改.
	 * @param serviceId
	 * @param type 1:退回修改,2:重新申请.
	 * @return
	 */
	@At
	@Ok("json")
	public View toDataApply(String serviceId, String type){
		Map<String, Object> map = new HashMap<String, Object>();
		//重新申请
		if(!Strings.isEmpty(type) && "2".equals(type) && !Strings.isEmpty(serviceId)){ 
			UserService temp = dao.fetch(UserService.class, serviceId);
			Webservice service = dao.fetch(Webservice.class, temp.getServiceId());
			map.put("service", service);
			return new ViewWrapper(new JspView("jsp.jkfw.service_apply"), map);
		}
		//退回修改
		if(!Strings.isEmpty(type) && "1".equals(type) && !Strings.isEmpty(serviceId)){
			UserService service = dao.fetch(UserService.class, serviceId);
			map.put("service", service);
			return new ViewWrapper(new JspView("jsp.jkfw.apply_edit"), map);
		}
		//第一次申请
		if(Strings.isEmpty(type) && !Strings.isEmpty(serviceId)){
			Webservice service = dao.fetch(Webservice.class, serviceId);
			map.put("service", service);
			return new ViewWrapper(new JspView("jsp.jkfw.service_apply"), map);
		}
		return null;
	}
	
	/**
	 * 查看服务申请历史记录.
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_record")
	public Map<String, Object> toApplyRecord(String flag, HttpSession session, HttpServletRequest request){ 
		Map<String, Object> map = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		Criteria cri1 = Cnd.cri();
		Criteria cri2 = Cnd.cri();
		Criteria cri3 = Cnd.cri();
		//总申请服务
		List<UserService> totalList = dao.query(UserService.class,  Cnd.where("userId", "=", user.getUserId()));
		//使用中
		cri1.where().and("endTime", ">", DateUtils.getCurrDate()).and("userId", "=", user.getUserId()).and("status", "=", Constants.AUDIT_YES);
		List<UserService> validaityList = dao.query(UserService.class, cri1); 
		//已申请,待审核
		cri2.where().and("endTime", ">", DateUtils.getCurrDate()).and("userId", "=", user.getUserId()).and("status", "=", Constants.AUDIT_NO);
		List<UserService> applyList = dao.query(UserService.class,cri2);
		//已过期
		cri3.where().and("userId", "=", user.getUserId()).and("endTime", "<", DateUtils.getCurrDate());
		List<UserService> invaList = dao.query(UserService.class, cri3);
		//总记录数
		map.put("totalCount", totalList.size());
		//使用中个数
		map.put("validaitCount", validaityList.size());
		//待审核个数
		map.put("applyCount", applyList.size());
		//已过期个数
		map.put("invalitCount", invaList.size());
		
		if(!StringUtils.isEmpty(flag)){
			if(Constants.FW_TOTAL_FLAG.equals(flag)){ 
				map.put("rows", totalList);
				
				Pager pager = new Pager();
				pager.setPageSize(10);
				pager.setRecordCount(dao.count(UserService.class,Cnd.where("userId", "=", user.getUserId())));
				//分页信息
		        map.put("pager",pager);
			}
			if(Constants.FW_VALIDITY_FLAG.equals(flag)){
				map.put("rows", validaityList);
				Pager pager = new Pager();
				pager.setPageSize(10);
				pager.setRecordCount(dao.count(UserService.class,cri1));
				//分页信息
		        map.put("pager",pager);
			}
			if(Constants.FW_APPLAY_FLAG.equals(flag)){
				map.put("rows", applyList);
				Pager pager = new Pager();
				pager.setPageSize(10);
				pager.setRecordCount(dao.count(UserService.class,cri2));
				//分页信息
		        map.put("pager",pager); 
			}
			if(Constants.FW_UNALIDITY_FLAG.equals(flag)){
				map.put("rows", invaList);
				Pager pager = new Pager();
				pager.setPageSize(10);
				pager.setRecordCount(dao.count(UserService.class,cri3));
				//分页信息
		        map.put("pager",pager);
			}
		}
		return map;
	}
	
	/**
	 * 数据服务接口申请保存.
	 */
	@At
	@Ok("jsp:jsp.jkfw.success")
	public View saveDataApply(@Param("::userService.") UserService userService, HttpSession session){
		if(userService != null){ 
			Date startTime = new Date();
			Date endTime = DateUtils.addDays(startTime, userService.getActiveTime());
			userService.setStartTime(startTime); 
			userService.setEndTime(endTime);
			User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
			userService.setUserId(user.getUserId());  
			userService.setUserName(getUserNameById(user.getUserId()));
			userService.setStatus(Constants.AUDIT_NO);   
			dao.insert(userService);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userService", userService);
		return new ViewWrapper(new JspView("jsp.jkfw.success"), result);
	}
	
	/**
	 * 接口服务审核list页面.
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_check_list")
	public Object toJkfwCheckList(HttpServletRequest request){
		//1.适配页面的查询参数
		Criteria cri = ConUtils.makeCri(request);
		cri.getOrderBy().desc("startTime"); 
		//2.适配页面的分页参数
		Pager pager =ConUtils.makePaginationPager(request);
		//结果查询
		List<UserService> list = dao.query(UserService.class, cri, pager);
		pager.setRecordCount(dao.count(UserService.class,cri));
		Map<String, Object> result = new HashMap<String, Object>();
		//数据对象
		result.put("rows", list);
		//分页信息
        result.put("pager",pager);
        this.checkUsable();
		return result;
	}
	
	/**
	 * 数据服务接口审核edit页面.
	 */
	@At
	@Ok("jsp:jsp.jkfw.service_check")
	public Map<String, Object> toDataCheck(@Param("userServiceId") String userServiceId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!Strings.isEmpty(userServiceId)){
			UserService userService = dao.fetch(UserService.class, userServiceId);
			map.put("userService", userService);
		}else{
			return null;
		}
		return map;
	}
	 
	/**
	 * 服务审核保存操作.
	 */ 
	@At 
	@Ok("jsp:jsp.jkfw.success")
	public View saveDataCheck(@Param("::userService.") UserService userService){
		if(!Strings.isEmpty(userService.getUserServiceId())){
			//审核意见
			String status = userService.getStatus();
			String auditMemo = userService.getAuditMemo();
			Date auditTime = userService.getAuditTime();
			userService = dao.fetch(UserService.class, userService.getUserServiceId());
			userService.setAuditMemo(auditMemo); 
			userService.setStatus(status);
			userService.setAuditTime(auditTime); 
			dao.update(userService);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userService", userService);
		return new ViewWrapper(new JspView("jsp.jkfw.success"), result);
	} 
	/**
	 * 审核查看.
	 */
	@At
	@Ok("jsp:jsp.jkfw.check_view")
	public Map<String, Object> toView(String userServiceId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(!Strings.isEmpty(userServiceId)){
			UserService userService = dao.fetch(UserService.class, userServiceId);
			map.put("userService", userService);
		}else{
			return null;
		}
		return map;
	}
	
	/**
	 * 查看详情.
	 * @param userServiceId
	 * @return
	 */
	@At
	@Ok("jsp:jsp.jkfw.view_details")
	public Map<String, Object> viewDetails(String userServiceId){
		Map<String, Object> service = new HashMap<String, Object>();
		Criteria cri = Cnd.cri();
		UserService userService = null;
		//已申请或使用中
		if(!Strings.isEmpty(userServiceId)){
			cri.where().andEquals("userServiceId", userServiceId);
			userService = dao.fetch(UserService.class, cri);
		}
		service.put("details", userService);
		return service;
	}
	/**
	 * 根据用户id获取用户名.
	 * @param userId
	 * @return
	 */
	public String getUserNameById(String userId){
		User user = dao.fetch(User.class, userId);
		return user.getDisplayName();
	}
	
	/**
	 * 检查更新服务是否过期.
	 */
	public void checkUsable(){
		List<UserService> serviceList = dao.query(UserService.class,  Cnd.where("endTime", "<", DateUtils.getCurrDate()));
		for(int i=0;i<serviceList.size();i++){ 
			//过滤已经申请且在有效期内的数据
			serviceList.get(i).setStatus(Constants.UNUSABLE);
			dao.update(serviceList.get(i));
		} 
	}
}
