package com.tuodao.bp.model.business.operation.input;


import com.tuodao.bp.model.business.traningcenter.output.SelectNoMateOutput;

import java.io.Serializable;

/**
 * @Author wuchengjie
 * @Date 2017/10/18 0018 13:59
 * @Introduction  投资优惠券 类
 */
public class TenderVoucherInput implements Serializable {


    /** 投资id*/
    private Integer tenderId;

    /**
     * 券id
     */
    private Integer voucherId;


    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }


    public TenderVoucherInput() {
    }

    public TenderVoucherInput toTenderVoucherInput(SelectNoMateOutput output)
    {
        this.tenderId = output.getId();

        if(output.getVoucherId() != null && output.getVoucherId()>0){
            this.voucherId = output.getVoucherId();
        }else if(output.getVoucherCouponId() != null && output.getVoucherCouponId()>0){
            this.voucherId = output.getVoucherCouponId();
        }

        return this;
    }
}
