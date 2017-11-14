package com.tuodao.bp.model.business.product.input;

import com.tuodao.bp.model.business.traningcenter.output.SelectNoMateOutput;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *  理财计划投资
 */
public class ProductTenderInput extends SelectNoMateOutput implements Serializable {

    private static final long serialVersionUID = -735454201634638737L;
    @NotNull
    private Integer id;
    @NotNull
    private String userId;
    @NotNull
    private BigDecimal preAccount;
    //已经投资 //分
  //  private BigDecimal tenderAmountYes;
    //剩余 //分
  //  private BigDecimal tenderAmountWait;
    private Date tenderDate;
    @NotNull
    private Integer productId;

    @Override
    public Integer getProductId() {
        return productId;
    }
    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public Integer getId() {
        return id;
    }
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public BigDecimal getPreAccount() {
        return preAccount;
    }
    @Override
    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public Date getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(Date tenderDate) {
        this.tenderDate = tenderDate;
    }

    @Override
    public String toString() {
        return "ProductTenderInput{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", tenderAmount=" + preAccount +
                ", tenderDate=" + tenderDate +
                '}';
    }
}