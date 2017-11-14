package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;

/**
 * 只有key和id的输入类
 */
public class JustKeyAndIdInput implements Serializable{

    private static final long serialVersionUID = 433946259900363650L;
    private String key;

    private Integer justId;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getJustId() {
        return justId;
    }

    public void setJustId(Integer justId) {
        this.justId = justId;
    }
}
