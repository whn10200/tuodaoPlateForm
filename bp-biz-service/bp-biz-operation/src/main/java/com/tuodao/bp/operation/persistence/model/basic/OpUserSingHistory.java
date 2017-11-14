package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class OpUserSingHistory implements Serializable {
    private Long id;

    private String userId;

    private Date signTime;

    private Date lastSingDate;

    private String isContinuous;

    private Integer thisSignScore;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    public Date getLastSingDate() {
        return lastSingDate;
    }

    public void setLastSingDate(Date lastSingDate) {
        this.lastSingDate = lastSingDate;
    }

    public String getIsContinuous() {
        return isContinuous;
    }

    public void setIsContinuous(String isContinuous) {
        this.isContinuous = isContinuous == null ? null : isContinuous.trim();
    }

    public Integer getThisSignScore() {
        return thisSignScore;
    }

    public void setThisSignScore(Integer thisSignScore) {
        this.thisSignScore = thisSignScore;
    }
}