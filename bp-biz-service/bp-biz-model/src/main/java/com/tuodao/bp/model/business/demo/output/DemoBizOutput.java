package com.tuodao.bp.model.business.demo.output;

import java.io.Serializable;

/**
 * demo biz output
 * @author hechuan
 *
 * @created 2017年8月31日
 *
 * @since 1.0.0
 */
public class DemoBizOutput implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

    private String userName;

    private String phone;

    private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	    
	    
}
