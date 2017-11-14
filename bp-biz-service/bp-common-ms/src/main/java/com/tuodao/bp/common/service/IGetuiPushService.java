package com.tuodao.bp.common.service;

import com.tuodao.bp.model.business.common.input.PushInput;

/**
 * Description: 个推消息推送
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IGetuiPushService {
    /**
     * 推送消息
     * @param input
     */
    void pushMessage(PushInput input);
}
