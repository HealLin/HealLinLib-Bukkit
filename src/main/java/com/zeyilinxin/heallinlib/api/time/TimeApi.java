package com.zeyilinxin.heallinlib.api.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeApi {

    public static String getTime(String pattern){
        if (pattern == null || pattern.isEmpty()){
            SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
            return df.format(new Date());
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }

    public static long getTime(){
        return System.currentTimeMillis() / 1000;
    }

    public static long getDay(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        cal.set(Calendar.MINUTE, 0);
        //将秒至0
        cal.set(Calendar.SECOND,0);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / 1000;
    }

    public static long getHandleDay(HandleDay handleDay){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        cal.set(Calendar.MINUTE, 0);
        //将秒至0
        cal.set(Calendar.SECOND,0);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 0);
        handleDay.run(cal);
        return cal.getTimeInMillis() / 1000;
    }

    public interface HandleDay{

        void run(Calendar cal);
    }


    public static String snoozeDay(String pattern ){
        if (pattern == null || pattern.isEmpty()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            cal.add(Calendar.DATE,1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
            String format = sdf.format(cal.getTime());
            return format;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,1);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(cal.getTime());
        return format;
    }

    public static long snoozeDay(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //将小时至0
        cal.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        cal.set(Calendar.MINUTE, 0);
        //将秒至0
        cal.set(Calendar.SECOND,0);
        //将毫秒至0
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE,100);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = cal.getTime();
        sdf.format(date);
        return (date.getTime() / 1000);
    }
}
