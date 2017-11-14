package com.tuodao.bp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;

/**
 * @description: 银行接口相关配置信息
 * @author: 王艳兵
 * @date: 2017/8/28
 * @time: 14:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BankConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankConfig.class);

    private static final String BANK_URI = SysConfig.getValue("bank_url");

    private static final String LOCAL_SERVER_URL = SysConfig.getValue("local_server_url");

    /**
     * 获取银行请求URL地址
     * @return
     */
    public static String getUri(String key){
        return BANK_URI + SysConfig.getValue(key);
    }


    /**
     * 获取银行配置密码
     * @return
     */
    public static String getKeyPwd(){
        try {
            byte[] bytePwd = new BASE64Decoder().decodeBuffer(SysConfig.getValue("pfx_pwd"));
            String pwd = new String(bytePwd,"UTF-8");
            return String.valueOf(Long.valueOf(pwd) + 8L);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("获取银行配置密码失败,{}",e.getMessage());
        }
        return null;
    }


    /**
     * 获取本地接口的请求的路径
     * @param key uri的key
     * @return ip:port/uri
     */
    public static String getLocalUri(String key){
        return LOCAL_SERVER_URL + SysConfig.getValue(key);
    }


}
