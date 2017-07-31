package com.wonders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.db.entity.Xdb_files;
import com.wonders.db.service.DocService;
import com.wonders.home.service.QueryBaseInfo;
import com.wonders.jkfw.entity.UserService;
import com.wonders.log.entity.LoginLog;
import com.wonders.tiles.authority.entity.Authority;
import com.wonders.tiles.authority.entity.Role;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.authority.service.SystemService;
import com.wonders.tiles.dic.DicDataUtils;
import com.wonders.tiles.extend.filter.SessionFilter;
import com.wonders.tiles.logger.entity.LoggerPro;
import com.wonders.utils.CookieUtils;

@Filters
@IocBean(fields = "dao")
public class LoginAt {

	private Log log = Logs.get();
	private Dao dao;
	
	
	@Inject
	private SystemService systemService;
	@Inject
	private DocService docService;
	@Inject
	private QueryBaseInfo queryBaseInfo;

	@At("/main/*")
	public View to(@Param("jspName") String jspName) {
		return new ViewWrapper(new JspView("jsp.authority.easyui.main."
				+ jspName), null);
	}

	/**
	 * 查询列表方法
	 * 
	 * @param displayName
	 * @return
	 * @return
	 */
	@At("/login")
	// @Ok("jsp:jsp.user.list")
	// @Ok("redirect:/sign/toSign")
	public View login(String loginName, String password, String type,
			HttpSession session, LoggerPro loggerPro,String biaoshi,
			HttpServletRequest request, HttpServletResponse response) {
		LoginLog loginLog=new LoginLog();
		loginLog.setOperateDate(new Date());
		loginLog.setOperateType("登录");
		loginLog.setOperateUser(loginName);
		loginLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		String errMsg = "";
		removeAllAttributes(session);
		User user = systemService.login(loginName, password);
		if (null == user) {
			errMsg = "输入的用户密码错误或用户不存在！";
			log.infof("[%s]用户登录失败%s", loginName,
					Times.format(new SimpleDateFormat(), Times.now()));
			loginLog.setOperateResult("失败");
			dao.insert(loginLog);
			return new ViewWrapper(new JspView("index_normal.jsp"), errMsg); // 带提示信息，跳转到登录页面
		}
		loginLog.setOperateResult("成功");
		dao.insert(loginLog);
		session.setAttribute(SystemConstants.SYSTEM_USER, user);

		int expiry = 60 * 60 * 24 * 365; // 过期时间30天
		CookieUtils.addCookie(request, response, SessionFilter.LOGON_NAME,
				loginName, expiry, null);
		CookieUtils.addCookie(request, response, SessionFilter.DISPLAY_NAME,
				user.getDisplayName(), expiry, null);

		this.setAuthToSession(session, user); // 把用户的权限信息存入sesssion

		session.setAttribute(SessionFilter.LOGON_NAME, user.getLogonName());
		session.setAttribute(SessionFilter.BS,biaoshi);
		session.setAttribute(SessionFilter.DISPLAY_NAME, user.getDisplayName());
		return new ServerRedirectView("/toIndex");

	}

	@At("/login_Public")
	public Map<String, Object> login_Public(String loginName, String password, String type,
			HttpSession session, LoggerPro loggerPro,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		LoginLog loginLog=new LoginLog();
		loginLog.setOperateDate(new Date());
		loginLog.setOperateType("登录");
		loginLog.setOperateUser(loginName);
		loginLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		String errMsg = "";
		removeAllAttributes(session);
		User user = systemService.login(loginName, password);
		if (null == user) {
			errMsg = "输入的用户密码错误或用户不存在！";
			log.infof("[%s]用户登录失败%s", loginName,
					Times.format(new SimpleDateFormat(), Times.now()));
			loginLog.setOperateResult("失败");
			dao.insert(loginLog);
			result.put("mage", errMsg);
		}
		loginLog.setOperateResult("成功");
		dao.insert(loginLog);
		session.setAttribute(SystemConstants.SYSTEM_USER, user);

		int expiry = 60 * 60 * 24 * 365; // 过期时间30天
		CookieUtils.addCookie(request, response, SessionFilter.LOGON_NAME,
				loginName, expiry, null);
		CookieUtils.addCookie(request, response, SessionFilter.DISPLAY_NAME,
				user.getDisplayName(), expiry, null);

		this.setAuthToSession(session, user); // 把用户的权限信息存入sesssion

		session.setAttribute(SessionFilter.LOGON_NAME, user.getLogonName());
		session.setAttribute(SessionFilter.DISPLAY_NAME, user.getDisplayName());
		result.put("iii", 1);
		result.put("user", user);
		result.put("LogonName", user.getLogonName());
		result.put("DisplayName", user.getDisplayName());
		return result;

	}

	
	
	@At
	public View toDreamHomeLogin() {
		return new ServerRedirectView("jsp/main");
	}
	@At
	@Ok("jsp:jsp.main")
	@Filters
	public Map<String, Object> main(String provideDepartment, String keyWord, String orderBy, String isSy,HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session
		.getAttribute(SystemConstants.SYSTEM_USER);
		if(user == null){
			// 清理session
			if (session != null) {
				Enumeration<?> enu = session.getAttributeNames();
				while (enu.hasMoreElements()) {
					session.removeAttribute((String) enu.nextElement());
				}
			}
			CookieUtils.cancleCookie(request, response, SessionFilter.LOGON_NAME, null);
			CookieUtils.cancleCookie(request, response, SessionFilter.DISPLAY_NAME,	null);
		}
		result.put("user", user);
		return result;
	}
	
	@At
	@Ok("jsp:jsp.update_pass")
	@Filters
	public Map<String, Object> update_pass(String provideDepartment, String keyWord, String orderBy, String isSy,HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session
		.getAttribute(SystemConstants.SYSTEM_USER);
		result.put("user", user);
		return result;
	}
	
	/**
	 * 修改密码
	 */
	@At
	@Ok("json")
	@Filters
	public Map<String, Object> checkUser(String logonName,String password,String newPassword){
		Map<String, Object> result = new HashMap<String, Object>();
		Criteria cri=Cnd.cri();
		cri.where().and("state","=","1");
		cri.where().and("logonName", "=", logonName);
		cri.where().and("password","=",password);
		User user = dao.fetch(User.class, cri);
		if(user!=null){
			user.setPassword(newPassword);
			dao.update(user);
			result.put("result", 1);
		}else{
			result.put("result", 0);
		}
		return result;
	}

	@At("/logout")
	public View logout(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		if(user != null){
			LoginLog loginLog=new LoginLog();
			loginLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			loginLog.setOperateDate(new Date());
			loginLog.setOperateResult("成功");
			loginLog.setOperateType("登出");
			loginLog.setOperateUser(user.getDisplayName());
			dao.insert(loginLog);
		}
		removeAllAttributes(session);
		// return new ViewWrapper(new JspView("/index"), null);
		CookieUtils.cancleCookie(request, response, SessionFilter.LOGON_NAME,
				null);
		CookieUtils.cancleCookie(request, response, SessionFilter.DISPLAY_NAME,
				null);

		return new ServerRedirectView("/home/toIndex");
	}
	@At("/logout1")
	public Map<String, Object>  logout1(HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		if(user != null){
			LoginLog loginLog=new LoginLog();
			loginLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			loginLog.setOperateDate(new Date());
			loginLog.setOperateResult("成功");
			loginLog.setOperateType("登出");
			loginLog.setOperateUser(user.getDisplayName());
			dao.insert(loginLog);
		}
		removeAllAttributes(session);
		// return new ViewWrapper(new JspView("/index"), null);
		CookieUtils.cancleCookie(request, response, SessionFilter.LOGON_NAME,
				null);
		CookieUtils.cancleCookie(request, response, SessionFilter.DISPLAY_NAME,
				null);
		 boolean message=true;
		 result.put("fo", message);

		return result;
	}

	/**
	 * 登录成功后展示主页面
	 * 
	 * @param session
	 * @return
	 */
	@Fail("jsp:/cj/500")
	@At
	public View home(HttpSession session, String type) {
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		if (null == user) {
			String errMsg = "请先登录应用系统";
			return new ViewWrapper(new JspView("/login"), errMsg); // 带提示信息，跳转到登录页面
		}
		if ("1".equals(type)) {
			return new ServerRedirectView("/main");
		}
		return new ServerRedirectView("index_normal.jsp");
		// return new ViewWrapper(new JspView("jsp.index"), null);
	}

	

	/**
	 * 清除Session所有信息
	 * 
	 * @param session
	 */
	private void removeAllAttributes(HttpSession session) {
		if (session == null)
			return;
		Enumeration<?> enu = session.getAttributeNames();
		while (enu.hasMoreElements()) {
			session.removeAttribute((String) enu.nextElement());
		}
	}

	/**
	 * 把用户的菜单权限和按钮权限存入session
	 * 
	 * @param <T>
	 * 
	 * @param session
	 * @param authCods
	 */
	private void setAuthToSession(HttpSession session, User user) {
		List<Authority> authorities = user.getAuthorities();
		Set<Authority> set = new HashSet<Authority>(authorities);
		List<Role> roles = user.getRoles();
		if (roles != null) {
			for (Role role : roles) {
				systemService.load(role);
				List<Authority> authorities2 = role.getAuthorities();
				if (authorities2 != null)
					set.addAll(authorities2);

			}
		}
		List<Authority> submenu = new ArrayList<Authority>();
		if (authorities != null) {
			for (Authority menu : set) {
				if ("2".equals(menu.getNodeLevel())) {
					submenu.add(menu);
				}
			}
			set.removeAll(submenu);
		}
		List<Authority> menuSort = new ArrayList<Authority>(set);
		Collections.sort(menuSort, new Comparator<Authority>() {
			@Override
			public int compare(Authority o1, Authority o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});

		Collections.sort(submenu, new Comparator<Authority>() {
			@Override
			public int compare(Authority o1, Authority o2) {
				String auth1 = o1.getNodeCode() + o1.getOrder();
				String auth2 = o2.getNodeCode() + o2.getOrder();
				return auth1.compareTo(auth2);
			}

		});
		session.setAttribute("menu", menuSort);
		session.setAttribute("submenu", submenu);
	}

	// 去除文件后缀名
	private void removesuffix(List<Xdb_files> list) {
		for (Xdb_files files : list) {
			String name = files.getFilenamelocal();
			int index = name.lastIndexOf('.');
			int len = name.length();
			if ( len > 20){
				files.setFilenamelocal(name.substring(0, 19)+"...");
			}else{
			files.setFilenamelocal(name.substring(0, index));
			}
		}
	}

	private List<UserService> getUserServices(String userid) {
		Criteria cri = Cnd.cri();
		cri.where().and("USER_ID", "=", userid);
		cri.getOrderBy().desc("START_TIME");
		Pager pager = dao.createPager(0, 15);
		List<UserService> result = dao.query(UserService.class, cri,
				pager);
		return result;
	}
	@At
	public View toIndex(HttpSession session,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		if(user==null)
		{
			String errMsg = "请先登录应用系统";
			result.put("errMsg", errMsg);
			result.put("user", user);
			return new ViewWrapper(new JspView("index_normal"), result); // 带提示信息，跳转到登录页面
		}
		else if (user.getSuperadmin()!=null&&user.getSuperadmin().equals("1")) {
			return new ViewWrapper(new JspView("index_sa"), null);
		}
		else if (user.getAdmin()!=null&&user.getAdmin().equals("1")||session.getAttribute(SessionFilter.BS)!=null) {
			List<Xdb_files> xdb_files = docService.getxdb_file();// 规范文档信息。
			removesuffix(xdb_files);
			if (xdb_files.size() > 3)// 只放3条数据
			{
				result.put("doc", xdb_files.subList(0, 3));
			} else {
				result.put("doc", xdb_files);
			}
			result.put("dept", DicDataUtils.getInstance().getDicData(Constants.DIC_DEPT_NAME, user.getDept()));
			return new ViewWrapper(new ServerRedirectView("/main"), result);
		}else{
			result = queryBaseInfo.getBaseInfo(request);
			List<Xdb_files> xdb_files = docService.getxdb_file();// 规范文档信息。
			removesuffix(xdb_files);
			if (xdb_files.size() > 5)// 只放5条数据
			{
				result.put("doc", xdb_files.subList(0, 5));
			} else {
				result.put("doc", xdb_files);
			}
			
			result.put("user", user);
			result.put("webservices", getUserServices(user.getUserId()));
			result.put("nowtime", new Date());
			result.put("dept", DicDataUtils.getInstance().getDicData(Constants.DIC_DEPT_NAME, user.getDept()));
//			return new ViewWrapper(new JspView("index_pt"), result);
			return new ViewWrapper(new JspView("index_normal"), result);
			
		}
		
	}
}
