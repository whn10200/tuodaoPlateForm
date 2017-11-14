package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.business.operation.input.OpCooperationInput;

/**
 * Description: 合作加盟service
 * User: yinping
 * Date: 2017/9/22
 * Time: 15:29
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IOpCooperationService {
    /**
     * 新增加盟
     * @param input
     */
    void addCooperationManage(OpCooperationInput input);

}
