package com.tuodao.bp.model.business.operation.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.operation.OperationResponseConstant;
import com.tuodao.bp.model.constant.operation.ScoreExchangeConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 积分兑换input
 * author hechuan
 * <p>
 * created on 2017/9/26
 * <p>
 * since V1.0.0
 */
public class ScoreExchangeInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = 5005292587919125444L;

    /**
     *  兑换数量
     */
    @NotNull(message= OperationResponseConstant.PARAM_SCORE_EXCHANGE_NUM_ERROR + "")
    private Integer num;

    /**
     * 所需要积分，前端算好，传给我
     * 比如 加息券1张需要100积分，如果传递数量为2，则这里是算好的200积分传到后端
     */
    @NotNull(message= OperationResponseConstant.PARAM_SCORE_EXCHANGE_SCORE_ERROR + "")
    private Integer sumScore;

    /**
     * 积分商城ID
     */
    @NotNull(message= OperationResponseConstant.PARAM_SCORE_EXCHANGE_ID_ERROR + "")
    private Long id;

    /**
     * 类型(1:加息券,2:免费提现次数)
     */
    @In(value = ScoreExchangeConstant.TYPE_FREE_INCREASE_ALL,required = true,message = OperationResponseConstant.PARAM_SCORE_CHANGE_TYPE_MATCH_ERROR+"")
    @NotNull(message= OperationResponseConstant.PARAM_SCORE_EXCHANGE_TYPE_ERROR + "")
    private Integer type;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",id)
                .add("type",type)
                .add("num",num)
                .add("sumScore",sumScore)
                .toString();
    }
}
