package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogExtInput;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @description: 资金记录接口
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 19:06
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class AccountLogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountLogController.class);

    @Autowired
    private AccountLogService accountLogService;

    /**
     * 分页获取资金记录信息
     * @param input
     * @return
     */
    @RequestMapping(value = "/getUserAccountLogByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<AccountLogVo> getUserAccountLogByPage(AccountLogInput input){
        LOGGER.debug("分页获取资金记录,入参:{}",input);
        return accountLogService.getUserAccountLogByPage(input);
    }

    /**
     * 其他服务调用 添加资金记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/addAccountLog",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addAccountLog(AccountLogExtInput input){
        LOGGER.debug("其他服务调用,添加资金记录入参:{}",input);
        AccountLog log = TransferUtil.transferBean(input, AccountLog.class);
        log.setAddTime(new Date());
        accountLogService.updateAccountAndLogPro(log);
    }


    /**
     * 分页获取资金流水 app使用
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAppUserAccountLogByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<AppAccountLogVo> getAppUserAccountLogByPage(AccountLogParam param){
        LOGGER.debug("app获取资金流水,入参:{}",param);
        return null;
    }

}
