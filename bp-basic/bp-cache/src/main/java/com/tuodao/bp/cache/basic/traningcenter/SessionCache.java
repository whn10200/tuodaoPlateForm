package com.tuodao.bp.cache.basic.traningcenter;

import com.tuodao.bp.cache.constant.RedisConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 14:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class SessionCache {

    private static final Logger logger = LoggerFactory.getLogger(SessionCache.class);

    /**
     * 存放投标结果缓存 默认30分钟过期
     * 更新ProjectTask缓存
     *
     * @param key
     * @param value
     * @return
     */
    @CachePut(cacheNames = RedisConstans.SESSION_ID, key = "T(com.tuodao.bp.cache.constant.RedisConstans).SESSION_ID+#p0",cacheManager = "cacheManagerTenMinute")
    public String putSession(String key,String value) {
        return value;
    }


    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.SESSION_ID, key = "T(com.tuodao.bp.cache.constant.RedisConstans).SESSION_ID +#p0")
    public String getSession(String key) {
        logger.info("select returnInfo from cache,keysss={}", key);
        return null;
    }


    /**
     * 缓存中删除
     *
     * @param key
     */
    @CacheEvict(cacheNames = RedisConstans.SESSION_ID, key = "T(com.tuodao.bp.cache.constant.RedisConstans).SESSION_ID +#p0")
    public void deleteSession(String key) {
        logger.info("delete  returnInfo from cache,key={} ", key);
    }
}
