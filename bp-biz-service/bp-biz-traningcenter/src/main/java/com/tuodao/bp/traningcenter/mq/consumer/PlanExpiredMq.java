package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.output.SelectClaimOutput;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.mq.PlatformTransferMqInfo;
import com.tuodao.bp.model.mq.TenderAccountMqInfo;
import com.tuodao.bp.model.mq.TenderBankCancelRequestMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.client.ProductClient;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowCollectionMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.*;
import com.tuodao.bp.traningcenter.mq.provider.PlanExpiredProvider;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:理财计划到期的mq
 * @author: wuzf
 * @date: 2017/10/25 0027.
 * @time: 11:12
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class PlanExpiredMq {

    private static final Logger logger = LoggerFactory.getLogger(ReverifyDevelopMq.class);

    @Resource
    private AccountStatusService accountStatusService;
    @Resource
    private BorrowTenderMapper borrowTenderMapper;
    @Resource
    private BorrowTenderService borrowTenderService;
    @Resource
    private ProducerMq producerMq;
    @Resource
    private AccountLogService accountLogService;
    @Resource
    private BorrowChoicenessTenderMapper borrowChoicenessTenderMapper;
    @Resource
    private BorrowCollectionMapper borrowCollectionMapper;
    @Resource
    private PlanExpiredProvider planExpiredProvider;
    @Resource
    private ProductClient productClient;
    @Resource
    private GenerateService generateService;
    /**
     * 理财计划到期的mq消费者
     * @param
     */
    @JmsListener(destination= MqContstant.TRANING_EXPIRED_QUEUE)
    public void planExpiredMq(JustIdInput inputs)
    {
        try
        {
            //查询该理财计划下在投资的 (需要修改)
            List<BorrowTender> borrowTenderList =borrowTenderMapper.selectListByNoReverify(inputs.getJustId());
            if(!borrowTenderList.isEmpty() && borrowTenderList!=null)
            {
                for(int i=0;i<borrowTenderList.size();i++)
                {
                    BorrowTender borrowTender =borrowTenderList.get(i);
                    if(borrowTender.getTenderType()==2)
                    {
                        borrowTender.setStatus(9);
                        ProductOutput productOutput = productClient.getProduct(borrowTender.getBorrowId());
                        //发送  投标撤销接口
                        TenderBankCancelRequestMqInfo tenderBankCancelRequestMqInfo = new TenderBankCancelRequestMqInfo();
                        tenderBankCancelRequestMqInfo.setOrderNo(borrowTender.getOrderId());
                        tenderBankCancelRequestMqInfo.setBorrowCode(productOutput.getProductCode());
                        tenderBankCancelRequestMqInfo.setUserId(borrowTender.getUserId());
                        planExpiredProvider.tenderCancelRequest(tenderBankCancelRequestMqInfo,"");

                        accountLogService.withDrawTender(borrowTender.getAccount(),borrowTender.getUserId());
                        //修改缓存
                        //发送到产品中心
                        borrowTenderService.updateProductResidue(inputs.getJustId(), borrowTender.getPreAccount().multiply(new BigDecimal(-1)));
                    }
                    else
                    {
                        borrowTender.setStatus(9);
                        producerMq.updateProductResidueBalanceMQ(borrowTender.getBorrowId(), borrowTender.getAccount().multiply(new BigDecimal(-1)));
                        //添加资金记录 减少冻结 增加余额
                        accountLogService.withDrawTender(borrowTender.getAccount(),borrowTender.getUserId());
                    }
                    borrowTenderMapper.updateByPrimaryKey(borrowTender);
                }
            }
            //查询未匹配的资金
            List<BorrowChoicenessTender> borrowChoicenessTenderList = borrowChoicenessTenderMapper.selectNoMateOver(inputs.getJustId());
            if (!borrowChoicenessTenderList.isEmpty() && borrowChoicenessTenderList!=null)
            {
                for(int j=0;j<borrowTenderList.size();j++)
                {
                    BorrowChoicenessTender borrowChoicenessTender =borrowChoicenessTenderList.get(j);
                    borrowChoicenessTender.setStatus(5);
                    borrowChoicenessTenderMapper.updateByPrimaryKey(borrowChoicenessTender);
                    //添加资金记录 减少冻结 增加余额
                    accountLogService.withDrawTender(borrowChoicenessTender.getPreAccount().divide(borrowChoicenessTender.getAccount()), borrowChoicenessTender.getUserId());
                }
            }


            //释放zq //发送到产品中心
            List<SelectClaimOutput> selectClaimOutputList = borrowCollectionMapper.selectClaim(null,inputs.getJustId());
            for(int k=0;k<selectClaimOutputList.size();k++)
            {
                //回款中tender记录全部改成转让中
                SelectClaimOutput selectClaimOutputs = selectClaimOutputList.get(k);
                BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(selectClaimOutputs.getTenderId());
                borrowTender.setStatus(6);
                borrowTenderMapper.updateByPrimaryKey(borrowTender);
            }
            //释放到用户中心
            List<BorrowChoicenessTender> borrowChoicenessTenders =borrowChoicenessTenderMapper.selectOriginalListByBorrowId(inputs.getJustId());
            for(int m=0;m<borrowChoicenessTenders.size();m++)
            {
                BorrowChoicenessTender borrowChoicenessTender = borrowChoicenessTenders.get(m);
                //平台转个人
                BigDecimal inrest =borrowChoicenessTender.getPreAccountInterest().divide(borrowChoicenessTender.getAccountInterest());
                String orderNo = "fs_" + inputs.getJustId() + "_transfer" + generateService.get() +"_expired";

                PlatformTransferMqInfo platformTransferRequest = new PlatformTransferMqInfo();
                platformTransferRequest.setUserId(borrowChoicenessTender.getUserId());
                platformTransferRequest.setRemark("受让人获取标的转让手续费");
                platformTransferRequest.setOrderNo(orderNo);
                platformTransferRequest.setAmount(inrest);
                platformTransferRequest.setBorrowId(inputs.getJustId());



                AccountLog accountLog = new AccountLog();
                accountLog.setUserId(borrowChoicenessTender.getUserId());
                accountLog.setType(AccountLogType.TRANSFER_AWARD_FEE.getType());
                accountLog.setChangeType(AccountLogType.TRANSFER_AWARD_FEE.getCode());
                accountLog.setOrderNo(orderNo);
                accountLog.setAccount(inrest);
                accountLog.setToAccount(borrowChoicenessTender.getUserId());
                accountLog.setFromAccount("");
                accountLog.setFromRemarks("平台自有账户");
                accountLog.setToRemarks("平账获取利息");
                accountLog.setRemarks("平账获取利息");
                accountLog.setIntrestAccount(inrest);
                accountLogService.updateAccountAndLogPro(accountLog);

                planExpiredProvider.sendToPlatformTransfer(platformTransferRequest,
                        DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_EXPIRED_VALUE);


                //发送用户中心  推荐奖励
                TenderAccountMqInfo tenderAccountMqInfo = new TenderAccountMqInfo();
                tenderAccountMqInfo.setIsFirstTender(false);
                tenderAccountMqInfo.setAwardInterest(borrowChoicenessTender.getPreAccountInterest().subtract(borrowChoicenessTender.getVoucherInterest()));
                producerMq.updateUserAccount(tenderAccountMqInfo);

                //发送到产品中心 表示已经完结


            }
        }
        catch (Exception e)
        {
            throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
        }
        finally {
            List<AccountStatus> accountStatusList = new ArrayList<>();
            AccountStatus accountStatus = new AccountStatus();
            accountStatus.setProductId(inputs.getJustId());
            accountStatusList.add(accountStatus);
            Boolean flag = accountStatusService.updateAccountStatus(accountStatusList, 6);
        }
    }

}
