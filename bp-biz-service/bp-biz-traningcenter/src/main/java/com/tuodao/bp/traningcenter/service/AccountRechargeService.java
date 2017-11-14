package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.business.traningcenter.input.RechargeFastCodeInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeFastInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeGatewayInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeOrderInput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeFastOutput;
import com.tuodao.bp.model.enums.ChannelType;
import com.tuodao.bp.traningcenter.db.model.basic.AccountRecharge;
import com.tuodao.bp.traningcenter.enums.RechargeChannel;
import com.tuodao.bp.traningcenter.enums.RechargeType;

import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description
 */
public interface AccountRechargeService {


    /**
     * 网关充值
     * @param input
     */
    void onlineBankRecharge(RechargeGatewayInput input);

    /**
     * 快捷充值 发送短信
     * @param input
     */
    RechargeFastOutput sendSmsCode(RechargeFastCodeInput input);

    /**
     * 快捷充值
     * @param input
     * @return RechargeConfirmOutput
     */
    RechargeConfirmOutput fastRecharge(RechargeFastInput input);


    void update(AccountRecharge accountRecharge);

    /**
     * 获取充值记录
     * @param orderNo
     * @return
     */
    AccountRecharge getByOrderNo(String orderNo);

    /**
     * 更新订单状态
     * @param orderNo 订单号
     * @param status 状态
     */
    void update(String orderNo, Integer status);

    /**
     * 回调
     * @param input
     */
    void asyn(RechargeOrderInput input);


}
