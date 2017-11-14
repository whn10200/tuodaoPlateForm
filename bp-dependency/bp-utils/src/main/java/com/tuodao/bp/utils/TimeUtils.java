package com.tuodao.bp.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.jni.Local;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间格式化工具类。
 *
 * @author hechuan
 * @created 2017年3月25日
 * @since 1.0.0
 */
public class TimeUtils {

    public static final long DAY_MILLISECOND = 86400000L;

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String getSysTime(String pattern) {

        return formatSysTime(new SimpleDateFormat(pattern, Locale.ENGLISH));
    }

    private static String formatSysTime(SimpleDateFormat format) {

        String str = format.format(Calendar.getInstance().getTime());
        return str;
    }

    public static String formatNomal(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Date date, String pattern) {

        if (date == null) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        String str = format.format(date);

        return str;
    }

    public static Date addMonth(Date date, int month) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return cal.getTime();
    }

    public static Date getYesterday() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }

    public static Date getLastmonth() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    public static Date getBeforelastmonth() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -2);
        return cal.getTime();
    }

    public static Date getLastyear() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

    public static Date getDate(int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static Date getDate(Date date, int days) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static Date getDateBySecond(Date date, int second) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    public static Date getHour(int hours) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return cal.getTime();
    }

    public static boolean validTime(String str, String pattern) {

        DateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

        Date date = null;
        try {
            date = (Date) formatter.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return str.equals(formatter.format(date));
    }

    public static Date format(String str, String pattern) {

        if (StringUtils.isEmpty(str)) {
            return null;
        }

        DateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

        Date date = null;
        try {
            date = (Date) formatter.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return date;
    }

    public static String getSysYear() {

        return getSysTime("yyyy");
    }

    public static String getSysTime() {

        return getSysTime("yyyy-MM-dd HH:mm:ss");
    }

    public static String getSysTimeS() {

        return getSysTime("yyyy-MM-dd HH:mm:ss,SSS");
    }

    public static String getSysTimeLong() {

        return getSysTime("yyyyMMddHHmmss");
    }

    public static String getSysTimeSLong() {

        return getSysTime("yyyyMMddHHmmssSSS");
    }

    public static String getSysTimeUsLong() {

        String nanoTime = System.nanoTime() + "";
        return getSysTime("yyyyMMddHHmmssSSS") + nanoTime.substring(nanoTime.length() - 3, nanoTime.length());
    }

    public static String getSysdate() {

        return getSysTime("yyyy-MM-dd");
    }

    public static String getSysSignupTime() {

        return getSysTime("yyyy年MM月dd日HH:mm");
    }

    public static String getSysyearmonthInt() {

        return getSysTime("yyyyMM");
    }

    public static String getSysdateInt() {

        return getSysTime("yyyyMMdd");
    }

    public static String getSysday() {

        return getSysTime("dd");
    }

    public static String getSysdateTimeStart() {

        return getSysdate() + " 00:00:00";
    }

    public static String getSysdateTimeEnd() {

        return getSysdate() + " 23:59:59";
    }

    public static String getSysdateTimeEndLong() {

        return getSysdateInt() + "235959";
    }

    public static String getSysDateLocal() {

        return getSysTime("yyyy年MM月dd日");
    }

    public static String getTimeFormat(String str) {

        return format(format(str, "yyyyMMddHHmmss"), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDateFormat(String str) {

        return format(format(str, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd");
    }

    public static String getDateFormatLocal(String str) {

        return format(format(str, "yyyy-MM-dd HH:mm:ss"), "yyyy年MM月dd日");
    }

    public static String getYesterdayInt() {

        return format(getYesterday(), "yyyyMMdd");
    }

    public static String getYesterdayDate() {

        return format(getYesterday(), "yyyy-MM-dd");
    }

    private static int getMondayPlus() {

        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    public static String getLastmondayInt() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, getMondayPlus() - 7);

        return format(cal.getTime(), "yyyyMMdd");
    }

    public static String getLastmondayDate() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, getMondayPlus() - 7);

        return format(cal.getTime(), "yyyy-MM-dd");
    }

    private static int getSundayPlus() {

        Calendar cal = Calendar.getInstance();
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

        if (dayOfWeek == 1) {
            return -7;
        } else {
            return 1 - dayOfWeek;
        }
    }

    public static String getLastsundayInt() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, getSundayPlus());

        return format(cal.getTime(), "yyyyMMdd");
    }

    public static String getLastsundayDate() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, getSundayPlus());

        return format(cal.getTime(), "yyyy-MM-dd");
    }

    public static String getLastmonthInt() {

        return format(getLastmonth(), "yyyyMM");
    }

    public static String getBeforelastmonthInt() {

        return format(getBeforelastmonth(), "yyyyMM");
    }

    public static String getLastmonthDate() {

        return format(getLastmonth(), "yyyy-MM");
    }

    public static String getLastmonthenddayInt() {

        return format(getDate(getThismonthTime(1, 0, 0), -1), "yyyyMMdd");
    }

    public static String getLastmonthendDate() {

        return format(getDate(getThismonthTime(1, 0, 0), -1), "yyyy-MM-dd");
    }

    public static String getThismonthInt() {

        return format(getThismonthTime(1, 0, 0), "yyyyMM");
    }

    public static String getThismonthDate() {

        return format(getThismonthTime(1, 0, 0), "yyyy-MM");
    }

    public static String getMonthstarttime(String month) {

        return month + "01000000";
    }

    public static String getMonthendtime(String month) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(format(month, "yyyyMM"));
        cal.add(Calendar.MONTH, 1);

        return format(getDate(cal.getTime(), -1), "yyyyMMdd") + "235959";
    }

    public static String getLastyearInt() {

        return format(getLastyear(), "yyyy");
    }

    public static String getDateInt(int days) {

        return format(getDate(days), "yyyyMMdd");
    }

    public static String getDateFormat(int days) {

        return format(getDate(days), "yyyy-MM-dd");
    }

    public static String getDateFormatLocal(int days) {

        return format(getDate(days), "yyyy年MM月dd日");
    }

    public static String getTimeFormatDays(int days) {

        return format(getDate(days), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeFormatHours(int hours) {

        return format(getHour(hours), "yyyy-MM-dd HH:mm:ss");
    }

    public static Date getHourTime(int hour) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // calendar.set(Calendar.HOUR, hour);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getHourTime(int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getHourTime(int hour, int minute, int second) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getYesterdayHourTime(int hour) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getYesterdayHourTime(int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getYesterdayHourTime(int hour, int minute, int second) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getTomorrowHourTime(int hour) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getTomorrowHourTime(int hour, int minute, int second) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getTomorrowHourTime(int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getThismonthTime(int day, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getNextmonthTime(int day, int hour, int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getMinute(int minute) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minute);
        Date time = calendar.getTime();

        return time;
    }

    public static Date getSecond(int second) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, second);
        Date time = calendar.getTime();

        return time;
    }

    public static int countMonths(String end, String start, String pattern) {

        Calendar calend = Calendar.getInstance();
        calend.setTime(format(end, pattern));

        Calendar calstart = Calendar.getInstance();
        calstart.setTime(format(start, pattern));

        return (calend.get(Calendar.YEAR) * 12 + calend.get(Calendar.MONTH))
                - (calstart.get(Calendar.YEAR) * 12 + calstart.get(Calendar.MONTH));
    }

    public static long subtract(String end, String start, String pattern) {

        return format(end, pattern).getTime() - format(start, pattern).getTime();
    }

    public static long subtractDay(String end, String start, String pattern) {

        return subtract(end, start, pattern) / DAY_MILLISECOND;
    }

    public static long subtractSSS(String start) {

        return subtract(getSysTimeS(), start, "yyyy-MM-dd HH:mm:ss,SSS");
    }

    public static long subtractSSS(String end, String start) {

        return subtract(end, start, "yyyy-MM-dd HH:mm:ss,SSS");
    }

    public static long subtractLong(String start) {

        return subtract(getSysTimeLong(), start, "yyyyMMddHHmmss");
    }

    public static long subtractLong(String end, String start) {

        return subtract(end, start, "yyyyMMddHHmmss");
    }

    public static long subtractSLong(String end, String start) {

        return subtract(end, start, "yyyyMMddHHmmssSSS");
    }

    public static long subtractLongS(String end, String start) {

        return subtract(end, start, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatGMTTime(String str, String pattern, String TimeZoneFormat) {

        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);

        Date date = null;
        try {
            date = (Date) dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone(TimeZoneFormat));
        String time = format.format(date);

        return time;
    }

    public static String formatTime(String str, String strpattern, String pattern, String TimeZoneFormat) {

        DateFormat dateFormat = new SimpleDateFormat(strpattern, Locale.ENGLISH);

        Date date = null;
        try {
            date = (Date) dateFormat.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        format.setTimeZone(TimeZone.getTimeZone(TimeZoneFormat));
        String time = format.format(date);

        return time;
    }

    public static String formatGMT8Time(String str) {

        return formatGMTTime(str, "yyyy-MM-dd HH:mm:ss", "GMT+08:00");
    }

    public static void sleep(long millis) {

        if (millis <= 0L) {
            return;
        }

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String secondtoMinutesecond(long second) {

        String result = "";
        result = second / 60 > 0 ? second / 60 + "分" : "";
        result = result + (second % 60 > 0 ? (second % 60 + "秒") : (!StringUtils.isEmpty(result) ? "钟" : "0分钟"));

        return result;
    }

    public static String getAddDayendtime(int day) {

        return format(getDate(day), "yyyy-MM-dd") + " 23:59:59";
    }

    public static boolean checkTime(String time) {

        if (time.length() != 14) {
            return false;
        }

        if (!time.matches("[2][0-9]+")) {
            return false;
        }

        return TimeUtils.validTime(time, "yyyyMMddHHmmss");
    }

    /**
     * 获取两个时间之间相差天数
     *
     * @param start
     * @param end
     * @return
     */
    public static int getDifDays(Date start, Date end) {
        return (int) ((end.getTime() - start.getTime()) / (1000 * 3600 * 24));
    }

    /**
     * 获取两个时间之间相差秒
     * @param start
     * @param end
     * @return
     */
    public static long getDifSec(Date start, Date end) {
        return diffSec(convertLocal(start), convertLocal(end));
    }

    /**
     * 比较两个时间相差月数
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     */
    public static int getDitMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 获取时间的第二天零点
     *
     * @param date
     * @return
     */
    public static Date getTomorrowZero(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当前时间零点
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当前时间23:59:59 timestamp 会加一秒
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 获取当前时间以前一天23:59:59 timestamp 会加一秒
     *
     * @param date
     * @return
     */
    public static Date getBeforeDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 根据指定类型计算两个日期相差的时间 eg. dateDiff(birth, today, Calendar.MONTH) 孩子的月龄
     *
     * @param sDate 开始时间
     * @param eDate 结束时间
     * @return 根据 diffType计算的 eDate - sDate 时差
     */
    public static String dateDiff(Date sDate, Date eDate) {

        long between = (eDate.getTime() - sDate.getTime()) / 1000;  // 除以1000是为了转换成秒
        long day = between / (24 * 3600);
        long hour = between % (24 * 3600) / 3600;
        long min = between % (24 * 3600) % 3600 / 60;
        long sec = between % (24 * 3600) % 3600 % 60;
        StringBuilder sb = new StringBuilder();
        if (0L != day)
            sb.append(day + "天");
        if (0L != hour)
            sb.append(hour + "小时");
        if (0L != min)
            sb.append(min + "分");
        if (0L != sec) {
            sb.append(sec + "秒");
        }
        if (StringUtils.isNotBlank(sb.toString())) {
            sb.append("之前");
        }
        return sb.toString();

    }

    public static String dateDiff1(Date sDate, Date eDate) {
        return dateDiff(convertLocal(sDate), convertLocal(eDate));
    }

    public static String dateDiff(LocalDateTime beginTime, LocalDateTime endTime) {
        StringBuffer sb = new StringBuffer();
        Duration duration = Duration.between(beginTime, endTime);
        long seconds = duration.getSeconds();
        long hours = seconds / (60*60);
        hours = Math.abs(hours);
        if(hours == 0L) {
            sb.append("00:");
        } else {
            sb.append(hours + ":");
        }
        long minutes = ((seconds % (60*60)) / 60);
        minutes = Math.abs(minutes);
        if(minutes == 0L) {
            sb.append("00:");
        } else {
            sb.append(minutes + ":");
        }
        long secs = (seconds % 60);
        seconds = Math.abs(secs);
        if(secs == 0L) {
            sb.append("00");
        } else {
            sb.append(secs);
        }

        return sb.toString();
    }

    public static long diffSec(LocalDateTime beginTime, LocalDateTime endTime) {
        Duration duration = Duration.between(beginTime, endTime);
        long seconds = duration.getSeconds();
        seconds = Math.abs(seconds);
        return seconds;
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

    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    public static Date addMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public static Date getNextMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        return calendar.getTime();
    }

    /**
     * 判断当前时间是否在晚10点到次日9点 之间
     *
     * @return 结果
     */
    public static boolean between22And9() {
        Date now = new Date();
        int hours = now.getHours();
        return hours > 22 || hours < 9;
    }

    public static boolean between(Date p, Date begin, Date end) {
        return p.getTime() >= begin.getTime() && p.getTime() <= end.getTime();
    }

    /**
     * 获取本月左后一天
     *
     * @return
     */
    public static Date getMonthEnd() {
        Calendar calendar = Calendar.getInstance();
        // 获取前月的最后一天
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static LocalTime convertTime(String time) {
        LocalTime localTime =  LocalTime.parse(time, TIME_FORMATTER);
        return localTime;
    }

    public static Date convertToDate(String time) {
        return convertToDate(convertTime(time));
    }

    public static Date convertToDate(LocalTime localTime) {
        Instant instant = localTime.atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static void main(String[] args) throws ParseException {


//        System.out.println("---------->" + getSysSignupTime());
//
//        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        try {
//            java.util.Date begin = dfs.parse("2009-07-10 10:22:21.214");
//            java.util.Date end = dfs.parse("2010-08-20 11:24:49.145");
//            String dateDiff = dateDiff(begin, end);
//            System.out.println(dateDiff);
//            System.out.println(getDitMonth(begin, end));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        Date addDay = addDay(new Date(), 8);
//        System.out.println(format(addDay, "yyyy-MM-dd HH:mm:ss.SSS"));
//
//        System.out.println(getNextMonth());
//
//        System.out.println(getBeforeDayEnd(new Date()));
//        System.out.println(between22And9());
//        System.out.println(getMonthEnd());
//
//        System.out.println(between(new Date(), convertToDate("12:00"), convertToDate("12:40")));

        Date now = new Date();
        System.out.println(dateDiff1(addMinute(now, 70), now));
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date begin=dfs.parse("2017-01-02 11:30:24");
        java.util.Date end = dfs.parse("2017-01-03 11:30:27");
        System.out.println(getDifSec(begin, end));
    }
}
