package com.fenghj.android.utilslibrary;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间相关工具类
 *
 * <p>Created by Fenghj on 2017/7/11.<p/>
 */
@SuppressLint("SimpleDateFormat")
public class TimeUtils {
    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC  = 1000;
    /**
     * 分与毫秒的倍数
     */
    public static final int MIN  = 60000;
    /**
     * 时与毫秒的倍数
     */
    public static final int HOUR = 3600000;
    /**
     * 天与毫秒的倍数
     */
    public static final int DAY  = 86400000;

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取友好型与当前时间的差
     *
     * @param time 毫秒时间戳
     * @return 友好型与当前时间的差
     * <ul>
     * <li>如果在1小时内，显示XXX分钟前</li>
     * <li>如果在今天内，显示XXX小时前</li>
     * <li>如果是昨天的，显示昨天</li>
     * <li>其余显示，2017-11-18</li>
     * <li>时间不合法的情况全部日期和时间信息，如星期六 十月 27 14:21:20 CST 2007</li>
     * </ul>
     */
    public static String formatDate1(long time) {
        long now = System.currentTimeMillis();
        long span = now - time;

        if (span < 0)
            return String.format("%tc", time);
        if (span < HOUR) {
            return String.format(Locale.getDefault(), "%d分钟前", span / MIN);
        } else if(span < DAY) {
            return String.format(Locale.getDefault(), "%d小时前", span / HOUR);
        }

        long wee = getWeeOfToday();
        if (time < wee && time >= wee - DAY) {
            return "昨天";
        } else {
            return String.format("%tF", time);
        }
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
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
