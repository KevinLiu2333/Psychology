/** 
 * @(#)Column.java 2008-11-20
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
 * @version $Revision$ 2008-11-20
 * @author $Date$ by $Author$
 * @since 1.0
 */
public class Column implements Area {

	private int startRow;

	private int endRow;

	private int column;

	public Column(int startRow, int endRow, int column) {
		this.startRow = startRow;
		this.endRow = endRow;
		this.column = column;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.Area#contains(int, int)
	 */
	public boolean contains(int row, int col) {
		if (this.column == col && this.startRow <= row) {
			if (this.endRow != -1 && row >= this.endRow) {
				return false;
			}
			else {
				return true;
			}
		}
		return false;
	}

}
