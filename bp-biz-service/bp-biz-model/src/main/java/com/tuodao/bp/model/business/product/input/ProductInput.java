package com.tuodao.bp.model.business.product.input;

import com.tuodao.bp.model.PagePojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductInput extends PagePojo implements Serializable {


    private static final long serialVersionUID = -715454201634638737L;
    private Integer id;


    private String productCode;

    private String businessId;

    private Integer orginalId;

    private Integer parentId;

    private String borrowUserId;

    private String borrowUserName;

    private BigDecimal borrowAmount;

    private BigDecimal borrowAccountYes;

    private BigDecimal borrowFee;

    private BigDecimal borrowApr;

    private Integer borrowType;

    private Integer borrowFullStatus;

    private Date borrowFullTime;

    public Date getBorrowFullTime() {
        return borrowFullTime;
    }

    public void setBorrowFullTime(Date borrowFullTime) {
        this.borrowFullTime = borrowFullTime;
    }

    private BigDecimal surplusInvestAmount;

    private Integer isJion;

    private Date repayLastTime;

    private BigDecimal mateAmount;

    private String borrowUse;

    private Integer isAutoPublish;

    private Integer isYetIssue;

    private Date autoPublishTime;

    private Date publishTime;

    private Integer isAutoInvest;

    private String productTitle;

    private Integer refundWay;

    private Integer productPeriod;

    private String periodUnit;

    private Integer defineType;

    private Integer awardType;

    private BigDecimal awardAmount;

    private BigDecimal awardScale;

    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    private Integer availableTransferDay;

    private Integer integralFold;

    private Integer isAuthCode;

    private Integer isIssue;

    private Integer isApp;

    private String appointPassword;

    private Integer productStatus;

    private Integer productType;

    private Date availableInvestTime;

    private String auditUserId;

    private String auditUserName;

    private Date auditTime;

    private Date createTime;

    private String sourceStore;

    private BigDecimal weighting;

    private Integer isTimingShow;

    private String isTurn;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public Integer getOrginalId() {
        return orginalId;
    }

    public void setOrginalId(Integer orginalId) {
        this.orginalId = orginalId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(String borrowUserId) {
        this.borrowUserId = borrowUserId == null ? null : borrowUserId.trim();
    }

    public String getBorrowUserName() {
        return borrowUserName;
    }

    public void setBorrowUserName(String borrowUserName) {
        this.borrowUserName = borrowUserName == null ? null : borrowUserName.trim();
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public BigDecimal getBorrowAccountYes() {
        return borrowAccountYes;
    }

    public void setBorrowAccountYes(BigDecimal borrowAccountYes) {
        this.borrowAccountYes = borrowAccountYes;
    }

    public BigDecimal getBorrowFee() {
        return borrowFee;
    }

    public void setBorrowFee(BigDecimal borrowFee) {
        this.borrowFee = borrowFee;
    }

    public BigDecimal getBorrowApr() {
        return borrowApr;
    }

    public void setBorrowApr(BigDecimal borrowApr) {
        this.borrowApr = borrowApr;
    }

    public Integer getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(Integer borrowType) {
        this.borrowType = borrowType;
    }

    public Integer getBorrowFullStatus() {
        return borrowFullStatus;
    }

    public void setBorrowFullStatus(Integer borrowFullStatus) {
        this.borrowFullStatus = borrowFullStatus;
    }

    public BigDecimal getSurplusInvestAmount() {
        return surplusInvestAmount;
    }

    public void setSurplusInvestAmount(BigDecimal surplusInvestAmount) {
        this.surplusInvestAmount = surplusInvestAmount;
    }

    public Integer getIsJion() {
        return isJion;
    }

    public void setIsJion(Integer isJion) {
        this.isJion = isJion;
    }

    public Date getRepayLastTime() {
        return repayLastTime;
    }

    public void setRepayLastTime(Date repayLastTime) {
        this.repayLastTime = repayLastTime;
    }

    public BigDecimal getMateAmount() {
        return mateAmount;
    }

    public void setMateAmount(BigDecimal mateAmount) {
        this.mateAmount = mateAmount;
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse == null ? null : borrowUse.trim();
    }

    public Integer getIsAutoPublish() {
        return isAutoPublish;
    }

    public void setIsAutoPublish(Integer isAutoPublish) {
        this.isAutoPublish = isAutoPublish;
    }

    public Integer getIsYetIssue() {
        return isYetIssue;
    }

    public void setIsYetIssue(Integer isYetIssue) {
        this.isYetIssue = isYetIssue;
    }

    public Date getAutoPublishTime() {
        return autoPublishTime;
    }

    public void setAutoPublishTime(Date autoPublishTime) {
        this.autoPublishTime = autoPublishTime;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getIsAutoInvest() {
        return isAutoInvest;
    }

    public void setIsAutoInvest(Integer isAutoInvest) {
        this.isAutoInvest = isAutoInvest;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    public Integer getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(Integer refundWay) {
        this.refundWay = refundWay;
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit == null ? null : periodUnit.trim();
    }

    public Integer getDefineType() {
        return defineType;
    }

    public void setDefineType(Integer defineType) {
        this.defineType = defineType;
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public BigDecimal getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(BigDecimal awardAmount) {
        this.awardAmount = awardAmount;
    }

    public BigDecimal getAwardScale() {
        return awardScale;
    }

    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getAvailableTransferDay() {
        return availableTransferDay;
    }

    public void setAvailableTransferDay(Integer availableTransferDay) {
        this.availableTransferDay = availableTransferDay;
    }

    public Integer getIntegralFold() {
        return integralFold;
    }

    public void setIntegralFold(Integer integralFold) {
        this.integralFold = integralFold;
    }

    public Integer getIsAuthCode() {
        return isAuthCode;
    }

    public void setIsAuthCode(Integer isAuthCode) {
        this.isAuthCode = isAuthCode;
    }

    public Integer getIsIssue() {
        return isIssue;
    }

    public void setIsIssue(Integer isIssue) {
        this.isIssue = isIssue;
    }

    public Integer getIsApp() {
        return isApp;
    }

    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
    }

    public String getAppointPassword() {
        return appointPassword;
    }

    public void setAppointPassword(String appointPassword) {
        this.appointPassword = appointPassword == null ? null : appointPassword.trim();
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
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
        this.auditUserId = auditUserId == null ? null : auditUserId.trim();
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName == null ? null : auditUserName.trim();
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSourceStore() {
        return sourceStore;
    }

    public void setSourceStore(String sourceStore) {
        this.sourceStore = sourceStore == null ? null : sourceStore.trim();
    }

    public BigDecimal getWeighting() {
        return weighting;
    }

    public void setWeighting(BigDecimal weighting) {
        this.weighting = weighting;
    }

    public Integer getIsTimingShow() {
        return isTimingShow;
    }

    public void setIsTimingShow(Integer isTimingShow) {
        this.isTimingShow = isTimingShow;
    }

    public String getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(String isTurn) {
        this.isTurn = isTurn == null ? null : isTurn.trim();
    }


    @Override
    public String toString() {
        return "ProductInput{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", businessId='" + businessId + '\'' +
                ", orginalId=" + orginalId +
                ", parentId=" + parentId +
                ", borrowUserId='" + borrowUserId + '\'' +
                ", borrowUserName='" + borrowUserName + '\'' +
                ", borrowAmount=" + borrowAmount +
                ", borrowAccountYes=" + borrowAccountYes +
                ", borrowFee=" + borrowFee +
                ", borrowApr=" + borrowApr +
                ", borrowType=" + borrowType +
                ", borrowFullStatus=" + borrowFullStatus +
                ", surplusInvestAmount=" + surplusInvestAmount +
                ", isJion=" + isJion +
                ", repayLastTime=" + repayLastTime +
                ", mateAmount=" + mateAmount +
                ", borrowUse='" + borrowUse + '\'' +
                ", isAutoPublish=" + isAutoPublish +
                ", isYetIssue=" + isYetIssue +
                ", autoPublishTime=" + autoPublishTime +
                ", publishTime=" + publishTime +
                ", isAutoInvest=" + isAutoInvest +
                ", productTitle='" + productTitle + '\'' +
                ", refundWay=" + refundWay +
                ", productPeriod=" + productPeriod +
                ", periodUnit='" + periodUnit + '\'' +
                ", defineType=" + defineType +
                ", awardType=" + awardType +
                ", awardAmount=" + awardAmount +
                ", awardScale=" + awardScale +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                ", availableTransferDay=" + availableTransferDay +
                ", integralFold=" + integralFold +
                ", isAuthCode=" + isAuthCode +
                ", isIssue=" + isIssue +
                ", isApp=" + isApp +
                ", appointPassword='" + appointPassword + '\'' +
                ", productStatus=" + productStatus +
                ", productType=" + productType +
                ", availableInvestTime=" + availableInvestTime +
                ", auditUserId='" + auditUserId + '\'' +
                ", auditUserName='" + auditUserName + '\'' +
                ", auditTime=" + auditTime +
                ", createTime=" + createTime +
                ", sourceStore='" + sourceStore + '\'' +
                ", weighting=" + weighting +
                ", isTimingShow=" + isTimingShow +
                ", isTurn='" + isTurn + '\'' +
                '}';
    }
}