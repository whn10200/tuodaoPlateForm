package com.tuodao.bp.traningcenter.until;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang3.time.DateUtils类
 *
 * @author wyl
 * @version 2014年9月18日
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    private static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss",
            "yyyy年MM月dd日","yyyy/MM/dd HH:mm","HH:mm","HH:mm:ss","yyyy.MM.dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     *
     * @param pattern 时间格式
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式（"yyyy-MM-dd","yyyy/MM/dd"）
     */
    public static Date parseDate(String str) {
        try {
            return parseDate(str, parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = System.currentTimeMillis() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取系统当前时间戳-秒数
     *
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static long getTimeStampSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取系统当前时间戳-秒数
     *
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static String getCurrTimeStampString() {
        return String.valueOf(getTimeStampSeconds());
    }

    /**
     * 获取指定时间的时间戳-秒数
     *
     * @param date
     * @return 自 1970 年 1 月 1 日 00:00:00 GMT 以来此日期表示的秒数。
     */
    public static long getTimeStampSeconds(Date date) {
        return (date.getTime() / 1000);
    }

    /**
     * 时间戳(秒数)转换为时间
     *
     * @param timeStamp 十位数
     * @return
     */
    public static Date getDate(long timeStamp) {
        Date date = new Date();
        date.setTime(timeStamp * 1000);
        return date;
    }

    /**
     * 时间戳转换为时间字符串
     *
     * @param timeStamp 十位数
     * @param pattern   时间格式
     * @return
     */
    public static String getDateString(long timeStamp, Object... pattern) {
        return formatDate(getDate(timeStamp), pattern);
    }


    /**
     * 时间戳转换为时间字符串，默认输出格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getDateString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.valueOf(timeStamp), parsePatterns[1]);
    }


    /**
     * 时间戳转换为时间字符串，默认输出格式为：yyyy-MM-dd HH:mm:ss
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getTimeString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.valueOf(timeStamp), parsePatterns[6]);
    }
    /**
     * 时间戳转换为时间字符串，默认输出格式为：yyyy.MM.dd HH:mm
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getStringTime(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.valueOf(timeStamp), parsePatterns[8]);
    }


    /**
     * 时间戳转换为时间字符串，默认输出格式为： HH:mm:ss
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getSecTimeString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.valueOf(timeStamp), parsePatterns[7]);
    }


    /**
     * 时间戳转换为时间字符串，默认输出格式为：yyyy-MM-dd
     *
     * @param timeStamp 十位数字符串
     * @return
     */
    public static String getSimpleDateString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.valueOf(timeStamp), parsePatterns[0]);
    }


    public static String getTwoDateString(String timeStamp) {
        if (timeStamp == null || "".equals(timeStamp)) {
            return "";
        }
        return getDateString(Long.valueOf(timeStamp), parsePatterns[5]);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (StringUtils.isBlank(seconds)) {
            return "";
        }
        if (StringUtils.isBlank(format))  {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return 精确到秒的字符串
     */
    public static String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取系统当前时间的秒位时间戳
     *
     * @return
     */
    public static int getDateLong() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * @param now
     * @param days
     * @return 字符串
     */
    public static String addDays(String now, int days) {
        Date nowDate = DateUtils.getDate(Long.valueOf(now));
        Date newDate = DateUtils.addDays(nowDate, days);
        return String.valueOf(getTimeStampSeconds(newDate));
    }

    public static String addMonths(String now, int months) {
        Date nowDate = DateUtils.getDate(Long.valueOf(now));
        Date newDate = DateUtils.addMonths(nowDate, months);
        return String.valueOf(getTimeStampSeconds(newDate));
    }


    public static long addNewMonths(long now, int months) {
        Date nowDate = DateUtils.getDate(now);
        Date newDate = DateUtils.addMonths(nowDate, months);
        return getTimeStampSeconds(newDate);
    }


    /**
     * @param now
     * @param days
     * @return 字符串
     */
    public static long addNewDays(long now, int days) {
        Date nowDate = DateUtils.getDate(now);
        Date newDate = DateUtils.addDays(nowDate, days);
        return getTimeStampSeconds(newDate);
    }


    /**
     * 计算日期相差天数(不算时分秒 如2016-04-05 23:59:59 与 2016-04-06 00:00:00 相差也是1天)
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfTwo(Date fDate, Date oDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        return day2 - day1;
    }

    /**
     * 计算日期相差天数(不算时分秒 如2016-04-05 23:59:59 与 2016-04-06 00:00:00 相差也是1天)
     *
     * @param fDate
     * @param oDate
     * @return
     */
    public static int daysOfToo(Date fDate, Date oDate) {

        Long diff = oDate.getTime() - fDate.getTime();//这样得到的差值是微秒级别
        Long days = diff / (1000 * 60 * 60 * 24);
        return days.intValue();
    }

    /**
     * 获取中文年月日
     *
     * @param date
     * @return
     */
    public static String getChineseDate(Date date) {

        return DateFormatUtils.format(date, parsePatterns[4]);
    }
    /*
    * 将时间转换为时间戳
    */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /**
	 * 日期转换为字符串 格式自定义
	 *
	 * @param date
	 * @return
	 */
	public static String dateStr(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String str = format.format(date);
		return str;
	}

    /**
     * 取得当月天数
     * */
    public static int getCurrentMonthLastDay()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
}
