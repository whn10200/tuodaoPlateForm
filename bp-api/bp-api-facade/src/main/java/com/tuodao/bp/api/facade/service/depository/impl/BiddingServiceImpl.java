package com.tuodao.bp.api.facade.service.depository.impl;


import org.springframework.stereotype.Service;

import com.tuodao.bp.api.facade.service.depository.BiddingService;

import com.tuodao.bp.model.facade.traningcenter.input.AccountCashParam;


@Service("biddingService")
public class BiddingServiceImpl implements BiddingService  {
    @Override
    public double cashApply(AccountCashParam param, String Ip, int source) {
        return 0;
    }

    @Override
    public void updateAccountCash(String orderNo, int orderStatus) {

    }
}







