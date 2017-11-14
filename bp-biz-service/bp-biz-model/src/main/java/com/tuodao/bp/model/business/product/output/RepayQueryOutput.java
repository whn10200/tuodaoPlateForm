package com.tuodao.bp.model.business.product.output;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/10/26
 * @description
 */
@Data
public class RepayQueryOutput {

    /**
     * 主键<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 借款人ID<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 标id<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : borrow_id<br>
     */
    private Integer borrowId;

    /**
     * 还款状态:0:未还1:已还,2:提前还款<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : status<br>
     */
    private Integer status;

    /**
     * 还款期数<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : period<br>
     */
    private Integer period;

    /**
     * 总期数<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : periods<br>
     */
    private Integer periods;

    /**
     * 还款手续费<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : fee<br>
     */
    private BigDecimal fee;

    /**
     * 预计还款利息<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : pre_interest<br>
     */
    private BigDecimal preInterest;

    /**
     * 预计还款本金<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : pre_capital<br>
     */
    private BigDecimal preCapital;

    /**
     * 预计还款时间<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : pre_repay_time<br>
     */
    private Date preRepayTime;

    /**
     * 实际还款利息<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : interest<br>
     */
    private BigDecimal interest;

    /**
     * 实际还款本金<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : capital<br>
     */
    private BigDecimal capital;

    /**
     * 实际还款时间<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : repay_time<br>
     */
    private Date repayTime;

    /**
     * 还款方式:0平台代付,1:个人还款<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : repay_mode<br>
     */
    private Integer repayMode;

    /**
     * 备注信息<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : remarks<br>
     */
    private String remarks;

    /**
     * 历史的borrow_nid:注意凡带有nid字段的均为兼容老系统的字段<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : borrow_nid<br>
     */
    private String borrowNid;


}
