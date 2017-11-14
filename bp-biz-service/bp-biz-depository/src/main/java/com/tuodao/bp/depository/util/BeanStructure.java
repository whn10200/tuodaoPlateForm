package com.tuodao.bp.depository.util;

import java.util.List;


/**结构对象。处理输入反着放，处理输出正着放*/

public class BeanStructure {
	public BeanStructure() {

	}

	public BeanStructure(String superkey, String linkKey, List<String> underkeys) {
		super();
		this.superkey = superkey;
		this.linkKey = linkKey;
		this.underkeys = underkeys;
	}

	public BeanStructure(String superkey, String linkKey) {
		this(superkey, linkKey, null);
	}

	/** 上级key */
	private String superkey;

	private String linkKey;

	/** 下级key */
	private List<String> underkeys;

	public String getSuperkey() {
		return superkey;
	}

	public void setSuperkey(String superkey) {
		this.superkey = superkey;
	}

	public List<String> getUnderkeys() {
		return underkeys;
	}

	public void setUnderkeys(List<String> underkeys) {
		this.underkeys = underkeys;
	}

	public String getLinkKey() {
		return linkKey;
	}

	public void setLinkKey(String linkKey) {
		this.linkKey = linkKey;
	}

}
