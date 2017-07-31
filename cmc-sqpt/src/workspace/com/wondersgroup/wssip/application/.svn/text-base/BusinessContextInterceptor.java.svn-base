/** 
 * @(#)BusinessContextInterceptor.java 2008-10-31
 * 
 * Copyright (c) 1995-2008 Wonders Information Co.,Ltd. 
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

package com.wondersgroup.wssip.application;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wondersgroup.framework.core5.business.context.Environment;
import com.wondersgroup.framework.core5.business.context.UserContext;
import com.wondersgroup.framework.core5.business.context.support.BusinessContextUtils;
import com.wondersgroup.wssip.application.context.WssipContextUtils;
import com.wondersgroup.wssip.application.context.WssipEnvironment;
import com.wondersgroup.wssip.application.context.WssipUserContext;

/**
 * @author xieguoking
 * @version $Revision$ 2014年12月10日
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class BusinessContextInterceptor extends MethodFilterInterceptor {
	private static final long serialVersionUID = -4481017225416257821L;

	private final Logger log = Logger.getLogger("struts2.businesscontext.log");

	protected void log() {
		UserContext userContext = BusinessContextUtils.getUserContext();
		Environment environment = BusinessContextUtils.getEnvironment();
		if (StringUtils.isEmpty(userContext.getOperatorId())) {
			log.error(String.format("%s[%s][#%d]:lost business context!", environment.getIpAddress(),
					environment.getMacAddress(), Thread.currentThread().getId()));
		}
	}

	/**
	 * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();

		WssipUserContext userContext = WssipContextUtils.getUserContext();
		WssipEnvironment environment = WssipContextUtils.getEnvironment();

		final HttpServletRequest httpRequest = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
		WssipContextUtils.getContext().put(SessionConstants.SESSION_ID, httpRequest.getSession().getId());

		//操作员
		userContext.setOperator(session.get(SessionConstants.WSSIP_OPERATOR));
		//操作员ID
		String operatorId = (String) session.get(SessionConstants.WSSIP_OPERATOR_ID);
		userContext.setOperatorId(operatorId);
		//操作员姓名
		String operatorName = (String) session.get(SessionConstants.WSSIP_OPERATOR_NAME);
		userContext.setOperatorName(operatorName);
		//权限所属行政区划
		final String departmentregion = (String) session.get(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_REGION);
		WssipContextUtils.getContext().put(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_REGION, departmentregion);
		//权限所属街道
		final String departmentstreet = (String) session.get(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_STREET);
		WssipContextUtils.getContext().put(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_STREET, departmentstreet);
		//权限机构
		String department = (String) session.get(SessionConstants.WSSIP_OPERATOR_DEPARTMENT);
		userContext.setDepartmentCode(department);
		//权限机构名称
		String departmentname = (String) session.get(SessionConstants.WSSIP_OPERATOR_DEPARTMENTNAME);
		userContext.setDepartmentName(departmentname);
		//权限机构类型
		String departmenttype = (String) session.get(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_TYPE);
		WssipContextUtils.getContext().put(SessionConstants.WSSIP_OPERATOR_DEPARTMENT_TYPE, departmenttype);
				
		String businesstype = (String) session.get(SessionConstants.WSSIP_ENVIRONMENT_BUSINESSTYPE);
		environment.setBusinessType(businesstype);
		environment.setCurrentDate(new Date());
		environment.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());

		String macAddress = (String) session.get(SessionConstants.WSSIP_CLIENT_MACADDRESS);
		environment.setMacAddress(macAddress);
		log();
		// log.error(String.format("curr function id:%s", businesstype));
		try {
			return invocation.invoke();
		}
		finally {
			WssipContextUtils.clearContext();
		}
	}
}
