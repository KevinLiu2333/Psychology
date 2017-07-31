/** 
 * @(#)ColCheckRule.java 2008-11-20
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

package com.wondersgroup.wssip.commons.file;

/**
 * 列校验规则，对所有行或多行进行该列的校验规则
 * 
 * @author Yao Jianping
 * @version $Revision$ 2008-11-20
 * @author $Date$ by $Author$
 * @since 1.0
 */
public class ColCheckRule implements CheckRule {

	private Validator validator;

	private Area area;

	public ColCheckRule(int column, Validator validator) {
		this.validator = validator;
		this.area = new Column(0, -1, column);
	}

	public ColCheckRule(int column, int startRow, Validator validator) {
		this.validator = validator;
		this.area = new Column(startRow, -1, column);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.CheckRule#getCheckArea()
	 */
	public Area getCheckArea() {
		return this.area;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.CheckRule#getValidator()
	 */
	public Validator getValidator() {
		return validator;
	}

}
