package com.fenghj.android.utilslibrary;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间相关工具类
 *
 * <p>Created by Fenghj on 2017/7/11.<p/>
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {
    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }
    /**
     * 获取与当前时间的差
     */
    public static String formatDate1(long t) {
        String strDate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String systemTime = sdf.format(new Date());
        String time = sdf.format(t);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -1);
        String yesterday = sdf.format(c.getTime());
        c.add(Calendar.DAY_OF_MONTH, -1);
        String yesterday_before = sdf.format(c.getTime());

        try {
            Date begin = sdf.parse(time);
            Date end = sdf.parse(systemTime);
            long between = (end.getTime() - begin.getTime()) / (1000 * 60);
            if (time.substring(0, 10).equals(yesterday.substring(0, 10))) {
                strDate = "昨天";
            } else if (time.substring(0, 10).equals(
                    yesterday_before.substring(0, 10))) {
                strDate = "前天";
            } else if (between <= 0) {
                strDate = "1分钟前";
            } else if (between < 60 && between > 0) {
                strDate = Math.round(between) + "分钟前";
            } else if (between >= 60 && between < 60 * 24) {
                strDate = Math.round(between / 60) + "小时前";
            } else {
                strDate = time.substring(0, 10);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 日期转换格式：yyyy-MM-dd
     *
     * @param t long型时间
     * @return 2014-03-10
     */
    public static String formatDate2(long t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(t);
    }

    /**
     * 日期转换格式：yyyy年MM月dd日
     *
     * @param t long型时间
     * @return 2014年03月10日
     */
    public static String formatDate3(long t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(t);
    }

    /**
     * 日期转换格式：yyyy-MM-dd HH:mm:ss
     *
     * @param t long型时间
     * @return 2014-03-10 09:01:01
     */
    public static String formatDate4(long t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(t);
    }

    /**
     * string类型转换为date类型
     * <br>ps：strTime的时间格式必须是yyyy-MM-dd HH:mm:ss
     *
     * @param strTime 要转换的string类型的时间
     * @return date类型
     */
    public static Date stringToDate(String strTime)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.parse(strTime);
    }

    /**
     * string类型转换为long类型
     * <br>ps：strTime的时间格式必须是yyyy-MM-dd HH:mm:ss
     */
    public static long stringToLong(String strTime) throws Exception {
        Date date = stringToDate(strTime); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            return dateToLong(date);
        }
    }

    /**
     * date类型转换为long类型
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
