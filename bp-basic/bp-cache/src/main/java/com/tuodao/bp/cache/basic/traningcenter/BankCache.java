package com.tuodao.bp.cache.basic.traningcenter;

import com.tuodao.bp.cache.constant.RedisConstans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 银行缓存
 * @author qingli.chen
 * @date 2017/10/12
 * @description
 */
@Component
public class BankCache {

    private static final Logger logger = LoggerFactory.getLogger(BankCache.class);



    /**
     * 网银
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_BANKS, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_ONLINE_BANKS")
    public List<OnlineBankInfo> putOnlineBank(List<OnlineBankInfo> list) {
        return list;
    }

    /**
     * 快捷充值
     * @return
     */
    @CachePut(cacheNames = RedisConstans.CACHE_NAME_FAST_BANKS, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_FAST_BANKS +#onlineBankInfo.bankId")
    public OnlineBankInfo putFastBank(OnlineBankInfo onlineBankInfo) {
        return onlineBankInfo;
    }

    /**
     *
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_BANKS, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_ONLINE_BANKS")
    public List<OnlineBankInfo> getOnlineBank() {
        return null;
    }

    /**
     *
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_NAME_FAST_BANKS, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_NAME_FAST_BANKS + #p0")
    public OnlineBankInfo getFastBank(String bankId) {
        return null;
    }
}
