package com.tuodao.bp.model.annotation;


import com.tuodao.bp.model.annotation.def.TimeFormatDef;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: 时间校验
 * @author zkai
 * @date 2017年9月28日 下午8:40:00
 * Company：拓道金服
 *
 */
@Documented
@Constraint(validatedBy = { TimeFormatDef.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface TimeFormat {
	/** 报错信息 */
	String message() default "参数非法";

	/**
	 * 是否必须
	 */
	boolean required() default false;

	/** 时间格式类型 */
	String format() default "yyyy-MM-dd";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
