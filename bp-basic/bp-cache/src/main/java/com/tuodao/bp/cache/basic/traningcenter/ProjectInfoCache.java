package com.tuodao.bp.cache.basic.traningcenter;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.ProjectInfoCacheInfo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @description: 理财计划产品剩余金额缓存
 * @author: wuzf
 * @date: 2017/10/10
 * @time: 16:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ProjectInfoCache {

    private static Logger logger = LoggerFactory.getLogger(ProjectInfoCache.class);

    /**
     * 更新ProjectTask缓存
     *
     * @param projectInfoCacheInfo
     * @return
     */
    @CachePut(cacheNames = RedisConstans.TC_PROJECT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TC_PROJECT+#projectInfoCacheInfo.projectId")
    public ProjectInfoCacheInfo putProjectInfo(ProjectInfoCacheInfo projectInfoCacheInfo) {
        return projectInfoCacheInfo;
    }


    /**
     * 读取缓存
     *
     * @param projectId 理财计划id
     * @return
     */
    @Cacheable(cacheNames = RedisConstans.TC_PROJECT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TC_PROJECT +#p0")
    public ProjectInfoCacheInfo getProjectInfo(Integer projectId) {
        logger.info("select projectInfo from cache,projectid={}", projectId);
        return null;
    }


    /**
     * 缓存中删除
     *
     * @param projectId 理财计划id
     */
    @CacheEvict(cacheNames = RedisConstans.TC_PROJECT, key = "T(com.tuodao.bp.cache.constant.RedisConstans).TC_PROJECT +#p0")
    public void deleteProjectInfo(Integer projectId) {
        logger.info("delete projectInfo from cache,projectId={} ", projectId);
    }



}
