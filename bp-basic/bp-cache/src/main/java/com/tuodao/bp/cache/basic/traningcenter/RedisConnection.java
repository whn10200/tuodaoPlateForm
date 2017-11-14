package com.tuodao.bp.cache.basic.traningcenter;


import redis.clients.jedis.Jedis;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/8/8
 * @time: 14:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RedisConnection {

    public static Jedis getRedis(){
        Jedis jedis = new Jedis("72.127.2.158",6379);
        jedis.auth("tuodao!@#321");
        return jedis;
    }
}
