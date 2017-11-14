package com.tuodao.bp.api.facade.controller.depository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapUtils {

	public static HashMap<String, String> RequestParameterMap2Map(Map<String, String[]> properties) {

		// 返回值Map
		HashMap<String, String> returnMap = new HashMap<String, String>();
		Iterator<Entry<String, String[]>> it = properties.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> en = it.next();
			returnMap.put(en.getKey(), en.getValue()[0]);
		}
		return returnMap;
	}

    /**
     * map转字符串 目前只用于打印银行返回结果信息
     * @param map
     * @return
     */
    public static String mapToString(Map<String,String> map){
	    if(map == null){
	        return null;
        }
        StringBuffer sb = new StringBuffer();
        for (String key :map.keySet()){
	        sb.append(key).append("=").append(map.get(key)).append(";");
        }
        return sb.toString();
    }

}
