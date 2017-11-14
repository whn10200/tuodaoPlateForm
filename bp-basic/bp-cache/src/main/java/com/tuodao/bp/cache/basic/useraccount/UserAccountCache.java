package com.tuodao.bp.cache.basic.useraccount;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description: 用户账户缓存信息
 * @author: mif
 * @date: 2017/9/5
 * @time: 16:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class UserAccountCache {
    private static Logger logger = LoggerFactory.getLogger(UserAccountCache.class);


    /**
     * 读取缓存
     *
     * @param userId 用户编号
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_USER_ACCOUNT_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_USER_ACCOUNT_INFO +#p0")
    public UserAccountInfo getUserAccoutInfo(String userId) {
        logger.info("select user account info from persistence,userId={}", userId);
        return null;
    }

    /**
     * 缓存中删除
     *
     * @param userId 用户编号
     */
    @CacheEvict(cacheNames = RedisConstans.CACHE_NAME_USER_ACCOUNT_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_USER_ACCOUNT_INFO +#p0")
    public void deleteUserAccountInfo(String userId) {
        logger.info("delete user account info from cache,userId={} ", userId);
    }

    /**
     * 存入缓存
     *
     * @param userAccountInfo 用户账户信息
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_USER_ACCOUNT_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_USER_ACCOUNT_INFO +#p0.userId")
    public UserAccountInfo putCache(UserAccountInfo userAccountInfo) {
        return userAccountInfo;
    }
}
