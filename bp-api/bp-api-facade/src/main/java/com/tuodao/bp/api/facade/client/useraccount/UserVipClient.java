package com.tuodao.bp.api.facade.client.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import com.tuodao.bp.model.business.useraccount.output.UserVipInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 用户VIP相关Client
 * @author: mif
 * @date: 2017/10/25
 * @time: 11:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface UserVipClient {
    /**
     * 查询用户VIP信息
     *
     * @param basePojo 基础类
     * @return Vip信息
     */
    @RequestMapping(value = "ua/userVip/getUserVipInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserVipInfoOutput getUserVipInfo(BasePojo basePojo);
}
