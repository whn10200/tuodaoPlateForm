package com.tuodao.bp.activemq.constant;

import org.apache.activemq.RedeliveryPolicy;

/**
 * author hechuan
 * <p>
 * created on 2017/10/19
 * <p>
 * since V1.0.0
 */
public class ActiveConstant {

    /**
     * 最大重试次数
     */
    public static final int MAX_REDILIVERD_COUNTER = RedeliveryPolicy.DEFAULT_MAXIMUM_REDELIVERIES - 1;
}
