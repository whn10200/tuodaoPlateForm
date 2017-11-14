package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.constant.operation.OperationResponseConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 抽奖input
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
public class InverstmentInput extends InverstmentQueryInput implements Serializable {

    private static final long serialVersionUID = -1869735492063805942L;

    /**
     * 抽奖所需积分
     */
    @NotNull(message = OperationResponseConstant.PARAM_SCORE_INVERST_SCORE_ERROR + "")
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return super.toString() + ",score = " + score;
    }
}
