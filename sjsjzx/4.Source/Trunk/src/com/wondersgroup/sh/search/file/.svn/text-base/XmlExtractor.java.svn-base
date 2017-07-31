/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 * 取得 xml 文件正文内容<br>
 * 
 * @author 虞越
 * @version 1.5 <br>
 *          created 2003/08/06 <br>
 *          revised by 虞越 2008/05/29 统一接口 <br>
 *          revised by 虞越 2009/04/20 使用 StAX 来解析 XML 文件 <br>
 *          revised by 虞越 2009/04/27 把抽取的文本内容用空格分隔开 <br>
 */

public class XmlExtractor extends AbstractExtractor {//modified by fengjia

	/**
	 * 取得 xml 文件正文内容, 文件后缀一般为 xml
	 * 
	 * @param stream
	 *            文件流
	 * @return 正文内容
	 */
	public String extract(InputStream stream) throws FileExtractException {
		try {
			StringBuffer sb = new StringBuffer("");
			XMLInputFactory xmlif = XMLInputFactory.newInstance();
			XMLStreamReader xmlr = xmlif.createXMLStreamReader(stream);
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.CHARACTERS:
					String text = xmlr.getText();
					sb.append(text + " ");
				}
				xmlr.next();
			}
			return standardStr(new String(sb));
		} catch (Throwable ex) {
			throw new FileExtractException(ex);
		}
	}

}
