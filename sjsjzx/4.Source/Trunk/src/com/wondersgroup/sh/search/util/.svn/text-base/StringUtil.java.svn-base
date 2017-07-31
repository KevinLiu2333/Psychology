/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.util;

import org.apache.log4j.Logger;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.sh.search.WdisException;

import java.io.IOException;
import java.util.*;

public class StringUtil {
	private static final Logger logger = Logger.getLogger(StringUtil.class);

	/**
	 * 将String按指定字符分隔成String数组，默认分隔符为";"
	 * <pre>
	 * 1、如果str为null，则返回null
	 * 2、如果分隔后的字符串数组中某个元素为""，则该元素会被删除
	 * </pre>.
	 * 
	 * @param str 字符串
	 * @param separator 分隔符
	 * 
	 * @return 分隔后的String数组
	 */
	static public String[] getString2StringArray(String str, String separator) {
		if (separator == null || "".equals(separator)) {
			separator = ";";
		}

		return StringUtils.split(str, separator);
	}

	/**
	 * 将String按指定字符分隔成ArrayList，默认分隔符为";"
	 * <pre>
	 * 1、如果str为null，则返回null
	 * 2、如果分隔后的ArrayList中某个元素为""，则该元素会被删除
	 * </pre>.
	 * 
	 * @param str 字符串
	 * @param separator 分隔符
	 * 
	 * @return 分隔后的ArrayList
	 */
	static public ArrayList getString2ArrayList(String str, String separator) {
		if (separator == null || "".equals(separator)) {
			separator = ";";
		}

		String[] strs = StringUtils.split(str, separator);

		return getStringArray2ArrayList(strs);
	}

	/**
	 * 将字符串数组转成ArrayList.
	 * 
	 * @param strs 字符串数组
	 * 
	 * @return 转换后的ArrayList
	 */
	static public ArrayList getStringArray2ArrayList(String[] strs) {
		if (strs == null) {
			return null;
		}

		ArrayList list = new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i]);
		}
		return list;
	}

	/**
	 * 将ArrayList转成字符串数组.
	 * 
	 * @param strList ArrayList数组
	 * 
	 * @return 字符串数组
	 */
	static public String[] getArrayList2StringArray(ArrayList strList) {
		if (strList == null) {
			return null;
		}

		String[] strArray = new String[strList.size()];
		for (int i = 0; i < strList.size(); i++) {
			try{
				String tmp = (String) strList.get(i);
				strArray[i] = tmp;
			}catch(Exception e){
				logger.error("This element is not String." + e);
				strArray[i] = "";
			}
		}

		return strArray;
	}

	/**
	 * 将ArrayList拼接成String.
	 * 
	 * @param strList ArrayList
	 * @param separator 分隔符
	 * 
	 * @return 拼接后的String
	 */
	static public String getArrayList2String(ArrayList strList, String separator) {
		return StringUtils.join(getArrayList2StringArray(strList), separator);
	}

	/**
	 * 将字符串数组拼接成String.
	 * 
	 * @param strs 字符串数组
	 * @param separator 分隔符
	 * 
	 * @return 拼接后的String
	 */
	static public String getStringArray2String(String[] strs, String separator) {
		return StringUtils.join(strs, separator);
	}

	/**
	 * 字符串替换.
	 * 
	 * @param strSource 源字符串
	 * @param strLookfor 源特征子串
	 * @param strSubstitute 目标特征子串
	 * 
	 * @return 替换后的字符串
	 */
	static public String replace(String strSource, String strLookfor,
			String strSubstitute) {
		return replace(strSource, strLookfor, strSubstitute, true);
	}

	/**
	 * 字符串替换(对大小写是否敏感).
	 * 
	 * @param strSource 源字符串
	 * @param strLookfor 源特征子串
	 * @param strSubstitute 目标特征子串
	 * @param bCaseSensitive 对大小写是否敏感
	 * 
	 * @return 替换后的字符串
	 */
	static public String replace(String strSource, String strLookfor,
			String strSubstitute, boolean bCaseSensitive) {
		try {
			if ( (strSource == null) || strSource.equals("") ||
					(strLookfor == null) || strLookfor.equals("") ||
					(strSubstitute == null)) {
				return "";
			}

			StringBuffer sb = new StringBuffer(strSource);
			int begin = 0;

			if (bCaseSensitive) {
				begin = strSource.indexOf(strLookfor);
			}
			else {
				begin = strSource.toLowerCase().indexOf(strLookfor.toLowerCase());
			}

			int end = begin + strLookfor.length();

			while (begin >= 0) {
				sb.replace(begin, end, strSubstitute);
				strSource = sb.toString();

				if (bCaseSensitive) {
					begin = strSource.indexOf(strLookfor,
							begin + strSubstitute.length());
				}
				else {
					begin = strSource.toLowerCase().indexOf(strLookfor.toLowerCase(),
							begin + strSubstitute.length());
				}

				end = begin + strLookfor.length();
			}

			return strSource;
		}
		catch (Exception e) {
			logger.error("StringProcess--replace--" + e);

			return null;
		}
	}

	/**
	 * 将阿拉伯数字转成汉字，支持小数点，
	 * 如果number为null，则返回"".
	 * 
	 * @param number String类型的阿拉伯数字
	 * 
	 * @return 汉字
	 */
	static public String getNum2ChiString(String number) {
		if (number == null) {
			return "";
		}

		if ("0".equals(number)) {
			return "零";
		}

		String chiresult = ""; //定义返回值参数chiresult为字符形式
		number = number.toLowerCase();
		int numberLen = number.length(); //定义numberLen为result的长度
		String temNumber = new String(number); //定义中间变量temNumber

		//将字符串temNumber中的全部数字替换为汉字
		temNumber = temNumber.replace('1', '一');
		temNumber = temNumber.replace('2', '二');
		temNumber = temNumber.replace('3', '三');
		temNumber = temNumber.replace('4', '四');
		temNumber = temNumber.replace('5', '五');
		temNumber = temNumber.replace('6', '六');
		temNumber = temNumber.replace('7', '七');
		temNumber = temNumber.replace('8', '八');
		temNumber = temNumber.replace('9', '九');
		temNumber = temNumber.replace('0', '零');
		temNumber = temNumber.replace('.', '点');

		//避免字符串temNumber中出现"零零", 但又不能改变字符串长度
		for (int i = temNumber.indexOf("零零"); i >= 0; i = temNumber.indexOf("零零")) {
			temNumber = temNumber.substring(0, i) + "位零" + temNumber.substring(i + 2);
		}

		//开始转换, i为位数确认参数, j为"十百千"确认参数, k为"万亿"确认参数
		for (int i = 1, j = 1, k = 1; i <= numberLen; i++) {
			//防止尾数为零, 如八十零, 二十零万
			if ( (temNumber.charAt(numberLen - 1) + "").equals("零") && i == 1) {
				chiresult = "位";
			}
			else if ( (temNumber.charAt(numberLen - i) + "").equals("零") && j == 1) {
				chiresult = "位" + chiresult;

			}
			//避免把"幂"和"点"当做实际位数, 而且单位确认变量重新计数
			else if ( (temNumber.charAt(numberLen - i) + "").equals("点")) {
				j = 1;
				k = 1;
				chiresult = temNumber.charAt(numberLen - i) + chiresult;
				continue;
			}
			else {
				chiresult = temNumber.charAt(numberLen - i) + chiresult;
			}

			//添加数字单位
			if (i < numberLen &&
					! (temNumber.charAt(numberLen - i - 1) + "").equals("位") &&
					! (temNumber.charAt(numberLen - i - 1) + "").equals("零")) {
				if (j == 1 && i < numberLen) {
					chiresult = "十" + chiresult;
				}
				else if (j == 2 && i < numberLen) {
					chiresult = "百" + chiresult;
				}
				else if (j == 3 && i < numberLen) {
					chiresult = "千" + chiresult;
				}
			}
			if (j == 4 && i < numberLen) {
				j = 0;
			}
			if (k == 4 && i < numberLen) {
				chiresult = "万" + chiresult;
			}
			else if (k == 8 && i < numberLen) {
				k = 0;
				chiresult = "亿" + chiresult;
			}
			//-----------
			j++;
			k++;
		}

		//避免出现一亿万的情况
		for (int i = chiresult.indexOf("位位位位万"); i >= 0;
		i = chiresult.indexOf("位位位位万")) {
			chiresult = chiresult.substring(0, i) + "位位位位%" +
			chiresult.substring(i + 5);

		}
		for (int i = chiresult.indexOf("%"); i >= 0; i = chiresult.indexOf("%")) {
			chiresult = chiresult.substring(0, i) + chiresult.substring(i + 1);
		}

		//避免字符串chiresult中出现"位"
		for (int i = chiresult.indexOf("位"); i >= 0; i = chiresult.indexOf("位")) {
			chiresult = chiresult.substring(0, i) + chiresult.substring(i + 1);

		}

		if (chiresult.length() >= 2 && chiresult.substring(0, 2).equals("一十")) { //避免出现"一十二"等情况
			chiresult = chiresult.substring(1);
		}
		if (chiresult.indexOf("点") >= 0) {
			String rebegin = chiresult.substring(0, chiresult.indexOf("点"));
			String remid = chiresult.substring(chiresult.indexOf("点"));
			remid = remid.replace('十', '%');
			remid = remid.replace('百', '%');
			remid = remid.replace('千', '%');
			remid = remid.replace('万', '%');
			remid = remid.replace('亿', '%');
			for (int i = remid.indexOf("%"); i >= 0; i = remid.indexOf("%")) {
				remid = remid.substring(0, i) + remid.substring(i + 1);
			}
			chiresult = rebegin + remid;
		}

		return chiresult;
	}

	/**
	 * 将字符型数字转成int型,转换失败返回-1.
	 * 
	 * @param number 字符型数字
	 * 
	 * @return 整型数字
	 */
	static public int parseInt(String number) {
		try {
			return Integer.parseInt(number, 10);
		}
		catch (NumberFormatException ex) {
			logger.error("数值格式不对！" + ex);

			return -1;
		}
		catch (Exception ex) {
			logger.error("提取整数失败！" + ex);

			return -1;
		}
	}

	/**
	 * 格式化数字为字符串，会补零,
	 * for example：调用numberToString(16,5)得到00016.
	 * 
	 * @param number 被格式化的数字，整型
	 * @param length 格式化的长度
	 * 
	 * @return 格式化的字符串
	 */
	static public String getNumToString(int number, int length) {
		return getNumToString("" + number, length);
	}

	/**
	 * 格式化字符串，会补零，
	 * for example：调用numberToString("16",5)得到"00016"。
	 * 如果number为null，则返回"".
	 * 
	 * @param number 被格式化的字符串
	 * @param length 格式化的长度
	 * 
	 * @return 格式化的字符串
	 */
	static public String getNumToString(String number, int length) {
		if (number == null) {
			return "";
		}
		return StringUtils.leftPad(number, length, "0");
	}

	/**
	 * 字符串补位，长度不足左侧补空格，
	 * 如果str为null，则返回"".
	 * 
	 * @param str 源字符串
	 * @param length 字符串指定长度
	 * 
	 * @return 一定长度的字符串
	 */
	static public String leftPad(String str, int length) {
		if (str == null) {
			return "";
		}

		return StringUtils.leftPad(str, length);
	}

	/**
	 * 字符串补位，长度不足左侧补指定字符，
	 * 如果str为null，则返回""，
	 * 如果separator为null，则补空格.
	 * 
	 * @param str 源字符串
	 * @param length 字符串指定长度
	 * @param separator 填补字符
	 * 
	 * @return 一定长度的字符串
	 */
	static public String leftPad(String str, int length, String separator) {
		if (str == null) {
			return "";
		}

		return StringUtils.leftPad(str, length, separator);
	}

	/**
	 * 字符串补位，长度不足右侧补空格，
	 * 如果str为null，则返回"".
	 * 
	 * @param str 源字符串
	 * @param length 字符串指定长度
	 * 
	 * @return 一定长度的字符串
	 */
	static public String rightPad(String str, int length) {
		if (str == null) {
			return "";
		}

		return StringUtils.rightPad(str, length);
	}

	/**
	 * 字符串补位，长度不足右侧补指定字符，
	 * 如果str为null，则返回""，
	 * 如果separator为null，则补空格。.
	 * 
	 * @param str 源字符串
	 * @param length 字符串指定长度
	 * @param separator 填补字符
	 * 
	 * @return 一定长度的字符串
	 */
	static public String rightPad(String str, int length, String separator) {
		if (str == null) {
			return "";
		}

		return StringUtils.rightPad(str, length, separator);
	}

	/**
	 * 检查字符串中出现了多少次子字符串.
	 * 
	 * @param str 源字符串
	 * @param sub 子字符串
	 * 
	 * @return 重复次数
	 */
	static public int countMatches(String str, String sub) {
		return StringUtils.countMatches(str, sub);
	}

	/**
	 * 检查字符串数组中是否包含指定字符串.
	 * 
	 * @param strs 字符串数组
	 * @param str 指定字符串
	 * 
	 * @return 包含则返回true，否则返回false
	 */
	static public boolean getStringInStringArray(String[] strs, String str) {
		if (strs == null || strs.length == 0 || str == null || "".equals(str)) {
			return false;
		}

		for (int i = 0; i < strs.length; i++) {
			if (str.equals(strs[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 将字符串复制指定次数
	 * <p>
	 * 1、str = null , 返回 ""<br>
	 * 2、str = "" ，返回 ""<br>
	 * 3、repeat < 0，返回""<br>
	 * </p>.
	 * 
	 * @param str    源字符串
	 * @param repeat 复制次数
	 * 
	 * @return 复制后的字符串
	 */
	static public String repeat(String str, int repeat) {
		if (str == null) {
			return "";
		}
		return StringUtils.repeat(str, repeat);
	}

	/**
	 * 将过长的字符串截取，并且用“...”代替
	 * <p>
	 * 当 maxWidth < 4 时，返回源字符串
	 * </p>
	 * 
	 * @param str      源字符串
	 * @param maxWidth 截取后的长度（包含“...”）
	 * 
	 * @return 截取后的字符串
	 */
	static public String abbreviate(String str, int maxWidth) {
		if (str == null) {
			return "";
		}

		if (maxWidth <= 3) {
			return str;
		}

		return StringUtils.abbreviate(str, maxWidth);
	}

	/**
	 * 将过长的字符串截取，并且用“...”代替
	 * <p>
	 * <pre>
	 * 1、当maxWidth < 4 时，返回源字符串
	 * 2、当maxWidth < 7 且 offset > 4 时，返回源字符串
	 * StringUtil.abbreviate(null, *, *)                = ""
	 * StringUtil.abbreviate("", 0, 4)                  = ""
	 * StringUtil.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
	 * StringUtil.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
	 * StringUtil.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
	 * StringUtil.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
	 * StringUtil.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
	 * </pre>
	 * </p>
	 * 
	 * @param str 源字符串
	 * @param offset 左边起始字符
	 * @param maxWidth 最大字符串长度
	 * 
	 * @return 截取后的字符串
	 */
	static public String abbreviate(String str, int offset, int maxWidth) {
		if (str == null) {
			return "";
		}

		if (maxWidth < 4 || (offset > 4 && maxWidth < 7)) {
			return str;
		}

		return StringUtils.abbreviate(str, offset, maxWidth);
	}

	/**
	 * 如果指定的字符串是null，则返回""，否则返回源字符串。.
	 * 
	 * @param str 指定字符串
	 * 
	 * @return ""或者指定字符串
	 */
	static public String defaultString(String str){
		return StringUtils.defaultString(str);
	}

	/**
	 * 如果指定的字符串是null，则返回默认字符串，否则返回源字符串。.
	 * 
	 * @param str 指定字符串
	 * @param defaultStr 默认字符串
	 * 
	 * @return 默认字符串或者指定字符串
	 */
	static public String defaultString(String str , String defaultStr){
		return StringUtils.defaultString(str,defaultStr);
	}

	/**
	 * 如果指定的字符串是null或者""，则返回默认字符串，否则返回源字符串。.
	 * 
	 * @param str 指定字符串
	 * @param defaultStr 默认字符串
	 * 
	 * @return 默认字符串或者指定字符串
	 */
	static public String defaultIfEmpty(String str , String defaultStr){
		return StringUtils.defaultIfEmpty(str,defaultStr);
	}

	public static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6',
		'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * Split a string based on a separator, but don't split if it's inside
	 * a string.  Assume '\' escapes the next char both inside and
	 * outside strings.
	 */
	public static List<String> splitSmart(String s, char separator) {
		ArrayList<String> lst = new ArrayList<String>(4);
		int pos=0, start=0, end=s.length();
		char inString=0;
		char ch=0;
		while (pos < end) {
			char prevChar=ch;
			ch = s.charAt(pos++);
			if (ch=='\\') {    // skip escaped chars
				pos++;
			} else if (inString != 0 && ch==inString) {
				inString=0;
			} else if (ch=='\'' || ch=='"') {
				// If char is directly preceeded by a number or letter
				// then don't treat it as the start of a string.
				// Examples: 50" TV, or can't
				if (!Character.isLetterOrDigit(prevChar)) {
					inString=ch;
				}
			} else if (ch==separator && inString==0) {
				lst.add(s.substring(start,pos-1));
				start=pos;
			}
		}
		if (start < end) {
			lst.add(s.substring(start,end));
		}

		/***
  if (SolrCore.log.isLoggable(Level.FINEST)) {
    SolrCore.log.trace("splitCommand=" + lst);
  }
		 ***/

		return lst;
	}

	/** Splits a backslash escaped string on the separator.
	 * <p>
	 * Current backslash escaping supported:
	 * <br> \n \t \r \b \f are escaped the same as a Java String
	 * <br> Other characters following a backslash are produced verbatim (\c => c)
	 *
	 * @param s  the string to split
	 * @param separator the separator to split on
	 * @param decode decode backslash escaping
	 */
	public static List<String> splitSmart(String s, String separator, boolean decode) {
		ArrayList<String> lst = new ArrayList<String>(2);
		StringBuilder sb = new StringBuilder();
		int pos=0, end=s.length();
		while (pos < end) {
			if (s.startsWith(separator,pos)) {
				if (sb.length() > 0) {
					lst.add(sb.toString());
					sb=new StringBuilder();
				}
				pos+=separator.length();
				continue;
			}

			char ch = s.charAt(pos++);
			if (ch=='\\') {
				if (!decode) sb.append(ch);
				if (pos>=end) break;  // ERROR, or let it go?
				ch = s.charAt(pos++);
				if (decode) {
					switch(ch) {
					case 'n' : ch='\n'; break;
					case 't' : ch='\t'; break;
					case 'r' : ch='\r'; break;
					case 'b' : ch='\b'; break;
					case 'f' : ch='\f'; break;
					}
				}
			}

			sb.append(ch);
		}

		if (sb.length() > 0) {
			lst.add(sb.toString());
		}

		return lst;
	}

	/**
	 * Splits file names separated by comma character.
	 * File names can contain comma characters escaped by backslash '\'
	 *
	 * @param fileNames the string containing file names
	 * @return a list of file names with the escaping backslashed removed
	 */
	public static List<String> splitFileNames(String fileNames) {
		if (fileNames == null)
			return Collections.<String>emptyList();

		List<String> result = new ArrayList<String>();
		for (String file : fileNames.split("(?<!\\\\),")) {
			result.add(file.replaceAll("\\\\(?=,)", ""));
		}

		return result;
	}

	/** Creates a backslash escaped string, joining all the items. */
	public static String join(List<String> items, char separator) {
		StringBuilder sb = new StringBuilder(items.size() << 3);
		boolean first=true;
		for (String item : items) {
			if (first) {
				first = false;
			} else {
				sb.append(separator);
			}
			for (int i=0; i<item.length(); i++) {
				char ch = item.charAt(i);
				if (ch=='\\' || ch == separator) {
					sb.append('\\');
				}
				sb.append(ch);
			}
		}
		return sb.toString();
	}



	public static List<String> splitWS(String s, boolean decode) {
		ArrayList<String> lst = new ArrayList<String>(2);
		StringBuilder sb = new StringBuilder();
		int pos=0, end=s.length();
		while (pos < end) {
			char ch = s.charAt(pos++);
			if (Character.isWhitespace(ch)) {
				if (sb.length() > 0) {
					lst.add(sb.toString());
					sb=new StringBuilder();
				}
				continue;
			}

			if (ch=='\\') {
				if (!decode) sb.append(ch);
				if (pos>=end) break;  // ERROR, or let it go?
				ch = s.charAt(pos++);
				if (decode) {
					switch(ch) {
					case 'n' : ch='\n'; break;
					case 't' : ch='\t'; break;
					case 'r' : ch='\r'; break;
					case 'b' : ch='\b'; break;
					case 'f' : ch='\f'; break;
					}
				}
			}

			sb.append(ch);
		}

		if (sb.length() > 0) {
			lst.add(sb.toString());
		}

		return lst;
	}

	public static List<String> toLower(List<String> strings) {
		ArrayList<String> ret = new ArrayList<String>(strings.size());
		for (String str : strings) {
			ret.add(str.toLowerCase());
		}
		return ret;
	}



	/** Return if a string starts with '1', 't', or 'T'
	 *  and return false otherwise.
	 */
	public static boolean parseBoolean(String s) {
		char ch = s.length()>0 ? s.charAt(0) : 0;
		return (ch=='1' || ch=='t' || ch=='T');
	}

	/** how to transform a String into a boolean... more flexible than
	 * Boolean.parseBoolean() to enable easier integration with html forms.
	 */
	public static boolean parseBool(String s) {
		if( s != null ) {
			if( s.startsWith("true") || s.startsWith("on") || s.startsWith("yes") ) {
				return true;
			}
			if( s.startsWith("false") || s.startsWith("off") || s.equals("no") ) {
				return false;
			}
		}
		throw new WdisException( WdisException.ErrorCode.BAD_REQUEST, "invalid boolean value: "+s );
	}

	/**
	 * URLEncodes a value, replacing only enough chars so that
	 * the URL may be unambiguously pasted back into a browser.
	 * <p>
	 * Characters with a numeric value less than 32 are encoded.
	 * &amp;,=,%,+,space are encoded.
	 * <p>
	 */
	public static void partialURLEncodeVal(Appendable dest, String val) throws IOException {
		for (int i=0; i<val.length(); i++) {
			char ch = val.charAt(i);
			if (ch < 32) {
				dest.append('%');
				if (ch < 0x10) dest.append('0');
				dest.append(Integer.toHexString(ch));
			} else {
				switch (ch) {
				case ' ': dest.append('+'); break;
				case '&': dest.append("%26"); break;
				case '%': dest.append("%25"); break;
				case '=': dest.append("%3D"); break;
				case '+': dest.append("%2B"); break;
				default : dest.append(ch); break;
				}
			}
		}
	}
}
