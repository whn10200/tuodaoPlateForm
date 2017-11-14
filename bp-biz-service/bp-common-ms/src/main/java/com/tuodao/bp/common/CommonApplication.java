package com.tuodao.bp.common;

import com.tuodao.bp.common.config.EnableSmgConfiguration;
import com.tuodao.bp.common.config.SmsConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * @description: 公共服务
 * @author: mif
 * @date: 2017/9/8
 * @time: 11:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootApplication
@EnableAsync
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.tuodao.bp.common.persistence"})
@EnableConfigurationProperties(SmsConfig.class)
@Import(EnableSmgConfiguration.class)
@ComponentScan(basePackages = {"com.tuodao.bp.common", "com.tuodao.bp.task.sdk"})
public class CommonApplication {

    public static void main(String[] args) {
        new SpringApplication(CommonApplication.class).run(args);
    }
}
