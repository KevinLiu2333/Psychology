/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.searchengine.lucene.LucenePDFDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 * 取得 Adobe Pdf 文件正文内容。使用pdfbox实现。<br>
 * 
 */

public class PdfExtractor extends AbstractExtractor {//modified by fengjia
	private static final Logger logger = Logger.getLogger(PdfExtractor.class);

	/**
	 * 取得 Adobe Pdf 文件正文内容, 文件后缀一般为 pdf
	 * 
	 * @param stream 
	 *            文件流
	 * @return 正文内容
	 */
	public String extract(InputStream stream) throws FileExtractException {
		PDDocument document = null;
		
		try {
			document = PDDocument.load(stream);
			PDFTextStripper stripper = new PDFTextStripper();
			return standardStr(stripper.getText(document));
		} 
		catch (Throwable ex) {
			throw new FileExtractException("抽取PDF文件内容失败", ex);
		}
		finally {
			if (document != null) {
				try {
					document.close();
					document = null;
				} catch (IOException ex) {
					logger.error("关闭PDF文件错误。", ex);
				}
			}
		}		
	}
	
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("D:/市委宣传部/lucene-pdf-925/4.pdf");
		Extractor extractor = new PdfExtractor();
		String text = extractor.extract(is);
		System.out.println(text);
	}
}
