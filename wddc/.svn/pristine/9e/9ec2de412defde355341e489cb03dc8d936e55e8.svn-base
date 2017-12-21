/**
 * @(#)StringUtils.java 2014-5-26
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

package com.wonders.sjic;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 字符串操作类
 * @author xieguoking
 * @author ($Date$ modification by $Author$)
 * @version $Revision$ 2014-5-26
 * @since 1.0
 */
public class StringUtils {

    /**
     * UTF8编码器
     */
    public static final Charset UTF8 = Charset.forName("UTF-8");

    public static final boolean isEmpty(String val) {
        return val == null || val.trim().length() == 0;
    }

    /**
     * 将指定字节数组采用UTF-8编码成字符串
     * @param bytes
     * @return
     */
    public static String createString(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将字符串按照UTF-8解码成字节数组
     * @param str
     * @return
     */
    public static byte[] createBytes(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


}
