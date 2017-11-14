package com.tuodao.bp.api.facade.service.transaction;

import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;

/**
 * @author 王艳兵
 */
public interface AccountService {

    /**
     * 校验用户可提现余额
     * @param userId 用户ID
     * @param money 金额
     * @return
     */
    AccountOutput verifyCashAccount(String userId, double money);

    /**
     * 校验用户账户可用总额
     * @param userId
     * @param money
     * @return
     */
    AccountOutput verifyAccount(String userId, double money);

    /**
     * 校验投标金额是否满足
     * @param userId 用户id
     * @param tenderMoney 投标金额
     * @param discount 优惠券信息
     * @return
     */
    AccountOutput verifyTenderAccount(String userId, double tenderMoney, UserDiscountOutput discount);

    /**
     * 获取用户账户信息
     * @param userId
     * @return
     */
    AccountOutput getUserAccount(String userId);

}
