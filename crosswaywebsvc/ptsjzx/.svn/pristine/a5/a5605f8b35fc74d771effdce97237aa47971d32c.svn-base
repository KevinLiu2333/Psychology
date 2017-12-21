package com.wonders.tiles.tag;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.TagSupport;

public class AuthorityTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	
	private final static String USER_AUTH = "user_auth"; //用户权限
	
	private String authId;
	
	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getAuthId() {
		return authId;
	}

	public AuthorityTag() {
	}

	public int doStartTag() throws JspException {
		if (this.hasAuthority()) {
			return EVAL_BODY_INCLUDE;
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
	
	@SuppressWarnings("unchecked")
	private boolean hasAuthority() {
		HttpSession session = pageContext.getSession();
		List <String> authList = (List <String>) session.getAttribute(USER_AUTH);
		if (authList.contains(authId)) {
			return true;
		}
		return false;
	}

}
