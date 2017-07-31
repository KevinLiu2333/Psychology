/** 
 * @(#)ImportResult.java 2008-10-13
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

import java.util.List;

import com.wondersgroup.wssip.commons.file.excel.MatrixDataUtils;

/**
 * @author Yao Jianping
 * @version $Revision: 4576 $ 2008-10-13
 * @author $Date: 2008-10-21 11:28:18 +0800 (周二, 21 十月 2008) $ by $Author: Yaojp $
 * @since 1.0
 */
public class ImportResult {

	private List<List<String>> resultList;

	private int length;

	public List<List<String>> getResultList() {
		return resultList;
	}

	public void setResultList(List<List<String>> resultList) {
		this.resultList = resultList;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Object[][] getResultArray() {
		return MatrixDataUtils.listToArray(resultList);
	}

}
