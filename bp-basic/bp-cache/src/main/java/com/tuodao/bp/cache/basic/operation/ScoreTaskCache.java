package com.tuodao.bp.cache.basic.operation;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Description: 积分任务缓存
 * User: zkai
 * Date: 2017/9/26
 * Time: 14:16
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class ScoreTaskCache {
    private static Logger log = LoggerFactory.getLogger(ScoreTaskCache.class);

    /**
     * 更新ScoreTask缓存
     *
     * @param opScoreTaskCacheInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.OP_SCORE_TASK, key = "T(com.tuodao.bp.cache.constant.RedisConstans).OP_SCORE_TASK +'_'+#opScoreTaskCacheInfo.id")
    public OpScoreTaskCacheInfo putScoreTask(OpScoreTaskCacheInfo opScoreTaskCacheInfo) {
        return opScoreTaskCacheInfo;
    }

    /**
     * 获取ScoreTask缓存
     * @param taskId 任务编号
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.OP_SCORE_TASK, key = "T(com.tuodao.bp.cache.constant.RedisConstans).OP_SCORE_TASK +'_'+#p0")
    public OpScoreTaskCacheInfo getScoreTaskInfo(long taskId) {
        return null;
    }
}
