package com.tuodao.bp.api.facade.service.transaction.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.facade.client.depository.DepositorySeekClient;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.api.facade.client.transaction.AccountCashClient;
import com.tuodao.bp.api.facade.client.transaction.AccountClient;
import com.tuodao.bp.api.facade.client.useraccount.UserDepositFegin;
import com.tuodao.bp.api.facade.service.transaction.AccountCashService;
import com.tuodao.bp.api.facade.service.transaction.GenerateService;
import com.tuodao.bp.api.facade.service.transaction.UserService;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.traningcenter.CashConstant;
import com.tuodao.bp.model.facade.operation.input.FindBankInfoInput;
import com.tuodao.bp.model.facade.operation.output.FindBankInfoOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AccountCashParam;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.input.*;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * @description: 提现
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 16:18
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("accountCashService")
public class AccountCashServiceImpl implements AccountCashService  {

    @Autowired
    private AccountCashClient accountCashClient;

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountCache userAccountCache;

    @Autowired
    private UserDiscountsClient userDiscountsClient;

    @Autowired
    private UserDepositFegin userDepositFegin;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private DepositorySeekClient depositorySeekClient;

    @Autowired
    private GenerateService generateService;

    @Override
    public double cashApply(AccountCashParam param) {

        UserAccountInfo user = userAccountCache.getUserAccoutInfo(param.getUserId());

        userService.verifyUserCommon(user,true);

        userService.verifyUserPayPassword(user.getUserId(),param.getPayPassword());

        AccountOutput account = accountClient.getUserAccount(param.getUserId());

        UserDepositOutput userDepositInfo = userDepositFegin.getUserDepositInfo(param);

        UserFreeNumberOutput userFreeNumber = userDiscountsClient.getUserFreeNumber(param);

        double cashFee = getFee(userFreeNumber,account,param.getCashMoney());

        //参数组装
        AccountCashApplyInput accountCash = new AccountCashApplyInput();

        accountCash.setUserId(param.getUserId());

        accountCash.setBankNum(userDepositInfo.getBankNum());
        //TODO 后期修改数据来源
        accountCash.setSource(1);

        accountCash.setFee(BigDecimal.valueOf(cashFee));

        accountCash.setAccount(BigDecimal.valueOf(param.getCashMoney()));

        accountCash.setOrderNo("TD_" + generateService.get());

        //免费提现次数大于0 自动使用免费提现次数 否则不使用
        accountCash.setUseFree((userFreeNumber != null && userFreeNumber.getFreeNumber() > 0) ? 1 : 0);

        if (account.getBalanceCash().doubleValue() > param.getCashMoney()){
            accountCash.setBalanceCash(BigDecimal.valueOf(param.getCashMoney()));
        }else{
            accountCash.setBalanceCash(account.getBalanceCash());
        }

        accountCashClient.cashBankRequest(accountCash);

        return cashFee;
    }



    @Override
    public double getFee(UserFreeNumberOutput user,AccountOutput account, double cashMoney) {
        if (cashMoney > CashConstant.MAX_CASH){
            throw new MicroServiceException(TransactError.CASH_MAX_ERROR);
        }
        if(cashMoney > account.getBalance().subtract(account.getRecharge()).doubleValue()){
            throw new MicroServiceException(TransactError.CASH_MONEY_ERROR);
        }
        if(user == null || user.getFreeNumber() <= 0){
            //无免费提现次数且免费提现金额大于提现金额
            if (account.getBalanceCash().doubleValue() >= cashMoney){
                return CashConstant.CASH_FEE;
            }
            double sub = BigDecimalUtils.sub(cashMoney, account.getBalanceCash().doubleValue());
            //未投资部分手续费
            double percent = BigDecimalUtils.mul(sub, CashConstant.CASH_FEE_PERCENT);
            //小于3元则按3元计算 否则按实际计算
            return percent <= CashConstant.CASH_FEE ? CashConstant.CASH_FEE : percent;
        }
        return 0D;
    }


    @Override
    public PageInfo<AccountCashVo> getUserCashListByPage(CashListParam param) {

        PageInfo<AccountCashVo> paging =  accountCashClient.getUserCashListByPage(param);

        List<String> bankIdList = FluentIterable.from(paging.getList()).transform(input -> input.getBankNum()).toList();
        FindBankInfoInput input = new FindBankInfoInput();
        input.setUserId(param.getUserId());
        input.setBankIds(bankIdList);
        List<FindBankInfoOutput> lists = userDepositFegin.findBanksByBankIds(input);

        List<AccountCashVo> accountCashVos = FluentIterable.from(paging.getList()).transform(cashVo -> {
            for (FindBankInfoOutput bank : lists) {
                if (bank.getBankCardId().equals(cashVo.getBankNum())) {
                    cashVo.setBankName(bank.getBankName());
                    cashVo.setBankNum(StringUtil.hideBankCard(cashVo.getBankNum(),4));
                    return cashVo;
                }
            }
            return cashVo;
        }).toList();

        PageInfo<AccountCashVo> result = new PageInfo<>();
        result.setList(accountCashVos);
        result.setTotal(paging.getTotal());

        return result;
    }

    @Override
    public double getBankBalanceFrost(String userId) {
        UserAccountInfo userAccoutInfo = userAccountCache.getUserAccoutInfo(userId);
        if(userAccoutInfo == null){
            throw new MicroServiceException(TransactError.USER_TIME_OUT_ERROR);
        }
        if(StringUtils.isBlank(userAccoutInfo.getDepositNo())){
            throw new MicroServiceException(TransactError.BANK_STATUS_ERROR);
        }

        if(true){
            return 0D;
        }
        //TODO 目前尚未和存管服务联通
        HashMap<String, String> map = Maps.newHashMap();
        map.put(BJFN.account,userAccoutInfo.getDepositNo());
        //12 投资账户 13:融资账户
        map.put(BJFN.acctType,"12");
        HashMap<String, String> result = depositorySeekClient.fundBalance(map);
        if (result.get(TDFN.success) != null && result.get(TDFN.success).length() > 0){
            return Double.parseDouble(result.get(BJFN.balance));
        }
        throw new MicroServiceException(TransactError.BANK_REQUEST_ERROR);
    }
}
