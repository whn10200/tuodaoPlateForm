package com.tuodao.bp.useraccount.properties;

import com.google.common.base.MoreObjects;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * author hechuan
 * <p>
 * created on 2017/11/8
 * <p>
 * since V1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "server.tomcat")
public class TomcatProperties {
    private int minSpareThreads;
    private int maxThreads;
    private int maxConnections;
    private int acceptCount;
    private int connectionTimeout;

    public int getMinSpareThreads() {
        return minSpareThreads;
    }

    public void setMinSpareThreads(int minSpareThreads) {
        this.minSpareThreads = minSpareThreads;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public int getMaxConnections() {
        return maxConnections;
    }

    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }

    public int getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(int acceptCount) {
        this.acceptCount = acceptCount;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("minSpareThreads", minSpareThreads)
                .add("maxThreads", maxThreads)
                .add("maxConnections", maxConnections)
                .add("acceptCount", acceptCount)
                .add("connectionTimeout", connectionTimeout)
                .toString();
    }
}
