package com.tuodao.bp.product.service;

import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.business.product.output.ProductAuditRecordOutput;
import com.tuodao.bp.product.db.model.basic.ProductAuditRecord;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/10/25 0025 09:55
 * @Introduction
 */
public interface IProductAuditRecordService {

    List<ProductAuditRecord>  getProductAuditRecord(List<Integer> id);

    List<ProductAuditRecordOutput> getRecordOutput(List<Integer> ids);
}
