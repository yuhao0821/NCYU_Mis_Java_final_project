package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    static public String LongToString(long TimeLong, String format){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            date.setTime(TimeLong*1000);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return sdf.format(date);
    }

    static String DateToString(Date date,String format){
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    static Date StringToDate(String timeString,String format){
        // 获取当前日期时间
        Calendar calendar = Calendar.getInstance();
        
        // 解析输入的时间字符串
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date time;
        try {
            time = sdf.parse(timeString);
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
        
        // 设置时间部分
        Calendar inputCal = Calendar.getInstance();
        inputCal.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, inputCal.get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, inputCal.get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        
        // 返回结果
        return calendar.getTime();
    }
    public static String getShortDateString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    public static String getWeekday() {
        // 獲取當前日期的星期幾
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        // 將星期幾轉換為對應的數字字串
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                return "7";
            case Calendar.MONDAY:
                return "1";
            case Calendar.TUESDAY:
                return "2";
            case Calendar.WEDNESDAY:
                return "3";
            case Calendar.THURSDAY:
                return "4";
            case Calendar.FRIDAY:
                return "5";
            case Calendar.SATURDAY:
                return "6";
            default:
                return ""; // 在非預期情況下返回空串或者其他錯誤標記
        }
    }
    
}

