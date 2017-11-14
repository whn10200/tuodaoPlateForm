package com.tuodao.bp.api.facade.controller.traningcenter;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.InviteClient;
import com.tuodao.bp.api.facade.client.transaction.AccountClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowCollectionClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowTenderClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.output.AccountAppVo;
import com.tuodao.bp.model.facade.traningcenter.output.AccountVo;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.BigDecimalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 账户信息
 * @author: 王艳兵
 * @date: 2017/10/20
 * @time: 15:27
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router")
public class AccountController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BorrowCollectionClient borrowCollectionClient;

    @Autowired
    private BorrowTenderClient borrowTenderClient;

    @Autowired
    private InviteClient inviteClient;

    /**
     * 获取用户资产信息
     * @param pojo
     * @return
     */
    @RequestMapping("/tc/user/account/get_account")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<AccountVo> getUserAccount(BasePojo pojo){
        AccountOutput account = accountClient.getUserAccount(pojo.getUserId());
        double userTenderFrost = borrowTenderClient.getUserTenderFrost(pojo.getUserId());
        AccountVo vo = new AccountVo();
        vo.setTotalAwait(BigDecimalUtils.centToYuanFormat(account.getTotal()));
        //待收本金
        vo.setTotalAwaitCapitalValue(BigDecimalUtils.centToYuan(account.getAwaitCapital().doubleValue()));
        vo.setTotalAwaitCapital(BigDecimalUtils.formatMoney(vo.getTotalAwaitCapitalValue()));
        //待收利息
        vo.setTotalAwaitInterestValue(BigDecimalUtils.centToYuan(account.getAwaitInterest().doubleValue()));
        vo.setTotalAwaitInterest(BigDecimalUtils.formatMoney(vo.getTotalAwaitInterestValue()));
        //总待收
        vo.setTotalAwait(BigDecimalUtils.centToYuanFormat(account.getAwaitCapital().add(account.getAwaitInterest())));
        //可用余额
        vo.setTotalBalanceValue(BigDecimalUtils.centToYuan(account.getBalance().doubleValue()));
        vo.setTotalBalance(BigDecimalUtils.formatMoney(vo.getTotalBalanceValue()));
        //总收益
        vo.setTotalEarnings(BigDecimalUtils.centToYuanFormat(account.getTotalEarnings()));
        //总资产
        vo.setTotalCapital(BigDecimalUtils.centToYuanFormat(account.getTotal()));
        //散标投标冻结
        vo.setTotalTenderFrost(BigDecimalUtils.centToYuanFormat(userTenderFrost));
        //提现冻结
        vo.setTotalCashFrost(BigDecimalUtils.centToYuanFormat(account.getCashFrost().doubleValue()));
        //精选计划投标冻结= 投标冻结-散标投标冻结
        vo.setTotalPlanTenderFrost(BigDecimalUtils.centToYuanFormat(BigDecimalUtils.sub(account.getTenderFrost().doubleValue(),userTenderFrost)));
        //总冻结金额
        vo.setTotalFrostValue(BigDecimalUtils.centToYuan(account.getCashFrost().add(account.getTenderFrost()).doubleValue()));
        vo.setTotalFrost(BigDecimalUtils.formatMoney(vo.getTotalFrostValue()));


        return RespResult.<AccountVo>create().setContent(vo);
    }


    /**
     * 获取用户资产信息 app端使用
     * @param pojo
     * @return
     */
    @RequestMapping("/tc/user/account/get_account_detail")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<AccountAppVo> getUserAccountDetail(BasePojo pojo){

        AccountOutput account = accountClient.getUserAccount(pojo.getUserId());

        CollectionCountOutput userCollectionInterest = borrowCollectionClient.getUserCollectionInterest(pojo.getUserId());
        if(userCollectionInterest == null){
            LOGGER.error("获取回款收益信息失败,userId:{}",pojo.getUserId());
            throw new MicroServiceException(TransactError.ACCOUNT_COUNT_ERROR);
        }
        double tenderVoucherCount = borrowTenderClient.getTenderVoucherCount(pojo.getUserId());
        //邀请奖励金额
        double inviteInterest = inviteClient.getTotalInviteAward(pojo).doubleValue();

        AccountAppVo vo = new AccountAppVo();
        //总资产
        vo.setTotal(BigDecimalUtils.centToYuanFormat(account.getTotal()));

        vo.setBalance(BigDecimalUtils.centToYuanFormat(account.getBalance()));
        vo.setBalanceValue(String.valueOf(BigDecimalUtils.centToYuan(account.getBalance().doubleValue())));

        //待收总额=待收本金+代收利息
        vo.setAwaitTotal(BigDecimalUtils.centToYuanFormat(account.getAwaitInterest().add(account.getAwaitCapital())));
        vo.setAwaitTotalValue(String.valueOf(BigDecimalUtils.centToYuan(account.getAwaitInterest().add(account.getAwaitCapital()).doubleValue())));

        vo.setAwaitCapital(BigDecimalUtils.centToYuanFormat(account.getAwaitCapital()));
        vo.setAwaitInterest(BigDecimalUtils.centToYuanFormat(account.getAwaitInterest()));

        vo.setFrostTotal(BigDecimalUtils.centToYuanFormat(account.getCashFrost().add(account.getTenderFrost())));
        vo.setFrostTotalValue(String.valueOf(BigDecimalUtils.centToYuan(account.getCashFrost().add(account.getTenderFrost()).doubleValue())));

        vo.setTenderInterest(BigDecimalUtils.centToYuanFormat(userCollectionInterest.getTenderInterest()));
        vo.setTenderInterestValue(String.valueOf(BigDecimalUtils.centToYuan(userCollectionInterest.getTenderInterest().doubleValue())));
        //优惠券奖励
        double voucherInterest = BigDecimalUtils.add(tenderVoucherCount,userCollectionInterest.getCouponInterest().doubleValue());

        vo.setVoucherInterest(BigDecimalUtils.centToYuanFormat(voucherInterest));
        vo.setVoucherInterestValue(String.valueOf(BigDecimalUtils.centToYuan(voucherInterest)));

        vo.setPlatformInterest(BigDecimalUtils.centToYuanFormat(userCollectionInterest.getPlatformInterest()));
        vo.setPlatformInterestValue(String.valueOf(BigDecimalUtils.centToYuan(userCollectionInterest.getPlatformInterest().doubleValue())));

        vo.setInviteInterest(BigDecimalUtils.centToYuanFormat(inviteInterest));
        vo.setInviteInterestValue(String.valueOf(BigDecimalUtils.centToYuan(inviteInterest)));

        double total = BigDecimalUtils.add(
                BigDecimalUtils.add(
                        BigDecimalUtils.add(inviteInterest,voucherInterest),
                        userCollectionInterest.getTenderInterest().doubleValue()
                )
                ,userCollectionInterest.getPlatformInterest().doubleValue());
        vo.setInterestTotal(BigDecimalUtils.centToYuanFormat(total));

        return RespResult.<AccountAppVo>create().setContent(vo);
    }


}
