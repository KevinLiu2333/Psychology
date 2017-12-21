package com.wonders.wddc.tiles.tools;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;

/**
 * 日期处理工具。
 * 
 * <p>
 * 相关的日期处理工具类为org.apache.commons.lang.time.DateFormatUtils
 * 
 * @since 1.0.0
 */

public class DateUtils {

	/** 默认日期格式 */
	public static final String FORMAT_DATE = "yyyy-MM-dd";

	/** 默认日期格式 */
	public static final String FORMAT_ZH_DATE = "yyyy年MM月dd日";

	/** 默认时间格式 */
	public static final String FORMAT_TIME = "HH:mm:ss";

	/** 默认日期时间格式 */
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
	
	/** 默认年份格式 */
	public static final String FORMAT_YEAR = "yyyy";

	/** 日志 */
	private static Logger logger = Logger.getLogger(DateUtils.class);

	/**
	 * 将日期转换成字符格式
	 * 
	 * @param date
	 *            java.util.Date类型
	 * @param format
	 *            如果为null或""，默认为DATE格式
	 * @return 无法成功转换则返回null
	 */
	public static String date2String(java.util.Date date, String format) {
		String result = null;
		if (date == null) {
			return result;
		}
		if (StringUtils.isEmpty(format)) {
			format = FORMAT_DATE;
		}
		try {
			result = DateFormatUtils.format(date, format);
		} catch (Exception ex) {
			logger.warn("日期转换为字符串错误，日期：" + date.toString() + "， 格式：" + format);
		}

		return result;
	}

	/**
	 * 将字符串转换成日期格式
	 * 
	 * 
	 * @param str
	 *            需要转换的字符串
	 * 
	 * @param format
	 *            相应的转换格式，如果参数为Blank则表示按常规的三种格式转换
	 * 
	 * @return 如果不能正常转换则返回null
	 */
	public static java.util.Date string2Date(String str, String format) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		Date result = null;
		String[] formats = null;
		if (StringUtils.isEmpty(format)) {
			formats = new String[3];
			formats[0] = FORMAT_DATETIME;
			formats[1] = FORMAT_DATE;
			formats[2] = FORMAT_TIME;
		} else {
			formats = new String[4];
			formats[0] = format;
			formats[1] = FORMAT_DATETIME;
			formats[2] = FORMAT_DATE;
			formats[3] = FORMAT_TIME;
		}
		try {
			result = org.apache.commons.lang.time.DateUtils.parseDate(str,
					formats);
		} catch (Exception ex) {
			logger.warn("日期或时间格式不正确，日期时间字符串：" + str + "， 格式：" + format);

		}

		return result;
	}

	/**
	 * 将时间戳转换成字符串格式
	 * 
	 * @param ts
	 *            时间戳
	 * 
	 * @param format
	 *            日期时间格式
	 * @return 转换后的字符串
	 */
	public static String timestamp2String(Timestamp ts, String format) {
		return ts == null ? null : date2String(
				new java.util.Date(ts.getTime()), format);
	}

	/**
	 * 将字符串转换成时间戳格式
	 * 
	 * @param str
	 *            需要转换的字符串
	 * 
	 * @param format
	 *            相应的转换格式，如果参数为Blank则表示按常规的三种格式转换
	 * 
	 * @return 如果不能正常转换则返回null
	 * @throws Exception
	 */
	public static Timestamp string2Timestamp(String str, String format) {
		return string2Date(str, format) == null ? null : new Timestamp(
				string2Date(str, format).getTime());
	}

	/**
	 * 为指定日期添加年。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            添加的年数
	 * 
	 * @return 添加后的日期
	 */
	public static java.util.Date addYears(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, num);
		return cal.getTime();
	}

	/**
	 * 为指定添加月份。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            添加对月数
	 * 
	 * @return 添加后的日期
	 */
	public static java.util.Date addMonths(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, num);
		return cal.getTime();
	}

	/**
	 * 为指定添加天数。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @param num
	 *            添加的天数
	 * 
	 * @return 添加后的日期
	 */
	public static java.util.Date addDays(java.util.Date date, int num) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, num);
		return cal.getTime();
	}

	/**
	 * 得到当年第一天的开始时间。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @return 当年第一天的开始时间
	 */
	public static java.util.Date getFirstDateOfYear(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, cal
				.getActualMinimum(Calendar.DAY_OF_YEAR));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 得到当月第一天的开始时间。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @return 当月第一天的开始时间
	 */
	public static java.util.Date getFirstDateOfMonth(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 得到当年的最后一天最后一秒。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @return 当年最后一天最后一秒
	 */
	public static java.util.Date getLastDateOfYear(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_YEAR, cal
				.getActualMaximum(Calendar.DAY_OF_YEAR));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 得到当月的最后一天最后一秒。
	 * 
	 * 
	 * @param date
	 *            日期
	 * @return 当月最后一天最后一秒
	 */
	public static java.util.Date getLastDateOfMonth(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 得到与当前日期间隔天数。
	 * 
	 * 
	 * @param date
	 *            待比较日期
	 * @return 比较结果，当前日期之前为负数，当前日期之后为整数，当前日期相等为0
	 */
	public static long getBetweenNowDays(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		long day = 0;
		try {
			day = (date.getTime() - cal.getTimeInMillis())
					/ (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			logger.warn("日期不正确，可能为空：" + date);
		}
		return day;
	}

	/**
	 * 获取一个简单的日期格式化对象
	 * 
	 *@return 一个简单的日期格式化对象
	 */
	private static SimpleDateFormat getFormatter(String parttern) {
		return new SimpleDateFormat(parttern);
	}

	/**
	 * 把字符串日期转换为f指定格式的Data对象
	 * 
	 * @param strDate
	 *            ,pattern
	 * @return
	 */
	public static Date format(String strDate, String pattern) {
		Date d = null;
		if (StringUtils.isNotEmpty(strDate)) {
			try {
				d = getFormatter(pattern).parse(strDate);
			} catch (ParseException pex) {
				logger.warn("日期与格式不匹配，日期为：" + strDate + "，待转换格式为：" + pattern);
			}
		}
		return d;
	}

	/**
	 * 日期格式化－将Date类型的日期格式化为String型
	 * 
	 * @param date
	 *            待格式化的日期
	 *@param pattern
	 *            时间样式
	 *@return 一个被格式化了的String日期
	 */
	public static String format(Date date, String pattern) {
		return getFormatter(pattern).format(date);
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 一个包含年月日的 Date型日期
	 */
	public static synchronized Date getCurrDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * 获取当前日期
	 * 
	 * @return 一个包含年月日的String型日期，但不包含时分秒。yyyy-mm-dd
	 */
	public static String getCurrDateStr() {
		return format(getCurrDate(), FORMAT_DATE);
	}

	/**
	 * 获取当前时间
	 * 
	 * @return 一个包含年月日时分秒的String型日期。hh:mm:ss
	 */
	public static String getCurrTime() {
		return format(getCurrDate(), FORMAT_TIME);
	}

	/**
	 * 获取当前完整时间,样式: yyyy－MM－dd hh:mm:ss
	 * 
	 * @return 一个包含年月日时分秒的String型日期。yyyy-MM-dd hh:mm:ss
	 */
	public static String getCurrDateTime() {
		return format(getCurrDate(), FORMAT_DATETIME);
	}

	/**
	 * 获取当前年分 样式：yyyy
	 * 
	 * @return 当前年分
	 */
	public static String getYear() {
		return format(getCurrDate(), FORMAT_YEAR);
	}

}
