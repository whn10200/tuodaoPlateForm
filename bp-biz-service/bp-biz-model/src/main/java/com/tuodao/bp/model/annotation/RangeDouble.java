package com.tuodao.bp.model.annotation;



import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import com.tuodao.bp.model.annotation.def.RangeDoubleDef;

/**
 * @Description: 投标金额范围限制
 * @author 王艳兵
 * @date 2017年11月1日 下午8:40:35
 * Company：拓道金服
 *
 */
@Documented
@Constraint(validatedBy = { RangeDoubleDef.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface RangeDouble {
    /** 最小值 */
    double min() default 0D;
    /** 最大值 */
    double max() default Double.MAX_VALUE;
    /** 报错信息 */
    String message() default "参数非法";
    /** 字段名 */
    String label() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@code @Range} annotations on the same element.
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Range[] value();
    }
}
