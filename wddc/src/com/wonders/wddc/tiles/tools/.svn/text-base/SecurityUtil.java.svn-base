package com.wonders.wddc.tiles.tools;

import java.security.MessageDigest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

/**
 * 密码加密解密工具。
 * 使用org.apache.commons.lang.RandomStringUtils
 * @since 1.0.0
 */

public class SecurityUtil {

    /** log4j日志 */
    private static Logger logger = Logger.getLogger(SecurityUtil.class);

    /**
     * 取得MD5加密字符串。
     * 
     * @param str
     *            加密前的字符串
     * @return 加密后的字符串
     */
    public static String getMD5Password(String str) {
        String digest = "";

        try {
            MessageDigest currentAlgorithm = MessageDigest.getInstance("md5");
            currentAlgorithm.reset();
            byte[] mess = str.getBytes();
            byte[] hash = currentAlgorithm.digest(mess);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i];
                if (v < 0) {
                    v = 256 + v;
                }
                if (v < 16) {
                    digest += "0";
                }
                digest += Integer.toString(v, 16).toUpperCase() + "";
            }
        } catch (Exception ex) {
            logger.warn("取得MD5加密字符串出错。", ex);
            digest = str;
        }

        return digest;
    }

    /**
     * 产生随机密码。
     * 
     * @param count
     *            密码长度
     * @return 随机密码
     */
    public static String createRandomPassword(int count) {
        if (count < 1) {
            count = 8;
        }
        return RandomStringUtils.randomAlphanumeric(count);
    }

}
