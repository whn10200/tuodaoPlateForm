package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.cache.basic.traningcenter.CreditAssignmentCache;
import com.tuodao.bp.model.business.common.input.EmailInput;
import com.tuodao.bp.model.business.traningcenter.cache.TransferTenderInfo;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import com.tuodao.bp.model.mq.TransferMqInfo;
import com.tuodao.bp.traningcenter.client.CommonClient;
import com.tuodao.bp.traningcenter.mq.provider.PlanCreditAssignmentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 理财计划债转 消费
 * @author wuzf
 * @date 2017/11/08
 * @description
 */
@Component
public class PlanCreditAssignmentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreditAssignmentConsumer.class);

    /**
     * 失败尝试次数
     */
    @Value("${depository.fail.trySec}")
    private int sec;

    @Value("${depository.fail.notifyUsers}")
    private String notifyUsers;

    @Autowired
    CreditAssignmentCache creditAssignmentCache;

    @Autowired
    PlanCreditAssignmentProvider planCreditAssignmentProvider;

    @Autowired
    CommonClient commonClient;


    /**
     * 转让监听
     *
     * @param transferRequest
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_BIDDING_TRANSFER_DEBT,
            selector = DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_PLAN_TRANSFER_SELECTOR)
    public void transferDebtListener(TransferMqInfo transferRequest) {
        logger.info("out bidding transfer plan :{}", transferRequest.toString());
        UnFreezeInfo unFreezeInfo = null;
        if (transferRequest.isSuccess()) {
            logger.info("transfer plan is success, prodId:{}", transferRequest.getProId());
            unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(transferRequest.getTransferId());
            if (unFreezeInfo != null) {
                List<TransferTenderInfo> list = unFreezeInfo.getTenderInfoList();
                list.stream()
                        .filter(p -> p.getOrderNo().equals(transferRequest.getOrderNo()))
                        .findFirst()
                        .get()
                        .setSuccess(true);

                unFreezeInfo.setTenderInfoList(list);
                creditAssignmentCache.putTransferDebt(unFreezeInfo);
                Long count = list.stream().filter(p -> p.isSuccess()).count();
                if (count.intValue() == list.size()) {
                    unFreezeInfo.setSuccess(true);
                    creditAssignmentCache.putTransferDebt(unFreezeInfo);
                }
            }
        }
        else
        {
            logger.info("transfer debt failed, info:{}", transferRequest.toString());
            int nowNum = Integer.parseInt(transferRequest.getOrderNo().split("_")[4]);
            int nextNum = nowNum+1;
            if(nowNum==sec)
            {
                //发送邮件
                sendEmail("transfer debt failed", transferRequest.toString());
            }
            else
            {
                unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(transferRequest.getTransferId());
                if (unFreezeInfo != null) {
                    List<TransferTenderInfo> list = unFreezeInfo.getTenderInfoList();
                    list.stream()
                            .filter(p -> p.getOrderNo().equals(transferRequest.getOrderNo()))
                            .findFirst()
                            .get()
                            .setOrderNo(transferRequest.getOrderNo().split("_")[0]+"_"
                                    +transferRequest.getOrderNo().split("_")[1]+"-"+
                                    transferRequest.getOrderNo().split("_")[2]+"-"+
                                    transferRequest.getOrderNo().split("_")[3]+"-"+ +nextNum);
                    unFreezeInfo.setTenderInfoList(list);
                    creditAssignmentCache.putTransferDebt(unFreezeInfo);
                }
            }
            planCreditAssignmentProvider.sendToTransfer(transferRequest, DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_PLAN_TRANSFER_VALUE);
        }

    }

    public void sendEmail(String subject, String content) {
        EmailInput input = new EmailInput();
        input.setSubject(subject);
        input.setContent(content);
        input.setReceivers(notifyUsers);
        commonClient.sendEmail(input);
    }
}
