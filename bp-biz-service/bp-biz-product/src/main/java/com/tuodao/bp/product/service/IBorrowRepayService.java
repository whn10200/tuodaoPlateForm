package com.tuodao.bp.product.service;

import java.util.List;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.TransferInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.RepayPlanOutput;
import com.tuodao.bp.product.db.model.basic.BorrowRepayment;
import com.tuodao.bp.product.db.model.basic.Product;

/**
 * @Author wuchengjie
 * @Date 2017/9/1 0001 16:57
 * @Introduction 产品的还款功能
 */
public interface IBorrowRepayService {

    /**
     * 插入还款记录（散标）
     * @param input
     * @return
     */
    Boolean insertBorrowRepayment(List<BorrowRepaymentInput> input);


    /**
     * 执行标的还款计划
     * @param borrowId
     * @param period  还款期数
     * @return
     */
    Boolean doBorrowRepay(Integer borrowId, Integer period, List<TransferInput> list, Boolean advance);

    Boolean doRepay(Integer borrowId, Integer period, Boolean advance);

    /**
     * 查询还款列表
     * @param borrowId 标的id
     * @param status 状态
     * @return
     */
    List<BorrowRepayment> getRepayList(Integer borrowId, Integer status);

    /**
     * 查询某一个标的未还款期数 上期还款日期  如果没还款过 那么就取复审时间
     */
    BorrowRepaymentInfoOutput getRepayInfoByBorrowId(Integer borrowId);

    Product createRepayPlan(Product product);
    
    /**
     *  根据主键ID获取还款信息
     */
    BorrowRepaymentInput getRepayInfoById(Integer id);
    
    /**
     *  根据传入参数获取当前期数之前已还款数据
     */
    BorrowRepaymentOutput getRepayInfoByparam(BorrowRepaymentInput input);
    
    /**
     * 跟新还款记录（散标）
     * @param borrowRepaymentInput
     * @return
     */
    Boolean updateRepayment(BorrowRepaymentInput borrowRepaymentInput);
    /**
     * 还款的临时列表（成标的时候用）
     * @param borrowId
     */
    List<RepayPlanOutput> getTemporaryRepayList(Integer borrowId);
}
