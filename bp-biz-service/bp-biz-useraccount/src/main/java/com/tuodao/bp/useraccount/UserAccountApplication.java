package com.tuodao.bp.useraccount;

import com.tuodao.bp.cache.basic.AccessCache;
import com.tuodao.bp.cache.basic.AccountBankCache;
import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.result.interceptor.MqInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.tuodao.bp.useraccount.persistence"})
@Import({AccessCache.class, MqInterceptor.class, UserAccountCache.class, ScoreTaskCache.class, BankCache.class, AccountBankCache.class})
@EnableScheduling
@EnableAsync
@ComponentScan(basePackages = {"com.tuodao.bp.useraccount"})
public class UserAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAccountApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public AsyncRestTemplate asyncRestTemplate() {
        return new AsyncRestTemplate();
    }

}
