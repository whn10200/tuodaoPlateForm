package com.tuodao.bp.depository.service;

import java.util.HashMap;
import java.util.List;

public interface BaseService {

	HashMap<String, String> requestAndInsertDbCommon(String requeryOrderNo, HashMap<String, String> map,
			String requestUrl, List<String> key);
	
	HashMap<String, String> notify(HashMap<String, String> map, List<String> key);
}
