package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;

/**
 * 登录之后，我的积分统计值（可用积分，累计积分，已使用积分）
 * author hechuan
 * <p>
 * created on 2017/9/26
 * <p>
 * since V1.0.0
 */
public class MyScoreOutput implements Serializable{

    private static final long serialVersionUID = -638958732154083599L;
    /**
     * 累计总积分
     */
    private Integer scoreTotal;

    /**
     * 已使用积分
     */
    private Integer scoreUsed;

    /**
     * 当前可用积分
     */
    private Integer scoreCurrent;

    /**
     * 今年将要过期积分
     */
    private Integer scopeExpireYear;


    public static MyScoreOutput create(){
        return new MyScoreOutput();
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public MyScoreOutput setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
        return this;
    }

    public Integer getScoreUsed() {
        return scoreUsed;
    }

    public MyScoreOutput setScoreUsed(Integer scoreUsed) {
        this.scoreUsed = scoreUsed;
        return this;
    }

    public Integer getScoreCurrent() {
        return scoreCurrent;
    }

    public MyScoreOutput setScoreCurrent(Integer scoreCurrent) {
        this.scoreCurrent = scoreCurrent;
        return this;
    }

    public Integer getScopeExpireYear() {
        return scopeExpireYear;
    }

    public MyScoreOutput setScopeExpireYear(Integer scopeExpireYear) {
        this.scopeExpireYear = scopeExpireYear;
        return this;
    }
}
