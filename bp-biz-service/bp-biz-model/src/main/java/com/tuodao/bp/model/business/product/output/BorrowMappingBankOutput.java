package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author wuchengjie
 * @Date 2017/11/6 0006 15:20
 * @Introduction  MappingBank
 */
public class BorrowMappingBankOutput implements Serializable {

    private static final long serialVersionUID = 3606848046241671196L;
    /****/
    private Integer id;
    /**借款id**/
    private Integer borrowId;
    /**是否要代偿  1:是  0 否**/
    private Integer isCompensatory;
    /**借款存管状态 0：初始    1：成标    2： 出账 **/
    private Integer borrowBankStatus;
    /**预计回到代偿人的钱**/
    private BigDecimal compensatoryAmount;
    /**实际偿还金额**/
    private BigDecimal compensatoryAmountYes;
    /**最后代偿请求状态（0 初始 1 部分成功 2全部成功 3 失败）**/
    private Integer compensatoryStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getIsCompensatory() {
        return isCompensatory;
    }

    public void setIsCompensatory(Integer isCompensatory) {
        this.isCompensatory = isCompensatory;
    }

    public Integer getBorrowBankStatus() {
        return borrowBankStatus;
    }

    public void setBorrowBankStatus(Integer borrowBankStatus) {
        this.borrowBankStatus = borrowBankStatus;
    }

    public BigDecimal getCompensatoryAmount() {
        return compensatoryAmount;
    }

    public void setCompensatoryAmount(BigDecimal compensatoryAmount) {
        this.compensatoryAmount = compensatoryAmount;
    }

    public BigDecimal getCompensatoryAmountYes() {
        return compensatoryAmountYes;
    }

    public void setCompensatoryAmountYes(BigDecimal compensatoryAmountYes) {
        this.compensatoryAmountYes = compensatoryAmountYes;
    }

    public Integer getCompensatoryStatus() {
        return compensatoryStatus;
    }

    public void setCompensatoryStatus(Integer compensatoryStatus) {
        this.compensatoryStatus = compensatoryStatus;
    }
}
