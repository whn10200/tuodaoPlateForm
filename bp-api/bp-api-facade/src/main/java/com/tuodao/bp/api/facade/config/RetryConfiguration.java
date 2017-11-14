package com.tuodao.bp.api.facade.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.concurrent.TimeUnit;

/**
 * 重试相关配置
 * author hechuan
 * <p>
 * created on 2017/11/7
 * <p>
 * since V1.0.0
 */
@Configuration
public class RetryConfiguration {

    /**
     * 重试的相关配置
     * @param env
     * @return
     */
    @Bean
    @Primary
    public Retryer retryer(ConfigurableEnvironment env) {
        long period = env.getProperty("bp-api-facade.ribbon.periodTimeoutInMilliseconds", long.class, 100L);
        int maxPeriod = env.getProperty("bp-api-facade.ribbon.maxPeriodSeconds", int.class, 1);
        int retryTimes = env.getProperty("bp-api-facade.ribbon.retryTimes", int.class, 3);
        return new Retryer.Default(period, TimeUnit.SECONDS.toMillis(maxPeriod), retryTimes);
    }

    /**
     * 连接，读取超时相关配置
     * @param env
     * @return
     */
    @Bean
    Request.Options requestOptions(ConfigurableEnvironment env){
        int ribbonReadTimeout = env.getProperty("bp-api-facade.ribbon.ReadTimeout", int.class, 6000);
        int ribbonConnectionTimeout = env.getProperty("bp-api-facade.ribbon.ConnectTimeoutx", int.class, 1000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
