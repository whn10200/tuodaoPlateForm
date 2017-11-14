package com.tuodao.bp.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.model.business.operation.input.OpCooperationInput;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpCooperationMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpCooperation;
import com.tuodao.bp.operation.service.IOpCooperationService;
import com.tuodao.bp.result.exception.BizFeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 新增加盟管理service
 * User: yinping
 * Date: 2017/9/22
 * Time: 16:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class OpCooperationServiceImpl implements IOpCooperationService {
    private static Logger logger = LoggerFactory.getLogger(OpCooperationServiceImpl.class);

    @Autowired
    private OpCooperationMapper opCooperationMapper;

    /**
     * 新增加盟管理
     * @return
     */
    @Override
    public void addCooperationManage(OpCooperationInput input) {
        OpCooperation oc = new OpCooperation();
        BeanUtils.copyProperties(input,oc);
        int result = opCooperationMapper.insert(oc);
        if(result != 1){
            logger.error("合作加盟表插入失败 opCooperation={}", JSON.toJSONString(oc));
            // 数据添加异常
            throw new BizFeignException(OperationRespExceptionConstant.ADD_ERROR);
        }
    }
}
