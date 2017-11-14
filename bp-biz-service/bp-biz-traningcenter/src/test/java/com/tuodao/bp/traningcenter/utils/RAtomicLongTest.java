package com.tuodao.bp.traningcenter.utils;

import com.tuodao.bp.traningcenter.TraningCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tookbra
 * @date 2017/11/8
 * @description
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RAtomicLongTest {

    @Autowired
    RedissonClient redissonClient;

    @Test
    public void getAtomicLong() {
        redissonClient.getAtomicLong("test333");
    }

    @Test
    public void getAndIncrement() {
        RAtomicLong num = redissonClient.getAtomicLong("test");
        num.getAndIncrement();
    }

    @Test
    public void delete() {
        RAtomicLong num = redissonClient.getAtomicLong("test");
        num.delete();
    }

    @Test
    public void threadTest() {
        ExecutorService executors = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++) {
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    RAtomicLong num = redissonClient.getAtomicLong("test1233");

                    System.out.println(Thread.currentThread().getName() + " " + num.getAndIncrement());

                }
            });
        }
    }
}
