package com.tuodao.bp.operation;

import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.result.interceptor.MqInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages= {"com.tuodao.bp.operation.persistence"})
@EnableScheduling
@EnableAsync
@Import({UserAccountCache.class, ScoreTaskCache.class, MqInterceptor.class})
@ComponentScan(basePackages = {"com.tuodao.bp.operation","com.tuodao.bp.task.sdk"})
public class OperationApplication {
	public static void main(String[] args) {
		SpringApplication.run(OperationApplication.class, args);
	}
}
