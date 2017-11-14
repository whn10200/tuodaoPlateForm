package com.tuodao.bp.traningcenter.client;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetLoginPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetPayPwInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 用户账户客户端
 * @author: 王艳兵
 * @date: 2017/10/22
 * @time: 10:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface UserClient {
    /**
     * 获取用户信息
     *
     * @param pojo
     * @return
     */
    @RequestMapping(value = "ua/getUserAccountInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserAccountInfo getUserAccountInfo(BasePojo pojo);


}
