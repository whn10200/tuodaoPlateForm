package com.tuodao.bp.cache.basic;

import com.tuodao.bp.cache.constant.RedisConstans;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description: 数据字典缓存
 * @author: mif
 * @date: 2017/9/19
 * @time: 14:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ConfigDictionaryCache {

    /**
     * 存储数据字典缓存
     *
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_CONFIG_DICTIONARY_CACHE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_CONFIG_DICTIONARY_CACHE +#p0")
    public String putDictionaryName(String key, String dicName) {
        return dicName;
    }

    /**
     * 获取错误次数缓存
     *
     * @param key
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_CONFIG_DICTIONARY_CACHE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_CONFIG_DICTIONARY_CACHE +#p0")
    public String getDictionaryName(String key) {
        return null;
    }
}
