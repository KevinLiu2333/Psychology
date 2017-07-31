package com.wondersgroup.wssip.application.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wondersgroup.cmc.commons.monitor.model.UserMonitorInfo;
import com.wondersgroup.cmc.commons.monitor.service.UserMonitorService;
import com.wondersgroup.cmc.model.bo.Organ;
import com.wondersgroup.cmc.model.dto.LeftMenuDTO;
import com.wondersgroup.cmc.model.dto.OrganDTO;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.struts2.interceptor.LoginInterceptor;
import com.wondersgroup.framework.core5.business.context.Environment;
import com.wondersgroup.framework.core5.business.context.UserContext;
import com.wondersgroup.framework.core5.business.context.support.BusinessContextUtils;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.commons.dao.CommonHibernateDaoUtils;
import com.wondersgroup.wssip.model.bo.UaAuthInfo;
import com.wondersgroup.wssip.model.bo.UaUserInfo;
import com.wondersgroup.wssip.util.CollectionTools;
import com.wondersgroup.wssip.util.StringTools;
public class LoginUtils{
	private static UserMonitorService userMonitorService;
	
	public void setUserMonitorService(UserMonitorService userMonitorService) {
		LoginUtils.userMonitorService = userMonitorService;
	}
	
	/**
	 * 正常登录
	 * 
	 * @param securityUser
	 */
/*	public static void login(Operator operator,HttpServletRequest request){
		UserMonitorInfo userMonitorInfo = new UserMonitorInfo();
		createBusinessContext(operator,request,userMonitorInfo);
		userMonitorService.userLogin(userMonitorInfo);
	}*/
	
	public static void login(UaUserInfo securityUser,HttpServletRequest request){
		UserMonitorInfo userMonitorInfo = new UserMonitorInfo();
		createBusinessContext(securityUser,request,userMonitorInfo);
		userMonitorService.userLogin(userMonitorInfo);
	}
	
	public static void queryPortalLoadArray(UaUserInfo securityUser,HttpServletRequest request){
		List<Map<String, String>> portalLoadArray = userMonitorService.queryPortalLoadArray(securityUser);
		if(portalLoadArray!=null){
			request.getSession().setAttribute(SessionConstants.PROTAL_LOAD_ARRAY, portalLoadArray);
		}
	}
	
	public static void queryAppids(UaUserInfo securityUser,HttpServletRequest request){
		String Appids = userMonitorService.queryAppids(securityUser);
		if(StringTools.hasText(Appids)){
			request.getSession().setAttribute(SessionConstants.APPIDS, Appids);
		}
	}
	
/*	public static void Logout(Operator operator,String sessionid){
		UserMonitorInfo userMonitorInfo = new UserMonitorInfo();
		userMonitorInfo.setUserid(String.valueOf(operator.getId()));
		userMonitorInfo.setUserName(operator.getName());
		userMonitorInfo.setLoginName(operator.getLoginName());
		userMonitorInfo.setSessionid(sessionid);
		userMonitorService.userLogout(userMonitorInfo);
	}*/
	
	public static void Logout(UaUserInfo securityUser,String sessionid){
		UserMonitorInfo userMonitorInfo = new UserMonitorInfo();
		userMonitorInfo.setUserid(String.valueOf(securityUser.getUserid()));
		userMonitorInfo.setUserName(securityUser.getDisplayname());
		userMonitorInfo.setLoginName(securityUser.getLoginname());
		userMonitorInfo.setSessionid(sessionid);
		userMonitorService.userLogout(userMonitorInfo);
	}
	
	public static boolean CheckLoginStatus(String userid,String sessionid){
		return userMonitorService.checkStatus(userid, sessionid);
	}
	/**
	 * 设置上下文
	 * @param securityUser
	 */
/*	public static void createBusinessContext(Operator operator,HttpServletRequest request,UserMonitorInfo userMonitorInfo){
		String macAddress = "00-00-00-00-00-00";
		String ipAddress = "0.0.0.0";
		
		//获得权限所属机构信息
		OrganDTO operateOrgan = LoginUtils.getOrganinfo(operator.getOrganCode());
		if(operateOrgan==null){
			throw new BusinessException("无法获取您所属经办机构信息");
		}
		
		// 设置应用上下文
		UserContext userContext = BusinessContextUtils.getUserContext();
		userContext.setOperatorId(String.valueOf(operator.getId()));
		userContext.setOperatorName(operator.getLoginName());
		Environment environment = BusinessContextUtils.getEnvironment();
		environment.setIpAddress(macAddress);
		environment.setMacAddress(ipAddress);
		// 设置Session
		request.getSession().setAttribute(LoginInterceptor.SECURITY_LOGIN_NAME, operator.getLoginName());
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR, operator);
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_ID, String.valueOf(operator.getId()));
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_NAME, operator.getName());
		//权限所属
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_DEPARTMENTNAME, operateOrgan.getOrganname());
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_DEPARTMENT, operateOrgan.getOrgancode());
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_STREET, operateOrgan.getAddress());
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_REGION, operateOrgan.getOrganregion());
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_TYPE, operateOrgan.getOrgantype());
		if(StringTools.hasText(operateOrgan.getServicestype())){
			request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_SERVICE_TYPE, operateOrgan.getServicestype());
		}
		
		BusinessContextUtils.setUserContext(userContext);
		BusinessContextUtils.setEnvironment(environment);
		
		userMonitorInfo.setUserid(String.valueOf(operator.getId()));
		userMonitorInfo.setUserName(operator.getName());
		userMonitorInfo.setLoginName(operator.getLoginName());
		userMonitorInfo.setRemoteAddr(macAddress);
		userMonitorInfo.setMacAddress(ipAddress);
		userMonitorInfo.setSessionid(request.getSession().getId());
	}*/
	
	
	public static void createBusinessContext(UaUserInfo user,HttpServletRequest request,UserMonitorInfo userMonitorInfo){
		String macAddress = "00-00-00-00-00-00";
		String ipAddress = "0.0.0.0";
		
		// 设置应用上下文
		UserContext userContext = BusinessContextUtils.getUserContext();
		userContext.setOperatorId(String.valueOf(user.getUserid()));
		userContext.setOperatorName(user.getLoginname());
		Environment environment = BusinessContextUtils.getEnvironment();
		environment.setIpAddress(macAddress);
		environment.setMacAddress(ipAddress);
		// 设置Session
		request.getSession().setAttribute(LoginInterceptor.SECURITY_LOGIN_NAME, user.getLoginname());
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR, user);
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_ID, String.valueOf(user.getUserid()));
		request.getSession().setAttribute(SessionConstants.WSSIP_OPERATOR_NAME, user.getDisplayname());
		
		BusinessContextUtils.setUserContext(userContext);
		BusinessContextUtils.setEnvironment(environment);
		
		userMonitorInfo.setUserid(String.valueOf(user.getUserid()));
		userMonitorInfo.setUserName(user.getDisplayname());
		userMonitorInfo.setLoginName(user.getLoginname());
		userMonitorInfo.setRemoteAddr(macAddress);
		userMonitorInfo.setMacAddress(ipAddress);
		userMonitorInfo.setSessionid(request.getSession().getId());
	}
	
	/**
	 * 根据机构代码获得机构信息
	 * oragncode 机构代码
	 * organname 机构名称
	 * 借用parentid  总中心代码
	 * organregion 区代码
	 * 借用address 街道代码	
	 * servicestype  服务点类别
	 * @param organcode
	 * @return
	 */
	public static OrganDTO getOrganinfo(String organcode){
		if(StringTools.isEmpty(organcode)){
			return null;
		}
		OrganDTO organDTO = new OrganDTO();
		Organ organ = CommonHibernateDaoUtils.load(Organ.class, organcode);
		if(organ == null){
			return null;
		} else {
			organDTO.setOrgancode(organ.getOrgancode());//机构代码
			organDTO.setOrganname(organ.getOrganname());//机构名称
			organDTO.setOrgantype(organ.getOrgantype());//机构类别
			//除分中心、代办点外，其他parentid为自身id
			if("4".equals(organDTO.getOrgantype())){
				if(StringTools.hasText(organDTO.getServicestype()) && !"1".equals(organDTO.getServicestype())){

				} else {
					organDTO.setParentid(organDTO.getOrgancode());
				}
			} else {
				organDTO.setParentid(organDTO.getOrgancode());
			}
			//设置区代码、街道代码
			try{
				organDTO.setOrganregion(organDTO.getOrgancode().substring(0, 6)+"000000");
				organDTO.setAddress(organDTO.getOrgancode().substring(0, 12));
			} catch (Exception e) {
				throw new BusinessException("机构编码["+organDTO.getOrgancode()+"]不符合编码规则");
			}
		}
		return organDTO;
	}

	/**
	 * 构造菜单分类数据
	 * @param servletRequest
	 */
/*	@SuppressWarnings("unchecked")
	public static void initMenu(HttpServletRequest request) {
		List<BaseUapMenuResource> menuResources = null;
		try {
			menuResources = (List<BaseUapMenuResource>) request.getSession().getAttribute(SessionConstants.MENU_DEFINE_CACHE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<LeftMenuDTO> menuList = new ArrayList<LeftMenuDTO>();
		List<LeftMenuDTO> secondMenuList = new ArrayList<LeftMenuDTO>();
		List<LeftMenuDTO> thirdMenuList = new ArrayList<LeftMenuDTO>();
		
		//生成一级菜单
		List<BaseUapMenuResource> list = LoginUtils.getChildMenus(menuResources, SessionConstants.ROOTMENUID);
		if(CollectionUtils.isNotEmpty(list)){
			for(BaseUapMenuResource menu : list){
				LeftMenuDTO leftMenuDTO = new LeftMenuDTO();
				leftMenuDTO.setId(menu.getMenuid().toString());
				leftMenuDTO.setPid(menu.getParentmenuid().toString());
				leftMenuDTO.setName(menu.getName());
				leftMenuDTO.setUrl(menu.getUrl());
				leftMenuDTO.setIconCls(menu.getIcon1().toString());
				menuList.add(leftMenuDTO);
			}
		}
		
		//生成二级菜单
		for(LeftMenuDTO menu : menuList){
			list = LoginUtils.getChildMenus(menuResources, menu.getId());
			if(CollectionUtils.isNotEmpty(list)){
				for(BaseUapMenuResource secondmenu : list){
					LeftMenuDTO leftMenuDTO = new LeftMenuDTO();
					leftMenuDTO.setId(secondmenu.getMenuid().toString());
					leftMenuDTO.setPid(secondmenu.getParentmenuid().toString());
					leftMenuDTO.setName(secondmenu.getName());
					leftMenuDTO.setUrl(secondmenu.getUrl());
					leftMenuDTO.setIconCls(secondmenu.getIcon1().toString());
					secondMenuList.add(leftMenuDTO);
				}
			}
		}
		
		//生成三级菜单
		for(LeftMenuDTO menu : secondMenuList){
			list = LoginUtils.getChildMenus(menuResources, menu.getId());
			if(CollectionUtils.isNotEmpty(list)){
				for(BaseUapMenuResource thirdmenu : list){
					LeftMenuDTO leftMenuDTO = new LeftMenuDTO();
					leftMenuDTO.setId(thirdmenu.getMenuid().toString());
					leftMenuDTO.setPid(thirdmenu.getParentmenuid().toString());
					leftMenuDTO.setName(thirdmenu.getName());
					leftMenuDTO.setUrl(thirdmenu.getUrl());
					leftMenuDTO.setIconCls(thirdmenu.getIcon1().toString());
					thirdMenuList.add(leftMenuDTO);
				}
			}
		}
		
		//缓存三级菜单
		request.getSession().setAttribute(SessionConstants.MENULIST, menuList);
		request.getSession().setAttribute(SessionConstants.SECONDMENULIST, secondMenuList);
		request.getSession().setAttribute(SessionConstants.THIRDMENULIST, thirdMenuList);
	}*/
	
	
	@SuppressWarnings("unchecked")
	public static void initMenu(HttpServletRequest request) {
		List<UaAuthInfo> menuResources = null;
		try {
			menuResources = (List<UaAuthInfo>) request.getSession().getAttribute(SessionConstants.MENU_DEFINE_CACHE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<LeftMenuDTO> menuList = new ArrayList<LeftMenuDTO>();
		List<LeftMenuDTO> secondMenuList = new ArrayList<LeftMenuDTO>();
		List<LeftMenuDTO> thirdMenuList = new ArrayList<LeftMenuDTO>();
		
		//生成一级菜单
		List<UaAuthInfo> list = LoginUtils.getChildMenus(menuResources, SessionConstants.ROOTMENUID);
		if(CollectionTools.isNotEmpty(list)){
			for(UaAuthInfo menu : list){
				LeftMenuDTO leftMenuDTO = new LeftMenuDTO();
				leftMenuDTO.setId(menu.getNodeid().toString());
				leftMenuDTO.setPid(menu.getParentnodeid().toString());
				leftMenuDTO.setName(menu.getNodename());
				leftMenuDTO.setUrl(menu.getUrl());
				leftMenuDTO.setIconCls(menu.getIcon1());
				menuList.add(leftMenuDTO);
			}
		}
		
		//生成二级菜单
		for(LeftMenuDTO menu : menuList){
			list = LoginUtils.getChildMenus(menuResources, menu.getId());
			if(CollectionTools.isNotEmpty(list)){
				for(UaAuthInfo secondmenu : list){
					LeftMenuDTO leftMenuDTO = new LeftMenuDTO();
					leftMenuDTO.setId(secondmenu.getNodeid().toString());
					leftMenuDTO.setPid(secondmenu.getParentnodeid().toString());
					leftMenuDTO.setName(secondmenu.getNodename());
					leftMenuDTO.setUrl(secondmenu.getUrl());
					leftMenuDTO.setIconCls(secondmenu.getIcon1());
					secondMenuList.add(leftMenuDTO);
				}
			}
		}
		
		//生成三级菜单
		for(LeftMenuDTO menu : secondMenuList){
			list = LoginUtils.getChildMenus(menuResources, menu.getId());
			if(CollectionTools.isNotEmpty(list)){
				for(UaAuthInfo thirdmenu : list){
					LeftMenuDTO leftMenuDTO = new LeftMenuDTO();
					leftMenuDTO.setId(thirdmenu.getNodeid().toString());
					leftMenuDTO.setPid(thirdmenu.getParentnodeid().toString());
					leftMenuDTO.setName(thirdmenu.getNodename());
					leftMenuDTO.setUrl(thirdmenu.getUrl());
					leftMenuDTO.setIconCls(thirdmenu.getIcon1());
					thirdMenuList.add(leftMenuDTO);
				}
			}
		}
		
		//缓存三级菜单
		request.getSession().setAttribute(SessionConstants.MENULIST, menuList);
		request.getSession().setAttribute(SessionConstants.SECONDMENULIST, secondMenuList);
		request.getSession().setAttribute(SessionConstants.THIRDMENULIST, thirdMenuList);
	}
	
	
	private static List<UaAuthInfo> getChildMenus(List<UaAuthInfo> menuResources,String pid){
		List<UaAuthInfo> dest = new ArrayList<UaAuthInfo>();
		if(menuResources!=null){
			CollectionTools.select(menuResources, "parentnodeid", Long.parseLong(pid), dest);
		}
		if(CollectionTools.isNotEmpty(dest)){
			LoginUtils.sortList(dest);
		}
		return dest;
	}
	
	/**
	   * 集合排序
	   * @param dest
	*/
	private static void sortList(List<UaAuthInfo> dest){
		Collections.sort(dest,new Comparator<UaAuthInfo>() {
			@Override
			public int compare(UaAuthInfo o1,
					UaAuthInfo o2) {
				Long p1 = o1.getNodeorder()!=null?o1.getNodeorder():01;
				Long p2 = o2.getNodeorder()!=null?o2.getNodeorder():01;
				return p1.compareTo(p2);
			}
		});
	}
}
