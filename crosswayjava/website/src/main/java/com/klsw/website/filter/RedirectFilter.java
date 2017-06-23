/*package com.klsw.website.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

@Component("redirectFilter")
public class RedirectFilter implements Filter {
	
	private static final List<String> oldDomains = Lists.newArrayList("http://localhost:8020");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		String hostName = "http://" + req.getServerName();
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		if (oldDomains.contains(hostName)) {// 如果请求旧域名
			// String queryString = (httpRequest.getQueryString() == null ? "":
			// "?" + httpRequest.getQueryString());
			response.setStatus(301);
			String requestUrl = httpRequest.getRequestURL().toString();
			requestUrl = requestUrl.replace(hostName, "http://www.klsw.com");// 把旧域名替换为新的域名
			response.setHeader("Location", requestUrl);
			response.setHeader("Connection", "close");
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
*/