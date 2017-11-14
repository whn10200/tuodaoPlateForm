package com.tuodao.bp.traningcenter.mq;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.model.constant.traningcenter.CashConstant;
import com.tuodao.bp.model.mq.AccountCashMqInfo;
import com.tuodao.bp.traningcenter.service.AccountCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description: 提现接收银行异步回调MQ
 * @author: 王艳兵
 * @date: 2017/10/19
 * @time: 17:38
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class AccountCashMq {

    @Autowired
    private AccountCashService accountCashService;


    /**
     * 银行异步回调 更新提现订单状态更新资金
     * @param info
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_WITHDRAW_APPLY_INVESTOR)
    public void cashBankAsyncResponse(AccountCashMqInfo info){
        if(info.isSuccess()){
            accountCashService.updateAccountCash(info.getOrderNo(), CashConstant.CASH_STATUS_SUCCESS);
        }else{
            accountCashService.updateAccountCash(info.getOrderNo(), CashConstant.CASH_STATUS_FAIL);
        }
    }
}
