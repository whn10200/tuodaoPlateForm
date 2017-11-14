package com.tuodao.bp.api.facade.service.common;

import com.tuodao.bp.model.facade.common.input.FacadeSmsInput;
import com.tuodao.bp.model.facade.common.input.FacadeValidateSmsCodeInput;

/**
 * @description: 聚合层公共服务接口
 * @author: mif
 * @date: 2017/9/12
 * @time: 18:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IFacadeCommonService {

    /**
     * 发送短信
     *
     * @param input
     * @param clientIP 请求IP
     */
    void sendSms(FacadeSmsInput input, String clientIP);

    /**
     * 验证短信验证码
     *
     * @param input
     */
    void validateSmsCode(FacadeValidateSmsCodeInput input);

}
