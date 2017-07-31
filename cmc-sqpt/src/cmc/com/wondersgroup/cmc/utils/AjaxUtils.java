/** 
 * @(#)AjaxUtils.java Jul 14, 2011
 * 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
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

import java.util.List;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.web.vo.VOUtils;

/**
 * @author wangchenglin
 * @version $Revision$ Jul 14, 2011
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class AjaxUtils {

	public static final String SUCCESS = "{\"success\":true}";
	
	public static final String DEFEAT = "{\"success\":false}";

	private static final String PREFIX_ARRAY = "{\"success\":true,result:";

	private static final String SUFFEX_ARRAY = "}";

	private static final String PREFIX_SINGLE = "{\"success\":true,result:[";

	private static final String SUFFEX_SINGLE = "]}";

	public static String getJsonDataFromPage(Page page, Class<?> voClass) {
		return VOUtils.getJsonDataFromPage(page, voClass);
	}

	public static String getJsonDataFromCollection(List<?> list) {
		return PREFIX_ARRAY + VOUtils.getJsonDataFromCollection(list) + SUFFEX_ARRAY;
	}

	public static String getJsonData(Object bean) {
		return PREFIX_SINGLE + VOUtils.getJsonData(bean) + SUFFEX_SINGLE;
	}
}
