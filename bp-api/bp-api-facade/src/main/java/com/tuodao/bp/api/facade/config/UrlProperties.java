package com.tuodao.bp.api.facade.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuchengjie
 * @Date 2017/11/9 0009 15:50
 * @Introduction 配置系统的相关路径
 */
@Configuration
@ConfigurationProperties(prefix ="url")
public class UrlProperties {


    private String staticResource;

    private String host;

    public String getStaticResource() {
        return staticResource;
    }

    public void setStaticResource(String staticResource) {
        this.staticResource = staticResource;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
