package com.tuodao.bp.cache.basic.traningcenter;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * 债转缓存
 * @author qingli.chen
 * @date 2017/10/24
 * @description
 */
public class CreditAssignmentCache {
    private static final Logger logger = LoggerFactory.getLogger(CreditAssignmentCache.class);


    /**
     * 解冻操作缓存
     * @param unFreezeInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.TRANSFER_UNFREEZE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TRANSFER_UNFREEZE+#unFreezeInfo.transferId")
    public UnFreezeInfo putTransferUnFreeze(UnFreezeInfo unFreezeInfo) {
        logger.info("put cache unFreezeInfo:{}", unFreezeInfo.toString());
        return unFreezeInfo;
    }

    /**
     * 读取解冻操作
     * @param transferId 债转id
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.TRANSFER_UNFREEZE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TRANSFER_UNFREEZE +#p0")
    public UnFreezeInfo getTransferUnFreeze(Integer transferId) {
        logger.info("load transfer unfreeze from cache, transferId:{}", transferId);
        return null;
    }


    /**
     * 删除解冻
     * @param transferId
     */
    @CacheEvict(cacheNames = RedisConstans.TRANSFER_UNFREEZE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TRANSFER_UNFREEZE +#p0")
    public void delTransferUnFreeze(Integer transferId) {
        logger.info("delete unFreezeInfo from cache,transferId:{} ", transferId);
    }


    /**
     * 债转缓存
     * @param unFreezeInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.TRANSFER_DEBT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TRANSFER_DEBT+#unFreezeInfo.transferId")
    public UnFreezeInfo putTransferDebt(UnFreezeInfo unFreezeInfo) {
        logger.info("put cache unFreezeInfo:{}", unFreezeInfo.toString());
        return unFreezeInfo;
    }

    /**
     * 获取债转缓存
     * @param unFreezeInfo
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.TRANSFER_DEBT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TRANSFER_DEBT +#p0")
    public UnFreezeInfo getTransferDebt(UnFreezeInfo unFreezeInfo) {
        logger.info("get cache unFreezeInfo:{}", unFreezeInfo.toString());
        return unFreezeInfo;
    }

    /**
     * 删除债转缓存
     * @param transferId
     */
    @CacheEvict(cacheNames = RedisConstans.TRANSFER_DEBT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TRANSFER_DEBT +#p0")
    public void delTransferDebt(Integer transferId) {
        logger.info("delete transfer debt from cache,transferId:{} ", transferId);
    }
}
