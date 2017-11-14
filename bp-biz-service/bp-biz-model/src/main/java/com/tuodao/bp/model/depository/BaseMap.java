package com.tuodao.bp.model.depository;

import java.io.Serializable;
import java.util.HashMap;

public abstract class BaseMap implements Serializable{

	public BaseMap() {
		
	}
	public BaseMap(HashMap<String, String> map) {
		this.map = map;
	}

	private static final long serialVersionUID = 8605173434964777433L;

	protected HashMap<String, String> map = new HashMap<String, String>();

	public HashMap<String, String> toMap() {
		return this.map;
	}
	
	public void fromMap(HashMap<String, String> map) {
		this.map = map;
	}

	protected void put(String key, String value) {
		this.map.put(key, value);
	}

	protected String get(String key) {
		return this.map.get(key);
	}

	public boolean isEmpty() {
		return map == null || map.size() == 0;
	}
	
	public boolean isNotEmpty() {
		return !isEmpty();
	}
	
}
