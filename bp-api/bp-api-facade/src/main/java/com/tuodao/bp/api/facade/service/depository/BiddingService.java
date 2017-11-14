package com.tuodao.bp.api.facade.service.depository;

import com.tuodao.bp.model.facade.traningcenter.input.AccountCashParam;

public interface BiddingService {

    /**
     * 投资人提现申请
     *
     * @param param  提现申请参数
     * @param Ip     请求的Ip地址
     * @param source 提现来源
     * @return 提现手续费 分
     */
    double cashApply(AccountCashParam param, String Ip, int source);


    /**
     * 更新提现申请信息
     *
     * @return
     */
    void updateAccountCash(String orderNo, int orderStatus);




}