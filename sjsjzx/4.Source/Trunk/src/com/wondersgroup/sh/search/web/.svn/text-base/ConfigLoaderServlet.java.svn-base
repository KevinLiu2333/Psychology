/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigLoaderServlet extends HttpServlet {
	private static final long serialVersionUID = -8173382757418107213L;
	private ContextLoader contextLoader;

	public void init() throws ServletException {
		this.contextLoader = createContextLoader();
		this.contextLoader.initConfiguration(getServletContext());
	}

	protected ContextLoader createContextLoader() {
		return new ContextLoader();
	}

	public ContextLoader getContextLoader() {
		return contextLoader;
	}

	/**
	 * This should never even be called since no mapping to this servlet should
	 * ever be created in web.xml. That's why a correctly invoked Servlet 2.3
	 * listener is much more appropriate for initialization work ;-)
	 * 
	 * @param request the request
	 * @param response the response
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getServletContext().log("Attempt to call service method on ConfigLoaderServlet as [" +
				request.getRequestURI() + "] was ignored");
		response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	}

	public String getServletInfo() {
		return "ConfigLoaderServlet for Servlet API 2.2/2.3 " +
		    "(deprecated in favor of ConfigLoaderListener for Servlet API 2.4)";
	}
}
