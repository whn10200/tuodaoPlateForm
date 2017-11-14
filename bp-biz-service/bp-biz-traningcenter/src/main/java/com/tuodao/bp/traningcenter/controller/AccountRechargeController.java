package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeFastOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeOutput;
import com.tuodao.bp.traningcenter.db.model.basic.AccountRecharge;
import com.tuodao.bp.traningcenter.service.AccountRechargeService;
import com.tuodao.bp.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description 充值
 */
@RestController
@RequestMapping("recharge")
public class AccountRechargeController {

    private static Logger logger = LoggerFactory.getLogger(AccountRechargeController.class);


    @Autowired
    private AccountRechargeService accountRechargeService;

    @Autowired
    private BankCache bankCache;

    /**
     * 银行充值
     * @param rechargeInput
     * @return
     */
    @RequestMapping(value = "onlineBankRingRecharge", method = RequestMethod.POST)
    public void onlineBankRecharge(RechargeGatewayInput rechargeInput) {
        accountRechargeService.onlineBankRecharge(rechargeInput);
    }

    /**
     * 快捷充值 发送短信
     * @param input
     */
    @RequestMapping(value = "sendSmsCode", method = RequestMethod.POST)
    public RechargeFastOutput sendSmsCode(RechargeFastCodeInput input) {
        RechargeFastOutput output = accountRechargeService.sendSmsCode(input);
        return output;
    }

    /**
     * 快捷充值确认
     * @param input
     * @return RechargeConfirmOutput
     */
    @RequestMapping(value = "fastRecharge", method = RequestMethod.POST)
    public RechargeConfirmOutput fastRecharge(RechargeFastInput input) {
        return accountRechargeService.fastRecharge(input);
    }

    /**
     * 更新充值记录
     * @param input
     */
    @RequestMapping(value = "updateRecharge")
    public void updateRecharge(FastRechargeInput input) {
        AccountRecharge accountRecharge = new AccountRecharge();
        TransferUtil.transfer(input, accountRecharge);
        accountRechargeService.update(accountRecharge);
    }


    /**
     * 获取充值记录
     * @param input
     * @return
     */
    @RequestMapping(value = "getByOrderNo")
    public RechargeOutput getByOrderNo(RechargeOrderInput input) {
        AccountRecharge accountRecharge =  accountRechargeService.getByOrderNo(input.getOrderNo());
        RechargeOutput rechargeOutput = new RechargeOutput();
        TransferUtil.transfer(accountRecharge, rechargeOutput);
        return rechargeOutput;
    }


    /**
     * 存管回调
     * @param input
     */
    @RequestMapping(value = "/notice", method = RequestMethod.POST)
    public void notice(RechargeOrderInput input) {
        accountRechargeService.asyn(input);
    }

}
