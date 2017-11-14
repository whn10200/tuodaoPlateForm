package com.tuodao.bp.depository.service;

import java.util.HashMap;

public interface BorrowerService extends BaseService {

	public HashMap<String, String> payBak(HashMap<String, String> map);

	public HashMap<String, String> biddingCompensation(HashMap<String, String> map);

	public HashMap<String, String> borrowerCompensation(HashMap<String, String> map);

}
