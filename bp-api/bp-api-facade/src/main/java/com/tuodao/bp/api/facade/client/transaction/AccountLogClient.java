package com.tuodao.bp.api.facade.client.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogExtInput;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @description: 资金记录接口
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 18:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface AccountLogClient {

    /**
     * 分页查询用户资金记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/getUserAccountLogByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<AccountLogVo> getUserAccountLogByPage(AccountLogInput input);

    /**
     * 其他服务调用 添加资金记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/addAccountLog",consumes = MediaType.APPLICATION_JSON_VALUE)
    void addAccountLog(AccountLogExtInput input);

    /**
     * 分页获取资金流水 app使用
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAppUserAccountLogByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<AppAccountLogVo> getAppUserAccountLogByPage(AccountLogParam param);
}
