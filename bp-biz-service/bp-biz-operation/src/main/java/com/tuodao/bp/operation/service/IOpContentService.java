package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.business.operation.input.OpContentInput;
import com.tuodao.bp.model.business.operation.input.OpContentTitleInput;
import com.tuodao.bp.model.facade.operation.output.OpContentManagelListOutput;

import java.util.List;

/**
 * Description: 内容管理service
 * User: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IOpContentService {
    /**
     * 查询标题列表和发布时间
     * @param input
     */
    List<OpContentManagelListOutput> selectContentTitle(OpContentTitleInput input);
    /**
     * 查询内容管理
     * @param input
     */
    List<OpContentManagelListOutput> selectContentManager(OpContentInput input);
}
