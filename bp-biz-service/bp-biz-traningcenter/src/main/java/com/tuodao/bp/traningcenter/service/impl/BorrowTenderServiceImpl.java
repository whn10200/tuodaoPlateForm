package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.model.*;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import com.tuodao.bp.model.business.traningcenter.output.UnderTenderOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.common.UserConstant;
import com.tuodao.bp.model.constant.operation.UserDiscountConstant;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.enums.ChannelType;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.BorrowParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderDetailParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderListParam;

import com.tuodao.bp.model.facade.traningcenter.output.ProductTenderVo;
import com.tuodao.bp.model.mq.TenderAccountMqInfo;
import com.tuodao.bp.model.traningcenter.input.RecheckInput;
import com.tuodao.bp.model.traningcenter.input.UserCollectionInput;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.Account;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTenderQuery;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.pdf.GeneratePDF;
import com.tuodao.bp.traningcenter.service.*;
import com.tuodao.bp.utils.*;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @description: 投标
 * @author: 王艳兵
 * @date: 2017/9/14
 * @time: 14:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("borrowTenderService")
public class BorrowTenderServiceImpl implements BorrowTenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowTenderServiceImpl.class);


    @Autowired
    private BorrowTenderMapper borrowTenderMapper;

    @Autowired
    private AccountLogService accountLogService;

    @Autowired
    private ProjectInfoCache projectInfoCache;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ReturnsCache returnsCache;

    @Autowired
    private AutoTenderService autoTenderService;

    @Autowired
    private ProducerMq producerMq;

    @Autowired
    private BorrowCollectionService borrowCollectionService;


    @Autowired
    private BorrowChoicenessTenderMapper choicenessTenderMapper;

    @Autowired
    private ScoreTaskCache scoreTaskCache;

    @Autowired
    private AccountService accountService;

    @Autowired
    private GenerateService generateService;

    @Override
    public BigDecimal getBalanceCash(String userId, double tenderMoney) {
        AccountOutput account = accountService.getUserAccount(userId);
        //可能需要付手续费才能提现的金额 = 可用余额-充值金额-免费提现金额 简称付费金额
        double subtract = account.getBalance().subtract(account.getRecharge()).subtract(account.getBalanceCash()).doubleValue();
        //付费金额小于投标金额
        if(subtract < tenderMoney){
            double sub = BigDecimalUtils.sub(tenderMoney, subtract);
            //免费提现金额+付费提现金额 小于投标金额
            if(account.getBalanceCash().doubleValue() < sub){
                return account.getBalanceCash();
            }else{
                //免费提现够用
                return BigDecimal.valueOf(sub);
            }
        }
        return BigDecimal.valueOf(0D);
    }

    @Override
    public void tenderProducer(TenderExecutor executor) {
        //放入预投标的结果
        this.putTenderResult(executor.getTenderKey(),"投标处理中",TenderConstant.TENDER_RESULT_PROGRESS);
        /**
         * 如果优惠券不为空,则更新优惠券为已使用,等待优惠券更新成功后再放入投标队列,
         * 如果优惠券为空,则直接放入投标队列
         */
        if(executor.getDiscount() != null){
            producerMq.updateVoucherStatus(Integer.parseInt(executor.getDiscount().getId().toString()), UserDiscountConstant.VOUCHER_STATUS_USED,executor.getUser().getUserId(),executor);
        }else{
            producerMq.commonTender(executor);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tenderConsumer(TenderExecutor executor) {
        LOGGER.debug("投标队列消费者,入参:{}", executor);
        try {
            BorrowTender borrowTender = preHandler(executor);

            BorrowTenderOutput borrowTenderOutput = transfer(borrowTender);

            borrowTenderOutput.setTenderKey(executor.getTenderKey());

            producerMq.tenderBankRequest(executor.getProduct(),borrowTenderOutput,executor.getUser());

        }catch (Exception e){
            if(e instanceof BizFeignException){
                LOGGER.error("投标队列消费者异常:{}",e.getMessage());
                this.putTenderResult(executor.getTenderKey(),e.getMessage(),TenderConstant.TENDER_RESULT_FAIL);
            }else{
                throw e;
            }
        }
    }

    @Override
    public boolean isExists(String key){
        if(key == null){
            return true;
        }
        ReturnCacheInfo info =  returnsCache.getReturnInfo(key);

        return info != null;
    }
    /**
     * 添加投标成功的缓存结果
     * @param key 前台查询结果唯一key
     * @param borrowTender 投标信息
     */
    private void putTenderSuccessResult(String key,BorrowTender borrowTender){
        if(key != null){
            ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
            returnCacheInfo.setStatus(TenderConstant.TENDER_RESULT_SUCCESS);
            returnCacheInfo.setKey(key);
            returnCacheInfo.setContent("投标成功");
            //投标金额
            returnCacheInfo.setAmount(BigDecimalUtils.centToYuanFormat(borrowTender.getPreAccount().doubleValue()));
            //利息
            returnCacheInfo.setPreInterest(BigDecimalUtils.centToYuanFormat(borrowTender.getAccountInterest().doubleValue()));
            int voucherType = autoTenderService.getVoucherType(borrowTender);

            //0默认不使用券 1:使用抵用券 2:使用加息券
            if(voucherType != 0){
                //券类型
                returnCacheInfo.setVoucherType(voucherType);
                //券面值
                returnCacheInfo.setVoucherMoney(borrowTender.getVoucherMoney().doubleValue());
            }
            returnsCache.putReturnInfo(returnCacheInfo);
        }
    }

    /**
     * 投标结果放入缓存中
     * @param key 唯一查询key
     * @param msg 结果信息
     * @param status 结果状态
     */
    @Override
    public void putTenderResult(String key,String msg,int status){
        ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
        returnCacheInfo.setStatus(status);
        returnCacheInfo.setKey(key);
        returnCacheInfo.setContent(msg);
        returnsCache.putReturnInfo(returnCacheInfo);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tenderSuccessTask(String orderNo,String tenderKey) {
        BorrowTender borrowTender = borrowTenderMapper.getByOrderId(orderNo);
        if(borrowTender == null){
            LOGGER.error("定时任务处理投标结果成功,但未发现该笔订单号的投资信息,orderId:{}",orderNo);
            return;
        }

        if(borrowTender.getStatus() != TenderConstant.TENDER_FROST){
            LOGGER.error("定时任务处理投标结果成功,但该笔投标已被更新,borrowTender:{}",borrowTender);
            return;
        }
        /*
         * 成功时,无论前台用户是否已经关闭该界面,均将该结果存入缓存 因为有600s缓存过期时间限制
         */
        this.putTenderSuccessResult(tenderKey, borrowTender);

        //更新投标状态,计算预计收益等
        this.updateBorrowTenderStatus(borrowTender.getId(), TenderConstant.TENDER_SUCCESS);

        //自动投标需要做额外后置处理
        if(borrowTender.getTenderMode() == TenderConstant.TENDER_MODE_AUTO){
            autoTenderService.addAutoTenderSuccess(borrowTender);
        }
        updateProductResidue(borrowTender.getBorrowId(), borrowTender.getPreAccount());
        //TODO 发送短信通知

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tenderFailTask(String orderNo,String errorMsg,String tenderKey) {
        BorrowTender borrowTender = borrowTenderMapper.getByOrderId(orderNo);
        if(borrowTender == null){
            LOGGER.error("定时任务处理投标结果失败,但未发现该笔订单号的投资信息,orderId:{}",orderNo);
            return;
        }

        if(borrowTender.getStatus() != TenderConstant.TENDER_FROST){
            LOGGER.error("定时任务处理投标结果失败,但该笔投标已被更新,borrowTender:{}",borrowTender);
            return;
        }

        this.updateBorrowTenderFail(borrowTender,errorMsg);

        accountLogService.insertTenderFailLog(borrowTender, AccountLogType.TENDER_FAIL);

        //更新为 未使用
        if(borrowTender.getVoucherId() != null && borrowTender.getVoucherId() > 0){
            producerMq.updateVoucherStatus(borrowTender.getVoucherId(),UserDiscountConstant.VOUCHER_STATUS_UNUSED,borrowTender.getUserId(),null);
        }else if(borrowTender.getVoucherCouponId() != null && borrowTender.getVoucherCouponId() > 0){
            producerMq.updateVoucherStatus(borrowTender.getVoucherCouponId(),UserDiscountConstant.VOUCHER_STATUS_UNUSED,borrowTender.getUserId(),null);
        }
        /**
         * 不发送短信 则插入缓存结果以便于提供一种通知方式
         */
        boolean isSend = isExists(tenderKey);
        if(!isSend){
            this.putTenderResult(tenderKey, errorMsg, TenderConstant.TENDER_RESULT_FAIL);
        }
        //TODO 短信通知
    }


    /**
     * 更新标的剩余可投金额
     * @param productId 标的ID
     * @param tenderMoney 投标金额
     */
    @Override
    public void updateProductResidue(int productId,BigDecimal tenderMoney){
        ProjectInfoCacheInfo projectInfo = projectInfoCache.getProjectInfo(productId);
        projectInfo.setLeftAccount(projectInfo.getLeftAccount().subtract(tenderMoney));
        projectInfoCache.putProjectInfo(projectInfo);
        producerMq.updateProductResidueBalanceMQ(productId,tenderMoney);
    }



    /**
     * 投保预处理:参数校验,添加投标记录,冻结资金,更新资金记录
     * @param executor  投标队列传递过来的参数
     * @return 投标信息
     */
    private BorrowTender preHandler(TenderExecutor executor){
        verifyTender(executor.getUser().getUserId(), executor.getProduct().getId());
        verifyBorrow(executor.getProduct().getId(), executor.getTenderMoney());
        verifyTenderAccount(executor.getUser().getUserId(), executor);
        return borrowTenderHandler(executor);
    }

    /**
     * 校验投标  每个投资人只能投一个标的一次
     * @param userId
     * @param borrowId
     */
    private void verifyTender(String userId,int borrowId) {
        long borrowTenderTotal = borrowTenderMapper.getByUserIdAndBorrowId(userId, borrowId);
        if (borrowTenderTotal > 0){
            throw new BizFeignException(TransactError.TENDER_MORE_ONE);
        }
    }

    /**
     * 校验用户账户余额
     * @param userId 用户id
     * @param executor 投标信息
     * @return
     */
    private void verifyTenderAccount(String userId,  TenderExecutor executor){
        Account account = accountMapper.getByUserId(userId);
        //不做为空判断 预判断已做
        double tenderMoney = executor.getTenderMoney();

        if(executor.getDiscount() != null && executor.getDiscount().getDiscountType() == UserDiscountConstant.VOUCHER_TYPE){
            tenderMoney = BigDecimalUtils.sub(tenderMoney,Double.parseDouble(executor.getDiscount().getDiscountAvailable()));
        }
        if(account.getBalance().doubleValue() < tenderMoney){
            throw new BizFeignException(TransactError.ACCOUNT_BALANCE_ERROR);
        }
    }

    /**
     * 消息队列验证标的信息
     *  标的会在复审时将剩余可投金额放入到缓存中,在满标复审后或者撤标时进行清空操作
     * @param productId     产品信息
     * @param tenderMoney 投标金额
     */
    @Override
    public void verifyBorrow(Integer productId,double tenderMoney){

        ProjectInfoCacheInfo projectInfo = projectInfoCache.getProjectInfo(productId);

        //标的被撤回或者满标
        if(projectInfo == null){
            throw new BizFeignException(TransactError.BORROW_FULL_OR_CANCEL);
        }

        if (projectInfo.getLeftAccount().doubleValue() <= 0){
            throw new BizFeignException(TransactError.TENDER_FULL_ERROR);
        }

        if(projectInfo.getLeftAccount().doubleValue() < tenderMoney){
            throw new BizFeignException(TransactError.TENDER_NOT_ENOUGH);
        }
    }

    @Override
    public List<BorrowTenderOutput> getListByStatus(Integer productId, Integer status) {

        List<BorrowTender> listByStatus = borrowTenderMapper.getListByStatus(productId, status, TenderConstant.TENDER_TYPE_0);

        return TransferUtil.transferList(listByStatus,BorrowTenderOutput.class);
    }


    /**
     * 设置加息券相关属性
     */
    private void setVoucher(BorrowTender borrow , UserDiscountOutput voucherOutput){
        if(voucherOutput != null){
            /**
             * 该字段存放额度 加息券为 %单位 抵用券为 分单位
             */
            borrow.setVoucherMoney(new BigDecimal(voucherOutput.getDiscountAvailable()));
            //抵用券
            if(voucherOutput.getDiscountType() == UserDiscountConstant.VOUCHER_TYPE){
                borrow.setVoucherId(Integer.parseInt(voucherOutput.getId().toString()));
                //实际投标金额= 实际投标金额-抵用券金额
                borrow.setAccount(borrow.getAccount().subtract(new BigDecimal(voucherOutput.getDiscountAvailable())));
            }else{
                //加息券
                borrow.setVoucherCouponId(Integer.parseInt(voucherOutput.getId().toString()));
            }
        }
    }


    /**
     * 投标预处理:增加投标冻结的投标信息,添加抵用券等信息,不计算收益<br/>
     * 业务逻辑说明: 在队列中处理投标时,需要首先插入投标信息(包含使用抵用券ID,金额等),之后请求银行,最后更新投标信息.
     * 注意:如果请求银行失败,则会进入定时任务队列进行更新投标,之前插入的投标信息所关联的抵用券等信息可能会失效,
     * 需要在定时任务中根据抵用券ID重新获取抵用券(直接查询未使用的抵用券),如果没有则默认为抵用券已使用
     * @param executor
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public BorrowTender borrowTenderHandler(TenderExecutor executor) {
        BorrowTender borrowTender = insertBorrowTender(executor);
        String remarks;
        if(borrowTender.getTenderMode() == TenderConstant.TENDER_MODE_HAND){
            remarks = new StringBuffer("手动投标[<a href=").append(BorrowUtils.getBorrowUrl(executor.getProduct().getId())).append(" target=_blank >")
                      .append(executor.getProduct().getProductTitle()).append("</a>]所冻结资金").toString();
        }else{
            remarks = new StringBuffer("自动投标[<a href=").append(BorrowUtils.getBorrowUrl(executor.getProduct().getId())).append(" target=_blank >")
                    .append(executor.getProduct().getProductTitle()).append("</a>]所冻结资金").toString();
        }
        accountLogService.insertBorrowTenderFrostLog(borrowTender,remarks);
        return borrowTender;
    }


    /**
     * @param executor
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public BorrowTender insertBorrowTender(TenderExecutor executor){
        BorrowTender borrowTender = new BorrowTender();

        borrowTender.setStatus(TenderConstant.TENDER_FROST);
        borrowTender.setAddTime(new Date());

        borrowTender.setAddIp(executor.getAddIp());
        borrowTender.setBorrowId(executor.getProduct().getId());
        borrowTender.setTenderMode(executor.getTenderMode());
        borrowTender.setTenderType(executor.getTenderType());
        borrowTender.setUserId(executor.getUser().getUserId());
        borrowTender.setMobile(executor.getUser().getMobile());
        borrowTender.setAutoTenderId(executor.getAutoTenderId());

        borrowTender.setTranAwardOrderid(executor.getTranAwardOrderid());
        borrowTender.setChoicenessTenderId(executor.getChoicenessTenderId());
        borrowTender.setPreAccount(BigDecimal.valueOf(executor.getTenderMoney()));
        /**
         * 预处理实际投标金额= 投标金额,后面可以进行加减操作
         */
        borrowTender.setAccount(borrowTender.getPreAccount());
        borrowTender.setTenderTranAward(BigDecimal.valueOf(executor.getTenderTranAward()));
        setVoucher(borrowTender,executor.getDiscount());

        setInterest(borrowTender,executor.getProduct());

        //订单号 p120000_tender170012312_0
        borrowTender.setOrderId(new StringBuffer("p").append(borrowTender.getBorrowId()).append("_tender").append(generateService.get()).append("_0").toString());

        BigDecimal balanceCash = this.getBalanceCash(borrowTender.getUserId(), borrowTender.getAccount().doubleValue());
        borrowTender.setFeeAccount(balanceCash);
        borrowTenderMapper.insertSelective(borrowTender);
        return borrowTender;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBorrowTenderStatus(Integer tenderId ,int status) {
        int count = borrowTenderMapper.updateTenderStatus(tenderId,status,"投标成功");
        if(count != 1){
            throw new BizFeignException(TransactError.TENDER_NOT_FOUND);
        }
    }


    @Override
    public BorrowTender getTenderInfo(Integer tenderId) {
        BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(tenderId);
        if(Objects.isNull(borrowTender)) {
            LOGGER.error("can not found tender info, tenderId:{}", tenderId);
            throw new BizFeignException(TransactError.TENDER_NOT_FOUND);
        }
        return borrowTender;
    }



    /**
     * 设置预计收益包含:总收益,加息券收益,平台加息收益
     * 总收益=平台加息+加息券收益+基础收益
     * @param tender 投标信息
     * @param product 加息券信息
     */
    @Override
    public void setInterest(BorrowTender tender, ProductOutput product) {

        /**
         * 设置平台加息收益
         */
        if(product.getAwardScale() != null && product.getAwardScale().doubleValue() != 0){
            double platformInterest = getInterest(
                    product.getProductPeriod(),
                    product.getAwardScale().doubleValue(),
                    product.getRefundWay(),
                    tender.getPreAccount().doubleValue());
            tender.setPlatformInterest(BigDecimal.valueOf(platformInterest));
        }
        /**
         * 设置加息券收益,设置总收益
         */
        if(tender.getVoucherCouponId() != null && tender.getVoucherCouponId() > 0 ){
            double totalInterest = getInterest(
                    product.getProductPeriod(),
                    //平台加息+基础利息+加息券
                    getTotalApr(product.getBorrowApr(),product.getAwardScale(),tender.getVoucherMoney()),
                    product.getRefundWay(),
                    tender.getPreAccount().doubleValue());

            double voucherInterest = getInterest(
                    product.getProductPeriod(),
                    //平台加息
                    tender.getVoucherMoney().doubleValue(),
                    product.getRefundWay(),
                    tender.getPreAccount().doubleValue());
            //加息券收益
            tender.setVoucherCouponMoney(BigDecimal.valueOf(voucherInterest));
            //总收益
            tender.setAccountInterest(BigDecimal.valueOf(totalInterest));
        }else{
            /**
             * 设置总收益 不包含加息券
             */
            double totalInterest = getInterest(
                    product.getProductPeriod(),
                    //平台加息+基础利息+加息券
                    getTotalApr(product.getBorrowApr(),product.getAwardScale(),null),
                    product.getRefundWay(),
                    tender.getPreAccount().doubleValue());
            tender.setAccountInterest(BigDecimal.valueOf(totalInterest));
        }
    }

    /**
     * 获取总利息 %
     * @param borrowApr 平台基础利息
     * @param platformApr 平台奖励利息
     * @param voucherMoney 加息券利息
     * @return
     */
    private  double getTotalApr(BigDecimal borrowApr,BigDecimal platformApr,BigDecimal voucherMoney){
        BigDecimal total = BigDecimal.valueOf(0);
        if(borrowApr != null){
            total = total.add(borrowApr);
        }
        if(platformApr != null){
            total = total.add(platformApr);
        }
        if(voucherMoney != null){
            total = total.add(voucherMoney);
        }
        return total.doubleValue();
    }




    /**
     * 计算收益
     * @param period 期限
     * @param apr 利息
     * @param refundWay   标的类型:0:等额本息 1:按月付息
     * @return
     */
    private double getInterest(int period,double apr,int refundWay,double tenderMoney){
        double interest;
        if(refundWay == ProductConstant.REFUND_WAY){
            interest = BorrowUtils.fixedPaymentMortgage(tenderMoney,period,apr);
        }else{
            interest = BorrowUtils.perMonth(tenderMoney, period, apr);
        }
        return interest;
    }


    @Override
    public PageInfo<BorrowTenderVo> getUserTenderByPage(TenderListParam param) {
        PageHelper.startPage(param.getCurrentPage(),param.getPageSize());
        BorrowTenderQuery query = new BorrowTenderQuery();
        query.setStatus(param.getStatus());
        query.setUserId(param.getUserId());
        query.setStartTime(param.getStartTime());
        query.setEndTime(param.getEndTime());

        List<BorrowTender> list = borrowTenderMapper.getBorrowList(query);
        PageInfo<BorrowTender> pageInfo = new PageInfo<>(list);
        List<BorrowTenderVo> voList = transferBorrowTender(pageInfo.getList());

        PageInfo<BorrowTenderVo> result = new PageInfo<>();
        result.setTotal(pageInfo.getTotal());
        result.setList(voList);
        return result;
    }

    /**
     * 将borrowTender对象转化为borrowTenderVo对象 方便前台展示
     * @param list
     * @return
     */
    private List<BorrowTenderVo> transferBorrowTender(List<BorrowTender> list){
        List<BorrowTenderVo> voList = new ArrayList<>();
        if(list != null && list.size() > 0){
            /*
             * 将数据库查询的参数重新包装为BorrowTenderVo对象返回
             */
            BorrowTenderVo vo;
            for(BorrowTender tender : list){
                vo = new BorrowTenderVo();
                vo.setPreAccount(BigDecimalUtils.centToYuanFormat(tender.getPreAccount().doubleValue()));
                vo.setProductId(tender.getBorrowId());
                vo.setStatus(tender.getStatus());
                vo.setTenderId(tender.getId());
                vo.setTenderTime(DateUtil.formatLong(tender.getAddTime()));
                vo.setInterest(BigDecimalUtils.centToYuanFormat(tender.getAccountInterest().doubleValue()));
                //加息券不为空
                if(tender.getVoucherCouponId() != null && tender.getVoucherCouponId() > 0){
                    vo.setVoucherMoney(tender.getVoucherMoney().doubleValue());
                    vo.setVoucherType(1);
                }else if(tender.getVoucherId() != null && tender.getVoucherId() > 0){
                    //抵用券ID不为空
                    vo.setVoucherMoney(tender.getVoucherMoney().doubleValue());
                    vo.setVoucherType(2);
                }else{
                    //不使用券
                    vo.setVoucherType(0);
                }
                voList.add(vo);
            }
        }
        return voList;
    }

    @Override
    public PageInfo<BorrowTenderVo> getUserAutoTenderByPage(AutoTenderExtParam param) {
        BorrowTenderQuery query = new BorrowTenderQuery();
        query.setAutoTenderId(param.getAutoTenderId());
        query.setUserId(param.getUserId());
        //只查询非失败的投标记录
        query.setFilterFail(1);
        PageHelper.startPage(param.getCurrentPage(),param.getPageSize());
        List<BorrowTender> list = borrowTenderMapper.getList(query);

        PageInfo<BorrowTender> pageInfo = new PageInfo<>(list);
        List<BorrowTenderVo> voList = transferBorrowTender(pageInfo.getList());
        PageInfo<BorrowTenderVo> result = new PageInfo<>();
        result.setTotal(pageInfo.getTotal());
        result.setList(voList);
        return result;
    }

    @Override
    public BorrowTenderOutput getUserBorrowTenderById(TenderDetailParam param) {
        BorrowTenderQuery query = new BorrowTenderQuery();
        query.setUserId(param.getUserId());
        query.setId(param.getTenderId());

        List<BorrowTender> list = borrowTenderMapper.getList(query);

        if(list != null && list.size() > 0){
            //出参对象重新组装
            BorrowTender borrowTender = list.get(0);
            BorrowTenderOutput output = transfer(borrowTender);
            //平台加息利息
            output.setPlatformInterest(borrowTender.getPlatformInterest());
            //基本利息
            output.setAccountInterest(borrowTender.getAccountInterest());
            //加息券利息
            output.setVoucherCouponMoney(borrowTender.getVoucherCouponMoney());
            //券额度
            output.setVoucherMoney(borrowTender.getVoucherMoney());
            return output;
        }
        throw new BizFeignException(TransactError.TENDER_NOT_FOUND);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecheck(RecheckInput recheck) {
        List<Integer> ids = FluentIterable.from(recheck.getCollectionList()).transform(input -> input.getBorrowTender().getId()).toList();

        //更更新投标状态 还款中
        borrowTenderMapper.updateTenderStatusBatch2(ids, TenderConstant.TENDER_REPAYMENT, recheck.getBorrowRecheckInput().getIsTransferred(), null);

        List<BorrowTenderOutput> tenderList = FluentIterable.from(recheck.getCollectionList()).transform(input -> input.getBorrowTender()).toList();

        String remarks = new StringBuffer("投资[<a href=").append(BorrowUtils.getBorrowUrl(recheck.getBorrowRecheckInput().getId())).append(" target=_blank >")
                .append(recheck.getBorrowRecheckInput().getProductTitle()).append("</a>]成功投资金额扣除").toString();

        accountLogService.insertTenderSuccessLog(tenderList,remarks);

        this.tenderAward(recheck.getCollectionList(),recheck.getBorrowRecheckInput());
        //TODO 通知产品中心消费成功
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void borrowRecheck(RecheckInput recheck) {
        borrowCollectionService.insertBorrowCollectionBatch(recheck.getCollectionList(), recheck.getBorrowRecheckInput().getId());
        this.updateRecheck(recheck);
    }


    @Override
    public PageInfo<UnderTenderOutput> selectTenderListByChoicenessTenderId(JustIdInput justIdInput){
        LOGGER.info("choiceness selectTenderListByChoicenessTenderId start ,input = {}", justIdInput);
        PageHelper.startPage(justIdInput.getCurrentPage(), justIdInput.getPageSize());
        List<UnderTenderOutput> tenderList = borrowTenderMapper.selectTenderListByChoicenessTenderId(justIdInput.getJustId());
        PageInfo<UnderTenderOutput> pageInfo = new PageInfo<>(tenderList);
        Page<UnderTenderOutput> page = (Page<UnderTenderOutput>)tenderList;
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    @Override
    public PageInfo<BorrowTenderOutput> getTenderList(TenderQueryInput input) {
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());

        List<BorrowTender> list = borrowTenderMapper.selectByBorrowIdAndType(input.getBorrowId(), input.getTenderType());
        PageInfo<BorrowTenderOutput> result = new PageInfo<>();

        List<BorrowTenderOutput> borrowTenderOutputs = TransferUtil.transferList(list, BorrowTenderOutput.class);

        Page<BorrowTender> page = (Page<BorrowTender>)list;
        result.setList(borrowTenderOutputs);
        result.setTotal(page.getTotal());

        return result;
    }

    @Override
    public List<BorrowTenderOutput> getTenderListByBorrowId(Integer borrowId) {
        BorrowTenderQuery query = new BorrowTenderQuery();
        query.setBorrowId(borrowId);
        query.setStatus(TenderConstant.TENDER_SUCCESS);
        List<BorrowTender> list = borrowTenderMapper.getList(query);
        //出参转换
        return TransferUtil.transferList(list, BorrowTenderOutput.class);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withdrawTender(ProductOutput product) {
        List<BorrowTender> list = borrowTenderMapper.getListByBorrowId(product.getId());


        if(list != null && list.size() > 0){

            List<BorrowTender> tenderList = FluentIterable.from(list).filter(input -> input.getStatus() == TenderConstant.TENDER_SUCCESS).toList();
            //将投标状态更新投标撤销
            borrowTenderMapper.updateTenderStatusBatch(tenderList,TenderConstant.TENDER_CANCEL,null,null);

            String remarks = getTenderCancelRemarks(product.getId(), product.getProductTitle());

            accountLogService.insertTenderCancelLog(tenderList,remarks);

            for (BorrowTender tender : tenderList){
                 updateVoucher(tender);
                 //TODO 短信通知撤销操作
            }
        }
    }

    @Override
    public String getTenderCancelRemarks(Integer productId, String title) {

        return new StringBuffer("标的[<a href=").append(BorrowUtils.getBorrowUrl(productId)).append(" target=_blank >")
                .append(title).append("</a>]撤回").toString();
    }


    /**
     * 更新用户抵用券为 未使用状态
     */
    private void updateVoucher(BorrowTender tender){
        if(tender.getVoucherId() != null && tender.getVoucherId() > 0){
            producerMq.updateVoucherStatus(tender.getVoucherId(), UserDiscountConstant.VOUCHER_STATUS_UNUSED, tender.getUserId(), null);
        }else if(tender.getVoucherCouponId() != null && tender.getVoucherCouponId() > 0){
            producerMq.updateVoucherStatus(tender.getVoucherCouponId(),UserDiscountConstant.VOUCHER_STATUS_UNUSED,tender.getUserId(),null);
        }
    }

    /**
     * 理财计划投资 专用
     */
    private void updateVoucher(BorrowChoicenessTender tender){
        if(tender.getVoucherId() != null && tender.getVoucherId() > 0){
            producerMq.updateVoucherStatus(tender.getVoucherId(), UserDiscountConstant.VOUCHER_STATUS_UNUSED, tender.getUserId(),null);
        }else if(tender.getVoucherCouponId() != null && tender.getVoucherCouponId() > 0){
            producerMq.updateVoucherStatus(tender.getVoucherCouponId(),UserDiscountConstant.VOUCHER_STATUS_UNUSED,tender.getUserId(),null);
        }
    }

    /**
     * 整个理财计划撤销(投资端）
     * @param productOutput
     * @return
     */
    public Boolean doWithDrawPlan(ProductOutput productOutput) {

        //理财计划 id
        Integer productId = productOutput.getParentId();
        //1.查询理财计划的底层投资
        List<BorrowTender> tenders = borrowTenderMapper.getBorrowTenderByProductId(productId);

        //满标的投资
        List<BorrowTender> repaying =  tenders.stream().filter(borrowTender
                -> borrowTender.getStatus() == TenderConstant.TENDER_REPAYMENT).collect(Collectors.toList());

        //还没有满标的 投资
        List<BorrowTender> tendering =  tenders.stream().filter(borrowTender
                -> borrowTender.getStatus() == TenderConstant.TENDER_SUCCESS).collect(Collectors.toList());


        //将投标状态更新投标撤销
        borrowTenderMapper.updateTenderStatusBatch(tendering,TenderConstant.TENDER_CANCEL,null,null);

        String remarks = getTenderCancelRemarks(productOutput.getId(), productOutput.getProductTitle());
        //更新资金的方法
        accountLogService.insertTenderCancelLog(tendering,remarks);

        List<BorrowChoicenessTender> choicenessTenders = choicenessTenderMapper.selectOriginalListByBorrowId(productId);

        // 如果是未匹配的话
        choicenessTenders.forEach(tender -> {
            if(tender.getPreAccount().longValue()>tender.getAccount().longValue()){
                accountLogService.thawingPlanFunds(tender);
            }
        });

        //所有加入理财计划 所用到的优惠券
        for (BorrowChoicenessTender tender : choicenessTenders){
            updateVoucher(tender);
            //短信通知撤销操作
        }

        //2.满标的投资


        return true;
    }

    @Override
    public List<BorrowTenderOutput> getByBorrowIdAndTypeId(Integer borrowId, Integer typeId) {
        List<BorrowTender> list = borrowTenderMapper.selectByBorrowIdAndType(borrowId, typeId);
        List<BorrowTenderOutput> outList = TransferUtil.transferList(list, BorrowTenderOutput.class);
        return outList;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBorrowTenderFail(BorrowTender tender,String errorMsg) {
        tender.setRemarks(tender.getRemarks());
        tender.setStatus(TenderConstant.TENDER_FAIL);
        tender.setUpdateTime(new Date());
        borrowTenderMapper.updateByPrimaryKeySelective(tender);
    }

    @Override
    public BorrowTenderOutput getMaxBorrowTender(Integer borrowId) {
        BorrowTender tender = borrowTenderMapper.getMaxBorrowTender(borrowId);
        return transfer(tender);
    }

    @Override
    public BorrowTenderOutput getLastBorrowTender(Integer borrowId) {
        BorrowTender tender = borrowTenderMapper.getLastBorrowTender(borrowId);
        return transfer(tender);
    }


    /**
     * 对象转换
     * @param tender
     * @return
     */
    public BorrowTenderOutput transfer(BorrowTender tender){
        BorrowTenderOutput output = new BorrowTenderOutput();
        BeanUtils.copyProperties(tender,output);
        return output;
    }

    /**
     * 对象转换
     * @param output
     * @return
     */
    public BorrowTender transfer(BorrowTenderOutput output){
        BorrowTender borrowTender = new BorrowTender();
        BeanUtils.copyProperties(output,borrowTender);
        return borrowTender;
    }

    @Override
    public  List<PlanBorrowTenderOutput>  selectTenderListOnOrgReverify(Integer borrowId)
    {
        return borrowTenderMapper.selectTenderListOnOrgReverify(borrowId);
    }

    @Override
    public  List<PlanBorrowTenderOutput>  selectTenderListOnDevReverify(Integer borrowId)
    {
        return borrowTenderMapper.selectTenderListOnDevReverify(borrowId);
    }

    @Override
    public  List<PlanBorrowTenderOutput>  selectTenderListOnDevReverifyPlay(Integer borrowId)
    {
        return borrowTenderMapper.selectTenderListOnDevReverifyPlay(borrowId);
    }




    @Override
    public void tenderAward(List<UserCollectionInput> list, BorrowRecheckInput product) {
        //相关积分奖励见op_score_task表配置信息
        for (UserCollectionInput user : list){
            firstOrSecondTender(user,product);
            appTender(user.getBorrowTender());
            under12MonthTender(user.getBorrowTender(),product);
            above12MonthTender(user.getBorrowTender(),product);
            dayTender(user.getBorrowTender(),product);
            inviteAward(user);
            createPdf(user,product);
        }
        BorrowTender max = maxTender(product);
        BorrowTender last = lastTender(product);
        maxAndLastTender(max,last, product);
    }

    /**
     * 散标生成pdf
     * @param user
     */
    private void createPdf(UserCollectionInput user,BorrowRecheckInput product) {
        try {
            Map<String,Object> map = Maps.newHashMap();
            //借款人相关
            if (product.getUserDeposit() != null ){
                if(StringUtils.isNotBlank(product.getUserDeposit().getIdCard())){
                    map.put("idcard2",StringUtil.hideBankCard(product.getUserDeposit().getIdCard()));
                }
                map.put("realname2", product.getUserDeposit().getRealName());
            }
            if(product.getUser() != null){
                map.put("username2", product.getUser().getUserName());
            }
            //投资人
            if(user.getUserDeposit() != null){
                if(StringUtils.isNotBlank(user.getUserDeposit().getRealName())){
                    map.put("idcard1", user.getUserDeposit().getIdCard());
                    map.put("realname1", user.getUserDeposit().getRealName());
                }
            }
            if(user.getUser() != null){
                map.put("username1", user.getUser().getUserName());
            }

            //标的相关 TODO
            map.put("borrowUse", product.getBorrowUse());
            map.put("account", BigDecimalUtils.centToYuanFormat(product.getBorrowAmount()));
            map.put("borrowPeriod", BorrowUtils.getPeriodText(product.getProductPeriod(), product.getPeriodUnit()));
            map.put("beforeDate",BorrowUtils.getBearingTime(product.getRepayLastTime(),product.getProductPeriod(),product.getPeriodUnit()));
            map.put("afterDate", DateUtil.formatShort(product.getRepayLastTime()));
            map.put("borrowId", product.getProductCode());
            //投标相关
            //文件名称
            map.put("no",product.getProductCode() + user.getBorrowTender().getId());
            //模板类型
            //TODO 待完成
//        map.put("type", type);
//        map.put("carModel", carModel);
            GeneratePDF.generatePdfFile(map);
        }catch (Exception e){
            LOGGER.error("满标复审,生成pdf失败,user:{},product:{},错误信息:{}",user,product,e.getMessage());
        }
    }

    /**
     * 邀请奖励
     * @param collection
     */
    private void inviteAward(UserCollectionInput collection) {

        if(collection.getUser() != null && collection.getUser().getInvestFlag() == UserConstant.INVEST_FLAG_0){
            String userId = collection.getUser().getUserId();
            List<BorrowTender> tenderList = borrowTenderMapper.selectByUserId(userId);
            //证明是第一次投标
            if (tenderList != null && tenderList.size() == 1){
                BorrowTender borrowTender = tenderList.get(0);
                TenderAccountMqInfo info = new TenderAccountMqInfo();
                info.setType(TenderConstant.ACCOUNT_MQ_TYPE_0);
                info.setUserId(userId);
                info.setIsFirstTender(true);
                info.setFirstTenderAccount(borrowTender.getPreAccount());
                producerMq.updateUserAccount(info);
            }
        }
    }

    /**
     * 扫尾并且最大投标 3倍积分
     * @param max 最大投标
     * @param last 最后投标
     * @param product 产品信息
     */
    @Override
    public void maxAndLastTender(BorrowTender max, BorrowTender last, BorrowRecheckInput product) {
        //最多投标和最大投标为同一条记录
        if(max != null
                && last != null
                && max.getId().intValue() == last.getId().intValue()){
            int baseScore = getBaseScore(max.getPreAccount().doubleValue(), product);
            if(baseScore > 0){
                producerMq.updateUserScore(PublicConstant.TASK_ID_MOST_AND_ROUND_OFF,max.getUserId(),max.getMobile(),max.getChannel()+"",product.getIntegralFold() * baseScore * 3);
            }
        }
    }

    /**
     * 扫尾投资 双倍积分
     * @param product
     * @return 减少查询次数
     */

    @Override
    public BorrowTender lastTender(BorrowRecheckInput product) {
        BorrowTender lastBorrowTender = borrowTenderMapper.getLastBorrowTender(product.getId());
        if(lastBorrowTender != null){
            OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_ROUND_OFF);
            if(scoreTaskInfo != null){
                producerMq.updateUserScore(PublicConstant.TASK_ID_ROUND_OFF,lastBorrowTender.getUserId(),lastBorrowTender.getMobile(),lastBorrowTender.getChannel()+"",scoreTaskInfo.getScore());
            }
        }
        return lastBorrowTender;
    }

    /**
     * 投资最多积分 双倍投资积分
     * @param product
     * @return 减少查询次数
     */
    @Override
    public BorrowTender maxTender(BorrowRecheckInput product) {
        BorrowTender maxBorrowTender = borrowTenderMapper.getMaxBorrowTender(product.getId());
        if(maxBorrowTender != null){
            int baseScore = getBaseScore(maxBorrowTender.getPreAccount().doubleValue(), product);
            if(baseScore > 0){
                producerMq.updateUserScore(PublicConstant.TASK_ID_MOST_INVEST,maxBorrowTender.getUserId(),maxBorrowTender.getMobile(),maxBorrowTender.getChannel()+"",product.getIntegralFold() * baseScore * 2);
            }
        }
        return maxBorrowTender;
    }

    /**
     * 获取本次投标的基础积分
     * 天标投资奖励 奖励：1积分/5000元投资额
     * 12月份之上的投标奖励 奖励：1积分/1000元投资额
     * 12月份之下的投标奖励 奖励：1积分/2000元投资额
     * @param tenderMoney
     * @param product
     * @return
     */
    @Override
    public int getBaseScore(double tenderMoney,BorrowRecheckInput product){
        int baseScore = 0;
        if(product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_DAY){
            baseScore = (int)(BigDecimalUtils.centToYuan(tenderMoney) / 5000);
        }else if(product.getProductPeriod() >= TenderConstant.SCORE_AWARD_MONTH
                && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
            baseScore = (int)(BigDecimalUtils.centToYuan(tenderMoney) / 1000);
        }else if(product.getProductPeriod() < TenderConstant.SCORE_AWARD_MONTH
                && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
            baseScore = (int)(BigDecimalUtils.centToYuan(tenderMoney) / 2000);
        }
        return baseScore;
    }

    /**
     * 天标投资奖励 奖励：1积分/5000元投资额
     * @param tender
     * @param product
     */
    @Override
    public void dayTender(BorrowTenderOutput tender, BorrowRecheckInput product) {
        int baseScore = getBaseScore(tender.getPreAccount().doubleValue(), product);
        if(baseScore > 0){
            producerMq.updateUserScore(PublicConstant.TASK_ID_INVEST_DAILY,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",product.getIntegralFold() * baseScore);
        }
    }

    /**
     * 12月份之上的投标奖励 奖励：1积分/1000元投资额
     * @param tender
     * @param product
     */
    @Override
    public void above12MonthTender(BorrowTenderOutput tender, BorrowRecheckInput product) {
        int baseScore = getBaseScore(tender.getPreAccount().doubleValue(), product);
        if(baseScore > 0 && product.getProductPeriod() >= TenderConstant.SCORE_AWARD_MONTH
                && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
            producerMq.updateUserScore(PublicConstant.TASK_ID_INVEST_ABOVE_12_MONTH,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",product.getIntegralFold() * baseScore);
        }
    }

    /**
     * 12月份之下的投标奖励 奖励：1积分/2000元投资额
     * @param tender
     * @param product
     */
    @Override
    public void under12MonthTender(BorrowTenderOutput tender, BorrowRecheckInput product) {
        int baseScore = getBaseScore(tender.getPreAccount().doubleValue(), product);
        if(baseScore > 0 && product.getProductPeriod() < TenderConstant.SCORE_AWARD_MONTH
                && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
            producerMq.updateUserScore(PublicConstant.TASK_ID_INVEST_UNDER_12_MONTH,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",product.getIntegralFold() * baseScore);
        }
    }
    /**
     * 手机投资奖励
     * @param tender
     */
    @Override
    public void appTender(BorrowTenderOutput tender) {
        if (tender.getChannel() == ChannelType.APP.getValue()){
            OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_APP_INVEST);
            if(scoreTaskInfo != null){
                producerMq.updateUserScore(PublicConstant.TASK_ID_APP_INVEST,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",scoreTaskInfo.getScore());
            }
        }
    }
    /**
     * 首投积分奖励,
     * 首投等额本息奖励,
     * 第二次投标奖励
     */
    @Override
    public void firstOrSecondTender(UserCollectionInput collection,BorrowRecheckInput product) {

        BorrowTenderOutput tender = collection.getBorrowTender();
        //未投资过
        if(collection.getUser() != null && collection.getUser().getInvestFlag() == UserConstant.INVEST_FLAG_0 ){
            OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_FIRST_INVEST);
            if(scoreTaskInfo != null){
                producerMq.updateUserScore(PublicConstant.TASK_ID_FIRST_INVEST,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",scoreTaskInfo.getScore());
            }
            //等额本息首投奖励
            if(product.getRefundWay() == ProductConstant.REFUND_WAY){
                OpScoreTaskCacheInfo scoreTask1 = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_FIRST_FIXED_PAYMENT_MORTGAGE);
                if (scoreTask1 != null){
                    producerMq.updateUserScore(PublicConstant.TASK_ID_FIRST_FIXED_PAYMENT_MORTGAGE,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",scoreTask1.getScore());
                }
            }
        }else{
            //二次复投非债权标的
            long count = borrowTenderMapper.getUserTenderCount(tender.getUserId());
            long plan = choicenessTenderMapper.getUserTenderCount(tender.getUserId());

            if (count == (count + plan)){
                OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_CHECK_IN);
                if(scoreTaskInfo != null){
                    producerMq.updateUserScore(PublicConstant.TASK_ID_SECOND_INVEST,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",scoreTaskInfo.getScore());
                }
            }
        }
    }

    @Override
    public BigDecimal getTenderVoucherCount(String userId) {
        return borrowTenderMapper.getTenderVoucherCount(userId);
    }

    @Override
    public PageInfo<ProductTenderVo> getProductTenderByPage(BorrowParam param) {
        PageHelper.startPage(param.getCurrentPage(),param.getPageSize());
        List<BorrowTender> productTender = borrowTenderMapper.getProductTender(param.getProductId());
        PageInfo<BorrowTender> pageInfo = new PageInfo<>(productTender);

        PageInfo<ProductTenderVo> result = new PageInfo<>();

        if(pageInfo.getList() == null || pageInfo.getList().size() == 0){
            return result;
        }
        List<ProductTenderVo> list = Lists.newArrayList();
        ProductTenderVo vo;
        for (int i = 0; i < pageInfo.getList().size() ; i++) {
            BorrowTender tender = pageInfo.getList().get(i);
            vo = new ProductTenderVo();
            //物理判断首投
            if (i == 0 && param.getCurrentPage() <= 1){
                vo.setFirst(true);
            }
            vo.setAddTime(DateUtil.formatLong(tender.getAddTime()));
            vo.setMobile(StringUtil.hideMobile(tender.getMobile()));
            vo.setMoney(BigDecimalUtils.centToYuanFormat(tender.getPreAccount()));
            if (tender.getTenderMode() == TenderConstant.TENDER_MODE_AUTO){
                vo.setType(2);
            }else if(tender.getChannel() == 1 || tender.getChannel() == 2){
                //TODO 投标渠道待确认
                vo.setType(1);
            }
            list.add(vo);
        }
        result.setTotal(pageInfo.getTotal());
        result.setList(list);
        return result;
    }

    @Override
    public BigDecimal getUserTenderFrost(String userId) {

        return borrowTenderMapper.getUserTenderFrost(userId);
    }
}
