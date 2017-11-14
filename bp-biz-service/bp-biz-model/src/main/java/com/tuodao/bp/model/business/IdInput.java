package com.tuodao.bp.model.business;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.facade.FacadeExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: Id
 * @author: mif
 * @date: 2017/10/11
 * @time: 16:19
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class IdInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = -4605522547687850559L;

    @NotNull(message = FacadeExceptionConstant.ID_CAN_NOT_BE_BLANK + "")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IdInput(int id,String userId){
        this.id = (long)id;
        this.userId = userId;
    }

    public IdInput(){

    }
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .toString();
    }
}
