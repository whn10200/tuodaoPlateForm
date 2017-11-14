package com.tuodao.bp.api.facade.client;

import com.tuodao.bp.model.business.common.input.EmailInput;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 公共服务FEIGN
 * @author: mif
 * @date: 2017/9/12
 * @time: 11:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BP-COMMON-MS")
public interface CommonClient {

    /**
     * 发送短信
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "common/sendSms", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public SmsOutput sendSms(SmsInput input);

    /**
     * 发送邮件
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "common/sendEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void sendEmail(EmailInput input);
}
