/** 
 * @(#)ExcelServiceImpl.java 2008-10-15
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

package com.wondersgroup.wssip.commons.file.excel.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jxcell.CellException;
import com.jxcell.View;
import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.file.CheckRule;
import com.wondersgroup.wssip.commons.file.CheckRules;
import com.wondersgroup.wssip.commons.file.ImportResult;
import com.wondersgroup.wssip.commons.file.ValidateFailedResult;
import com.wondersgroup.wssip.commons.file.ValidationException;
import com.wondersgroup.wssip.commons.file.excel.ExcelService;
import com.wondersgroup.wssip.commons.file.excel.RowMapper;

/**
 * @author Yao Jianping
 * @version $Revision: 8509 $ 2008-10-15
 * @author $Date: 2008-12-09 13:17:38 +0800 (周二, 09 十二月 2008) $ by $Author: Suhl $
 * @since 1.0
 */
public class ExcelServiceImpl implements ExcelService {
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#validate(java.io.InputStream,
	 *      com.wondersgroup.wssip.commons.file.CheckRules)
	 */
	public boolean validate(InputStream inputStream, CheckRules checkRules) {
		return validate(inputStream, checkRules, null);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#validate(java.io.InputStream,
	 *      com.wondersgroup.wssip.commons.file.CheckRules, java.util.List)
	 */
	public boolean validate(InputStream inputStream, CheckRules checkRules, List<ValidateFailedResult> failedResults) {
		View view = new View();
		try {
			view.read(inputStream);
			for (int i = 0; i <= view.getLastRow(); i++) {
				for (int j = 0; j <= view.getLastCol(); j++) {
					String text = view.getText(i, j);
					List<CheckRule> checkRulesList = checkRules.getCheckRulesWith(i, j);
					for (CheckRule checkRule : checkRulesList) {
						try {
							checkRule.getValidator().validate(text);
						}
						catch (ValidationException ex) {
							if (failedResults == null) {
								throw new BusinessException("[" + i + ":" + j + "]:" + ex.getMessage());
							}
							else {
								failedResults.add(new ValidateFailedResult(i, j, text, ex.getMessage(), ex
										.getValidator()));
							}
						}
					}
				}
			}
			view.cancelEdit();
			view.releaseLock();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch (CellException ex) {
			ex.printStackTrace();
		}
		return failedResults == null ? true : failedResults.size() == 0;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream)
	 */
	public ImportResult read(InputStream inputStream) {
		return read(inputStream, 1);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream, int)
	 */
	public ImportResult read(InputStream inputStream, int startRow) {
		ImportResult importResult = new ImportResult();
		View view = new View();
		try {
			view.read(inputStream);
			importResult.setLength(view.getLastRow() + 1);
			List<List<String>> resultList = new ArrayList<List<String>>();
			for (int i = startRow - 1; i <= view.getLastRow(); i++) {
				List<String> thisRow = new ArrayList<String>();
				for (int j = 0; j <= view.getLastCol(); j++) {
					String text = view.getText(i, j);
					thisRow.add(text);
				}
				resultList.add(thisRow);
			}
			importResult.setResultList(resultList);
		}
		catch (IOException ex) {
			logger.error(ex);
			throw new BusinessException("文件读取失败", ex);
		}
		catch (CellException ex) {
			logger.error(ex);
			throw new BusinessException("Excel文件读取失败", ex);
		}
		return importResult;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream, int, int)
	 */
	public ImportResult read(InputStream inputStream, int startRow, int endRow) {
		ImportResult importResult = new ImportResult();
		View view = new View();
		try {
			view.read(inputStream);
			importResult.setLength(endRow - startRow + 1);
			List<List<String>> resultList = new ArrayList<List<String>>();
			for (int i = startRow - 1; i <= endRow; i++) {
				List<String> thisRow = new ArrayList<String>();
				for (int j = 0; j <= view.getLastCol(); j++) {
					String text = view.getText(i, j);
					thisRow.add(text);
				}
				resultList.add(thisRow);
			}
			importResult.setResultList(resultList);
		}
		catch (IOException ex) {
			logger.error(ex);
			throw new BusinessException("文件读取失败", ex);
		}
		catch (CellException ex) {
			logger.error(ex);
			throw new BusinessException("Excel文件读取失败", ex);
		}
		return importResult;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream,
	 *      com.wondersgroup.wssip.commons.file.excel.RowMapper)
	 */
	public <T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper) {
		return read(inputStream, rowMapper, 1);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream,
	 *      com.wondersgroup.wssip.commons.file.excel.RowMapper, int)
	 */
	public <T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper, int startRow) {
		ImportResult importResult = read(inputStream, startRow);
		List<List<String>> resultList = importResult.getResultList();
		List<T> result = new ArrayList<T>();
		for (List<String> list : resultList) {
			result.add(rowMapper.mapRow(list));
		}
		return result;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#write(java.util.List, com.jxcell.View)
	 */
	public void write(List<List<String>> datas, View view) {
		try {
			for (int i = 0; i < datas.size(); i++) {
				List<String> dataRow = datas.get(i);
				for (int j = 0; j < dataRow.size(); j++) {
					view.setText(i, j, dataRow.get(j));
				}
			}
		}
		catch (CellException ex) {
			logger.error(ex);
			throw new BusinessException("Excel文件导出失败", ex);
		}
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#write(java.util.List, java.io.OutputStream)
	 */
	public View write(List<List<String>> datas, OutputStream outputStream) {
		View view = new View();
		try {
			write(datas, view);
			view.write(outputStream, View.eFileExcel);
		}
		catch (IOException ex) {
			logger.error(ex);
			throw new BusinessException("文件写入失败", ex);
		}
		catch (CellException ex) {
			logger.error(ex);
			throw new BusinessException("Excel文件导出失败", ex);
		}
		return view;
	}
}
