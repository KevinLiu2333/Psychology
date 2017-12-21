package com.wonders.tiles.extend.setup;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;

public class SessionFilter implements ActionFilter {
	
	
	public SessionFilter() {}
	
	
	@Override
	public View match(ActionContext context) {
		HttpSession session = Mvcs.getHttpSession(false);
    	//获取user
    	User sessionUser = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
        if (null == sessionUser){
        	return new ViewWrapper(new JspView("/timeout.jsp"), "会话过期，请重新登录"); 
        }
        //正确时候返回null	
        return null;
	}
	

}
