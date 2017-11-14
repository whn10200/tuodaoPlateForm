package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.CheckUserSignOutput;

/**
 * Description: 服务 - 用户签到service
 * User: zkai
 * Date: 2017/9/25
 * Time: 15:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IUserSignService {

    /**
     *  用户签到
     * @param basePojo
     * @return 签到积分
     */
    int userSign(BasePojo basePojo);

    /**
     *  用户是否签到
     * @param basePojo
     * @return true 签到  false 没有签到
     */
    CheckUserSignOutput checkSign(BasePojo basePojo);
}
