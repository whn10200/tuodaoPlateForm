package com.tuodao.bp.model.annotation.def;


import com.tuodao.bp.model.annotation.TimeFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description: 检查时间格式是否准确。
 * @author zkai
 * @date 2017年9月28日 下午8:36:52
 * Company：拓道金服
 *
 */
public class TimeFormatDef implements ConstraintValidator<TimeFormat, Object> {
    // 待检查的值范围
    private String format;
    private boolean required;

    @Override
    public void initialize(TimeFormat constraintAnnotation) {
        this.format = constraintAnnotation.format();
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object values, ConstraintValidatorContext context) {
        String value = "test";
        if (null == values) {
            value = null;
        }else {
            value = (String) values;
        }
        if ((value == null || value.trim().equals("")) && this.required) {
            return false;
        } else if ((value == null || value.trim().equals("")) && !this.required) {
            return true;
        }
        DateFormat formatter = new SimpleDateFormat(this.format);
        try{
            Date date = (Date)formatter.parse(value);
            return value.equals(formatter.format(date));
        }catch(Exception e){
            return false;
        }
    }
}
