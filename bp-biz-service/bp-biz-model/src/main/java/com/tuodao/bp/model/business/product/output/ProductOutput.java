package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductOutput implements Serializable {


    private static final long serialVersionUID = -715454201634638737L;
    private Integer id;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 业务ID: 业务系统推送过来的散标唯一标识
     */
    private String businessId;

    /**
     * 原始散标唯一标识
     */
    private Integer orginalId;

    /**
     * 父节点ID
     */
    private Integer parentId;

    /**
     * 借款人ID
     */
    private String borrowUserId;

    /**
     * 借款人名称
     */
    private String borrowUserName;

    /**
     * 借款金额（元）
     */
    private BigDecimal borrowAmount;

    /**
     * 实际借款人到账金额（元）
     */
    private BigDecimal borrowAccountYes;

    /**
     * 借款手续费（元）
     */
    private BigDecimal borrowFee;

    /**
     * 年利率
     */
    private BigDecimal borrowApr;

    /**
     * 标的类型 0：天标 1:月标
     */
    private Integer borrowType;

    /**
     * 满标时间
     */
    private Date borrowFullTime;

    /**
     *  对应的中文内容
     */
    private String borrowTypeText;
    private String refundWayText;
    private String productStatusText;
    private String periodUnitText;
    private String defineTypeText;
    /**类型的pc图片地址*/
    private String typePcUrl;
    /**类型的app图片地址*/
    private String typeAppUrl;
    /**类型的备注*/
    private String typeRemark;


    public Date getBorrowFullTime() {
        return borrowFullTime;
    }

    public void setBorrowFullTime(Date borrowFullTime) {
        this.borrowFullTime = borrowFullTime;
    }

    private Integer borrowFullStatus;

    /**
     * 剩余可投
     */
    private BigDecimal surplusInvestAmount;

    /**
     * 是否已加入精选计划  0:否 1：是
     */
    private Integer isJion;

    /**
     * 最后还款时间
     */
    private Date repayLastTime;

    /**
     * 已匹配金额
     */
    private BigDecimal mateAmount;

    /**
     * 借款用途
     */
    private String borrowUse;

    /**
     * 是否为自动发布 0:否 1：是
     */
    private Integer isAutoPublish;

    /**
     * 是否已发布（后台已经发布未必前台一定展示 ） 0:否 1：是'
     */
    private Integer isYetIssue;

    /**
     * 自动发布时间
     */
    private Date autoPublishTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 是否为自动投标标的 0:否 1：是
     */
    private Integer isAutoInvest;

    /**
     * 产品标题
     */
    private String productTitle;

    /**
     * 还款方式 0：等额本息 1：按月付息 2：按天付息
     */
    private Integer refundWay;

    /**
     * 产品期限（借款期限）
     */
    private Integer productPeriod;
    /**
     * 期限单位 0：天（day）1：月（month）2：年（year）
     */
    private Integer periodUnit;
    /**
     * 自定义计划类型 关联（自定义标种类型表）
     */
    private Integer defineType;
    /**
     * 奖励类型 0:无 1:按金额 2:按比例
     */
    private Integer awardType;

    /**
     * 奖励值
     */
    private BigDecimal awardAmount;

    /**
     * 奖励利率
     */
    private BigDecimal awardScale;

    /**
     * 最低投资金额
     */
    private BigDecimal minAmount;
    /**
     * 最高投资金额
     */
    private BigDecimal maxAmount;
    /**
     * 可转天数
     */
    private Integer availableTransferDay;
    /**
     * 积分倍数
     */
    private Integer integralFold;
    /**
     * 是否需要验证码 0:否 1：是
     */
    private Integer isAuthCode;
    /**
     * 是否可发 0:否 1：是
     */
    private Integer isIssue;
    /**
     * APP专享 0:否 1：是
     */
    private Integer isApp;
    /**
     * 约标密码
     */
    private String appointPassword;
    /**
     * 产品状态 0：初始 1：被打回 2：被撤回 3：待审核4：待发布 5：募集中 6：满标待审7：还款中 8：已还款 9：已还款（提前还款）
     */
    private Integer productStatus;
    /**
     * 产品类别 0：散标 1:精选计划
     */
    private Integer productType;
    /**
     * 可投时间
     */
    private Date availableInvestTime;
    /**
     * 审核人id
     *
     */
    private String auditUserId;
    /**
     * 审核人
     */
    private String auditUserName;
    /**
     * 审核时间
     */
    private Date auditTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 来源门店
     */
    private String sourceStore;
    /**
     * 标展示标志位（前台看到一定是1） 0:否 1：是
     */
    private Integer isTimingShow;
    /**
     * 轮询标志位
     */
    private String isTurn;
    /**
     * 募集截止时间
     */
    private Date collectAbortTime;
    /**
     * 有效时间(天)
     */
    private Integer validTime;

    public Date getCollectAbortTime() {
        return collectAbortTime;
    }

    public void setCollectAbortTime(Date collectAbortTime) {
        this.collectAbortTime = collectAbortTime;
    }

    public Integer getValidTime() {
        return validTime;
    }

    public void setValidTime(Integer validTime) {
        this.validTime = validTime;
    }

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

    public String getBorrowTypeText() {
        return borrowTypeText;
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

    public String getTypePcUrl() {
        return typePcUrl;
    }

    public String getTypeAppUrl() {
        return typeAppUrl;
    }

    public String getTypeRemark() {
        return typeRemark;
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

    public Integer getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(Integer periodUnit) {
        this.periodUnit = periodUnit;
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

    public void setBorrowTypeText(String borrowTypeText) {
        this.borrowTypeText = borrowTypeText;
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
                ", borrowFullTime=" + borrowFullTime +
                ", borrowTypeText='" + borrowTypeText + '\'' +
                ", refundWayText='" + refundWayText + '\'' +
                ", productStatusText='" + productStatusText + '\'' +
                ", periodUnitText='" + periodUnitText + '\'' +
                ", defineTypeText='" + defineTypeText + '\'' +
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
                ", periodUnit=" + periodUnit +
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
                ", isTimingShow=" + isTimingShow +
                ", isTurn='" + isTurn + '\'' +
                ", collectAbortTime=" + collectAbortTime +
                ", validTime=" + validTime +
                '}';
    }

    public void setTypePcUrl(String typePcUrl) {
        this.typePcUrl = typePcUrl;
    }

    public void setTypeAppUrl(String typeAppUrl) {
        this.typeAppUrl = typeAppUrl;
    }

    public void setTypeRemark(String typeRemark) {
        this.typeRemark = typeRemark;
    }
}