package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.util.Date;

/**
 * ProductAuditRecordOutput
 * @author Administrator
 */
public class BorrowTypeOutput  implements Serializable {

    private static final long serialVersionUID = -4620232352753343502L;
    private Integer id;

    private String code;

    private String name;

    private Integer isSpecial;

    private Integer vipLevel;

    private String appPictureUrl;

    private String pcPictureUrl;

    private String remark;

    private Date createTime;

    private String createUserId;

    private Integer orderNumber;

    private Integer enable;

    private Integer isShow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public String getAppPictureUrl() {
        return appPictureUrl;
    }

    public void setAppPictureUrl(String appPictureUrl) {
        this.appPictureUrl = appPictureUrl == null ? null : appPictureUrl.trim();
    }

    public String getPcPictureUrl() {
        return pcPictureUrl;
    }

    public void setPcPictureUrl(String pcPictureUrl) {
        this.pcPictureUrl = pcPictureUrl == null ? null : pcPictureUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    @Override
    public String toString() {
        return "BorrowTypeOutput{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", isSpecial=" + isSpecial +
                ", vipLevel=" + vipLevel +
                ", appPictureUrl='" + appPictureUrl + '\'' +
                ", pcPictureUrl='" + pcPictureUrl + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", createUserId='" + createUserId + '\'' +
                ", orderNumber=" + orderNumber +
                ", enable=" + enable +
                ", isShow=" + isShow +
                '}';
    }
}