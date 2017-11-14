package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.cache.basic.traningcenter.CreditAssignmentCache;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.model.business.traningcenter.cache.TransferTenderInfo;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import com.tuodao.bp.model.business.traningcenter.input.PlanDevelopListInput;
import com.tuodao.bp.model.business.traningcenter.input.PlanNomalInput;
import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import com.tuodao.bp.model.mq.TransferMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.mq.provider.PlanCreditAssignmentProvider;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessService;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:理财计划下转让的复审的mq
 * @author: wuzf
 * @date: 2017/10/25 0027.
 * @time: 11:12
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ReverifyDevelopMq {

    private static final Logger logger = LoggerFactory.getLogger(ReverifyDevelopMq.class);

    @Resource
    private BorrowChicenessService borrowChicenessService;
    @Resource
    private AccountStatusService accountStatusService;
    @Resource
    ReturnsCache returnsCache;
    @Resource
    BorrowTenderService borrowTenderService;
    @Resource
    CreditAssignmentCache creditAssignmentCache;
    @Resource
    BorrowTenderMapper borrowTenderMapper;
    @Resource
    PlanCreditAssignmentProvider planCreditAssignmentProvider;

    @Resource
    private GenerateService generateService;

    /**
     * 理财计划下普通标的复审消费者
     * @param
     */
    @JmsListener(destination= MqContstant.TRANING_REVERIFY_DEVELOP_QUEUE)
    public void ReverifyDevelop(PlanDevelopListInput inputs)
    {
        try
        {
            UnFreezeInfo unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(inputs.getBorrowId());
            if(unFreezeInfo != null && unFreezeInfo.isSuccess())
            {
                borrowChicenessService.reverifyDevelop(inputs);
            }
            else if(unFreezeInfo==null)
            {
                transferDebt(inputs);
            }
        }
        catch (Exception e)
        {

            throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
        }
        finally {
            List<AccountStatus> list = new ArrayList<>();
            List<PlanNomalInput> tenderTransferInputs = inputs.getList();
            for(int i=0;i<tenderTransferInputs.size();i++)
            {
                AccountStatus accountStatus = new AccountStatus();
                accountStatus.setProductId(tenderTransferInputs.get(i).getProductId());
                accountStatus.setLastProductId(tenderTransferInputs.get(i).getLastProductId());
                accountStatus.setType(1);
                list.add(accountStatus);
            }
            Boolean flag = accountStatusService.updateAccountStatus(list, 6);
        }
    }


    public void transferDebt(PlanDevelopListInput input) {
        Date now = new Date();
        List<PlanBorrowTenderOutput>  tenderList = borrowTenderService.selectTenderListOnDevReverify(input.getBorrowId());

        UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
        unFreezeInfo.setTransferId("plan"+input.getBorrowId());
        List<TransferTenderInfo> tenderInfoList = new ArrayList<>();
        List<TransferMqInfo> transferRequestList = new ArrayList<>();
        for(int i = 0; i < tenderList.size(); i++) {
            PlanBorrowTenderOutput tender = tenderList.get(i);
            BorrowTender borrowTender = borrowTenderService.getTenderInfo(tender.getId());
            borrowTender.setUpdateTime(new Date());
            String orderNo = "fs_" + input.getBorrowId() + "_transfer" + generateService.get() +"_plan"+"_0";
            borrowTender.setOrderId(orderNo);
            TransferMqInfo transferRequest = new TransferMqInfo();
            transferRequest.setProId(tender.getBorrowId());
            transferRequest.setPlatcustUserId(input.getUserId());
            transferRequest.setTransShare(tender.getAccount());
            transferRequest.setDealAmount(tender.getAccount());
            transferRequest.setCouponAmt(tender.getVoucherMoney());
            transferRequest.setDealPlatcustUserId(tender.getUserId());
            transferRequest.setPublishDate(TimeUtils.formatNomal(now));
            transferRequest.setTransDate(TimeUtils.formatNomal(now));
            transferRequest.setRelatedProdIds(input.getPreBorrowId());
            transferRequest.setSubjectPriority("0");
            transferRequest.setIncomeAcct("01");
            transferRequest.setOrderNo(orderNo);
            transferRequest.setTransferId(tender.getBorrowId());
            transferRequest.setTransAmt(tender.getAccount());

            transferRequest.setPayoutAmt(new BigDecimal(0));
            transferRequest.setTransferIncome(new BigDecimal(0));
            transferRequestList.add(transferRequest);

            TransferTenderInfo transferTenderInfo = new TransferTenderInfo();
            transferTenderInfo.setOrderNo(orderNo);
            tenderInfoList.add(transferTenderInfo);
            borrowTenderMapper.updateByPrimaryKey(borrowTender);
        }

        unFreezeInfo.setTenderInfoList(tenderInfoList);
        //放入缓存
        creditAssignmentCache.putTransferDebt(unFreezeInfo);

        transferRequestList.forEach(transferRequest -> {
            //调用转让mq
            planCreditAssignmentProvider.sendToTransfer(transferRequest,DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_PLAN_TRANSFER_VALUE);
        });
    }


}
