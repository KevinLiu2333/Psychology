package com.wonders.tiles.tag;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.nutz.lang.Strings;

import com.wonders.tiles.dic.DicDataUtils;

public class RadioTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private String name;

	private String id;

	private Integer dicCode;

	private String labelClassName;

	private String className;

	private String style;

	private int rowSize;

	private String defaultValue;

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
		if (null != dicCode) {
			Map<String, String> dicMaps = DicDataUtils.getInstance().getDic(dicCode);
			if (dicMaps != null) {
				Set<String> keySets = dicMaps.keySet();
				int index = 0;
				for (String key : keySets) {
					index++;
					boolean flag = false;
					if (defaultValue != null && defaultValue.equals(key))
						flag = true;
					String value = dicMaps.get(key);
					sb.append("<label");
					if (!Strings.isEmpty(labelClassName))
						sb.append(" class=\"" + labelClassName + "\"");
					sb.append(">");
					sb.append("<input type=\"radio\"");
					if (!Strings.isEmpty(id))
						sb.append(" id=\"" + id + "\"");
					if (!Strings.isEmpty(name))
						sb.append(" name=\"" + name + "\"");
					if (!Strings.isEmpty(style))
						sb.append(" style=\"" + style + "\"");
					if (!Strings.isEmpty(className))
						sb.append(" class=\"" + className + "\"");
					if (!Strings.isEmpty(key))
						sb.append(" value=\"" + key + "\"");
					if (flag)
						sb.append(" checked");
					sb.append("/>");
					sb.append(value);
					sb.append("</label>&nbsp;");
					if (rowSize != 0 && index == rowSize)
						sb.append("</br>");
				}
			}
		}
		return sb.toString();
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void setLabelClassName(String labelClassName) {
		this.labelClassName = labelClassName;
	}

	public void setDicCode(Integer dicCode) {
		this.dicCode = dicCode;
	}

	public void setId(String id) {
		this.id = id;
	}

}
