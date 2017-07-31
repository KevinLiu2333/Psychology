/** 
 * @(#)POIExcelService.java 2012-6-6
 * 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
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

import java.io.InputStream;
import java.util.List;

import com.wondersgroup.wssip.commons.file.ImportResult;

/**
 * @author Su Hua-Lin
 * @version $Revision$ 2012-6-6
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public interface POIExcelService {

	/**
	 * 从指定的行开始，根据一个输入流导入Excel文件
	 * 
	 * @param inputStream 输入流
	 * @param startRow 开始行,从1开始计算
	 * @param columns 列数
	 * @return
	 */
	ImportResult read(InputStream inputStream, int startRow, int columns);

	/**
	 * 读取指定Excel文件，指定起始行和结束行
	 * 
	 * @param inputStream 输入流
	 * @param startRow 起始行，从1开始计算
	 * @param endRow 结束行,从1开始计算
	 * @return 导入结果
	 */
	ImportResult read(InputStream inputStream, int startRow, int endRow, int columns);

	/**
	 * 从指定的行开始，根据一个输入流导入Excel文件，并转换到DTO列表
	 * 
	 * @param inputStream 输入流
	 * @param rowMapper 行映射
	 * @param startRow 开始行,从1开始计算
	 * @param columns 列数
	 * @return
	 */
	<T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper, int startRow, int columns);

	/**
	 * 从指定的行开始，根据一个输入流导入Excel文件，并转换到DTO列表
	 * 
	 * @param <T>
	 * @param inputStream 输入流
	 * @param rowMapper 行映射
	 * @param startRow 起始行，从1开始计算
	 * @param endRow 结束行,从1开始计算
	 * @param columns 列数
	 * @return
	 */
	<T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper, int startRow, int endRow, int columns);
}
