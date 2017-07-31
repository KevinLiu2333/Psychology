/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.wondersgroup.sh.search.util.StreamHelper;


/**
 * 取得 Microsoft Excel 文件正文内容。使用POI 3.5中的方法进行处理<br>
 * 
 */
public class ExcelExtractor extends AbstractExtractor {//modified by fengjia

	/**
	 * 取得 Microsoft Excel 文件正文内容, 文件后缀一般为 xls
	 * 
	 * @param stream
	 *            文件流
	 * @return 正文内容
	 */
	public String extract(InputStream stream) throws FileExtractException {
		try {
			POIFSFileSystem fs = new POIFSFileSystem(stream);
			org.apache.poi.hssf.extractor.ExcelExtractor extractor = new org.apache.poi.hssf.extractor.ExcelExtractor(
					fs);
			return standardStr(extractor.getText());
		} 
		catch (Throwable ex) {
			throw new FileExtractException(ex);
		}
	}
}
