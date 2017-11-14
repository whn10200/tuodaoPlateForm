package com.tuodao.bp.model.business.operation.output;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户免费提现次数
 * author hechuan
 * <p>
 * created on 2017/10/25
 * <p>
 * since V1.0.0
 */
@Data
public class UserFreeNumberOutput implements Serializable {
    private static final long serialVersionUID = -5596490250839173310L;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户免费提现次数
     */
    private Integer freeNumber;
}
