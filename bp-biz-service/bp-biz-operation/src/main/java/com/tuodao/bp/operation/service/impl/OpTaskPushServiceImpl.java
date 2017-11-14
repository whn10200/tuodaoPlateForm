package com.tuodao.bp.operation.service.impl;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpTaskPushInfoMapper;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserOperationDataMapper;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpScoreTaskMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpTaskPushInfo;
import com.tuodao.bp.operation.persistence.model.basic.OpTaskPushInfoExample;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationData;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationDataExample;
import com.tuodao.bp.operation.service.IOpTaskPushService;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 服务-新人任务消息推送service
 * User: zkai
 * Date: 2017/10/10
 * Time: 16:56
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class OpTaskPushServiceImpl implements IOpTaskPushService {
    private static Logger logger = LoggerFactory.getLogger(OpTaskPushServiceImpl.class);

    @Autowired
    private UserAccountCache userAccountCache;

    @Autowired
    private BizOpScoreTaskMapper bizOpScoreTaskMapper;

    @Autowired
    private OpTaskPushInfoMapper opTaskPushInfoMapper;

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    /**
     * 新人任务定时推送
     */
    @Override
    public void timedPush() {
        // 新人任务消息推送基础信息列表
        List<OpTaskPushInfo> opTaskPushInfos = opTaskPushInfoMapper.selectByExample(new OpTaskPushInfoExample());
        if (opTaskPushInfos == null || opTaskPushInfos.size() == 0) {
            logger.info("新人任务消息推送定时不存在");
            return;
        }

        // 获取需要推送的用户信息
        List<OpUserOperationData> opUserOperationDatas = opUserOperationDataMapper.selectByExample(new OpUserOperationDataExample());
        if (opUserOperationDatas != null && opUserOperationDatas.size() > 0) {
            for (OpUserOperationData opUserOperationData : opUserOperationDatas) {
                UserAccountInfo userAccountInfo = new UserAccountInfo();
                Date registerDate = null;
                try {
                    userAccountInfo = userAccountCache.getUserAccoutInfo(opUserOperationData.getUserId());
                    if (userAccountInfo != null && userAccountInfo.getRegisterDate() !=null ) {
                        registerDate = userAccountInfo.getRegisterDate();
                    }else {
                        logger.info("用户userId={}缓存中无注册时间", opUserOperationData.getUserId());
                        continue;
                    }
                    long l = new Date().getTime() - registerDate.getTime();
                    long days =  l / (24 * 60 * 60 * 1000);
                    logger.info("用户userId={}已注册{}天", userAccountInfo.getUserId(), days);
                    if(days>30){
                        logger.info("用户userId={}已不在是新手,不在推送新手任务", userAccountInfo.getUserId());
                        continue;
                    }
                    for (OpTaskPushInfo opTaskPushInfo : opTaskPushInfos) {
                        if (Integer.valueOf(String.valueOf(days)).equals(opTaskPushInfo.getTriggerTime())) {
                            doPush(opTaskPushInfo,userAccountInfo);
                        }
                    }

                } catch (Exception e) {
                    logger.error("新人任务定时推送异常e={},", e);
                }
            }
        }
    }

    /**
     * 时间条件满足,触发推送
     * @param opTaskPushInfo
     * @param userAccountInfo
     */
    private void doPush(OpTaskPushInfo opTaskPushInfo, UserAccountInfo userAccountInfo) {
        UserTaskListInput userTaskListInput = new UserTaskListInput();
        userTaskListInput.setUserId(userAccountInfo.getUserId());
        userTaskListInput.setType(OperationBizConstant.SCORE_TASK_TYPE_NEWBIE);
        List<UserTaskListOutput> userTaskListOutputs = bizOpScoreTaskMapper.findUserTask(userTaskListInput);
        if (userTaskListOutputs == null || userTaskListOutputs.size() == 0) {
            logger.info("用户不存在新手任务");
            return;
        }

        /**
         * 获取用户新手任务完成情况
         */
        // 用户已完成的新手任务任务map
        Map<Integer, String> completedTaskMap = new HashMap<Integer, String>();
        // 用户未完成的新手任务任务map
        Map<Integer, String> inCompletedTaskMap = new HashMap<Integer, String>();
        for (UserTaskListOutput output : userTaskListOutputs) {
            if (OperationBizConstant.TASK_IS_COMPLETE_YES.equals(output.getIsComplete())) {
                // 已完成任务
                completedTaskMap.put(output.getTaskId(), output.getTaskName());
            } else {
                // 未完成
                inCompletedTaskMap.put(output.getTaskId(), output.getTaskName());
            }
        }

        // 消息推送条件要求的未完成的任务
        String conditionInCompletedTask = opTaskPushInfo.getIncompletedTasks();
        // 消息推送条件要求的已完成任务
        String conditionCompletedTask = opTaskPushInfo.getCompletedTasks();

        if (conditionInCompletedTask == null){
            return;
        }

        /**
         * 未完成任务是否通过
         */
        // 未完成任务条件
        String inCompletedTaskRule =  getJudgeRule(conditionInCompletedTask,"inCompletedTaskMap");
        JexlEngine jexlInCompleted = new JexlEngine();
        Expression expressInCompleted = jexlInCompleted.createExpression(inCompletedTaskRule);
        JexlContext jcInCompleted = new MapContext();
        jcInCompleted.set("inCompletedTaskMap",inCompletedTaskMap);
        Boolean inCompletedTaskRuleIsPass = (Boolean) expressInCompleted.evaluate(jcInCompleted);
        if(conditionCompletedTask == null ){
            if(inCompletedTaskRuleIsPass){
                // 如果已完成的推送触发条件为空,未完成的任务触发条件为true直接发送
                logger.info("用户userId={}触发了条件= {}; 的通知",userAccountInfo.getUserId(),opTaskPushInfo.getRemark());
//                logger.info("发送短信内容"+opTaskPushInfo.getSmsContent());
//                logger.info("消息推送内容"+opTaskPushInfo.getPushContnet());
                //todo 发送邮件，发送通知
                return;
            }else {
                return;
            }
        }

        /**
         * 已完成条件是否通过
         */
        // 已完成任务条件
        String completedTaskRule = getJudgeRule(conditionCompletedTask,"completedTaskMap");
        JexlEngine jexlCompleted = new JexlEngine();
        Expression expressCompleted = jexlCompleted.createExpression(completedTaskRule);
        JexlContext jc = new MapContext();
        jc.set("completedTaskMap",completedTaskMap);
        Boolean completedTaskRuleIsPass = (Boolean) expressCompleted.evaluate(jc);

        if(completedTaskRuleIsPass && inCompletedTaskRuleIsPass){
            logger.info("用户userId={}触发了条件= {}; 的通知",userAccountInfo.getUserId(),opTaskPushInfo.getRemark());
//            logger.info("发送短信内容"+opTaskPushInfo.getSmsContent());
//            logger.info("消息推送内容"+opTaskPushInfo.getPushContnet());
            //todo 发送邮件，发送通知
        }

    }

    /**
     * 将数字替换为我们需要用到的字符串
     * 例如：4#&&8# 转换为 labelTaskMap.containsKey(4) && labelTaskMap.containsKey(8)
     *
     * @param originalString 原始数据（数据库中获取的字符串：例如 4&&7）
     * @param value  对应的map
     * @return
     */
    private String getJudgeRule(String originalString,String value) {
        String regEx = "(\\d+)";
        Pattern p = Pattern.compile(regEx);
        Matcher matcher = p.matcher(originalString);
        String result = originalString;
        while (matcher.find()) {
//            System.out.println(matcher.group());
            result = result.replaceAll(matcher.group()+"#",value+".containsKey(" + matcher.group() + ")");
        }
        return result;
    }

//    /**
//     *
//     * @param expression 表达式
//     * @return
//     */
//    private boolean ruleIsPass(String expression){
//        JexlEngine jexl = new JexlEngine();
//        Expression e = jexl.createExpression(expression);
//        JexlContext jc = new MapContext();
//        jc.set("labelTaskMap",labelTaskMap);
//    }

//    public static void main(String[] args) {
//        Map<String, String> labelTaskMap = new HashMap<>();
//        labelTaskMap.put("4", "cs");
//        labelTaskMap.put("10", "cs");
//        labelTaskMap.put("5", "cs");
//
////        String expression = "if(labelTaskMap.containsKey(value) == true){return true}";
//        String expression = "labelTaskMap.containsKey(4) && labelTaskMap.containsKey(5)";
//
//        JexlEngine jexl = new JexlEngine();
//        Expression e = jexl.createExpression(expression);
//        JexlContext jc = new MapContext();
//        jc.set("labelTaskMap",labelTaskMap);
////        jc.set("value","4");
////        jc.set("value2","11");
//        System.out.println(e.evaluate(jc));
//    }

//    public static void main(String[] args) {
//        Map<Integer, String> labelTaskMap = new HashMap<>();
//        labelTaskMap.put(4, "cs");
//        labelTaskMap.put(10, "cs");
//        labelTaskMap.put(5, "cs");
//
////        String expression = "if(labelTaskMap.containsKey(value) == true){return true}";
//        String expression = "inCompletedTaskMap.containsKey(1)||inCompletedTaskMap.containsKey(2)||inCompletedTaskMap.containsKey(3)||inCompletedTaskMap.containsKey(4)||inCompletedTaskMap.containsKey(5)||inCompletedTaskMap.containsKey(6)||inCompletedTaskMap.containsKey(7)||inCompletedTaskMap.containsKey(8)||inCompletedTaskMap.containsKey(9)||inCompletedTaskMap.containsKey(10)";
////        String expression = "inCompletedTaskMap.containsKey(4)";
//
//        JexlEngine jexl = new JexlEngine();
//        Expression e = jexl.createExpression(expression);
//        JexlContext jc = new MapContext();
//        jc.set("inCompletedTaskMap",labelTaskMap);
////        jc.set("value","4");
////        jc.set("value2","11");
//        System.out.println(e.evaluate(jc));
//    }
//
//    public static void main(String[] args) {
//        String conditionCompletedTask = "1#||12#|| 12#|| 4#|| 5#|| 6#|| 7#|| 8#|| 9#|| 10#";
//        System.out.println(new OpTaskPushServiceImpl().getJudgeRule(conditionCompletedTask,"completedTaskMap"));
//    }
}
