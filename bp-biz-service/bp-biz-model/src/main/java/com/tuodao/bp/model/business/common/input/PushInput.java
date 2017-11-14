package com.tuodao.bp.model.business.common.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.annotation.TimeFormat;
import com.tuodao.bp.model.constant.push.PushConstants;
import com.tuodao.bp.model.constant.push.PushResposeConstans;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Map;

/**
 * Description: 消息推送输入类
 * User: zkai
 * Date: 2017/8/17
 * Time: 11:47
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class PushInput implements Serializable {
    private static final long serialVersionUID = -6319415790646951195L;

    /**
     * 推送对象 1:所有平台 2: 推送到对应别名的设备上 3:推送给安卓 4:推送给ios
     */
    @In(value = PushConstants.PUSH_OJECT_ALLS,message = PushResposeConstans.PUSH_OBJECT_IS_ERROR + "")
    public Integer pushObject;

    /**
     * 透传内容：格式
     */
    @NotBlank(message = PushResposeConstans.PUSH_CONTENT_NOT_NULL + "")
    public String pushContent;


    /**
     * 推送别名列表(使用","分割)
     */
    public String pushAlias;

    /**
     * 时间格式 2016-07-30 12:30:25
     */
    @TimeFormat(format = "yyyy-MM-dd HH:mm:ss",message = PushResposeConstans.PUSH_TIME_ERROR+"")
    public String pushTime;

    public Integer getPushObject() {
        return pushObject;
    }

    public void setPushObject(Integer pushObject) {
        this.pushObject = pushObject;
    }


    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }

    public String getPushAlias() {
        return pushAlias;
    }

    public void setPushAlias(String pushAlias) {
        this.pushAlias = pushAlias;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }
}
