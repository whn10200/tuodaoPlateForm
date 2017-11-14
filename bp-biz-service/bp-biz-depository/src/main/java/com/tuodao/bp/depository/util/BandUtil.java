package com.tuodao.bp.depository.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.BBDD;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.CFN;
import com.tuodao.bp.model.constant.depository.ONP;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.depository.key.BiddingKey;
import com.tuodao.bp.model.constant.depository.key.UserKey;
import com.tuodao.bp.utils.JsonUtil;

public class BandUtil {

	public static String getNo(String head) {
		return head + "_" + new Long(new Date().getTime()).toString();
	}

	public static String getOneDetailNo(String orderNo) {
		return orderNo + "_s1";
	}

	/** 获取userId中的手机号，不是mobile字段！！！如果没有连接符-则返回全部 */
	public static String getUserIdMobile(String userId) {
		return userId.contains("-") ? userId.substring(0, userId.indexOf("-")) : userId;
	}

	public static Map<String, String> setCommonPara(Map<String, String> map) {
		return setCommonPara(map, new Date());
	}

	public static Map<String, String> setCommonPara(Map<String, String> map, Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		map.put(BJFN.partnerTransDate, df.format(date));
		df.applyPattern("HHmmss");
		map.put(BJFN.partnerTransTime, df.format(date));
		map.put(BJFN.platNo, BBC.PLAT_NO);
		return map;
	}

	public static Map<String, String> proccessResponseSubbean(Map<String, String> map, String bjField,
			String bjSubfield, String tdField) {
		if (map.get(bjField) == null || map.get(bjField).length() == 0) {
			map.put(tdField, null);
		} else {
			ArrayList<HashMap<String, String>> rList = JsonUtil.json2List(map.get(bjField));

			StringBuilder bs = new StringBuilder();
			for (HashMap<String, String> sMap : rList) {
				bs.append(",");
				bs.append(sMap.get(bjSubfield));
			}
			map.put(tdField, bs.substring(1));
		}

		return map;
	}

	public static String getResponseSingleSubbean(Map<String, String> map, String beanFn, String subbeanFn) {
		Map<String, String> re = getResponseSingleSubbeanToMap(map, beanFn);
		if (re != null && re.size() > 0) {
			if (re.get(subbeanFn) != null && re.get(subbeanFn).length() > 0) {
				return re.get(subbeanFn);
			}
		}
		return null;
	}

	public static Map<String, String> getResponseSingleSubbeanToMap(Map<String, String> map, String beanFn) {
		if (map.get(beanFn) == null || map.get(beanFn).length() == 0) {
			return null;
		} else {
			return JsonUtil.json2List(map.get(beanFn)).get(0);
		}
	}

	public static Map<String, String> isSuccessSingle(Map<String, String> bj, Map<String, String> td) {
		boolean flag = false;

		String re = bj.get(BJFN.recode);
		if (!StringUtils.isEmpty(re) && re.equals(BBDD.success10000)) {
			flag = true;
		}
		if (flag) {
			td.put(TDFN.success, "true");
		} else {
			td.put(TDFN.success, "false");
		}
		return td;
	}

	public static Map<String, String> isSuccessBatch(Map<String, String> bj, Map<String, String> td) {
		boolean flag = false;

		String re = bj.get(BJFN.recode);

		if (!StringUtils.isEmpty(re) && re.equals(BBDD.success10000)) {

			String total = bj.get(BJFN.totalNum);

			if (StringUtils.isEmpty(total)) {
				flag = true;
			} else {
				if (total.equals(bj.get(BJFN.successNum))) {
					flag = true;
				}
			}
		}

		if (flag) {
			td.put(TDFN.success, "true");
		} else {
			td.put(TDFN.success, "false");
		}
		return td;
	}

	public static Map<String, String> checkSuccess(Map<String, String> bj) {
		boolean flag = false;

		String re = bj.get(BJFN.recode);

		if (!StringUtils.isEmpty(re) && re.equals(BBDD.success10000)) {

			String total = bj.get(BJFN.totalNum);

			if (StringUtils.isEmpty(total)) {
				flag = true;
			} else {
				if (total.equals(bj.get(BJFN.successNum))) {
					flag = true;
				}
			}

			String orderStatus = bj.get(BJFN.totalNum);
		}

		if (flag) {
			bj.put(TDFN.success, "true");
		} else {
			bj.put(TDFN.success, "false");
		}
		return bj;
	}

	public static HashMap<String, String> clearField(Map<String, String> bj, List<String> keys) {
		HashMap<String, String> re = new HashMap<String, String>(keys.size());

		for (String key : keys) {
			if (bj.get(key) != null && !bj.get(key).isEmpty()) {
				re.put(key, bj.get(key));
			}
		}
		return re;
	}

	public static HashMap<String, String> structure(HashMap<String, String> bj, List<BeanStructure> bss) {

		for (BeanStructure bs : bss) {

			// structureCircle(bj, bs.getSuperkey(), bs.getUnderkeys(),
			// bs.getLinkKey());

			/*
			 * if (bs.getIsList()) { List<Map<String, String>> sublist = new
			 * ArrayList<Map<String, String>>(); for (int i = 0; i <
			 * bs.getLength(); i++) { HashMap<String, String> submap = new
			 * HashMap<String, String>(); for (String underkey :
			 * bs.getUnderkeys()) { String underValue = bj.get(bs.getSuperkey()
			 * + CFN.s + i + CFN.e + underkey);
			 * if(underValue!=null&&underValue.length()>0){ submap.put(underkey,
			 * underValue); } } if (submap.size() > 0) { sublist.add(submap); }
			 * } if (sublist.size() > 0) { bj.put(bs.getSuperkey(),
			 * JsonUtil.list2json(sublist)); } } else { HashMap<String, String>
			 * submap = new HashMap<String, String>(); for (String underkey :
			 * bs.getUnderkeys()) { String underValue = bj.get(bs.getSuperkey()
			 * + CFN.se + underkey);
			 * if(underValue!=null&&underValue.length()>0){ submap.put(underkey,
			 * underValue); }
			 * 
			 * } if (submap.size() > 0) { bj.put(bs.getSuperkey(),
			 * JsonUtil.map2json(submap)); } }
			 */

		}

		return bj;
	}

	/*
	 * public static void structureCircle(HashMap<String, String> bj, String
	 * superkey, List<String> underkeys, String linkKey) { structureCircle(bj,
	 * superkey, underkeys, linkKey, 10); }
	 * 
	 *//** maxSubbeanNum list元素的数量 *//*
									 * public static String
									 * structureCircle(HashMap<String, String>
									 * bj, String superkey, ArrayList<String>
									 * underkeys, String linkKey, int
									 * maxSubbeanNum) {
									 * 
									 * if (superkey.indexOf(CFN.elelx) > -1) {
									 * //
									 * 虽然结果为null就跳出，但是防止死循环使用for，list元素数量限制为10
									 * for (int i = 0; i < maxSubbeanNum; i++) {
									 * String newSuperkey =
									 * superkey.substring(0,
									 * superkey.indexOf(CFN._elelx_)) +
									 * CFN._eleln_(i) + superkey
									 * .substring(superkey.lastIndexOf(CFN.
									 * _elelx_) + CFN._elelx_.length(),
									 * superkey.length()); String json =
									 * structureCircle(bj, newSuperkey,
									 * underkeys, linkKey, maxSubbeanNum); //
									 * 这是按顺序遍历的，所以如果没有1，就自然没有2，就跳出了 if (json ==
									 * null || json.isEmpty()) { break; } } }
									 * else { if (linkKey.equals(CFN._elelx_)) {
									 * ArrayList<HashMap<String, String>> list =
									 * new ArrayList<HashMap<String, String>>();
									 * for (int i = 0; i < maxSubbeanNum; i++) {
									 * HashMap<String, String> map = new
									 * HashMap<String, String>(); for (String
									 * underkey : underkeys) { String key =
									 * superkey + CFN._eleln_(i) + underkey;
									 * String value = bj.get(key); if (value !=
									 * null && !value.isEmpty()) {
									 * map.put(underkey, value); } } if
									 * (!map.isEmpty()) { list.add(map); } }
									 * 
									 * if (!list.isEmpty()) { String json =
									 * JsonUtil.list2WithoutNull(list,
									 * underkeys); if (json != null &&
									 * !json.isEmpty()) { bj.put(superkey,
									 * json); return json; } } } else if
									 * (linkKey.equals(CFN._swb_)) {
									 * HashMap<String, String> map = new
									 * HashMap<String, String>(); for (String
									 * underkey : underkeys) { String key =
									 * superkey + CFN._swb_ + underkey; String
									 * value = bj.get(key); if (value != null &&
									 * !value.isEmpty()) { map.put(underkey,
									 * value); } } if (!map.isEmpty()) { String
									 * json = JsonUtil.map2JsonWithoutNull(map,
									 * underkeys); if (json != null &&
									 * !json.isEmpty()) { bj.put(superkey,
									 * json); return json; } } }
									 * 
									 * }
									 * 
									 * return null; }
									 */

	public static void main0(String[] args) {
		String superkey = BJFN.data + CFN._sexs_ + BJFN.commission + CFN._swb_;
		System.out.println(superkey);
		System.out.println(superkey.substring(0, superkey.indexOf(CFN._elelx_)));
		System.out.println(
				superkey.substring(superkey.lastIndexOf(CFN._elelx_) + CFN._elelx_.length(), superkey.length()));

		String newSuperkey = superkey.substring(0, superkey.indexOf(CFN._elelx_)) + CFN._eleln_(0)
				+ superkey.substring(superkey.lastIndexOf(CFN._elelx_) + CFN._elelx_.length(), superkey.length());

		System.out.println(newSuperkey);
	}

	public static HashMap<String, String> analyze(HashMap<String, String> bj, List<BeanStructure> bss) {

		for (BeanStructure bs : bss) {
			String subjson = bj.get(bs.getSuperkey());
			if (subjson != null) {
				if (bs.getLinkKey().equals(CFN._elelx_)) {
					ArrayList<HashMap<String, String>> list = JsonUtil.json2List(subjson);
					for (int i = 0; i < list.size(); i++) {
						for (HashMap.Entry<String, String> en : list.get(i).entrySet()) {
							bj.put(bs.getSuperkey() + CFN._eleln_(i) + en.getKey(), en.getValue());
						}
					}
				} else if (bs.getLinkKey().equals(CFN._swb_)) {
					HashMap<String, String> map = JsonUtil.json2Map(subjson);
					for (HashMap.Entry<String, String> en : map.entrySet()) {
						bj.put(bs.getSuperkey() + CFN._swb_ + en.getKey(), en.getValue());
					}
				}
			}
		}

		return bj;
	}

	public static void main2(String[] args) {
		String json1 = "{\"finish_datetime\":\"2017-11-01 16:12:30\",\"order_info\":\"批量注册处理完成\",\"order_status\":\"2\",\"recode\":\"10000\",\"remsg\":\"成功\",\"signdata\":\"ccUBVev2/cWjeg+keV07jlRfwEtc80TfDTouOrXdP4Lf3fJZIu8jXLpzteZf73zblEqPYYhx0uBtUAhZ1RJId84T8pAVfNobmW/A5c8tDfDdNxZ9ZzAhMH9TeBDFsRGlcu81sh/FWreBhQy2EYtegNOTmcJi4wqyAFUb5U9MUGw=\",\"success_data\":[{\"platcust\":\"2017041112584053510028\",\"detail_no\":\"td_ubr1491886715234_s0\",\"mobile\":\"13958996689\"},{\"platcust\":\"2017110117015049910008\",\"detail_no\":\"fs_reg4Ele1509526853877_s1\",\"mobile\":\"13616810257\"}],\"success_num\":1,\"total_num\":1}";
		String json2 = "{\"error_data\":[{\"error_no\":\"31111\",\"detail_no\":\"fs_reg4Ele1509524856595_s1\",\"error_info\":\"账户验证失败：error_no[027091],error_info[证件号码格式错误]\",\"mobile\":\"13616810257\"}],\"finish_datetime\":\"2017-11-01 15:38:36\",\"order_info\":\"批量注册处理完成\",\"order_status\":\"2\",\"recode\":\"10000\",\"remsg\":\"成功\",\"signdata\":\"ADE8l2+mhAF5anyyY9C2OoSHjUi0tkL0oanQ/2MJqhOM6VEPtfzfG+dpNdFzTOILlqaChVQM9f4Au6mSeflcFgandXVu9GmdsTSUH3I9fJlbTd+ERC1g3Ba0KSY3DoCGTx2pkeJrMyxHcQX7u43I6m4pHFqD0eoK2QyeMyQCrIs=\",\"success_num\":0,\"total_num\":1}";
		String json3 = "{\"error_data\":[{\"error_no\":\"20001\",\"error_info\":\"缺少必要参数\",\"mobile\":\"13616810257\"}],\"finish_datetime\":\"2017-11-02 17:34:35\",\"order_info\":\"批量注册处理完成\",\"order_status\":\"2\",\"recode\":\"10000\",\"remsg\":\"成功\",\"signdata\":\"aNhhQ2g6wqYkJrreZ/jH+kL4zu4q6F7B0bJRCZ44nS/swYRh0ePlOUuPZnVEAnaArQ9nO3RxJI9Yzqo5e4rbOKcklYspeq7nehf+hmhqIrFkcveSki+u+OUU0d+ERqVxspMECoqyaWt0n/I/jmiqwC1tJ0ANtG7ciXjw9Pf1cjw=\",\"success_num\":0,\"total_num\":1}";

		String json4 = "{\"data\":{\"balance\":\"0.0000\",\"frozen_amount\":\"0.0000\"},\"recode\":\"10000\",\"remsg\":\"成功\",\"signdata\":\"lULgaeYj8pQfRfLq5IOoFF2lk5PNSgZcmoLF7jj2XxSdWAce7jEpMLW0dJI6TMk41b4HRrNvkEO%2FV7czUmzdmP2Z0RhgiEW4NL4yTdRW2UAV3bCiXZE%2BVLXii0fxvWtL5xIPRfW28z8e0YuksCJ9Vnl8rqYUbe784q3AIiyL0cQ%3D\"}";

		String json5 = "{\"data\":{\"fundList\":[{\"amt_type\":\"冻结\",\"amt\":132.18,\"trans_time\":\"20:39:54\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"},{\"amt_type\":\"解冻\",\"amt\":132.18,\"trans_time\":\"20:42:20\",\"plat_no\":\"BOB-TDJR-A-2017017\",\"trans_date\":\"2017-04-11\",\"platcust\":\"TD-2017-04-09-97084225949340800\",\"trans_name\":\"提现\"}]},\"recode\":\"10000\",\"remsg\":\"成功\"}";

		HashMap<String, String> map = JsonUtil.json2Map(json5);

		List<BeanStructure> bss = new ArrayList<BeanStructure>();
		bss.add(new BeanStructure(BJFN.errorData, CFN._elelx_));
		bss.add(new BeanStructure(BJFN.successData, CFN._elelx_));
		bss.add(new BeanStructure(BJFN.data, CFN._swb_));
		bss.add(new BeanStructure(BJFN.data + CFN._swb_, CFN._elelx_));// +
																		// BJFN.fundList
		BandUtil.analyze(map, bss);

		map = MapTransKeyUtil.TransBj2Td(map);

		for (Map.Entry<String, String> e : map.entrySet()) {
			System.out.println("keys :\t" + e.getKey());
			System.out.println("value:\t" + e.getValue());
		}
	}
	
	public static void main(String[] args) {
		HashMap<String, String> bj = new HashMap <String, String>();
		
		bj.put(BJFN.data + CFN._lisize, "1");
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.name, "qqq");
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.idType, BBDD.CERT_TYPE_ID);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.idCode,  "1234567789");
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.mobile,  "1111");
		//bj.put(BJFN.data + CFN._eleln_(0) + BJFN.email, null);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.cusType, BBDD.CUSTOMER_TYPE_PERSONAL);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.cardNo,  "000000");
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.cardType, BBDD.BANK_CARD_TYPE_DEBIT);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.preMobile,  "1234567789");
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.payCode, BBC.PAY_CODE);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.remark, "ccc");

		putList2JsonWithoutNUll(bj, BJFN.data, new Integer(bj.get(BJFN.data + CFN._lisize)),
				UserKey.regist4EleRequest);

		for (Map.Entry<String, String> e : bj.entrySet()) {
			System.out.println("keys :\t" + e.getKey());
			System.out.println("value:\t" + e.getValue());
		}
	}

	public static void getSuccessForSingle(HashMap<String, String> bj) {
		bj.put(BJFN.success, BBDD.xfalse);
		if (bj.get(BJFN.successNum) != null && !bj.get(BJFN.successNum).isEmpty()) {
			if (bj.get(BJFN.recode).equals(BBDD.success10000) && bj.get(BJFN.successNum).equals("1")) {
				bj.put(BJFN.success, BBDD.xtrue);
			}
		} else {
			if (bj.get(BJFN.recode).equals(BBDD.success10000)) {
				bj.put(BJFN.success, BBDD.xtrue);
			}
		}
	}

	public static String list2JsonWithoutNUll(Map<String, String> bj, String addFielName, Integer listSize,
			List<String> fieldOrder) {
		StringBuffer data = new StringBuffer();
		StringBuffer result = new StringBuffer();

		for (int i=0; i < listSize; i++) {
			data.append(",");
			data.append(JsonUtil.map2JsonWithoutNull(bj, addFielName+CFN._eleln_(i), fieldOrder));
		}
		result.append("[");
		result.append(data.substring(1));
		result.append("]");
		return result.toString();
	}
	
	public static void putList2JsonWithoutNUll(Map<String, String> bj, String addFielName, Integer listSize,
			List<String> fieldOrder) {
		bj.put(addFielName, list2JsonWithoutNUll(bj, addFielName, listSize, fieldOrder));
	}
	public static String map2JsonWithoutNull(Map<String, String> bj, String addFielName, Integer listSize,
			List<String> fieldOrder) {
		return JsonUtil.map2JsonWithoutNull(bj, addFielName+CFN._swb_, fieldOrder);
	}
	
	public static void putMap2JsonWithoutNull(Map<String, String> bj, String addFielName, Integer listSize,
			List<String> fieldOrder) {
		bj.put(addFielName, map2JsonWithoutNull(bj, addFielName, listSize, fieldOrder));
	}
}
