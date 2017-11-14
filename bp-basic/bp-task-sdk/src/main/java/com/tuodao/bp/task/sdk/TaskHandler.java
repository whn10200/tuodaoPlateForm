package com.tuodao.bp.task.sdk;

import com.tuodao.bp.task.sdk.event.TaskEvent;

/**
 * 业务实现定时钟所需要执行的业务逻辑
 * author hechuan
 * <p>
 * created on 2017/9/14
 * <p>
 * since V1.0.0
 */
public interface TaskHandler {
    /**
     * 执行
     */
    boolean execute(TaskEvent taskEvent);


}
