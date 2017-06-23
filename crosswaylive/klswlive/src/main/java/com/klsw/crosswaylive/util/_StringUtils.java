package com.klsw.crosswaylive.util;

import java.util.Random;

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
     * @return 数据
     */
    public static String getToken(String password) {
        String salt = password.substring(0, 2);
        StringBuffer sb = new StringBuffer(salt);
        sb.append(new String(PasswdEncryption.getSaltOfASCII(6)));
        return sb.toString();
    }

    /**
     * 获取游客用户名
     *
     * @return 游客用户名
     */
    public static String getVisitor() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
