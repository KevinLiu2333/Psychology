/** 
 * @(#)Operator.java 2014年12月16日
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

package com.wondersgroup.wssip.application.login;

import java.io.Serializable;

/**
 * 操作员信息，用于屏蔽不同权限系统
 * 
 * @author xieguoking
 * @version $Revision$ 2014年12月16日
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class Operator implements Serializable {

	private static final long serialVersionUID = 3020521849249058060L;

	private Long id;

	private String loginName;

	private String name;

	/**
	 * 组织节点代码
	 */
	private String organCode;

	/**
	 * 组织节点名称
	 */
	private String organName;

	/**
	 * 行政区划
	 */
	private String regionaCode;

	public Operator(Long id, String loginName, String name) {
		this.id = id;
		this.loginName = loginName;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	public String getRegionaCode() {
		return regionaCode;
	}

	public void setRegionaCode(String regionaCode) {
		this.regionaCode = regionaCode;
	}

}
