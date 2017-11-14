package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.business.useraccount.input.SAddUserFeedBack;

/**
 * Description: 意见反馈相关service
 * User: zkai
 * Date: 2017/9/12
 * Time: 14:39
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IUserFeedBackService {
    /**
     * 添加意见反馈
     * @param addUserFeedBack
     */
    void addUserFeedBack(SAddUserFeedBack addUserFeedBack);
}
