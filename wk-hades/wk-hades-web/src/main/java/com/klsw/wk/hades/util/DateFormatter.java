package com.klsw.wk.hades.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateFormatter implements Formatter<Date>{

	public String print(Date object, Locale locale) {
		return null;
	}

	public Date parse(String text, Locale locale) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(text);
		} catch (Exception e) {
			format = new SimpleDateFormat("yyyy-MM-dd");
			date = format.parse(text);
		}
		return date;
	}

	public static String parse(Long date) {	
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date_1 =new Date(date);
	    	return format.format(date_1);
		
	}
	
	public static void main(String args[]){
		Long timestamp = 1462842478000L;
		String date=DateFormatter.parse(timestamp);
		System.out.println(date);
		
	}
}
