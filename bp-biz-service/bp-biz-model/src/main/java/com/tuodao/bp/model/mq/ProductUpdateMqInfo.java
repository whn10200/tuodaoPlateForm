package com.tuodao.bp.model.mq;


import com.tuodao.bp.model.business.product.input.ProductVerifyInput;

import java.io.Serializable;

/**
 * @description: 投资的队列
 * @author: wuchengjie
 * @date: 2017/9/25
 * @time: 09:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ProductUpdateMqInfo extends ProductTenderMqInfo implements Serializable {


    private static final long serialVersionUID = -4475243215749421409L;
    private Integer productId;  //产品id

    private Integer operation;  // 产品的操作对应不同的方法

    private String operationUserId; //操作的用户id

    private String operationUserName; //操作的用户

    private String operationRemark; //操作备注

    private Integer auditResult;  //审核结果

    private int type; //类型(1:产品，2:债权)

    private Integer productStatus; //产品的状态

    public Integer getAuditResult() {
        return auditResult;
    }

    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    public String getOperationRemark() {
        return operationRemark;
    }

    public void setOperationRemark(String operationRemark) {
        this.operationRemark = operationRemark;
    }

    public String getOperationUserName() {
        return operationUserName;
    }

    public void setOperationUserName(String operationUserName) {
        this.operationUserName = operationUserName;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(String operationUserId) {
        this.operationUserId = operationUserId;
    }
    @Override
    public int getType() {
        return type;
    }
    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }
    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ProductUpdateMqInfo() {
    }

    @Override
    public String toString() {
        return "ProductUpdateMqInfo{" +
                "productId=" + productId +
                ", operation=" + operation +
                ", operationUserId='" + operationUserId + '\'' +
                ", type=" + type +
                ", productStatus=" + productStatus +
                '}';
    }


    /**
     * 转换成input对象  不包括审核类型
     * @return
     */
    public ProductVerifyInput toVerifyInput()
    {
        ProductVerifyInput verifyInput = new ProductVerifyInput();
        Integer borrowId = this.getBorrowId();
        String auditRemark = this.getOperationRemark();
        String userId = this.getOperationUserId();
        String auditUserName = this.getOperationUserName();
        Integer result = this.getAuditResult();

        verifyInput.setBorrowId(borrowId);
        verifyInput.setAuditRemark(auditRemark);
        verifyInput.setAuditUserId(userId);
        verifyInput.setAuditUserName(auditUserName);
        verifyInput.setAuditResult(result);

        return verifyInput;
    }
}
