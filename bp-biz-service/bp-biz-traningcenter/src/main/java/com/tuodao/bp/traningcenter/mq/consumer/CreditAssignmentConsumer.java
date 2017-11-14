package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.cache.basic.traningcenter.CreditAssignmentCache;

import com.tuodao.bp.model.business.common.input.EmailInput;
import com.tuodao.bp.model.business.traningcenter.cache.TransferTenderInfo;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import com.tuodao.bp.model.mq.FreezeMqInfo;
import com.tuodao.bp.model.mq.PlatformTransferMqInfo;
import com.tuodao.bp.model.mq.TransferMqInfo;
import com.tuodao.bp.traningcenter.client.CommonClient;
import com.tuodao.bp.traningcenter.mq.provider.CreditAssignmentProvider;
import com.tuodao.bp.traningcenter.service.CreditAssignmentService;
import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 债转 消费
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
@Component
public class CreditAssignmentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CreditAssignmentConsumer.class);


    @Autowired
    CreditAssignmentService creditAssignmentService;

    @Autowired
    CreditAssignmentCache creditAssignmentCache;

    @Autowired
    CreditAssignmentProvider creditAssignmentProvider;

    @Autowired
    CommonClient commonClient;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    private GenerateService generateService;
    /**
     * 失败尝试次数
     */
    @Value("${depository.fail.trySec}")
    private int sec;

    @Value("${depository.fail.notifyUsers}")
    private String notifyUsers;

    private Map<String, AtomicInteger> map = new ConcurrentHashMap<>();


    /**
     * 冻结资金 监听
     * 如果冻结资金失败 回滚投资及资金记录
     * @param freezeRequest
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_PLATFORM_FUND_FREEZE,
            selector = DepositoryMqConstant.DE_IN_PLATFORM_FUND_FREEZE_TRANSFER_SELECTOR)
    public void freezeListener(FreezeMqInfo freezeRequest) {
        if(!freezeRequest.isSuccess()) {
//            logger.info("freeze is failed, the order no is:{}", freezeRequest.getOrderNo());
//            creditAssignmentProvider.sendToFreeze(freezeRequest, DepositoryMqConstant.DE_IN_PLATFORM_FUND_FREEZE_TRANSFER_VALUE);
            //冻结失败，回滚
            logger.info("freeze is failed, the order no is:{}", freezeRequest.getOrderNo());
            creditAssignmentService.rollbackFreeze(freezeRequest.getFreezeOrderNo());
        }
    }


    /**
     * 解冻资金 监听 转让
     * 如果解冻资金成功 更新缓存
     * @param freezeRequest
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_PLATFORM_FUND_UNBLOCK,
            selector = DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_SELECTOR)
    public void unFreezeListener(FreezeMqInfo freezeRequest) {
        //存管返回失败重试、保证成功
        if(!freezeRequest.isSuccess()) {
            logger.info("unFreeze is failed, the order no is:{}", freezeRequest.getFreezeOrderNo());
            RAtomicLong num = redissonClient.getAtomicLong("unFree_transfer_"+freezeRequest.getFreezeOrderNo());
            num.getAndIncrement();
            if(num.get() == sec) {
                sendEmail("transfer debt failed", freezeRequest.toString());
                num.delete();
            } else {
                String orderNo = "fs_" + freezeRequest.getBorrowId() + "_transfer" + generateService.get() +"_unFreeze";
                freezeRequest.setOrderNo(orderNo);
                creditAssignmentProvider.sendToUnFreeze(freezeRequest,
                        DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_REVOKED_VALUE);
            }
        }
    }

    /**
     * 解冻资金 监听 债转撤销
     * @param freezeRequest
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_PLATFORM_FUND_UNBLOCK,
            selector = DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_REVOKED_SELECTOR)
    public void unFreezeRevokedListener(FreezeMqInfo freezeRequest) {
        if(!freezeRequest.isSuccess()) {
            logger.info("unFreeze is success, the order no is:{}", freezeRequest.getFreezeOrderNo());
            RAtomicLong num = redissonClient.getAtomicLong("unFree_transfer_revoked_"+freezeRequest.getFreezeOrderNo());
            num.getAndIncrement();
            if(num.get() == sec) {
                sendEmail("transfer debt failed", freezeRequest.toString());
                num.delete();
            } else {
                String orderNo = "fs_" + freezeRequest.getBorrowId() + "_transfer_revoked_" + generateService.get() +"_unFreeze";
                freezeRequest.setOrderNo(orderNo);
                creditAssignmentProvider.sendToUnFreeze(freezeRequest,
                        DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_REVOKED_VALUE);
            }
//            creditAssignmentService.validateUnFreeze(freezeRequest.getFreezeOrderNo());
        }
    }

    /**
     * 转让监听
     * @param transferRequest
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_BIDDING_TRANSFER_DEBT,
            selector = DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_DEBT_TRANSFER_SELECTOR)
    public void transferDebtListener(TransferMqInfo transferRequest) {
        logger.info("out bidding transfer debt :{}", transferRequest.toString());
        UnFreezeInfo unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(transferRequest.getTransferId());
        if(Objects.isNull(unFreezeInfo)) {
            return;
        }
        RAtomicLong num = redissonClient.getAtomicLong(unFreezeInfo.getTransferId());
        if(transferRequest.isSuccess()) {
            logger.info("transfer debt is success, prodId:{}", transferRequest.getProId());
            if(unFreezeInfo != null) {
                List<TransferTenderInfo> list = unFreezeInfo.getTenderInfoList();
                list.stream()
                        .filter(p -> p.getOrderNo().equals(transferRequest.getOrderNo()))
                        .findFirst()
                        .get()
                        .setSuccess(true);

                unFreezeInfo.setTenderInfoList(list);
                creditAssignmentCache.putTransferDebt(unFreezeInfo);
                Long count = list.stream().filter(p -> p.isSuccess()).count();
                if(count.intValue() == list.size()) {
                    unFreezeInfo.setSuccess(true);
                    creditAssignmentCache.putTransferDebt(unFreezeInfo);
                }
            }
        } else {
            logger.info("transfer debt failed, info:{}", transferRequest.toString());
            num.getAndIncrement();
            if(num.get() == sec) {
                sendEmail("transfer debt failed", transferRequest.toString());
                num.delete();
            } else {
                String orderNo = "fs_" + transferRequest.getRelatedProdIds() + "_transfer" + generateService.get() +"_debt";
                transferRequest.setOrderNo(orderNo);
                creditAssignmentProvider.sendToTransfer(transferRequest,
                        DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_DEBT_TRANSFER_VALUE);
            }
        }

        if(unFreezeInfo != null && unFreezeInfo.isSuccess()) {
            logger.info("transfer debt all success, begin full review, info:{}", transferRequest.toString());
            creditAssignmentService.modifyCollection(Integer.parseInt(unFreezeInfo.getTransferId()));
        }
    }

    /**
     * 平台转个人监听
     * @param platformTransferMqInfo
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_PLATFORM_FUND_UNBLOCK,
            selector = DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_TRANSFER_SELECTOR)
    public void platformToPersonListener(PlatformTransferMqInfo platformTransferMqInfo) {
        if(!platformTransferMqInfo.isSuccess()) {
            logger.info("platform transfer is fail, info:{}", platformTransferMqInfo.toString());
            RAtomicLong num = redissonClient.getAtomicLong("platform_transfer_fee_"+platformTransferMqInfo.getUserId());
            num.getAndIncrement();
            if(num.get() == sec) {
                sendEmail("transfer debt failed", platformTransferMqInfo.toString());
                num.delete();
            } else {
                String orderNo = "fs_" + platformTransferMqInfo.getBorrowId() + "_transfer" + generateService.get() +"_transfer";
                platformTransferMqInfo.setOrderNo(orderNo);
                creditAssignmentProvider.sendToPlatformTransfer(platformTransferMqInfo,
                        DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_TRANSFER_VALUE);
            }
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
