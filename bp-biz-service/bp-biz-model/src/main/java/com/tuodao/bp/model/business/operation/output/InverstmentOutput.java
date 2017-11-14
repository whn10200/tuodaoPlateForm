package com.tuodao.bp.model.business.operation.output;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * 抽奖结果output
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
public class InverstmentOutput implements Serializable {

    private static final long serialVersionUID = 3910227266550086865L;

    /**
     * 抽奖种类（1：10积分，2：100积分）
     */
    private Integer drawType;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品类型(1:积分,2:抵用券,3:加息券,4:实物)
     */
    private Integer prizeType;

    /**
     * 奖品额度(如:10,266等)
     */
    private Integer prizeValue;

    /**
     * 金额限制
     */
    private Integer moneyLimit;

    /**
     * 时长限制
     */
    private Integer dateLimit;

    public Integer getDrawType() {
        return drawType;
    }

    public void setDrawType(Integer drawType) {
        this.drawType = drawType;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public Integer getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    public Integer getPrizeValue() {
        return prizeValue;
    }

    public void setPrizeValue(Integer prizeValue) {
        this.prizeValue = prizeValue;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("drawType", drawType)
                .add("prizeName", prizeName)
                .add("prizeType", prizeType)
                .add("prizeValue", prizeValue)
                .add("moneyLimit", moneyLimit)
                .add("dateLimit", dateLimit)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(drawType,prizeName,prizeType,prizeValue,moneyLimit,dateLimit);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InverstmentOutput) {
            InverstmentOutput ident = (InverstmentOutput) obj;
            return Objects.equal(drawType,ident.drawType)
                    && Objects.equal(prizeName,ident.prizeName)
                    && Objects.equal(prizeType,ident.prizeType)
                    && Objects.equal(prizeValue,ident.prizeValue)
                    && Objects.equal(moneyLimit,ident.moneyLimit)
                    && Objects.equal(dateLimit,ident.dateLimit);
        }
        return false;
    }
}
