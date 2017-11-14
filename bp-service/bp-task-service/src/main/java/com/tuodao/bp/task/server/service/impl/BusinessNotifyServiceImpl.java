package com.tuodao.bp.task.server.service.impl;

import com.google.common.collect.Maps;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.task.server.constant.EnumConstant;
import com.tuodao.bp.task.server.dao.mapper.BusinessServerMapper;
import com.tuodao.bp.task.server.dao.mapper.TaskMapper;
import com.tuodao.bp.task.server.dao.mapper.TaskResultMapper;
import com.tuodao.bp.task.server.dao.model.*;
import com.tuodao.bp.task.server.service.IBusinessNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;


/**
 * Description:
 * User: sxj
 * Date: 2017/5/22 0022
 * Time: 20:43
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class BusinessNotifyServiceImpl implements IBusinessNotifyService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessNotifyServiceImpl.class);

    private static final String URL_APPEND ="/task?taskId={taskId}";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    BusinessServerMapper businessServerMapper;

    @Autowired
    TaskResultMapper taskResultMapper;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "taskTimerSendQueue")
    private javax.jms.Queue taskTimerSendQueue;

    @Override
    public void notifyBusiness(int taskId) {
        // 查询对应的task任务
        Task task = taskMapper.selectByPrimaryKey(taskId);

        TaskResult taskResult = new TaskResult();
        String success = "SUCCESS";

        // http 通知
        if (task.getNotify() == EnumConstant.Notify.NOTIFY_1.getType()){
            // 获取执行server
            List<BusinessServer> vector = getBusinessServers(task);

            for (BusinessServer businessServer:vector){

                // 执行一次
                if (task.getMathematics()==EnumConstant.Mathematics.MATHEMATICS_1.getType()){
                    String result = restTemplate.getForObject(businessServer.getServeradd()+URL_APPEND, String.class,  taskId);
                    if (success.equals(result)){
                        saveTaskResult(taskId, taskResult, businessServer,EnumConstant.ResultStatus.RESULT_STATUS_3);
                    }else {
                        saveTaskResult(taskId, taskResult, businessServer,EnumConstant.ResultStatus.RESULT_STATUS_2);
                        logger.error("任务执行失败，原因：http返回结果[{}]",result);
                    }
                }
                // 保证成功
                else if (task.getMathematics()==EnumConstant.Mathematics.MATHEMATICS_2.getType()){
                    String result = restTemplate.getForObject(businessServer.getServeradd()+URL_APPEND, String.class,  taskId);
                    int i = 0;
                    while (!success.equals(result)&&i<200){
                        result = restTemplate.getForObject(businessServer.getServeradd()+URL_APPEND, String.class,  taskId);
                        i++;
                    }

                    if (success.equals(result)){
                        saveTaskResult(taskId, taskResult, businessServer,EnumConstant.ResultStatus.RESULT_STATUS_3);
                    }else {
                        saveTaskResult(taskId, taskResult, businessServer,EnumConstant.ResultStatus.RESULT_STATUS_2);
                        logger.error("任务执行失败，原因：http返回结果[{}]",result);
                    }

                }
                // 保证成功(任务不在执行中)
                else if (task.getMathematics()==EnumConstant.Mathematics.MATHEMATICS_3.getType()){
                   // TODO 几乎不存在这样的情况，暂时不实现
                }

            }
        }else if (task.getNotify() == EnumConstant.Notify.NOTIFY_2.getType()){
            // mq 消息发送
            Map<String,Object> map = Maps.newHashMap();
            map.put("taskId",taskId);
            map.put("taskQueue",task.getDescription());
            jmsMessagingTemplate.convertAndSend(taskTimerSendQueue,map,map);
            // 保存
            saveTaskResult(taskId, taskResult, null,EnumConstant.ResultStatus.RESULT_STATUS_2);
            logger.info("mq消息-定时任务通知已发送");
        }
    }

    /**
     * 保存执行结果
     * @param taskId 任务ID
     * @param taskResult 任务参数集合
     * @param businessServer 运行设备
     * @param status 状态
     */
    private void saveTaskResult(int taskId, TaskResult taskResult, BusinessServer businessServer,EnumConstant.ResultStatus status) {
        getTaskResult(taskId, taskResult, businessServer);
        taskResult.setStatus(status.getType());
        saveResult(taskResult);
    }

    /**
     * 获取定时钟在HTTP下执行的SERVER
     * @param task
     * @return
     */
    private List<BusinessServer> getBusinessServers(Task task) {
        // 查询对应的通知执行SERVER[集群里多台服务器]
        BusinessServerExample example = new BusinessServerExample();
        example.createCriteria().andBusinessidEqualTo(task.getBusinessid());
        List<BusinessServer> list = businessServerMapper.selectByExample(example);

        List<BusinessServer> vector = new Vector<>();

        // 随机调度
        if (task.getDispatch()== EnumConstant.Dispatch.DISPATCH_1.getType()){
            BusinessServer businessServer = getRandomServer(list);
            vector.add(businessServer);
        }
        // 指定设备
        else if (task.getDispatch()== EnumConstant.Dispatch.DISPATCH_2.getType()){
            BusinessServer businessServer = null;
            for (BusinessServer businessServerTmp:list){
                if (businessServerTmp.getServerid()==task.getBusinessserverid()){
                    businessServer = businessServerTmp;
                    break;
                }
            }
            vector.add(businessServer);
        }
        // 全体执行
        else if (task.getDispatch()== EnumConstant.Dispatch.DISPATCH_3.getType()){
            Collections.copy(vector,list);
        }
        return vector;
    }

    /**
     * 组装运行结果
     * @param taskId 任务ID
     * @param taskResult 任务结果
     * @param businessServer 运行服务器
     */
    private void getTaskResult(int taskId, TaskResult taskResult, BusinessServer businessServer) {
        taskResult.setTaskid(taskId);
        taskResult.setLastruntime(new Date());
        if(null != businessServer){
            taskResult.setRunserverid(businessServer.getServerid());
        }

    }

    @Override
    public String callBack(int taskId) {
        try {
            TaskResult taskResult = new TaskResult();
            taskResult.setStatus(EnumConstant.ResultStatus.RESULT_STATUS_1.getType());
            taskResult.setLastendtime(new Date());

            TaskResultExample example = new TaskResultExample();
            example.createCriteria().andTaskidEqualTo(taskId);

            taskResultMapper.updateByExampleSelective(taskResult, example);
            return EnumConstant.CallBackResult.CALL_BACK_RESULT_SUCCESS.getName();
        }catch (Exception e){
            logger.error("回调失败:taskId:[{}]",taskId,e);
            return EnumConstant.CallBackResult.CALL_BACK_RESULT_FAILED.getName();
        }
    }


    @Override
    @JmsListener(destination = MqContstant.TASK_TIMER_RECEIVE_QUEUE)
    public void callBackMQ(int taskId) {
        TaskResult taskResult = new TaskResult();
        taskResult.setStatus(EnumConstant.ResultStatus.RESULT_STATUS_1.getType());
        taskResult.setLastendtime(new Date());

        TaskResultExample example = new TaskResultExample();
        example.createCriteria().andTaskidEqualTo(taskId);

        taskResultMapper.updateByExampleSelective(taskResult,example);
        logger.info("消息通知完成，更新数据库完成..taskId : [{}]",taskId);
    }

    private synchronized BusinessServer getRandomServer(List<BusinessServer> list){
        //获取100-200的随机数
        int i=100+(int)(Math.random()*100);
        int index = i%list.size();

        return list.get(index);
    }

    private void saveResult(TaskResult taskResult){
        TaskResultExample example = new TaskResultExample();
        example.createCriteria().andTaskidEqualTo(taskResult.getTaskid());

        List<TaskResult> list = taskResultMapper.selectByExample(example);

        if (list==null||list.size()==0){
            taskResultMapper.insert(taskResult);
        }else {
            taskResultMapper.updateByExampleSelective(taskResult,example);
        }
    }

}
