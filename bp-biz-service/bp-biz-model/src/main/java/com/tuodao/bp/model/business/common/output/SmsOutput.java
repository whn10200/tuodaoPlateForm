package com.tuodao.bp.model.business.common.output;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Map;

/**
 * @description: 短信发送返回类
 * @author: mif
 * @date: 2017/5/16
 * @time: 14:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SmsOutput implements Serializable {

    private static final long serialVersionUID = -4011318403224604533L;

    /**
     * 成功记录数
     */
    private Integer successCount;

    /**
     * 失败记录数
     */
    private Integer errorCount;

    /**
     * 失败号码列表
     */
    private Map<String, String> errorMap;

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("successCount", successCount)
                .add("errorCount", errorCount)
                .add("errorMap", errorMap)
                .toString();
    }
}
