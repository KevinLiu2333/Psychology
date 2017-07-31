/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.io.InputStream;


/**
 * 取得 Microsoft Word 文件正文内容<br>
 * 
 * @author 杨闪, 虞越
 * @version 1.6 <br>
 *          created 2003/03/31 <br>
 *          revised by 虞越 2003/08/05 实现 IParser 接口, parse 方法改为非静态方法 <br>
 *          revised by 虞越 2008/05/29 统一接口 <br>
 *          revised by 虞越 2008/08/10 根据最新的POI接口, 改换了调用方法 <br>
 *          revised by 虞越 2008/10/08 换用最新版的POI 3.5中的方法进行处理
 */

public class WordExtractor extends AbstractExtractor {//modified by fengjia

	/**
	 * 取得 Microsoft Word 文件正文内容, 文件后缀一般为 doc
	 * 
	 * @param stream
	 *            文件流
	 * @return 正文内容
	 */
	public String extract(InputStream stream) throws FileExtractException {
		try {
			org.apache.poi.hwpf.extractor.WordExtractor extractor = new org.apache.poi.hwpf.extractor.WordExtractor(
					stream);
			return standardStr(extractor.getText());
		} 
		catch (Throwable ex) {
			throw new FileExtractException(ex);
		}
	}

}
