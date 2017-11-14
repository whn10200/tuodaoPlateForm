package com.tuodao.bp.model.business.demo.input.demo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;

/**
 * demo input
 * 
 * @author hechun
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
public class DemoDbInput extends PagePojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = UaParamExceptionConstant.MOBILE_IS_NULL + "")
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("phone", phone).omitNullValues().toString();
	}
}
