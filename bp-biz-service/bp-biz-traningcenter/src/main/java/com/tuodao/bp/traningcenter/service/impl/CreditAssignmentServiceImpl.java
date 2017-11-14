package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.*;
import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.cache.basic.traningcenter.CreditAssignmentCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.cache.TransferTenderInfo;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTransferOutput;
import com.tuodao.bp.model.business.traningcenter.output.CreditAssignmentOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.enums.ChannelType;
import com.tuodao.bp.model.mq.AccountLogMqInfo;
import com.tuodao.bp.model.mq.OpDiscountsChangeMqInfo;
import com.tuodao.bp.model.mq.FreezeMqInfo;
import com.tuodao.bp.model.mq.PlatformTransferMqInfo;
import com.tuodao.bp.model.mq.TransferMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowCollectionMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTransferMapper;
import com.tuodao.bp.traningcenter.db.model.basic.*;
import com.tuodao.bp.traningcenter.enums.CollectionStatus;
import com.tuodao.bp.traningcenter.enums.TenderStatus;
import com.tuodao.bp.traningcenter.enums.TenderType;
import com.tuodao.bp.traningcenter.enums.TransferStatus;
import com.tuodao.bp.traningcenter.mq.AccountLogMq;
import com.tuodao.bp.traningcenter.mq.provider.CreditAssignmentProvider;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import com.tuodao.bp.traningcenter.service.CreditAssignmentService;
import com.tuodao.bp.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author qingli.chen
 * @date 2017/9/14
 * @description 债权转让
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class CreditAssignmentServiceImpl implements CreditAssignmentService {

    private static final Logger logger = LoggerFactory.getLogger(CreditAssignmentServiceImpl.class);

    @Autowired
    BorrowTransferMapper borrowTransferMapper;

    @Autowired
    BorrowCollectionMapper borrowCollectionMapper;

    @Autowired
    BorrowTenderService borrowTenderService;

    @Autowired
    BorrowTenderMapper borrowTenderMapper;

    @Autowired
    AccountLogService accountLogService;

    @Autowired
    UserAccountCache userAccountCache;

    @Autowired
    AccountLogMq accountLogMq;

    @Autowired
    CreditAssignmentProvider creditAssignmentProvider;

    @Autowired
    CreditAssignmentCache creditAssignmentCache;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private GenerateService generateService;

    @Value("${creditAssignment.updateTime}")
    private long updateTime;

    @Override
    public PageInfo<CreditAssignmentOutput> pageByTransferableList(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentOutput> result = new PageInfo<>();
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());

        List<CreditAssignmentOutput> outputList = new ArrayList<>();
        List<CreditAssignmentBean> list = borrowTransferMapper.pageByStatus(input.getUserId(), input.getStartTime(), input.getEndTime());

        if(!CollectionUtils.isEmpty(list)) {
            List<Integer> tenderIdList = FluentIterable.from(list).transform(new Function<CreditAssignmentBean, Integer>() {
                @Override
                public Integer apply(CreditAssignmentBean creditAssignmentBean) {
                    return creditAssignmentBean.getTenderId();
                }
            }).toList();


            List<BorrowCollectionCapital> borrowCollectionCapitalList = borrowCollectionMapper.selectByTenderIdListAndStatus(tenderIdList, 1);

            Map<Integer,BorrowCollectionCapital> map = Maps.uniqueIndex(borrowCollectionCapitalList.iterator(),
                    new Function<BorrowCollectionCapital, Integer>() {
                @Override
                public Integer apply(BorrowCollectionCapital borrowCollectionCapital) {
                    return borrowCollectionCapital.getTenderId();
                }
            });

            list.forEach(creditAssignmentBean -> {
                CreditAssignmentOutput creditAssignmentOutput = new CreditAssignmentOutput();
                TransferUtil.transfer(creditAssignmentBean, creditAssignmentOutput);

                BorrowCollectionCapital borrowCollectionCapital = map.get(creditAssignmentOutput.getTenderId());

                if(borrowCollectionCapital != null) {
                    creditAssignmentOutput.setRecoverCapital(BigDecimalUtils.centToYuan(borrowCollectionCapital.getPreCapital()));
                    creditAssignmentOutput.setRemaining(borrowCollectionCapital.getSumCount());
                }

                outputList.add(creditAssignmentOutput);
            });
        }
        result.setList(outputList);
        Page<CreditAssignmentBean> page = (Page<CreditAssignmentBean>)list;
        result.setTotal(page.getTotal());

       return result;
    }

    @Override
    public PageInfo<BorrowTransferOutput> pageByTransferList(CreditAssignmentInput input) {

        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());


        if(ChannelType.getValue(input.getRequestType()).getValue() == ChannelType.APP.getValue()) {
            input.setStatus(null);
        }
        List<BorrowTransferBean> borrowTransferList = borrowTransferMapper.selectByUserIdAndStatus(input.getUserId(),
                input.getStatus(), input.getStartTime(), input.getEndTime());

        borrowTransferList.forEach(borrowTransferBean -> {
            BorrowTransferOutput borrowTransferOutput = new BorrowTransferOutput();
            TransferUtil.transfer(borrowTransferBean, borrowTransferOutput);

            borrowTransferOutput.setTransferId(borrowTransferBean.getId());
            borrowTransferOutput.setAccount(BigDecimalUtils.centToYuan(borrowTransferOutput.getAccount()));
            borrowTransferOutput.setClaimsValue(BigDecimalUtils.centToYuan(borrowTransferOutput.getClaimsValue()));
            borrowTransferOutput.setDiscount(BigDecimalUtils.centToYuan(borrowTransferOutput.getDiscount()));
            borrowTransferOutput.setFee(BigDecimalUtils.centToYuan(borrowTransferOutput.getFee()));
            if(borrowTransferBean.getStatus() == 0) {
                borrowTransferOutput.setStatusMsg("转让中");
            } else {
                borrowTransferOutput.setStatusMsg("转让成功");
            }
        });

        List<BorrowTransferOutput> borrowTransferOutputList = TransferUtil.transferList(borrowTransferList, BorrowTransferOutput.class);

        PageInfo<BorrowTransferOutput> result = new PageInfo<>(borrowTransferOutputList);
        Page<BorrowTransferBean> page = (Page<BorrowTransferBean>)borrowTransferList;
        result.setTotal(page.getTotal());
        return result;
    }

    @Override
    public PageInfo<BorrowTransferOutput> pageByTransferee(CreditAssignmentInput input) {
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
        List<BorrowTender> borrowTenderList = borrowTenderMapper.selectBorrowTenderByUserIdAndStatus(input.getUserId(), input.getStatus());
        List<BorrowTransferOutput> transfereeOutputList = new ArrayList<>();
        borrowTenderList.forEach(borrowTender -> {
            BorrowTransferOutput borrowTransferOutput = new BorrowTransferOutput();
            TransferUtil.transfer(borrowTender, borrowTransferOutput);

            if(borrowTender.getAccount() != null) {
                borrowTransferOutput.setAccount(BigDecimalUtils.centToYuan(borrowTender.getAccount()));
            }
            if(borrowTender.getAccountInterest() != null) {
                borrowTransferOutput.setInterest(BigDecimalUtils.centToYuan(borrowTender.getAccountInterest()));
            }
            if(borrowTender.getTenderTranAward() != null) {
                borrowTransferOutput.setReward(BigDecimalUtils.centToYuan(borrowTender.getTenderTranAward()));
            }


            borrowTransferOutput.setStatusMsg(TenderStatus.getMsg(borrowTender.getStatus()));
            if(borrowTender.getStatus() == TenderStatus.BIDING.getValue()
                    || borrowTender.getStatus() == TenderStatus.BID_SUCCESS.getValue()) {
                borrowTransferOutput.setStatus(1);
            } else if (borrowTender.getStatus() == TenderStatus.BACK_MONEY.getValue()) {
                borrowTransferOutput.setStatus(2);
            } else if (borrowTender.getStatus() == TenderStatus.BACK_MONEY_OVER.getValue()) {
                borrowTransferOutput.setStatus(3);
            }

            transfereeOutputList.add(borrowTransferOutput);
        });

        PageInfo<BorrowTransferOutput> result = new PageInfo<>(transfereeOutputList);
        Page<BorrowTender> page = (Page<BorrowTender>)borrowTenderList;
        result.setTotal(page.getTotal());
        return result;
    }

    @Override
    public BorrowTransfer findByTenderId(Integer tenderId, String userId, List<Integer> statusList) {
        return null;
    }

    @Override
    public BorrowTransfer findByTenderId(Integer tenderId) {
        return borrowTransferMapper.selectByPrimaryKey(tenderId);
    }

    @Override
    public BorrowTransfer findById(Integer id) {
        return borrowTransferMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<BorrowTransfer> findByStatus(TransferQueryInput input) {
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
        List<BorrowTransfer> list = borrowTransferMapper.selectByPeriod(input.getBeginPeriod(), input.getEndPeriod());

        PageInfo<BorrowTransfer> result = new PageInfo<>(list);
        Page<BorrowTransfer> page = (Page<BorrowTransfer>)list;
        result.setTotal(page.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void save(BorrowTransfer borrowTransfer) {
        if(borrowTransfer.getId() == null) {
            borrowTransferMapper.insertSelective(borrowTransfer);
        } else {
            borrowTransferMapper.updateByPrimaryKeySelective(borrowTransfer);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public BorrowTender tender(TransferTenderInput input) {

        RLock fairLock = redissonClient.getFairLock(RedisConstans.TENDER_TRANSFER_LOCK + input.getTransferId());
        BorrowTender borrowTender = new BorrowTender();
        try {
            if (fairLock.tryLock(3, TimeUnit.SECONDS)) {
                TenderTransferInput tenderTransferInput = new TenderTransferInput();
                tenderTransferInput.setTenderId(input.getTransferId());
                BorrowTransfer borrowTransfer = this.findById(input.getTransferId());

                //债转标不存在
                if (borrowTransfer == null) {
                    logger.error("transfer is not found, transferId:{}", input.getTransferId());
                    throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_NULL);
                }

                //该债权转让是你自己的，亲~
                if (borrowTransfer.getUserId().equals(input.getUserId())) {
                    logger.error("this transfer is your self, transferId:{}", input.getTransferId());
                    throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_OWNER);
                }

                UserAccountInfo userAccountInfo = userAccountCache.getUserAccoutInfo(input.getUserId());
                //判断是否开通存管
                if (userAccountInfo.getIsOpenDeposit() != 1) {
                    logger.error("depository is not open, userId:{}", input.getUserId());
                    throw new MicroServiceException(CreditAssignmentConstant.DEPOSITORY_IS_NOT_OPEN);
                }

                //当前状态不可投标
                if (borrowTransfer.getStatus() == TransferStatus.FLOW_MARK.getValue()
                        || borrowTransfer.getStatus() == TransferStatus.TRANSFER_SUCCESS.getValue()) {
                    logger.error("transfer can not tender, transferId:{}", input.getTransferId());
                    throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_CAN_NOT_TENDER);
                }

                //当期债转已结束
                if (borrowTransfer.getEndTime().before(new Date())) {
                    logger.error("transfer can not tender, transferId:{}", input.getTransferId());
                    throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_END);
                }

                BigDecimal lastAccount = borrowTransfer.getAccount().subtract(borrowTransfer.getAccountYes());
                BigDecimal money = BigDecimal.ZERO;
                //判断是否满标
                if (lastAccount.compareTo(BigDecimal.ZERO) < 0) {
                    logger.error("transfer is fill, transferId:{}", input.getTransferId());
                    throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_FULL);
                }

                //剩余可投金额
                if (lastAccount.compareTo(input.getMoney()) >= 0) {
                    money = input.getMoney();
                } else {
                    //投资金额大于可投金额
                    logger.error("tender money more than the last account,userId:{}, lastAccount:{},current money:{}",
                            input.getUserId(), lastAccount, input.getMoney());
                    throw new MicroServiceException(CreditAssignmentConstant.TENDER_MORE_THAN_BALANCE);
                }

                if (userAccountInfo.getUsableFund().compareTo(money) < 0) {
                    //账户可用余额不足
                    logger.error("Insufficient balance of available accounts, userId:{}, current money:{}",
                            input.getUserId(), money);
                    throw new MicroServiceException(CreditAssignmentConstant.INSUFFICIENT_BALANCE);
                }


                BigDecimal money2Fen = BigDecimalUtils.yuanToCent(money);
                //调用投资
                String orderNo = "fs_" + borrowTransfer.getPreBorrowId() + "_transfer" + generateService.get() + "_freeze";

                borrowTender.setUserId(input.getUserId());
                borrowTender.setMobile(input.getMobile());
                borrowTender.setBorrowId(input.getTransferId());
                borrowTender.setPreAccount(money2Fen);
                borrowTender.setAccount(money2Fen);
                borrowTender.setOrderId(orderNo);
                borrowTender.setTenderType(TenderType.TRANSFER_TENDER.getValue());
                if (StringUtils.isNotBlank(input.getRequestType())) {
                    borrowTender.setChannel(Integer.parseInt(input.getRequestType()));
                }
                borrowTender.setRemarks("手动投标");

                borrowTender.setFeeAccount(money2Fen);
                ProductOutput productOutput = new ProductOutput();
                productOutput.setProductPeriod(borrowTransfer.getPeriod());
                productOutput.setAwardScale(borrowTransfer.getPlatformApr());
                productOutput.setRefundWay(borrowTransfer.getRaymentType());
                productOutput.setBorrowApr(borrowTransfer.getApr());
                if (!Objects.isNull(input.getVoucherId()) && input.getVoucherId() != 0L) {
                    borrowTender.setVoucherId(Integer.parseInt(String.valueOf(input.getVoucherId())));
                    OpDiscountsChangeMqInfo opDiscountsChangeMqInfo = new OpDiscountsChangeMqInfo();
                    opDiscountsChangeMqInfo.setDisId(input.getVoucherId());
                    opDiscountsChangeMqInfo.setDisStaus(2);
                    opDiscountsChangeMqInfo.setUserId(input.getUserId());
                    creditAssignmentProvider.sendToDiscountChange(opDiscountsChangeMqInfo);
                }

                borrowTenderService.setInterest(borrowTender, productOutput);
                borrowTenderMapper.insertSelective(borrowTender);

                //调用资金记录接口
                AccountLogMqInfo accountLogMqInfo = new AccountLogMqInfo();
                accountLogMqInfo.setUserId(input.getUserId());
                accountLogMqInfo.setOrderNo(orderNo);
                accountLogMqInfo.setType(AccountLogType.TRANSFER_TENDER_FROST.getType());
                accountLogMqInfo.setAccount(money2Fen);
                accountLogMqInfo.setRemarks("手动投标[" + borrowTransfer.getBorrowName() + "]所冻结资金");
                accountLogMqInfo.setTenderFrost(money2Fen);
                accountLogMqInfo.setChangeType(AccountLogType.TRANSFER_TENDER_FROST.getCode());
                accountLogMqInfo.setFromRemarks("用户投资账号");
                accountLogMqInfo.setFromAccount(input.getUserId());
                accountLogMqInfo.setToAccount(borrowTransfer.getUserId());
                accountLogMqInfo.setToRemarks("借款人投资账号");
                accountLogMqInfo.setBalance(money2Fen);
                accountLogMq.addAccountIncomeDetail(accountLogMqInfo);

                //调用存管冻结接口
                FreezeMqInfo freezeRequest = new FreezeMqInfo();
                freezeRequest.setAmount(money2Fen);
                freezeRequest.setFreezeFlg("01");
                freezeRequest.setOrderNo(orderNo);
                creditAssignmentProvider.sendToFreeze(freezeRequest, DepositoryMqConstant.DE_IN_PLATFORM_FUND_FREEZE_TRANSFER_VALUE);

                //更新债权
                if (lastAccount.subtract(money).equals(BigDecimal.ZERO)) {
                    borrowTransfer.setStatus(TransferStatus.TRANSFER_SUCCESS.getValue());
                    borrowTransfer.setVerifyTime(new Date());
                    save(borrowTransfer);
                }
            }
        } catch (InterruptedException e) {
            logger.error("tender transfer failed, userId:{}, msg:{}", input.getUserId(), e.getMessage());
            throw new MicroServiceException(CreditAssignmentConstant.TENDER_TRANSFER_FAILED);
        } finally {
            fairLock.unlock();
        }
        return borrowTender;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void rollbackFreeze(String orderNo) {
        BorrowTender borrowTender = borrowTenderMapper.getByOrderId(orderNo);
        borrowTender.setRemarks("冻结资金失败");

        borrowTenderService.updateBorrowTenderFail(borrowTender, "");
        accountLogService.insertTenderFailLog(borrowTender, AccountLogType.TRANSFER_UNFREEZE);
    }

    @Override
    public void unFreeze() {
        //查询满标债转
        List<BorrowTransfer> borrowTransferList = this.findFullAndNotReview();

        borrowTransferList.forEach(borrowTransfer -> {
            logger.info("transferId:{}", borrowTransfer.getId());

            UnFreezeInfo unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(borrowTransfer.getId());
            if(unFreezeInfo != null & !unFreezeInfo.isSuccess()) {
                List<TransferTenderInfo> tenderInfoList = unFreezeInfo.getTenderInfoList();
                tenderInfoList = tenderInfoList.stream().filter(transferTenderInfo -> !transferTenderInfo.isSuccess())
                        .collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(tenderInfoList)) {
                    tenderInfoList.forEach(transferTenderInfo -> {
                        FreezeMqInfo request = new FreezeMqInfo();
                        String orderNo = "fs_" + borrowTransfer.getPreBorrowId() + "_transfer" + generateService.get() +"_unFreeze";
                        request.setOrderNo(orderNo);
                        request.setAmount(transferTenderInfo.getAccount());
                        request.setFreezeOrderNo(transferTenderInfo.getOrderNo());
                        request.setBorrowId(borrowTransfer.getPreBorrowId());

                        creditAssignmentProvider.sendToUnFreeze(request,
                                DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_VALUE);
                    });
                }
            }
        });
    }

    @Override
    public void validateUnFreeze(String orderNo) {
        BorrowTender borrowTender = borrowTenderMapper.getByOrderId(orderNo);
        Optional<BorrowTender> borrowTenderOptional = Optional.of(borrowTender);
        UnFreezeInfo unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(borrowTenderOptional.get().getBorrowId());
        if(unFreezeInfo != null) {
            List<TransferTenderInfo> list = unFreezeInfo.getTenderInfoList();
            list.stream()
                    .filter(p -> p.getOrderNo().equals(orderNo))
                    .findFirst()
                    .get()
                    .setSuccess(true);

            Long count = list.stream().filter(p -> p.isSuccess()).count();
            if(count.intValue() == list.size()) {
                unFreezeInfo.setSuccess(true);
                creditAssignmentCache.putTransferUnFreeze(unFreezeInfo);
            }
        }
    }

    @Override
    public List<BorrowTransfer> findFullAndNotReview() {
        List<BorrowTransfer> list = borrowTransferMapper.selectFullAndNotReview();
        return list;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void fullReview() {
        //查询满标债转
        List<BorrowTransfer> borrowTransferList = this.findFullAndNotReview();
        borrowTransferList.forEach(borrowTransfer -> {
            //判断是否全部解冻成功，如果是进行下一步复审
            UnFreezeInfo unFreezeInfo = creditAssignmentCache.getTransferUnFreeze(borrowTransfer.getId());
            if(unFreezeInfo != null && unFreezeInfo.isSuccess()) {
                transferDebt(borrowTransfer);
//                this.modifyCollection(borrowTransfer);
            }
        });
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void revokedTransfer(Integer borrowId, BorrowTransfer borrowTransfer) {
        logger.info("提前还款，borrowId:{}", borrowId);
        Date now = new Date();
        if(borrowTransfer == null) {
            BorrowTransferExample example = new BorrowTransferExample();
            example.createCriteria().andPreBorrowIdEqualTo(borrowId).andStatusEqualTo(TransferStatus.TRANSFER.getValue());
            List<BorrowTransfer> list = borrowTransferMapper.selectByExample(example);

            //筛选还未复审
            borrowTransfer = list.stream()
                    .filter(b -> b.getStatus().equals(TransferStatus.TRANSFER.getValue()) && b.getEndTime().before(now))
                    .findFirst()
                    .get();
        }
        //查询投标记录
        List<BorrowTender> borrowTenderList = borrowTenderMapper.selectByBorrowIdAndType(borrowTransfer.getPreBorrowId(),
                TenderStatus.BIDING.getValue());
        //设置债转流标
        borrowTransfer.setStatus(TransferStatus.FLOW_MARK.getValue());
        this.save(borrowTransfer);

        //设置投标失败
        List<AccountLog> accountLogList = new ArrayList<>();
        Integer preBorrowId = borrowTransfer.getPreBorrowId();
        borrowTenderList.forEach(borrowTender -> {
            borrowTender.setStatus(TenderStatus.TENDER_FAILED.getValue());
            borrowTender.setUpdateTime(now);

            FreezeMqInfo request = new FreezeMqInfo();
            String orderNo = "fs_" + preBorrowId + "_transfer_revoked_" + generateService.get() +"_unFreeze";
            request.setOrderNo(orderNo);
            request.setAmount(borrowTender.getAccount());
            request.setFreezeOrderNo(borrowTender.getOrderId());
            creditAssignmentProvider.sendToUnFreeze(request,
                    DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_REVOKED_VALUE);

            AccountLog accountLog = new AccountLog();
            accountLog.setUserId(borrowTender.getUserId());
            accountLog.setType(AccountLogType.TRANSFER_FAIL.getCode());
            accountLog.setAccount(borrowTender.getAccount());
            accountLog.setChangeType(AccountLogType.TRANSFER_FAIL.getType());
            accountLog.setOrderNo(orderNo);
            accountLogList.add(accountLog);
        });

        borrowTenderMapper.updateBatch(borrowTenderList);
        accountLogService.batchUpdate(accountLogList);
    }

    @Override
    public void revokedTransfer() {
        BorrowTransferExample example = new BorrowTransferExample();
        example.createCriteria().andStatusEqualTo(TransferStatus.TRANSFER.getValue()).andEndTimeLessThan(new Date());
        List<BorrowTransfer> list = borrowTransferMapper.selectByExample(example);
        list.forEach(borrowTransfer -> {
            this.revokedTransfer(borrowTransfer.getId(), borrowTransfer);
        });
    }

    public void transferDebt(BorrowTransfer borrowTransfer) {
        Date now = new Date();
        List<BorrowTender> tenderList = borrowTenderMapper.selectByBorrowIdAndType(borrowTransfer.getId(), 1);

        UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
        unFreezeInfo.setTransferId(borrowTransfer.getId().toString());
        List<TransferTenderInfo> tenderInfoList = new ArrayList<>();
        List<TransferMqInfo> transferRequestList = new ArrayList<>();
        for(int i = 0; i < tenderList.size(); i++) {
            BorrowTender tender = tenderList.get(i);

            String orderNo = "fs_" + borrowTransfer.getPreBorrowId() + "_transfer" + generateService.get() +"_debt";
            TransferMqInfo transferRequest = new TransferMqInfo();
            transferRequest.setProId(tender.getBorrowId());
            transferRequest.setPlatcustUserId(borrowTransfer.getUserId());
            transferRequest.setTransShare(tender.getAccount());
            transferRequest.setDealAmount(tender.getAccount());
            transferRequest.setCouponAmt(tender.getVoucherCouponMoney());
            transferRequest.setDealPlatcustUserId(tender.getUserId());
            transferRequest.setPublishDate(TimeUtils.formatNomal(now));
            transferRequest.setTransDate(TimeUtils.formatNomal(now));
            transferRequest.setRelatedProdIds(borrowTransfer.getPreBorrowId());
            transferRequest.setSubjectPriority("0");
            transferRequest.setIncomeAcct("01");
            transferRequest.setOrderNo(orderNo);
            transferRequest.setTransferId(tender.getBorrowId());
            transferRequest.setTransAmt(tender.getAccount());
            if (i == 0) {
                transferRequest.setPayoutAmt(borrowTransfer.getFee());
                transferRequest.setTransferIncome(borrowTransfer.getProfit());
            }
            transferRequestList.add(transferRequest);

            TransferTenderInfo transferTenderInfo = new TransferTenderInfo();
            transferTenderInfo.setOrderNo(orderNo);
            tenderInfoList.add(transferTenderInfo);
        }

        unFreezeInfo.setTenderInfoList(tenderInfoList);
        //放入缓存
        creditAssignmentCache.putTransferDebt(unFreezeInfo);

        transferRequestList.forEach(transferRequest -> {
            //调用转让mq
            creditAssignmentProvider.sendToTransfer(transferRequest, DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_DEBT_TRANSFER_VALUE);
        });
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void test() {

        BorrowTransfer borrowTransfer = new BorrowTransfer();
        borrowTransfer.setUserId("123456");
        borrowTransferMapper.insertSelective(borrowTransfer);


        OpDiscountsChangeMqInfo opDiscountsChangeMqInfo = new OpDiscountsChangeMqInfo();
        opDiscountsChangeMqInfo.setDisId(45L);
        opDiscountsChangeMqInfo.setDisStaus(2);
        opDiscountsChangeMqInfo.setUserId("15988888884-d9ccs6n849nz9wl8t1ci");
        creditAssignmentProvider.sendToDiscountChange(opDiscountsChangeMqInfo);

//        BorrowTender borrowTender = new BorrowTender();
//        borrowTenderMapper.insertSelective(borrowTender);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void modifyCollection(Integer transferId) {
        BorrowTransfer borrowTransfer = borrowTransferMapper.selectByPrimaryKey(transferId);
        if(borrowTransfer == null) {
            return;
        }
        //查询回款计划
        List<BorrowCollection> borrowCollectionList = borrowCollectionMapper.selectByTenderId(borrowTransfer.getTenderId());
        if(CollectionUtils.isEmpty(borrowCollectionList)) {
            logger.error("the collection list is empty, tenderId:{}", borrowTransfer.getTenderId());
            throw new BizFeignException(CreditAssignmentConstant.COLLECTION_LIST_IS_EMPTY);
        }



        //排序
        Ordering<BorrowCollection> dateOrdering = new Ordering<BorrowCollection>() {
            @Override
            public int compare(BorrowCollection borrowCollection, BorrowCollection t1) {
                return borrowCollection.getPreCollectionTime().compareTo(t1.getPreCollectionTime());
            }
        };
        //已经还款列表
        List<BorrowCollection> repaidList = borrowCollectionList.stream()
                .filter(p -> p.getStatus() == CollectionStatus.SUCCESS.getValue())
                .sorted(dateOrdering)
                .collect(Collectors.toList());


        if(CollectionUtils.isEmpty(repaidList)) {
            logger.error("collection success list is empty, tenderId: {}", borrowTransfer.getTenderId());
            throw new BizFeignException(CreditAssignmentConstant.COLLECTION_SUCCESS_LIST_IS_EMPTY);
        }

        BorrowCollection repaidCollection = repaidList.get(0);
        long day = DateUtil.diffDay(repaidCollection.getPreCollectionTime(), new Date());
        if(day < 0L) {
            logger.error("");
            throw new BizFeignException(1);
        }

        //未还款列表
        List<BorrowCollection> waitList = borrowCollectionList.stream()
                .filter(p -> p.getStatus() == CollectionStatus.WAIT.getValue())
                .sorted(dateOrdering)
                .collect(Collectors.toList());
        //未回款时间列表
        List<Date> waitDateList = waitList.stream().map(BorrowCollection :: getPreCollectionTime).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(waitList)) {
            logger.error("collection wait list is empty, tenderId: {}", borrowTransfer.getTenderId());
            throw new BizFeignException(CreditAssignmentConstant.COLLECTION_WAIT_LIST_IS_EMPTY);
        }
        BorrowCollection waitCollection = waitList.get(0);
        //当期总利息*天数/30
        BigDecimal borrowerInterest = waitCollection.getPreInterest().multiply(new BigDecimal(day)).divide(new BigDecimal(30));
        //本金
        BigDecimal account = BigDecimal.ZERO;
        //转让价值
        BigDecimal transferWorth = BigDecimal.ZERO;

        waitList.forEach(borrowCollection -> {
            account.add(borrowCollection.getPreCapital());
            transferWorth.add(borrowCollection.getPreCapital());
        });
        transferWorth.subtract(borrowerInterest);

        //更新状态成已转让
        borrowCollectionMapper.updateStatusByTenderIdList(borrowTransfer.getTenderId(), CollectionStatus.TRANSFERED.getValue());

        //修改第一期本金利息
        BorrowCollection borrowCollection = borrowCollectionList.get(0);
        borrowCollection.setStatus(CollectionStatus.TRANSFERED.getValue());
        borrowCollection.setCapital(account);
        borrowCollection.setInterest(borrowerInterest);
        borrowCollectionMapper.updateByPrimaryKeySelective(borrowCollection);

        //修改债转表
        borrowTransfer.setTransferWorth(transferWorth);
        borrowTransferMapper.updateByPrimaryKeySelective(borrowTransfer);

        //查询投资债权的记录
        List<BorrowTender> tenderList = borrowTenderMapper.selectByBorrowIdAndType(borrowTransfer.getId(),1);
        Date now = new Date();

        List<BorrowCollection> collectionList = new ArrayList<>();
//        List<AccountLog> logList = new ArrayList<>();
        tenderList.forEach(borrowTender -> {
            AccountLog accountLog = new AccountLog();
            accountLog.setUserId(borrowTender.getUserId());
            accountLog.setType(AccountLogType.TRANSFER_TENDER_SUCCESS.getType());
            accountLog.setAccount(borrowTender.getAccount());
            accountLog.setTenderFrost(borrowTender.getAccount());
            accountLog.setToAccount(borrowTransfer.getUserId());
            accountLog.setChangeType(AccountLogType.TRANSFER_TENDER_SUCCESS.getCode());
            accountLog.setToRemarks("投标[" + borrowTransfer.getBorrowName() + "]成功待收金额增加");
            accountLog.setFromRemarks("投标[" + borrowTransfer.getBorrowName() + "]成功待收金额增加");
            accountLog.setFromAccount(borrowTender.getUserId());

            accountLogService.updateAccountAndLogPro(accountLog);

            List<BorrowPlan> recoverList = BorrowUtils.collectionFixedPaymentMortgage(
                    borrowTransfer.getPeriod(),
                    borrowTender.getAccount().doubleValue(),
                    borrowTransfer.getApr().doubleValue(),
                    borrowTransfer.getPlatformApr().doubleValue(),
                    borrowTender.getVoucherCouponMoney().doubleValue(),now);

            for(int i = 0; i < recoverList.size(); i++) {
                BorrowPlan borrowPlan = recoverList.get(i);
                BorrowCollection collection = new BorrowCollection();
                TransferUtil.transfer(borrowPlan, collection);
                if(i == 0) {
                    // 第一期利息重新计算
                    BigDecimal rate1 = new BigDecimal(new Double(30 - day) / 30);
                    collection.setPreInterest(new BigDecimal(borrowPlan.getPreInterest()).multiply(rate1));
                }
                collection.setTenderId(borrowTender.getId());
                collection.setUserId(borrowTender.getUserId());
                collection.setIsShow(TenderConstant.COLLECTION_SHOW);
                collection.setBorrowId(borrowTransfer.getPreBorrowId());
                collection.setPreCollectionTime(waitDateList.get(i));
                collectionList.add(collection);
            }

            //异步解冻操作
//            FreezeRequest request = new FreezeRequest();
//            request.setOrderNo("");
//            request.setAmount(borrowTender.getAccount());
//            request.setFreezeOrderNo(borrowTender.getOrderId());
//            creditAssignmentProvider.sendToUnFreeze(request);
        });


        //批量插入回款计划表
        if(!CollectionUtils.isEmpty(collectionList)) {
            borrowCollectionMapper.batchInsert(collectionList);
        }
        //更新用户投标状态
        borrowTenderMapper.updateTenderStatusBatch(tenderList, TenderStatus.BID_SUCCESS.getValue(),
                null,null);

        //将转让人的投资信息改为已转让
        BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(borrowTransfer.getTenderId());
        borrowTender.setStatus(TenderStatus.TRANSFER_SUCCESS.getValue());
        borrowTender.setUpdateTime(now);
        borrowTenderMapper.updateByPrimaryKeySelective(borrowTender);


        for(int i = 0; i < tenderList.size(); i++) {
            BorrowTender tender = tenderList.get(i);


            //平台把手续费给受让人
            if(borrowTransfer.getToSource() == 1) {
                String orderNo = "fs_" + borrowTransfer.getPreBorrowId() + "_transfer" + generateService.get() +"_transfer";

                PlatformTransferMqInfo platformTransferRequest = new PlatformTransferMqInfo();
                platformTransferRequest.setUserId(tender.getUserId());
                platformTransferRequest.setRemark("受让人获取标的转让手续费");
                platformTransferRequest.setOrderNo(orderNo);
                platformTransferRequest.setAmount(tender.getTenderTranAward());
                platformTransferRequest.setBorrowId(borrowTransfer.getPreBorrowId());

                //受让人 手续费资金记录
                AccountLog accountLog = new AccountLog();
                accountLog.setUserId(tender.getUserId());
                accountLog.setType(AccountLogType.TRANSFER_AWARD_FEE.getType());
                accountLog.setChangeType(AccountLogType.TRANSFER_AWARD_FEE.getCode());
                accountLog.setOrderNo(orderNo);
                accountLog.setAccount(tender.getTenderTranAward());
                accountLog.setToAccount(tender.getUserId());
                accountLog.setFromAccount("");
                accountLog.setFromRemarks("平台自有账户");
                accountLog.setToRemarks("受让人获取标的[" + borrowTransfer.getBorrowName() + "]的转让手续费");
                accountLog.setRemarks("受让人获取标的[" + borrowTransfer.getBorrowName() + "]的转让手续费");
                accountLog.setFeeAccount(tender.getTenderTranAward());
                accountLogService.updateAccountAndLogPro(accountLog);

                //平台自有账户 手续费资金记录
                AccountLog platAccountLog = new AccountLog();
                platAccountLog.setUserId(tender.getUserId());
                platAccountLog.setType(AccountLogType.TRANSFER_AWARD_FEE.getType());
                platAccountLog.setChangeType(AccountLogType.TRANSFER_AWARD_FEE.getCode());
                platAccountLog.setOrderNo(orderNo);
                platAccountLog.setAccount(tender.getTenderTranAward().negate());
                platAccountLog.setToAccount(tender.getUserId());
                platAccountLog.setFromAccount("");
                platAccountLog.setFromRemarks("平台自有账户");
                platAccountLog.setToRemarks("受让人获取标的[" + borrowTransfer.getBorrowName() + "]的转让手续费");
                platAccountLog.setRemarks("受让人获取标的[" + borrowTransfer.getBorrowName() + "]的转让手续费");
                accountLog.setFeeAccount(tender.getTenderTranAward().negate());
                accountLogService.updateAccountAndLogPro(platAccountLog);

                creditAssignmentProvider.sendToPlatformTransfer(platformTransferRequest,
                        DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_TRANSFER_VALUE);
            }

        }

        String orderNo = "";
        //转让人 扣除手续费 资金记录
        AccountLog accountLog = new AccountLog();
        accountLog.setUserId(borrowTransfer.getUserId());
        accountLog.setType(AccountLogType.TRANSFER_FEE.getType());
        accountLog.setChangeType(AccountLogType.TRANSFER_FEE.getCode());
        accountLog.setOrderNo(orderNo);
        accountLog.setAccount(borrowTransfer.getFee());
        accountLog.setToAccount("");
        accountLog.setFromAccount(borrowTransfer.getUserId());
        accountLog.setRemarks("债权转让[" + borrowTransfer.getBorrowName() + "]扣除手续费");
        accountLog.setFromRemarks("债权转让[" + borrowTransfer.getBorrowName() + "]扣除手续费");
        accountLog.setToRemarks("债权转让[" + borrowTransfer.getBorrowName() + "]扣除手续费");
        accountLog.setFeeAccount(borrowTransfer.getFee().negate());
        accountLogService.updateAccountAndLogPro(accountLog);

        //删除缓存
        creditAssignmentCache.delTransferUnFreeze(borrowTransfer.getId());
        creditAssignmentCache.delTransferDebt(borrowTransfer.getId());
    }

}
