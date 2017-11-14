package com.tuodao.bp.common.service.impl;

import com.github.rholder.retry.*;
import com.google.common.base.Splitter;
import com.tuodao.bp.common.constants.CommonConstant;
import com.tuodao.bp.common.persistence.model.basic.EmailLog;
import com.tuodao.bp.common.service.async.EmailLogService;
import com.tuodao.bp.common.service.IEmailService;
import com.tuodao.bp.model.business.common.input.EmailInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description: 邮件发送服务
 * @author: mif
 * @date: 2017/9/11
 * @time: 10:01
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class EmailServiceImpl implements IEmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private EmailLogService emailLogService;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * guava retry
     * 结果返回false  重试:固定等待时长为 300 ms,最多尝试 3 次
     */
    static Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfExceptionOfType(MailException.class)
            .retryIfRuntimeException()
//            .retryIfResult(Predicates.equalTo(false))
            .withWaitStrategy(WaitStrategies.fixedWait(300, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();


    @Override
    public void sendEmail(EmailInput input) {
        logger.info("start to send email,input={}", input);

        SimpleMailMessage message = new SimpleMailMessage();

        List<String> receivers = Splitter.on(CommonConstant.SPLIT_CHAR)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(input.getReceivers());

        receivers.forEach(receiver -> {

            String flag = "SUCCESS";

            message.setFrom(from);
            message.setSubject(input.getSubject());
            message.setTo(receiver);
            message.setText(input.getContent());
            Callable<Boolean> sendTask = () -> {
                javaMailSender.send(message);
                return true;
            };
            try {
                retryer.call(sendTask);
            } catch (ExecutionException | RetryException e) {
                flag = "FAILED";
                logger.error("重试三次，发送短信失败，receiver={}", receiver);
                e.printStackTrace();
            }
            // 记录发送日志
            emailLogService.insertEmailLog(new EmailLog(from, receiver, input.getSubject(), input.getContent(), flag));
        });

    }


}
