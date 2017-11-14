package com.tuodao.bp.model.enums;

import java.util.Arrays;

/**
 * 区分是PC,APP登录
 * PC的登录时效性是：30分钟，APP登录的时效性是30天,BANK的请求和响应内容,格式都不一样,所以,这里要进行区分
 * author hechuan
 * <p>
 * created on 2017/9/19
 * <p>
 * since V1.0.0
 */
public enum AccessType {
    // PC端请求
    PC,

    // APP端请求
    APP,

    // 银行端请求
    BANK,

    // 后台请求
    MANAGER;

    /**
     * 是否是APP
     * @param type
     * @return
     */
    public static boolean isApp(AccessType type){
        if(type.equals(APP)){
            return true;
        }
        return false;
    }

    /**
     * 是否是PC
     * @param type
     * @return
     */
    public static boolean isPC(AccessType type){
        if(type.equals(PC)){
            return true;
        }
        return false;
    }

    /**
     * 是否是BANK
     * @param type
     * @return
     */
    public static boolean isBank(AccessType type){
        if(type.equals(BANK)){
            return true;
        }
        return false;
    }

    /**
     * 是否是合法的访问请求
     * @param type
     * @return
     */
    public static boolean isValidAccessType(String type){
        AccessType[] values = AccessType.values();
        for(AccessType value : values){
            if (value.name().equalsIgnoreCase(type)){
                return true;
            }
        }
        return false;
    }

    /**
     * 请求是否正确的抬头
     * @param types
     * @return
     */
    public static boolean isRightRequest(AccessType[] types){
        AccessType[] values = AccessType.values();
        boolean allMatch = Arrays.asList(types).stream().allMatch(s -> Arrays.asList(values).contains(s));
        return allMatch;
    }

}
