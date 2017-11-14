package com.tuodao.bp.traningcenter.service;


import com.tuodao.bp.model.traningcenter.output.AccountOutput;

/**
 * @author 王艳兵
 */
public interface AccountService {

    /**
     * 获取用户可用资金
     * @param userId
     * @return
     */
    AccountOutput getUserAccount(String userId);

    /**
     * 创建用户账户信息
     * @param userId
     */
    void createUserAccount(String userId);

}
