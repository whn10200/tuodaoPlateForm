package com.tuodao.bp.cache.basic.traningcenter;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.Date;


/**
 * 21位:{6位日期}+{6位时间}+{3位毫秒}+{6位随机}
 * @description:序列号生成伪生成器
 * @author: 王艳兵
 * @date: 2017年8月7日
 * @time: 上午9:44:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class GenerateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerateUtils.class);

	private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("yyMMddHHmmssSSS");

    /**
     * 默认生成唯一序列号的锁值
     */
    private static final String GENERATE_KEY = "generateKey";

    private static final int RANDOM_LENGTH = 6;
    /**
     * 利用redis生成唯一订单号
     * 现采用GenerateService生成,限交易中心使用
     * @return
     */
    @Deprecated
    public static String get(){
        LOGGER.info("begin to generate key:{}", System.currentTimeMillis());
        RedisLock lock = new RedisLock(GENERATE_KEY);
		try {
			if(lock.lock()){
				String dateStr = DATE_FORMAT.format(new Date());
				return new StringBuffer(dateStr).append(RandomStringUtils.randomNumeric(RANDOM_LENGTH)).toString();
			}
		}catch (Exception e){
		    e.printStackTrace();
        }finally{
            lock.unlock();
		}
		LOGGER.error("generate key error:",System.currentTimeMillis());
		throw new RuntimeException("parameter failed");
	}

    public static void main(String[] args) {
        System.out.println(RandomStringUtils.randomNumeric(6));
    }
}
