package com.wonders;

import com.beyondbit.netsso.client.Receipt;
import com.wonders.db.service.DocService;
import com.wonders.dddl.DddlService;
import com.wonders.jkfw.entity.UserService;
import com.wonders.log.entity.LoginLog;
import com.wonders.mlgx.entity.TSourceApply;
import com.wonders.qxkz.QXConstants;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.authority.service.SystemService;
import com.wonders.tiles.logger.entity.LoggerPro;
import com.wonders.utils.CookieUtils;
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
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Filters
@IocBean(fields = "dao")
public class LoginAt {

	private Log log = Logs.get();
	private Dao dao;
	@Inject
	private SystemService systemService;
	@Inject
	private DocService docService;

	@At("/main/*")
	public View to(@Param("jspName") String jspName) {
		return new ViewWrapper(new JspView("jsp.authority.easyui.main." + jspName), null);
	}

	/**
	 * 查询列表方法
	 * 
	 * @param displayName
	 * @return
	 * @return
	 */
	@At("/login")
	public View login(String logonName, String password, String type, HttpSession session, LoggerPro loggerPro,
			HttpServletRequest request, HttpServletResponse response) {
		logonName = "liqq";
		password = "y6u7i8o9";
		//logonName = "wangb";
		//password = "52564588";
		//logonName = "jcsjk";
		//password = "52564588";
		//logonName = "yanzh";
		//password = "52564588";
		LoginLog loginLog = new LoginLog();
		loginLog.setOperateDate(new Date());
		loginLog.setOperateType("登录");
		loginLog.setOperateUser(logonName);
		loginLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		String errMsg = "";
		removeAllAttributes(session);
		User user = systemService.login(logonName, password);
		if (null == user) {
			errMsg = "输入的用户密码错误或用户不存在！";
			log.infof("[%s]用户登录失败%s", logonName, Times.format(new SimpleDateFormat(), Times.now()));
			loginLog.setOperateResult("失败");
			dao.insert(loginLog);
			return new ViewWrapper(new JspView("/login.jsp"), errMsg); // 带提示信息，跳转到登录页面
		}
		loginLog.setOperateDept(user.getDept());
		loginLog.setOperateResult("成功");
		dao.insert(loginLog);
		session.setAttribute(SystemConstants.SYSTEM_USER, user);
		int expiry = 60 * 60 * 24 * 365; // 过期时间30天
		CookieUtils.addCookie(request, response, SystemConstants.LOGON_NAME, logonName, expiry, null);
		CookieUtils.addCookie(request, response, SystemConstants.DISPLAY_NAME, user.getDisplayName(), expiry, null);
		session.setAttribute(SystemConstants.LOGON_NAME, user.getLogonName());
		session.setAttribute(SystemConstants.DISPLAY_NAME, user.getDisplayName());
		return new ServerRedirectView("/toIndex");
	}
	
	
	@At("/Tanchuang")
	public void toTanchuang(HttpSession session) {
		session.setAttribute(SystemConstants.TC_STATE, "Y");
	}

	@At
	public View toDreamHomeLogin() {
		return new ServerRedirectView("/index");
	}

	@At("/logout")
	public View logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		LoginLog loginLog = new LoginLog();
		loginLog.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		loginLog.setOperateDate(new Date());
		loginLog.setOperateResult("成功");
		loginLog.setOperateType("登出");
		loginLog.setOperateUser(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getLogonName());
		loginLog.setOperateDept(((User) session.getAttribute(SystemConstants.SYSTEM_USER)).getDept());
		dao.insert(loginLog);
		removeAllAttributes(session);
		// return new ViewWrapper(new JspView("/index"), null);
		CookieUtils.cancleCookie(request, response, SystemConstants.LOGON_NAME, null);
		CookieUtils.cancleCookie(request, response, SystemConstants.DISPLAY_NAME, null);

		return new ServerRedirectView("/login.jsp");
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
			return new ServerRedirectView("/index");
		}
		return new ServerRedirectView("index.jsp");
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

	// 获取资源共享申请申请
	private List<TSourceApply> getTSourceApplies(String stapplierid) {
		Criteria cri = Cnd.cri();
		cri.where().and("ST_APPLIER_ID", "=", stapplierid);
		cri.getOrderBy().desc("DT_APPLY");
		Pager pager = dao.createPager(1, 5);
		List<TSourceApply> result = dao.query(TSourceApply.class, cri, pager);
		return result;
	}

	private List<UserService> getUserServices(String userid) {
		Criteria cri = Cnd.cri();
		cri.where().and("USER_ID", "=", userid);
		cri.getOrderBy().desc("START_TIME");
		Pager pager = dao.createPager(1, 5);
		List<UserService> result = dao.query(UserService.class, cri, pager);
		return result;
	}

	@At
	public View toIndex(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DddlService().goTo(request, response);
//		if (session.getAttribute(SystemConstants.SYSTEM_USER) == null) {
//			String errMsg = "请先登录应用系统";
//			return new ViewWrapper(new JspView("/login"), errMsg); // 带提示信息，跳转到登录页面
//		}
		User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
		if (user.getRoleId().contains(QXConstants.ADMIN)) {
			Receipt receipt = new Receipt();
			receipt.setUserUid("Admin");
			session.setAttribute("com.beyondbit.netsso.client.receipt", receipt);
			session.setAttribute("com.beyondbit.netsso.client.userUid", receipt.getUserUid());
			return new ViewWrapper(new JspView("admin_index"), null);
		} else if (user.getRoleId().contains(QXConstants.SUPER_A)) {
			Receipt receipt = new Receipt();
			receipt.setUserUid("Administrator");
			session.setAttribute("com.beyondbit.netsso.client.receipt", receipt);
			session.setAttribute("com.beyondbit.netsso.client.userUid", receipt.getUserUid());
			return new ViewWrapper(new JspView("index_sa"),null);
		}
		return null;
	}
}
