package com.tuodao.bp.api.facade.service.transaction.impl;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;
import com.tuodao.bp.utils.DateUtil;
import com.tuodao.bp.api.facade.client.transaction.AccountLogClient;
import com.tuodao.bp.api.facade.service.transaction.AccountLogService;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 18:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("accountLogService")
public class AccountLogServiceImpl implements AccountLogService {

    @Autowired
    private AccountLogClient accountLogClient;

    @Override
    public PageInfo<AccountLogVo> getUserAccountLogByPage(AccountLogParam param) {
        AccountLogInput input = new AccountLogInput();
        BeanUtils.copyProperties(param,input);

        if(param.getEndTime() != null){
            //spring数据绑定如果忽略 时分秒在查询时需要额外一天才能查询到对应天的数据
            input.setEndTime(DateUtil.addDays(input.getEndTime(),1));
        }
        return accountLogClient.getUserAccountLogByPage(input);
    }

    @Override
    public PageInfo<AppAccountLogVo> getAppUserAccountLogByPage(AccountLogParam param) {
        return null;
    }


}
