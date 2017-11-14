package com.tuodao.bp.traningcenter;

import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.traningcenter.CreditAssignmentCache;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableAsync
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages= {"com.tuodao.bp.traningcenter.db"})
@Import({ProjectInfoCache.class, ReturnsCache.class, BankCache.class, ScoreTaskCache.class, UserAccountCache.class, CreditAssignmentCache.class})
@EnableFeignClients(basePackages= {"com.tuodao.bp.traningcenter.client"})
@ComponentScan(basePackages = {"com.tuodao.bp.traningcenter"})
public class TraningCenterApplication {
	public static void main(String[] args) {
		SpringApplication.run(TraningCenterApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
