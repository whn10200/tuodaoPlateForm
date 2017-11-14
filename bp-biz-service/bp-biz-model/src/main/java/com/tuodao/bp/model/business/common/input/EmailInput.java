package com.tuodao.bp.model.business.common.input;

import com.tuodao.bp.model.constant.common.CommonExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 邮件发送请求封装
 * @author: mif
 * @date: 2017/9/11
 * @time: 09:55
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class EmailInput implements Serializable {

    private static final long serialVersionUID = -341434542890754760L;

    /**
     * 主题
     */
    @Size(max = 200, message = CommonExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String subject;

    /**
     * 收件人，多个已分号“；”分隔
     */
    @NotBlank(message = CommonExceptionConstant.EMAIL_TO_CAN_NOT_BE_BLANK + "")
    @Size(max = 200, message = CommonExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String receivers;

    /**
     * 邮件内容
     */
    @NotBlank(message = CommonExceptionConstant.EMAIL_CONTENT_CAN_NOT_BE_BLANK + "")
    private String content;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceivers() {
        return receivers;
    }

    public void setReceivers(String receivers) {
        this.receivers = receivers;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EmailInput{" +
                "subject='" + subject + '\'' +
                ", receivers='" + receivers + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
