package com.klsw.piano.util;

import javax.servlet.http.HttpServletRequest;

import com.klsw.pianoCommon.api.model.TbPmuser;

public class LoginHelper {

	public static boolean validate(HttpServletRequest request) {
		TbPmuser pmuser = (TbPmuser) request.getSession().getAttribute("tbPmuser");
		if(pmuser == null) {
			return false;
		}
		return true;
	}
}
