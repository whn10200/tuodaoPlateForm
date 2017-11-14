package com.tuodao.bp.depository.util;

import java.util.HashMap;
import java.util.Map;

import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.CFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.depository.keyCouple.KeyCouple;

public class MapTransKeyUtil {

	public static HashMap<String, String> Trans1(Map<String, String> source, Map<String, String> keyCouple) {
		HashMap<String, String> target = new HashMap<String, String>();

		if (source == null || source.size() == 0) {
			return null;
		}
		for (Map.Entry<String, String> en : source.entrySet()) {
			String[] keySec = en.getKey().split(CFN._swb_);
			String transKey = "";
			for (String sec : keySec) {
				// 如果开头为elel
				if (sec.indexOf(CFN.elel) == 0) {
					transKey = transKey + CFN._swb_ + sec;
				} else {
					//如果中间有一段不在keyCouple中，则不兑换KEY
					if(keyCouple.containsKey(sec))
					{
						transKey = transKey + CFN._swb_ + keyCouple.get(sec);
					}else{
						transKey = CFN._swb_ +en.getKey();
						break;
					}
				}
			}
			// 老一套，连接符是加在前面的，所以从前面取
			target.put(transKey.substring(CFN._swb_.length(), transKey.length()), en.getValue());
		}
		return target;

	}

	public static HashMap<String, String> Trans(Map<String, String> source, Map<String, String> keyCouple) {
		HashMap<String, String> target = new HashMap<String, String>();

		if (source == null || source.size() == 0) {
			return null;
		}
		for (Map.Entry<String, String> en : source.entrySet()) {
			String key = en.getKey();
			String[] secs = key.split(CFN.ul);
			if (secs.length > 1) {
				String targetSec = "";
				String targetKey = "";
				//会忽略最后一个连接词后面的下划线
				for (String sec : secs) {
					if (sec.contains(CFN.elel) || sec.contains(CFN.swb)) {
						if (!targetSec.isEmpty()) {
							targetSec = targetSec.substring(CFN.ul.length());
							targetKey = targetKey + CFN.ul + keyCouple.get(targetSec);
							targetSec ="";
						}
						targetKey = targetKey + CFN.ul + sec;
					} else {
						targetSec = targetSec + CFN.ul + sec;
					}
				}
				
				//最后一节如果没有连接词，那么再循环一次。
				if (!targetSec.isEmpty()) {
					targetSec = targetSec.substring(CFN.ul.length());
					targetKey = targetKey + CFN.ul + keyCouple.get(targetSec);
					targetSec ="";
				}
				
				target.put(targetKey.substring(CFN.ul.length()), en.getValue());
			} else {
				target.put(keyCouple.get(key), en.getValue());
			}

		}
		return target;

	}

	public static HashMap<String, String> TransBj2Td(Map<String, String> source) {
		return Trans(source, KeyCouple.bj2td);
	}

	public static HashMap<String, String> TransTd2Bj(Map<String, String> source) {
		return Trans(source, KeyCouple.td2bj);
	}

	public static void main(String[] args) {
		// list 作为map 时，键为序列号，值为对应值
		HashMap<String, String> aa = new HashMap<>();
		aa.put(BJFN.data + CFN._eleln_(0) + BJFN.platCust + CFN._eleln_(0) + BJFN.platAccount, "aaa");
		aa.put(BJFN.data + CFN._eleln_(36) + BJFN.platCust + CFN._eleln_(1) + BJFN.platAccount, "bbb");
		aa.put(BJFN.data + CFN._swb, "ccc");
		aa.put(BJFN.totalNum, "111");
		HashMap<String, String> bb = TransTd2Bj(aa);

		for (HashMap.Entry<String, String> entry : aa.entrySet()) {
			System.out.println(entry.getKey());
		}

		System.out.println("");
		for (HashMap.Entry<String, String> entry : bb.entrySet()) {
			System.out.println(entry.getKey());
		}
	}

}
