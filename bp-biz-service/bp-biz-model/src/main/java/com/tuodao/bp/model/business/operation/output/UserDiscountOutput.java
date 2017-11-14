package com.tuodao.bp.model.business.operation.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.tuodao.bp.utils.TimeUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的优惠券输出类
 * author hechuan
 * <p>
 * created on 2017/9/21
 * <p>
 * since V1.0.0
 */
public class UserDiscountOutput implements Serializable {
    private static final long serialVersionUID = -9055123542078758664L;

    /**
     * 优惠券ID
     */
    private Long id;

    /**
     * 标题
     */
    private String discountTitle;

    /**
     * 类型(1:抵用券,2:加息券)
     */
    private Integer discountType;

    /**
     * 优惠券状态（1：可使用，2：已使用，3：已过期）
     */
    private Integer disStatus;
    /**
     * 额度(抵用券就是多少钱，加息券就是百分比)
     */
    private String discountAvailable;

    /**
     * 生效日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date effectDate;

    /**
     * 有效期(天)
     */
    private Integer effectDay;

    /**
     * 失效日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date invalidDate;

    /**
     * 来源(如：新手福利，存管福利)
     */
    private String source;

    /**
     * 金额限制(如:5000元以上的标的)
     */
    private Integer moneyLimit;

    /**
     * 时长限制(如:12个月及以上)
     */
    private Integer dateLimit;

    /**
     * 生效-失效说明
     */
    private String dateExplain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateExplain(String dateExplain) {
        this.dateExplain = dateExplain;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public String getDiscountAvailable() {
        return discountAvailable;
    }

    public void setDiscountAvailable(String discountAvailable) {
        this.discountAvailable = discountAvailable;
    }


    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Integer getEffectDay() {
        return effectDay;
    }

    public void setEffectDay(Integer effectDay) {
        this.effectDay = effectDay;
    }


    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getMoneyLimit() {
        return moneyLimit;
    }

    public void setMoneyLimit(Integer moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    public Integer getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Integer dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public Integer getDisStatus() {
        return disStatus;
    }

    public void setDisStatus(Integer disStatus) {
        this.disStatus = disStatus;
    }

    public String getDateExplain() {
        // 开始日期
        String beginDate = TimeUtils.format(this.effectDate, "yyyy/MM/dd");

        // 结束日期
        String endDate = TimeUtils.format(this.invalidDate, "yyyy/MM/dd");

        dateExplain = Joiner.on("-").join(beginDate, endDate);
        return dateExplain;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("discountTitle", discountTitle)
                .add("discountType", discountType)
                .add("disStatus", disStatus)
                .add("discountAvailable", discountAvailable)
                .add("effectDate", effectDate)
                .add("effectDay", effectDay)
                .add("invalidDate", invalidDate)
                .add("source", source)
                .add("moneyLimit", moneyLimit)
                .add("dateLimit", dateLimit)
                .add("dateExplain", getDateExplain())
                .toString();
    }
}
