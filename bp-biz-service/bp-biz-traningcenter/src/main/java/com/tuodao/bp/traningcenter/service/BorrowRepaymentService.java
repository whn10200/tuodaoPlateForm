package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.business.traningcenter.input.BorrowRepaymentInput;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BorrowRepaymentService {

    /**
     * 批量插入还款计划列表
     * @param list
     * @return
     */
    int insertBorrowRepayment(List<BorrowRepaymentInput> list);

    /**
     * 获取还款最新时间
     * @param borrowId 标的id
     * @return
     */
    Date getLastRepayTime(Integer borrowId);

    /**
     * 获取最早一期 未还利息
     * @param borrowId 标的id
     * @return
     */
    BigDecimal getEarliestInterest(Integer borrowId);
}
