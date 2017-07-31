package com.wonders.tiles.extend.filter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.lang.Strings;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.RawView;
import org.nutz.mvc.view.ServerRedirectView;
import org.nutz.mvc.view.ViewWrapper;

import com.wonders.tiles.authority.entity.Authority;
import com.wonders.tiles.authority.entity.Role;
import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;
import com.wonders.tiles.authority.service.SystemService;
import com.wonders.utils.CookieUtils;

public class SessionFilter implements ActionFilter {
	
	@Inject
	private SystemService systemService;

    private String path;

    public final static String LOGON_NAME = "dreamhome_user";
    public final static String BS = "biaoshi";
    public final static String DISPLAY_NAME = "dreamhome_display_name";

    private static final String JSON = "{\"statusCode\":\"301\",\"message\":\"登陆超时，请重新登陆!\",\"navTabId\":\"\",\"callbackType\":\"\",\"forwardUrl\":\"\"}";

    public SessionFilter() {
    }

    public SessionFilter(String path) {
        this.path = path;
    }

    // 由于要从cookie中取得用户信息,对match()方法做了修改
    @Override
    public View match(ActionContext context) {
        HttpSession session = Mvcs.getHttpSession();
        User user = (User) session.getAttribute(SystemConstants.SYSTEM_USER);
        if (null != user) {
            session.setAttribute(LOGON_NAME, user.getLogonName());
        } else { // 如果session中没有用户信息,则从cookie中读取
            HttpServletRequest request = Mvcs.getReq();
            systemService = Mvcs.getIoc().get(SystemService.class);
            String logonName = CookieUtils.getCookie(request, LOGON_NAME);
            if (logonName != null) {
                session.setAttribute(LOGON_NAME, logonName);
                user = this.systemService.getUser(logonName);
                if (user != null){
                	session.setAttribute(SystemConstants.SYSTEM_USER, user);
                	session.setAttribute(DISPLAY_NAME, user.getDisplayName());
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
                    
//                    String loginType = request.getParameter("loginType");
//                    if (StringUtils.isNotEmpty(loginType)){
//                    	session.setAttribute("loginType", loginType);
//                    }
                }

                return null;
            }
            // Cookie [] cookies = request.getCookies();
            // if(cookies!=null && cookies.length>0){
            // for (Cookie cookie : cookies) { //从cookie中查找是否以dreamhome的用户登录
            // if (Strings.equals(cookie.getName(), LOGON_NAME)) {
            // String userName = cookie.getValue();
            // //如果存在用户,则将用户名存入session
            // session.setAttribute(LOGON_NAME, userName);
            // return null;
            // }
            // }
            // }
            if (Strings.isEmpty(path)) {
                return new ViewWrapper(new RawView("htm"), JSON);
            } else {
                // 跳转到指定的路径
                return new ServerRedirectView(path);
            }
        }
        // 正常跳转
        return null;
    }

    /*
     * @Override public View match(ActionContext context) { HttpSession session
     * = Mvcs.getHttpSession(false); if (session == null) if
     * (Strings.isEmpty(path)) return null; else return new
     * ServerRedirectView(path); Object obj =
     * session.getAttribute(SystemConstants.SYSTEM_USER); if (null == obj)
     * return new ViewWrapper(new RawView("htm"), JSON); return null; }
     */
}
