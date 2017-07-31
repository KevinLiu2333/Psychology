/** 
 * @(#)ExcelService.java 2008-10-15
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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.jxcell.View;
import com.wondersgroup.wssip.commons.file.CheckRules;
import com.wondersgroup.wssip.commons.file.ImportResult;
import com.wondersgroup.wssip.commons.file.ValidateFailedResult;

/**
 * Excel文件的导入导出
 * 
 * @author Yao Jianping
 * @version $Revision: 8509 $ 2008-10-15
 * @author $Date: 2008-12-09 13:17:38 +0800 (周二, 09 十二月 2008) $ by $Author: Suhl $
 * @since 1.0
 */
public interface ExcelService {

	/**
	 * 导入前校验文件
	 * 
	 * @param inputStream
	 * @return
	 */
	boolean validate(InputStream inputStream, CheckRules checkRules);

	/**
	 * 导入前校验文件
	 * 
	 * @param inputStream 导入输入流
	 * @param checkRules 规则集
	 * @param failedResults 校验失败结果集
	 * @return 校验结果
	 */
	boolean validate(InputStream inputStream, CheckRules checkRules, List<ValidateFailedResult> failedResults);

	/**
	 * 根据一个输入流导入Excel文件
	 * 
	 * @param inputStream
	 * @return 预览结果
	 */
	ImportResult read(InputStream inputStream);

	/**
	 * 从指定的行开始，根据一个输入流导入Excel文件
	 * 
	 * @param inputStream
	 * @param startRow
	 * @return
	 */
	ImportResult read(InputStream inputStream, int startRow);

	/**
	 * 读取指定Excel文件，指定起始行和结束行
	 * 
	 * @param inputStream 输入流
	 * @param startRow 起始行
	 * @param endRow 结束行
	 * @return 导入结果
	 */
	ImportResult read(InputStream inputStream, int startRow, int endRow);

	/**
	 * 根据一个输入流导入Excel文件，并转换到DTO列表
	 * 
	 * @param <T>
	 * @param inputStream 输入流
	 * @param rowMapper 行转换器
	 * @return 列表
	 */
	<T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper);

	/**
	 * 从指定的行开始，根据一个输入流导入Excel文件，并转换到DTO列表
	 * 
	 * @param <T>
	 * @param inputStream
	 * @param rowMapper
	 * @param startRow 指定行，从1开始计算
	 * @return
	 */
	<T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper, int startRow);

	/**
	 * 导出到JxCell视图，配合Struts2的ExcelResult使用
	 * 
	 * @param datas 数据
	 * @param view jxcell视图
	 */
	void write(List<List<String>> datas, View view);

	/**
	 * 将表格数据直接导入到Excel文件
	 * 
	 * @param outputStream 输出流
	 * @param datas 原始的表格数据
	 * @return
	 */
	View write(List<List<String>> datas, OutputStream outputStream);
}
