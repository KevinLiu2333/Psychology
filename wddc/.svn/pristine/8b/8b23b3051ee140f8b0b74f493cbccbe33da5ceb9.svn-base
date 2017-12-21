package com.wonders.wddc.config;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import org.nutz.mvc.Mvcs;

import com.wonders.wddc.suite.logger.service.LogCoreService;
import com.wonders.wddc.suite.user.service.UserInfoService;
import com.wonders.wddc.tiles.jk.entity.User;
/**
 * 用户登录登出监听器.
 * @author Administrator
 *
 */
public class OnlineUserListener implements HttpSessionBindingListener {

	 /**
	  * 登录用户对象
	  */
	 private User user;
	
	 public OnlineUserListener(User user){
	        this.user = user;
	    }
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
    	//设置在线信息
		UserInfoService userService = Mvcs.ctx().getDefaultIoc().get(UserInfoService.class);
		userService.addOnline(user.getLogonName());
        //记录登录日志
        LogCoreService logService = Mvcs.ctx().getDefaultIoc().get(LogCoreService.class);
        logService.insertOpLog("xt", "xt001",user,null);
	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
    	//设置在线信息
		UserInfoService userService = Mvcs.ctx().getDefaultIoc().get(UserInfoService.class);
		userService.removeOnline(user.getLogonName());
	    //记录登录日志
	    LogCoreService logService = Mvcs.ctx().getDefaultIoc().get(LogCoreService.class);
	    logService.insertOpLog("xt", "xt002",user,null);
	}

}
