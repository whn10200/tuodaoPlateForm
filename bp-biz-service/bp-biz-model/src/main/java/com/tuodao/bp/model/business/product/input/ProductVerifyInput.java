package com.tuodao.bp.model.business.product.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.product.ProductVerifyExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class ProductVerifyInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = 469913601908510861L;

    @NotNull(message = ProductVerifyExceptionConstant.VERIFY_REMARK_NULL+"")
    private String auditRemark;
    @NotNull(message = ProductVerifyExceptionConstant.VERIFY_STATUS_NULL+"")
    private Integer borrowStatus;

    private Date availableInvestTime;

    private String auditUserId;

    private String auditUserName;
    @NotNull(message = ProductVerifyExceptionConstant.VERIFY_RESUALT_NULL+"")
    private Integer auditResult;  //审核结果

    private Date auditTime = new Date();
    @NotNull(message = ProductVerifyExceptionConstant.VERIFY_BORROWID_NULL+"")
    private Integer borrowId;

    private Integer auditType;  //审核类型  0：设置标的 1：发布标的 2：审核标的 3：手动满标审核标的',

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Integer getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(Integer borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public Date getAvailableInvestTime() {
        return availableInvestTime;
    }

    public void setAvailableInvestTime(Date availableInvestTime) {
        this.availableInvestTime = availableInvestTime;
    }

    public String getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(String auditUserId) {
        this.auditUserId = auditUserId;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }
}