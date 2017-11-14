package com.tuodao.bp.api.facade.service.transaction.impl;

import com.google.common.collect.FluentIterable;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.facade.client.depository.DepositoryBiddingClient;
import com.tuodao.bp.api.facade.client.depository.DepositorySeekClient;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.api.facade.client.product.BorrowTypeClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowTenderClient;
import com.tuodao.bp.api.facade.client.useraccount.UserClient;
import com.tuodao.bp.api.facade.client.useraccount.UserDepositFegin;
import com.tuodao.bp.api.facade.client.useraccount.UserVipClient;
import com.tuodao.bp.api.facade.controller.depository.MapUtils;
import com.tuodao.bp.api.facade.service.transaction.AccountService;
import com.tuodao.bp.api.facade.service.transaction.GenerateService;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.ProjectInfoCacheInfo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.output.BorrowTypeOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.business.useraccount.output.UserVipInfoOutput;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.operation.UserDiscountConstant;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.api.facade.service.transaction.BorrowTenderService;
import com.tuodao.bp.api.facade.service.transaction.UserService;
import com.tuodao.bp.model.enums.ChannelType;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowProtocolVo;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.TenderTransferVo;
import com.tuodao.bp.model.facade.traningcenter.output.TransfereeVo;
import com.tuodao.bp.model.traningcenter.input.*;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;



import java.util.*;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 19:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("borrowTenderService")
public class BorrowTenderServiceImpl implements BorrowTenderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowTenderServiceImpl.class);



    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BorrowTenderClient borrowTenderClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private UserAccountCache userAccountCache;

    @Autowired
    private UserDiscountsClient userDiscountsClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserDepositFegin userDepositFegin;

    @Autowired
    private UserVipClient userVipClient;

    @Autowired
    private BorrowTypeClient borrowTypeClient;

    @Autowired
    private DepositorySeekClient depositorySeekClient;

    @Autowired
    private DepositoryBiddingClient depositoryBiddingClient;

    @Autowired
    private ProjectInfoCache projectInfoCache;

    @Autowired
    private GenerateService generateService;

    @Override
    public String tender(TenderParam param, String tenderAuthCode) {

        UserAccountInfo user = userAccountCache.getUserAccoutInfo(param.getUserId());
        verifyUser(user,param.getPayPassword());

        ProductOutput product = productClient.getProduct(param.getBorrowId());

        verifyBorrow(product,param,tenderAuthCode,user);

        UserDiscountOutput discountOutput = null;

        if(param.getVoucherId() != null && param.getVoucherId() > 0){
            discountOutput = userDiscountsClient.getUserDiscountById(new IdInput(param.getVoucherId(),param.getUserId()));
            verifyVoucher(param.getTenderMoney(),discountOutput,product);
        }

        accountService.verifyTenderAccount(param.getUserId(),param.getTenderMoney(),discountOutput);
        /**
         * 参数组装
         */
        TenderExecutor executor = new TenderExecutor();
        executor.setProduct(product);
        executor.setDiscount(discountOutput);
        executor.setUser(user);
        executor.setTenderMoney(param.getTenderMoney());
        executor.setTenderMode(TenderConstant.TENDER_MODE_HAND);
        executor.setChannel(param.getChannel());
        executor.setAddIp(param.getIp());
        executor.setTenderType(ProductConstant.PRODUCT_TYPE_COMMON);
        /**
         * 生成唯一查询结果
         */
        String tenderKey = param.getUserId() + CommonUtils.getUUID();
        executor.setTenderKey(tenderKey);
        //添加到MQ中进行消费
        borrowTenderClient.tenderProducer(executor);

        return tenderKey;
    }




    @Override
    public TransfereeVo tenderInfo(Integer tenderId) {
        TenderInput tenderInput = new TenderInput();
        tenderInput.setTenderId(tenderId);
        BorrowTenderOutput borrowTenderOutput = borrowTenderClient.findById(tenderInput);
        //调用产品接口，获取产品信息
        ProductOutput productOutput = productClient.getProduct(borrowTenderOutput.getBorrowId());

        TransfereeVo transfereeVo = new TransfereeVo();
        TransferUtil.transfer(borrowTenderOutput, transfereeVo);
        TransferUtil.transfer(productOutput, transfereeVo);
        transfereeVo.setTenderTime(borrowTenderOutput.getAddTime());
        transfereeVo.setBorrowApr(productOutput.getBorrowApr().toString() + "%");
        if(!Objects.isNull(borrowTenderOutput.getVoucherCouponId())) {
            transfereeVo.setVoucher(borrowTenderOutput.getVoucherCouponMoney() + "%加息券");
        }

        if(!Objects.isNull(borrowTenderOutput.getVoucherId())) {
            transfereeVo.setVoucher(borrowTenderOutput.getVoucherMoney() + "元抵扣券");
        }

        if(borrowTenderOutput.getStatus() == 0 || borrowTenderOutput.getStatus() == 1) {
            //募集中
            transfereeVo.setStatus(1);
        } else if(borrowTenderOutput.getStatus() == 5) {
            //已回款
            transfereeVo.setStatus(3);
        }

//        if(productOutput.getRepayLastTime() != null) {
//            Instant instant = productOutput.getRepayLastTime().toInstant();
//            ZoneId zone = ZoneId.systemDefault();
//            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
//            LocalDate localDate = localDateTime.toLocalDate();
//            long day = localDate.toEpochDay() - LocalDate.now().toEpochDay();
//
//        }

        return transfereeVo;

    }



    /**
     * 校验用户信息合法性<br/>
     * 1:是否绑定存管账户
     * 2:是否锁定
     * 3:支付密码是否正确
     * @param user      用户信息
     * @param payPassword 交易密码
     */
    @Override
    public void verifyUser(UserAccountInfo user,String payPassword) {
        userService.verifyUserCommon(user,false);
        userService.verifyUserPayPassword(user.getUserId(),payPassword);
    }

    /**
     * 校验标的信息
     * @param product  用户信息
     * @param param 前台传递过来的参数
     * @param tenderAuthCode 投标验证码
     * @param user           用户信息
     */
    @Override
    public void verifyBorrow(ProductOutput product, TenderParam param, String tenderAuthCode,UserAccountInfo user) {

        //标的不存在
        if (product == null){
            throw new MicroServiceException(TransactError.BORROW_NOT_FOUND);
        }
        //标的已满标
        if(product.getSurplusInvestAmount().doubleValue() <= 0){
            throw new MicroServiceException(TransactError.TENDER_FULL_ERROR);
        }

        //标的为新手标,用户非新手
        if (product.getDefineType() != null && product.getDefineType() == ProductConstant.NOVICE
                && user.getIsNewbie() != ProductConstant.NOVICE){
            throw new MicroServiceException(TransactError.NOVICE_BORROW_ERROR);
        }
        //投标验证码
        if (product.getIsAuthCode() == ProductConstant.IS_AUTH_CODE){
            if(param.getAuthCode() == null || !param.getAuthCode().equalsIgnoreCase(tenderAuthCode)){
                throw new MicroServiceException(TransactError.TENDER_CODE_ERROR);
            }
        }
        //约标密码
        if(!verifyBorrowOrderPassword(product,param.getOrderPassword())){
            throw new MicroServiceException(TransactError.ORDER_PASSWORD_ERROR);
        }
        //app专享
        if(product.getIsApp() == ProductConstant.IS_APP
                && Integer.parseInt(param.getRequestType()) != ChannelType.APP.getValue()){
            throw new MicroServiceException(TransactError.ORDER_APP_ERROR);
        }
        //募集中可投
        if(product.getProductStatus() != ProductConstant.TENDER_STATUS){
            throw new MicroServiceException(TransactError.BORROW_STATUS_ERROR);
        }
        //最大投标金额
        if(product.getMaxAmount().doubleValue() < param.getTenderMoney()){
            throw new MicroServiceException(TransactError.MAX_TENDER_ERROR);
        }
        //最小投标金额 如果剩余可投小于最小投标金额则不判断
        if(product.getMinAmount().doubleValue() <= product.getSurplusInvestAmount().doubleValue()
                && product.getMinAmount().doubleValue() > param.getTenderMoney()){
            throw new MicroServiceException(TransactError.MIN_TENDER_ERROR);
        }

        //如果剩余可投小于最小投标金额 则必须全部投完
        if(product.getSurplusInvestAmount().doubleValue() < product.getMinAmount().doubleValue()
                && product.getSurplusInvestAmount().doubleValue() != param.getTenderMoney()){
            throw new MicroServiceException(TransactError.TENDER_MONEY_MIN_ERROR);
        }

        //天标不能使用加息券
        if(product.getBorrowType() == ProductConstant.BORROW_TYPE_DAY
                && param.getVoucherId() != null){
            throw new MicroServiceException(TransactError.DAY_BORROW_ERROR);
        }

        //校验vip等级
        if (product.getDefineType() != null && product.getDefineType() > 0){
            BorrowTypeOutput borrowType = borrowTypeClient.getEnableBorrowType(product.getDefineType());
            if(borrowType != null && borrowType.getVipLevel() != null ){

                BasePojo pojo = new BasePojo();
                pojo.setUserId(user.getUserId());
                UserVipInfoOutput userVipInfo = userVipClient.getUserVipInfo(pojo);
                if(userVipInfo == null){
                    throw new MicroServiceException(TransactError.USER_VIP_NOT_FOUND);
                }
                if(borrowType.getVipLevel() > userVipInfo.getVipLevel()){
                    throw new MicroServiceException(TransactError.VIP_TENDER_ERROR);
                }
            }
        }

        /*
         *预售时间判断
         */
        if (product.getAvailableInvestTime() != null && new Date().before(product.getAvailableInvestTime())){
            throw new MicroServiceException(TransactError.PRE_TENDER_ERROR);
        }

    }



    /**
     * 校验加息券信息
     * @param tenderMoney 投标金额
     * @param voucher 加息券相关信息
     * @param product 产品信息
     */
    private void verifyVoucher(double tenderMoney, UserDiscountOutput voucher,ProductOutput product) {
        //加息券不存在
        if(voucher == null){
            throw new BizFeignException(TransactError.VOUCHER_NOT_FOUND);
        }
        //加息券无效
        if(voucher.getDisStatus() != UserDiscountConstant.VOUCHER_STATUS_UNUSED){
            throw new BizFeignException(TransactError.VOUCHER_USE_ERROR);
        }
        //有效期校验
        Date now = new Date();
        if (voucher.getEffectDate().after(now) || voucher.getInvalidDate().before(now) ){
            throw new BizFeignException(TransactError.VOUCHER_VALID_ERROR);
        }
        //优惠券期限不匹配
        if (voucher.getDateLimit() > product.getProductPeriod() ){
            throw new BizFeignException(TransactError.VOUCHER_LIMIT_ERROR);
        }
        //抵用券起投金额 判断
        if (voucher.getDiscountType() == UserDiscountConstant.COUPON_TYPE
                && voucher.getMoneyLimit().doubleValue() > tenderMoney){
            throw new BizFeignException(TransactError.VOUCHER_MONEY_ERROR);
        }
    }



    @Override
    public void borrowFullCheck(ProductOutput product) {
        LOGGER.debug("标的复审通过,开始生成还款及回款计划,borrowId:{}");
        List<BorrowTenderOutput> borrowTenderList = borrowTenderClient.getBorrowTenderList(product.getId());

        List<UserCollectionInput> userCollection = createUserCollection(borrowTenderList, product);
        BorrowRecheckInput borrowRecheck = createBorrowRecheck(product);

        RecheckInput input = new RecheckInput();

        input.setBorrowRecheckInput(borrowRecheck);
        input.setCollectionList(userCollection);
        //更新投标复审信息
        borrowTenderClient.borrowRecheck(input);
    }

    @Override
    public boolean verifyBorrowOrderPassword(ProductOutput product, String orderPassword) {
        //约标密码
        if (StringUtils.isNotBlank(product.getAppointPassword())){
            return product.getAppointPassword().equalsIgnoreCase(orderPassword);
        }
        return true;
    }

    /**
     * 创建关于标的的信息
     * @param product
     * @return
     */
    private BorrowRecheckInput createBorrowRecheck(ProductOutput product){
        BorrowRecheckInput recheckInput = TransferUtil.transferBean(product, BorrowRecheckInput.class);
        /**
         * 设置是否可转,转让时间
         * 条件:等额本息,月标大于一个月或年标, 投标金额大于100 但该处不进行金额判断 因为取不到 因此在数据库更新时进行判断
         */
        boolean isTransfer = product.getRefundWay() == ProductConstant.REFUND_WAY
                && ((product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH && (product.getProductPeriod() * 30) > product.getAvailableTransferDay())
                || (product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_YEAR && (product.getProductPeriod() * 365) > product.getAvailableTransferDay()));

        if(isTransfer){
            recheckInput.setIsTransferred(TenderConstant.IS_TRANSFERRED_YES);
            recheckInput.setTransferTime(DateUtil.addDays(new Date(),ProductConstant.TRANSFER_INTERVAL));
        }else{
            recheckInput.setIsTransferred(TenderConstant.IS_TRANSFERRED_NO);
        }

        UserAccountInfo userAccount = getUserAccount(product.getBorrowUserId());
        UserDepositOutput userDeposit = getUserDeposit(product.getBorrowUserId());
        recheckInput.setUser(userAccount);
        recheckInput.setUserDeposit(userDeposit);

        return recheckInput;
    }
    /**
     * 生成回款信息 同时组装所有需要用户信息
     * @param borrowTenderList
     * @param product
     * @return
     */
    private List<UserCollectionInput> createUserCollection(List<BorrowTenderOutput> borrowTenderList,ProductOutput product){
        //将该标的的所有投资人生成的回款计划组装成一个list
        List<UserCollectionInput> inputList = Lists.newArrayList();
        UserCollectionInput collection;
        for (BorrowTenderOutput tender : borrowTenderList){
            collection =  new UserCollectionInput();

            List<BorrowPlan> list = getBorrowPlans(tender,product);
            collection.setCollectionList(createCollectionList(list, tender));

            UserDepositOutput userDeposit = getUserDeposit(tender.getUserId());
            collection.setUserDeposit(userDeposit);

            UserAccountInfo userAccount = getUserAccount(tender.getUserId());
            collection.setUser(userAccount);

            collection.setBorrowTender(tender);

            inputList.add(collection);
        }
        return inputList;
    }

    /**
     * 等额本息 按月付息生成回款列表
     * @param output
     * @param productOutput
     * @return
     */
    private List<BorrowPlan> getBorrowPlans(BorrowTenderOutput output,ProductOutput productOutput){
        double couponApr = 0D;
        if(output.getVoucherCouponId() != null){
            couponApr = output.getVoucherMoney().doubleValue();
        }
        Date now = new Date();
        //等额本息
        if(ProductConstant.REFUND_WAY == productOutput.getRefundWay()){
            return BorrowUtils.collectionFixedPaymentMortgage(
                    productOutput.getProductPeriod(),
                    output.getPreAccount().doubleValue(),
                    productOutput.getBorrowApr().doubleValue(),
                    productOutput.getAwardScale().doubleValue(),
                    couponApr,now);
        }else{
            return BorrowUtils.collectionPerMonth(
                    productOutput.getProductPeriod(),
                    output.getPreAccount().doubleValue(),
                    productOutput.getBorrowApr().doubleValue(),
                    productOutput.getAwardScale().doubleValue(),
                    couponApr,now);
        }
    }

    /**
     * 生成投标的回款计划
     * @param list 回款计划列表
     * @param tenderOutput 投标信息
     * @return
     */
    private List<BorrowCollectionInput> createCollectionList(List<BorrowPlan> list,BorrowTenderOutput tenderOutput){

        List<BorrowCollectionInput> inputList = new ArrayList<>();
        //将生成的回款计划转换为接口入参
        if(list != null){
            BorrowCollectionInput input;
            for(BorrowPlan plan : list){
                input = new BorrowCollectionInput();
                BeanUtils.copyProperties(plan,input);
                input.setTenderId(tenderOutput.getId());
                input.setUserId(tenderOutput.getUserId());
                input.setIsShow(TenderConstant.COLLECTION_SHOW);
                input.setPreCollectionMonth(plan.getPreMonth());
                inputList.add(input);
            }
        }
        return inputList;
    }

    @Override
    public PageInfo<BorrowTenderVo> getUserTenderByPage(TenderListParam param) {
        PageInfo<BorrowTenderVo> paging = borrowTenderClient.getUserTenderByPage(param);

        if(paging == null){
            return paging;
        }

        List<Integer> productIds = FluentIterable.from(paging.getList()).transform(input -> input.getProductId()).toList();

        //根据产品id查询产品信息 做关联查询
        final List<ProductOutput> productOutputList = productClient.getListByIdList(productIds);

        List<BorrowTenderVo> list = FluentIterable.from(paging.getList()).transform(input -> {
            for (ProductOutput output : productOutputList){
                if (input.getProductId() == output.getId()){
                    input.setProductTitle(output.getProductTitle());
                    input.setProductCode(output.getProductCode());
                    input.setPeriod(output.getProductPeriod());
                    break;
                }
            }
            return input;
        }).toList();

        PageInfo<BorrowTenderVo> result = new PageInfo<>();
        result.setTotal(paging.getTotal());
        result.setList(list);
        return result;
    }

    @Override
    public PageInfo<BorrowTenderVo> getUserAutoTenderByPage(AutoTenderExtParam param) {
        return borrowTenderClient.getUserAutoTenderByPage(param);
    }

    @Override
    public BorrowTenderVo getTenderDetail(TenderDetailParam param) {
        BorrowTenderOutput tenderOutput = borrowTenderClient.getUserBorrowTenderById(param);

        ProductOutput productOutput = productClient.getProduct(tenderOutput.getBorrowId());

        BorrowTenderVo vo = new BorrowTenderVo();
        vo.setPreAccount(BigDecimalUtils.centToYuanFormat(tenderOutput.getPreAccount().doubleValue()));
        vo.setProductCode(productOutput.getProductCode());
        vo.setProductTitle(productOutput.getProductTitle());
        vo.setSourceStore(productOutput.getSourceStore());
        vo.setProductStatus(productOutput.getProductStatus());
        vo.setRefundWay(productOutput.getRefundWay());
        vo.setTenderTime(DateUtil.formatLong(tenderOutput.getAddTime()));
        //预期收益=平台加息+基本利息+加息券利息
        vo.setInterest(BigDecimalUtils.centToYuanFormat(tenderOutput.getAccountInterest().doubleValue()));
        vo.setAward(BigDecimalUtils.centToYuan(tenderOutput.getPlatformInterest().doubleValue()));

        //加息券不为空
        if(tenderOutput.getVoucherCouponId() != null && tenderOutput.getVoucherCouponId() > 0){
            vo.setVoucherType(2);
            vo.setVoucherMoney(tenderOutput.getVoucherMoney().doubleValue());
        }else if(tenderOutput.getVoucherId() != null && tenderOutput.getVoucherId() > 0){
            //抵用券不为空
            vo.setVoucherType(1);
            //抵用券金额
            vo.setVoucherMoney(BigDecimalUtils.centToYuan(tenderOutput.getVoucherMoney().doubleValue()));
        }else{
            vo.setVoucherType(0);
        }
        //到期时间
        vo.setEndTime(DateUtil.formatShort(productOutput.getRepayLastTime()));
        vo.setBaseApr(productOutput.getBorrowApr().doubleValue());
        //平台奖励利息值 %
        vo.setPlatformApr(productOutput.getAwardScale().doubleValue());
        return vo;
    }

    @Override
    public PageInfo<TenderTransferVo> getTenderTransferList(TransferQueryInfoParam param) {
        TenderQueryInput input = new TenderQueryInput();
        TransferUtil.transfer(param, input);
        input.setTenderType(1);

        PageInfo<TenderTransferVo> transferVoPageInfo = new PageInfo<>();
        PageInfo<BorrowTenderOutput> pageInfo = borrowTenderClient.getTenderTransferList(input);

        List<BorrowTenderOutput> list = pageInfo.getList();
        if(CollectionUtils.isEmpty(list)) {
            return transferVoPageInfo;
        }

        List<TenderTransferVo> tenderTransferVoList = new ArrayList<>();
        list.forEach(borrowTenderOutput -> {
            TenderTransferVo tenderTransferVo = new TenderTransferVo();
            TransferUtil.transfer(borrowTenderOutput, tenderTransferVo);

            String phone = borrowTenderOutput.getMobile();
            phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            tenderTransferVo.setMobile(phone);

            if(borrowTenderOutput.getChannel().equals(ChannelType.APP.getValue())) {
                tenderTransferVo.setApp(true);
            }
            tenderTransferVo.setAccount(BigDecimalUtils.centToYuan(tenderTransferVo.getAccount()));

            tenderTransferVoList.add(tenderTransferVo);
        });

        transferVoPageInfo.setList(tenderTransferVoList);
        transferVoPageInfo.setTotal(pageInfo.getTotal());
        return transferVoPageInfo;
    }

    /**
     * 定时任务处理投标
     */
    @Override
    public void tenderCorn(){
        List<BorrowTenderOutput> tenderList = borrowTenderClient.getListByStatus(TenderConstant.TENDER_FROST);
        /**
         * 定时任务处理投标 不管银行是否处理成功,均以失败做处理
         */
        if(tenderList != null && tenderList.size() > 0){
            tenderList.forEach(tender -> {
                boolean success = tenderStatusRequest(tender.getOrderId());
                if (success) {
                    this.tenderCancelTask(tender);
                } else {
                    borrowTenderClient.tenderFailTask(tender.getOrderId(), "投标失败,银行请求错误");
                }
            });
        }
    }

    @Override
    public void tenderCancelTask(BorrowTenderOutput borrowTender) {
        LOGGER.debug("定时任务撤销投标操作:{}",borrowTender);
        //标的满标,但银行已经成功,撤销投标操作
        UserAccountInfo userAccountInfo = getUserAccountInfo(borrowTender.getUserId());

        ProjectInfoCacheInfo projectInfo = projectInfoCache.getProjectInfo(borrowTender.getBorrowId());

        boolean success = tenderCancelRequest(borrowTender.getOrderId(), projectInfo.getProductCode(), userAccountInfo.getDepositNo());

        if (success) {
            borrowTenderClient.tenderFailTask(borrowTender.getOrderId(), "投标失败,银行请求错误");
        }else {
            LOGGER.error("定时任务撤销投标,银行失败,不做任务处理");
        }
    }

    /**
     * 撤销投标 请求银行
     * @param orderId
     * @param productCode
     * @param depositNo
     * @return
     */
    private boolean tenderCancelRequest(String orderId,String productCode,String depositNo){
        String orderNo = generateService.get();
        LOGGER.debug("定时任务撤销投标,orderId:{},productCode:{},depositNo:{},新订单号:{}",orderId,productCode,depositNo,orderNo);

        HashMap<String,String> map  = Maps.newHashMap();

        map.put(BJFN.platCust,depositNo);
        map.put(BJFN.prodId,productCode);
        map.put(BJFN.orderNo,orderNo);
        map.put(BJFN.oldOrderNo,orderId);

        HashMap<String, String> result = depositoryBiddingClient.cancel(map);

        LOGGER.debug("定时任务撤销投标结果:订单号{},结果集:{}",orderId, MapUtils.mapToString(map));

        if (result.get(TDFN.success) != null && result.get(TDFN.success).length() > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取用户信息 目前项目中唯一一个直接调用其他服务的方法
     * @param userId 用户ID
     * @return
     */
    private UserAccountInfo getUserAccountInfo(String userId){
        BasePojo pojo = new BasePojo();
        pojo.setUserId(userId);
        UserAccountInfo userAccountInfo = userClient.getUserAccountInfo(pojo);
        if(userAccountInfo == null){
            throw new BizFeignException(TransactError.USER_NOT_FOUND);
        }
        return userAccountInfo;
    }

    /**
     * 请求银行
     * @param orderId
     * @return
     */
    private boolean tenderStatusRequest(String orderId){
        LOGGER.debug("定时任务订单状态查询:{}",orderId);
        HashMap<String,String> map  = Maps.newHashMap();
        map.put(BJFN.queryOrderNo,orderId);
        HashMap<String, String> result = depositorySeekClient.orderStatus(map);
        LOGGER.debug("订单查询结果,订单号:{} 结果:{}",orderId, MapUtils.mapToString(map));
        if (result.get(TDFN.success) != null && result.get(TDFN.success).length() > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据用户id获取存管信息
     * @param userId
     * @return
     */
    private UserDepositOutput getUserDeposit(String userId){
        BasePojo pojo = new BasePojo();
        pojo.setUserId(userId);
        return userDepositFegin.getUserDepositInfo(pojo);
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    private UserAccountInfo getUserAccount(String userId){
        BasePojo pojo = new BasePojo();
        pojo.setUserId(userId);
        return userClient.getUserAccountInfo(pojo);
    }


    @Override
    public BorrowProtocolVo getBorrowProtocol(TenderDetailParam param) {

        BorrowTenderOutput borrowTender = borrowTenderClient.getUserBorrowTenderById(param);

        if(borrowTender == null){
            throw new MicroServiceException(TransactError.TENDER_NOT_FOUND);
        }

        ProductOutput product = productClient.getProduct(borrowTender.getBorrowId());

        if(product == null){
            throw new MicroServiceException(TransactError.BORROW_NOT_FOUND);
        }
        UserAccountInfo tenderUser = userClient.getUserAccountInfo(param);

        BasePojo pojo = new BasePojo();
        pojo.setUserId(param.getUserId());
        UserAccountInfo borrowUser = userClient.getUserAccountInfo(pojo);

        UserDepositOutput borrowerDeposit = userDepositFegin.getUserDepositInfo(pojo);

        UserDepositOutput tenderDeposit = userDepositFegin.getUserDepositInfo(param);

        BorrowProtocolVo vo = new BorrowProtocolVo();

        vo.setBorrowIdNum(StringUtil.hideIdNum(borrowerDeposit.getIdCard()));
        vo.setBorrowUserName(borrowUser.getUserName());

        vo.setIdNum(tenderDeposit.getIdCard());
        vo.setUserName(tenderUser.getUserName());
        vo.setMobile(tenderUser.getMobile());

        vo.setPeriod(product.getProductPeriod());
        vo.setPeriodUnit(product.getPeriodUnit());
        vo.setEndTime(DateUtil.formatShort(product.getRepayLastTime()));
        //TODO 投标计息开始日期
        vo.setStartTime(DateUtil.formatShort(new Date()));
        vo.setTenderMoney(BigDecimalUtils.centToYuanFormat(borrowTender.getPreAccount().doubleValue()));
        return vo;
    }


}
