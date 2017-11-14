package com.tuodao.bp.cache.basic;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.db.model.basic.ConfigPushTemp;
import com.tuodao.bp.db.model.basic.ConfigSmsTemp;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @description: 消息模板缓存
 * @author: mif
 * @date: 2017/9/30
 * @time: 16:32
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class MsgTempCache {

    /**
     * 存储短信模板
     *
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_SMS_TEMPLATE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_SMS_TEMPLATE +#p0.code")
    public ConfigSmsTemp putSmsTemp(ConfigSmsTemp configSmsTemp) {
        return configSmsTemp;
    }


    /**
     * 存储短信模板
     *
     * @param code 模版编码
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_SMS_TEMPLATE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_SMS_TEMPLATE +#p0")
    public ConfigSmsTemp getSmsTemp(String code) {
        return null;
    }

    /**
     * 存储推送模板
     *
     * @param configPushTemp 推送模版
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_PUSH_TEMPLATE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_PUSH_TEMPLATE +#p0.code")
    public ConfigPushTemp putPushTemp(ConfigPushTemp configPushTemp) {
        return configPushTemp;
    }


    /**
     * 存储推送模板
     *
     * @param code 模版编码
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_PUSH_TEMPLATE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_PUSH_TEMPLATE +#p0")
    public ConfigPushTemp getPushTemp(String code) {
        return null;
    }
}
