/** 
 * @(#)SessionConstants.java 2008-10-24
 * 
 * Copyright (c) 1995-2008 Wonders Information Co.,Ltd. 
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

package com.wondersgroup.wssip.application;

/**
 * Session常量
 * 
 * @author Yao Jianping
 * @version $Revision: 4021 $ 2008-10-24
 * @author $Date: 2010-07-20 10:50:12 +0800 (周二, 20 七月 2010) $ by $Author: Xiegj $
 * @since 1.0
 */
public interface SessionConstants {

	/**
	 * 操作员编号
	 */
	public static final String WSSIP_OPERATOR_ID = "com.wondersgroup.wssip.operator.id";

	/**
	 * 操作员登录名
	 */
	public static final String WSSIP_OPERATOR_LOGINNAME = "com.wondersgroup.wssip.operator.loginName";

	/**
	 * 操作员姓名
	 */
	public static final String WSSIP_OPERATOR_NAME = "com.wondersgroup.wssip.operator.name";

	/**
	 * 操作员对象(SecurityUser)
	 */
	public static final String WSSIP_OPERATOR = "com.wondersgroup.wssip.operator";

	/**
	 * 操作员客户端MAC地址
	 */
	public static final String WSSIP_CLIENT_MACADDRESS = "com.wondersgroup.wssip.operator.macaddress";

	/**
	 * 权限代理
	 */
	public static final String WSSIP_OPERATOR_SECURITYAGENT = "com.wondersgroup.wssip.operator.securityagent";

	/**
	 * 业务操作上下文业务编码
	 */
	public static final String WSSIP_ENVIRONMENT_BUSINESSTYPE = "com.wondersgroup.wssip.environment.businesstype";

	/**
	 * 操作员所属权限部门机构代码
	 */
	public static final String WSSIP_OPERATOR_DEPARTMENT = "com.wondersgroup.wssip.operator.department";
	
	/**
	 * 操作员所属权限部门机构名称
	 */
	public static final String WSSIP_OPERATOR_DEPARTMENTNAME = "com.wondersgroup.wssip.operator.department.name";
	
	/**
	 * 操作员所属权限部门街道
	 */
	public static final String WSSIP_OPERATOR_DEPARTMENT_STREET = "com.wondersgroup.wssip.operator.department.street";
	
	/**
	 * 操作员所属权限部门行政区划
	 */
	public static final String WSSIP_OPERATOR_DEPARTMENT_REGION = "com.wondersgroup.wssip.operator.department.region";
	
	/**
	 * 操作员所属权限部门类型
	 */
	public static final String WSSIP_OPERATOR_DEPARTMENT_TYPE = "com.wondersgroup.wssip.operator.department.type";
	
	/**
	 * 操作员所属权限部门服务机构类型
	 */
	public static final String WSSIP_OPERATOR_SERVICE_TYPE = "com.wondersgroup.wssip.operator.service.type";
	
	/**
	 * SESSIONID
	 */
	public static final String SESSION_ID = "com.wondersgroup.cmc.session.id";
	
	/**
	 * 菜单定义
	 */
	public static final String MENU_DEFINE_CACHE = "com.wondersgroup.cmc.menu";
	
	/**
	 * 菜单资源类型
	 */
	public static final String MENU_RESOURCE_TYPE = "MENU";
	
	/**
	 * 事项定义
	 */
	public static final String AFFAIRS_DEFINE_CACHE = "com.wondersgroup.cmc.affairs.define";
	
	/**
	 * 事项资源类型
	 */
	public static final String AFFAIRS_RESOURCE_TYPE = "AFFAIRS";

	/**
	 * 资源操作类型
	 */
	public static final String RESOURCE_ACTION = "ACCESS";
	
	/**
	 * 菜单根节点ID
	 */
	public static final String ROOTMENUID = "3000";
	
	/**
	 * 一级菜单
	 */
	public static final String MENULIST = "com.wondersgroup.cmc.menu.list";
	
	/**
	 * 二级菜单
	 */
	public static final String SECONDMENULIST = "com.wondersgroup.cmc.menu.second.list";
	
	/**
	 * 三级菜单
	 */
	public static final String THIRDMENULIST = "com.wondersgroup.cmc.menu.third.list";
	
	/**
	 * 初始加载菜单
	 */
	public static final String PROTAL_LOAD_ARRAY = "com.wondersgroup.cmc.protal.load.array";
	
	/**
	 * 桌面菜单
	 */
	public static final String APPIDS = "com.wondersgroup.cmc.appids";
}
