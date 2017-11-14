package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * @Author wuchengjie
 * @Date 2017/9/19 0019 15:11
 * @Introduction
 */
public class ProductAppOutput implements Serializable {
    private static final long serialVersionUID = -715454201634638737L;
    private Integer id;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 年利率
     */
    private BigDecimal borrowApr;

    /**
     * 标的类型 0：天标 1:月标
     */
    private Integer borrowType;
    /**
     *  对应的中文内容
     */
    private String refundWayText;
    /**
     * 产品状态 0：初始 1：被打回 2：被撤回 3：待审核4：待发布 5：募集中 6：满标待审7：还款中 8：已还款 9：已还款（提前还款）
     */
    private Integer productStatus;
    private String productStatusText;
    private String periodUnitText;

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 自定义计划类型 关联（自定义标种类型表）
     */
    private Integer defineType;

    private String defineTypeText;
    /**类型的app图片地址*/
    private String typeAppUrl;
    /**类型的备注*/
    private String typeRemark;

    public ProductAppOutput(){

    }

    public Integer getDefineType() {
        return defineType;
    }

    public void setDefineType(Integer defineType) {
        this.defineType = defineType;
    }

    private Integer borrowFullStatus;

    /**
     * 剩余可投
     */
    private BigDecimal surplusInvestAmount;
    /**
     * 借款金额（元）
     */
    private BigDecimal borrowAmount;
    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 产品期限（借款期限）
     */
    private Integer productPeriod;
    /**
     * 期限单位 0：天（day）1：月（month）2：年（year）
     */
    private Integer periodUnit;
    /**
     * 奖励利率
     */
    private BigDecimal awardScale;
    /**
     * 积分倍数
     */
    private Integer integralFold;
    /**
     * APP专享 0:否 1：是
     */
    private Integer isApp;
    /**
     * 产品类别 0：散标 1:精选计划
     */
    private Integer productType;
    /**
     * 可投时间
     */
    private Date availableInvestTime;


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
    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }
    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle == null ? null : productTitle.trim();
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
    }

    public Integer getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Integer periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Integer getIsApp() {
        return isApp;
    }

    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
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


    public void setRefundWayText(String refundWayText) {
        this.refundWayText = refundWayText;
    }

    public void setPeriodUnitText(String periodUnitText) {
        this.periodUnitText = periodUnitText;
    }

    public void setProductStatusText(String productStatusText) {
        this.productStatusText = productStatusText;
    }

    public String getProductStatusText() {
        return productStatusText;
    }

    public void setDefineTypeText(String defineTypeText) {
        this.defineTypeText = defineTypeText;
    }

    @Override
    public String toString() {
        return "ProductOutput{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", borrowApr=" + borrowApr +
                ", borrowType=" + borrowType +
                ", refundWayText='" + refundWayText + '\'' +
                ", productStatusText='" + productStatusText + '\'' +
                ", periodUnitText='" + periodUnitText + '\'' +
                ", defineTypeText='" + defineTypeText + '\'' +
                ", borrowFullStatus=" + borrowFullStatus +
                ", surplusInvestAmount=" + surplusInvestAmount +
                ", borrowAmount=" + borrowAmount +
                ", productTitle='" + productTitle + '\'' +
                ", productPeriod=" + productPeriod +
                ", periodUnit=" + periodUnit +
                ", awardScale=" + awardScale +
                ", integralFold=" + integralFold +
                ", isApp=" + isApp +
                ", productType=" + productType +
                ", availableInvestTime=" + availableInvestTime +
                '}';
    }


    public String getRefundWayText() {
        return refundWayText;
    }

    public String getPeriodUnitText() {
        return periodUnitText;
    }

    public String getDefineTypeText() {
        return defineTypeText;
    }

    public String getTypeAppUrl() {
        return typeAppUrl;
    }

    public void setTypeAppUrl(String typeAppUrl) {
        this.typeAppUrl = typeAppUrl;
    }

    public String getTypeRemark() {
        return typeRemark;
    }

    public void setTypeRemark(String typeRemark) {
        this.typeRemark = typeRemark;
    }

    public BigDecimal getAwardScale() {
        return awardScale;
    }

    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    public Integer getIntegralFold() {
        return integralFold;
    }

    public void setIntegralFold(Integer integralFold) {
        this.integralFold = integralFold;
    }
}
