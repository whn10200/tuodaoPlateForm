package com.tuodao.bp.api.facade.service.admin;

import com.tuodao.bp.model.admin.ChangeBankInput;
import com.tuodao.bp.model.admin.UserModifyMobileInput;

/**
 * @description: 大后台用户相关接口
 * @author: mif
 * @date: 2017/11/10
 * @time: 16:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IFacadeAdminUserService {
    /**
     * 修改用户号码
     *
     * @param userModifyMobileInput 输入参数
     */
    void modifyMobile(UserModifyMobileInput userModifyMobileInput);

    /**
     * 换卡
     *
     * @param changeBankInput 换卡
     */
    void changeBank(ChangeBankInput changeBankInput);
}
