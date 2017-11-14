package com.tuodao.bp.api.core.log;

import com.tuodao.bp.api.core.spring.SystemOperateLogComponent;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * 操作日志记录类
 *
 * @author hechuan
 *
 * @since V1.0.0
 *
 * @Created 2017-11-10
 */
public abstract class LogRecordHandler implements InitializingBean {

    /** 用户操作日志组件 */
    @Resource
    private SystemOperateLogComponent systemOperateLogComponent;

    /**
     * 记录操作日志
     *
     * @param userOperateLog 操作日志
     */
    public abstract void recordLog(SystemOperateLog systemOperateLog);

    @Override
    public void afterPropertiesSet() throws Exception {
        systemOperateLogComponent.addLogRecordHandler(this);
    }

}
