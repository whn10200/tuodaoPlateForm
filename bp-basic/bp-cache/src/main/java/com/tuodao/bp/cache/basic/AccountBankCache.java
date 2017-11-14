package com.tuodao.bp.cache.basic;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.db.model.basic.AccountBank;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 系统内部银行表缓存
 * User: zkai
 * Date: 2017/10/30
 * Time: 16:45
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class AccountBankCache {
    /**
     * 系统内部银行加入到redis中
     *
     * @param AccountBanks
     */
    @CachePut(cacheNames = RedisConstans.CACHE_ACCOUNT_BANK, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_ACCOUNT_BANK")
    public List<AccountBank> putAccountBank(List<AccountBank> AccountBanks) {
        return AccountBanks;
    }

    /**
     * 获取redis中系统内部银行
     *
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.CACHE_ACCOUNT_BANK, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_ACCOUNT_BANK")
    public List<AccountBank> getAccountBank() {
        return null;
    }
}
