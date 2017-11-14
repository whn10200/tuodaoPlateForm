package com.tuodao.bp.useraccount.service.impl;

import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.AccountIncomeDetailMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.AccountIncomeDetailMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountIncomeDetail;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountIncomeDetailExample;
import com.tuodao.bp.useraccount.service.IAccountIncomeDetailService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Description:  账户累计收益明细相关service实现
 * User: zkai
 * Date: 2017/9/13
 * Time: 15:48
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class AccountIncomeDetailServiceImpl implements IAccountIncomeDetailService {
    private static final Logger logger = LoggerFactory.getLogger(AccountIncomeDetailServiceImpl.class);

    @Autowired
    private AccountIncomeDetailMapper accountIncomeDetailMapper;

    /**
     * 添加账户累计收益明细
     * 1.根据用户编号和明细类型查找数据库
     * 2.如果数据库中有数据 修改对应用户对应明细的明细金额
     * 3.否则插入此明细
     * @param input
     */
    @Override
    public void addAccountIncomeDetail(AccountIncomeDetailMqInfo input) {

        if(StringUtils.isBlank(input.getUserId())){
            // 用户ID不能为空
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_ID_CAN_NOT_BE_NULL);
        }
        if(StringUtils.isBlank(input.getIncomeType())){
            // 账户累计收益详细明细类型不能为空
            throw new BizFeignException(UaRespExceptionConstant.INCOME_TYPE_NOT_BE_NULL);
        }

        // 根据用户编号和明细类型查找数据库
        AccountIncomeDetailExample example = new AccountIncomeDetailExample();
        AccountIncomeDetailExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(input.getUserId());
        criteria.andIncomeTypeEqualTo(input.getIncomeType());
        criteria.andIsDelEqualTo(PublicConstant.DEL_NO);
        List<AccountIncomeDetail> list = accountIncomeDetailMapper.selectByExample(example);

        int result = 0;
        if(list.size()>1){
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_RECORD_NOT_ONLY);
        }
        // 如果没有数据库中有数据 插入对应用户对应明细的明细金额
        if(list.size() == 0){
            AccountIncomeDetail accountIncomeDetail = new AccountIncomeDetail();
            accountIncomeDetail.setUserId(input.getUserId());
            accountIncomeDetail.setIncomeType(input.getIncomeType());
            accountIncomeDetail.setIncomeMoney(input.getIncomeMoney());
            accountIncomeDetail.setRemark(input.getRemark());
            accountIncomeDetail.setGmtCreator(input.getOperator());
            accountIncomeDetail.setGmtModifier(input.getOperator());
            result = accountIncomeDetailMapper.insertSelective(accountIncomeDetail);
        }else {
            AccountIncomeDetail accountIncomeDetail = list.get(0);
            BigDecimal incomeMoney =  accountIncomeDetail.getIncomeMoney().add(input.getIncomeMoney());
            AccountIncomeDetail accountIncomeDetailUpdate = new AccountIncomeDetail();
            accountIncomeDetailUpdate.setIncomeMoney(incomeMoney);
            accountIncomeDetailUpdate.setRemark(input.getRemark());
            accountIncomeDetailUpdate.setGmtModifier(input.getOperator());
            result = accountIncomeDetailMapper.updateByExampleSelective(accountIncomeDetailUpdate,example);
        }
        if(result != 1){
            logger.error("同步账户累计收益明细失败");
            throw new BizFeignException(UaRespExceptionConstant.ADD_ERROR);
        }

    }
}
