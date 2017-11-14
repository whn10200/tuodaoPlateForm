package com.tuodao.bp.result.interceptor;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.model.business.common.input.EmailInput;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Queue;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @description: 对MQ异常进行处理
 * @author: zkai
 * @date: 2017/9/15
 * @time: 09:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Aspect
@Component
public class MqInterceptor {

    static final Logger logger = LoggerFactory.getLogger(MqInterceptor.class);

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "taskTimerReceiveQueue")
    private Queue taskTimerReceiveQueue;

    @Autowired
    RestTemplate restTemplate;

    /**
     * LoadBalanced 注解表明restTemplate使用LoadBalancerClient执行请求
     *
     * @return
     */
    @Bean
    @LoadBalanced
    @Primary
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        RestTemplate template = new RestTemplate(factory);
        return template;
    }

    /**
     * 拦截定时任务的回调
     * @param point
     */
    @After(value = "@annotation(org.springframework.jms.annotation.JmsListener)")
    public void doAfter(JoinPoint point){
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        JmsListener listener = method.getAnnotation(JmsListener.class);
        Object param = point.getArgs()[0];
        if(param instanceof Map && StringUtils.contains(listener.selector(),"taskQueue")){
            Map<String,Object> map = (Map<String,Object>)param;

            Object taskId = map.get("taskId");
            Object taskQueue = map.get("taskQueue");

            logger.info("消息定时消费结束,开始回调，taskId : [{}],taskQueue：[{}],selector:[{}]",taskId,taskQueue,listener.selector());

            jmsMessagingTemplate.convertAndSend(taskTimerReceiveQueue,taskId);

            logger.info("消息定时消费结束,结束回调，taskId : [{}],taskQueue：[{}],selector:[{}]",taskId,taskQueue,listener.selector());
        }
    }

    /**
     * 注解的方法在执行完成后失败的进行处理
     * @param point
     * @param e
     */
    @AfterThrowing(value = "@annotation(org.springframework.jms.annotation.JmsListener)",throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e){
        logger.warn("同步数据异常,e={}",e);
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        JmsListener listener = method.getAnnotation(JmsListener.class);
        Object[] args = point.getArgs();
        ActiveMQMessage activeMQMessage = (ActiveMQMessage) args[0];
        String messageObject = null;

        logger.info("消息唯一标识 messageId={}",activeMQMessage.getMessageId());
        int errorTimes = activeMQMessage.getRedeliveryCounter();
        logger.info("重发次数={}",errorTimes);

        if(errorTimes >= 5){
            try {
                if(args[0] instanceof ActiveMQObjectMessage){
                    ActiveMQObjectMessage message = (ActiveMQObjectMessage)args[0];
                    try {
                        messageObject = JSON.toJSONString(message.getObject());
                    } catch (JMSException e1) {
                        logger.error("获取ActiveMQObjectMessage 中的object异常e={}",e1);
                    }
                }else if(args[0] instanceof ActiveMQMapMessage) {
                    ActiveMQMapMessage message = (ActiveMQMapMessage)args[0];
                    try {
                        Map<String, Object> map = message.getContentMap();
                        messageObject = map.toString();
                    } catch (JMSException e1) {
                        logger.error("获取ActiveMQMapMessage 中的map异常e={}",e1);
                    }
                }

                // 发送邮件,清理redis
                EmailInput emailInput = new EmailInput();

                StringBuffer contnet = new StringBuffer();
                contnet.append("同步消息队列名=" + activeMQMessage.getDestination().getPhysicalName());
                contnet.append("\r\n\n");
                contnet.append("同步消息明细=" + activeMQMessage.toString());
                contnet.append("\r\n\n");
                contnet.append("selector=" + listener.selector());
                contnet.append("\r\n\n");
                contnet.append("同步对象message=" + messageObject);

                contnet.append("\r\n\n");
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                contnet.append("错误异常信息=" + sw.toString());

                emailInput.setContent(String.valueOf(contnet));
                emailInput.setSubject("消息同步异常");
                emailInput.setReceivers("zhoukai@51tuodao.com");
                sendEmail(emailInput);
            }catch (Exception ex){
                logger.error("邮件发送异常ex={}",ex);
            }
        }
    }

    /**
     * 发送邮件
     * @param emailInput
     */
    @Async
    private void sendEmail(EmailInput emailInput){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(emailInput), headers);

        ResponseEntity<String> entity = restTemplate.postForEntity("http://BP-COMMON-MS/common/sendEmail",
                requestEntity, String.class);
        if(entity.getStatusCodeValue() != 200){
            logger.error("邮件发送异常,发送内容inpu={}",JSON.toJSONString(emailInput));
        }
    }
}
