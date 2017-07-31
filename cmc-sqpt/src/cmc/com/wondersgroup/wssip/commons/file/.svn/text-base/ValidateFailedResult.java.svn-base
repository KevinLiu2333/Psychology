/** 
 * @(#)ValidateFailedResult.java Dec 9, 2008
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
 * 检查失败结果
 * 
 * @author suhualin
 * @version $Revision: 8509 $ Dec 9, 2008
 * @author (lastest modification by $Author: Suhl $)
 * @since 1.0
 */
public class ValidateFailedResult {
	private int row;

	private int column;

	private String value;

	private String message;

	private Validator validator;

	/**
	 * @param row
	 * @param column
	 * @param value
	 * @param message
	 * @param validator
	 */
	public ValidateFailedResult(int row, int column, String value, String message, Validator validator) {
		super();
		this.row = row;
		this.column = column;
		this.value = value;
		this.message = message;
		this.validator = validator;
	}

	public ValidateFailedResult() {
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer("{").append("行:").append(getRow() + 1).append(",列:").append(getColumn() + 1).append(
				"值:").append(getValue()).append(",信息:").append(getMessage()).toString();
	}
}
