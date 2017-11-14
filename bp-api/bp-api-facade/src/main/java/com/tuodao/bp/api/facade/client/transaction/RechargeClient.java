package com.tuodao.bp.api.facade.client.transaction;

import com.tuodao.bp.model.business.traningcenter.input.RechargeFastCodeInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeFastInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeOrderInput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeFastOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeOutput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeGatewayInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author qingli.chen
 * @date 2017/9/13
 * @description
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface RechargeClient {


    /**
     * 网银充值
     * @param rechargeInput
     */
    @RequestMapping(value = "/recharge/onlineBankRingRecharge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void online(RechargeGatewayInput rechargeInput);

    /**
     * 快捷充值 发送短信
     * @param rechargeInput
     */
    @RequestMapping(value = "/recharge/sendSmsCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    RechargeFastOutput sendSmsCode(RechargeFastCodeInput rechargeInput);


    /**
     * 快捷充值
     * @param input
     */
    @RequestMapping(value = "/recharge/fastRecharge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    RechargeConfirmOutput fastRecharge(RechargeFastInput input);


    /**
     * 更新充值记录
     * @param rechargeInput
     */
    @RequestMapping(value = "/recharge/updateRecharge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateRecharge(RechargeFastCodeInput rechargeInput);

    /**
     * 获取充值记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/recharge/getByOrderNo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    RechargeOutput getByOrderNo(RechargeOrderInput input);

    /**
     * 存管回调
     * @param input
     */
    @RequestMapping(value = "/recharge/notice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void notice(RechargeOrderInput input);

}
