package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;
import java.util.Date;

/**
 * 积分详情
 * author hechuan
 * <p>
 * created on 2017/9/29
 * <p>
 * since V1.0.0
 */
public class ScoreDetailOutput implements Serializable{

    private static final long serialVersionUID = -1659386797202735140L;

    /**
     * 发生时间
     */
    private Date hanppenTime;

    /**
     * 积分来源
     */
    private String source;

    /**
     * 类型（收入，支出）
     */
    private Integer type;

    /**
     * 积分
     */
    private Integer score;

    public Date getHanppenTime() {
        return hanppenTime;
    }

    public void setHanppenTime(Date hanppenTime) {
        this.hanppenTime = hanppenTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    
}
