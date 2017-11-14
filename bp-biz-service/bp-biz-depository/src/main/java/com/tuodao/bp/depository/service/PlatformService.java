package com.tuodao.bp.depository.service;

import java.util.HashMap;

public interface PlatformService extends BaseService {

	public HashMap<String, String> fundFreeze(HashMap<String, String> map);

	public HashMap<String, String> fundUnblock(HashMap<String, String> map);

	public HashMap<String, String> platformConverse(HashMap<String, String> map);

	public HashMap<String, String> platformRecharge(HashMap<String, String> map);

	public HashMap<String, String> platformTransfer(HashMap<String, String> map);

	public HashMap<String, String> platformWithDraw(HashMap<String, String> map);

}
