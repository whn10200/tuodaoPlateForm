package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转保存 入参
 */
public class TransferModifyInput extends BasePojo {


    private static final long serialVersionUID = -5318389872424516431L;
    private Integer id;

    //状态
    @NotNull(message = CreditAssignmentConstant.STATUS_IS_NULL + "")
    private Integer status;

    //复审时间
    private Date verifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

