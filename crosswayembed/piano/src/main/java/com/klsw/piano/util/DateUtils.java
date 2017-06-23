package com.klsw.piano.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {


    public static String formatDate(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    public static Date parseDateString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
