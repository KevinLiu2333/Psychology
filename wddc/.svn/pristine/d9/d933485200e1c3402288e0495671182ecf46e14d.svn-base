package com.wonders.wddc.tiles.tools;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串处理类。
 * <p>
 * 包含 org.apache.commons.lang.StringUtils 中没有的字符串处理方法。
 * 
 * @since 1.0.0
 */

public class StringUtil {

    /**
     * html文件中的特定替换
     * 
     * @param source
     *            String 源字符串
     * @return String 替换后的字符串
     */
    public static String htmlEncode(String source) {
        source = StringUtils.replace(source, "&", "&amp;");
        source = StringUtils.replace(source, "'", "&quot;");
        source = StringUtils.replace(source, "<", "&lt;");
        source = StringUtils.replace(source, ">", "&gt;");
        source = StringUtils.replace(source, " ", "&nbsp;");
        source = StringUtils.replace(source, "\"", "&quot;");
        return source;
    }

    /**
     * 替换回hmtl中的格式
     * 
     * @param source
     *            String 源字符串
     * @return String 替换后的字符串
     */
    public static String htmlDecode(String source) {
        source = StringUtils.replace(source, "&amp;", "&");
        source = StringUtils.replace(source, "&quot;", "'");
        source = StringUtils.replace(source, "&lt;", "<");
        source = StringUtils.replace(source, "&gt;", ">");
        source = StringUtils.replace(source, "&nbsp;", " ");
        source = StringUtils.replace(source, "&quot;", "\"");
        return source;
    }

    /**
     * 将Html中的<br>
     * 替换为回车
     * 
     * @param source
     *            String 源字符串
     * @return String 替换后的字符串
     */
    public static String htmlBrToLine(String source) {
        source = StringUtils.replace(source, "<br>", "\r\n");
        return source;
    }

    /**
     * 将回车替换为html中的<br>
     * 
     * @param source
     *            String 源字符串
     * @return String 替换后的字符串
     */
    public static String htmlLineToBr(String source) {
        source = StringUtils.replace(source, "\r\n", "<br>");
        source = StringUtils.replace(source, "\n", "<br>");
        return source;
    }

    /**
     * 中文转换为UNICODE字符
     * 
     * @param source
     *            String 源字符串
     * @return String 替换后的字符串
     */
    public static String gbToUnicode(String source) {
        try {
            return new String(source.getBytes("ISO8859_1"), "gb2312");
        } catch (Exception e) {
            return source;
        }
    }

    /**
     * UNICODE字符转换为中文
     * 
     * @param source
     *            String 源字符串
     * @return String 替换后的字符串
     */
    public static String unicodeToGB(String source) {
        try {
            return new String(source.getBytes("gb2312"), "ISO8859_1");
        } catch (Exception e) {
            return source;
        }
    }

    /**
     * 将中文文件名转为utf8格式
     * 
     * @param s
     *            原中文文件名
     * @return 转换后的utf8格式文件名
     */
    public static String gbToUtf8(String s) {
        if (s == null) {
            return null;
        }

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 0 && c <= 255) {
                sb.append(c);
            } else {
                byte[] b;
                try {
                    b = String.valueOf(c).getBytes("utf-8");
                } catch (Exception ex) {
                    b = new byte[0];
                }

                for (int j = 0; j < b.length; j++) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }

        return sb.toString();
    }

    /**
     * 将null转变为&nbsp;
     * 
     * @param input
     *            输入的字符串
     * @return String
     */
    public static String emptyObj2Blank(Object obj) {
        if (obj == null) {
            return "";
        } else if ("null".equals(obj.toString())) {
            return "";
        } else {
            return obj.toString();
        }
    }

}
