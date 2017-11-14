package com.tuodao.bp.model.business;

import com.tuodao.bp.model.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 用户Id列表
 * @author: mif
 * @date: 2017/10/25
 * @time: 10:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserIdListInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = 323952264978201143L;

    /**
     * 用户Id列表
     */
    private List<String> userIds;
}
