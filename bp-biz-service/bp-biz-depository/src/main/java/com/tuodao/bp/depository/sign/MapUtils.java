package com.tuodao.bp.depository.sign;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

public class MapUtils {

	/**
	 * 将Map中的数据转换成按照Key的ascii码排序后的key1=value1&key2=value2的形式，用于生成。</br> 不得包含多余字段
	 * 
	 * @param tree
	 *            待拼接的Map数据 </br>
	 *            注意，此处必须使用TreeMap，以保证遍历出的顺序相同。从而保证合成字符串的字段顺序相同，加密结果相同。
	 * @return 拼接好后的字符串
	 */
	/*public static String andCombination(TreeMap<String, String> tree) {
		Iterator<Entry<String, String>> it = tree.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			sf.append("&" + en.getKey() + "=" + en.getValue().trim());
		}
		return sf.substring(1);
	}*/
	
	public static String andCombination(Map<String, String> map) {
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			if(en.getValue()!=null&&!en.getValue().equals(""))
			{
				sf.append("&" + en.getKey().trim() + "=" + en.getValue().trim());
			}
		}
		return sf.substring(1);
	}
	
	public static String andCombination(Map<String, String> map,List<String> fieldOrderlist) {
		if(map==null||map.size()==0||fieldOrderlist==null||fieldOrderlist.size()==0)
		{
			return null;
		}
		
		StringBuffer sf = new StringBuffer();
		for(String fieldName:fieldOrderlist){
			String value = map.get(fieldName);
			if (value != null && !value.equals("")){
				sf.append("&" + fieldName.trim() + "=" +value.trim());
			}
		}		
		
		return sf.substring(1);
	}


	public static String verticalCombination(Map<String, String> map) {
		if(map==null||map.size()==0)
		{
			return null;
		}
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			if (en.getValue() != null ) {//&& !en.getValue().equals("")
				sf.append("|" );
				sf.append(en.getValue().trim());
			}
		}
		return sf.substring(1);
	}
	
	public static String verticalCombination(Map<String, String> map,List<String> fieldOrderlist) {
		if(map==null||map.size()==0||fieldOrderlist==null||fieldOrderlist.size()==0)
		{
			return null;
		}
		
		StringBuffer sf = new StringBuffer();
		for(String fieldName:fieldOrderlist){
			String value = map.get(fieldName);
			if (value != null && !value.equals("")){
				sf.append("|" );
				sf.append(value.trim());
			}
		}		
		
		return sf.substring(1);
	}
	
	public static String verticalCombination(Map<String, String> map,String prefix,String suffix) {
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			if (en.getValue() != null && !en.getValue().equals("")) {
				sf.append("|" );
				sf.append(prefix);
				sf.append(en.getValue().trim());
				sf.append(suffix);
			}
		}
		return sf.substring(1);
	}
	
	
	public static String verticalCombination(List<String> list) {
		
		StringBuffer sf = new StringBuffer();
		for(String st:list) {
			sf.append("|"+ st.trim());
		}
		return sf.substring(1);
	}

	/**
	 * 将Map中的数据转换成按照Key的ascii码排序后的key1=value1&key2=value2的形式，用于生成。</br> 不得包含多余字段
	 * 
	 * @param tree
	 *            待拼接的Map数据 </br>
	 *            注意，此处必须使用TreeMap，以保证遍历出的顺序相同。从而保证合成字符串的字段顺序相同，加密结果相同。
	 * @return 拼接好后的字符串
	 */
	public static String jsonCombination1(TreeMap<String, String> tree) {
		Iterator<Entry<String, String>> it = tree.entrySet().iterator();
		StringBuffer sf = new StringBuffer();
		while (it.hasNext()) {
			Entry<String, String> en = it.next();
			sf.append(",\"" + en.getKey() + "\":\"" + en.getValue() + "\"");
		}
		return "{" + sf.substring(1) + "}";
	}
	
	public static String map2jsonBean(Map<String, String> map,List<String> fieldOrderlist) {
		if(map==null){
			return null;
		}
		StringBuilder sf = new StringBuilder();
		for(String field:fieldOrderlist){
			String value = map.get(field);
			if(value!=null&&value.length()>0)
			{
				sf.append(",\"" + field + "\":\"" + value.trim() + "\"");
			}
		}
		return "{" + sf.substring(1) + "}";
	}
	
	public static String map2jsonList(List<String> list) {
		StringBuilder sf = new StringBuilder();
		for(String value:list){
			if(value!=null&&value.length()>0)
			{
				sf.append(","+ value);
			}
		}
		return "[" + sf.substring(1) + "]";
	}

	
	
	/**
	 * 获取请求参数中所有的信息
	 * 
	 * @param request
	 * @return
	 */
	public static HashMap<String, String> getAllRequestParam(final HttpServletRequest request) {
		HashMap<String, String> res = new HashMap<String, String>();
		Enumeration<?> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = (String) temp.nextElement();
				String value = request.getParameter(en);
				res.put(en, value);
				//在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
				//System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
				if (null == res.get(en) || "".equals(res.get(en))) {
					res.remove(en);
				}
			}
		}
		return res;
	}
	
	public static LinkedHashMap<String, String> map2link(Map<String, String> map,List<String> orderKeys){
		LinkedHashMap<String, String> link = new LinkedHashMap<String, String>();
		
		if(orderKeys!=null&&orderKeys.size()>0){
			for(String key:orderKeys){
				String value = map.get(key);
				if(value!=null&&!value.equals(""))
				{
					link.put(key, map.get(key));
				}
			}
		}
		
		return link;
	}
	
	
	public static HashMap<String,String> RequestParameterMap2Map(Map<String,String[]> properties) {
		
		// 返回值Map
		HashMap<String,String> returnMap = new HashMap<String,String>();
		Iterator<Entry<String, String[]>> it = properties.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String[]> en = it.next();
			returnMap.put(en.getKey(), en.getValue()[0]);
		}
		return returnMap;
	}
		
		
		
	
}
