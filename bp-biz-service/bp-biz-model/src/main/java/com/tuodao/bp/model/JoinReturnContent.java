package com.tuodao.bp.model;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
public class JoinReturnContent {

    //加入金额
    private String amount;

    //到期收益
    private String intrest;

    //使用优惠卷类型（0未使用1加息卷2抵扣卷）
    private String type;

    //加息卷（如1%）抵扣卷（如5元）
    private String typeValue;


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIntrest() {
        return intrest;
    }

    public void setIntrest(String intrest) {
        this.intrest = intrest;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
