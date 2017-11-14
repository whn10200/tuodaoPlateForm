package com.tuodao.bp.cache.basic.depository;

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
 * @author: yck
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class DepositoryCache {
    private static Logger logger = LoggerFactory.getLogger(DepositoryCache.class);

    /**
     * 存入缓存
     *
     * @param orderNo
     */
    @CachePut(cacheNames = RedisConstans.DEPOSITORY_ORDER_NO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).DEPOSITORY_ORDER_NO +#p0", cacheManager = "cacheManagerTenMinute")
    public String putOrderNo(String orderNo) {
        return orderNo;
    }
    /**
     * 读取缓存
     *
     * @param orderNo
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.DEPOSITORY_ORDER_NO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).DEPOSITORY_ORDER_NO +#p0")
    public String getOrderNo(String orderNo) {
        logger.info("select depository order no. from persistence,orderNo={}", orderNo);
        return null;
    }

    /**
     * 缓存中删除
     *
     * @param orderNo 
     */
    @CacheEvict(cacheNames = RedisConstans.DEPOSITORY_ORDER_NO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).DEPOSITORY_ORDER_NO +#p0")
    public void deleteOrderNo(String orderNo) {
        logger.info("delete depository order no. from cache,orderNo={} ", orderNo);
    }

    
}
