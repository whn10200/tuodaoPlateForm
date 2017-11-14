package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.input.AccountCashApplyInput;
import com.tuodao.bp.model.traningcenter.output.AccountCashOutput;
import com.tuodao.bp.traningcenter.service.AccountCashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王艳兵
 */
@RestController
public class AccountCashController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountCashController.class);
	
	@Autowired
	private AccountCashService accountCashService;


    /**
     * 提现申请请求银行
     * @param input
     */
    @RequestMapping(value = "/account/cashBankRequest",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void cashBankRequest(AccountCashApplyInput input){
        LOGGER.debug("提现申请请求银行,入参:{}",input);
        accountCashService.cashBankRequest(input);
    }

    /**
     * 获取用户的已经提现的总额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getCashTotalByUserId",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountCashOutput getCashTotalByUserId(@RequestParam("userId") String userId){
        LOGGER.debug("获取用户已经提现的总额,入参:{}",userId);
        double total = accountCashService.getCashTotalByUserId(userId);
        LOGGER.debug("获取用户已经提现的总额,出参:{}",total);
        return new AccountCashOutput(total);
    }

    /**
     * 分页获取用户的提现记录
     * @return
     */
    @RequestMapping(value = "/user/account/getUserCashListByPage",produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<AccountCashVo> getUserCashListByPage(CashListParam param){
        LOGGER.debug("分页获取用户提现记录入参:{}",param);
        return accountCashService.getUserCashListByPage(param);
    }

}
