package com.tuodao.bp.common.service;

import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.model.constant.push.PushConstants;
import com.tuodao.bp.result.exception.BizFeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:21
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class PushService {

    @Resource
    private IJPushService jPushService;

    @Resource
    private IGetuiPushService getuiPushService;

    @Value("${pushTool}")
    private String pushTool;

    /**
     * 推送消息
     * @param pushInput
     * @throws BizFeignException
     */
    public void pushMessage(PushInput pushInput) throws BizFeignException {
        if(pushTool == null){
            // 默认极光推送
            pushTool = PushConstants.PUSH_TOOL_JIGUANG;
        }
        switch (pushTool){
            case PushConstants.PUSH_TOOL_JIGUANG:
                // 极光消息推送
                jPushService.pushMessage(pushInput);
                break;
            case PushConstants.PUSH_TOOL_GETUI:
                // 个推消息推送
                getuiPushService.pushMessage(pushInput);
                break;
            default:
                jPushService.pushMessage(pushInput);
                break;
        }
    }
}
