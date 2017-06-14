package com.rogrand.atom.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间日期管理
 */

public class DateMgr {

    private static final long MINUTE = 60 * 1000; // 1分钟
    private static final long HOUR = 60 * MINUTE; // 1小时
    private static final long DAY = 24 * HOUR; // 1天
    private static final long MONTH = 31 * DAY; // 月
    private static final long YEAR = 12 * MONTH; // 年
    private static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"; // 日期格式：yyyy-MM-dd HH:mm:ss
    private static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm"; // 日期格式：yyyy-MM-dd HH:mm
    private static final String DF_MM_DD_HH_MM = "MM-dd HH:mm"; // 日期格式：MM-dd HH:mm
    private static final String DF_YYYY_MM_DD = "yyyy-MM-dd"; // 日期格式：yyyy-MM-dd
    private static final String DF_HH_MM_SS = "HH:mm:ss"; // 日期格式：HH:mm:ss
    private static final String DF_HH_MM = "HH:mm"; // 日期格式：HH:mm

    public DateMgr() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     */
    public static String formatFriendly(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "个小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL 日期
     */
    public static String formatDateTime(long dateL) {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        return sdf.format(new Date(dateL));
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL 日期
     */
    public static String formatDateTime(long dateL, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(new Date(dateL));
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param formater 日期
     */
    public static String formatDateTime(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

}
