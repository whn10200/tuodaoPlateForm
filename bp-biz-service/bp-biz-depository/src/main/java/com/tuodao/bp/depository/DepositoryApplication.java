package com.tuodao.bp.depository;

import com.tuodao.bp.result.interceptor.MqInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.tuodao.bp.cache.basic.depository.DepositoryCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages= {"com.tuodao.bp.depository.db"})
@Import(value={DepositoryCache.class,UserAccountCache.class, MqInterceptor.class})
public class DepositoryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DepositoryApplication.class, args);
	}
	
}
