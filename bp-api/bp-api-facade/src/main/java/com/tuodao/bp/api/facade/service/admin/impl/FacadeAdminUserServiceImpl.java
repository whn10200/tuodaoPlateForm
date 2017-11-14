package com.tuodao.bp.api.facade.service.admin.impl;

import com.google.common.collect.Maps;
import com.tuodao.bp.api.facade.client.depository.DepositoryUserClient;
import com.tuodao.bp.api.facade.client.useraccount.AdminUserClient;
import com.tuodao.bp.api.facade.service.admin.IFacadeAdminUserService;
import com.tuodao.bp.model.admin.ChangeBankInput;
import com.tuodao.bp.model.admin.UserModifyMobileInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description:
 * @author: mif
 * @date: 2017/11/10
 * @time: 16:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class FacadeAdminUserServiceImpl implements IFacadeAdminUserService {

    private static Logger logger = LoggerFactory.getLogger(FacadeAdminUserServiceImpl.class);

    @Resource
    private AdminUserClient adminUserClient;

    @Resource
    private DepositoryUserClient depositoryUserClient;

    /**
     * 修改用户号码
     *
     * @param userModifyMobileInput 输入参数
     */
    @Override

    public void modifyMobile(UserModifyMobileInput userModifyMobileInput) {

        adminUserClient.modifyMobile(userModifyMobileInput);

        depositoryUserClient.updateInfo(userModifyMobileInput.toHashMap());
    }

    /**
     * 换卡
     *
     * @param changeBankInput 换卡
     */
    @Override
    public void changeBank(ChangeBankInput changeBankInput) {

        // 先解绑
        depositoryUserClient.changeCard(changeBankInput.toHashMap());


        //在绑卡
        depositoryUserClient.boundCard(changeBankInput.toHashMap());
    }

}
