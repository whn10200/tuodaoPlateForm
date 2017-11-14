package com.tuodao.bp.model.business.traningcenter.output;

import com.tuodao.bp.model.annotation.In;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author wuchengjie
 * @Date 2017/9/26 0026 18:24
 * @Introduction
 */
public class ChicenessTenderOutput implements Serializable {


    private static final long serialVersionUID = -2064049635760281023L;

    private Integer id;

    private String userId;

    private BigDecimal tenderAmount; //投资总金额 //分

    private BigDecimal tenderAmountYes; //已经投资 //分

    private BigDecimal tenderAmountWait; //剩余 //分

    private Date tenderDate;

    private Integer productId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Date getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(Date tenderDate) {
        this.tenderDate = tenderDate;
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

    public BigDecimal getTenderAmount() {
        return tenderAmount;
    }

    public void setTenderAmount(BigDecimal tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    public BigDecimal getTenderAmountYes() {
        return tenderAmountYes;
    }

    public void setTenderAmountYes(BigDecimal tenderAmountYes) {
        this.tenderAmountYes = tenderAmountYes;
    }

    public BigDecimal getTenderAmountWait() {
        return tenderAmountWait;
    }

    public void setTenderAmountWait(BigDecimal tenderAmountWait) {
        this.tenderAmountWait = tenderAmountWait;
    }


}
