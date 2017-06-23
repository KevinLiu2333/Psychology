package com.klsw.wk.hades.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.klsw.wk.hades.service.IUserService;

@Component("urlPermissionsFilter")
public class URLPermissionsFilter extends PermissionsAuthorizationFilter{
	@Autowired
	private IUserService userService;

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		String curUrl = getRequestUrl(request);
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipal() == null 
				|| StringUtils.endsWithAny(curUrl, ".js",".css",".html")
				|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg")
				|| StringUtils.equals(curUrl, "/unauthor")) {
			return true;
		}
		List<String> urls = null;//userService.findPermissionUrl(subject.getPrincipal().toString()); comment by liulixi 2017年6月16日14:22:20
		
		return urls.contains(curUrl);
	}
	
	/**
	 * 获取当前URL+Parameter
	 * @author liulixi
	 * @since  2017年6月16日14:20:06
	 * @param request	拦截请求request
	 * @return			返回完整URL
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();

		queryString = StringUtils.isBlank(queryString) ? "" : "?" + queryString;
		return req.getRequestURI() + queryString;
	}
}
