package com.tuodao.bp.task.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 定时任务中心
 * author hechuan
 * <p>
 * created on 2017/9/11
 * <p>
 * since V1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@MapperScan(value = {"com.tuodao.bp.task.server.dao.mapper"})
@ComponentScan(value = {"com.tuodao.bp.task.server"})
public class TaskServer {
    public static void main(String[] args) {
        SpringApplication.run(TaskServer.class,args);
    }
}
