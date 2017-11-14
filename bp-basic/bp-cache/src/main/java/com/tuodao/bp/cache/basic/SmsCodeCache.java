package com.tuodao.bp.cache.basic;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.AccessInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description: 短信验证码缓存
 * @author: mif
 * @date: 2017/9/12
 * @time: 14:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class SmsCodeCache {
    /**
     * 存储缓存
     *
     * @param smsCode
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_SMS_CODE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_SMS_CODE +#p0", cacheManager = "cacheManagerTenMinute")
    public String putSmsCode(String key, String smsCode) {
        return smsCode;
    }

    /**
     * 从缓存中获取
     *
     * @param key
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_SMS_CODE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_SMS_CODE +#p0")
    public String getSmsCode(String key) {
        return null;
    }

    /**
     * 删除
     *
     * @param key
     */
    @CacheEvict(cacheNames = RedisConstans.CACHE_NAME_SMS_CODE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_SMS_CODE +#p0")
    public void delSmsCode(String key) {

    }
}
