//package com.klsw.piano.filter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.klsw.piano.util.Constants;
//import com.klsw.pianoCommon.api.model.TbPmuser;
//
//
///**
// * 用户登录验证
// * @author HKJ
// *
// */
//@Component("userAuthFilter")
//public class UserAuthFilter implements Filter {
//
//	private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	private static final List<String> excludedPath = Arrays.asList(Constants.EXECLUDE_PATH);
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		logger.info("session filter init...");
//	}
//
//	@Override
//	public void doFilter(ServletRequest req, ServletResponse resp,
//			FilterChain chain) throws IOException, ServletException {
//
//		HttpServletRequest request = (HttpServletRequest) req;
//		HttpServletResponse response = (HttpServletResponse)resp;
//		//String servletPath = request.getServletPath();
//		//String requestUrl = request.getRequestURI().substring(request.getContextPath().length() + servletPath.length());
//
//		Object obj = request.getSession().getAttribute("tbPmuser");
//
//		boolean isAuth = false;
//
//		if(obj != null) {
//			if(obj instanceof TbPmuser) {
//				isAuth = true;
//			}
//		}
//
//		for(String path: excludedPath) {
//			if(path.toLowerCase().equals(request.getServletPath().toLowerCase())) {
//				isAuth = true;
//				break;
//			}
//		}
//
//		if(isAuth) {
//			chain.doFilter(req, resp);
//		} else {
//			request.getRequestDispatcher("/user/toLogin").forward(request, response);
//			//response.sendRedirect("/login");
//		}
//	}
//
//	@Override
//	public void destroy() {
//		logger.info("session filter destroy...");
//	}
//
//}