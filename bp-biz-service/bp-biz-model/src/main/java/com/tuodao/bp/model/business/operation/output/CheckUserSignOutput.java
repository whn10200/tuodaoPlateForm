package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;

/**
 * Description: 校验用户签到输出类
 * User: zkai
 * Date: 2017/11/7
 * Time: 14:03
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class CheckUserSignOutput implements Serializable {

    private static final long serialVersionUID = 3910227266550086865L;

    /**
     * 是否签到
     */
    private Boolean ifSign;

    /**
     * 签到积分
     */
    private int signScore;

    public Boolean getIfSign() {
        return ifSign;
    }

    public void setIfSign(Boolean ifSign) {
        this.ifSign = ifSign;
    }

    public int getSignScore() {
        return signScore;
    }

    public void setSignScore(int signScore) {
        this.signScore = signScore;
    }
}
