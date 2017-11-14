package com.tuodao.bp.cache.basic.traningcenter;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.ReturnCacheInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Set;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
public class ReturnsCache {

    /**
     * 由于注解形式的缓存无法进行模糊查询,删除 需要手动注入
     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static Logger logger = LoggerFactory.getLogger(ReturnsCache.class);

    /**
     * 存放投标结果缓存 默认30分钟过期
     * 更新ProjectTask缓存
     *
     * @param returnCacheInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.TC_RESULT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TC_RESULT+#returnCacheInfo.key",cacheManager = "cacheManagerTenMinute")
    public ReturnCacheInfo putReturnInfo(ReturnCacheInfo returnCacheInfo) {
        return returnCacheInfo;
    }






    /**
     * 根据startWith删除缓存
     * @param keyStartWith
     */
    public void deleteDimKey(String keyStartWith){
        Set<String> keys = stringRedisTemplate.keys(keyStartWith + "*");
        stringRedisTemplate.delete(keys);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.TC_RESULT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TC_RESULT +#p0")
    public ReturnCacheInfo getReturnInfo(String key) {
        logger.info("select returnInfo from cache,keysss={}", key);
        return null;
    }


    /**
     * 缓存中删除
     *
     * @param key 理财计划id
     */
    @CacheEvict(cacheNames = RedisConstans.TC_RESULT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TC_RESULT +#p0")
    public void deleteReturnInfo(String key) {
        logger.info("delete  returnInfo from cache,key={} ", key);
    }


}
