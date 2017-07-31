package com.wonders;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.wddc.config.OnlineUserListener;
import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.tiles.jk.UserService;
import com.wonders.wddc.tiles.jk.entity.User;
import com.wonders.wddc.tiles.tools.CookieUtils;
/**
 * 用户登录 .
 *
 */
@Filters
@IocBean
public class LoginAt {

    private Log log = Logs.get();

    @Inject("refer:userService")
    private UserService userService;

    @Inject
    private Dao dao;
    
    @Inject
    private LogCoreService logservice;

    /**
     * 用户登录方法
     * @param logonName 登录名称
     * @param password 密码
     * @param session session
     * @param request 请求
     * @param response 回应
     * @return
     */
    @At("/login")
    public View login(String logonName, String password,HttpSession session,HttpServletRequest request, HttpServletResponse response) {
    	//登陆对象用于存储登陆登出信息
    	String errMsg = "";
        removeAllAttributes(session);
        boolean isSuccess = userService.login(logonName, password);
        if (!isSuccess) {
            errMsg = "输入的用户密码错误或用户不存在！";
            log.infof("[%s]用户登录失败%s", logonName, Times.format(new SimpleDateFormat(), Times.now()));
            //设置登陆日志信息
            logservice.insertLoginErrorLog(request, logonName, password, errMsg);
            //存入数据库

            Map<String,Object>  result= new HashMap<String,Object>();
            result.put("errMsg", errMsg);
            return new ViewWrapper(new JspView("/WEB-INF/ypsjzx/index"), result); // 带提示信息，跳转到登录页面
        }
        //获取用户信息
        User user = userService.getUser(logonName);
        //设置session和addCookie信息
        session.setAttribute(SessionFilter.SESSION_USER, user);
        int expiry = 60 * 60 * 24 * 365; // 过期时间30天
        CookieUtils.addCookie(request, response, SessionFilter.LOGON_NAME, logonName, expiry, null);
        CookieUtils.addCookie(request, response, SessionFilter.DISPLAY_NAME, user.getDisplayName(), expiry, null);
		//添加日志信息
        OnlineUserListener onlieUser = new OnlineUserListener(user);
        session.setAttribute("Listener", onlieUser);
        //logservice.insertOpLog("xt", "xt001", request);
       //跳转到其他入口函数中
        String jumpUrl = request.getParameter("jumpUrl");
        if(Strings.isNotBlank(request.getParameter("jumpUrl"))){
        	return new ServerRedirectView(request.getParameter("jumpUrl"));
        }
        return new ServerRedirectView("/into?jspPath=home");
    }

    @At("/logout")
    public View logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    	User user = (User) session.getAttribute(SessionFilter.SESSION_USER);
    	//logservice.insertOpLog("xt", "xt002", request);
    	if(user == null){
            Map<String,Object>  result= new HashMap<String,Object>();
            result.put("errMsg", "会话过期！");
    		 return new ViewWrapper(new JspView("/WEB-INF/ypsjzx/index"), result); // 带提示信息，跳转到登录页面
    	}
    	//添加日志信息
        
        removeAllAttributes(session);
        
        CookieUtils.cancleCookie(request, response, SessionFilter.LOGON_NAME, null);
        CookieUtils.cancleCookie(request, response, SessionFilter.DISPLAY_NAME, null);
        return new ServerRedirectView("/");
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

    

}
