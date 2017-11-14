package com.tuodao.bp.operation.persistence.model.basic;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OpInviteAwardRecord implements Serializable {
    private Integer id;

    private String userId;

    private String invitePhoneNum;

    private String directInvitee;

    private String indirectInvitee;

    private Integer awardType;

    private BigDecimal awardMoney;

    private Date releaseTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getInvitePhoneNum() {
        return invitePhoneNum;
    }

    public void setInvitePhoneNum(String invitePhoneNum) {
        this.invitePhoneNum = invitePhoneNum == null ? null : invitePhoneNum.trim();
    }

    public String getDirectInvitee() {
        return directInvitee;
    }

    public void setDirectInvitee(String directInvitee) {
        this.directInvitee = directInvitee == null ? null : directInvitee.trim();
    }

    public String getIndirectInvitee() {
        return indirectInvitee;
    }

    public void setIndirectInvitee(String indirectInvitee) {
        this.indirectInvitee = indirectInvitee == null ? null : indirectInvitee.trim();
    }

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public BigDecimal getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(BigDecimal awardMoney) {
        this.awardMoney = awardMoney;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("invitePhoneNum", invitePhoneNum)
                .add("directInvitee", directInvitee)
                .add("indirectInvitee", indirectInvitee)
                .add("awardType", awardType)
                .add("awardMoney", awardMoney)
                .add("releaseTime", releaseTime)
                .add("remark", remark)
                .toString();
    }
}