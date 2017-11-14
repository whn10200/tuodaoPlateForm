package com.tuodao.bp.model.annotation;

import com.tuodao.bp.model.enums.AccessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Description: access 校验
 * User: zkai
 * Date: 2017/8/17
 * Time: 11:47
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessToken {

	/**
	 * 是否需要检查ACCESSID,KEY。。
	 * 动态ACCESSID,KEY的时候登录是不需要检查的
	 * @return true为检查
	 */
	boolean checkAccess() default true;

	/**
	 * 默认请求为PC，可为数组，即是：PC,APP均可
	 * @return
	 */
	AccessType[] access() default AccessType.PC;

}
