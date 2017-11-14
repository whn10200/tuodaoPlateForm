package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.business.useraccount.input.ConsigneeInfoInput;
import com.tuodao.bp.model.business.useraccount.output.UserInfoDetailOutput;

/**
 * @description: 用户详情接口
 * @author: mif
 * @date: 2017/10/12
 * @time: 14:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IUserInfoDetailService {

    /**
     * 获取用户详情
     *
     * @param userId 用户ID
     * @return 用户详情
     */
    UserInfoDetailOutput getUserInfoDetail(String userId);

    /**
     * 修改收件人信息
     *
     * @param consigneeInfoInput 收件人信息
     */
    void updateConsigneeInfo(ConsigneeInfoInput consigneeInfoInput);
}
