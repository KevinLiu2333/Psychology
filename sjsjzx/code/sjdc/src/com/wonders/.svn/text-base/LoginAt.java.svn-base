package com.wonders;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.wonders.wddc.config.SessionFilter;
import com.wonders.wddc.suite.logger.entity.LogLoginBo;
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


    @Inject
    private Dao dao;


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
        removeAllAttributes(session);
        boolean isSuccess = this.login(logonName, password);
        if (!isSuccess) {
            String errMsg = "输入的用户密码错误或用户不存在！";
            log.infof("[%s]用户登录失败%s", logonName, Times.format(new SimpleDateFormat(), Times.now()));
            Map<String,Object>  result= new HashMap<String,Object>();
            result.put("errMsg", errMsg);
            return new ViewWrapper(new JspView("/index"), result); // 带提示信息，跳转到登录页面
        }
        //获取用户信息
        User user = this.getUser(logonName);
        //设置session和addCookie信息
        session.setAttribute(SessionFilter.SESSION_USER, user);
        int expiry = 60 * 60 * 24 * 365; // 过期时间30天
        CookieUtils.addCookie(request, response, SessionFilter.LOGON_NAME, logonName, expiry, null);
        CookieUtils.addCookie(request, response, SessionFilter.DISPLAY_NAME, user.getDisplayName(), expiry, null);
		
       //跳转到其他入口函数中
        if(Strings.isNotBlank(request.getParameter("jumpUrl"))){
        	return new ServerRedirectView(request.getParameter("jumpUrl"));
        }
        return new ServerRedirectView("/open.jsp");
    }

    @At("/logout")
    public View logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
    	User user = (User) session.getAttribute(SessionFilter.SESSION_USER);
    	if(user == null){
            Map<String,Object>  result= new HashMap<String,Object>();
            result.put("errMsg", "会话过期！");
    		 return new ViewWrapper(new JspView("/login"), result); // 带提示信息，跳转到登录页面
    	}
        removeAllAttributes(session);
        CookieUtils.cancleCookie(request, response, SessionFilter.LOGON_NAME, null);
        CookieUtils.cancleCookie(request, response, SessionFilter.DISPLAY_NAME, null);
        return new ServerRedirectView("/login.jsp");
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
     * 将登入信息保存到数据库中
     */
    public void saveLogin(LogLoginBo loginInfo){
    	dao.insert(loginInfo);
    }
    
    
    public boolean login(String logonName, String password) {

        return true;
    }

    public User getUser(String logonName) {
        User user = new User();
        user.setLogonName(logonName);
        if("admin".equals(logonName)){
            user.setDisplayName("科学技术委员会");
            user.setUserId("DT_05");
            user.setUnitId("111");
            user.setUnitName("科学技术委员会");
            user.setUserType("管理员");
        }else{
            user.setUserId("100");
            user.setUnitId("222");
            user.setDisplayName("民政局");
            user.setUnitName("民政局");
            user.setUserType("普通用户");
        }
        return user;
    }

}
