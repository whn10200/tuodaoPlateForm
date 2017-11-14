package com.tuodao.bp.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.uitls.AppConditions;
import com.github.rholder.retry.*;
import com.google.common.base.Predicates;
import com.tuodao.bp.common.config.GetuiProperties;
import com.tuodao.bp.common.service.IGetuiPushService;
import com.tuodao.bp.common.util.GetuiPushUtil;
import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.model.constant.push.PushConstants;
import com.tuodao.bp.model.constant.push.PushResposeConstans;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Description: 个推消息推送
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:35
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class GetuiPushServiceImpl implements IGetuiPushService {

    private static Logger log = LoggerFactory.getLogger(GetuiPushServiceImpl.class);

    @Autowired
    private GetuiProperties getuiProperties;

    @Autowired
    private GetuiPushUtil getuiPushUtil;

    @Value("${pushTool}")
    private String pushTool;


    /**
     * 初始化 AppMessage
     * @param template
     * @return
     */
    private AppMessage initAppMessage(ITemplate template){
        AppMessage appMessage = new AppMessage();
        appMessage.setData(template);
        appMessage.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        appMessage.setOfflineExpireTime(getuiProperties.getTimeLive());
        //推送给App的目标用户需要满足的条件
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(getuiProperties.getAppId());
        appMessage.setAppIdList(appIdList);
        return appMessage;
    }

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
        pushMessage(pushAlias,input.getPushContent(), String.valueOf(input.getPushObject()), input.getPushTime());
    }

    /**
     * 消息推送到所有平台（透传）
     * @param alias 别名列表
     * @param notification 透传内容
     * @param pushObject 推送对象
     * @param pushTime 消息推送时间
     * @return
     */
    private void pushMessage(String[] alias,String notification, String pushObject,String pushTime) {
        log.info("个推推送消息:alias 别名列表{},notification 透传内容{},pushObject 推送对象{}",
               JSON.toJSONString(alias),notification,pushObject);
        if(pushObject == null){
            // 默认推送到所有平台
            pushObject = PushConstants.PUSH_OJECT_ALL;
        }
        ITemplate template = null;
        // 透传
        template = getuiPushUtil.transmissionTemplate(notification,pushTime);

        AppMessage appMessage = initAppMessage(template);
        //推送给App的目标用户需要满足的条件
        AppConditions cdt = null;
        switch (pushObject){
            case PushConstants.PUSH_OJECT_ALL:
                // 推送到所有平台
                try {
                    retryer.call(getuiPushUtil.pushMessageToApp(appMessage));
                } catch (ExecutionException | RetryException e) {
                    log.error("消息推送异常:e{}",e);
                    throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
                }
                break;
            case PushConstants.PUSH_OJECT_ALIA:
                // 推送到对应别名的设备上
                if(alias.length ==0){
                    throw new BizFeignException(PushResposeConstans.PUSH_ALIA_NOT_NULL);
                }
                ListMessage listMessage = new ListMessage();
                listMessage.setData(template);
                // 设置消息离线，并设置离线时间
                listMessage.setOffline(true);
                // 离线有效时间，单位为毫秒，可选
                listMessage.setOfflineExpireTime(getuiProperties.getTimeLive());
                // 配置推送目标
                List targets = new ArrayList();
                for (int i = 0; i < alias.length; i++) {
                    Target target = new Target();
                    target.setAppId(getuiProperties.getAppId());
                    target.setAlias(alias[i]);
                    targets.add(target);
                }

                try {
                    retryer.call(getuiPushUtil.pushMessageToList(targets,listMessage));
                } catch (ExecutionException | RetryException e) {
                    log.error("消息推送失败：e{}",e);
                    throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
                }
                break;
            case PushConstants.PUSH_OJECT_ANDROID:
                // 推送给安卓
                cdt = new AppConditions();
                AppConditions conditionsAndroid = new AppConditions();
                //手机类型
                List<String> phoneTypes = new ArrayList<String>();
                phoneTypes.add("ANDROID");
                conditionsAndroid.addCondition(AppConditions.PHONE_TYPE, phoneTypes);
                appMessage.setConditions(cdt);
                try {
                    retryer.call(getuiPushUtil.pushMessageToApp(appMessage));
                } catch (ExecutionException | RetryException e) {
                    log.error("消息推送失败：e{}",e);
                    throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
                }
                break;
            case PushConstants.PUSH_OJECT_IOS:
                // 推送给ios
                cdt = new AppConditions();
                AppConditions conditionsIos = new AppConditions();
                //手机类型
                List<String> phoneTypesIos = new ArrayList<String>();
                phoneTypesIos.add("IOS");
                conditionsIos.addCondition(AppConditions.PHONE_TYPE, phoneTypesIos);
                appMessage.setConditions(cdt);
                try {
                    retryer.call(getuiPushUtil.pushMessageToApp(appMessage));
                } catch (ExecutionException | RetryException e) {
                    log.error("消息推送失败：e{}",e);
                    throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
                }
                break;
            default:
                // 推送到所有平台
                try {
                    retryer.call(getuiPushUtil.pushMessageToApp(appMessage));
                } catch (ExecutionException | RetryException e) {
                    log.error("消息推送失败：e{}",e);
                    throw new BizFeignException(PushResposeConstans.PUSH_ERROR);
                }
                break;
        }
    }

}
