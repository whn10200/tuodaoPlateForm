package com.tuodao.bp.model.business.demo.output;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class DemoOuput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String outName;

	private String outAddress;

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getOutAddress() {
		return outAddress;
	}

	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("outName", outName).add("outAddress", outAddress).omitNullValues().toString();
	}
}
