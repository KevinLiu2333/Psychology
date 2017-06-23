package com.klsw.piano.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 处理字符串的工具类
 *
 * @author liukun
 */
public class _StringUtils {

    /**
     * 获取token
     *
     * @param password 数据库密码
     * @return
     */
    public static String getToken(String password) {
        String salt = password.substring(0, 2);
        StringBuffer sb = new StringBuffer(salt);
        sb.append(new String(PasswdEncryption.getSaltOfASCII(6)));
        return sb.toString();
    }

    public static String getSha1(String data) {
        StringBuffer buf = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for (int i = 0; i < bits.length; i++) {
                int a = bits[i];
                if (a < 0) a += 256;
                if (a < 16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }

}


