package com.klsw.weikesite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.weikesite.constants.Constants;


/**
 * 用户登录验证
 * @author Hkj
 *
 */
@Component("userAuthFilter")
public class UserAuthFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("session filter init...");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse)resp;
//		String servletPath = request.getServletPath();
//		String requestUrl = request.getRequestURI().substring(request.getContextPath().length(),request.getContextPath().length() + servletPath.length());
		
		Object obj = request.getSession().getAttribute(Constants.USER);
		
		boolean isAuth = false;
		
		if(obj != null) {
			if(obj instanceof TbCwk) {
				isAuth = true;
			}
		}
		
		if(isAuth) {
			chain.doFilter(req, resp);
		} else {
//				request.getRequestDispatcher("/toLogin").forward(request, response);
			response.sendRedirect("/");
		}
	
		
	}
	
	@Override
	public void destroy() {
		logger.info("session filter destroy...");
	}

}