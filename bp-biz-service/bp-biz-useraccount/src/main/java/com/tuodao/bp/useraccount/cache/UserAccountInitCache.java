package com.tuodao.bp.useraccount.cache;

import com.tuodao.bp.useraccount.persistence.mapper.basic.BaseLevelInfoMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.BaseLevelInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.BaseLevelInfoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 运营中心 启动初始化
 * User: zkai
 * Date: 2017/9/26
 * Time: 14:12
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class UserAccountInitCache implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(UserAccountInitCache.class);

    @Autowired
    private BaseLevelInfoMapper baseLevelInfoMapper;

    @Autowired
    private BaseVipLevelCache baseVipLevelCache;

    @Override
    public void run(String... strings) throws Exception {

        logger.info("base level info of query begin............");
        /**
         * 初始化redis中的 vip等级
         */
        BaseLevelInfoExample example = new BaseLevelInfoExample();
        List<BaseLevelInfo> baseLevelInfos  = baseLevelInfoMapper.selectByExample(example);
        if(baseLevelInfos.size() == 0){
            logger.error("获取的vip基础信息表数据不存在");
        }
        baseVipLevelCache.putBaseLevelInfos(baseLevelInfos);
        logger.info("base level infoof query end............");
    }
}
