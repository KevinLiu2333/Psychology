/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.bridge.builtin;

import org.apache.log4j.Logger;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Map;

import com.wondersgroup.sh.search.SearchException;
import com.wondersgroup.sh.search.bridge.ParameterizedBridge;
import com.wondersgroup.sh.search.bridge.StringBridge;
import com.wondersgroup.sh.search.file.Extractor;
import com.wondersgroup.sh.search.file.FileExtractException;
import com.wondersgroup.sh.search.file.FileExtractorFactory;
import com.wondersgroup.sh.search.file.UnsupportedFileTypeException;

/**
 * Map a Blob element.
 */
public class BlobBridge implements StringBridge, ParameterizedBridge {
	private static final Logger logger = Logger.getLogger(BlobBridge.class);
	public static final String PARAM_FILE_TYPE = "fileType";
	
	private String fileType;
	
	public void setParameterValues(Map parameters) {
		fileType = (String)parameters.get(PARAM_FILE_TYPE);
	}
	
	public String objectToString(Object object) {
		if( object == null ) 
			return null;
		
		String value = null;
		try {
			Extractor extractor = FileExtractorFactory.getExtractor(fileType);
			value = extractor.extract(((Blob)object).getBinaryStream());
		} 
		catch (UnsupportedFileTypeException e) {
			logger.warn(e.getMessage());
		} 
		catch (FileExtractException e) {	// igonre this error because file may be too strange, its content can't be extracted forever.
			logger.error("抽取文件中的文本出错。", e);
		} 
		catch (SQLException e) {
			throw new SearchException("抽取blob中的文本出错。", e);
		}
		
		return value;
	}
}
