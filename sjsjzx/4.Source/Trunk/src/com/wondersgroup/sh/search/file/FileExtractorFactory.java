/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.file;

import java.util.HashMap;
import java.util.Map;

import com.wondersgroup.sh.search.enums.FileTypeEnum;

/**
 * A factory for creating FileExtractor objects.
 */
public class FileExtractorFactory {
	/** The built in extrators. */
	private static Map builtInExtrators = new HashMap();
	
	/** The Constant HTML. */
	public static final Extractor HTML = new HtmlExtractor();
	
	/** The Constant WORD. */
	public static final Extractor WORD = new WordExtractor();
	
	/** The Constant XML_WORD. */
	public static final Extractor XML_WORD = new XmlWordExtractor();
	
	/** The Constant EXCEL. */
	public static final Extractor EXCEL = new ExcelExtractor();
	
	/** The Constant XML_EXCEL. */
	public static final Extractor XML_EXCEL = new XmlExcelExtractor();
	
	/** The Constant PPT. */
	public static final Extractor PPT = new PowerPointExtractor();
	
	/** The Constant XML_PPT. */
	public static final Extractor XML_PPT = new XmlPowerPointExtractor();
	
	/** The Constant TXT. */
	public static final Extractor TXT = new TextExtractor();
	
	/** The Constant PDF. */
	public static final Extractor PDF = new PdfExtractor();
	
	/** The Constant RTF. */
	public static final Extractor RTF = new RtfExtractor();
	
	static {
		builtInExtrators.put(FileTypeEnum.HTM, HTML);
		builtInExtrators.put(FileTypeEnum.HTML, HTML);
		builtInExtrators.put(FileTypeEnum.SHTML, HTML);//by fengjia
		builtInExtrators.put(FileTypeEnum.MHT, HTML);
		builtInExtrators.put(FileTypeEnum.DOC, WORD);
		builtInExtrators.put(FileTypeEnum.DOCX, XML_WORD);
		builtInExtrators.put(FileTypeEnum.XLS, EXCEL);
		builtInExtrators.put(FileTypeEnum.XLSX, XML_EXCEL);	
		builtInExtrators.put(FileTypeEnum.PPT, PPT);	
		builtInExtrators.put(FileTypeEnum.PPTX, XML_PPT);	
		builtInExtrators.put(FileTypeEnum.TXT, TXT);	
		builtInExtrators.put(FileTypeEnum.PDF, PDF);	
		builtInExtrators.put(FileTypeEnum.RTF, RTF);	
	}
	
	/**
	 * Instantiates a new file extractor factory.
	 */
	private FileExtractorFactory() {
	}

	/**
	 * Gets the file ext.
	 * 
	 * @param path the path
	 * 
	 * @return the file ext
	 */
	public static String getFileExt(String path) {
		int index = path.lastIndexOf(".");
		return index >= 0 ? path.substring(index + 1) : path;
	}
	
	/**
	 * Gets the extractor.
	 * 
	 * @param path the path
	 * 
	 * @return the extractor
	 * 
	 * @throws UnsupportedFileTypeException the unsupported file type exception
	 */
	public static Extractor getExtractor(String path) throws UnsupportedFileTypeException {
		String fileExt = getFileExt(path);
		FileTypeEnum fileType = FileTypeEnum.getEnum(fileExt);
		Extractor extractor = (Extractor)builtInExtrators.get(fileType);
		if( extractor == null ) {
			throw new UnsupportedFileTypeException("系统目前不支持文件'" + path + "'的格式。");
		}
		return extractor;
	}
}
