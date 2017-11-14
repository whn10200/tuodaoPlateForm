package com.tuodao.bp.useraccount.interceptor;

import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @description: 检查用户是否存在
 * @author: mif
 * @date: 2017/8/28
 * @time: 16:01
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Documented
@Target(METHOD)
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface UnableValidate {
}
