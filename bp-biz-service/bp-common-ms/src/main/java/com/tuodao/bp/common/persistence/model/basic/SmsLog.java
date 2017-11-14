package com.tuodao.bp.common.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class SmsLog implements Serializable {
    private Integer id;

    private String requestIp;

    private Date requestTime;

    private Integer smsServicer;

    private String mobile;

    private String content;

    private String customsIp;

    private Date resposeTime;

    private String resposeContent;

    private String resposeResult;

    private String remark;

    private static final long serialVersionUID = 1L;


    public SmsLog() {
    }

    public SmsLog(String requestIp, Date requestTime, Integer smsServicer, String mobile, String content, String customsIp, String remark) {
        this.requestIp = requestIp;
        this.requestTime = requestTime;
        this.smsServicer = smsServicer;
        this.mobile = mobile;
        this.content = content;
        this.customsIp = customsIp;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public Integer getSmsServicer() {
        return smsServicer;
    }

    public void setSmsServicer(Integer smsServicer) {
        this.smsServicer = smsServicer;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCustomsIp() {
        return customsIp;
    }

    public void setCustomsIp(String customsIp) {
        this.customsIp = customsIp == null ? null : customsIp.trim();
    }

    public Date getResposeTime() {
        return resposeTime;
    }

    public void setResposeTime(Date resposeTime) {
        this.resposeTime = resposeTime;
    }

    public String getResposeContent() {
        return resposeContent;
    }

    public void setResposeContent(String resposeContent) {
        this.resposeContent = resposeContent == null ? null : resposeContent.trim();
    }

    public String getResposeResult() {
        return resposeResult;
    }

    public void setResposeResult(String resposeResult) {
        this.resposeResult = resposeResult == null ? null : resposeResult.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}