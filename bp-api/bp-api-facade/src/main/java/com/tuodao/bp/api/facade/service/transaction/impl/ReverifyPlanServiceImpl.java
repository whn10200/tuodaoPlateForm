package com.tuodao.bp.api.facade.service.transaction.impl;

import com.tuodao.bp.api.facade.client.transaction.JoinPlanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/10/19 0019.
 */
@Service
public class ReverifyPlanServiceImpl {

    @Autowired
    JoinPlanClient joinPlanClient;
}
