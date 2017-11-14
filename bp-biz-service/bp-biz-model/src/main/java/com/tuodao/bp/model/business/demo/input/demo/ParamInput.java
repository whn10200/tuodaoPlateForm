package com.tuodao.bp.model.business.demo.input.demo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.google.common.base.MoreObjects;

public class ParamInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank(message="key null not alowed")
	private String key;
	
	@NotBlank(message="value must not blank")
	private String value;
	
	@Range(min=3,max=12,message="3-12之间")
	private int age;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("key", key).add("value", value).add("age", age).toString();
	}
}
