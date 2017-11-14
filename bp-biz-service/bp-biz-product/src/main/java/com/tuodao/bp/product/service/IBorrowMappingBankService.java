package com.tuodao.bp.product.service;

import com.tuodao.bp.model.business.product.input.BorrowMappingBankInput;
import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import com.tuodao.bp.product.db.model.basic.BorrowMappingBank;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/11/6 0006 14:55
 * @Introduction
 */
public interface IBorrowMappingBankService {

	/**
	 * 更新记录的状态
	 */
	Boolean updateRecord(BorrowMappingBankInput input);

	/**
	 * 新增记录
	 */
	Boolean insertRecord(BorrowMappingBank record);

	 
	/**
	 * 根据产品Id删除记录
	 * @param productId
	 * @return void
	 */
	void deleteBorrowMappingBankByProductId(Integer productId);

	/**
	 * 获取记录
	 * @param borrowId
	 * @return
	 */
	BorrowMappingBankOutput getRecordByBorrowId(Integer borrowId);

	/**
	 * 查询所有需要垫付资金的 记录
	 * @return
	 */
	List<BorrowMappingBankOutput> getCompensationList();
}
