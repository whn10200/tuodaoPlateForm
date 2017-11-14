package com.tuodao.bp.api.facade.client.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.ConsigneeInfoInput;
import com.tuodao.bp.model.business.useraccount.output.UserInfoDetailOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 用户详情FEIGN
 * @author: mif
 * @date: 2017/10/12
 * @time: 15:06
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface UserInfoDetailClient {

    /**
     * 用户详情
     *
     * @param basePojo 基础POJO
     * @return 用户详情
     */
    @RequestMapping(value = "ua/userDetail/getUserInfoDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserInfoDetailOutput getUserInfoDetail(BasePojo basePojo);

    /**
     * @param consigneeInfoInput 收件信息
     */
    @RequestMapping(value = "ua/userDetail/updateConsigneeInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateConsigneeInfo(ConsigneeInfoInput consigneeInfoInput);
}
