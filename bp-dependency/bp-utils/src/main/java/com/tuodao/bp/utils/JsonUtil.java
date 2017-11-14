package com.tuodao.bp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	public static String list2json(List<Map<String, String>> list) {
		StringBuffer data = new StringBuffer();
		StringBuffer result = new StringBuffer();
		for (Map<String, String> map : list) {
			data.append(",");
			data.append(map2json(map));
		}
		result.append("[");
		result.append(data.substring(1));
		result.append("]");
		return result.toString();
	}

	public static String list2JsonWithoutNull(ArrayList<HashMap<String, String>> list, ArrayList<String> fieldOrder) {
		return list2JsonWithoutNull(list, "", fieldOrder);
	}

	public static String list2JsonWithoutNull(ArrayList<HashMap<String, String>> list, String addFielName,
			ArrayList<String> fieldOrder) {
		StringBuffer data = new StringBuffer();
		StringBuffer result = new StringBuffer();
		for (HashMap<String, String> map : list) {
			data.append(",");
			data.append(map2JsonWithoutNull(map, addFielName, fieldOrder));
		}
		result.append("[");
		result.append(data.substring(1));
		result.append("]");
		return result.toString();
	}
	
	
	

	public static String map2json(Map<String, String> map) {
		StringBuffer data = new StringBuffer();
		StringBuffer result = new StringBuffer();

		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue() != null) {
				data.append(",\"");
				data.append(entry.getKey());
				data.append("\":\"");
				data.append(entry.getValue());
				data.append("\"");
			}
		}

		result.append("{");
		result.append(data.substring(1));
		result.append("}");

		return result.toString();

	}

	public static String map2JsonWithoutNull(HashMap<String, String> map, ArrayList<String> fieldOrder) {
		return map2JsonWithoutNull(map, "", fieldOrder);
	}

	public static String map2JsonWithoutNull(Map<String, String> map, String addFieldName, List<String> fieldOrder) {
		StringBuffer data = new StringBuffer();
		StringBuffer result = new StringBuffer();

		for (String s : fieldOrder) {
			if (map.get(addFieldName + s) != null && map.get(addFieldName + s).length() > 0) {
				data.append(",\"");
				data.append(s);
				data.append("\":\"");
				data.append(map.get(addFieldName + s));
				data.append("\"");
			}
		}
		if (data.length() > 0) {
			result.append("{");
			result.append(data.substring(1));
			result.append("}");
			return result.toString();
		} else {
			return null;
		}

	}

	public static String map2jsonContainNull(Map<String, String> map, String[] fieldOrder) {
		StringBuffer data = new StringBuffer();
		StringBuffer result = new StringBuffer();

		for (String s : fieldOrder) {
			data.append(",\"");
			data.append(s);
			data.append("\":\"");
			data.append(map.get(s));
			data.append("\"");
		}
		result.append("{");
		result.append(data.substring(1));
		result.append("}");

		return result.toString();

	}

	/*
	 * public static Map<String, String> json2Map(String json) { json =
	 * json.substring(2, json.length() - 2); String fields[] =
	 * json.split("\",\"");
	 * 
	 * Map<String, String> map = new HashMap<String, String>(); for (String f :
	 * fields) { String pair[] = f.split("\":\""); map.put(pair[0], pair[1]); }
	 * System.out.println(map); return map; }
	 */

	public static HashMap<String, String> json2Map2(String json) {
		HashMap<String, String> map = new HashMap<String, String>();
		json = json.substring(1, json.length() - 1);
		json = "," + json + ",";

		int start = 0;
		int end = 0;

		char ca[] = json.toCharArray();

		int kv = 0;// 0是初始，1 是key，2是value

		String key = null;
		String value = null;

		int i = 0;

		String ss = "";

		String sp = "";

		while (i < json.length() - 1) {

			char c = ca[i];
			if (!(c == ',' || c == '\"' || c == ':')) {
				i++;
				continue;
			}

			char cc = ca[i + 1];

			if ((c == ',' && cc == '\"')) {
				sp = "\":";
				kv = 1;
				i = i + 2;
			}
			if ((c == ',' && Character.isDigit(cc))) {
				sp = ":";
				kv = 1;
				i = i + 1;
			}

			if ((c == ':' && cc == '[' && ca[i + 2] == '{')) {
				sp = "}],";
				kv = 2;
				i = i + 1;
			}
			if (c == ':' && cc == '{') {
				sp = "},";
				kv = 2;
				i = i + 1;
			}

			if ((c == ':' && cc == '\"')) {
				sp = "\",";
				kv = 2;
				i = i + 2;
			}
			if ((c == ':' && Character.isDigit(cc))) {
				sp = ",";
				kv = 2;
				i = i + 1;
			}

			end = json.indexOf(sp, i);

			if ((c == ':' && cc == '[' && ca[i + 1] == '{')) {
				end = end + 2;
			}

			if (c == ':' && cc == '{') {
				end = end + 1;
			}

			ss = json.substring(i, end);
			// ss = tirmQ(ss);

			if (kv == 1) {
				key = ss;
				i = end;
			}
			if (kv == 2) {
				value = ss;
				map.put(key, value);
				i = end;
				key = null;
				value = null;
			}

			if (ca[i] == '\"') {
				i++;
			}
			sp = "";
		}

		return map;

	}

	public static HashMap<String, String> json2Map(String json) {
		HashMap<String, String> map = new HashMap<String, String>();
		json = json.substring(1, json.length() - 1);
		json = "," + json + ",";

		int start = 0;
		int end = 0;

		char ca[] = json.toCharArray();

		int kv = 1;// 0是初始，1 是key，2是value
		int bracket = 0;
		int colon = 0;
		int type = 0;// 0是初始，1 是冒号colon，2是括号bracket

		String key = null;
		String value = null;

		String ss = "";

		String sp = "";

		for (int i = 0; i < ca.length; i++) {
			char c = ca[i];
			if ("\"{}[]1234567890.,".indexOf(c) > -1) {

				if (type == 0 && c == '"') {
					type = 1;
					start = i;
					continue;
				}
				if (type == 0 && (c == '{' || c == '[') && bracket == 0) {
					type = 2;
					start = i;
					bracket++;
					continue;
				}
				if (type == 0 && "1234567890".indexOf(c) > -1) {
					type = 3;
					start = i;
					continue;
				}

				if (type == 2 && (c == '{' || c == '[')) {
					bracket++;
				}

				if (type == 1 && c == '"' && key == null) {
					key = json.substring(start + 1, i);
					type = 0;
				}
				if (type == 1 && c == '"' && key != null) {
					value = json.substring(start + 1, i);
					map.put(key, value);
					key = null;
					value = null;
					type = 0;
				}

				if (type == 2 && (c == '}' || c == ']')) {
					bracket--;
				}

				if (type == 2 && bracket == 0) {
					value = json.substring(start, i + 1);
					map.put(key, value);
					key = null;
					value = null;
					type = 0;
				}
				if (type == 3 && c == ',') {
					value = json.substring(start, i);
					map.put(key, value);
					key = null;
					value = null;
					type = 0;
				}
			}
		}

		return map;

	}

	public static String tirmQ(String st) {
		if (st.charAt(0) == '\"' && st.charAt(st.length()) == '\"' && st.length() > 2) {
			return st.substring(1, st.length() - 1);
		}
		return st;
	}

	public static ArrayList<HashMap<String, String>> json2List(String json) {
		json = json.replace(" ", "");
		json = json.substring(2, json.length() - 2);

		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		String elements[] = json.split("\\},\\{");

		for (String e : elements) {
			e = "{" + e + "}";
			list.add(json2Map(e));
		}
		return list;

	}

	public static void main(String[] args) throws Exception {
		String a = "{\"error_data\":[{\"error_no\":\"31111\",\"detail_no\":\"td_r4e1488271760721_s1\""
				+ ",\"error_info\":\"账户验证失败：error_no[027091],error_info[证件号码格式错误]\",\"mobile\":\"13616810257\"}]"
				+ ",\"finish_datetime\":\"2017-02-28 16:14:50\",\"order_info\":\"批量注册处理完成\",\"order_status\":\"2\""
				+ ",\"recode\":\"10000\",\"remsg\":\"成功\""
				+ ",\"signdata\":\"pBt4CsTTZ7hKioJ1%2BZJityBo%2FKSDkPFBvuuHuYTAHIOaPMcllK0qspc17lwRcNf3WbA5JkC6v"
				+ "JSVUu%2B9%2BcC9MHAhrpGT%2B8OpwqBDtEj4cMCcoksHe8OfaNr9dcImj1EtSuK%2BbzA9DaHEMBJVMQaHfx2OYxqbDX"
				+ "5aQV7ftc8p3fI%3D\",\"success_num\":0,\"total_num\":1}";

		String a2 = "{\"error_no\":\"31111\",\"detail_no\":\"td_r4e1488271760721_s1\""
				+ ",\"error_info\":\"账户验证失败：error_no[027091],error_info[证件号码格式错误]\",\"mobile\":\"13616810257\"}";

		String a3 = "{\"data\":{\"prod_id\":\"td_1488617734492\"},\"recode\":\"10000\"," + "\"remsg\":\"成功\","
				+ "\"signdata\":\"Gh5T/5aVYCgg8nIhqmtEpdVMMFBZX2qFS8y7OvAD1giKqEtB8Vvq+46"
				+ "WeAzk0NgM52wL4/NiHPfsxEpiqQh52xlZHIQ2FqCf2Ihen7ZQo4Q0oOZj1JdMYhMlV/DsM"
				+ "t82tY0X3R7kY8MOMalGEypG6WcowLNMLOjcW9Of9Ag/MOU=\"}";
		String a4 = "{\"amt_type\":\"解冻\",\"amt\":132.18,\"trans_time\":\"20:42:20\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"}";

		String json5 = "{\"data\":{\"fundList\":[{\"amt_type\":\"冻结\",\"amt\":132.18,\"trans_time\":\"20:39:54\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"},{\"amt_type\":\"解冻\",\"amt\":132.18,\"trans_time\":\"20:42:20\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"}]},\"recode\":\"10000\",\"remsg\":\"成功\"}";

		String li = "[{\"amt_type\":\"冻结\",\"amt\":132.18,\"trans_time\":\"20:39:54\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"},{\"amt_type\":\"解冻\",\"amt\":132.18,\"trans_time\":\"20:42:20\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"}]";

		// System.out.println(json2Map(a4));
		System.out.println(json2List(li));
	}

}
