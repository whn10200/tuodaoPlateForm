package com.tuodao.bp.operation.cache;

import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpScoreTaskMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpScoreTask;
import com.tuodao.bp.operation.persistence.model.basic.OpScoreTaskExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description: 运营中心 启动初始化
 * User: zkai
 * Date: 2017/9/26
 * Time: 14:12
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class InitCache implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(InitCache.class);

    @Autowired
    private OpScoreTaskMapper opScoreTaskMapper;

    @Autowired
    private ScoreTaskCache scoreTaskCache;

    @Override
    public void run(String... strings) throws Exception {

        logger.info("op score task of query begin............");
        /**
         * 初始化redis中的 积分任务
         */
        OpScoreTaskExample opScoreTaskExample = new OpScoreTaskExample();
        opScoreTaskExample.createCriteria().andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpScoreTask> opScoreTasks = opScoreTaskMapper.selectByExample(opScoreTaskExample);
        if (CollectionUtils.isEmpty(opScoreTasks)) {
            logger.warn("op score task init over beacuase the basicDataList is Empty.....");
            return;
        }
        for (OpScoreTask opScoreTask: opScoreTasks) {
            OpScoreTaskCacheInfo opScoreTaskCacheInfo = new OpScoreTaskCacheInfo();
            BeanUtils.copyProperties(opScoreTask,opScoreTaskCacheInfo);
            scoreTaskCache.putScoreTask(opScoreTaskCacheInfo);
        }
        logger.info("op score task of query end............");
    }
}
