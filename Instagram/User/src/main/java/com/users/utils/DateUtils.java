package com.users.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

//    public static String getStringDateWithTimeSetToZero(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.set(Calendar.HOUR_OF_DAY, 0);
//        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.SECOND, 0);
//        cal.set(Calendar.MILLISECOND, 0);
//        return cal.getTime().toString();
//    }

    public static Date getTodaysDateWithTimeSetToZero() {
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDateWithTimeAndAmPm(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
//        cal.set(cal.get(Calendar.AM_PM));
        return cal.getTime();
    }
}
