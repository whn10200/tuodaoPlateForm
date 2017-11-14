package com.tuodao.bp.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Description: 极光配置
 * User: zkai
 * Date: 2017/8/17
 * Time: 10:33
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
@ConfigurationProperties(prefix = "jpush")
public class JpushProperties implements Serializable {
    private static final long serialVersionUID = 2124382631593632075L;

    private String appKey;

    private String masterSecret;

    /**
     * 默认提示音
     */
    private String sound;

    /**
     * 极光推送设置离线消息保留时间（秒）
     */
    private long timeLive;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public long getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(long timeLive) {
        this.timeLive = timeLive;
    }
}
