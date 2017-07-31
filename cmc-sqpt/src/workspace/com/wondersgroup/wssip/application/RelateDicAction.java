package com.wondersgroup.wssip.application;

import java.util.Map;

import javax.servlet.ServletContext;

import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;
import com.wondersgroup.wssip.util.StringTools;

public class RelateDicAction extends BaseAjaxAction {
	private static final long serialVersionUID = 1L;

	private String parentCode;
	private String dicCode;

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getDicCode() {
		return dicCode;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	@Override
	protected String operate() {
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	public String getRelatedDic() {

		String dicJson = null;

		if (StringTools.isEmpty(dicCode) || StringTools.isEmpty(parentCode)) {
			dicJson = "[]";
		} else {
			ServletContext servlet = getServletRequest().getSession()
					.getServletContext();
			String codeJsonsKey = servlet.getInitParameter("codeJsonsKey");
			Map<String, String> dicJsonMap = (Map<String, String>) servlet
					.getAttribute(codeJsonsKey);
			dicJson = dicJsonMap.get(String.format("%s@%s", new Object[] {
					dicCode, parentCode }));
			if ( StringTools.isEmpty(dicJson) ){
				dicJson = "[]";
			}
		}
		this.createJSonData(dicJson);
		return AJAX;
	}

}
