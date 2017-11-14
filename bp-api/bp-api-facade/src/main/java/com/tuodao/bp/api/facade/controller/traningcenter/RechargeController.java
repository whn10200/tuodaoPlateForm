package com.tuodao.bp.api.facade.controller.traningcenter;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.utils.TransferUtil;
import com.tuodao.bp.api.facade.client.transaction.RechargeClient;
import com.tuodao.bp.api.facade.service.transaction.RechargeService;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.traningcenter.input.RechargeOrderInput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeSmsVo;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeVo;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author qingli.chen
 * @date 2017/9/13
 * @description 充值
 */
@RestController
@RequestMapping("/router/recharge")
public class RechargeController {


    @Autowired
    BankCache bankCache;

    @Autowired
    RechargeService rechargeService;

    @Autowired
    RechargeClient rechargeClient;

    /**
     * 银行列表
     * @param basePojo
     * @return
     */
    @RequestMapping(value = "bankList")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult bankList(BasePojo basePojo) {
        List<OnlineBankInfo> list = bankCache.getOnlineBank();
        return RespResult.create().setContent(list);
    }

    /**
     * 用户绑定的卡
     * @param basePojo
     * @return
     */
    @RequestMapping(value = "rechargeInfo")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult rechargeInfo(BasePojo basePojo) {
        FastRechargeVo fastRechargeVo = rechargeService.rechargeInfo(basePojo.getUserId(), basePojo.getRequestType());
        return RespResult.create().setContent(fastRechargeVo);
    }

    /**
     * 银行充值
     * @return
     */
    @RequestMapping(value = "online")
    @AccessToken(access = {AccessType.PC})
    public RespResult onlineBankRecharge(OnlineBankParam onlineBankParam) {

        String msg = rechargeService.onlineBankRecharge(onlineBankParam);
        return RespResult.create().setSuccess(true).setContent(msg);
    }


    /**
     * 快捷充值 发送短信
     * @param param
     * @return
     */
    @RequestMapping(value = "sendSmsCode")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult sendFastCode(FastRechargeSmsParam param) {
        FastRechargeSmsVo result = rechargeService.sendFastCode(param);
        return RespResult.create().setContent(result);
    }

    /**
     * 快捷充值
     * @param param
     * @return
     */
    @RequestMapping(value = "fast/pay")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult fastRecharge(FastRechargeParam param) {

        RechargeConfirmOutput rechargeConfirmOutput = rechargeService.fastRecharge(param);
        if(rechargeConfirmOutput.isSuccess()) {
            return RespResult.create();
        } else {
            return RespResult.create()
                    .setSuccess(false)
                    .setCode(rechargeConfirmOutput.getCode())
                    .setMsg(rechargeConfirmOutput.getMsg());
        }
    }


    /**
     * 网银充值 回调
     */
    @RequestMapping(value = "/notice/online_bank", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.BANK})
    public void rechargeAsynGateway(OnlineBankAsynParam param) {
        RechargeOrderInput input = new RechargeOrderInput(param.getOrderNo());
        TransferUtil.transfer(param, input);
        rechargeClient.notice(input);
    }

//    /**
//     * 快捷充值 回调
//     */
//    @RequestMapping(value = "/notice/fast", method = RequestMethod.POST)
//    @AccessToken(checkAccess = true, access = {AccessType.BANK})
//    public void rechargeAsynFast(OnlineBankAsynParam param) {
//        RechargeOrderInput input = new RechargeOrderInput(param.getOrderNo());
//        TransferUtil.transfer(param, input);
//        rechargeClient.notice(input);
//    }

}
