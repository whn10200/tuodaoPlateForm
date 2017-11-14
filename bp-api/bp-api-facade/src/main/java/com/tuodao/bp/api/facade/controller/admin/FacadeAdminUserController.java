package com.tuodao.bp.api.facade.controller.admin;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.service.admin.IFacadeAdminUserService;
import com.tuodao.bp.model.admin.ChangeBankInput;
import com.tuodao.bp.model.admin.UserModifyMobileInput;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 后台调用用户请求
 * @author: mif
 * @date: 2017/11/9
 * @time: 11:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("router/admin/user")
public class FacadeAdminUserController {
    private static Logger logger = LoggerFactory.getLogger(FacadeAdminUserController.class);

    @Resource
    private IFacadeAdminUserService facadeAdminUserService;


    /**
     * 修改用户号码
     *
     * @param userModifyMobileInput 输入参数
     * @return 结果
     */
    @PostMapping(value = "/modifyMobile")
    @AccessToken(checkAccess = false, access = AccessType.MANAGER)
    public RespResult<String> modifyMobile(UserModifyMobileInput userModifyMobileInput) {
        logger.info("后台修改手机号码，userModifyMobileInput={}", userModifyMobileInput);
        facadeAdminUserService.modifyMobile(userModifyMobileInput);
        return RespResult.<String>create().setContent(null);
    }

    /**
     * 换卡
     *
     * @param changeBankInput 换卡
     * @return 结果
     */
    @PostMapping(value = "/changeBank")
    @AccessToken(checkAccess = false, access = AccessType.MANAGER)
    public RespResult<String> changeBank(ChangeBankInput changeBankInput) {
        logger.info("后台换卡，changeBankInput={}", changeBankInput);
        facadeAdminUserService.changeBank(changeBankInput);
        return RespResult.<String>create().setContent(null);
    }
}
