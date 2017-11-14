package com.tuodao.bp.api.facade.client.useraccount;

import com.tuodao.bp.model.admin.UserModifyMobileInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 大后台接口
 * @author: mif
 * @date: 2017/11/9
 * @time: 17:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface AdminUserClient {

    /**
     * 修改手机号码
     *
     * @param userModifyMobileInput 手机内容
     */
    @RequestMapping(value = "admin/user/modifyMobile", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void modifyMobile(UserModifyMobileInput userModifyMobileInput);
}
