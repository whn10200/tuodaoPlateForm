package com.tuodao.bp.model.facade.operation.output;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 内容管理 输出类
 * User: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpContentManagelListOutput implements Serializable {

    private static final long serialVersionUID = 7643040165021362527L;

    private String title;

    private Integer contentId;

    private Date publishTime;

    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
