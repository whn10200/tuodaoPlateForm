package com.tuodao.bp.product;

import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.result.interceptor.MqInterceptor;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = { "com.tuodao.bp.product.db" })
@EnableFeignClients(basePackages= {"com.tuodao.bp.product.client"})
@EnableScheduling
@Import({ MqInterceptor.class, UserAccountCache.class,ProjectInfoCache.class, })
public class ProductApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
