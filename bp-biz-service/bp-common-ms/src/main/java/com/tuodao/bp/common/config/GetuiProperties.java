package com.tuodao.bp.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Description: 个推配置
 * User: zkai
 * Date: 2017/8/17
 * Time: 10:33
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
@ConfigurationProperties(prefix = "getui")
public class GetuiProperties implements Serializable {
    private static final long serialVersionUID = 2124382631593632075L;

    private String appId;

    private String appSecret;

    private String appKey;

    private String masterSecret;

    private String host;

    private long timeLive;

    private int pushNetWorkType;

    private String autoBadge;

    private String sound;

    private int transmissionType;

    public String  getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public long getTimeLive() {
        return timeLive;
    }

    public void setTimeLive(long timeLive) {
        this.timeLive = timeLive;
    }

    public int getPushNetWorkType() {
        return pushNetWorkType;
    }

    public void setPushNetWorkType(int pushNetWorkType) {
        this.pushNetWorkType = pushNetWorkType;
    }

    public String getAutoBadge() {
        return autoBadge;
    }

    public void setAutoBadge(String autoBadge) {
        this.autoBadge = autoBadge;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public int getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(int transmissionType) {
        this.transmissionType = transmissionType;
    }
}
