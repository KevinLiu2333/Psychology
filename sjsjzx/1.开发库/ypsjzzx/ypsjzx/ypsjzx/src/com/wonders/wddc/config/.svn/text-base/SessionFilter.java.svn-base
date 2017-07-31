package com.wonders.wddc.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.wddc.suite.logger.LogManager;
import com.wonders.wddc.tiles.jk.entity.User;

public class SessionFilter implements ActionFilter {

    private String path;

    public final static String LOGON_NAME = "sys_user_name";

    public final static String DISPLAY_NAME = "sys_display_name";

    public final static String SESSION_USER = "session_user";


    public SessionFilter() {
    }

    public SessionFilter(String path) {
        this.path = path;
    }

    // 由于要从cookie中取得用户信息,对match()方法做了修改
    @Override
    public View match(ActionContext context) {
        HttpSession session = Mvcs.getHttpSession();
        //正常跳转
        User user = (User) session.getAttribute(SESSION_USER);
        if(user == null){
            Map<String,Object>  result= new HashMap<String,Object>();
            result.put("errMsg", "会话过期，请重新登录！");
            return new ViewWrapper(new JspView(path), result); // 带提示信息，跳转到登录页面
        }
        String classPath = context.getMethod().getDeclaringClass().getName();
		String methodName = context.getMethod().getName();
		if(!"com.wonders.MainModule.into".equals(classPath+"."+methodName)&&"普通用户".equals(user.getUserType())){
			if("com.wonders.wdac.at.ZyAct.toZyIndex".equals(classPath+"."+methodName)||"com.wonders.wdac.at.ZyAct.toDetail".equals(classPath+"."+methodName)){
				return null;
			}else{
				Map<String,Object>  result= new HashMap<String,Object>();
	            result.put("errMsg", "您没有此功能权限！");
	            return new ViewWrapper(new JspView(path), result); // 带提示信息，跳转到登录页面
			}
        }
        return null;
    }

}
