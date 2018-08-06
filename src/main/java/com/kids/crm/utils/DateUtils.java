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

}
