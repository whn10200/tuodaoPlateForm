package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.service.transaction.AccountLogService;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 资金记录
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 17:56
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router")
public class AccountLogController {


    @Autowired
    private AccountLogService accountLogService;
    /**
     * 分页获取资金记录列表
     * @param param
     * @return
     */
    @RequestMapping("/tc/account/account_log_list")
    @AccessToken(access = {AccessType.PC})
    public RespResult<PageInfo<AccountLogVo>> getAccountLogList(AccountLogParam param){
        PageInfo<AccountLogVo> paging = accountLogService.getUserAccountLogByPage(param);
        return RespResult.<PageInfo<AccountLogVo>>create().setContent(paging);
    }

    @RequestMapping("/tc/account/app_account_log_list")
    @AccessToken(access = {AccessType.APP})
    public RespResult<PageInfo<AppAccountLogVo>> getAppAccountLogList(AccountLogParam param){
        PageInfo<AppAccountLogVo> page = accountLogService.getAppUserAccountLogByPage(param);

        return RespResult.<PageInfo<AppAccountLogVo>>create().setContent(page);
    }

}
