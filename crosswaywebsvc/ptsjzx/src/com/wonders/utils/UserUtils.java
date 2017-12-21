package com.wonders.utils;

import javax.servlet.http.HttpServletRequest;

import com.wonders.tiles.authority.entity.User;
import com.wonders.tiles.authority.service.SystemConstants;

public class UserUtils {
	public static String getUserId(HttpServletRequest request){
		User sessionUser = (User)request.getSession().getAttribute(SystemConstants.SYSTEM_USER);
		String userId = sessionUser.getUserId();
		return userId;
	}
	public static String getOrganId(HttpServletRequest request){
		String OrganId = "DT01";
		return OrganId;
	}
	public static String getName(HttpServletRequest request){
		String Name = "普陀数据中心";
		return Name;
	}
}
