package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.facade.operation.output.OpBannerManagelListOutput;
import com.tuodao.bp.model.facade.operation.output.OpContentManagelListOutput;
import com.tuodao.bp.operation.persistence.model.basic.OpBannerManager;

import java.util.List;

/**
 * Description: banner管理service(PC)
 * User: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IOpBannerService {
    /**
     * 查询PC banner管理
     * @param input
     */
    List<OpBannerManagelListOutput> selectBanner(OpBannerManager input);
}
