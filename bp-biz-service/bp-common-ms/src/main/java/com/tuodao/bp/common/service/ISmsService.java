package com.tuodao.bp.common.service;

import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;

/**
 * @description: 短信发送
 * @author: mif
 * @date: 2017/9/11
 * @time: 13:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface ISmsService {
    /**
     * 发送短信
     *
     * @param smsInput
     * @param requestIp 请求ip
     * @return
     */
    public SmsOutput sendSms(SmsInput smsInput, final String requestIp);
}
