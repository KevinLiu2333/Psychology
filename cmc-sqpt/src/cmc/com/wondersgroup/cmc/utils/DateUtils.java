/** 
 * @(#)DateUtils.java Jan 14, 2012
 * 
 * Copyright (c) 1995-2012 Wonders Information Co.,Ltd. 
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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.wondersgroup.wssip.util.DateTools;

/**
 * @author wangchenglin
 * @version $Revision$ Jan 14, 2012
 * @author ($Date$ modification by $Author$)
 * @since 1.0
 */
public class DateUtils extends DateTools {

	public static Long wrapDate(Long d, String pattern, String wrapStr) {

		return Long.valueOf(DateUtils.format(DateUtils.wrapDate(DateUtils.parseDate(String.valueOf(d), pattern),
				wrapStr), pattern));
	}
	
	/*
	 * *java中对日期的加减操作 *gc.add(1,-1)表示年份减一. *gc.add(2,-1)表示月份减一. *gc.add(3.-1)表示周减一. *gc.add(5,-1)表示天减一. 
	 * *以此类推应该可以精确的毫秒.
	 * *GregorianCalendar类的add(int field,int amount)方法表示年月日加减. *field参数表示年,月.日等. 
	 * *amount参数表示要加减的数量
	 */
	public static Date getDiffDate(Date date, int field, int amount) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(field, amount);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DATE));
		return gc.getTime();
	}
	
	/**
	 * 时间相减得到天数
	 * @param beginDateStr
	 * @param endDateStr
	 * @return
	 */
	public static long getDaySub(String beginDateStr,String endDateStr) {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyyMMdd");    
        java.util.Date beginDate;
        java.util.Date endDate;
        try{
            beginDate = format.parse(beginDateStr);
            endDate= format.parse(endDateStr);    
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);    
            //System.out.println("相隔的天数="+day);   
        } catch (ParseException e) {
            // TODO 自动生成 catch 块
            e.printStackTrace();
        }   
        return day;
	}
	
	public static void main(String[] args){
		long result=getDaySub("20120130","20111201");
		System.out.println("=========result: "+result);
	}
}
