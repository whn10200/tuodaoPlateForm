package com.tuodao.bp.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.OpScoreTaskDetailOutput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.facade.operation.input.UserScoreDetailListInput;
import com.tuodao.bp.model.facade.operation.output.OpUserScoreDetailListOutput;
import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.*;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpScoreTaskMapper;
import com.tuodao.bp.operation.persistence.model.basic.*;
import com.tuodao.bp.operation.service.IOpScoreTaskService;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description: 积分任务相关service
 * User: zkai
 * Date: 2017/9/20
 * Time: 15:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class OpScoreTaskServiceImpl implements IOpScoreTaskService {
    private static Logger logger = LoggerFactory.getLogger(OpScoreTaskServiceImpl.class);

    @Autowired
    private BizOpScoreTaskMapper bizOpScoreTaskMapper;

    @Autowired
    private OpUserTaskRelationMapper opUserTaskRelationMapper;

    @Autowired
    private OpUserScoreMapper opUserScoreMapper;

    @Autowired
    private OpUserScoreDetailMapper opUserScoreDetailMapper;

    @Autowired
    private OpScoreTaskMapper opScoreTaskMapper;

    @Autowired
    private OpUserSingHistoryMapper opUserSingHistoryMapper;

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    @Autowired
    private UserAccountCache userAccountCache;

    /**
     * 查找用户任务
     *
     * @param userScoreTaskListInput
     * @return
     */
    @Override
    public PageInfo<UserTaskListOutput> findUserTask(UserTaskListInput userScoreTaskListInput) {
        logger.info("查找用户任务input={}", JSON.toJSONString(userScoreTaskListInput));
        PageHelper.startPage(userScoreTaskListInput.getCurrentPage(), userScoreTaskListInput.getPageSize());
        List<UserTaskListOutput> userTaskListOutputs = bizOpScoreTaskMapper.findUserTask(userScoreTaskListInput);
        if(userTaskListOutputs == null || userTaskListOutputs.size() == 0){
            return new PageInfo<UserTaskListOutput>(userTaskListOutputs);
        }
        userTaskListOutputs.stream().forEach((UserTaskListOutput userTaskListOutput) ->{
            if(userTaskListOutput.getType() == OperationBizConstant.SCORE_TASK_TYPE_NEWBIE){
                // 新手任务，判断任务是否过期
                // 判断用户是否为新手
                UserAccountInfo userAccountInfo = userAccountCache.getUserAccoutInfo(userScoreTaskListInput.getUserId());
                if (userAccountInfo.getIsNewbie() == PublicConstant.IF_NO) {
                    // 不是新手,显示过期
                    userTaskListOutput.setIsOverdue(OperationBizConstant.TASK_IS_COMPLETE_YES);
                }
            }
        } );
        return new PageInfo<UserTaskListOutput>(userTaskListOutputs);
    }

    /**
     * 用户完成任务，执行操作
     * 1.插入"用户任务关系表"(op_user_task_relation)
     * 2.修改”用户积分表“(op_user_score)
     * 3.插入"用户积分明细表"(op_user_score_detail)
     * 4.修改 "用户运营数据表" (op_user_operation_data)
     *
     * @param input
     */
    @Override
    @Transactional
    public void userCompleteTask(OpScoreTaskMqInput input) {
        insertOpUserTaskRelation(input.getUserId(), input.getMobile(), input.getTaskId(),input.getScore());
        updateOpUserScore(input.getUserId(), input.getScore(), input.getMobile());
        insertOpUserScoreDetail(input.getUserId(), input.getMobile(), input.getSource(),
                input.getScore(), input.getRemark());
        // 修改 用户运营数据表中的积分信息
        updateuserOperationData(input.getUserId(), input.getMobile(), null, input.getScore());

    }

    /**
     * 获取 积分任务详细 信息
     *
     * @param input
     * @return
     */
    @Override
    public OpScoreTaskDetailOutput OpScoreTaskDetail(OpScoreTaskDetailInput input) {
        OpScoreTaskDetailOutput opScoreTaskDetailOutput = new OpScoreTaskDetailOutput();
        OpScoreTask opScoreTask = getOpScoreTask(input.getTaskId());
        BeanUtils.copyProperties(opScoreTask, opScoreTaskDetailOutput);
        return opScoreTaskDetailOutput;
    }

    /**
     * 获取 用户积分明细表
     *
     * @param input
     * @return
     */
    @Override
    public PageInfo<OpUserScoreDetailListOutput> getUserScoreDetailList(UserScoreDetailListInput input) {
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
        OpUserScoreDetailExample userScoreDetailExample = new OpUserScoreDetailExample();
        userScoreDetailExample.createCriteria().andUserIdEqualTo(input.getUserId());
        userScoreDetailExample.setOrderByClause(" gmt_create desc");
        List<OpUserScoreDetail> opUserScoreDetailList = opUserScoreDetailMapper.selectByExample(userScoreDetailExample);
        ImmutableList<OpUserScoreDetailListOutput> resultList = FluentIterable.<OpUserScoreDetail>from(opUserScoreDetailList).transform(new Function<OpUserScoreDetail, OpUserScoreDetailListOutput>() {
            @Override
            public OpUserScoreDetailListOutput apply(OpUserScoreDetail opUserScoreDetail) {
                OpUserScoreDetailListOutput out = new OpUserScoreDetailListOutput();
                BeanUtils.copyProperties(opUserScoreDetail, out);
                return out;
            }
        }).toList();

        PageInfo<OpUserScoreDetailListOutput> result = new PageInfo<OpUserScoreDetailListOutput>(resultList);
        Page<OpUserScoreDetail> page = (Page<OpUserScoreDetail>) opUserScoreDetailList;
        result.setTotal(page.getTotal());
        return result;
    }

    /**
     * 根据任务编号，获取积分任务信息
     */
    private OpScoreTask getOpScoreTask(Long taskId) {
        OpScoreTask opScoreTask = opScoreTaskMapper.selectByPrimaryKey(taskId);
        if (opScoreTask == null || opScoreTask.getId() == null) {
            logger.error("任务编号taskId={} 的积分任务不存在", taskId);
            // 数据不存在
            throw new BizFeignException(OperationRespExceptionConstant.DATA_NOT_EXIT);
        }
        if (opScoreTask.getIsDel() == 0) {
            logger.error("任务编号taskId={} 的积分已删除", taskId);
            // 积分任务被删除
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_TASK_IS_DEL);
        }
        return opScoreTask;
    }


    /**
     * 建立任务积分关系
     *
     * @param userId
     * @param userMobile
     * @param taskId
     */
    private void insertOpUserTaskRelation(String userId, String userMobile, Long taskId,int score) {
        // 根据任务编号，获取任务信息
        OpScoreTask opScoreTask = getOpScoreTask(taskId);
        int scoreType = opScoreTask.getType();
        // 如果任务类型为新手任务,查询 op_user_task_relation(用户任务关系表)
        switch (scoreType) {
            case OperationBizConstant.SCORE_TYPE_NEWBIE_TASK:
                // 判断用户是否为新手
                UserAccountInfo userAccountInfo = userAccountCache.getUserAccoutInfo(userId);
                if (userAccountInfo.getIsNewbie() == PublicConstant.IF_NO) {
                    // 不是新手
                    logger.info("用户userId={}不是新手任务", userId);
                    return;
                }

                // 如果任务类型为新手任务,插入 op_user_task_relation(用户任务关系表)
                insertTaskRelation(userId, taskId, userMobile);
                break;
            case OperationBizConstant.SCORE_TASK_DAILY_WORK:
                // 日常任务
                if (taskId == 11) {
                    // 用户签到
                    doSing(userId, userMobile,score);
                }
        }

    }

    /**
     * 插入用户任务关系表
     *
     * @param userId     用户编号
     * @param taskId     任务编号
     * @param userMobile 用户编号
     */
    private void insertTaskRelation(String userId, Long taskId, String userMobile) {
        OpUserTaskRelationExample opUserTaskRelationExample = new OpUserTaskRelationExample();
        opUserTaskRelationExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andTaskIdEqualTo(taskId);
        List<OpUserTaskRelation> opUserTaskRelations = opUserTaskRelationMapper.selectByExample(opUserTaskRelationExample);
        if (opUserTaskRelations.size() >= 1) {
            logger.info("新手任务已完成userId={},taskId={}", userId, taskId);
            return;
            // 此新手任务已完成
//            throw new BizFeignException(OperationRespExceptionConstant.NOVICE_TASK_BEEN_COMPLETED);
        }

        OpUserTaskRelation opUserTaskRelation = new OpUserTaskRelation();
        opUserTaskRelation.setUserId(userId);
        opUserTaskRelation.setUserMobile(userMobile);
        opUserTaskRelation.setTaskId(taskId);
        int result = opUserTaskRelationMapper.insertSelective(opUserTaskRelation);
        if (result != 1) {
            logger.error("用户任务关系表插入失败 opUserTaskRelation={}", JSON.toJSONString(opUserTaskRelation));
            // 数据添加异常
            throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
        }
    }

    /**
     * 用户签到
     * <p>
     * 1.判断用户是否签到 --- 查找 (op_user_sing_history)用户签到记录表，判断是否签到
     * 2.未签到，插入 (op_user_sing_history) 用户签到记录
     * 3.已签到，抛出异常
     * 4.修改 用户运营数据表
     */
    private void doSing(String userId, String mobile,int score) {
        // 判断用户今日是否签到
        OpUserSingHistoryExample opUserSingHistoryExample = new OpUserSingHistoryExample();
        opUserSingHistoryExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andSignTimeEqualTo(new Date());
        List<OpUserSingHistory> opUserSingHistories = opUserSingHistoryMapper.selectByExample(opUserSingHistoryExample);
        if (opUserSingHistories.size() >= 1) {
            logger.info("用户userId={} 已签到", userId);
            return;
            // 用户今日已签到
//            throw new BizFeignException(OperationRespExceptionConstant.USER_HAS_SIGNED_IN);
        }

        // 查找上次签到时间
        OpUserSingHistoryExample opUserSingLastExample = new OpUserSingHistoryExample();
        opUserSingLastExample.createCriteria()
                .andUserIdEqualTo(userId);
        opUserSingLastExample.setOrderByClause("sign_time DESC");
        List<OpUserSingHistory> opUserSingLasts = opUserSingHistoryMapper.selectByExample(opUserSingLastExample);

        // 是否连续签到
        String isContinuous = null;

        /** 插入用户签到记录 */
        OpUserSingHistory opUserSingHistory = new OpUserSingHistory();
        opUserSingHistory.setUserId(userId);
        opUserSingHistory.setSignTime(new Date());
        opUserSingHistory.setThisSignScore(score);
        if (opUserSingLasts.size() == 0) {
            // 首次签到
            opUserSingHistory.setLastSingDate(new Date());
            opUserSingHistory.setIsContinuous(OperationBizConstant.IS_CONTINUOUS_YES);
        } else {
            /** 判断是否连续签到 */
            // 上次签到时间
            String lastSingDate = TimeUtils.format(opUserSingLasts.get(0).getSignTime(), "yyyy-MM-dd");
            // 昨天
            String lastDate = TimeUtils.format(new Date(new Date().getTime() - 86400000L), "yyyy-MM-dd");
            if (lastDate.equals(lastSingDate)) {
                // 昨天 == 上次连续签到， 连续签到
                opUserSingHistory.setIsContinuous(OperationBizConstant.IS_CONTINUOUS_YES);
                isContinuous = OperationBizConstant.IS_CONTINUOUS_YES;
            } else {
                opUserSingHistory.setIsContinuous(OperationBizConstant.IS_CONTINUOUS_NO);
                isContinuous = OperationBizConstant.IS_CONTINUOUS_NO;
            }
            opUserSingHistory.setLastSingDate(opUserSingLasts.get(0).getSignTime());
        }
        int insertResult = opUserSingHistoryMapper.insertSelective(opUserSingHistory);
        if (insertResult != 1) {
            logger.error("用户签到记录插入失败", JSON.toJSONString(opUserSingHistory));
            // 数据添加异常
            throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
        }

        // 修改 用户运营数据表中是否连续签到
        updateuserOperationData(userId, mobile, isContinuous, 0);

    }


    /**
     * 修改 用户运营数据表
     *
     * @param userId       用户编号
     * @param mobile       电话号码
     * @param isContinuous 是否连续 如果参数为空，则不修改
     * @param addScore     添加的可用积分
     */
    private void updateuserOperationData(String userId, String mobile, String isContinuous, int addScore) {
        OpUserOperationData opUserOperationData = opUserOperationDataMapper.selectByPrimaryKey(userId);

        if (null == opUserOperationData) {
            // 插入 用户运营数据(op_user_operation_data)
            opUserOperationData = new OpUserOperationData();
            opUserOperationData.setUserId(userId);
            opUserOperationData.setUserMobile(mobile);
            opUserOperationData.setContinuousSignTimes(1);
            opUserOperationData.setCumulativeSignTimes(1);

            int insertResult = opUserOperationDataMapper.insertSelective(opUserOperationData);
            if (insertResult != 1) {
                logger.error("用户运营数据插入失败", JSON.toJSONString(opUserOperationData));
                // 数据添加异常
                throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
            }
            return;
        } else {
            if (!StringUtils.isEmpty(isContinuous)) {
                switch (isContinuous) {
                    case OperationBizConstant.IS_CONTINUOUS_YES:
                        opUserOperationData.setContinuousSignTimes(opUserOperationData.getContinuousSignTimes() + 1);
                        break;
                    case OperationBizConstant.IS_CONTINUOUS_NO:
                        opUserOperationData.setContinuousSignTimes(1);
                        break;
                }
            }
        }
        opUserOperationData.setUsableScores(opUserOperationData.getUsableScores() + addScore);
        int updateResult = opUserOperationDataMapper.updateByPrimaryKeySelective(opUserOperationData);
        if (updateResult != 1) {
            logger.error("用户运营数据表修改失败input={}", JSON.toJSONString(opUserOperationData));
            // 数据添加异常
            throw new BizFeignException(OperationRespExceptionConstant.UPDATE_ERROR);
        }
    }


    /**
     * 修改用户积分表
     *
     * @param userId     用户编号
     * @param score      积分
     * @param userMobile 用户编号
     */
    private void updateOpUserScore(String userId, int score, String userMobile) {
        OpUserScoreExample opUserScoreExample = new OpUserScoreExample();
        opUserScoreExample.createCriteria()
                .andIsDelEqualTo(PublicConstant.DEL_NO)
                .andUserIdEqualTo(userId);
        List<OpUserScore> opUserScores = opUserScoreMapper.selectByExample(opUserScoreExample);
        if (opUserScores == null || opUserScores.size() == 0) {
            // 插入用户积分
            OpUserScore opUserScore = new OpUserScore();
            opUserScore.setUserId(userId);
            opUserScore.setUserMobile(userMobile);
            opUserScore.setScoreTotal(score);
            opUserScore.setScoreUsed(0);
            opUserScore.setScoreInvalid(0);
            opUserScore.setScoreCurrent(score);
            opUserScore.setGmtCreator(userId);
            int result = opUserScoreMapper.insertSelective(opUserScore);
            if (result != 1) {
                logger.error("用户积分表插入失败 OpUserScore={}", JSON.toJSONString(opUserScore));
                // 数据添加异常
                throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
            }
            return;
        }
        if (opUserScores.size() > 1) {
            logger.error("用户编号userID={},积分信息不唯一", userId);
            // 记录条数不唯一
            throw new BizFeignException(OperationRespExceptionConstant.DATA_NOT_ONLY);
        }
        OpUserScore opUserScoreFind = opUserScores.get(0);

        // 修改用户积分
        OpUserScore opUserScoreUpdate = new OpUserScore();
        opUserScoreUpdate.setUserId(opUserScoreFind.getUserId());
        opUserScoreUpdate.setScoreTotal(opUserScoreFind.getScoreTotal() + score);
        opUserScoreUpdate.setScoreCurrent(opUserScoreFind.getScoreCurrent() + score);
        int resultUpdate = opUserScoreMapper.updateByPrimaryKeySelective(opUserScoreUpdate);
        if (resultUpdate != 1) {
            logger.error("用户积分表修改失败 OpUserScore={}", JSON.toJSONString(opUserScoreUpdate));
            // 数据修改异常
            throw new BizFeignException(OperationRespExceptionConstant.UPDATE_ERROR);
        }
    }

    /**
     * 用户积分明细表插入
     *
     * @param userId     用户ID
     * @param userMobile 用户手机号
     * @param source     来源
     * @param score      积分
     * @param remark     备注
     */
    private void insertOpUserScoreDetail(String userId, String userMobile,
                                         String source, int score, String remark) {
        OpUserScoreDetail opUserScoreDetail = new OpUserScoreDetail();
        opUserScoreDetail.setUserId(userId);
        opUserScoreDetail.setUserMobile(userMobile);
        opUserScoreDetail.setHanppenTime(new Date());
        opUserScoreDetail.setSource(source);
        opUserScoreDetail.setScore(score);
        opUserScoreDetail.setRemark(remark);
        opUserScoreDetail.setInvalidTime(getNextYearLastTime());
        opUserScoreDetail.setGmtCreator(userId);
        int resultInsert = opUserScoreDetailMapper.insertSelective(opUserScoreDetail);
        if (resultInsert != 1) {
            logger.error("用户积分明细表插入失败 OpUserScoreDetail={}", JSON.toJSONString(opUserScoreDetail));
            // 数据添加异常
            throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
        }

    }

    /**
     * 获取当前时间次年的12月31号24点
     * 2017年7月转换为，在2018年12月31日24点
     *
     * @return
     */
    private Date getNextYearLastTime() {
        Calendar calendar = Calendar.getInstance();
        int nowYear = calendar.get(Calendar.YEAR);
        int nextYear = nowYear + 1;
        String nextYearLastTime = nextYear + "-12-31 23:59:59";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(nextYearLastTime, pos);
    }

}
