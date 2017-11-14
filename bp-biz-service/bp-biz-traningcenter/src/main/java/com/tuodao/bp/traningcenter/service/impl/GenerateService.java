package com.tuodao.bp.traningcenter.service.impl;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * 21位:{6位日期}+{6位时间}+{3位毫秒}+{6位随机}
 * @description:序列号生成伪生成器
 * @author: 王艳兵
 * @date: 2017年8月7日
 * @time: 上午9:44:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component("generateService")
public class GenerateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateService.class);

	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyMMddHHmmssSSS");

    /**
     * 默认生成唯一序列号的锁值
     */
    private static final String GENERATE_KEY = "generateKey";

    private static final int RANDOM_LENGTH = 6;

    /**
     * 线程等待时间 10s
     */
    private static final int WAIT_TIME = 10;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 利用redis生成唯一订单号
     * @return
     */
    public  String get(){
        LOGGER.info("begin to generate key:{}", System.currentTimeMillis());
        RLock fairLock = redissonClient.getFairLock(GENERATE_KEY);
		try {
			if(fairLock.tryLock(WAIT_TIME, TimeUnit.SECONDS)){
				String dateStr = DATE_FORMAT.format(new Date());
				return new StringBuffer(dateStr).append(RandomStringUtils.randomNumeric(RANDOM_LENGTH)).toString();
			}
		}catch (Exception e){
		    e.printStackTrace();
        }finally{
            fairLock.unlock();
		}
		LOGGER.error("generate key error:",System.currentTimeMillis());
		throw new RuntimeException("parameter failed");
	}


}
