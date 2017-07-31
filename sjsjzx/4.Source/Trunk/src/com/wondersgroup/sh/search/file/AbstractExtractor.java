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

import org.nutz.lang.Strings;

/**
 * 
 * @author fengjia
 *
 */
public class AbstractExtractor implements Extractor{
	/**
	 * 取得文件正文内容。
	 * 
	 * @param stream 文件流
	 * @return 正文内容
	 */
	public String extract(InputStream stream) throws FileExtractException {
		return "";
	}
	/**
	 * 取得map形式的文件内容，对于非网页文件，仅存正文内容
	 */
	public Map<String,String> mapExtract(InputStream stream) throws FileExtractException{
		try {
			Map<String,String> extractMap = new HashMap<String,String>();
			extractMap.put("content", extract(stream));
			return extractMap;
		} 
		catch (Throwable ex) {
			throw new FileExtractException(ex);
		}
	}
	/**
	 * 标准化处理结果字符串.
	 * @param str
	 * @return
	 */
	public String standardStr(String str){
		if(!Strings.isBlank(str)){
			str = str.replaceAll("\n", "");
			str = str.replaceAll("\r", "");
			str = str.replaceAll("\t", "");
			str = str.replaceAll("\"", "'");
			str = str.replaceAll(">", "&gt;");
			str = str.replaceAll("<", "&lt;");
		}
		return str;
	}
}
