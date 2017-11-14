package com.tuodao.bp.api.facade.service.transaction;

import com.tuodao.bp.model.traningcenter.input.ChoicenessTenderInput;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 投资理财计划facade service
 * @author: wuzf
 * @date: 2017/9/11 0011.
 * @time: 11:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BorrowChicenessTenderService {

    /**
     * 生成投资记录
     * @param choicenessTenderInput
     * @return int
     */
    public int insertChoiceness(ChoicenessTenderInput choicenessTenderInput,HttpServletRequest request);
}
