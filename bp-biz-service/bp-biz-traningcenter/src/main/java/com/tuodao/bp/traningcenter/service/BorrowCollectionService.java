package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.business.traningcenter.output.RecoverListOutput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.traningcenter.input.BorrowCollectionInput;
import com.tuodao.bp.model.traningcenter.input.UserCollectionInput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollectionCapital;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 王艳兵
 */
public interface BorrowCollectionService {

    /**
     * 获取用户已回款的总额
     * @param userId 用户id
     * @return
     */
    double getUserTotalCollection(String userId);

    /**
     * 获取用户回款计划
     * @param input
     * @return
     */
    PageInfo<BackMoneyPlanOutput> getRecoveredByTenderId(UserBackMoneyInput input);

    /**
     * 查询回款计划
     * @param userId 用户id
     * @param tenderId 投标id
     * @return
     */
    List<BackMoneyPlanOutput> getByTenderId(String userId, int tenderId);

    /**
     * 统计剩余期数
     * @param tenderId 投标id
     * @return
     */
    BorrowCollectionCapital getBackPlan(Integer tenderId);

    /**
     * 统计未回款剩余
     * @param tenderId 投标id
     * @return
     */
    BorrowCollectionCapital getUnBackPlan(Integer tenderId);

    /**
     * 获取回款计划
     * @param tenderId 投标id
     * @param status 投标状态
     * @return
     */
    List<BorrowCollection> getCollection(Integer tenderId, Integer status);

    PageInfo<RecoverListOutput> selectRecoverListBychioId(JustIdInput justIdInput);

    /**
     * 根据标的id与标的还款期数查询对应标的的回款列表
     * @param repayment 标的id ,第几期回款,是否为提前还款
     * @return
     */
    List<BackMoneyPlanOutput> getTenderCollection(BorrowRepaymentInput repayment);

    /**
     * 批量添加标的回款信息
     * @param input    回款计划列表
     * @param borrowId 标的ID
     * @return
     */
    int insertBorrowCollectionBatch(List<UserCollectionInput> input, Integer borrowId);

    /**
     * 获取用户某月或某天 已回款的资金
     * @param param
     * @return
     */
    Map<String,BigDecimal> getRealCollection(CollectionParam param);
    /**
     * 获取用户某月或某天 未回款的资金
     * @param param 
     * @return
     */
    Map<String,BigDecimal> getPreCollection(CollectionParam param);

    /**
     * 根据天分页获取回款信息
     * @param param
     * @return
     */
    PageInfo<BackMoneyPlanOutput> getCollectionCalendarByPage(CollectionParam param);

    /**
     * 获取用户某一月的所有回款日期
     * @param userId
     * @param month
     * @return
     */
    List<CollectionDayVo> getCollectionDays(String userId, String month);

    /**
     * 借款人还款后更新回款信息为成功 更新回款资金记录
     * @param repayment 还款信息+订单号
     */
    void collection(BorrowRepaymentInput repayment);


    /**
     * 根据回款信息组装 mq通知信息 主要回款奖励等信息
     * @param collection
     * @return
     */
    void sendCollectionAccount(List<BorrowCollection> collection);

    /**
     * 获取用户回款收益
     * @param userId
     * @return
     */
    CollectionCountOutput getUserCollectionInterest(String userId);
}
