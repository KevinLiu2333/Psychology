/** 
 * @(#)SerializableUtils.java 2014-5-14
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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author xieguoking
 * @version $Revision$ 2014-5-14
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class SerializableUtils {

	/**
	 * 将序列化对象序列化后进行Base64编码，取UTF-8字节码
	 * 
	 * @param val
	 * @return
	 */
	public static byte[] toHexBytes(Serializable val) {

		try {
			return toHex(val).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new SerializationException("toHexBytes", e);
		}
	}

	/**
	 * 将Base64编码后的数据解码之后进行反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object fromHexBytes(byte[] bytes) {
		try {
			return fromHex(new String(bytes, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new SerializationException("fromHexBytes", e);
		}
	}

	public static String toHex(Serializable val) {
		return Base64Utils.encodeString(serialize(val));
	}

	public static Object fromHex(String data) {
		return unserialize(Base64Utils.decodeBytes(data));
	}

    /**
     * 将Serializable对象进行序列化
     * @param val
     * @return
     */
	public static byte[] serialize(Serializable val) {
		ByteArrayOutputStream buffer = null;
		ObjectOutputStream output = null;
		try {
			buffer = new ByteArrayOutputStream();
			output = new ObjectOutputStream(buffer);
			output.writeObject(val);
			output.flush();
			buffer.flush();
			return buffer.toByteArray();
		} catch (IOException e) {
			throw new SerializationException("serialize error!", e);
		}
		finally {
			if (output != null)
				try {
					output.close();
				}
				catch (IOException e) {
				}

			if (buffer != null)
				try {
					buffer.close();
				}
				catch (IOException e) {
				}
		}
	}

    /**
     * 将字节流反序列化为Serializable对象
     * @param array
     * @return
     */
	public static Object unserialize(byte[] array) {
		ByteArrayInputStream buffer = null;
		ObjectInputStream input = null;
		try {
			buffer = new ByteArrayInputStream(array);
			input = new ObjectInputStream(buffer);
			return input.readObject();
		} catch (IOException e) {
			throw new SerializationException("unserialize error!", e);
		} catch (ClassNotFoundException e) {
			throw new SerializationException("class not found!", e);
		}
		finally {
			if (buffer != null)
				try {
					buffer.close();
				} catch (IOException e) {
				}

			if (input != null)
				try {
					input.close();
				} catch (IOException e) {
				}
		}
	}
}
