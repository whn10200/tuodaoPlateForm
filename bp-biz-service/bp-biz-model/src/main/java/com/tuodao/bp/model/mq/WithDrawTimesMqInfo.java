package com.tuodao.bp.model.mq;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 免费提现次数消息队列INFO
 * author hechuan
 * <p>
 * created on 2017/10/23
 * <p>
 * since V1.0.0
 */
@Data
@ToString
public class WithDrawTimesMqInfo implements Serializable {

    private static final long serialVersionUID = -1712848315319661716L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 次数
     * +2 | -2
     */
    private int times;


}
