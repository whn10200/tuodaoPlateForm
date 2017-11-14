package com.tuodao.bp.common.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


/**
 * @description: 短信相关配置
 * @author: mif
 * @date: 2017/8/15
 * @time: 11:13
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class EnableSmgConfiguration {

    @Resource
    private SmsConfig smsConfig;

    /**
     * 秒数间隔；60秒内只能发送一次
     *
     * @return
     */
    @Bean(name = "secondsCacheManager")
    public CacheManager secondsCacheManager(StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(stringRedisTemplate);
        // 使用自定义key名
        cacheManager.setUsePrefix(true);
        // 30分钟过期
        cacheManager.setDefaultExpiration(smsConfig.getSecondsInterval());
        return cacheManager;
    }

    /**
     * 保存一天的缓存
     *
     * @return
     */
    @Bean(name = "daysCacheManager")
    public CacheManager daysCacheManager(StringRedisTemplate stringRedisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(stringRedisTemplate);
        // 使用自定义key名
        cacheManager.setUsePrefix(true);
        cacheManager.setDefaultExpiration(60 * 60 * 24);
        return cacheManager;

//        GuavaCacheManager cacheManager = new GuavaCacheManager();
//        CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
//                .maximumSize(100)
//                .expireAfterWrite(1, TimeUnit.DAYS);
//        cacheManager.setCacheBuilder(cacheBuilder);
//        return cacheManager;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
