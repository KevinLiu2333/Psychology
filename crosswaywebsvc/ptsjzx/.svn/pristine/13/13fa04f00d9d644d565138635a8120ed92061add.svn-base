package com.wonders.tiles.tag;

import com.wonders.tiles.dic.DicDataUtils;
import java.io.IOException;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

import org.nutz.lang.Strings;

public class DicValueTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String className;
	private String style;
	private Integer dicId;
	private String dicCode;

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDicId(Integer dicId) {
		this.dicId = dicId;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}

	public DicValueTag() {
	}

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
		StringBuilder sb = new StringBuilder();
		sb.append("<span");
		if (!Strings.isEmpty(id))
			sb.append((new StringBuilder(" id=\"")).append(id).append("\""));
		if (!Strings.isEmpty(name))
			sb.append((new StringBuilder(" name=\"")).append(name).append("\""));
		if (!Strings.isEmpty(className))
			sb.append((new StringBuilder(" class=\"")).append(className).append("\""));
		if (!Strings.isEmpty(style))
			sb.append((new StringBuilder(" style=\"")).append(style).append("\""));
		Map<String, String> dicMap = DicDataUtils.getInstance().getDic(dicId);
		sb.append(">\n");
		if (dicMap != null) {
			String dicValue = DicDataUtils.getInstance().getDicData(dicId,dicCode);
			if (null == dicValue) {
				dicValue = "";
			}
			sb.append(dicValue);
		}
		sb.append("</span>");
		return sb.toString();
	}

}
