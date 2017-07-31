/**
 * @(#)Base64Utils.java 2014-4-9
 *
 * Copyright (c) 1995-2014 Wonders Information Co.,Ltd.
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

package com.wondersgroup.cmc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

import org.apache.commons.codec.binary.Base64;

/**
 * BASE64编码解码工具
 * 支持编码之后进行压缩
 * @author xieguoking
 * @version $Revision$ 2014-4-9
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class Base64Utils {

	static int BUFFER_SIZE = 1024;

	/**
	 * 是否进行压缩
	 */
	static boolean COMPRESSED = false;

	public static byte[] decompress(byte[] data) {
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
			InflaterInputStream iis = new InflaterInputStream(bis);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] bytes = new byte[BUFFER_SIZE];
			int readBytes = -1;
			while ((readBytes = iis.read(bytes)) > 0) {
				bos.write(bytes, 0, readBytes);
			}
			return bos.toByteArray();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] compress(byte[] data) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DeflaterOutputStream dos = new DeflaterOutputStream(bos);//Deflater.BEST_COMPRESSION
			dos.write(data);
			dos.finish();
			dos.flush();
			return bos.toByteArray();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字节流编码先采用Deflater压缩，然后编码成Base64字符串
	 *
	 * @param data 编码字节数组
	 * @return
	 */
	public static String encodeString(byte[] data) {
		if (COMPRESSED) {
			data = compress(data);
		}
		return Base64.encodeBase64String(data);
	}

	/**
	 * 将Base64字符串解码
	 *
	 * @param base64Str
	 * @return
	 */
	public static byte[] decodeBytes(String base64Str) {

		byte[] data = null;
		try {
			byte[] b = base64Str.getBytes("UTF-8");
			if(Base64.isBase64(b)){
				data = Base64.decodeBase64(base64Str.getBytes("UTF-8"));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		if (COMPRESSED) {
			data = decompress(data);
		}

		return data;
	}

	public static void main(String[] args) {
		/*
		try {
			String text = "万达信息股份有限公司aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
			byte[] bytes = text.getBytes("UTF-8");
			String base64Str = encodeString(bytes);
			System.out.println(String.format("原始长度：%d,编码后长度:%d", bytes.length, base64Str.getBytes("UTF-8").length));
			System.out.println(base64Str);
			System.out.println(new String(decodeBytes(base64Str)));

		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		*/

		String text = "xieguojun";
		byte[] bytes = text.getBytes();
		String base64Str = encodeString(bytes);
		System.out.println(String.format("原始长度：%d,编码后长度:%d", bytes.length, base64Str.getBytes().length));
		System.out.println(base64Str);
		System.out.println(new String(decodeBytes(base64Str)));
	}


}
