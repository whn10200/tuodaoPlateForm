package com.tuodao.bp.api.facade.service.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AccountCashParam;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;

/**
 * @author 王艳兵
 */
public interface AccountCashService {

    /**
     * 投资人提现申请
     *
     * @param param  提现申请参数
     * @return 提现手续费 分
     */
    double cashApply(AccountCashParam param);


    /**
     * 获取用户提现手续费
     * @param user   免费提现次数
     * @param account 资金信息
     * @param cashMoney 提现金额
     * @return
     */
    double getFee(UserFreeNumberOutput user, AccountOutput account, double cashMoney);

    /**
     * 分页获取用户的提现记录信息
     * @param param
     * @return
     */
    PageInfo<AccountCashVo> getUserCashListByPage(CashListParam param);

    /**
     * 获取用户在银行的不能提现金额(未清算资金)
     * @param userId
     * @return
     */
    double getBankBalanceFrost(String userId);

}