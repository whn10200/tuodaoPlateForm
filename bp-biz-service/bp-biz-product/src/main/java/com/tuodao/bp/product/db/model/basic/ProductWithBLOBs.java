package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;

public class ProductWithBLOBs extends Product implements Serializable {
    private String setRemark;

    private String publishRemark;

    private String auditRemark;

    private static final long serialVersionUID = 1L;

    public String getSetRemark() {
        return setRemark;
    }

    public void setSetRemark(String setRemark) {
        this.setRemark = setRemark == null ? null : setRemark.trim();
    }

    public String getPublishRemark() {
        return publishRemark;
    }

    public void setPublishRemark(String publishRemark) {
        this.publishRemark = publishRemark == null ? null : publishRemark.trim();
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }
}