package com.tuodao.bp.cache.basic;


import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.AccessInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * 用户accessId 缓存
 *
 * @author zkai [NO.0276]
 * @since 2017年09月08日 14:04
 */
@Component
public class AccessCache {
    private static Logger log = LoggerFactory.getLogger(AccessCache.class);

    /**
     * 更新Accee缓存
     *
     * @param accessInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_ACCESS_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_ACCESS_INFO +#accessInfo.accessId",
            cacheManager = "cacheManagerAccess")
    public AccessInfo putAccessInfo(AccessInfo accessInfo) {
        return accessInfo;
    }

    /**
     * 获取ACCESS缓存
     * @param accessId
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_ACCESS_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_ACCESS_INFO +#p0")
    public AccessInfo getAccessInfo(String accessId) {
        return null;
    }


    /**
     * 更新Access APP缓存
     *
     * @param accessInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_ACCESS_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_ACCESS_INFO_APP +#accessInfo.accessId",
            cacheManager = "cacheManagerAccessApp")
    public AccessInfo putAccessInfoApp(AccessInfo accessInfo) {
        return accessInfo;
    }

    /**
     * 获取ACCESS APP 缓存
     * @param accessId
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_ACCESS_INFO, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_ACCESS_INFO_APP +#p0",
            cacheManager = "cacheManagerAccessApp")
    public AccessInfo getAccessInfoApp(String accessId) {
        return null;
    }

}
