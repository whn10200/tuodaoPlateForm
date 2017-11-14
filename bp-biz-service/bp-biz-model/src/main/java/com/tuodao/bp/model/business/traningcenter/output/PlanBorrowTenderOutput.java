package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * auto wuzf
 * 理财计划底层标的复审的输出类
 */
public class PlanBorrowTenderOutput implements Serializable{

    private static final long serialVersionUID = 5567872312090557504L;
    /**
     * 主键<br>
     * 表 : borrow_tender<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 用户id<br>
     * 表 : borrow_tender<br>
     * 对应字段 : user_id<br>
     */
    private String userId;


    /**
     * 属于哪一个理财计划
     * @return
     */
    private Integer borrowId;

    private BigDecimal voucherMoney;

    private BigDecimal account;

    /**
     * 预计总利息
     */
    private BigDecimal preAllIntrest;

    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getPreAllIntrest() {
        return preAllIntrest;
    }

    public void setPreAllIntrest(BigDecimal preAllIntrest) {
        this.preAllIntrest = preAllIntrest;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
