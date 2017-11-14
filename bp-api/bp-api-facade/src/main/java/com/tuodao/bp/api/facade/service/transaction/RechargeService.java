package com.tuodao.bp.api.facade.service.transaction;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.model.facade.traningcenter.input.FastRechargeParam;
import com.tuodao.bp.model.facade.traningcenter.input.FastRechargeSmsParam;
import com.tuodao.bp.model.facade.traningcenter.input.OnlineBankParam;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeSmsVo;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeVo;

/**
 * @author qingli.chen
 * @date 2017/10/16
 * @description 充值
 */
public interface RechargeService {

    /**
     * 网银充值
     * @param onlineBankParam
     * @return
     */
    String onlineBankRecharge(OnlineBankParam onlineBankParam);

    /**
     * 快捷充值 发送短信
     * @param param
     * @return
     */
    FastRechargeSmsVo sendFastCode(FastRechargeSmsParam param);

    /**
     * 获取充值信息
     * @param userId 用户id
     * @param requestType 请求渠道
     * @return
     */
    FastRechargeVo rechargeInfo(String userId, String requestType);

    /**
     * 快捷充值
     * @param param
     * @return RechargeConfirmOutput
     */
    RechargeConfirmOutput fastRecharge(FastRechargeParam param);
}
