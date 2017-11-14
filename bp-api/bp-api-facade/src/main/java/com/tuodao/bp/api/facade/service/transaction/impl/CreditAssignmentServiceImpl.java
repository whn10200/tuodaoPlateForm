package com.tuodao.bp.api.facade.service.transaction.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.client.useraccount.AccountClient;
import com.tuodao.bp.api.facade.service.transaction.BorrowCollectionService;
import com.tuodao.bp.cache.basic.ConfigDictionaryCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.cache.constant.DictionaryConstants;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.input.RepayQueryInput;
import com.tuodao.bp.model.business.product.output.RepayQueryOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.*;
import com.tuodao.bp.utils.*;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.transaction.AccountLogClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowCollectionClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowTenderClient;
import com.tuodao.bp.api.facade.client.transaction.CreditAssignmentClient;
import com.tuodao.bp.api.facade.client.useraccount.UserClient;
import com.tuodao.bp.api.facade.service.transaction.CreditAssignmentService;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description 债权转让
 */
@Service
public class CreditAssignmentServiceImpl implements CreditAssignmentService {

    private static final Logger logger = LoggerFactory.getLogger(CreditAssignmentServiceImpl.class);

    @Autowired
    BorrowTenderClient borrowTenderClient;

    @Autowired
    CreditAssignmentClient creditAssignmentClient;

    @Autowired
    BorrowCollectionClient borrowCollectionClient;

    @Autowired
    BorrowCollectionService borrowCollectionService;

    @Autowired
    ProductClient productClient;

    @Autowired
    UserClient userClient;

    @Autowired
    AccountLogClient accountLogClient;

    @Autowired
    UserDiscountsClient userDiscountsClient;

    @Autowired
    UserAccountCache userAccountCache;

    @Autowired
    AccountClient accountClient;

    @Autowired
    ConfigDictionaryCache configDictionaryCache;

    @Autowired
    RepayClient repayClient;




    @Value("${transaction.center.credit_assignment.update_time}")
    private long updateTime;

    @Override
    public CreditAssignmentInfoVo info(Integer tenderId) {
        TenderInput tenderInput = new TenderInput();
        tenderInput.setTenderId(tenderId);
        BorrowTenderOutput borrowTenderOutput = borrowTenderClient.findById(tenderInput);

        ProductOutput productOutput = productClient.getProduct(borrowTenderOutput.getBorrowId());
        boolean zqFlag = false;
        BigDecimal currentInterest = BigDecimal.ZERO;
        if(borrowTenderOutput.getTenderType() == 1) {
            zqFlag = true;
            TenderTransferInput tenderTransferInput = new TenderTransferInput();
            tenderTransferInput.setTenderId(1);
            TenderTransferOutput tenderTransferOutput = creditAssignmentClient.info(tenderTransferInput);
            if(tenderTransferOutput != null) {
                productOutput = productClient.getProduct(tenderTransferOutput.getPreBorrowId());
            }
        }

        CreditAssignmentInfoVo creditAssignmentInfoVo = new CreditAssignmentInfoVo();
        creditAssignmentInfoVo.setBorrowId(productOutput.getId());
        if(productOutput != null) {
            creditAssignmentInfoVo.setTenderId(tenderId);
            creditAssignmentInfoVo.setProductTitle(productOutput.getProductTitle());
            creditAssignmentInfoVo.setBorrowApr(productOutput.getBorrowApr());
            //区别新旧zq  是否需要加上平台奖励利率
            if(productOutput.getAuditTime().getTime() > updateTime && productOutput.getAwardScale() != null) {
                creditAssignmentInfoVo.setAwardScale(productOutput.getAwardScale());
            }

            UserBackMoneyInput input = new UserBackMoneyInput();
            input.setTenderId(1);
            //剩余期数
            BorrowCollectionCapitalOutput borrowCollectionCapitalOutput = borrowCollectionClient.getBackPeriod(input);

            if(borrowCollectionCapitalOutput != null) {
                creditAssignmentInfoVo.setPeriod(borrowCollectionCapitalOutput.getSumCount());
                //可转金额
                creditAssignmentInfoVo.setAccount(borrowCollectionCapitalOutput.getPreCapital());

                RepayQueryInput repayQueryInput = new RepayQueryInput();
                repayQueryInput.setBorrowId(productOutput.getId());
                repayQueryInput.setStatus(1);
                List<RepayQueryOutput> repayQueryOutputList = repayClient.getRepayList(repayQueryInput);
                if(CollectionUtils.isEmpty(repayQueryOutputList)) {
                    throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_END);
                }

                RepayQueryOutput repayQueryOutput = repayQueryOutputList.stream()
                        .sorted(Comparator.comparing(RepayQueryOutput :: getRepayTime).reversed())
                        .findFirst()
                        .get();
                long day = DateUtil.diffDay(repayQueryOutput.getRepayTime(), new Date());
                if(day < 0L) {
                    day = 0L;
                }
//                BigDecimal dayInterest = borrowCollectionCapitalOutput.getRepayInterest().divide(new BigDecimal(30));
//                BigDecimal interest = dayInterest.multiply(new BigDecimal(day));
                //当期利息
                currentInterest = borrowCollectionCapitalOutput.getCurrentInterest();

                currentInterest = currentInterest.multiply(new BigDecimal(day)).divide(new BigDecimal(30));
                currentInterest = borrowCollectionCapitalOutput.getInterest().subtract(currentInterest);

                //债权价值 剩余本金+剩余利息(不包含加息卷的剩余利息)
                creditAssignmentInfoVo.setTransferWorth(borrowCollectionCapitalOutput.getPreCapital().add(currentInterest));
            }

            //手续费
            BigDecimal fee = this.calculateFee(creditAssignmentInfoVo.getAccount(), productOutput.getAuditTime());
            creditAssignmentInfoVo.setFee(fee);
            //转让亏盈 已经获取利息+当前应收利息-手续费
            creditAssignmentInfoVo.setProfit(borrowCollectionCapitalOutput.getInterest().add(currentInterest).subtract(fee));
        }
        return creditAssignmentInfoVo;
    }


    @Override
    public void applyTransfer(SaveTransferParam saveTransferParam) {
        TenderInput tenderInput = new TenderInput();
        tenderInput.setTenderId(saveTransferParam.getTenderId());

        BorrowTenderOutput borrowTenderOutput = borrowTenderClient.findById(tenderInput);
        //判断是否有投资记录
        if(borrowTenderOutput == null) {
            logger.info("not found tender recording, tenderId:{}, userId:{}",
                    saveTransferParam.getTenderId(), saveTransferParam.getUserId());
            throw new MicroServiceException(CreditAssignmentConstant.TENDER_IS_NULL);
        }

        TenderTransferInput tenderTransferInput = new TenderTransferInput();
        tenderTransferInput.setTenderId(saveTransferParam.getTenderId());
        TenderTransferOutput tenderTransferOutput = creditAssignmentClient.info(tenderTransferInput);
        //判断是否转让过
        if(tenderTransferOutput != null && tenderTransferOutput.getStatus() < 2) {
            logger.info("this transfer is transfered, tenderId:{}, userId:{}",
                    saveTransferParam.getTenderId(), saveTransferParam.getUserId());
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_READY);
        }

        //判断支付密码
        ValidatePayPwInput validatePayPwInput = new ValidatePayPwInput();
        validatePayPwInput.setPayPw(saveTransferParam.getPayPassword());
        validatePayPwInput.setUserId(saveTransferParam.getUserId());
        if(!accountClient.validatePayPw(validatePayPwInput)) {
            logger.info("pay password is error, pwd:{}, tenderId:{}, userId:{}", saveTransferParam.getPayPassword(),
                    saveTransferParam.getTenderId(), saveTransferParam.getUserId());
            throw new MicroServiceException(CreditAssignmentConstant.PAY_PWD_ERROR);
        }

        //判断转让金额大于100
        String minMoney = configDictionaryCache.getDictionaryName(DictionaryConstants.MIN_TENDER_MONEY);
        if(borrowTenderOutput.getAmount().compareTo(new BigDecimal(minMoney)) < 0) {
            logger.info("this transfer money is error, tenderId:{}, userId:{}",
                    saveTransferParam.getTenderId(), saveTransferParam.getUserId());
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_MONEY_ERROR);
        }

        ProductOutput productOutput = productClient.getProduct(borrowTenderOutput.getBorrowId());
        boolean zqFlag = false;
        BigDecimal currentInterest = BigDecimal.ZERO;
        if(borrowTenderOutput.getTenderType() == 1) {
            zqFlag = true;
            if(tenderTransferOutput != null) {
                productOutput = productClient.getProduct(tenderTransferOutput.getPreBorrowId());
            }
        }

        Date now = new Date();
        TransferSaveInput transferSaveInput = new TransferSaveInput();
        transferSaveInput.setUserId(saveTransferParam.getUserId());
        transferSaveInput.setTenderId(saveTransferParam.getTenderId());
        transferSaveInput.setPeriod(productOutput.getProductPeriod());
        transferSaveInput.setPeriodType(productOutput.getPeriodUnit());
        transferSaveInput.setBorrowName(productOutput.getProductTitle());
        transferSaveInput.setRaymentType(productOutput.getRefundWay());
        transferSaveInput.setApr(productOutput.getBorrowApr());
        transferSaveInput.setStatus(0);
        transferSaveInput.setAddTime(now);
        transferSaveInput.setEndTime(DateUtil.addHours(now, 48));

        UserBackMoneyInput userBackMoneyInput = new UserBackMoneyInput();
        userBackMoneyInput.setTenderId(saveTransferParam.getTenderId());
        BorrowCollectionCapitalOutput borrowCollectionCapitalOutput = borrowCollectionClient.getUnBackPeriod(userBackMoneyInput);
        if(borrowCollectionCapitalOutput != null) {
            //标的总额
            transferSaveInput.setAccount(borrowCollectionCapitalOutput.getCapital());

        }

        RepayQueryInput repayQueryInput = new RepayQueryInput();
        repayQueryInput.setBorrowId(productOutput.getId());
        repayQueryInput.setStatus(1);
        List<RepayQueryOutput> repayQueryOutputList = repayClient.getRepayList(repayQueryInput);
        if(CollectionUtils.isEmpty(repayQueryOutputList)) {
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_END);
        }

        RepayQueryOutput repayQueryOutput = repayQueryOutputList.stream()
                .sorted(Comparator.comparing(RepayQueryOutput :: getRepayTime).reversed())
                .findFirst()
                .get();
        long day = DateUtil.diffDay(repayQueryOutput.getRepayTime(),new Date());
        if(day < 0L) {
            day = 0L;
        }

        if(productOutput.getAuditTime().getTime() > updateTime) {
            transferSaveInput.setPlatformApr(productOutput.getAwardScale());
        }

        //手续费
        BigDecimal fee = this.calculateFee(transferSaveInput.getAccount(), productOutput.getAuditTime());
        transferSaveInput.setFee(fee);
        //原始标的
        transferSaveInput.setPreBorrowId(borrowTenderOutput.getBorrowId());

        //给转让人的利息
        BigDecimal interest = borrowCollectionCapitalOutput.getCurrentInterest().multiply(new BigDecimal(day)).divide(new BigDecimal(30));
        //未获得利息减去当期利息
        BigDecimal transferWorth = borrowCollectionCapitalOutput.getUnInterest().subtract(interest);
        //转让价值
        transferSaveInput.setTransferWorth(transferWorth);
        transferSaveInput.setToSource(0);
        //转让盈亏  已经获取利息+当前应收利息-手续费
        transferSaveInput.setProfit(borrowCollectionCapitalOutput.getInterest().add(interest).subtract(fee));
        transferSaveInput.setReturnInterest(interest);
        creditAssignmentClient.save(transferSaveInput);

    }

    @Override
    public PageInfo<CreditAssignmentVo> pageByTransferableList(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentVo> creditAssignmentVoPageInfo = new PageInfo<>();

        PageInfo<CreditAssignmentOutput> pageInfo = creditAssignmentClient.pageByTransferableList(input);
        List<CreditAssignmentOutput> list = pageInfo.getList();

        if(CollectionUtils.isEmpty(list)) {
            return creditAssignmentVoPageInfo;
        }

        List<Integer> borrowIdList = FluentIterable.from(list).transform(new Function<CreditAssignmentOutput, Integer>() {
            @Override
            public Integer apply(CreditAssignmentOutput creditAssignmentOutput) {
                return creditAssignmentOutput.getBorrowId();
            }
        }).toList();

        //调用产品接口
        List<ProductOutput> productOutputList = productClient.getListByIdList(borrowIdList);

        if(!CollectionUtils.isEmpty(productOutputList)) {
            Map<Integer, ProductOutput> map = Maps.uniqueIndex(productOutputList, new Function<ProductOutput, Integer>() {
                @Override
                public Integer apply(ProductOutput productOutput) {
                    return productOutput.getId();
                }
            });
            list.forEach(creditAssignmentOutput -> {
                ProductOutput productOutput = map.get(creditAssignmentOutput.getBorrowId());
                if(productOutput == null) {
                    return;
                }
                creditAssignmentOutput.setProductTitle(productOutput.getProductTitle());
                creditAssignmentOutput.setProductCode(productOutput.getProductCode());
            });
        }

        List<CreditAssignmentVo> creditAssignmentVoList = TransferUtil.transferList(list, CreditAssignmentVo.class);
        creditAssignmentVoPageInfo.setList(creditAssignmentVoList);
        creditAssignmentVoPageInfo.setTotal(pageInfo.getTotal());
        return creditAssignmentVoPageInfo;
    }

    @Override
    public PageInfo<CreditAssignmentVo> pageByTransferList(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentVo> creditAssignmentVoPageInfo = new PageInfo<>();
        List<CreditAssignmentVo> creditAssignmentVoList = new ArrayList<>();

        PageInfo<BorrowTransferOutput> borrowTransferOutputPageInfo = creditAssignmentClient.pageByTransferList(input);
        List<BorrowTransferOutput> list = borrowTransferOutputPageInfo.getList();

        if(CollectionUtils.isEmpty(list)) {
            return creditAssignmentVoPageInfo;
        }

        List<Integer> borrowIdList = FluentIterable.from(list).transform(new Function<BorrowTransferOutput, Integer>() {
            @Override
            public Integer apply(BorrowTransferOutput borrowTransferOutput) {
                return borrowTransferOutput.getBorrowId();
            }
        }).toList();

        //调用产品接口
        List<ProductOutput> productOutputList = productClient.getListByIdList(borrowIdList);

        if(!CollectionUtils.isEmpty(productOutputList)) {
            Map<Integer, ProductOutput> map = Maps.uniqueIndex(productOutputList, new Function<ProductOutput, Integer>() {
                @Override
                public Integer apply(ProductOutput productOutput) {
                    return productOutput.getId();
                }
            });
            list.forEach(borrowTransferOutput -> {
                ProductOutput productOutput = map.get(borrowTransferOutput.getPreBorrowId());
                if(productOutput == null) {
                    return;
                }
                CreditAssignmentVo creditAssignmentVo = new CreditAssignmentVo();
                TransferUtil.transfer(borrowTransferOutput, creditAssignmentVo);

                if(productOutput.getAuditTime().getTime() > updateTime && productOutput.getBorrowApr() != null) {
                    creditAssignmentVo.setBorrowApr(productOutput.getBorrowApr().add(productOutput.getAwardScale()));
                } else {
                    creditAssignmentVo.setBorrowApr(productOutput.getBorrowApr());
                }

                creditAssignmentVo.setProductTitle(productOutput.getProductTitle());
                creditAssignmentVo.setProductCode(productOutput.getProductCode());
                creditAssignmentVoList.add(creditAssignmentVo);
            });
        }

        creditAssignmentVoPageInfo.setList(creditAssignmentVoList);
        creditAssignmentVoPageInfo.setTotal(borrowTransferOutputPageInfo.getTotal());
        return creditAssignmentVoPageInfo;
    }

    @Override
    public PageInfo<CreditAssignmentVo> pageByTransferee(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentVo> creditAssignmentVoPageInfo = new PageInfo<>();
        List<CreditAssignmentVo> creditAssignmentVoList = new ArrayList<>();

        PageInfo<BorrowTransferOutput> borrowTransferOutputPageInfo = creditAssignmentClient.pageByTransferee(input);
        List<BorrowTransferOutput> list = borrowTransferOutputPageInfo.getList();

        if(CollectionUtils.isEmpty(list)) {
            return creditAssignmentVoPageInfo;
        }

        List<Integer> borrowIdList = FluentIterable.from(list).transform(p -> p.getBorrowId()).toList();

        //调用产品接口
        List<ProductOutput> productOutputList = productClient.getListByIdList(borrowIdList);

        if(!CollectionUtils.isEmpty(productOutputList)) {
            Map<Integer, ProductOutput> map = Maps.uniqueIndex(productOutputList, new Function<ProductOutput, Integer>() {
                @Override
                public Integer apply(ProductOutput productOutput) {
                    return productOutput.getId();
                }
            });
            list.forEach(borrowTransferOutput -> {
                ProductOutput productOutput = map.get(borrowTransferOutput.getBorrowId());
                if(productOutput == null) {
                    return;
                }
                CreditAssignmentVo creditAssignmentVo = new CreditAssignmentVo();
                TransferUtil.transfer(borrowTransferOutput, creditAssignmentVo);


                creditAssignmentVo.setProductTitle(productOutput.getProductTitle());
                creditAssignmentVo.setProductCode(productOutput.getProductCode());
                creditAssignmentVoList.add(creditAssignmentVo);
            });
        }
        creditAssignmentVoPageInfo.setList(creditAssignmentVoList);
        creditAssignmentVoPageInfo.setTotal(borrowTransferOutputPageInfo.getTotal());
        return creditAssignmentVoPageInfo;
    }

    @Override
    public PageInfo<TransferCompromisedVo> pageByCompromised(PagePojo pagePojo) {
        PageInfo<TransferCompromisedVo> compromisedVoPageInfo = new PageInfo<>();
        List<TransferCompromisedVo> list = new ArrayList<>();

        CreditAssignmentInput input = new CreditAssignmentInput();
        TransferUtil.transfer(pagePojo, input);
        input.setStatus(3);
        PageInfo<BorrowTransferOutput> borrowTransferOutputPageInfo = creditAssignmentClient.pageByTransferee(input);
        List<BorrowTransferOutput> transferOutputList = borrowTransferOutputPageInfo.getList();
        if(CollectionUtils.isEmpty(list)) {
            return compromisedVoPageInfo;
        }
        List<Integer> borrowIdList = FluentIterable.from(transferOutputList).transform(p -> p.getBorrowId()).toList();
        //调用产品接口
        List<ProductOutput> productOutputList = productClient.getListByIdList(borrowIdList);
        if(!CollectionUtils.isEmpty(productOutputList)) {
            Map<Integer, ProductOutput> map = Maps.uniqueIndex(productOutputList, new Function<ProductOutput, Integer>() {
                @Override
                public Integer apply(ProductOutput productOutput) {
                    return productOutput.getId();
                }
            });
            transferOutputList.forEach(borrowTransferOutput -> {
                ProductOutput productOutput = map.get(borrowTransferOutput.getBorrowId());
                if(productOutput == null) {
                    return;
                }
                TransferCompromisedVo transferCompromisedVo = new TransferCompromisedVo();
                transferCompromisedVo.setTitle(productOutput.getProductTitle());
                transferCompromisedVo.setProductCode(productOutput.getProductCode());
                transferCompromisedVo.setAmount(borrowTransferOutput.getAccount());
                transferCompromisedVo.setInterest(borrowTransferOutput.getInterest());
                transferCompromisedVo.setStatus(borrowTransferOutput.getStatus());
                transferCompromisedVo.setAddTime(borrowTransferOutput.getAddTime());
                transferCompromisedVo.setEndTime(productOutput.getRepayLastTime());

                list.add(transferCompromisedVo);
            });
        }
        compromisedVoPageInfo.setList(list);
        compromisedVoPageInfo.setTotal(borrowTransferOutputPageInfo.getTotal());
        return compromisedVoPageInfo;
    }

    @Override
    public PageInfo<TransferCompromisedVo> pageByTransferLog(PagePojo pagePojo) {
        PageInfo<TransferCompromisedVo> compromisedVoPageInfo = new PageInfo<>();
        List<TransferCompromisedVo> list = new ArrayList<>();

        CreditAssignmentInput input = new CreditAssignmentInput();
        TransferUtil.transfer(pagePojo, input);
        input.setStatus(2);
        PageInfo<BorrowTransferOutput> borrowTransferOutputPageInfo = creditAssignmentClient.pageByTransferList(input);
        List<BorrowTransferOutput> transferOutputList = borrowTransferOutputPageInfo.getList();
        if(CollectionUtils.isEmpty(list)) {
            return compromisedVoPageInfo;
        }
        List<Integer> borrowIdList = FluentIterable.from(transferOutputList).transform(p -> p.getBorrowId()).toList();
        //调用产品接口
        List<ProductOutput> productOutputList = productClient.getListByIdList(borrowIdList);
        if(!CollectionUtils.isEmpty(productOutputList)) {
            Map<Integer, ProductOutput> map = Maps.uniqueIndex(productOutputList, new Function<ProductOutput, Integer>() {
                @Override
                public Integer apply(ProductOutput productOutput) {
                    return productOutput.getId();
                }
            });
            transferOutputList.forEach(borrowTransferOutput -> {
                ProductOutput productOutput = map.get(borrowTransferOutput.getBorrowId());
                if(productOutput == null) {
                    return;
                }
                TransferCompromisedVo transferCompromisedVo = new TransferCompromisedVo();
                transferCompromisedVo.setTitle(productOutput.getProductTitle());
                transferCompromisedVo.setProductCode(productOutput.getProductCode());
                transferCompromisedVo.setAmount(borrowTransferOutput.getAccount());
                transferCompromisedVo.setStatus(borrowTransferOutput.getStatus());
                transferCompromisedVo.setAddTime(borrowTransferOutput.getAddTime());
                transferCompromisedVo.setEndTime(productOutput.getRepayLastTime());

                list.add(transferCompromisedVo);
            });
        }
        compromisedVoPageInfo.setList(list);
        compromisedVoPageInfo.setTotal(borrowTransferOutputPageInfo.getTotal());
        return compromisedVoPageInfo;
    }

    @Override
    public PageInfo<TransferableVo> pageByTransferable(PagePojo pagePojo) {
        PageInfo<TransferableVo> transferableVoPageInfo = new PageInfo<>();

        CreditAssignmentInput input = new CreditAssignmentInput();
        TransferUtil.transfer(pagePojo, input);
        input.setStatus(0);
        PageInfo<CreditAssignmentOutput> pageInfo = creditAssignmentClient.pageByTransferableList(input);
        List<CreditAssignmentOutput> list = pageInfo.getList();

        if(CollectionUtils.isEmpty(list)) {
            return transferableVoPageInfo;
        }

        List<Integer> borrowIdList = FluentIterable.from(list).transform(new Function<CreditAssignmentOutput, Integer>() {
            @Override
            public Integer apply(CreditAssignmentOutput creditAssignmentOutput) {
                return creditAssignmentOutput.getBorrowId();
            }
        }).toList();

        //调用产品接口
        List<ProductOutput> productOutputList = productClient.getListByIdList(borrowIdList);

        List<TransferableVo> creditAssignmentVoList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(productOutputList)) {
            Map<Integer, ProductOutput> map = Maps.uniqueIndex(productOutputList, new Function<ProductOutput, Integer>() {
                @Override
                public Integer apply(ProductOutput productOutput) {
                    return productOutput.getId();
                }
            });
            list.forEach(creditAssignmentOutput -> {
                ProductOutput productOutput = map.get(creditAssignmentOutput.getBorrowId());
                if(productOutput == null) {
                    return;
                }
                TransferableVo transferableVo = new TransferableVo();
                transferableVo.setId(creditAssignmentOutput.getTenderId());
                transferableVo.setTitle(productOutput.getProductTitle());
                transferableVo.setProductCode(productOutput.getProductCode());
                transferableVo.setAmount(creditAssignmentOutput.getRecoverCapital());
                transferableVo.setRemaining(creditAssignmentOutput.getRemaining());
                transferableVo.setApr(creditAssignmentOutput.getBorrowApr() + "%");
                creditAssignmentVoList.add(transferableVo);
            });
        }

        transferableVoPageInfo.setList(creditAssignmentVoList);
        transferableVoPageInfo.setTotal(pageInfo.getTotal());
        return transferableVoPageInfo;
    }

    @Override
    public TransferVo queryInfo(TransferQueryInfoParam transferQueryInfoParam) {
        TransferQueryInput transferQueryInput = new TransferQueryInput();
        transferQueryInput.setTransferId(transferQueryInfoParam.getTransferId());
        BorrowTransferInfoOutput borrowTransferQueryOutput = creditAssignmentClient.queryInfo(transferQueryInput);
        if(borrowTransferQueryOutput == null) {
            logger.error("query transfer info error, transferId:{}", transferQueryInfoParam.getTransferId());
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_NULL);
        }

        ProductOutput productOutput = productClient.getProduct(transferQueryInfoParam.getTransferId());

        BasePojo basePojo = new BasePojo();
        basePojo.setUserId(borrowTransferQueryOutput.getUserId());
        UserAccountInfo userAccountInfo = userClient.getUserAccountInfo(basePojo);

        TransferVo transferQueryInfoVo = new TransferVo();
        TransferUtil.transfer(borrowTransferQueryOutput, transferQueryInfoVo);
        transferQueryInfoVo.setProductCode("NO." + productOutput.getProductCode());
        transferQueryInfoVo.setRemainingTime(TimeUtils.dateDiff1(transferQueryInfoVo.getEndTime(), new Date()));
        if(userAccountInfo != null) {

            String phone = userAccountInfo.getMobile();
            phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            if(Objects.equals(transferQueryInfoParam.getRequestType(), AccessType.APP.name())) {
                transferQueryInfoVo.setUserPhone("用户" + phone);
            } else {
                transferQueryInfoVo.setUserPhone(phone);
            }
        }

        String beginTime = configDictionaryCache.getDictionaryName(DictionaryConstants.DEPOSIT_CLENT_BEGIN_TIME);
        String endTime = configDictionaryCache.getDictionaryName(DictionaryConstants.DEPOSIT_CLENT_END_TIME);
        if(TimeUtils.between(new Date(), TimeUtils.convertToDate(beginTime), TimeUtils.convertToDate(endTime))) {
            transferQueryInfoVo.setCleanTime(true);
        }

        transferQueryInfoVo.setTimeLeft(borrowTransferQueryOutput.getEndTime().getTime() - System.currentTimeMillis());

        return transferQueryInfoVo;
    }

    @Override
    public TransferQueryInfoVo queryTransferInfo(TransferQueryInfoParam transferQueryInfoParam) {
        TransferQueryInput transferQueryInput = new TransferQueryInput();
        transferQueryInput.setTransferId(transferQueryInfoParam.getTransferId());
        BorrowTransferQueryOutput borrowTransferQueryOutput = creditAssignmentClient.queryInfo(transferQueryInput);
        if(borrowTransferQueryOutput == null) {
            logger.error("query transfer info error, transferId:{}", transferQueryInfoParam.getTransferId());
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_NULL);
        }

        ProductOutput productOutput = productClient.getProduct(borrowTransferQueryOutput.getPreBorrowId());
        if(productOutput == null) {
            logger.error("query product info error, trnasferId:{}", transferQueryInfoParam.getTransferId());
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_NULL);
        }

        TransferQueryInfoVo transferQueryInfoVo = new TransferQueryInfoVo();
        TransferUtil.transfer(borrowTransferQueryOutput, transferQueryInfoVo);
        transferQueryInfoVo.setTitle(productOutput.getProductTitle());
        transferQueryInfoVo.setProductCode("NO." + productOutput.getProductCode());
        transferQueryInfoVo.setId(productOutput.getId());
        transferQueryInfoVo.setApr(borrowTransferQueryOutput.getApr() + "%");
        return transferQueryInfoVo;
    }

    @Override
    public TransferableInfoVo queryTransferableInfo(TenderDetailParam param) {
        TransferableInfoVo transferableVo = new TransferableInfoVo();
        BorrowTenderOutput tenderOutput = borrowTenderClient.getUserBorrowTenderById(param);

        ProductOutput productOutput = productClient.getProduct(tenderOutput.getBorrowId());
        if(productOutput == null) {
            logger.error("query tender info error, tenderId:{}", param.getTenderId());
            throw new MicroServiceException(CreditAssignmentConstant.TRANSFER_IS_NULL);
        }
        TransferUtil.transfer(tenderOutput, transferableVo);
        transferableVo.setEndTime(productOutput.getRepayLastTime());
        transferableVo.setTitle(productOutput.getProductTitle());
        transferableVo.setProductCode(productOutput.getProductCode());
        transferableVo.setTenderId(param.getTenderId());

        if(productOutput.getRefundWay() == 0) {
            transferableVo.setStyleType("等额本息");
        } else if(productOutput.getRefundWay() == 2) {
            transferableVo.setStyleType("按月付息");
        } else if(productOutput.getRefundWay() == 3) {
            transferableVo.setStyleType("按天付息");
        }
        if(Objects.isNull(tenderOutput.getVoucherId())) {
            transferableVo.setVoucher(BigDecimalUtils.centToYuan(tenderOutput.getVoucherMoney()) + "元抵扣券");
        }
        if(Objects.isNull(tenderOutput.getVoucherCouponId())) {
            transferableVo.setVoucher(BigDecimalUtils.centToYuan(tenderOutput.getVoucherCouponMoney()) + "%加息券");
        }
        transferableVo.setInterest(Objects.isNull(tenderOutput.getPlatformInterest()) ?
                BigDecimalUtils.centToYuan(tenderOutput.getAccountInterest()) :
                BigDecimalUtils.centToYuan(tenderOutput.getPlatformInterest().add(tenderOutput.getAccountInterest())));

        UserBackMoneyInput userBackMoneyInput = new UserBackMoneyInput();
        TransferUtil.transfer(param, userBackMoneyInput);

        List<BackMoneyPlanVo> backMoneyPlanVoList = borrowCollectionService.getByTenderId(userBackMoneyInput);
        List<BackMoneyPlanAppVo> list = TransferUtil.transferList(backMoneyPlanVoList, BackMoneyPlanAppVo.class);
        transferableVo.setBackPlan(list);
        return transferableVo;
    }

    @Override
    public TenderTransferInfoVo tenderTransfer(TenderTransferParam param) {
        TransferTenderInput input = new TransferTenderInput();

        //调用获取券接口
        if(!Objects.isNull(param.getVoucherId()) && param.getVoucherId() != 0L) {
            IdInput idInput = new IdInput();
            idInput.setId(param.getVoucherId());
            UserDiscountOutput userDiscountOutput = userDiscountsClient.getUserDiscountById(idInput);
            //判断加息券是否有效
            if (userDiscountOutput.getDisStatus() != 1) {
                logger.info("coupons is used, couponsId:{}", param.getVoucherId());
                throw new MicroServiceException(CreditAssignmentConstant.COUPONS_IS_USED);
            }
            //判断是否加息券
            if(userDiscountOutput.getDiscountType() != 2) {
                logger.info("discount type is error, userId:{}, couponsId:{}", param.getUserId(), param.getVoucherId());
                throw new MicroServiceException(CreditAssignmentConstant.COUPONS_IS_USED);
            }

            input.setVoucherId(param.getVoucherId());
            input.setDiscountAvailable(new BigDecimal(userDiscountOutput.getDiscountAvailable()));
        }


        //验证密码是否正确
        ValidatePayPwInput validatePayPwInput = new ValidatePayPwInput();
        validatePayPwInput.setUserId(param.getUserId());
        validatePayPwInput.setPayPw(param.getPayPassword());
        if(!accountClient.validatePayPw(validatePayPwInput)) {
            logger.info("pay password is error, pwd:{}, tenderId:{}, userId:{}", param.getPayPassword(),
                    param.getTransferId(), param.getUserId());
            throw new MicroServiceException(CreditAssignmentConstant.PWD_IS_ERROR);
        }

        TransferUtil.transfer(param, input);
        BorrowTenderOutput borrowTenderOutput = creditAssignmentClient.tender(input);
        ProductOutput productOutput = productClient.getProduct(borrowTenderOutput.getBorrowId());
        TenderTransferInfoVo tenderTransferInfoVo = new TenderTransferInfoVo();
        tenderTransferInfoVo.setTitle(productOutput.getProductTitle());
        tenderTransferInfoVo.setAccount(BigDecimalUtils.centToYuan(borrowTenderOutput.getPreAccount()));
        if(!Objects.isNull(borrowTenderOutput.getPlatformInterest())) {
            tenderTransferInfoVo.setInterest(BigDecimalUtils.centToYuan(borrowTenderOutput.getPlatformInterest()
                    .add(BigDecimalUtils.centToYuan(borrowTenderOutput.getAccountInterest()))));
        } else {
            tenderTransferInfoVo.setInterest(BigDecimalUtils.centToYuan(borrowTenderOutput.getAccountInterest()));
        }

        if(!Objects.isNull(borrowTenderOutput.getVoucherCouponId())) {
            tenderTransferInfoVo.setVoucher("加息券" + borrowTenderOutput.getVoucherCouponMoney() + "%");
        }

        if(!Objects.isNull(borrowTenderOutput.getVoucherId())) {
            tenderTransferInfoVo.setVoucher("抵扣券" + borrowTenderOutput.getVoucherMoney() + "元");
        }
        return tenderTransferInfoVo;

    }


    /**
     * 手续费计算
     * @param amount  金额
     * @param auditTime 时间
     * @return
     */
    private BigDecimal calculateFee(BigDecimal amount, Date auditTime) {
        //手续费 三个月后千分之二 否则千分之五
        BigDecimal fee = new BigDecimal(100);
        if(DateUtil.subDay(new Date(), 90).after(auditTime)) {
            fee = amount.multiply(new BigDecimal(2).divide(new BigDecimal(1000)));
            if(fee.compareTo(new BigDecimal(1)) < 0) {
                fee = new BigDecimal(1);
            }
        } else {
            fee = amount.multiply(new BigDecimal(5).divide(new BigDecimal(1000)));
            if(fee.compareTo(new BigDecimal(1)) < 0) {
                fee = new BigDecimal(1);
            }
        }
        return fee;
    }


    public void test(String userId, Integer tenderId) {

        //查询未回款计划,根据用户id和投标id

        //根据实际转让时间，重新设置参数。
        //获取当前日期减去上期还款日期 天数

        //修改回款计划表已转让，并且修改当期的回款本金和利息


//        modifyCollection

        //获取原产品信息
        ProductOutput productOutput = productClient.getProduct(0);

        //获取投资信息



        //判断时间 1496912817 计算利息
        //插入回款计划
        //调用vip等级接口
        //修改投标信息
        //添加资金记录


        //根据债转投标id查询投资信息
        //更新投资信息，status已经转让，account_interest总收益，update_time

        //调用存管




    }
}
