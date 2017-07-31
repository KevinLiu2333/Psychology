package com.wonders.tiles.tag;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.nutz.lang.Strings;

import com.wonders.tiles.dic.DicDataUtils;


public class SelectTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String name;
	
	private Integer dicCode;
	
	private String className;
	
	private String initValue;
	
	private String defaultValue;
	
	private String dataOptions;
	
	private String style;
	
	private String onchange;
	
	/**
	 * 其他事项，例如自定义的datatype.
	 */
	private String others;
	
	public int doStartTag() throws JspException {
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		try {
			String strHTML = this.makeHtml();
			pageContext.getOut().write(strHTML);
		} catch (IOException e) {
			new JspException("IO Exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	private String makeHtml() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<select");
		if (!Strings.isEmpty(id))
			sb.append(" id=\"" + id + "\"");
		if (!Strings.isEmpty(name))
			sb.append(" name=\"" + name + "\"");
		if (!Strings.isEmpty(className))
			sb.append(" class=\"" + className + "\"");
		if (!Strings.isEmpty(dataOptions))
			sb.append(" data-options=\"" + dataOptions + "\"");
		if (!Strings.isEmpty(style))
			sb.append(" style=\"" + style + "\"");
		if (!Strings.isEmpty(onchange))
				sb.append(" onchange=\"" + onchange + "\"");
		if (!StringUtils.isEmpty(others)){
		    sb.append(" " + others + " ");
		}
		sb.append(">\n");
		if (!Strings.isEmpty(initValue)) {
			sb.append("<option value=\"\"" + ((defaultValue == null || "".equals(defaultValue))?" selected":"") + ">" + initValue + "</option>\n");
		}
		if (null != dicCode) {
			Map<String, String> dic = DicDataUtils.getInstance().getDic(dicCode);
			if (dic != null)
				for (Entry<String, String> entry : dic.entrySet()) {
					sb.append("<option value=\"" + entry.getKey() + "\"" +  (entry.getKey().equals(defaultValue)?" selected":"") + ">" + entry.getValue() + "</option>\n");
				}
		}
		sb.append("</select>");
		return sb.toString();
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setDicCode(Integer dicCode) {
		this.dicCode = dicCode;
	}

	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}

    
    public void setOthers(String others) {
        this.others = others;
    }
	
}
