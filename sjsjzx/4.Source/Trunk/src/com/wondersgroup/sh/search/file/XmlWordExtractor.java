/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.io.InputStream;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.openxml4j.opc.Package;


/**
 * 取得 Microsoft Word 文件正文内容 (2007版) <br>
 * 
 * @author 虞越
 * @version 1.6 <br>
 *          created 2008/10/13
 */

public class XmlWordExtractor extends AbstractExtractor {//modified by fengjia

	/**
	 * 取得 Microsoft Word 文件正文内容 (2007版), 文件后缀一般为 docx
	 * 
	 * @param stream
	 *            文件流
	 * @return 正文内容
	 */
	public String extract(InputStream stream) throws FileExtractException {
		try {
			Package p = Package.open(stream);
			XWPFWordExtractor extractor = new XWPFWordExtractor(p);
			return standardStr(extractor.getText());
		} 
		catch (Throwable ex) {
			throw new FileExtractException(ex);
		}
	}

}
