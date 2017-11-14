package com.tuodao.bp.utils;

import java.util.Date;

/**
 * @description: 省份证工具类
 * @author: mif
 * @date: 2017/8/28
 * @time: 17:17
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class IdCardUtils {

    /**
     * 根据身份证号码获取生日
     *
     * @param idCard 身份证号码
     * @return 生日
     */
    public static Date getBirthday(String idCard) {
        String birthdayStr = idCard.substring(6, 14);
        return TimeUtils.format(birthdayStr, "yyyyMMdd");
    }

    /**
     * 根据身份证获取性别
     *
     * @param idCard 身份证号码
     * @return 性别（1:男；2:女）
     */
    public static Integer getSex(String idCard) {
        int sex = (Integer.parseInt(idCard.substring(16, 17)) + 1) % 2;
        return sex == 0 ? 1 : 2;
    }

    /**
     * 根据身份证获取星座
     *
     * @param idCard 身份证号码
     * @return 星座
     */
    public static String getConstellation(String idCard) {
        int month = Integer.parseInt(idCard.substring(10, 12));
        int day = Integer.parseInt(idCard.substring(12, 14));

        String value = "";
        if (((month == 3) && (day >= 21)) || ((month == 4) && (day <= 19))) value = "白羊座";
        if (((month == 4) && (day >= 20)) || ((month == 5) && (day <= 20))) value = "金牛座";
        if (((month == 5) && (day >= 21)) || ((month == 6) && (day <= 21))) value = "双子座";
        if (((month == 6) && (day >= 22)) || ((month == 7) && (day <= 22))) value = "巨蟹座";
        if (((month == 7) && (day >= 23)) || ((month == 8) && (day <= 22))) value = "狮子座";
        if (((month == 8) && (day >= 23)) || ((month == 9) && (day <= 22))) value = "处女座";
        if (((month == 9) && (day >= 23)) || ((month == 10) && (day <= 23))) value = "天秤座";
        if (((month == 10) && (day >= 24)) || ((month == 11) && (day <= 22))) value = "天蝎座";
        if (((month == 11) && (day >= 23)) || ((month == 12) && (day <= 21))) value = "射手座";
        if (((month == 12) && (day >= 22)) || ((month == 1) && (day <= 19))) value = "摩羯座";
        if (((month == 1) && (day >= 20)) || ((month == 2) && (day <= 18))) value = "水瓶座";
        if (((month == 2) && (day >= 19)) || ((month == 3) && (day <= 20))) value = "双鱼座";
        return value;
    }

    /**
     * 根据身份证号码获取生肖
     *
     * @param idCard 身份证号码
     * @return
     */
    public static String getZodiac(String idCard) {
        int year = Integer.parseInt(idCard.substring(6, 10));
        String zodiac[] = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        int i = (year - 4) % 12;
        return zodiac[i];
    }


    public static void main(String[] args) {
        String idCard = "330724199212190712";
        System.out.println("性别 = " + getSex(idCard));
        System.out.println("生日 = " + getBirthday(idCard));
        System.out.println("星座 = " + getConstellation(idCard));
        System.out.println("生肖 = " + getZodiac(idCard));
    }
}
