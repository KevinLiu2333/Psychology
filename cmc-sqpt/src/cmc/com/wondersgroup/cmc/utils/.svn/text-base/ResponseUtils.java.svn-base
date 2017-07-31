/** 
 * @(#)ResponseUtils.java 2014-2-19
 * 
 * Copyright (c) 1995-2014 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Wonders Group.
 * (Social Security Department). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gnu.org
 */

package com.wondersgroup.cmc.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

/**
 * @author xieguoking
 * @version $Revision$ 2014-2-19
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public final class ResponseUtils {

	/**
	 * 写入政务平台Metro数据
	 * 
	 * @param request 请求对象
	 * @param response 响应对象
	 * @param html 响应内容
	 */
	public final static void writeMetroData(HttpServletRequest request, HttpServletResponse response, String html) {
		response.reset();
		response.setContentType("text/javascript");
		response.setCharacterEncoding("GBK");
		JSONObject json = new JSONObject();
		json.put("html", html);
		String callback = StringUtils.trimToEmpty(request.getParameter("callback"));
		String result = callback + "(" + json.toString() + ")";
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(result);
			pw.flush();
		}
		catch (IOException ie) {
			//
		}
		finally {
			if (pw != null) {
				pw.close();
			}
		}

	}

}
