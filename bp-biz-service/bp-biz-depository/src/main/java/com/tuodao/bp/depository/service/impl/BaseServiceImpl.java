package com.tuodao.bp.depository.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tuodao.bp.cache.basic.depository.DepositoryCache;
import com.tuodao.bp.depository.db.mapper.basic.HttpReceiveLogMapper;
import com.tuodao.bp.depository.db.mapper.basic.HttpRequestLogMapper;
import com.tuodao.bp.depository.db.model.basic.HttpReceiveLogWithBLOBs;
import com.tuodao.bp.depository.db.model.basic.HttpRequestLogWithBLOBs;
import com.tuodao.bp.depository.service.BaseService;
import com.tuodao.bp.depository.sign.CreateSign;
import com.tuodao.bp.depository.sign.MapUtils;
import com.tuodao.bp.depository.sign.ValidateSign;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.BBDD;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.JCAC;
import com.tuodao.bp.model.constant.depository.ONP;
import com.tuodao.bp.model.constant.depository.key.SeekKey;
import com.tuodao.bp.result.error.DepositoryError;
import com.tuodao.bp.result.exception.DepositoryFeignException;
import com.tuodao.bp.utils.HttpClientUtils;
import com.tuodao.bp.utils.JsonUtil;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseServiceImpl.class);

	@Autowired
	private HttpRequestLogMapper httpRequestLogMapper;
	@Autowired
	private HttpReceiveLogMapper httpReceiveLogMapper;

	@Autowired
	private DepositoryCache depositoryCache;

	public HashMap<String, String> requestAndInsertDbCommon(String queryOrderNo, HashMap<String, String> map,
			String requestUrl, List<String> key) {
		
		if(!new Boolean(BBC.ACTIVE)){
			map.put(BJFN.success, BBDD.xfalse);
			map.put(BJFN.recode, BBDD.error90909);
			map.put(BJFN.remsg, BBDD.error90909msg);	
			return map;
		}		
		
		if (depositoryCache.getOrderNo(map.get(BJFN.orderNo)) == null) {
		//if (true) {
			return noneCache(map, requestUrl, key);
		} else {
			depositoryCache.putOrderNo(map.get(BJFN.orderNo));
			return existCache(queryOrderNo, map.get(BJFN.partnerTransDate), map.get(BJFN.partnerTransTime));
		}

	}

	private HashMap<String, String> existCache(String queryOrderNo, String date, String time) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(BJFN.platNo, BBC.PLAT_NO);
		map.put(BJFN.partnerTransDate, date);
		map.put(BJFN.partnerTransTime, time);
		map.put(BJFN.orderNo, BandUtil.getNo(ONP.orderStatus));
		map.put(BJFN.queryOrderNo, queryOrderNo);

		return noneCache(map, BBC.REQUEST_URL_SEEK_ORDER_STATUS, SeekKey.orderStatusRequest);
	}

	private HashMap<String, String> noneCache(HashMap<String, String> requestMap, String requestUrl, List<String> key) {
		HashMap<String, String> map  = BandUtil.clearField(requestMap, key);
		
		HttpRequestLogWithBLOBs rl = new HttpRequestLogWithBLOBs();

		rl.setOrderNo(map.get(BJFN.orderNo));

		String readySign = MapUtils.verticalCombination(map, key);
		String sign = CreateSign.bobRs(readySign);

		long t0 = System.currentTimeMillis();
		rl.setRequestTime(new Date(t0));

		String json = null;

		try {
			String urlPara = MapUtils.andCombination(map, key) + "&" + BJFN.signdata + "="
					+ URLEncoder.encode(URLEncoder.encode(sign, "UTF-8"), "UTF-8");
			rl.setRequestUrl(requestUrl + urlPara);

			long t1 = System.currentTimeMillis();
			LOGGER.info("bei jing depository request post");
			LOGGER.info("post start time={}", t1);
			
			Map<String, Object> paraMap = new HashMap<String, Object>(map);			
			paraMap.put(BJFN.signdata, URLEncoder.encode(URLEncoder.encode(sign, "UTF-8"), "UTF-8"));
			json = HttpClientUtils.postParam2String(requestUrl, paraMap, JCAC.UTF8);

			long t2 = System.currentTimeMillis();
			LOGGER.info("post end time={}" + (t2 - t1) / 1000 + "秒");

			LOGGER.info("request url = {}", requestUrl);
			LOGGER.info("request para = {}", readySign);
			LOGGER.info("request sign = {}", sign);
			LOGGER.info("response json = {}", json);

			if (json != null && !json.equals("")) {
				json = URLDecoder.decode(json, "UTF-8");
				rl.setResponseContent(json);
				if (!ValidateSign.validate(json)) {
					rl.setLocalError("validate sign false");
				}
			} else {
				rl.setLocalError("response content is null");
			}

		} catch (Exception e) {
			rl.setLocalError(e.toString());
			LOGGER.info("bei jing depository request post ERROR： {}", e.toString());
			e.printStackTrace();
		}

		httpRequestLogMapper.insert(rl);

		if (json == null || json.length() < 1) {
			throw new DepositoryFeignException(DepositoryError.BEI_JING_BANKE_REQUES_OUT_TIME);
		}
		HashMap<String, String> rm = JsonUtil.json2Map(json);

		/*
		 * boolean flag = false; String re = rm.get(BJFN.recode); if
		 * (!StringUtils.isEmpty(re) && re.equals(BBDD.success10000)) {
		 * 
		 * String total = rm.get(BJFN.totalNum);
		 * 
		 * if (StringUtils.isEmpty(total)) { flag = true; } else { if
		 * (total.equals(rm.get(BJFN.successNum))) { flag = true; } }
		 * 
		 * if (map.get(BJFN.orderNo).contains("orderStatus")) {
		 * 
		 * String data =
		 * JsonUtil.json2List(map.get(BJFN.data)).get(0).get(BJFN.status); if
		 * (data.equals(BBDD.tradeSuccess)) { flag = true; } }
		 * 
		 * }
		 * 
		 * if (flag) { rm.put(BJFN.success, "true"); } else {
		 * rm.put(BJFN.success, "false"); }
		 */

		return rm;
	}

	public HashMap<String, String> notify(HashMap<String, String> map, List<String> key) {
		HttpReceiveLogWithBLOBs rl = new HttpReceiveLogWithBLOBs();
		rl.setReceiveTime(new Date(System.currentTimeMillis()));
		boolean b = false;

		try {
			String original = MapUtils.verticalCombination(map, key);
			String sign = map.get(BJFN.signdata);
			b = ValidateSign.bobRs(sign, original);

			if (b) {
				map.put(BJFN.success, "true");
				
				rl.setOrderId(map.get(BJFN.orderNo));
				rl.setReceiveIp("");
				rl.setReceiveUrl(map.get(BJFN.requestUrl));
				String urlPara = MapUtils.andCombination(map, key) + "&" + BJFN.signdata + "=" + sign;
				rl.setResponseContent(urlPara);

			} else {
				rl.setLocalError("validate sign false");
			}
		} catch (Exception e) {
			rl.setLocalError(e.toString());
		} finally {
			httpReceiveLogMapper.insertSelective(rl);
		}
		
		map = MapTransKeyUtil.TransBj2Td(map);
		
		return map;
	}

}
