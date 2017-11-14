package com.tuodao.bp.model.business.operation.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.operation.OpConstant;
import com.tuodao.bp.model.constant.operation.OperationResponseConstant;

import java.io.Serializable;

/**
 * 抽奖查询
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
public class InverstmentQueryInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = -3548145896651196412L;

    /**
     * 抽奖类型
     */
    @In(value = OpConstant.TYPE_INVERST_ALL,required = true,message = OperationResponseConstant.PARAM_SCORE_INVERST_TYPE_ERROR+"")
    private Integer inverstType;

    public Integer getInverstType() {
        return inverstType;
    }

    public void setInverstType(Integer inverstType) {
        this.inverstType = inverstType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("inverstType",inverstType)
                .toString();
    }
}
