/**
 * @(#)NumberUtils.java Jun 3, 2010
 *
 * Copyright (c) 1995-2010 Wonders Information Co.,Ltd.
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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.wondersgroup.wssip.util.DateTools;

/**
 * 常用的数值运算
 *
 * @author xieguojun
 * @version $Revision$ May 21, 2010
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public final class NumberUtils {

	/**
	 * long数据格式化
	 *
	 * @param l 需要格式化的long数据
	 * @param format 格式化字符串
	 * @return
	 */
	public static String format(long l, String format) {

		DecimalFormat numberFormat = new DecimalFormat(format);

		return numberFormat.format(l);
	}

	/**
	 * 加法运算
	 *
	 * @param values 参与加法的参数组,可变参数，不定长度
	 * @return
	 */
	public static BigDecimal add(BigDecimal... values) {
		if (values == null) {
			throw new RuntimeException("values can not be null!");
		}

		BigDecimal sum = BigDecimal.ZERO;
		for (int i = 0; i < values.length; i++) {
			BigDecimal v = values[i];
			if (v == null) {
				throw new RuntimeException(String.format("values[%d] can not be null!", Integer.valueOf(i)));
			}
			sum = sum.add(v);
		}

		return sum;
	}

	/**
	 * 取相反数
	 *
	 * @param value
	 * @return
	 */
	public static BigDecimal negative(BigDecimal value) {
		if (value == null) {
			throw new RuntimeException("value can not be null!");
		}

		return value == null ? BigDecimal.ZERO : value.multiply(new BigDecimal("-1"));
	}

	/**
	 * 乘法运算
	 *
	 * @param values 参与乘法的参数组，可变参数，不定长度
	 * @return
	 */
	public static BigDecimal multi(BigDecimal... values) {

		if (values == null) {
			throw new RuntimeException("values can not be null!");
		}

		BigDecimal sum = new BigDecimal(1);
		for (int i = 0; i < values.length; i++) {
			BigDecimal v = values[i];
			if (v == null) {
				throw new RuntimeException(String.format("values[%d] can not be null!", Integer.valueOf(i)));
			}
			sum = sum.multiply(v);
		}

		return sum;
	}

	/**
	 * 除法运算，结果保留指定位小数
	 *
	 * @param v1 被除数
	 * @param v2 除数
	 * @param scale 结果保留小数位数
	 * @return
	 */
	public static BigDecimal div(BigDecimal value1, BigDecimal value2, int scale) {
		if (value1 == null) {
			throw new RuntimeException("value1 can not be null!");
		}

		if (value2 == null) {
			throw new RuntimeException("value2 can not be null!");
		}

		return value1.divide(value2, scale, RoundingMode.HALF_UP);
	}

	/**
	 * 除法运算，结果默认保留2位小数
	 *
	 * @param v1 被除数
	 * @param v2 除数
	 * @return
	 */
	public static BigDecimal div(BigDecimal value1, BigDecimal value2) {

		return div(value1, value2, 2);
	}

	/**
	 * 减法运算
	 *
	 * @param v1 被减数
	 * @param v2 减数
	 * @return
	 */
	public static BigDecimal sub(BigDecimal value1, BigDecimal value2) {
		if (value1 == null) {
			throw new RuntimeException("value1 can not be null!");
		}

		if (value2 == null) {
			throw new RuntimeException("value2 can not be null!");
		}

		return value1.subtract(value2);
	}

	/**
	 * 减法运算
	 *
	 * @param v1 被减数
	 * @param v2 减数
	 * @return
	 */
	public static BigDecimal sub(BigDecimal value1, BigDecimal... values) {
		if (value1 == null) {
			throw new RuntimeException("value1 can not be null!");
		}

		for (int i = 0; i < values.length; i++) {
			BigDecimal v = values[i];
			if (v == null) {
				throw new RuntimeException(String.format("values[%d] can not be null!", Integer.valueOf(i)));
			}
			value1 = value1.subtract(v);
		}

		return value1;
	}

	/**
	 * 见分进角
	 *
	 * @param value
	 * @return
	 */
	public static BigDecimal jianFenJinJiao(BigDecimal value) {
		if (value == null) {
			throw new RuntimeException("value can not be null!");
		}

		BigDecimal ten = new BigDecimal("10");
		value = multi(round(value, 2), ten);
		value = new BigDecimal(Math.ceil(value.doubleValue()));
		return div(value, ten, 1);
	}

	/**
	 * 见分进元
	 *
	 * @param value
	 * @return
	 */
	public static BigDecimal jianFenJinYuan(BigDecimal value) {
		if (value == null) {
			throw new RuntimeException("value can not be null!");
		}

		value = round(value, 2);
		return new BigDecimal(Math.ceil(value.doubleValue()));
	}

	/**
	 * 四舍五入
	 *
	 * @param d
	 * @return
	 */
	public static BigDecimal round(BigDecimal d, int scale) {
		return d.setScale(scale, BigDecimal.ROUND_HALF_UP);
		//return d.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 日期转换为数字，只支持转换为Long型的年、年月、年月日
	 *
	 * @param d
	 * @param pattern yyyyMMdd yyyyMM yyyy
	 * @return
	 */
	public static Long convertDateToLong(Date d, String pattern) {

		if (d == null || StringUtils.isBlank(pattern)) {
			return null;
		}

		if (!("yyyy".equalsIgnoreCase(pattern) || "yyyyMM".equalsIgnoreCase(pattern) || "yyyyMMdd"
				.equalsIgnoreCase(pattern))) {
			throw new RuntimeException("错误的日期转换格式，正确格式为：[yyyy,yyyyMM,yyyyMMdd]");
		}
		return Long.valueOf(DateTools.format(d, pattern));
	}

	// 主要用来判断主键是否为空
	public static boolean isEmpty(Long id) {
		return id == null || id <= 0;
	}

	// 主要用来判断主键是否为空
	public static boolean isNotEmpty(Long id) {
		return !isEmpty(id);
	}

	public static void main(String args[]) {

		System.out.println(round(multi(new BigDecimal("22.345"), new BigDecimal("22.345")), 2));

		System.out.println(round(sub(new BigDecimal("22.357"), new BigDecimal("22.345")), 2));

		System.out.println(round(add(new BigDecimal("22.357"), new BigDecimal("22.345")), 2));

		System.out.println(div(new BigDecimal("29.357"), new BigDecimal("22.345")));

		System.out.println(jianFenJinJiao(new BigDecimal("29.0081")));

		System.out.println(jianFenJinYuan(new BigDecimal("29.0041")));

		System.out.println(convertDateToLong(new Date(), "yyyyMMdd1"));
	}

}
