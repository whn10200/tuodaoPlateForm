package com.tuodao.bp.api.facade.service.useraccount;

import com.tuodao.bp.model.facade.useraccout.input.FacadeAddUserFeedBack;

/**
 * Description: 用户反馈聚合service
 * User: zkai
 * Date: 2017/9/14
 * Time: 10:58
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IFacadeUserFeedbackService {
    /**
     * 添加用户反馈
     * @param input
     * @return
     */
    void addUserFeedBack(FacadeAddUserFeedBack input);
}
