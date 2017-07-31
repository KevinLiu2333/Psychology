/*
 * (C) Copyright 2009 上海万达信息股份有限公司。
 * 保留对所有使用、复制、修改和发布整个软件和相关文档的权利
 * 本计算机程序受著作权法和国际公约的保护，未经授权擅自复制或
 * 传播本程序的全部或部分，可能受到严厉的民事和刑事制裁，并
 * 在法律允许的范围内受到最大可能的起诉。
 */
package com.wondersgroup.sh.search.util;

import org.apache.log4j.Logger;

import java.util.*;
import org.apache.commons.lang.StringUtils;



public class StringDateUtil {
	private static final Logger logger = Logger.getLogger(StringDateUtil.class);

  /** 时间格式：12和24，默认为24，输入12或者24以外的数据，将被转成24. */
  static public int STYLE_HOUR = 24;
  
  /** 凌晨的时间分隔点，默认为6点以前，不包含6点. */
  static public int SECTION_DAWN = 6;
  
  /** 上午的时间分隔点， 取 SECTION_DAWN 到 SECTION_MORNING 之间的时间， 默认为6点到12点，包含6点，不包含12点. */
  static public int SECTION_MORNING = 12;
  
  /** 中午的时间分隔点， 取 SECTION_MORNING 到 SECTION_NOON 之间的时间， 默认为12点到14点，包含12点，不包含14点. */
  static public int SECTION_NOON = 14;
  
  /** 下午的时间分隔点， 取 SECTION_NOON 到 SECTION_AFTERNOON 之间的时间， 默认为14点到18点，包含14点，不包含18点. */
  static public int SECTION_AFTERNOON = 18;
  
  /** 晚上的时间分隔点， 取 SECTION_AFTERNOON 到 SECTION_EVENING 之间的时间， 默认为18点到24点，包含18点，不包含24点. */
  static public int SECTION_EVENING = 24;

  /**
   * 将Date转成String型日期。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * 字符型日期可以是不完整格式长度，会出现2006/6/6格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param date date型日期
   * @param sFormat 日期格式
   * @param isFullDate 是否是完整时间长度，true则补零，false则不补零
   * 
   * @return String型日期
   */
  static private String getDate2String(Date date, String sFormat,
                                       boolean isFullDate) {
    try {

      if (STYLE_HOUR != 12) {
        STYLE_HOUR = 24;
      }
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);

      //年月日时分秒
      String strYear = String.valueOf(calendar.get(Calendar.YEAR));
      String strMonth = String.valueOf(calendar.get(Calendar.MONTH) + 1);
      String strDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
      String strHour = "";
      if (STYLE_HOUR == 24) {
        strHour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
      }
      else if (STYLE_HOUR == 12) {
        strHour = String.valueOf(calendar.get(Calendar.HOUR));
      }
      else {
        strHour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        logger.error("StringDateUtil--getDate2String--STYLE_HOUR error");
      }
      String strMinute = String.valueOf(calendar.get(Calendar.MINUTE));
      String strSecond = String.valueOf(calendar.get(Calendar.SECOND));

      //星期
      String strWeek = "星期" +
          "日一二三四五六".charAt(calendar.get(Calendar.DAY_OF_WEEK) - 1);

      //时段
      String strSection = "";
      if (calendar.get(Calendar.HOUR_OF_DAY) < SECTION_DAWN) {
        strSection = "凌晨";
      }
      else if (calendar.get(Calendar.HOUR_OF_DAY) < SECTION_MORNING) {
        strSection = "上午";
      }
      else if (calendar.get(Calendar.HOUR_OF_DAY) < SECTION_NOON) {
        strSection = "中午";
      }
      else if (calendar.get(Calendar.HOUR_OF_DAY) < SECTION_AFTERNOON) {
        strSection = "下午";
      }
      else if (calendar.get(Calendar.HOUR_OF_DAY) < SECTION_EVENING) {
        strSection = "晚上";
      }
      else {
        strSection = "全天";
        logger.error("StringDateUtil--getDate2String--SECTION error");
        logger.error("SECTION_DAWN is " + SECTION_DAWN);
        logger.error("SECTION_MORNING is " + SECTION_MORNING);
        logger.error("SECTION_NOON is " + SECTION_NOON);
        logger.error("SECTION_AFTERNOON is " + SECTION_AFTERNOON);
        logger.error("SECTION_EVENING is " + SECTION_EVENING);
      }

      //完整时间格式，补零
      if (isFullDate) {
        strYear = StringUtil.leftPad(strYear, 4, "0");
        strMonth = StringUtil.leftPad(strMonth, 2, "0");
        strDay = StringUtil.leftPad(strDay, 2, "0");
        strHour = StringUtil.leftPad(strHour, 2, "0");
        strMinute = StringUtil.leftPad(strMinute, 2, "0");
        strSecond = StringUtil.leftPad(strSecond, 2, "0");
      }

      String sValue = "";

      if ( (sFormat != null) && !sFormat.trim().equals("")) {
        sFormat = StringUtils.lowerCase(sFormat);
        sValue = sFormat;
        sValue = StringUtil.replace(sValue, "yyyy", strYear);
        sValue = StringUtil.replace(sValue, "mm", strMonth);
        sValue = StringUtil.replace(sValue, "dd", strDay);
        sValue = StringUtil.replace(sValue, "hh", strHour);
        sValue = StringUtil.replace(sValue, "mi", strMinute);
        sValue = StringUtil.replace(sValue, "ss", strSecond);
        sValue = StringUtil.replace(sValue, "wd", strWeek);
        sValue = StringUtil.replace(sValue, "sn", strSection);

        return sValue;
      }

      //yyyy/mm/dd hh:mi:ss
      return strYear + "/" + strMonth + "/" + strDay + " " + strHour +
          ":" + strMinute + ":" + strSecond;

    }
    catch (Exception e) {
      logger.error("StringDateUtil--getDate2String--" + e);

      return null;
    }
  }

  /**
   * 将String型日期转成Calendar。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * 字符型日期格式必须是完整格式长度，即不支持2006/6/6格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（24小时格式）
   * mi    分钟
   * ss    秒
   * </pre>.
   * 
   * @param sDate String型日期
   * @param sFormat 日期格式
   * 
   * @return Calendar
   */
  public static Calendar getString2Calendar(String sDate, String sFormat) {
    try {
      int iYear = 0, iMonth = 0, iDay = 1, iHour = 0, iMinute = 0, iSecond = 0;
      if (sFormat == null || "".equals(sFormat)) {
        sFormat = "yyyy/mm/dd hh:mi:ss";

      }
      sFormat = StringUtils.lowerCase(sFormat);

      //search year
      int iStart = 0;
      iStart = sFormat.indexOf("yyyy");
      if (iStart >= 0) {
        iYear = StringUtil.parseInt(sDate.substring(iStart, 4 + iStart));
      }
      if (iYear < 0) {
        iYear = 0;

        //search month
      }
      iStart = sFormat.indexOf("mm");
      if (iStart >= 0) {
        iMonth = StringUtil.parseInt(sDate.substring(iStart, 2 + iStart));
      }
      if (iMonth < 0) {
        iMonth = 1;

        //search day
      }
      iStart = sFormat.indexOf("dd");
      if (iStart >= 0) {
        iDay = StringUtil.parseInt(sDate.substring(iStart, 2 + iStart));
      }
      if (iDay < 0) {
        iDay = 1;

        //search hour
      }
      iStart = sFormat.indexOf("hh");
      if (iStart >= 0) {
        iHour = StringUtil.parseInt(sDate.substring(iStart, 2 + iStart));
      }
      if (iHour < 0) {
        iHour = 0;

        //search minute
      }
      iStart = sFormat.indexOf("mi");
      if (iStart >= 0) {
        iMinute = StringUtil.parseInt(sDate.substring(iStart, 2 + iStart));
      }
      if (iMinute < 0) {
        iMinute = 0;

        //search second
      }
      iStart = sFormat.indexOf("ss");
      if (iStart >= 0) {
        iSecond = StringUtil.parseInt(sDate.substring(iStart, 2 + iStart));
      }
      if (iSecond < 0) {
        iSecond = 0;

      }
      Calendar calendar = Calendar.getInstance();
      calendar.set(iYear, iMonth - 1, iDay, iHour, iMinute, iSecond);

      return calendar;
    }
    catch (Exception e) {
      logger.error("StringDateUtil--transString2Date--" + e);
      return null;
    }
  }

  /**
   * 将String型日期转成Date。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * 字符型日期格式必须是完整格式长度，即不支持2006/6/6格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（24小时格式）
   * mi    分钟
   * ss    秒
   * </pre>.
   * 
   * @param sDate String型日期
   * @param sFormat 日期格式
   * 
   * @return Date
   */
  static public Date getString2Date(String sDate, String sFormat) {
    return getString2Calendar(sDate, sFormat).getTime();
  }

  /**
   * 将Calendar转成String型日期。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss。
   * 字符型日期是完整格式长度，长度不足的会补0，即不会出现2006/6/6格式。
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param calendar calendar型日期
   * @param sFormat 日期格式
   * 
   * @return String型日期
   */
  static public String getCalendar2FullString(Calendar calendar, String sFormat) {
    return getDate2String(calendar.getTime(), sFormat, true);
  }

  /**
   * 将Date转成String型日期。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * 字符型日期是完整格式长度，即不会出现2006/6/6格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param date date型日期
   * @param sFormat 日期格式
   * 
   * @return String型日期
   */
  static public String getDate2FullString(Date date, String sFormat) {
    return getDate2String(date, sFormat, true);
  }

  /**
   * 将Calendar转成String型日期。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * 字符型日期可以是不完整格式长度，会出现2006/6/6格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param calendar calendar型日期
   * @param sFormat 日期格式
   * 
   * @return String型日期
   */
  static public String getCalendar2String(Calendar calendar, String sFormat) {
    return getDate2String(calendar.getTime(), sFormat, false);
  }

  /**
   * 将Date转成String型日期。
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * 字符型日期可以是不完整格式长度，会出现2006/6/6格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param date date型日期
   * @param sFormat 日期格式
   * 
   * @return String型日期
   */
  static public String getDate2String(Date date, String sFormat) {
    return getDate2String(date, sFormat, false);
  }

  /**
   * 获得今天，以及向后顺延N天的日期，
   * 返回的是String[]，长度为2，String[0]为今天的日期，String[1]为顺延后的日期
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param amount 先后顺延的时间
   * @param sFormat 日期格式
   * 
   * @return 今天以及顺延后的日期
   */
  static public String[] getPostponeDay(int amount, String sFormat) {
    return getDateSpan(Calendar.getInstance(), amount, Calendar.DATE, sFormat);
  }

  /**
   * 获得今天，以及向后(向前）顺延N天的所有日期，
   * 返回的是String[]，数组长度为N+1，
   * 数组中的日期升序排列，日期小的在前，
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss，
   * amount = 0 时，返回String[]长度为1，即今天的String格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param amount 先后顺延的时间
   * @param sFormat 日期格式
   * 
   * @return 今天以及顺延后的日期
   */
  static public String[] getAllPostponeDay(int amount, String sFormat) {
    return getAllDateSpan(Calendar.getInstance(), amount, Calendar.DATE, sFormat);
  }

  /**
   * 计算相差N个日期（或者时间段，如N月），并返回起始和结束日期
   * 返回的是String[]，长度为2，String[0]为起始日期，String[1]为结束日期，
   * 时间小的在前，默认时间格式是 yyyy/mm/dd hh:mi:ss
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param sCal 开始时间
   * @param amount 日期（或者时间段，时间段可以是年，月日等）差额
   * @param field 日期（或者时间段），如：Calendar.YEAR,etc
   * @param sFormat 时间格式
   * 
   * @return 起始和结束日期
   */
  static public String[] getDateSpan(Calendar sCal, int amount, int field,
                                     String sFormat) {
    Calendar eCal = (Calendar) sCal.clone();
    if (amount > 0) {
      eCal.add(field, amount);
    }
    else if (amount < 0) {
      sCal.add(field, amount);
    }
    return getDateSpan(sCal, eCal, sFormat);
  }

  /**
   * 计算相差N个日期（或者时间段，如N月），并返回相隔的所有日期，
   * 返回的是String[]，长度为N+1，时间小的在前，
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss，
   * amount = 0 时，返回String[]长度为1，即sCal的String格式
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param sCal 开始时间
   * @param amount 日期（或者时间段，时间段可以是年，月日等）差额
   * @param field 日期（或者时间段），如：Calendar.YEAR,etc
   * @param sFormat 时间格式
   * 
   * @return 起始和结束日期
   */
  static public String[] getAllDateSpan(Calendar sCal, int amount, int field,
                                     String sFormat) {
    Calendar eCal = (Calendar) sCal.clone();
    if(amount == 0){
      return new String[] {
          getCalendar2FullString(sCal, sFormat)
      };
    }
    String[] strDates = null;
    if (amount > 0){
      strDates = new String[amount + 1];
      eCal.add(field, amount);
    }
    else{
      sCal.add(field, amount);
      strDates = new String[1 - amount];
      amount = -amount;
    }

    strDates[0] = getCalendar2FullString(sCal, sFormat);
    for(int i = 1 ; i < strDates.length ; i++){
      sCal.add(field, 1);
      strDates[i] = getCalendar2FullString(sCal, sFormat);
    }
    return strDates;
  }

  /**
   * 返回起始和结束日期
   * 返回的是String[]，长度为2，String[0]为起始日期，String[1]为结束日期
   * 默认时间格式是 yyyy/mm/dd hh:mi:ss
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param sCal 开始时间
   * @param eCal 结束时间
   * @param sFormat 时间格式
   * 
   * @return 起始和结束日期
   */
  static public String[] getDateSpan(Calendar sCal, Calendar eCal,
                                     String sFormat) {
    return new String[] {
        getCalendar2FullString(sCal, sFormat),
        getCalendar2FullString(eCal, sFormat)
    };
  }

  /**
   * 将Calendar日期转成汉字格式的日期，自动将年月日等中午字加入，
   * 如果某个区域的值是0，如12：00，则显示12时，0分将不显示；<br>
   * 如果某个区域的值是0，但是其下级区域值不为0，如00：30：00，则显示零时三十分<br>
   * sFormat中不需给出年月日等中文字
   * 默认时间格式是 yyyymmddhhmiss，如二零零六年六月六日十二时三十分三十秒
   * <pre>
   * sFormat格式包括（大小写通用）：
   * yyyy  年
   * mm    月
   * dd    日
   * hh    小时（12小时或者24小时格式，通过设置 STYLE_HOUR 更改，默认为24小时格式）
   * mi    分钟
   * ss    秒
   * wd    星期
   * sn    时段（上午，中午，下午等，时段根据小时划分，可更改）
   * 0  ≤ sn ＜ 6   凌晨
   * 6  ≤ sn ＜ 12  上午
   * 12 ≤ sn ＜ 14  中午
   * 14 ≤ sn ＜ 18  下午
   * 18 ≤ sn 　     晚上
   * </pre>.
   * 
   * @param calendar 日期
   * @param sFormat 日期格式
   * 
   * @return 汉字格式的日期
   */
  public static String getChinaDateString(Calendar calendar, String sFormat) {
    String strDate = getCalendar2FullString(calendar,
                                            "yyyy/mm/dd hh:mi:ss wd sn");
    String strYear = StringUtil.getNum2ChiString(strDate.substring(0, 1)) +
        StringUtil.getNum2ChiString(strDate.substring(1, 2)) +
        StringUtil.getNum2ChiString(strDate.substring(2, 3)) +
        StringUtil.getNum2ChiString(strDate.substring(3, 4)) + "年";
    String strMonth = StringUtil.getNum2ChiString(strDate.substring(5, 7));
    String strDay = StringUtil.getNum2ChiString(strDate.substring(8, 10));
    String strHour = strDate.substring(11, 13);
    String strMinute = strDate.substring(14, 16);
    String strSecond = strDate.substring(17, 19);

    strMonth = StringUtil.replace(strMonth, "零", "") + "月";
    strDay = StringUtil.replace(strDay, "零", "") + "日";
    if ("00".equals(strHour) && "00".equals(strMonth) && "00".equals(strSecond)) {
      strHour = "";
    }
    else if ("00".equals(strHour)) {
      strHour = "零时";
    }
    else {
      strHour = StringUtil.getNum2ChiString(strHour);
      strHour = StringUtil.replace(strHour, "零", "") + "时";
    }
    if ("00".equals(strMinute) && "00".equals(strSecond)) {
      strMinute = "";
    }
    else if ("00".equals(strMinute)) {
      strMinute = "零分";
    }
    else {
      strMinute = StringUtil.getNum2ChiString(strMinute);
      strMinute = StringUtil.replace(strMinute, "零", "") + "分";
    }
    if ("00".equals(strSecond)) {
      strSecond = "";
    }
    else {
      strSecond = StringUtil.getNum2ChiString(strSecond);
      strSecond = StringUtil.replace(strSecond, "零", "") + "秒";
    }
    String strWeek = strDate.substring(20, 23);
    String strSection = strDate.substring(24, 26);

    String sValue = "";

    if ( (sFormat != null) && !sFormat.trim().equals("")) {
      sFormat = StringUtils.lowerCase(sFormat);
      sValue = sFormat;
      sValue = StringUtil.replace(sValue, "yyyy", strYear);
      sValue = StringUtil.replace(sValue, "mm", strMonth);
      sValue = StringUtil.replace(sValue, "dd", strDay);
      sValue = StringUtil.replace(sValue, "hh", strHour);
      sValue = StringUtil.replace(sValue, "mi", strMinute);
      sValue = StringUtil.replace(sValue, "ss", strSecond);
      sValue = StringUtil.replace(sValue, "wd", strWeek);
      sValue = StringUtil.replace(sValue, "sn", strSection);

      return sValue;
    }

    //年月日时分秒
    return strYear + strMonth + strDay + strHour + strMinute + strSecond;
  }

}
