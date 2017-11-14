package com.tuodao.bp.traningcenter.mq.provider;

import com.google.common.collect.Maps;
import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.mq.*;
import com.tuodao.bp.model.traningcenter.input.AccountCashApplyInput;
import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


/**
 * @description: 统一维护: 交易中心往其他服务推送的MQ(包含自产自销)
 * @author: 王艳兵
 * @date: 2017/10/12
 * @time: 14:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ProducerMq {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerMq.class);
    /**
     * 投标队列
     */
    @Autowired
    @Qualifier("productTenderQueue")
    private Queue productTenderQueue;

    /**
     * 运营中心优惠券
     */
    @Autowired
    @Qualifier("opCouponGrantQueue")
    private Queue opCouponGrantQueue;

    /**
     * 产品中心
     */
    @Autowired
    @Qualifier("productUpdateQueue")
    private Queue productUpdateQueue;

    /**
     * 运营中心积分等
     */
    @Autowired
    @Qualifier("opScoreTaskQueue")
    private Queue opScoreTaskQueue;

    @Autowired
    @Qualifier("accountDealFinanceQueue")
    private Queue accountDealFinanceQueue;

    @Autowired
    @Qualifier("traningOrginalTenderQueue")
    private Queue traningOrginalTenderQueue;


    @Autowired
    @Qualifier("traningDevelopTenderQueue")
    private Queue traningDevelopTenderQueue;

    /**
     * 更新提现次数
     */
    @Autowired
    @Qualifier("opWithDrawTimesChangeQueue")
    private Queue opWithDrawTimesChangeQueue;

    /**
     * 抵用券变更消息队列
     */
    @Autowired
    @Qualifier("opDiscountChangeMqQueue")
    private Queue opDiscountChangeMqQueue;

    /**
     * 投标请求银行队列
     */
    @Autowired
    @Qualifier("deInBiddingBid")
    private Queue deInBiddingBid;

    /**
     * 提现请求银行队列
     */
    @Autowired
    @Qualifier("deInWithdrawApplyInvestor")
    private Queue deInWithdrawApplyInvestor;

    /**
     * 首投或回款奖励队列
     */
    @Autowired
    @Qualifier("accountFirstInvestOrCashQueue")
    private Queue accountFirstInvestOrCashQueue;


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Autowired
    private GenerateService generateService;

    /**
     * 更新抵用券状态
     * @param voucherId 优惠券id
     * @param status    更新后优惠券状态
     * @param userId    用户id
     * @param executor 投标信息 异步回调将该对象返回
     */
    public void updateVoucherStatus(Integer voucherId,int status,String userId,TenderExecutor executor){      //TODO 更新用户抵用券使用状态
        OpDiscountsChangeMqInfo info = new OpDiscountsChangeMqInfo();
        info.setDisId(Long.parseLong(voucherId.toString()));
        info.setDisStaus(status);
        info.setUserId(userId);
        info.setExecutor(executor);
        jmsMessagingTemplate.convertAndSend(opDiscountChangeMqQueue,info);
    }

    /**
     * 普通投标队列 自产自销
     * @param executor
     */
    public void commonTender(TenderExecutor executor){
        ProductTenderMqInfo info = new ProductTenderMqInfo();
        info.setBusinessType(0);
        info.setExecutor(executor);
        jmsMessagingTemplate.convertAndSend(productTenderQueue,info);
    }

    /**
     * 投标发送银行请求,异步进行结果处理
     * {@link com.tuodao.bp.traningcenter.mq.TenderMq}
     * @param product      产品信息
     * @param borrowTender 标的信息
     * @param user 用户信息
     */
    public void tenderBankRequest(ProductOutput product, BorrowTenderOutput borrowTender, UserAccountInfo user){
        LOGGER.debug("(异步)投标请求银行接口,product:{},borrowTender:{},user:{}",product,borrowTender,user);

        TenderBankRequestMqInfo info = new TenderBankRequestMqInfo();
        info.setAccount(borrowTender.getAccount().doubleValue());
        info.setPreAccount(borrowTender.getPreAccount().doubleValue());
        info.setUserId(user.getUserId());
        info.setUserId(user.getUserId());
        info.setOrderId(borrowTender.getOrderId());
        info.setProductCode(product.getProductCode());
        info.setTenderKey(borrowTender.getTenderKey());
        //使用了加息券
        if(borrowTender.getVoucherCouponId() != null && borrowTender.getVoucherCouponId() > 0){
            info.setVoucherCouponMoney(borrowTender.getVoucherMoney().doubleValue());
            info.setVoucherMoney(0D);
        }else if (borrowTender.getVoucherId() != null && borrowTender.getVoucherId() > 0){
            info.setVoucherMoney(borrowTender.getVoucherMoney().doubleValue());
            info.setVoucherCouponMoney(0D);
        }else{
            info.setVoucherCouponMoney(0D);
            info.setVoucherMoney(0D);
        }
        HashMap<String, Object> headers = Maps.newHashMap();
        headers.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY,DepositoryMqConstant.TENDER_IN_VALUE);
        jmsMessagingTemplate.convertAndSend(deInBiddingBid, info,headers);
    }


    /**
     * 提现申请请求银行
     * @param apply 提现参数
     */
    public void cashApplyBankRequest(AccountCashApplyInput apply){
        AccountCashMqInfo info = new AccountCashMqInfo();
        info.setAmt(String.valueOf(apply.getAccount().doubleValue()));
        info.setFeeAmt(String.valueOf(apply.getFee().doubleValue()));
        info.setOrderNo(generateService.get());
        info.setUserId(apply.getUserId());
        Map<String,Object> headers = Maps.newHashMap();
        headers.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY,DepositoryMqConstant.DE_IN_CASH_APPLY_VALUE);
        jmsMessagingTemplate.convertAndSend(deInWithdrawApplyInvestor,info,headers);
    }

    /**
     * 理财计划下普通标的投标队列
     * @param executor
     */
    public void orginalTender(TenderExecutor executor){
        jmsMessagingTemplate.convertAndSend(traningOrginalTenderQueue,executor);
    }

    /**
     * 理财计划下转让标的投标队列
     * @param executor
     */
    public void developTender(TenderExecutor executor){
        jmsMessagingTemplate.convertAndSend(traningDevelopTenderQueue,executor);
    }




    /**
     * 更新标的剩余可投金额 MQ 剩余金额减少
     * @param borrowId 标的id
     * @param tenderMoney 投标金额
     */
    public void updateProductResidueBalanceMQ(Integer borrowId, BigDecimal tenderMoney){
        ProductUpdateMqInfo productUpdateMqInfo = new ProductUpdateMqInfo();
        productUpdateMqInfo.setOperation(1);
        productUpdateMqInfo.setProductId(borrowId);
        productUpdateMqInfo.setTenderAmount(tenderMoney);
        jmsMessagingTemplate.convertAndSend(productUpdateQueue, productUpdateMqInfo);
    }

    /**
     * 发送资金变动信息
     * account在数据库中默认零 该处不进行非空判断
     * 发送mq到用户中心接受（生产者）
     */
    public void sendAccountDealMqInfo(AccountDealMqInfo accountDealMqInfo){
        jmsMessagingTemplate.convertAndSend(accountDealFinanceQueue, accountDealMqInfo);
    }

    /**
     * 更新提现次数
     * @param userId 用户Id
     * @param times 提现次数更新 -1:扣除一次手续 1:增加一次手续费
     */
    public void updateUserCashNum(String userId,int times){
        WithDrawTimesMqInfo info = new WithDrawTimesMqInfo();
        info.setUserId(userId);
        info.setTimes(times);
        jmsMessagingTemplate.convertAndSend(opWithDrawTimesChangeQueue,userId);
    }

    /**
     * 创建用户异常回滚通知操作
     */
    public void rollbackCreateUserAccount(String userId){
        //TODO 创建账户失败
    }

    /**
     * 散标复审
     */
    public void creatproductUpdateMqInfo(Integer borrowId,String userId)
    {
        ProductUpdateMqInfo productUpdateMqInfo = new ProductUpdateMqInfo();
        productUpdateMqInfo.setOperation(3);
        productUpdateMqInfo.setType(1);
        productUpdateMqInfo.setUserId(userId);
        jmsMessagingTemplate.convertAndSend(productUpdateQueue, productUpdateMqInfo);
    }

    /**
     * 通知运营中心更新积分
     * @param taskId 任务id
     * @param userId 用户id
     * @param mobile 手机号码
     * @param source 来源
     * @param score 积分
     */
    public void updateUserScore(long taskId,String userId,String mobile,String source,int score){
        LOGGER.debug("通知运营中心更新积分入参,taskId:{},userId:{},mobile:{},source:{},score:{}",taskId,userId,mobile,source,score);
        OpScoreTaskMqInput info = new OpScoreTaskMqInput();
        info.setTaskId(taskId);
        info.setMobile(mobile);
        info.setScore(score);
        info.setSource(source);
        info.setUserId(userId);
        jmsMessagingTemplate.convertAndSend(opScoreTaskQueue,info);
    }

    /**
     * 通知运营中心 投标邀请奖励 回款奖励等
     * @param info
     */
    public void updateUserAccount(TenderAccountMqInfo info){
        jmsMessagingTemplate.convertAndSend(accountFirstInvestOrCashQueue,info);
    }

}
