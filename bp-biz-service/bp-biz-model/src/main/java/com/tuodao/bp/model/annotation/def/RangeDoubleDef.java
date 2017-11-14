package com.tuodao.bp.model.annotation.def;

import com.tuodao.bp.model.annotation.RangeDouble;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/3
 * @time: 13:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RangeDoubleDef implements ConstraintValidator<RangeDouble, Double> {

    private static final String REG = "^\\d+(\\.\\d{0,2})?$|^\\.\\d{1,2}$";

    private double max;

    private double min;

    @Override
    public void initialize(RangeDouble constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        if(value == null ){
            return false;
        }

        if (min <= value && value <= max) {
            return true;
        }
        return false;
    }
}
