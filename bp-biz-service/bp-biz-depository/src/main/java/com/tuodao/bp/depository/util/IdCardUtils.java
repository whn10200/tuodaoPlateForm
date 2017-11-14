package com.tuodao.bp.depository.util;

/**
 * @description: 身份证工具类
 * @author: 杨超凯
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class IdCardUtils {

    /**
     * 根据身份证号码获取生日
     *
     * @param idCard 身份证号码
     * @return 生日
     */
    public static String getBirthday(String idCard) {
        String birthdayStr = idCard.substring(6, 14);
        return birthdayStr;
    }

    /**
     * 根据身份证获取性别
     *
     * @param idCard 身份证号码
     * @return 性别（0:男；1:女）
     */
    public static String getSex(String idCard) {
        return new Integer((new Integer(idCard.substring(16, 17))+1) % 2).toString();
    }

   


    public static void main(String[] args) {
        String idCard = "330724199212190712";
        System.out.println("性别 = " + getSex(idCard));
        System.out.println("生日 = " + getBirthday(idCard));
    }
}
