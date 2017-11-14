package com.tuodao.bp.api.core.spring;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.tuodao.bp.api.core.log.LogRecordHandler;
import com.tuodao.bp.api.core.log.SystemOperateLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Async;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户操作日志组件
 *
 * @author hechuan
 *
 * @since V1.0.0
 *
 * @Created 2017-11-10
 */
public class SystemOperateLogComponent {

    /** 日志记录类 */
    private static final Logger LOGGER = LoggerFactory.getLogger(SystemOperateLogComponent.class);

    /** 日志记录 */
    private final ThreadLocal<SystemOperateLog> threadLocalLog = new ThreadLocal<>();

    /** 日志记录类列表 */
    private final List<LogRecordHandler> logHandlers = Lists.newArrayList();

    /** 转换成json */
    @Resource
    private MappingJackson2HttpMessageConverter messageConverter;

    /**
     * 取得当前用户操作日志
     *
     * @return  当前用户操作日志
     */
    public SystemOperateLog currentUserOperateLog() {

        SystemOperateLog log = threadLocalLog.get();

        if (log == null) {
            log = new SystemOperateLog();
            threadLocalLog.set(log);
        }

        return log;
    }

    /**
     * 添加一个日志记录类
     *
     * @param logRecordHandler 日志记录类
     */
    public void addLogRecordHandler(LogRecordHandler logRecordHandler){
        logHandlers.add(logRecordHandler);
    }

    /**
     * 记录日志
     */
    public void recordLog(){
        // 取得用户操作日志
        SystemOperateLog systemOperateLog = threadLocalLog.get();
        recordLog(systemOperateLog);
        removeCurrentUserOperateLog();
    }

    /**
     * 使用日志记录类列表记录日志
     *
     * @param userOperateLog    用户操作日志
     */
    @Async
    public void recordLog(SystemOperateLog userOperateLog){

        try {
            // 如果是RespBody返回体
            if (userOperateLog.getRespResult() != null) {

                // 将完整返回信息当作备注
                String remark = messageConverter.getObjectMapper().writeValueAsString(userOperateLog.getRespResult());
                userOperateLog.setRemark(remark);

            }
        } catch (JsonProcessingException e) {
            LOGGER.error("用户操作日志记录时转换请求体成json时出错了。", e);
        }

        // 如果UserId为空，则为DEF_USERID
        if (Strings.isNullOrEmpty(userOperateLog.getUserId())) {
            userOperateLog.setUserId("DEF_USERID");
        }
        // 如果MOBILE为空，则为DEF_MOBILE
        if (Strings.isNullOrEmpty(userOperateLog.getMobile())) {
            userOperateLog.setMobile("DEF_MOBILE");
        }

        // 使用日志记录类列表分别记录日志
        for (LogRecordHandler handler : logHandlers) {
            handler.recordLog(userOperateLog);
        }
    }

    /**
     * 删除当前用户操作日志
     */
    private void removeCurrentUserOperateLog(){
        threadLocalLog.remove();
    }

    /** 默认日志记录类 */
    public static class DefaultLogRecordHandler extends LogRecordHandler {

        @Override
        public void recordLog(SystemOperateLog systemOperateLog) {
            LOGGER.info("请求结束：{}", systemOperateLog);
        }
    }

}
