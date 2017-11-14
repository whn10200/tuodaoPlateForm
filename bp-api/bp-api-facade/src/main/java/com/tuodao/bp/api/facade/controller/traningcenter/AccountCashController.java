package com.tuodao.bp.api.facade.controller.traningcenter;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.api.facade.client.transaction.AccountClient;
import com.tuodao.bp.api.facade.client.useraccount.UserDepositFegin;
import com.tuodao.bp.api.facade.service.transaction.AccountCashService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.constant.traningcenter.CashConstant;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.AccountCashParam;
import com.tuodao.bp.model.facade.traningcenter.input.CashCalcParam;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashExtVo;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Map;


/**
 * @description: 提现
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 14:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router")
@RestController
public class AccountCashController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountCashController.class);

    @Autowired
    private AccountCashService accountCashService;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private UserDepositFegin userDepositFegin;

    @Autowired
    private UserDiscountsClient userDiscountsClient;


    /**
     * 前台提现申请
     * @return
     */
    @RequestMapping("/tc/account/cash_apply")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<String> applyCash(AccountCashParam param) throws Exception{
        LOGGER.debug("提现申请,前台入参:{}",param);
        if(BorrowUtils.isClearingTime()){
            throw new MicroServiceException(TransactError.TENDER_TIME_ERROR);
        }
        param.setCashMoney(BigDecimalUtils.yuanToCent(param.getCashMoney()));

        double cashFee = accountCashService.cashApply(param);

        return  RespResult.<String>create().setContent(String.valueOf(BigDecimalUtils.centToYuan(cashFee)));
    }

    /**
     * 提现页面内容数据请求
     * @return
     */
    @RequestMapping("/tc/account/to_cash")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<AccountCashExtVo> toCash(BasePojo pojo) throws ParseException{
        AccountCashExtVo vo = new AccountCashExtVo();
        UserDepositOutput userDeposit = userDepositFegin.getUserDepositInfo(pojo);
        if(userDeposit == null){
            //没有开通存管
            vo.setStatus(CashConstant.DEPOSIT_STATUS_0);
            return RespResult.<AccountCashExtVo>create().setContent(vo);
        }else{
            //已开通存管
            AccountOutput account = accountClient.getUserAccount(pojo.getUserId());
            UserFreeNumberOutput user = userDiscountsClient.getUserFreeNumber(pojo);
            double bankBalanceFrost = accountCashService.getBankBalanceFrost(pojo.getUserId());
            /**
             * 可提现金额 = 本地可用余额-银行冻结金额
             * 原因:当用户发生债权转让时,如果受让人刚充值进来的钱直接投资该债权,这部分钱是未清算的 无法提现
             */
            vo.setBalanceValue(BigDecimalUtils.sub(BigDecimalUtils.centToYuan(account.getBalance().doubleValue()),bankBalanceFrost));
            vo.setBalance(BigDecimalUtils.formatMoney(vo.getBalanceValue()));
            //已投资 可提现金额
            vo.setTenderCash(BigDecimalUtils.centToYuan(account.getBalanceCash().doubleValue()));
            //未投资 可提现金额 = 可用余额-已投资可提现金额-银行未清算金额 = 可提现金额-已投资可提现金额
            vo.setChargeCash(BigDecimalUtils.sub(vo.getBalanceValue(),BigDecimalUtils.centToYuan(account.getBalanceCash().doubleValue())));

            vo.setBankCode(userDeposit.getBankCode());
            vo.setBankNum(StringUtil.hideBankCard(userDeposit.getBankNum(),4));
            vo.setCashNum(user.getFreeNumber());
            vo.setStatus(CashConstant.DEPOSIT_STATUS_1);
            vo.setClearTime(BorrowUtils.isClearingTime());
            return RespResult.<AccountCashExtVo>create().setContent(vo);
        }
    }

    /**
     * 计算提现手续费
     * @param param 提现金额
     * @return
     */
    @RequestMapping("/tc/account/cash_fee")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<Map<String,String>> getCashFee(CashCalcParam param){
        AccountOutput account = accountClient.getUserAccount(param.getUserId());

        UserFreeNumberOutput user = userDiscountsClient.getUserFreeNumber(param);

        double fee = accountCashService.getFee(user,account,BigDecimalUtils.yuanToCent(param.getCashMoney()));
        fee = BigDecimalUtils.centToYuan(fee);
        double realAccount = BigDecimalUtils.sub(param.getCashMoney(),fee);
        Map<String,String> map = Maps.newHashMap();
        //手续费
        map.put("fee",BigDecimalUtils.formatMoney(fee));
        //实际到账金额
        map.put("realAccount",BigDecimalUtils.formatMoney(realAccount));
        return RespResult.<Map<String,String>>create().setContent(map);
    }

    /**
     * 分页获取用户的提现列表
     * @return
     */
    @RequestMapping("/tc/account/cash_list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<PageInfo<AccountCashVo>> cashList(CashListParam param){
        PageInfo<AccountCashVo> paging =  accountCashService.getUserCashListByPage(param);
        return RespResult.<PageInfo<AccountCashVo>>create().setContent(paging);
    }


}