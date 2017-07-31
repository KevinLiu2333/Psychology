/** 
 * @(#)POIExcelServiceImpl.java 2012-6-6
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

package com.wondersgroup.wssip.commons.file.excel.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.wondersgroup.framework.core.exception.BusinessException;
import com.wondersgroup.wssip.commons.file.ImportResult;
import com.wondersgroup.wssip.commons.file.excel.POIExcelService;
import com.wondersgroup.wssip.commons.file.excel.RowMapper;

/**
 * @author Su Hua-Lin
 * @version $Revision$ 2012-6-6
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class POIExcelServiceImpl implements POIExcelService {
	protected final Log logger = LogFactory.getLog(getClass());

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream, int)
	 */
	public ImportResult read(InputStream inputStream, int startRow, int columns) {
		return read(inputStream, startRow, -1, columns);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream, int, int)
	 */
	public ImportResult read(InputStream inputStream, int startRow, int endRow, int columns) {
		ImportResult importResult = new ImportResult();
		try {
			HSSFWorkbook wb = new HSSFWorkbook(inputStream);
			HSSFSheet sheet = wb.getSheetAt(0);
			if (startRow < 0) {
				startRow = sheet.getFirstRowNum();
			}
			else {
				startRow = startRow - 1;
			}
			if (endRow < 0) {
				endRow = sheet.getLastRowNum();
			}
			else {
				endRow = endRow - 1;
			}
			if (startRow <= endRow) {
				importResult.setLength(endRow - startRow + 1);
				List<List<String>> resultList = new ArrayList<List<String>>(importResult.getLength());
				for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
					HSSFRow row = sheet.getRow(i);
					String[] rowDatas = new String[columns];
					for (int j = 0; j < Math.min(row.getLastCellNum(), columns); j++) {
						HSSFCell cell = row.getCell((short) j);
						if (cell != null) {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								rowDatas[j] = String.valueOf(cell.getNumericCellValue());
							}
							else {
								rowDatas[j] = cell.getRichStringCellValue().getString().trim();
							}
						}
					}
					resultList.add(Arrays.asList(rowDatas));
				}
				importResult.setResultList(resultList);
			}
		}
		catch (IOException ex) {
			logger.error(ex);
			throw new BusinessException("文件读取失败", ex);
		}
		finally {
			try {
				inputStream.close();
			}
			catch (IOException e) {//
			}
		}
		return importResult;
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.ExcelService#read(java.io.InputStream,
	 *      com.wondersgroup.wssip.commons.file.excel.RowMapper, int)
	 */
	public <T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper, int startRow, int columns) {
		return read(inputStream, rowMapper, startRow, -1, columns);
	}

	/**
	 * @see com.wondersgroup.wssip.commons.file.excel.POIExcelService#read(java.io.InputStream,
	 *      com.wondersgroup.wssip.commons.file.excel.RowMapper, int, int, int)
	 */
	public <T> List<T> read(InputStream inputStream, RowMapper<T> rowMapper, int startRow, int endRow, int columns) {
		ImportResult importResult = read(inputStream, startRow, endRow, columns);
		List<List<String>> resultList = importResult.getResultList();
		List<T> result = new ArrayList<T>(importResult.getLength());
		if (importResult.getLength() > 0) {
			for (List<String> list : resultList) {
				result.add(rowMapper.mapRow(list));
			}
		}
		return result;
	}
}
