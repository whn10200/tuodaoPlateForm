package com.tuodao.bp.traningcenter.service;


import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.input.AccountCashApplyInput;

/**
 * 投资人提现
 * @author 王艳兵
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
public interface AccountCashService {

    /**
     * 根据用户id获取已经提现的总额
     * @param userId
     * @return
     */
    double getCashTotalByUserId(String userId);


    /**
     * 投资人添加提现记录,更新提现次数
     * @param input 提现相关参数
     */
    void addAccountCash(AccountCashApplyInput input);

    /**
     * 提现申请请求银行
     * @param input
     */
    void cashBankRequest(AccountCashApplyInput input);


    /**
     *  更新提现结果状态
     * @param orderNo
     * @param status
     */
    void updateAccountCash(String orderNo ,Integer status);

    /**
     * 分页获取用户的提现列表
     * @param param
     * @return
     */
    PageInfo<AccountCashVo> getUserCashListByPage(CashListParam param);
}
