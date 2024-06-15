package damn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTime {
    static public String LongToString(long TimeLong, String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            date.setTime(TimeLong * 1000);
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
        }
        return sdf.format(date);
    }

    static String DateToString(Date date) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(date);
    }

    static Date StringToDate(String timeString) {
        // 获取当前日期时间
        Calendar calendar = Calendar.getInstance();

        // 解析输入的时间字符串
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date time;
        try {
            time = sdf.parse(timeString);
        } catch (ParseException e) {
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
}