package com.tuodao.bp.model.annotation.def;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tuodao.bp.model.annotation.PhoneNum;

/**
 * @Description: 验证用户号码
 * @author Lwk
 * @date 2017年3月21日 下午8:37:34
 * Company：拓道金服
 *
 */
public class PhoneNumDef implements ConstraintValidator<PhoneNum, String> {

	@Override
	public void initialize(PhoneNum constraintAnnotation) {
	}

	@Override
	public boolean isValid(String num, ConstraintValidatorContext arg1) {
		// 如果为空则不验证
		if (num == null || "".equals(num)) {
			return true;
		}

		boolean flag = num
		        .matches("^(((1([3,4,5,7,8][0-9]))\\d{8}))$");

		return flag;
	}

}
