package com.tuodao.bp.api.facade.service.transaction.impl;

import com.tuodao.bp.api.facade.client.transaction.GenerateClient;
import com.tuodao.bp.api.facade.service.transaction.GenerateService;
import com.tuodao.bp.model.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/13
 * @time: 18:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("generateService")
public class GenerateServiceImpl implements GenerateService {

    @Autowired
    private GenerateClient generateClient;

    @Override
    public String get() {
        return generateClient.getGenerateId(new BasePojo());
    }
}
