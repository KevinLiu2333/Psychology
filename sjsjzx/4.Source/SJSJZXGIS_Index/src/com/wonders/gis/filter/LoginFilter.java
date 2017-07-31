package com.wonders.gis.filter;

import java.io.IOException;
 
 import javax.servlet.Filter;
 import javax.servlet.FilterChain;
 import javax.servlet.FilterConfig;
 import javax.servlet.ServletException;
 import javax.servlet.ServletRequest;
 import javax.servlet.ServletResponse;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpSession;
 
 public class LoginFilter implements Filter {
 
     @Override
     public void init(FilterConfig filterConfig) throws ServletException {
         // TODO Auto-generated method stub
 
     }
 
     @Override
     public void doFilter(ServletRequest request, ServletResponse response,
             FilterChain chain) throws IOException, ServletException {
         // 获得在下面代码中要用的request,response,session对象
         HttpServletRequest servletRequest = (HttpServletRequest) request;
         HttpServletResponse servletResponse = (HttpServletResponse) response;
         HttpSession session = servletRequest.getSession();
 
         // 获得用户请求的URI
         String path = servletRequest.getRequestURI();
         //System.out.println(path);
         
         if(path.indexOf("requestType=interfaceGIS") != -1 ) {
             chain.doFilter(servletRequest, servletResponse);
             return;
         }
         
         String userName = (String) session.getAttribute("userName");
 
         if(path.indexOf("/index.html") > -1){
        	 String loginMsg = "访问失败，请登陆后访问系统！";
             servletResponse.sendRedirect("/SJSJZXGIS_Index/login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
         }
         
         // 登陆、非jsp页面无需过滤
         if(path.indexOf("/servlet/Login") > -1 || path.indexOf("/login.jsp") > -1|| path.indexOf(".jsp") < 0|| path.indexOf("keylogin.jsp") > 0) {
             chain.doFilter(servletRequest, servletResponse);
             return;
         }
         
         // 判断如果没有取到员工信息,就跳转到登陆页面
         if (userName == null || "".equals(userName)) {
             // 跳转到登陆页面
        	 String loginMsg = "访问失败，请登陆后访问系统！";
             servletResponse.sendRedirect("/SJSJZXGIS_Index/login.jsp?errMsg="  + java.net.URLEncoder.encode(loginMsg));
         } else {
             // 已经登陆,继续此次请求
             chain.doFilter(request, response);
         }
 
     }
 
     @Override
     public void destroy() {
         // TODO Auto-generated method stub
 
     }
 
 }