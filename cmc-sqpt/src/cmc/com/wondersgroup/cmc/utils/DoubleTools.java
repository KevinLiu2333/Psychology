/** 
 * @(#)DoubleTools.java 2011-7-27
 * 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
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
import java.util.regex.Pattern;

/**
 * @author Huoej
 * @version $Revision$ 2011-7-27
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class DoubleTools {
	//默认的运算精度
	private static final int DEF_DIV_SCALE = 10;
	
	//不能实例化这个类
	public DoubleTools(){
		
	}
	/**
	 * 2个浮点数进行加法运算
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static Double plus(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return b1.add(b2).doubleValue();
	}
	/**
	 * 对动态个数的浮点数进行加法运算
	 * @param scale 返回的小数位，采用四舍五入的方式
	 * @param ins
	 * @return
	 */
	public static Double plus(int scale, Double... ins){
		if(ins.length < 2){
			throw new IllegalArgumentException("There must be two doubles to be added at least");
		}
		BigDecimal b1 = (ins[0] == null)? new BigDecimal("0") : new BigDecimal(Double.toString(ins[0]));
		for(int i = 1; i < ins.length; i++){
			BigDecimal b2 = (ins[i] == null)? new BigDecimal("0") : new BigDecimal(Double.toString(ins[i]));
			b1 = b1.add(b2);
		}
		return round(b1.doubleValue(), scale);
	}
	/**
	 * 对动态个数的浮点数进行加法运算
	 * @param ins
	 * @return
	 */
	public static Double plus(Double... ins){
		return plus(DEF_DIV_SCALE, ins);
	}
	/**
	 * 2个浮点数进行加法运算，并指定返回的浮点数的小数位
	 * @param in1
	 * @param in2
	 * @param scale 返回的小数位，采用四舍五入的方式
	 * @return
	 */
	public static Double plus(Double in1, Double in2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return round(b1.add(b2).doubleValue(), scale);
	}
	/**
	 * 2个浮点数进行减法运算
	 * @param in1 减数
	 * @param in2 被减数
	 * @return
	 */
	public static Double minus(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return b1.subtract(b2).doubleValue();
	}
	/**
	 * 2个浮点数进行减法运算
	 * @param in1 减数
	 * @param in2 被减数
	 * @param scale 返回的小数位，采用四舍五入的方式
	 * @return
	 */
	public static Double minus(Double in1, Double in2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return round(b1.subtract(b2).doubleValue(), scale);
	}
	/**
	 * 2个浮点数进行除法运算，默认保留10位小数
	 * @param in1 除数
	 * @param in2 被除数
	 * @return
	 */
	public static Double divide(Double in1, Double in2){
		return divide(in1, in2, DEF_DIV_SCALE);
	}

	/**
	 * 2个浮点数进行除法运算，并指定返回的浮点数的小数位
	 * @param in1 除数
	 * @param in2 被除数
	 * @param scale 返回的小数位，采用四舍五入的方式
	 * @return
	 */
	public static Double divide(Double in1, Double in2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 2个浮点数进行乘法运算
	 * @param in1 
	 * @param in2 
	 * @return
	 */
	public static Double multiply(Double in1, Double in2) {
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return b1.multiply(b2).doubleValue();
	}
	/**
	 * 对动态个数的浮点数进行乘法运算
	 * @param scale 返回的小数位，采用四舍五入的方式
	 * @param ins
	 * @return
	 */
	public static Double multiply(int scale, Double... ins) {
		if(ins.length < 2){
			throw new IllegalArgumentException("There must be two doubles to be multiplied at least");
		}
		BigDecimal b1 = (ins[0] == null)? new BigDecimal("0") : new BigDecimal(Double.toString(ins[0]));
		for(int i = 1; i < ins.length; i++){
			BigDecimal b2 = (ins[i] == null)? new BigDecimal("0") : new BigDecimal(Double.toString(ins[i]));
			b1 = b1.multiply(b2);
		}
		return round(b1.doubleValue(), scale);
	}
	/**
	 * 对动态个数的浮点数进行乘法运算
	 * @param ins
	 * @return
	 */
	public static Double multiply(Double... ins){
		return multiply(DEF_DIV_SCALE, ins);
	}
	/**
	 * 2个浮点数进行乘法运算，并指定返回的浮点数的小数位
	 * @param in1
	 * @param in2
	 * @param scale 返回的小数位，采用四舍五入的方式
	 * @return
	 */
	public static Double multiply(Double in1, Double in2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		return round(b1.multiply(b2).doubleValue(), scale);
	}
	
	/**
	 * 对指定浮点数的小数位数，采用四舍五入的方式
	 * @param in1
	 * @param scale
	 * @return
	 */
	public static Double round(Double in1, int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal one = new BigDecimal("1");
		return b1.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 对指定浮点数的小数位数，采用进位的方式
	 * @param in1
	 * @param scale
	 * @return
	 */
	public static Double roundUp(Double in1, int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal one = new BigDecimal("1");
		return b1.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
	}
	/**
	 * 对指定浮点数的小数位数，采用去尾的方式
	 * @param in1
	 * @param scale
	 * @return
	 */
	public static Double roundDown(Double in1, int scale){
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal one = new BigDecimal("1");
		return b1.divide(one, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}
	
	/**
	 * 去除浮点数后面多余的0
	 * @param in1
	 * @return
	 */
/*	public static Double trim(Double in1) {
		String str = Double.toString(in1);
		if (str.indexOf(".") != -1 && str.charAt(str.length() - 1) == '0') {
			return trim(Double.valueOf(str.substring(0, str.length() - 1)));
		} else {
			return Double.valueOf(str.charAt(str.length() - 1) == '.' ? str.substring(0, str.length() - 1) : str);
		}
	}*/
	/**
	 * 比较两个浮点数属否相等
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static boolean equal(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		if(b1.compareTo(b2) == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 浮点数in1是否大于浮点数in2
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static boolean greater(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		if(b1.compareTo(b2) == 1){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 浮点数in1是否小于浮点数in2
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static boolean lesser(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		if(b1.compareTo(b2) == -1){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 浮点数in1是否小于等于浮点数in2
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static boolean notGreater(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		if(b1.compareTo(b2) <= 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 浮点数in1是否大于等于浮点数in2
	 * @param in1
	 * @param in2
	 * @return
	 */
	public static boolean notLesser(Double in1, Double in2){
		BigDecimal b1 = (in1 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in1));
		BigDecimal b2 = (in2 == null)? new BigDecimal("0") : new BigDecimal(Double.toString(in2));
		if(b1.compareTo(b2) >= 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断字符串str是不是一个整数
	 * @param in
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
	/**
	 * 判断字符串str是不是一个整数
	 * @param in
	 * @return
	 */
	public static boolean isInteger(Double in) {
		Double inD = roundDown(in, 0);
		return equal(in, inD);
	}
	/**
	 * 判断字符串str是不是一个浮点数
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
		return pattern.matcher(str).matches();
	}
	 
	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零
	 * 要用到正则表达式
	 */
	public static String toRMB(double n){
	    String fraction[] = {"角", "分"};
	    String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	    String unit[][] = {{"元", "万", "亿"},
	                 {"", "拾", "佰", "仟"}};
	 
	    String head = n < 0? "负": "";
	    n = Math.abs(n);
	     
	    String s = "";
	    for (int i = 0; i < fraction.length; i++) {
	        s += (digit[(int)(Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i]).replaceAll("(零.)+", "");
	    }
	    if(s.length()<1){
	        s = "整";   
	    }
	    int integerPart = (int)Math.floor(n);
	 
	    for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
	        String p ="";
	        for (int j = 0; j < unit[1].length && n > 0; j++) {
	            p = digit[integerPart%10]+unit[1][j] + p;
	            integerPart = integerPart/10;
	        }
	        s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i] + s;
	    }
	    return head + s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "").replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}
}
