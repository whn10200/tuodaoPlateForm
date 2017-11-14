package com.tuodao.bp.useraccount.controller.admin;

import com.tuodao.bp.model.admin.UserModifyMobileInput;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.useraccount.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 大后台用户控制类
 * @author: mif
 * @date: 2017/11/9
 * @time: 17:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("admin/user")
public class AdminUserController {
    private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Resource
    private IUserService userService;

    /**
     * 修改用户号码
     *
     * @param userModifyMobileInput 输入参数
     */
    @PostMapping(value = "/modifyMobile")
    @AccessToken(checkAccess = false, access = AccessType.PC)
    public void modifyMobile(UserModifyMobileInput userModifyMobileInput) {
        logger.info("后台修改手机号码，userModifyMobileInput={}", userModifyMobileInput);
        userService.modifyMobile(userModifyMobileInput);
    }
}
