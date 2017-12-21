package com.wonders.pzgl.util;

import java.util.HashSet;

public class ArrayUtil {
    /**
     * 将String类型的数组转变为某分隔符分隔的字符串
     * 
     * @param strArray
     *            String类型的数组
     * @param split
     *            分隔符
     * @return 逗号分隔的字符串
     */
    public static String strArray2StrSplit(String[] strArray, String split) {
        StringBuffer strSplit = new StringBuffer();

        if (strArray != null && strArray.length > 0) {
            for (int i = 0; i < strArray.length; i++) {
                if (i < strArray.length - 1)
                    strSplit.append(strArray[i]).append(split);
                else
                    strSplit.append(strArray[i]);
            }
        }

        return strSplit.toString();
    }
    /**
     * 将String类型的部分数组转变为某分隔符分隔的字符串
     * 
     * @param strArray
     *            String类型的数组
     * @param split
     *            分隔符
     * @param split
     *            转化截止的长度
     * @return 逗号分隔的字符串
     */
    public static String strPartArray2StrSplit(String[] strArray, String split,int length) {
        StringBuffer strSplit = new StringBuffer();

        if (strArray != null && length > 0) {
            for (int i = 0; i <length; i++) {
                if (i < length - 1)
                    strSplit.append(strArray[i]).append(split);
                else
                    strSplit.append(strArray[i]);
            }
        }

        return strSplit.toString();
    }
    /**
     * 将String类型的数组转变为某分隔符分隔的字符串
     * 
     * @param strArray
     *            String类型的数组
     * @param split
     *            分隔符
     * @return 逗号分隔的字符串
     */
    public static boolean strArrayContainsArray(String[] strArray,String[] array) {
        StringBuffer strSplit = new StringBuffer();
        HashSet strHashSet = new HashSet();
        HashSet hashSet = new HashSet();
        if (strArray != null && strArray.length > 0) {
            for (int i = 0; i < strArray.length; i++) {
            	strHashSet.add(strArray[i]);
            }
        }
        if (array != null && array.length > 0) {
            for (int i = 0; i < array.length; i++) {
            	hashSet.add(array[i]);
            }
        }

        return strHashSet.containsAll(hashSet);
    }
    
    /**
     * 将String类型的数组转变为逗号分隔的字符串
     * 
     * @param strArray
     *            String类型的数组
     * @return 逗号分隔的字符串
     */
    public static String strArray2StrComma(String[] strArray) {
        StringBuffer strComma = new StringBuffer();

        if (strArray != null && strArray.length > 0) {
            for (int i = 0; i < strArray.length; i++) {
                if (i < strArray.length - 1)
                    strComma.append("'").append(strArray[i]).append("',");
                else
                    strComma.append("'").append(strArray[i]).append("'");
            }
        } else
            strComma.append("''");

        return strComma.toString();
    }
}

