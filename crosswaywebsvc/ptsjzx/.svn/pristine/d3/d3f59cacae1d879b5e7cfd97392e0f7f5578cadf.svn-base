package com.wonders.jkfw.at;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.wonders.jkfw.entity.UserService;
import com.wonders.jkfw.entity.Webservice;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.extend.adaptor.util.ConUtils;
import com.wonders.utils.DateUtils;

@At("/apifw")
@IocBean(fields = "dao")
public class ApifwAt {
private Dao dao;
	
	@At
	@Ok("jsp:jsp.fw.fw_api")
	public Object toApiList(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		map.put("list", list);
		return map;
	}
	
	@At
	public View open(String openId, String chestr, String apiType) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(openId, openId);
		result.put("chestr", chestr);
		result.put("apiType", apiType);
		return new ViewWrapper(new JspView("jsp.fw.open"), result);
		
	} 
	@At
	public View view(String openId) throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		result.put(openId, openId);
		return new ViewWrapper(new JspView("jsp.fw.view_"+ openId), result);
		
	} 
	@At
	public View xieyi() throws UnsupportedEncodingException{
		Map<String, Object> result=new HashMap<String, Object>();
		return new ViewWrapper(new JspView("jsp.fw.xieyi"), result);
		
	} 
	@At
	@Ok("jsp:jsp.jkfw.success")
	public View saveFw(String chestr, HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		UserService service = new UserService();
		service.setUserId(user.getUserId());
		service.setApplyInfo(chestr);
		service.setStartTime(new Date());
		dao.insert(service); 
		return new ViewWrapper(new JspView("jsp.jkfw.success"), result);
	}
	/**
	 * api服务申请edit页面.
	 */
	@At
	@Ok("json")
	public View toApiApply(@Param("::userService.") UserService userService){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("userService", userService);
		return new ViewWrapper(new JspView("jsp.fw.api_apply"), result);
	}
	
	/**
	 * 保存api服务申请.
	 */
	@At
	@Ok("redirect:/apifw/toApiList")
	public void saveApiApply(@Param("::userService.") UserService userService, HttpSession session){
		Date startTime = new Date();
		Date endTime = DateUtils.addDays(startTime, userService.getActiveTime());
		userService.setStartTime(startTime);
		userService.setEndTime(endTime);
		if(Constants.FRDJ_INFO_YW.equals(userService.getType())){
			userService.setServiceName(Constants.FRDJ_INFO_ZW);
		}else if(Constants.FRZZ_INFO_YW.equals(userService.getType())){
			userService.setServiceName(Constants.FRZZ_INFO_ZW);
		}else if(Constants.FRJG_INFO_YW.equals(userService.getType())){
			userService.setServiceName(Constants.FRJG_INFO_ZW);
		}else{ 
			userService.setServiceName("未知");
		}
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		userService.setUserId(user.getUserId());
		userService.setUserName(getUserNameById(user.getUserId()));
		userService.setStatus(Constants.AUDIT_NO); 
		dao.insert(userService);
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
	 * 数据服务接口审核edit页面.
	 */
	@At
	@Ok("jsp:jsp.fw.api_check")
	public Map<String, Object> toApiCheck(@Param("userServiceId") String userServiceId){
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
	 * 开通服务.
	 * @return
	 */
	@At
	@Ok("jsp:jsp.fw.openfw_list")
	public Object toOpenFwList(HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		//1.适配页面的查询参数
		Criteria cri =  ConUtils.makeCri(request);
		//2.适配页面的分页参数
		Pager pager = ConUtils.makePaginationPager(request);
		cri.where().andIn("status", Constants.STATUS_APPLIED,Constants.STATUS_OPENSERVICE);
		ConUtils.makePaginationOrder(request, cri, "startTime", "desc");
		List<UserService> list = dao.query(UserService.class, cri, pager);
		pager.setRecordCount(dao.count(UserService.class, cri));
		//数据对象
		result.put("rows", list);
		//分页信息
        result.put("pager",pager);
		return result;
	}
	
	/**
	 * api服务查看.
	 */
	@At
	@Ok("jsp:jsp.fw.api_view")
	public Map<String, Object> toApiView(String userServiceId){
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
	 * 开通服务.
	 */
	@At
	@Ok("json")
	public void openService(@Param("userServiceId") String userServiceId){
		if(!Strings.isEmpty(userServiceId)){
			UserService service = dao.fetch(UserService.class, userServiceId);
			if(service != null){
				service.setStatus(Constants.STATUS_OPENSERVICE);
				dao.update(service); 
			}
		}
	}
}
