package com.tuodao.bp.useraccount.cache;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.BaseLevelInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 用户等级基础信息表
 * User: zkai
 * Date: 2017/9/26
 * Time: 14:16
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class BaseVipLevelCache {
    private static Logger log = LoggerFactory.getLogger(BaseVipLevelCache.class);

    /**
     * 更新base_level_info缓存
     * @return
     */
    @CachePut(cacheNames = RedisConstans.BASE_VIP_LEVEL_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).BASE_VIP_LEVEL_INFO ")
    public List<BaseLevelInfo> putBaseLevelInfos(List<BaseLevelInfo> baseLevelInfos) {
        return baseLevelInfos;
    }

    /**
     * 获取base_level_info缓存
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.BASE_VIP_LEVEL_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).BASE_VIP_LEVEL_INFO")
    public List<BaseLevelInfo> getBaseLevelInfos() {
        return null;
    }
}
