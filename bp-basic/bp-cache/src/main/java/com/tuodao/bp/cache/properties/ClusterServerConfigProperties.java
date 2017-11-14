package com.tuodao.bp.cache.properties;

import org.redisson.config.ClusterServersConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * author hechuan
 * <p>
 * created on 2017/11/3
 * <p>
 * since V1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "dislock.redisson.clusterServersConfig")
public class ClusterServerConfigProperties extends ClusterServersConfig {
}
