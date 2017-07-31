/** 
 * @(#)MatrixDataUtils.java 2008-10-13
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

package com.wondersgroup.wssip.commons.file.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * 二维矩阵的表示与转换
 * 
 * @author Yao Jianping
 * @version $Revision: 4576 $ 2008-10-13
 * @author $Date: 2008-10-21 11:28:18 +0800 (周二, 21 十月 2008) $ by $Author: Yaojp $
 * @since 1.0
 */
public class MatrixDataUtils {
	/**
	 * 二维集合转为二维数组
	 * 
	 * @param resultList 二维集合
	 * @return 二维数组
	 */
	public static Object[][] listToArray(List<List<String>> resultList) {
		int size = resultList.size();
		Object obj[][] = new Object[size][];
		if (size == 0) {
			return null;
		}
		for (int i = 0; i < size; i++) {
			obj[i] = resultList.get(i).toArray();
		}
		return obj;
	}

	/**
	 * 将二维数组转为二维ArrayList
	 * 
	 * @param arr 二维数组
	 * @return 二维ArrayList
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<ArrayList> arrayToList(Object[][] arr) {
		ArrayList<ArrayList> all = new ArrayList<ArrayList>();
		for (int i = 0; i < arr.length; i++) {
			all.add(arrayToList(arr[i]));
		}
		return all;
	}

	/**
	 * 将一维数组转为List对象
	 * 
	 * @param array 一维数组
	 * @return List对象
	 */
	public static ArrayList<Object> arrayToList(Object[] array) {
		ArrayList<Object> result = new ArrayList<Object>();
		for (Object obj : array) {
			result.add(obj);
		}
		return result;
	}
}
