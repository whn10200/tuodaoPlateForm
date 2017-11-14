package com.tuodao.bp.model.depository;

import java.util.HashMap;

public interface DepositoryParaInterface {

	HashMap<String, String> toHashMap();

	void fromHashMap(HashMap<String, String> map);

	boolean isSuccess();
	
	String getRemark();

}