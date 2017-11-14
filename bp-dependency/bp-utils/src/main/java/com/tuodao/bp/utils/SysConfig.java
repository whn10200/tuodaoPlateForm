package com.tuodao.bp.utils;

/**
 * @description: 全局系统参数
 * @author: 王艳兵
 * @date: 2017/8/28
 * @time: 14:27
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SysConfig {

    /**
     * 获取系统参数的值
     * @param key
     * @return
     */
    public static String getValue(String key){
        return key;
    }

    /**
     * 获取系统参数值
     * @param key
     * @return
     */
    public static double getDouble(String key){
        String value = getValue(key);
        if(value != null){
            return Double.parseDouble(value);
        }
        return 0D;
    }
}
