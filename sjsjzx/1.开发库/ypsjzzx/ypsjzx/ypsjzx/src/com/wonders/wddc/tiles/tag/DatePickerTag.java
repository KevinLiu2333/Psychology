package com.wonders.wddc.tiles.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.nutz.lang.Strings;
import org.nutz.lang.Times;

public class DatePickerTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private static final String DEFAULTFORMAT = "yyyy-MM-dd";

	private String id;

	private String name;

	private String className;
	
	private String style;

	private Date defaultValue;

	private String dateFormat;

	private String minDate;

	private String maxDate;

	private String options;
	
	/**
	 * 其他属性，例如验证组件自定义的datatype等.
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
		sb.append("<input type=\"text\"");
		if (!Strings.isEmpty(id))
			sb.append(" id=\"" + id + "\"");
		if (!Strings.isEmpty(name))
			sb.append(" name=\"" + name + "\"");
		if (!Strings.isEmpty(className))
			sb.append(" class=\"" + className + "\"");
		if (!Strings.isEmpty(style))
			sb.append(" style=\"" + style + "\"");
		if (!StringUtils.isEmpty(others)){
            sb.append(" " + others + " ");
        }
		if (null != defaultValue) {
			if (!Strings.isEmpty(dateFormat))
				sb.append(" value=\""
						+ Times.format(new SimpleDateFormat(dateFormat),
								defaultValue) + "\"");
			else
				sb.append(" value=\""
						+ Times.format(new SimpleDateFormat(DEFAULTFORMAT),
								defaultValue) + "\"");
		} else {
			sb.append(" value=\"\"");
		}
		StringBuffer dateOptions = new StringBuffer();
		dateOptions.append("WdatePicker(");
		if (!Strings.isEmpty(dateFormat) || !Strings.isEmpty(minDate)
				|| !Strings.isEmpty(maxDate) || !Strings.isEmpty(options)) {
			dateOptions.append("{");
			if (!Strings.isEmpty(dateFormat))
				dateOptions.append("dateFmt:'" + dateFormat + "',");
			if (!Strings.isEmpty(minDate))
				dateOptions.append("minDate:'" + minDate + "',");
			if (!Strings.isEmpty(maxDate))
				dateOptions.append("maxDate:'" + maxDate + "',");
			if (!Strings.isEmpty(options))
				dateOptions.append(options);
			dateOptions.append("}");
		}
		dateOptions.append(")");
		String options = dateOptions.toString();
		options = options.replace(",}", "}");

		sb.append(" onClick=\"" + options + "\"");
		sb.append(">");
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

	public void setDefaultValue(Date defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public void setStyle(String style) {
		this.style = style;
	}

    
    public void setOthers(String others) {
        this.others = others;
    }

}
