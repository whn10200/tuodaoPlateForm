package com.tuodao.bp.api.facade.service.useraccount.impl;

import com.tuodao.bp.api.facade.client.DemoClient;
import com.tuodao.bp.api.facade.service.useraccount.IDemoService;
import com.tuodao.bp.model.business.useraccount.input.DemoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author hechuan
 * <p>
 * created on 2017/9/26
 * <p>
 * since V1.0.0
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    private DemoClient demoClient;

    @Override
    public boolean getResult(DemoInput input) {
        return demoClient.resolveDemo(input);
    }
}
