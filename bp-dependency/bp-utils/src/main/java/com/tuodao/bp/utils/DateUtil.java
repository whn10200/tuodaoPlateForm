package com.tuodao.bp.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @description: 日期工具类
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 19:44
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class DateUtil extends DateUtils {

    private static final String LONG_DATE = "yyyy-MM-dd HH:mm:ss";

    private static final String SHORT_DATE = "yyyy-MM-dd";

    private static final String MIN_DATE = "yyyy-MM";
    
    private static final String HHMMSS = "HH:mm:ss";

    /**
     * 格式化日期 yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String formatLong(Date date){
        return format(date,LONG_DATE);
    }


    /**
     * 格式化日期 yyyy-MM-dd
     * @param date
     * @return
     */
    public static String formatShort(Date date){
        return format(date,SHORT_DATE);
    }

    /**
     * 格式化日期 HH:mm:ss
     * @param date
     * @return
     */
    public static String formatMin(Date date){
        return format(date,MIN_DATE);
    }

    /**
     * 根据给定的字符串格式来格式化日期
     * @param date 日期
     * @param match
     * @return
     */
    public static String format(Date date,String match){
        SimpleDateFormat sdf = new SimpleDateFormat(match);
        return sdf.format(date);
    }

    /**
     * 将字符串转为Date yyyy-MM-dd HH:mm:ss
     * @param str
     * @return
     */
    public static Date parseLong(String str)throws ParseException{
        return parseDate(str,LONG_DATE);
    }
    /**
     * 将字符串转为Date yyyy-MM-dd HH:mm:ss
     * @param str
     * @return
     */
    public static Date parseShort(String str)throws ParseException {
        return parseDate(str,SHORT_DATE);
    }


    /**
     * 相隔天数
     * @param beginTime
     * @param endTime
     * @return
     */
    public static long diffDay(Date beginTime, Date endTime) {
        return diffDay(convertLocal(beginTime), convertLocal(endTime));
    }

    /**
     * 相隔天数
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long diffDay(LocalDateTime beginDate, LocalDateTime endDate) {
        return beginDate.until(endDate, ChronoUnit.DAYS);
    }

    /**
     * Date to LocalDate
     * @param date
     * @return
     */
    public static LocalDateTime convertLocal(Date date) {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDate to Date
     * @param localDateTime
     * @return
     */
    public static Date convertDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * 获取几天前的时间
     * @param date 时间
     * @param day 天数
     * @return
     */
    public static Date subDay(Date date, int day) {
        LocalDateTime localDateTime = subDay(convertLocal(date), day);
        return convertDate(localDateTime);
    }

    /**
     * 获取几天前的时间
     * @param localDateTime 时间
     * @param day 天数
     * @return
     */
    public static LocalDateTime subDay(LocalDateTime localDateTime, int day) {
        return localDateTime.minusDays(day);
    }
    /**
     * yyyyMMddHHmmss
     * 
     * @param date
     * @return
     */
    public static String dateStr(Date date) {
        return dateStr(date, "yyyyMMddHHmmss");
    }

    /**
     * 日期转换为字符串 格式自定义
     * 
     * @param date
     * @param f
     * @return
     */
    public static String dateStr(Date date, String f) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(f);
        String str = format.format(date);
        return str;
    }
    
    
    /**
     * 格式化日期 yyyy-MM
     * @param date
     * @return
     */
    public static String formatHms(Date date){
        return format(date,HHMMSS);
    }
    public static void main(String[] args) {
        Date date1 = new Date(1604022400000L);
        Date date2 = new Date(1505810419798L);
        System.out.println(diffDay(date1, date2));

        Date date = new Date();
        System.out.println(subDay(convertLocal(date), 2).toString());
      
    }
    
    /**
     * 获取系统当前时间戳-秒数
     *
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static long getTimeStampSeconds() {
        return new Date().getTime() / 1000;
    }
    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }
}
