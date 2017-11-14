package com.tuodao.bp.model.facade.operation.input;

import com.tuodao.bp.model.BasePojo;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 根据银行卡列表，获取用户银行信息传入信息
 * User: zkai
 * Date: 2017/10/30
 * Time: 16:33
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FindBankInfoInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = 8736941330905929258L;

    private List<String> bankIds;

    public List<String> getBankIds() {
        return bankIds;
    }

    public void setBankIds(List<String> bankIds) {
        this.bankIds = bankIds;
    }
}
