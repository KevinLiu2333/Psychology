package com.klsw.website.filter;

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

import com.klsw.common.mall.model.BgManager;
import com.klsw.website.util.Bg_Constants;

/**
 * 后台管理人员登录验证
 * @author liukun
 *
 */
@Component("managerAuthFilter")
public class ManagerAuthFilter implements Filter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("session filter init...");

		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain)throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse)resp;
		Object obj = request.getSession().getAttribute(Bg_Constants.MANAGER);
		boolean isAuth = false;
		if(obj != null) {
			if(obj instanceof BgManager) {
				isAuth = true;
			}
		}
		
		if(isAuth) {
			chain.doFilter(req, resp);
		} else {
			response.sendRedirect("/manage");
		}
	}
		

	@Override
	public void destroy() {
		logger.info("session filter destroy...");		
	}

}
