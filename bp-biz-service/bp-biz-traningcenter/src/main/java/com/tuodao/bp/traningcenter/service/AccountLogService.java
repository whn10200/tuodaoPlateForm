package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;
import com.tuodao.bp.traningcenter.db.model.basic.AccountCash;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/11 0011.
 * @time: 16:32
 * @copyright: 拓道金服 Copyright (c) 2017
 * @author 王艳兵
 */
public interface AccountLogService {

    /**
     * 存管更新用户的资金
     *
     * @param accountLog
     */
    void updateAccountAndLogPro(AccountLog accountLog);

    /**
     * 存管更新用户的资金
     * @param accountLogList
     */
    void batchUpdate(List<AccountLog> accountLogList);

    /**
     * 添加提现申请资金日志
     * @param cash 提现申请信息
     */
    void insertAccountCashApplyLog(AccountCash cash);

    /**
     * 提现成功,更新资金记录
     * @param cash    提现信息
     * @param remarks 备注信息
     */
    void insertAccountCashSuccessLog(AccountCash cash,String remarks);

    /**
     * 提现成功,扣除手续费资金记录
     * @param cash
     * @param remarks
     */
    void insertAccountCashFeeLog(AccountCash cash,String remarks);
    /**
     * 提现失败添加资金记录
     * @param cash
     */
    void insertAccountCashFailLog(AccountCash cash);
    /**
     * 添加投标冻结资金日志,该方法与其他区别:<br/>
     * 资金记录需要判断是否扣除的是充值金额还是其可用余额<br/>
     * 其他资金记录均为组装好参数后调用{@link AccountLogService#updateAccountAndLogPro} 方法更新账户信息与资金记录信息
     * @param tender 投标信息
     * @param remark 备注信息 用于显示资金流水
     */
    void insertBorrowTenderFrostLog(BorrowTender tender,String remark);

    /**
     * 分页获取用户的资金记录
     * @param input 查询条件
     * @return
     */
    PageInfo<AccountLogVo> getUserAccountLogByPage(AccountLogInput input);

    /**
     * 获取用户资金记录
     * @param param
     * @return
     */
    PageInfo<AppAccountLogVo> getAppUserAccountLogByPage(AccountLogParam param);

    /**
     * 批量添加回款成功资金记录
     * @param list 回款列表
     */
    void insertBorrowCollectionLogBatch(List<BorrowCollection> list);

    /**
     * 添加回款成功资金记录 单个
     * @param collection
     */
    void insertBorrowCollectionLog(BorrowCollection collection);

    /**
     * 投标成功,生成回款信息的资金记录
     * @param list    投资列表
     * @param remarks 资金记录备注信息 用于显示
     */
    void insertTenderSuccessLog(List<BorrowTenderOutput> list,String remarks);

    /**
     * 标的撤回,添加资金记录,解冻资金
     * @param list
     * @param remarks 备注信息
     */
    void insertTenderCancelLog(List<BorrowTender> list,String remarks);

    /**
     * 投标失败更新资金日志
     * @param tender
     */
    void insertTenderFailLog(BorrowTender tender, AccountLogType accountLogType);

    void insertPlanReverifyLog(BigDecimal addAwaitInterest,String userId,String id);

    void insertPlanReverifyCapitalLog(BigDecimal addAwaitCapital,String userId,String id);

    void withDrawTender(BigDecimal amount,String userId);

    /**
     * 解冻理财计划
     * @return
     */
    Boolean thawingPlanFunds(BorrowChoicenessTender tender);
}
