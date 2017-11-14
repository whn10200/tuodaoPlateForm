package com.tuodao.bp.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 短信配置信息
 * @author: mif
 * @date: 2017/8/23
 * @time: 14:34
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@ConfigurationProperties(prefix = "configs.sms")
public class SmsConfig {
    /**
     * 开关
     */
    private Boolean enable;

    /**
     * 秒数间隔；60秒内只能发送一次
     */
    private Integer secondsInterval;

    /**
     * 手机条数限制:单个号码一天内发送短信条数
     */
    private Integer topNumberLimit;

    /**
     * IP限制：单个IP一天内发送短息条数
     */
    private Integer topIpLimit;

    /**
     * 手机号码绑定IP限制：单个手机号在1天内最多可绑定6个IP发送短信
     */
    private Integer topMobileIpLimit;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getSecondsInterval() {
        return secondsInterval;
    }

    public void setSecondsInterval(Integer secondsInterval) {
        this.secondsInterval = secondsInterval;
    }

    public Integer getTopNumberLimit() {
        return topNumberLimit;
    }

    public void setTopNumberLimit(Integer topNumberLimit) {
        this.topNumberLimit = topNumberLimit;
    }

    public Integer getTopIpLimit() {
        return topIpLimit;
    }

    public void setTopIpLimit(Integer topIpLimit) {
        this.topIpLimit = topIpLimit;
    }

    public Integer getTopMobileIpLimit() {
        return topMobileIpLimit;
    }

    public void setTopMobileIpLimit(Integer topMobileIpLimit) {
        this.topMobileIpLimit = topMobileIpLimit;
    }
}
