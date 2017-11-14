package com.tuodao.bp.model.business.useraccount.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;

import java.io.Serializable;

/**
 * @description: 用户头像信息
 * @author: mif
 * @date: 2017/10/12
 * @time: 16:22
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserAvatarInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = -2277625367689056369L;

    /**
     * 头像地址
     */
    private String avatarUrl;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public UserAvatarInput(String userId, String avatarUrl) {
        this.userId = userId;
        this.avatarUrl = avatarUrl;
    }

    public UserAvatarInput() {
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("avatarUrl", avatarUrl)
                .add("userId", userId)
                .toString();
    }
}
