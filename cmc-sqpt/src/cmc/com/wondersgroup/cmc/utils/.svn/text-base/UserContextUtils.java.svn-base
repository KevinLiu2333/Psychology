package com.wondersgroup.cmc.utils;

import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.application.SessionConstants;
import com.wondersgroup.wssip.application.context.WssipContextUtils;

public class UserContextUtils {
    // 操作员ID
    public static String getOperatorId() {
	return WssipContextUtils.getUserContext().getOperatorId();
    }

    // 操作员姓名
    public static String getOperatorName() {
	return WssipContextUtils.getUserContext().getOperatorName();
    }

    // 操作员权限所属机构代码
    public static String getDepartmentCode() {
	return WssipContextUtils.getUserContext().getDepartmentCode();
    }

    // 操作员权限所属机构名称
    public static String getDepartmentName() {
	return WssipContextUtils.getUserContext().getDepartmentName();
    }

    // 操作员权限所属机构街道代码
    public static String getDepartmentStreetCode() {
	return (String) WssipContextUtils.getContext().get(
		SessionConstants.WSSIP_OPERATOR_DEPARTMENT_STREET);
    }

    // 操作员权限所属机构行政区划代码
    public static String getDepartmentRegionCode() {
	String code = (String) WssipContextUtils.getContext().get(
		SessionConstants.WSSIP_OPERATOR_DEPARTMENT_REGION);
	try {
	    return code.substring(0, 6);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new BusinessException("未获得所属机构行政区划，请重新登录。");
	}
    }

    // 操作员权限所属机构类型
    public static String getDepartmentType() {
	return (String) WssipContextUtils.getContext().get(
		SessionConstants.WSSIP_OPERATOR_DEPARTMENT_TYPE);
    }

    // 操作员登录所属机构代码
    public static String getOrganCode() {
	return WssipContextUtils.getUserContext().getOrganCode();
    }

    // 操作员登录所属机构行政区划代码
    public static String getRegionCode() {
	return WssipContextUtils.getUserContext().getZoneCode();
    }
}
