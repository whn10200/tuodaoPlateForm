package com.tuodao.bp.common.service.impl;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.tuodao.bp.common.config.JpushProperties;
import com.tuodao.bp.common.service.IJPushService;
import com.tuodao.bp.common.util.JiGuangPushUtil;
import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.model.constant.push.PushConstants;
import com.tuodao.bp.model.constant.push.PushResposeConstans;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description: 极光消息推送
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:35
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class JPushServiceImpl implements IJPushService {

    private static Logger log = LoggerFactory.getLogger(JPushServiceImpl.class);

    @Autowired
    private JpushProperties jpushProperties;


    @Autowired
    private JiGuangPushUtil jiGuangPushUtil;

    /**
     * guava retry
     * 结果返回false  重试:固定等待时长为 300 ms,最多尝试 3 次
     */
    static Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfExceptionOfType(RestClientException.class)
            .retryIfResult(Predicates.equalTo(false))
            .withWaitStrategy(WaitStrategies.fixedWait(300, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    @Override
    public void pushMessage(PushInput input) {

        // 推送别名列表
        String[] pushAlias = null;
        if(!StringUtils.isBlank(input.getPushAlias())){
            pushAlias = input.getPushAlias().split(PushConstants.SPILE_TYPE);
        }
        // 推送透传
        pushPassthrough(pushAlias,input.getPushContent(), String.valueOf(input.getPushObject()),input.getPushTime());
    }

    /**
     * 消息推送到所有平台（通知）
     * @param alias 别名列表
     * @param title 标题
     * @param notification 通知内容为
     * @param pushObject 推送对象
     * @param extras 添加内容
     * @param tags 组名称
     * @return
     */
    @Deprecated
    private void pushNotice(String[] alias, String title, String notification, String pushObject, Map<String, String> extras, String[] tags,String pushTime) {
        log.info("极光推送消息通知:alias 别名列表{},title 标题{},notification 通知内容{},pushObject 推送对象{},extras 添加内容{},组名称{}",JSON.toJSONString(alias),title,notification,pushObject, JSON.toJSONString(extras),JSON.toJSONString(tags));
        PushPayload payload = null;
        if(pushObject == null){
            // 默认推送到所有平台
            pushObject = PushConstants.PUSH_OJECT_ALL;
        }
        switch (pushObject){
            case PushConstants.PUSH_OJECT_ALL:
                // 推送到所有平台
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.all()) // 推送到安卓和ios
                        .setAudience(Audience.all()) //Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                        .setOptions(Options.newBuilder().setTimeToLive(jpushProperties.getTimeLive()).build())  //极光推送设置离线消息保留时间
                        .setNotification(Notification.newBuilder()
                                .setAlert(notification)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .addExtras(extras)
                                        .build())
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1)
                                        .setSound(jpushProperties.getSound())
                                        .addExtras(extras)
                                        .build())
                                .build())
                        .build();
                break;
            case PushConstants.PUSH_OJECT_ALIA:
                // 推送到对应别名的设备上
                if(alias.length ==0){
                    throw new BizFeignException(PushResposeConstans.PUSH_ALIA_NOT_NULL);
                }
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.all())
                        .setAudience(Audience.alias(alias))
                        .setOptions(Options.newBuilder().setTimeToLive(jpushProperties.getTimeLive()).build())
                        .setNotification(Notification.newBuilder()
                                .setAlert(notification)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .addExtras(extras)
                                        .build())
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1)
                                        .setSound(jpushProperties.getSound())
                                        .addExtras(extras).build())
                                .build())
                        .build();
                break;
            case PushConstants.PUSH_OJECT_ANDROID:
                // 推送给安卓
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.android())
                        .setAudience(Audience.all())
                        .setOptions(Options.newBuilder().setTimeToLive(jpushProperties.getTimeLive()).build())
                        .setNotification(Notification.newBuilder()
                                .setAlert(notification)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .addExtras(extras)
                                        .build())
                                .build())
                        .build();
                break;
            case PushConstants.PUSH_OJECT_IOS:
                // 推送给ios
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.ios())
                        .setAudience(Audience.all())
                        .setOptions(Options.newBuilder().setTimeToLive(jpushProperties.getTimeLive()).build())
                        .setNotification(Notification.newBuilder()
                                .setAlert(notification)
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1)
                                        .setSound(jpushProperties.getSound())
                                        .addExtras(extras).build())
                                .build())
                        .build();
                break;
            default:
                // 推送到所有平台
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.all()) // 推送到安卓和ios
                        .setAudience(Audience.all()) //Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                        .setOptions(Options.newBuilder().setTimeToLive(jpushProperties.getTimeLive()).build())  //极光推送设置离线消息保留时间
                        .setNotification(Notification.newBuilder()
                                .setAlert(notification)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .addExtras(extras)
                                        .build())
                                .addPlatformNotification(IosNotification.newBuilder()
                                        .incrBadge(1)
                                        .setSound(jpushProperties.getSound())
                                        .addExtras(extras)
                                        .build())
                                .build())
                        .build();
                break;
        }

        try {
            retryer.call(jiGuangPushUtil.sendPush(payload,pushTime));
        } catch (ExecutionException | RetryException e) {
            log.error("消息推送失败：e{}",e);
            throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
        }
    }


    /**
     * 消息推送到所有平台（透传）
     * @param alias 别名列表
     * @param notification 透传内容
     * @param pushObject 推送对象
     * @return
     */
    private void pushPassthrough(String[] alias, String notification, String pushObject,String pushTime) {
        log.info("极光推送透传通知:alias别名列表:{},notification透传内容:{},pushObject推送对象:{}" +
                        ",pushTime消息推送时间 如果为空，不为定时推送:{}",JSON.toJSONString(alias)
                ,notification,pushObject,pushTime);
        PushPayload payload = null;
        if(pushObject == null){
            // 默认推送到所有平台
            pushObject = PushConstants.PUSH_OJECT_ALL;
        }
        switch (pushObject){
            case PushConstants.PUSH_OJECT_ALL:
                // 推送到所有平台
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.all()) // 推送到安卓和ios
                        .setAudience(Audience.all()) //Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                        .setMessage(Message.newBuilder()
                                .setMsgContent(notification).build())
                        .build();
                break;
            case PushConstants.PUSH_OJECT_ALIA:
                // 推送到对应别名的设备上
                if(alias.length ==0){
                    throw new BizFeignException(PushResposeConstans.PUSH_ALIA_NOT_NULL);
                }
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.android_ios())
                        .setAudience(Audience.alias(alias))
                        .setMessage(Message.newBuilder()
                                .setMsgContent(notification).build())
                        .build();
                break;
            case PushConstants.PUSH_OJECT_ANDROID:
                // 推送给安卓
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.android())
                        .setAudience(Audience.all())
                        .setMessage(Message.newBuilder()
                                .setMsgContent(notification).build())
                        .build();
                break;
            case PushConstants.PUSH_OJECT_IOS:
                // 推送给ios
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.ios())
                        .setAudience(Audience.all())
                        .setMessage(Message.newBuilder()
                                .setMsgContent(notification).build())
                        .build();
                break;
            default:
                // 推送到所有平台
                payload = PushPayload.newBuilder()
                        .setPlatform(Platform.all()) // 推送到安卓和ios
                        .setAudience(Audience.all()) //Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                        .setMessage(Message.newBuilder()
                                .setMsgContent(notification).build())
                        .build();
                break;
        }
        try {
            retryer.call(jiGuangPushUtil.sendPush(payload,pushTime));
        } catch (ExecutionException | RetryException e) {
            log.error("消息推送失败：e{}",e);
            throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
        }

    }

}
