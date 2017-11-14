package com.tuodao.bp.common.util;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jiguang.common.resp.BaseResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import com.tuodao.bp.common.config.JpushProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * Description: 极光消息推送方法
 * User: zkai
 * Date: 2017/8/17
 * Time: 11:47
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class JiGuangPushUtil {

    private static Logger log = LoggerFactory.getLogger(JiGuangPushUtil.class);

    @Autowired
    private JpushProperties jpushProperties;

    private static JPushClient jpushClient = null;
    void initJpushClient() {
        if(jpushClient == null){
            jpushClient = new JPushClient(jpushProperties.getMasterSecret(), jpushProperties.getAppKey(), null, ClientConfig.getInstance());
        }

    }

    /**
     * 推送消息
     * @param payload
     * @return
     */
    public Callable<Boolean> sendPush(PushPayload payload){

        Callable<Boolean> sendPush = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                PushResult result = null;
                initJpushClient();
                try {
                    result = jpushClient.sendPush(payload);
                } catch (APIConnectionException e) {
                    log.error("连接极光服务器异常", e);
                    return false;
                } catch (APIRequestException e) {
                    log.error("Should review the error, and fix the request", e);
                    log.error("HTTP Status: " + e.getStatus());
                    log.error("Error Code: " + e.getErrorCode());
                    log.error("Error Message: " + e.getErrorMessage());
                    return false;
                }
                log.info("极光消息推送返回结果,result:{}",result);
                return true;
            }
        };
        return sendPush;
    }

    /**
     * 推送消息
     * @param payload
     * @return
     */
    public Callable<Boolean> sendPush(PushPayload payload,String pushTime){

        Callable<Boolean> sendPush = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                BaseResult result = null;
                initJpushClient();
                try {
                    if(StringUtils.isBlank(pushTime)){
                        result = jpushClient.sendPush(payload);
                    }else {
                        result = jpushClient.createSingleSchedule("定时推送",pushTime,payload);
                    }

                } catch (APIConnectionException e) {
                    log.error("连接极光服务器异常", e);
                    return false;
                } catch (APIRequestException e) {
                    log.error("Should review the error, and fix the request", e);
                    log.error("HTTP Status: " + e.getStatus());
                    log.error("Error Code: " + e.getErrorCode());
                    log.error("Error Message: " + e.getErrorMessage());
                    return false;
                }
                log.info("极光消息推送返回结果,result:{}",result);
                return true;
            }
        };
        return sendPush;
    }
}
