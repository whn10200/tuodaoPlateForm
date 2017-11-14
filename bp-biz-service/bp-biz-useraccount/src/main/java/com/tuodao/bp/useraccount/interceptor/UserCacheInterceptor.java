package com.tuodao.bp.useraccount.interceptor;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizUserAccountMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;

/**
 * @description: 用户账户信息缓存
 * @author: mif
 * @date: 2017/9/7
 * @time: 13:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Aspect
@Order(-1)
@Component
public class UserCacheInterceptor {

    private static Logger logger = LoggerFactory.getLogger(UserCacheInterceptor.class);

    @Resource
    private UserAccountCache userAccountCache;

    @Resource
    private BizUserAccountMapper bizUserAccountMapper;

    @After("execution(* com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoMapper.update*(..)) || execution(* com.tuodao.bp.useraccount.persistence.mapper.basic.AccountFinanceMapper.update*(..)) ")
    public void deleteCache(JoinPoint point) {
        Object[] args = point.getArgs();
        Object obj = args[0];
        String userId = (String) getFieldValue(obj, "userId");
        logger.info("delete user account cache ,userId = {}", userId);
        //先删除
        userAccountCache.deleteUserAccountInfo(userId);
        //后存入
        UserAccountInfo cache = bizUserAccountMapper.selectUserAccountInfo(userId);
        logger.debug("cache ={}", cache);
        userAccountCache.putCache(cache);
    }

    /**
     * 获取当前对象对应字段的属性（对象）
     * 声明，需要注意在NoSuchFieldException异常捕捉中捕获自己需要的属性字段进行拦截，告诉当查询这些属性名的时候，指定是查找的哪些对象，如果不告诉它，它是不知道的
     *
     * @param obj   当前对象
     * @param field 需要获取的属性名，可以是当前对象中的属性名， 也可以是当前对象中的对象的属性名
     * @return Object  当前对象指定属性值
     */
    private static Object getFieldValue(Object obj, String field) {
        Class<?> clazz = obj.getClass();
        Field f = null;
        Object fieldValue = null;
        try {
            f = clazz.getDeclaredField(field);
            f.setAccessible(true);
            fieldValue = f.get(obj);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
            logger.info("can not find userId");
            e.printStackTrace();
        }
        return fieldValue;
    }
}
