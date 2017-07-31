/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;


// TODO: Auto-generated Javadoc
/**
 * 字节流的相关操作<br>.
 * 
 * @author 虞越
 * @version 1.6 <br>
 * created 2002/09/26 <br>
 * revised by 虞越 2003/08/01 添加 toOutputStream 方法 <br>
 * revised by 虞越 2003/11/18 添加 toWriter 及 toStringBuffer 方法
 */

public class StreamHelper {

	/**
	 * 读取输入流中的内容.
	 * 
	 * @param is 输入流, 不能为 null
	 * 
	 * @return 成功返回字节流, 失败或异常抛出例外
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] toByteArray(InputStream is) throws IOException {
		if (is == null) {
			return null;
		} else {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			toOutputStream(is, baos);
			return baos.toByteArray();
		}
	}

	/**
	 * 将输入流的内容置入输出流中.
	 * 
	 * @param is 输入流, 不能为 null
	 * @param os 输出流, 不能为 null
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void toOutputStream(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[2048];
		int bytesRead;
		while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
	}

	/**
	 * 读取 reader 中的内容.
	 * 
	 * @param reader reader
	 * 
	 * @return 成功返回字符串, 失败或异常抛出例外
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static StringBuffer toStringBuffer(Reader reader) throws IOException {
		if (reader == null) {
			return null;
		} else {
			StringWriter writer = new StringWriter();
			toWriter(reader, writer);
			return writer.getBuffer();
		}
	}

	/**
	 * 将 reader 中的内容置入 writer 中.
	 * 
	 * @param reader reader
	 * @param writer writer
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void toWriter(Reader reader, Writer writer) throws IOException {
		char[] buffer = new char[2048];
		int charsRead;
		while ((charsRead = reader.read(buffer, 0, 1024)) != -1) {
			writer.write(buffer, 0, charsRead);
		}
	}
}
