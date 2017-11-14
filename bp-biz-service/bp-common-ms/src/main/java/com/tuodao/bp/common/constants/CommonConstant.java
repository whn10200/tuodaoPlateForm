package com.tuodao.bp.common.constants;

/**
 * @description: 公共服务常量
 * @author: mif
 * @date: 2017/9/8
 * @time: 11:17
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CommonConstant {
    /**
     * 短信服务商（1：互亿;2:创蓝;3:梦网短信）
     */
    public static final int HUYI_SMS_SERVICER = 1;
    public static final int CHUANGLAN_SMS_SERVICE = 2;
    public static final int EMP_SMS_SERVICE = 3;

    /**
     * 基础数据类型：business：异常；iplimit：ip黑名单
     */
    public static final String BUSSINESS_MODULE = "business";
    public static final String IPLIMIT_MODULE = "iplimit";

    /**
     * 分隔符
     */
    public static final String SPLIT_CHAR = ";";

    /**
     * 状态（0：禁用,1:启用）
     */
    public static final int SMS_ACCOUNT_STATUS_ORBIDDEN = 0;
    public static final int SMS_ACCOUNT_STATUS_ENABLED = 1;

    /**
     * 缓存信息
     */
    public static final String MOBILE_CACHE = "MOBILE_CACHE_";
    public static final String PHONE_COUNT_CACHE = "PHONE_COUNT_CACHE_";
    public static final String IP_NUMBER_CACHE = "IP_NUMBER_CACHE_";
    public static final String PHONE_IP_NUMBER_CACHE = "PHONE_IP_NUMBER_CACHE_";
    public static final String BASIC_DATA_CACHE = "BASIC_DATA_CACHE_";

    /**
     * 返回结果
     */
    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_FAILED = "failed";

}
