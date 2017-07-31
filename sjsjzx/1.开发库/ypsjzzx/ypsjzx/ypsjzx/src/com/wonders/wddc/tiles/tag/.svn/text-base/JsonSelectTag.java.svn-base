package com.wonders.wddc.tiles.tag;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.nutz.lang.Strings;

public class JsonSelectTag extends TagSupport {

	private static final long serialVersionUID = 849502142019488870L;
	private String id;
	private String name;
	private String jsonstr;
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
		if(!Strings.isEmpty(jsonstr)){
			JSONObject jo = JSONObject.fromObject(jsonstr);
			@SuppressWarnings("unchecked")
			Iterator<String>  it  = jo.keys();
			while(it.hasNext()){
				// 获得key  
			    String key = it.next();  
			    // 根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可  
			    String value = jo.getString(key);  
			    sb.append("<option value=\"" + key + "\"" +  (key.equals(defaultValue)?" selected":"") + ">" + value + "</option>\n");
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJsonstr() {
		return jsonstr;
	}
	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getInitValue() {
		return initValue;
	}
	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getDataOptions() {
		return dataOptions;
	}
	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
	
	
}
