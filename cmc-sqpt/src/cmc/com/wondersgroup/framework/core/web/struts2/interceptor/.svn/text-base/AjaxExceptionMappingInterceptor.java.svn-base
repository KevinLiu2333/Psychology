package com.wondersgroup.framework.core.web.struts2.interceptor;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ExceptionMappingConfig;
import com.opensymphony.xwork2.config.entities.ResultConfig;
import com.opensymphony.xwork2.interceptor.ExceptionHolder;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;
import com.wondersgroup.framework.core.web.struts2.AjaxProvider;

@SuppressWarnings("serial")
public class AjaxExceptionMappingInterceptor extends ExceptionMappingInterceptor {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String intercept(ActionInvocation invocation) throws Exception

	{
		String result = null;
		try {
			result = invocation.invoke();
		}
		catch (Exception e) {
			if (this.logEnabled) {
				handleLogging(e);
			}

			List exceptionMappings = invocation.getProxy().getConfig().getExceptionMappings();
			String mappedResult = findResultFromExceptions(exceptionMappings, e);
			if (mappedResult != null) {
				result = mappedResult;
				Object action = invocation.getAction();
				if ((action instanceof AjaxProvider)) {
					AjaxProvider ajaxAction = (AjaxProvider) action;
					Map results = invocation.getProxy().getConfig().getResults();
					ResultConfig resultConfig = (ResultConfig) results.get(result);
					String location = (String) resultConfig.getParams().get("location");

					invocation.getInvocationContext().put("com.wondersgroup.framework.core.web.ajax.errors.flag",
							"true");
					ajaxAction.setErrorResultLocation(location);
					result = "ajaxError";
				}
				super.publishException(invocation, new ExceptionHolder(e));
			}
			else {
				throw e;
			}
		}
		return result;
	}

	protected String findResultFromExceptions(List<ExceptionMappingConfig> exceptionMappings, Throwable t) {
		String result = null;
		int deepest;
		if (exceptionMappings != null) {
			deepest = 2147483647;
			for (ExceptionMappingConfig exceptionMappingConfig : exceptionMappings) {
				int depth = getDepth(exceptionMappingConfig.getExceptionClassName(), t);
				if ((depth >= 0) && (depth < deepest)) {
					deepest = depth;
					result = exceptionMappingConfig.getResult();
				}
			}
		}
		return result;
	}
}
