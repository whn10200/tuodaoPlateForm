package com.tuodao.bp.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.operation.output.UserLabelTaskStatusOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserLabelMapper;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserOperationDataMapper;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpLabelTaskMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpUserLabel;
import com.tuodao.bp.operation.persistence.model.basic.OpUserLabelExample;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationData;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationDataExample;
import com.tuodao.bp.operation.service.IUserLabelService;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 服务--用户标签接口实现类
 * User: zkai
 * Date: 2017/9/30
 * Time: 17:16
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class UserLabelServiceImpl implements IUserLabelService {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserLabelServiceImpl.class);

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    @Autowired
    private BizOpLabelTaskMapper bizOpLabelTaskMapper;

    @Autowired
    private OpUserLabelMapper opUserLabelMapper;


    @Autowired
    private UserAccountCache userAccountCache;

    /**
     * 定时修改用户标签信息
     */
    @Override
    public void timeClockUpdateLable() {
        List<OpUserOperationData> opUserOperationDatas = opUserOperationDataMapper.selectByExample(new OpUserOperationDataExample());
        if(opUserOperationDatas != null && opUserOperationDatas.size()>0){
            for (OpUserOperationData opUserOperationData: opUserOperationDatas) {
                String label30 = null; // 30天标签
                String label60 = null; // 60天标签
                String label90 = null; // 90天标签
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
                    long l=new Date().getTime()-registerDate.getTime();
                    String days = String.valueOf(l/(24*60*60*1000));
                    switch (days){
                        case "31":
                            logger.info("用户已注册31天，记录用户30天的用户标签");
                            label30 = calculateUserLabelTask(userAccountInfo.getUserId());
                            break;
                        case "61":
                            logger.info("用户已注册61天，记录用户60天的用户标签");
                            label60 = calculateUserLabelTask(userAccountInfo.getUserId());
                            break;
                        case "91":
                            logger.info("用户已注册91天，记录用户90天的用户标签");
                            label90 = calculateUserLabelTask(userAccountInfo.getUserId());
                            break;
                    }
                    updateOpUserLabel(userAccountInfo.getUserId(),label30,label60,label90);
                }catch (Exception e){
                    logger.error("定时修改用户userId={}标签信息异常e={},",opUserOperationData.getUserId(),e);
                }
            }
        }
    }

    /**
     * 计算用户标签
     */
    private String calculateUserLabelTask(String userId){
        List<UserLabelTaskStatusOutput> userLabelTaskStatusOutputs = bizOpLabelTaskMapper.userLabelTaskFinishStatus(userId);
        if(userLabelTaskStatusOutputs == null || userLabelTaskStatusOutputs.size() == 0){
            logger.info("运营中心标签任务表无数据");
            throw new BizFeignException(OperationRespExceptionConstant.DATA_NOT_EXIT);
        }
        Map<Integer,String> labelTaskMap = new HashMap<Integer,String>();
        for (UserLabelTaskStatusOutput userLabelTaskStatusOutput : userLabelTaskStatusOutputs) {
            if(OperationBizConstant.TASK_IS_COMPLETE_YES.equals(userLabelTaskStatusOutput.getIsComplete())){
                labelTaskMap.put(userLabelTaskStatusOutput.getLabelTaskId(),userLabelTaskStatusOutput.getLabelTaskName());
            }
        }
        if(labelTaskMap.isEmpty()){
            logger.info("用户userId={},未完成任何任务,为{}类客户",userId,OperationBizConstant.USER_LABEL_C_3);
            return OperationBizConstant.USER_LABEL_C_3;
        }
        if(labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_1)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_2)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_3_1)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_3_2)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_4)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_6)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_7)
                && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_8)){
            logger.info("用户userId={},完成所有任务,为{}类客户",userId,OperationBizConstant.USER_LABEL_A_1);
            return OperationBizConstant.USER_LABEL_A_1;
        }
        if (!labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_2)){
            // 未完成首投
            if(labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_1)){
                logger.info("用户userId={},未完成任务2(首投任务)，但完成了任务1(认证任务),为{}类客户",userId,OperationBizConstant.USER_LABEL_C_1);
                return OperationBizConstant.USER_LABEL_C_1;
            }
            if((labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_3_1)
                    && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_3_2))
                    || labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_6)
                    || labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_7)){
                logger.info("用户userId={},未完成任务2(首投任务)，且未完成任务1(认证任务)，但完成了任务3,任务6,任务7中的至少一个任务,为{}类客户",userId,OperationBizConstant.USER_LABEL_C_2);
                return OperationBizConstant.USER_LABEL_C_2;
            }
            return OperationBizConstant.USER_LABEL_C_2;
        }else {
            // 用户完成首投
            if(labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_4)
                    && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_8)){
                // 用户完同时成任务2,任务4,任务8
                logger.info("用户userId={},完成任务2(首投任务),完成任务4和任务8,为{}类客户",userId,OperationBizConstant.USER_LABEL_A_2);
                return OperationBizConstant.USER_LABEL_A_2;
            }else if (labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_4)
                    || labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_8)){
                // 用户完成任务4和任务8其中一项
                //todo 获取30天内的投资金额
                BigDecimal investmentAmount = new BigDecimal(1000000);
                if(investmentAmount == null ){
                    investmentAmount = new BigDecimal(0);
                }
                if( investmentAmount.compareTo(new BigDecimal(100000)) == -1){
                    logger.info("用户userId={},完成任务2(首投任务),完成任务4和任务8至少1项,且30天内投资金额<10万，为{}类客户",userId,OperationBizConstant.USER_LABEL_B_1);
                    return  OperationBizConstant.USER_LABEL_B_1;
                }else {
                    logger.info("用户userId={},完成任务2(首投任务),完成任务4和任务8至少1项,且30天内投资金额>=10万，为{}类客户",userId,OperationBizConstant.USER_LABEL_A_3);
                    return  OperationBizConstant.USER_LABEL_A_3;
                }
            }else {
                // 用户未完成任务4和任务8
                if((labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_3_1)
                            && labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_3_2))
                        || labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_6)
                        || labelTaskMap.containsKey(OperationBizConstant.USER_LABEL_TASK_7)
                        ){
                    logger.info("用户userId={},完成任务2(首投任务),未完成任务4和任务8,但完成了任务3,任务6,任务7中的至少一个任务，为{}类客户",userId,OperationBizConstant.USER_LABEL_B_2);
                    return OperationBizConstant.USER_LABEL_B_2;
                }else {
                    logger.info("用户userId={},完成任务2(首投任务),为{}类客户",userId,OperationBizConstant.USER_LABEL_B_3);
                    return OperationBizConstant.USER_LABEL_B_3;
                }
            }
        }
    }

    /**
     * 修改用户标签
     */
    private void updateOpUserLabel(String userId,String label30,String label60,String label90){
        if(StringUtils.isBlank(label30) && StringUtils.isBlank(label60) && StringUtils.isBlank(label90) ){
            return;
        }
        OpUserLabel opUserLabelFind = getOpuserLabel(userId, PublicConstant.DEL_NO);
        if(opUserLabelFind == null){
            // 用户标签不存在,插入数据
            OpUserLabel opUserLabelInsert = new OpUserLabel();
            opUserLabelInsert.setUserId(userId);
            opUserLabelInsert.setUserLabel30(label30);
            opUserLabelInsert.setUserLabel60(label60);
            opUserLabelInsert.setUserLabel90(label90);
            int insertResult = opUserLabelMapper.insertSelective(opUserLabelInsert);
            if(insertResult != 1){
                logger.error("用户标签信息插入失败OpUserLabel={}", JSON.toJSONString(opUserLabelInsert));
                throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
            }
        }else {
            OpUserLabel opUserLabelUpdate = new OpUserLabel();
            opUserLabelUpdate.setId(opUserLabelFind.getId());
            opUserLabelUpdate.setUserLabel30(label30);
            opUserLabelUpdate.setUserLabel60(label60);
            opUserLabelUpdate.setUserLabel90(label90);
            // 修改用户标签
            int updateResult = opUserLabelMapper.updateByPrimaryKeySelective(opUserLabelUpdate);
            if(updateResult != 1){
                logger.error("用户标签信息修改失败OpUserLabel={}", JSON.toJSONString(opUserLabelUpdate));
                throw new BizFeignException(OperationRespExceptionConstant.UPDATE_ERROR);
            }
        }
    }

    /**
     * 根据用户编号和标签状态，查找用户标签
     * @param userId 用户编号
     * @param isDel 用户标签状态
     * @return
     */
    private OpUserLabel getOpuserLabel(String userId,Integer isDel){
        OpUserLabelExample opUserLabelExample = new OpUserLabelExample();
        OpUserLabelExample.Criteria criteria = opUserLabelExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        if(isDel != null){
            criteria.andIsDelEqualTo(isDel);
        }
        List<OpUserLabel> opUserLabels = opUserLabelMapper.selectByExample(opUserLabelExample);
        if(opUserLabels == null || opUserLabels.size() == 0){
            return null;
        }
        if(opUserLabels.size()>1){
            logger.error("用户userId={}的用户标签不唯一",userId);
            throw new BizFeignException(OperationRespExceptionConstant.DATA_NOT_ONLY);
        }
        return opUserLabels.get(0);
    }
}
