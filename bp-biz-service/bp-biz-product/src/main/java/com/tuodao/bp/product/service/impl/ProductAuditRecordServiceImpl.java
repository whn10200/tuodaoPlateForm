package com.tuodao.bp.product.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.business.product.output.ProductAuditRecordOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.product.constants.ProductAuditRecordConstant;
import com.tuodao.bp.product.db.mapper.basic.ProductAuditRecordMapper;
import com.tuodao.bp.product.db.model.basic.ProductAuditRecord;
import com.tuodao.bp.product.db.model.basic.ProductAuditRecordExample;
import com.tuodao.bp.product.db.model.basic.ProductWithBLOBs;
import com.tuodao.bp.product.service.IProductAuditRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/10/25 0025 10:04
 * @Introduction 产品审核 业务逻辑
 */
@Transactional
@Service
public class ProductAuditRecordServiceImpl implements IProductAuditRecordService {



    @Autowired
    private ProductAuditRecordMapper auditRecordMapper;

    /**
     * 根据产品id 获取审核记录
     * @param ids
     * @return
     */
    @Override
    public  List<ProductAuditRecord> getProductAuditRecord(List<Integer> ids) {

        int status = ProductAuditRecordConstant.RECORD_STATUS_3;

        ProductAuditRecordExample example = new ProductAuditRecordExample();
        example.createCriteria().andProductIdIn(ids).andAuditTypeEqualTo(status);

        List<ProductAuditRecord>  list  = auditRecordMapper.selectByExample(example);

        return list;
    }


    /**
     * 返回相应的 审核output
     * @param ids
     * @return
     */
    @Override
    public  List<ProductAuditRecordOutput> getRecordOutput(List<Integer> ids) {
        List<ProductAuditRecord> list  = getProductAuditRecord(ids);
        ImmutableList<ProductAuditRecordOutput> resultList = FluentIterable.<ProductAuditRecord>from(list)
                .transform(new Function<ProductAuditRecord, ProductAuditRecordOutput>() {
            @Override
            public ProductAuditRecordOutput apply(ProductAuditRecord input) {
                ProductAuditRecordOutput out = new ProductAuditRecordOutput();
                BeanUtils.copyProperties(input, out);
                return out;
            }
        }).toList();

        return resultList;
    }
}
