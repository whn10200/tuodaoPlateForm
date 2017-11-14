package com.tuodao.bp.useraccount.mq.provider;

import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.mq.AccountLogMqInfo;
import com.tuodao.bp.model.mq.AccountMqInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountFinance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.math.BigDecimal;

/**
 * @description: 发送至交易中心的MQ 生产者
 * @author: mif
 * @date: 2017/9/28
 * @time: 11:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class MqProviderToTraning {

    private static Logger logger = LoggerFactory.getLogger(MqProviderToTraning.class);

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "traningccountLogQueue")
    private Queue traningccountLogQueue;

    @Resource(name = "createAccountQueue")
    private Queue createAccountQueue;

    /**
     * 发送消息到交易中心-账户资金变动日志
     *
     * @param accountFinance 用户账户信息
     * @param changeMoney    变动资金
     * @param type           变动类型
     */
    public void send2Deal4AccountLog(AccountFinance accountFinance, BigDecimal changeMoney, Integer type) {

        final String userId = accountFinance.getUserId();
        AccountLogMqInfo mqInfo = new AccountLogMqInfo();
        mqInfo.setUserId(userId);
        mqInfo.setAccount(changeMoney);
        mqInfo.setType(type);
        mqInfo.setRemarks(AccountLogType.getMsg(type));
        mqInfo.setTotal(new BigDecimal("0.00"));        //账户备份:用户账户总额,总资
        mqInfo.setCashFrost(new BigDecimal("0.00"));    //账户备份:提现冻结金额
        mqInfo.setTenderFrost(new BigDecimal("0.00"));  //账户备份:投标冻结(散标,精选计划
        mqInfo.setBalance(new BigDecimal("0.00"));      //账户备份:可用余额(可投资,可提现金额)
        mqInfo.setRecharge(new BigDecimal("0.00"));     //账户备份:充值金额(可投资不可提现金额)
        mqInfo.setAwaitInterest(new BigDecimal("0.00"));//账户备份:总待收利息
        mqInfo.setAwaitCapital(new BigDecimal("0.00")); //账户备份:总待收本金
        mqInfo.setFromAccount(userId);                      //资金来源账户(如果为平台账户该字段有值)
        mqInfo.setFromRemarks("");                          //账户名称备注(例如平台子账户,清算账户,加息券子账户等
        mqInfo.setToAccount(userId);                        //资金去往账户(如果为平台账户该字段有值)
        mqInfo.setToRemarks("");                            //账户名称备注(例如平台子账户,加息券子账户等)
        mqInfo.setIsShow(PublicConstant.IF_YES);            //是否显示资金日志,0:不显示(精选计划底层标的资金记录) 1:显示
        mqInfo.setIntrestAccount(changeMoney);              //收益发生变动的资金
        mqInfo.setFeeAccount(new BigDecimal("0.00"));   //手续费发生变动的资金
        mqInfo.setChangeType(AccountLogType.getType(type)); //0收入1支出2冻结
        logger.info("发送账户资金变动只交易中心,mqInfo ={}", mqInfo);
        jmsMessagingTemplate.convertAndSend(traningccountLogQueue, mqInfo);
    }

    /**
     * 用户注册
     *
     * @param userId 用户编码
     */
    public void send2Deal4Register(String userId) {
        AccountMqInfo accountMqInfo = new AccountMqInfo();
        accountMqInfo.setUserId(userId);

        jmsMessagingTemplate.convertAndSend(createAccountQueue, accountMqInfo);
    }
}
