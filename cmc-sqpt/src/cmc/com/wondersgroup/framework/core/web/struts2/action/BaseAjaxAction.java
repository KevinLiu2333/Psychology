/** 
 * @(#)BaseAjaxAction.java 2010-8-20
 * 
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.framework.core.web.struts2.action;

import com.opensymphony.xwork2.ActionContext;
import com.wondersgroup.framework.core.web.struts2.AjaxProvider;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * @author xieguojun
 * @version $Revision$ 2010-8-20
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class BaseAjaxAction extends AbstractAction implements AjaxProvider {
	private static final long serialVersionUID = 1L;

	private String responseData;

	private String errorResultLocation;

	private boolean ajaxSuccess;

	public BaseAjaxAction() {
		this.ajaxSuccess = false;
	}

	protected String getGid() {
		return getServletRequest().getParameter("__gid__");
	}

	@Override
	public ValueObject getValueObject() {
		return null;
	}

	public String getErrorResultLocation() {
		return this.errorResultLocation;
	}

	public void setErrorResultLocation(String errorResultLocation) {
		this.errorResultLocation = errorResultLocation;
	}

	public String getResponseData() {
		return this.responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public boolean isAjaxSuccess() {
		return this.ajaxSuccess;
	}

	public void setAjaxSuccess(boolean ajaxSuccess) {
		this.ajaxSuccess = ajaxSuccess;
	}

	public boolean hasAjaxErrors() {
		String object = (String) ActionContext.getContext().get(
				"com.wondersgroup.framework.core.web.ajax.errors.flag");
		return Boolean.parseBoolean(object);
	}

	@Override
	public String execute() throws Exception {
		long start = System.currentTimeMillis();
		String result = operate();
		createJSonData(result);
		if (logger.isErrorEnabled()) {
			logger.info(String.format("本次请求时间:%sms", System.currentTimeMillis()
					- start));
		}

		return "ajax";
	}

	@Override
	protected String operate() {
		return "none";
	}

	protected void createJSonData(String jsonData) {
		setResponseData(jsonData);
	}

	protected Integer getPageSize() {
		String limit = this.servletRequest.getParameter("limit");
		if (limit != null) {
			return Integer.valueOf(Integer.parseInt(limit));
		}

		return Integer.valueOf(10);
	}

	protected Integer getPageStart() {
		String start = this.servletRequest.getParameter("start");
		if (start != null) {
			return Integer.valueOf(Integer.parseInt(start));
		}

		return Integer.valueOf(0);
	}
}
