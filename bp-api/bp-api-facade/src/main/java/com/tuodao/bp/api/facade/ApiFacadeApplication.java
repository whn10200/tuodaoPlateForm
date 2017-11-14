package com.tuodao.bp.api.facade;

import com.tuodao.bp.cache.basic.ConfigDictionaryCache;
import com.tuodao.bp.cache.basic.MsgTempCache;
import com.tuodao.bp.cache.basic.SmsCodeCache;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.cache.basic.traningcenter.SessionCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients(basePackages= {"com.tuodao.bp.api.facade.client"})
@Import({SmsCodeCache.class,UserAccountCache.class, MsgTempCache.class, ReturnsCache.class, BankCache.class, ConfigDictionaryCache.class, SessionCache.class, ProjectInfoCache.class})
@EnableAsync
@EnableCircuitBreaker
public class ApiFacadeApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ApiFacadeApplication.class, args);
	}


}
