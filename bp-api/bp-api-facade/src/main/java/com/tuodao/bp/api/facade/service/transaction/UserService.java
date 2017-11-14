package com.tuodao.bp.api.facade.service.transaction;

import com.tuodao.bp.model.business.useraccount.UserAccountInfo;


/**
 * @author 王艳兵
 */
public interface UserService {

    /**
     * 校验用户交易密码是否正确<br/>
     * @param userId      用户ID
     * @param payPassword 交易密码
     */
    void verifyUserPayPassword(String userId, String payPassword);

    /**
     * 校验用户基本信息 注意 提现需要开启校验 投资不需要
     * @param user        用户信息
     * @param verifyInner 是否校验内部用户信息 true:校验 false:不校验
     */
    void verifyUserCommon(UserAccountInfo user,boolean verifyInner);
}
