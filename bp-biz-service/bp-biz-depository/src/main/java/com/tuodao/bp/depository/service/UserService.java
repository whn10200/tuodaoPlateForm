package com.tuodao.bp.depository.service;

import java.util.HashMap;

/**废弃接口表示并未在项目中使用，若要启用请重新调试*/
public interface UserService extends BaseService {

	public HashMap<String, String> regist4Ele(HashMap<String, String> map);

	public HashMap<String, String> registRealname(HashMap<String, String> map);

	public HashMap<String, String> changeCard(HashMap<String, String> map);

	@Deprecated
	public HashMap<String, String> messageBoundCardApply(HashMap<String, String> map);
	@Deprecated
	public HashMap<String, String> messageBoundCardValidate(HashMap<String, String> map);

	public HashMap<String, String> boundCard(HashMap<String, String> map);

	public HashMap<String, String> updateInfo(HashMap<String, String> map);
}
