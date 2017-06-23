package com.klsw.klswWebService.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

//import org.junit.Test;


public class BeanUtil {

	/**
	 * 方法功能：获取两次领取威豆相差的天数
	 * 
	 * @param oldDate
	 *            上次领取威豆的时间
	 * @param newDate
	 *            本次请求领取威豆的时间
	 * @return
	 * @throws ParseException 
	 */
	public static int days(Date oldDate, Date newDate) throws ParseException {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(oldDate);

		int day1 = calendar.get(Calendar.DAY_OF_YEAR);
		int year1 = calendar.get(Calendar.YEAR);
		

		calendar.setTime(newDate);

		int day2 = calendar.get(Calendar.DAY_OF_YEAR);
		int year2 = calendar.get(Calendar.YEAR);
		
		int cross_year_days = 0;
		for(int i=year1;i<year2;i++) {
			cross_year_days += getAllYearDays(i);
		}
		
		return day2 + cross_year_days - day1;
	}

	public static int getAllYearDays(Integer year) throws ParseException {
		Date date = DateFormat.getDateInstance().parse(year + "-12-31 0:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_YEAR);
	}
	
//	@Test
//	public void testDays() {
//		try {
////			int year = 2018;
////			Date date = DateFormat.getDateInstance().parse(year + "-12-31 0:00:00");
////			Calendar calendar = Calendar.getInstance();
////			calendar.setTime(date);
////			System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
//			
//			
//			Date oldDate = DateFormat.getDateInstance().parse("2016-12-31 10:00:00");
//			Date newDate = DateFormat.getDateInstance().parse("2017-08-01 2:00:00");
//			
//			System.out.println(days(oldDate, newDate));
//			
//			
////			System.out.println(day1 + "   " + day2);
////			System.out.println(year1 + "   " + year2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
