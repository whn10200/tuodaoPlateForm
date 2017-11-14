package com.tuodao.bp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * @description: 投标结果返回
 * @author: 王艳兵
 * @date: 2017/11/9
 * @time: 17:53
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderResult implements Serializable {

    private static final long serialVersionUID = -7036977532892049649L;
    /**
     * 返回状态（0处理中 1成功 2失败 ）
     */
    private int status;

    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 调用H5的链接 带参数
     */
    private String url;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
