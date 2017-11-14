package com.tuodao.bp.common.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class DelaySms implements Serializable {
    private Long id;

    private String requestIp;

    private String mobiles;

    private String content;

    private String customsIp;

    private Date gmtCreate;

    private Integer isDel;

    private String remark;

    private static final long serialVersionUID = 1L;

    public DelaySms(String requestIp, String mobiles, String content, String customsIp,String remark) {
        this.requestIp = requestIp;
        this.mobiles = mobiles;
        this.content = content;
        this.customsIp = customsIp;
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp == null ? null : requestIp.trim();
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {
        this.mobiles = mobiles == null ? null : mobiles.trim();
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

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}