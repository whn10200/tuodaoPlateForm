package com.tuodao.bp.model.business.common.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.constant.common.CommonExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 短信请求封装
 * @author: mif
 * @date: 2017/5/9
 * @time: 15:58
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SmsInput implements Serializable {

    private static final long serialVersionUID = -4606783222108578254L;
    /**
     * 手机号码（多个已分号“；”分隔,最多50个）
     */
    @NotBlank(message = CommonExceptionConstant.MOBILE_CAN_NOT_BE_NULL + "")
    private String mobiles;

    /**
     * 短信内容
     */
    @NotBlank(message = CommonExceptionConstant.CONTENT_CAN_NOT_BE_NULL + "")
    @Size(max = 300, message = CommonExceptionConstant.CONTENT_LENGTH_MAX_300 + "")
    private String content;

    /**
     * 客户端请求IP
     */
    @NotBlank(message = CommonExceptionConstant.CUSTOMER_IP_CAN_NOT_BE_NULL + "")
    @Pattern(regexp = "[0-9]+(?:\\.[0-9]+){0,3}", message = CommonExceptionConstant.CUSTOMER_IP_FORMAT_ERROR + "")
    private String customerIp;

    /**
     * 是否内部调用
     */
    private boolean inner = false;

    /**
     * 短信备注
     */
    private String remark;

    public SmsInput() {
    }

    public SmsInput(String mobiles, String content, String customerIp, String remark, boolean inner) {
        this.mobiles = mobiles;
        this.content = content;
        this.customerIp = customerIp;
        this.remark = remark;
        this.inner = inner;
    }

    public SmsInput(String mobiles, String content, String customerIp, String remark) {
        this.mobiles = mobiles;
        this.content = content;
        this.customerIp = customerIp;
        this.remark = remark;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles;
    }

    public boolean isInner() {
        return inner;
    }

    public void setInner(boolean inner) {
        this.inner = inner;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SmsInput{" +
                "mobiles='" + mobiles + '\'' +
                ", content='" + content + '\'' +
                ", customerIp='" + customerIp + '\'' +
                ", inner=" + inner +
                ", remark='" + remark + '\'' +
                '}';
    }
}
