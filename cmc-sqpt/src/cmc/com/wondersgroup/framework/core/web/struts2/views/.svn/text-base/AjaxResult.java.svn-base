package com.wondersgroup.framework.core.web.struts2.views;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.UndeclaredThrowableException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.inject.Inject;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.framework.core.web.struts2.AjaxProvider;

public class AjaxResult extends StrutsResultSupport {

	private static final long serialVersionUID = -2391682376523761525L;

	Log log = LogFactory.getLog(AjaxResult.class);

	static final String AJAX_SUCCESS = "{\"success\":true}";

	static final String SUCCESS_PERFIX = "{\"success\":true,result:[";

	static final String FAILURE_PERFIX = "{\"success\":false,result:[],";

	static final String SUFFIX = "]}";

	Writer writer;

	String defaultEncoding;

	public AjaxResult() {
		this.defaultEncoding = "UTF-8";
	}

	@Inject("struts.i18n.encoding")
	public void setDefaultEncoding(String encoding) {
		this.defaultEncoding = encoding;
	}

	@SuppressWarnings("rawtypes")
	protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
		Object action = invocation.getAction();
		String responseData = "";
		if (action instanceof AjaxProvider) {
			AjaxProvider ajaxAction = (AjaxProvider) action;
			HttpServletResponse response = ServletActionContext.getResponse();
			// String encoding = getEncoding(finalLocation);
			String contentType = "text/html;charset=UTF-8";
			response.setContentType(contentType);

			if ((action instanceof ValidationAware)) {
				ValidationAware va = (ValidationAware) action;

				Map errors = va.getFieldErrors();

				if ((errors != null) && (!errors.isEmpty())) {
					getWriter().write(getFailureData(errors));
					return;
				}

			}

			String successData = ajaxAction.getResponseData();
			if (successData != null) {
				if ("success".equals(successData)) {
					responseData = "{\"success\":true}";
				}
				else if (ajaxAction.isAjaxSuccess()) {
					responseData = "{\"success\":true,result:[" + successData + "]}";
				}
				else {
					successData = successData.trim();
					if (responseData.indexOf("'") > 0) { // 存在单引号
						if (successData.charAt(0) == '[') {
							JSONArray array = JSONArray.fromObject(successData);
							responseData = array.toString();
						}
						else {
							JSONObject json = JSONObject.fromObject(successData);
							responseData = json.toString();
						}
					}
					else {
						responseData = successData;
					}
					// responseData=successData;
				}

			}
			else if (ajaxAction.hasAjaxErrors()) {
				String errorResultLocation = ajaxAction.getErrorResultLocation();
				String exceptionMessage = invocation.getStack().findString("exception.message");
				if (exceptionMessage != null) {
					exceptionMessage =StringUtils.replaceEach(exceptionMessage,new String[]{"\\","\"","\r","\n","\t"},new String[]{"\\\\","\\\"","\\r","\\n","\\t"});
				}
				else {
					exceptionMessage = "未知错误！";
				}
				responseData = getFailureData(errorResultLocation, exceptionMessage);
			}
			if (log.isDebugEnabled()) {
				log.debug("response:" + responseData);
			}
			getWriter().write(responseData);
		}
	}

	@SuppressWarnings("rawtypes")
	private String getFailureData(Map errors) {
		StringBuffer failureData = new StringBuffer("{success:false,result:[]");
		StringBuffer exceptionMsg = new StringBuffer();

		Iterator it = errors.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();

			String key = (String) entry.getKey();

			if (exceptionMsg.length() > 0) {
				exceptionMsg.append(",");
			}
			exceptionMsg.append("{msg:'[").append(key).append("]转换错误!'}");

			if (isDebug()) {
				List errorList = (List) entry.getValue();
				printException(errorList);
			}
		}

		failureData.append(",exception:[").append(exceptionMsg.toString()).append("]");
		failureData.append(",errors:[").append(exceptionMsg.toString()).append("]");
		failureData.append("}");

		return failureData.toString();
	}

	private String getFailureData(String errorResultLocation, String exceptionMessage) {
		String errors = "\"errors\":[{\"msg\":\"" + exceptionMessage + "\"}],";
		String target = "\"target\":\"" + errorResultLocation;
		String failureData = "{\"success\":false,\"result\":[]," + errors + target + "\"}";
		return failureData;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	protected Writer getWriter() throws IOException {
		if (this.writer != null) {
			return this.writer;
		}
		return ServletActionContext.getResponse().getWriter();
	}

	protected String getContentType(String templateLocation) {
		return "application/json";
	}

	protected String getEncoding(String templateLocation) {
		String encoding = this.defaultEncoding;
		if (encoding == null) {
			encoding = System.getProperty("file.encoding");
		}
		if (encoding == null) {
			encoding = "UTF-8";
		}
		return encoding;
	}

	public String getErrorMsg(Throwable throwable) {
		if ((throwable instanceof NullPointerException)) {
			return "空指针异常，请联系管理员!";
		}

		if (((throwable instanceof BusinessException)) || ((throwable instanceof SQLException))) {
			return throwable.getMessage();
		}

		if (((throwable instanceof RuntimeException)) && (!(throwable instanceof UndeclaredThrowableException))) {
			return throwable.getMessage();
		}

		return getErrorMsg(throwable.getCause());
	}

	@SuppressWarnings("rawtypes")
	private void printException(List errorList) {
		for (int i = 0; i < errorList.size(); i++) {
			String msg = errorList.get(i).toString();
			new RuntimeException(msg).printStackTrace();
		}
	}

	private boolean isDebug() {
		return true;
	}
}
