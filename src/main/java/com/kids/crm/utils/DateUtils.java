package com.kids.crm.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    static Calendar cal = Calendar.getInstance();

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public static Date oneMonthBack(){
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date oneMonthBack(Date from){
        cal.setTime(from);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
    public static Date daysBack15(Date from){
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH, -15);
        return cal.getTime();
    }
    public static Date daysBack(Date from,int toBack){
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH,-1*toBack);
        return cal.getTime();
    }

    public static Date daysBack10(Date from){
        cal.setTime(from);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, -10);
        return cal.getTime();
    }
    public static Date daysBack1(Date from){
        cal.setTime(from);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    public static Date today(){
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    public static Date backEleventhDate(){
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //as used for lessThanEqual in the parameter name to. its eleventh
        cal.add(Calendar.DAY_OF_MONTH, -10);
        return cal.getTime();
    }
    public static Date backFortyFirstDate(){
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        // take 40 because condition is greaterThan as from parameter. its forty first
        cal.add(Calendar.DAY_OF_MONTH, -40);
        return cal.getTime();
    }

}
