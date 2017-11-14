package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;

public class AccountStatus implements Serializable {
    private Integer productId;

    private Integer type;

    private Integer step;

    private Integer status;

    private Integer lastProductId;

    private static final long serialVersionUID = 1L;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLastProductId() {
        return lastProductId;
    }

    public void setLastProductId(Integer lastProductId) {
        this.lastProductId = lastProductId;
    }
}