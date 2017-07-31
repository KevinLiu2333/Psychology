/** 
 * @(#)CheckRule.java 2008-10-30
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
 * @author Yao Jianping
 * @version $Revision: 5495 $ 2008-10-30
 * @author $Date: 2008-10-31 11:46:16 +0800 $ by $Author: Yaojp $
 * @since 1.0
 */
public class CellCheckRule implements CheckRule {

	private int row = -1;// 应用于某列所有的行

	private int col = -1;// 应用于某行所有的列

	private Validator validator;

	private Area area;

	public CellCheckRule(int row, int col, Validator validator) {
		this.row = row;
		this.col = col;
		this.validator = validator;
		this.area = new Cell(row, col);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.CheckRule#getCheckArea()
	 */
	public Area getCheckArea() {
		return this.area;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.CheckRule#getRow()
	 */
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.CheckRule#getCol()
	 */
	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.CheckRule#getValidator()
	 */
	public Validator getValidator() {
		return validator;
	}

	public void setValidator(Validator validator) {
		this.validator = validator;
	}

}
