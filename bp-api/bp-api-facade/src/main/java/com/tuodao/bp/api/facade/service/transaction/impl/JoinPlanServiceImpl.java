package com.tuodao.bp.api.facade.service.transaction.impl;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.facade.client.useraccount.UserClient;
import com.tuodao.bp.api.facade.service.transaction.AccountService;
import com.tuodao.bp.api.facade.service.transaction.BorrowTenderService;
import com.tuodao.bp.cache.basic.traningcenter.SessionCache;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.input.TenderParam;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.DateUtil;
import com.tuodao.bp.utils.TransferUtil;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.transaction.JoinPlanClient;
import com.tuodao.bp.api.facade.service.transaction.JoinPlanService;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.JoinReturnContent;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
@Service("JoinPlanService")
public class JoinPlanServiceImpl implements JoinPlanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoinPlanServiceImpl.class);

    @Autowired
    ProductClient productClient;
    @Autowired
    JoinPlanClient joinPlanClient;
    @Autowired
    UserAccountCache userAccountCache;
    @Autowired
    UserDiscountsClient userDiscountsClient;
    @Autowired
    UserClient userClient;
    @Autowired
    AccountService accountService;
    @Autowired
    BorrowTenderService borrowTenderService;
    @Autowired
    private SessionCache sessionCache;



    @Override
    public JoinReturnContent joinPlan(TenderParam param,HttpServletRequest request,String key) {
        UserAccountInfo user = userAccountCache.getUserAccoutInfo(param.getUserId());
        borrowTenderService.verifyUser(user,param.getPayPassword());

        ProductOutput productOutput = productClient.getProduct(param.getBorrowId());
        String authCode = getAuthCode(request);
        borrowTenderService.verifyBorrow(productOutput, param, authCode, user);

        //根据borrowid查询产品信息
        ChoicenessTenderInput choicenessTenderInput = new ChoicenessTenderInput();
        JoinReturnContent joinReturnContent=new JoinReturnContent();


        choicenessTenderInput.setBorrowId(param.getBorrowId());
        choicenessTenderInput.setPreAccount(new BigDecimal(BigDecimalUtils.yuanToCent(param.getTenderMoney())));
        choicenessTenderInput.setRecoverType(productOutput.getRefundWay());
        choicenessTenderInput.setAwardApr(productOutput.getAwardScale());
        choicenessTenderInput.setBorrowType(productOutput.getBorrowType());
        choicenessTenderInput.setChannel(param.getChannel());
        choicenessTenderInput.setPeriod(productOutput.getProductPeriod());
        choicenessTenderInput.setUserId(param.getUserId());
        //查询可用余额
        AccountOutput account = accountService.getUserAccount(param.getUserId());
        double useMoney = account.getBalance().doubleValue();

        choicenessTenderInput.setVoucherApr(new BigDecimal(0));
        choicenessTenderInput.setAllApr(productOutput.getBorrowApr().add(productOutput.getAwardScale()));
        choicenessTenderInput.setVoucherCouponId(null);
        choicenessTenderInput.setVoucherId(null);
        choicenessTenderInput.setDkamount("0");
        //根据优惠券id查询优惠券信息 是否已使用
        if(param.getVoucherId()!=null)
        {
            IdInput idInput = new IdInput();
            idInput.setId((long) param.getVoucherId());
            UserDiscountOutput discountOutput = userDiscountsClient.getUserDiscountById(idInput);
            if(discountOutput==null)
            {
                //抛出该优惠券券不存在的异常
                throw new MicroServiceException(TransactError.VOUCHER_NOT_FOUND);
            }
            //如果过期
            else if(discountOutput.getDisStatus()==3)
            {
                //抛出该优惠券券不在使用期间
                throw new MicroServiceException(TransactError.VOUCHER_VALID_ERROR);
            }
            else if(discountOutput.getDisStatus()==2)
            {
                //抛出该优惠券券无法使用
                throw new MicroServiceException(TransactError.VOUCHER_USE_ERROR);
            }
            else
            {
                //抵扣卷
                if(discountOutput.getDiscountType()==1)
                {
                    choicenessTenderInput.setVoucherCouponId(param.getVoucherId());
                    if(productOutput.getProductPeriod()<discountOutput.getDateLimit())
                    {
                        //抛出优惠券使用期限不匹配
                        throw new MicroServiceException(TransactError.VOUCHER_LIMIT_ERROR);
                    }
                    int r=choicenessTenderInput.getPreAccount().compareTo(new BigDecimal(discountOutput.getMoneyLimit()));
                    if(r==-1)
                    {
                        //抛出投标金额不满足优惠券使用额度
                        throw new MicroServiceException(TransactError.VOUCHER_MONEY_ERROR);
                    }
                    choicenessTenderInput.setDkamount(discountOutput.getDiscountAvailable());
                    choicenessTenderInput.setMoneyLimit(new BigDecimal(discountOutput.getMoneyLimit()));
                }
                else
                {
                    choicenessTenderInput.setVoucherId(param.getVoucherId());
                    String apr = discountOutput.getDiscountAvailable().replaceAll("%", "");
                    choicenessTenderInput.setVoucherApr(new BigDecimal(apr));
                    choicenessTenderInput.setAllApr(choicenessTenderInput.getAllApr().add(new BigDecimal(apr)));
                }
            }
        }
        //可用余额不够
        if(useMoney < (choicenessTenderInput.getPreAccount().multiply(new BigDecimal(100)).doubleValue()-Double.valueOf(choicenessTenderInput.getDkamount()))){
            throw new MicroServiceException(TransactError.ACCOUNT_BALANCE_ERROR);
        }
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId(choicenessTenderInput.getUserId());
        String mobile =userClient.getUserAccountInfo(basePojo).getMobile();
        choicenessTenderInput.setKey(key);
        choicenessTenderInput.setIp(request.getRemoteAddr());
        choicenessTenderInput.setMobile(mobile);
        //调取加入理财计划接口 入库
        joinPlanClient.insertChoiceness(choicenessTenderInput);
        return null;
    }

    /**
     * 获取验证码
     * @param request
     * @return
     */
    private String getAuthCode(HttpServletRequest request){

        String accessKey = request.getHeader(TenderConstant.ACCESS_ID);

        return sessionCache.getSession(accessKey);
    }


   public PageInfo<TenderRecord> getJoinLists(TenderTraRecordInput tenderTrRecordInput)
   {
       PageInfo<TenderRecord> tenderRecordPageInfo = joinPlanClient.getJoinLists(tenderTrRecordInput);
       return  tenderRecordPageInfo;
   }

    @Override
    public PageInfo<SelectTenderOutput> getTenderByUserId(SelectTenderInput tenderInput)
    {
        PageInfo<SelectTenderOutput> selectTenderOutputPageInfo = joinPlanClient.getTenderByUserId(tenderInput);
        List<SelectTenderOutput> list = selectTenderOutputPageInfo.getList();
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<SelectTenderOutput> selectTenderOutputs = TransferUtil.transferList(list, SelectTenderOutput.class);
        List<SelectTenderOutput> selectTenderOutputss = new ArrayList<>();
        selectTenderOutputs.forEach(selectTenderOutput -> {
            //根据borrowId查询标的名称以及borrownid
            int borrowId = selectTenderOutput.getBorrowId();
            ProductOutput productOutput = productClient.getProduct(borrowId);
            selectTenderOutput.setProductName(productOutput.getProductTitle());
            selectTenderOutput.setBorrowNid(productOutput.getProductCode());
            Integer couvherId = selectTenderOutput.getVoucherId()!=null?selectTenderOutput.getVoucherId():selectTenderOutput.getVoucherCouponId();
            if(couvherId==null)
            {
                selectTenderOutput.setUseVoucherRemark("无");
            }
            else
            {
                IdInput idInput = new IdInput();
                idInput.setId((long)couvherId);
                idInput.setUserId(tenderInput.getUserId());
                UserDiscountOutput discountOutput = userDiscountsClient.getUserDiscountById(idInput);
                if(discountOutput==null)
                {
                    //抛出该优惠券券不存在的异常
                    throw new MicroServiceException(TransactError.VOUCHER_NOT_FOUND);
                }
                else
                {
                    //抵扣卷
                    if(discountOutput.getDiscountType()==1)
                    {
                        selectTenderOutput.setUseVoucherRemark("抵扣"+discountOutput.getDiscountAvailable()+"元");
                    }
                    //加息卷
                    else if(discountOutput.getDiscountType()==2)
                    {
                        selectTenderOutput.setUseVoucherRemark("加息"+discountOutput.getDiscountAvailable());
                    }
                }
            }
            selectTenderOutputss.add(selectTenderOutput);
        });
        selectTenderOutputPageInfo.setList(selectTenderOutputss);
        return  selectTenderOutputPageInfo;
    }


    @Override
    public TenderDetailsOutput selectTenderById(JustIdInput justIdInput)
    {
        TenderDetailsOutput tenderDetailsOutput = joinPlanClient.selectTenderById(justIdInput);
        ProductOutput productOutput = productClient.getProduct(tenderDetailsOutput.getBorrowId());
        tenderDetailsOutput.setBorrowNid(productOutput.getProductCode());
        tenderDetailsOutput.setProductName(productOutput.getProductTitle());
        tenderDetailsOutput.setRepayType(productOutput.getRefundWay().toString());
        tenderDetailsOutput.setOutTime(DateUtil.formatShort(productOutput.getRepayLastTime()));
        tenderDetailsOutput.setBorrowType(productOutput.getDefineType().toString());
        return  tenderDetailsOutput;
    }


    @Override
    public PageInfo<UnderTenderOutput> selectTenderListByChoicenessTenderId(JustIdInput justIdInput)
    {
        PageInfo<UnderTenderOutput> selectTenderOutputPageInfo = joinPlanClient.selectTenderListByChoicenessTenderId(justIdInput);
        List<UnderTenderOutput> list = selectTenderOutputPageInfo.getList();
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<UnderTenderOutput> underTenderOutputs = TransferUtil.transferList(list, UnderTenderOutput.class);
        List<UnderTenderOutput> underTenderOutputss= new ArrayList<>();
        underTenderOutputs.forEach(underTenderOutput -> {
            //根据borrowId查询标的名称以及borrownid
            ProductOutput productOutput = productClient.getProduct(Integer.parseInt(underTenderOutput.getBorrowId()));
            underTenderOutput.setProductName(productOutput.getProductTitle());
            underTenderOutput.setBorrowNid(productOutput.getProductCode());
            underTenderOutputss.add(underTenderOutput);
        });
        selectTenderOutputPageInfo.setList(underTenderOutputss);
        return  selectTenderOutputPageInfo;
    }

    @Override
    public PageInfo<RecoverListOutput> selectRecoverListBychioId(JustIdInput justIdInput)
    {
        return joinPlanClient.selectRecoverListBychioId(justIdInput);
    }
    @Override
    public TenderTraRecordOutput getMaxAndLast(TenderTraRecordInput tenderTrRecordInput)
    {
        return joinPlanClient.getMaxAndLast(tenderTrRecordInput);
    }
}
