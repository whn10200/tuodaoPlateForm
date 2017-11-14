package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.product.db.model.basic.ProductAuditRecord;
import com.tuodao.bp.product.db.model.basic.ProductAuditRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductAuditRecordMapper {
    int countByExample(ProductAuditRecordExample example);

    int deleteByExample(ProductAuditRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductAuditRecord record);

    int insertSelective(ProductAuditRecord record);

    List<ProductAuditRecord> selectByExampleWithBLOBs(ProductAuditRecordExample example);

    List<ProductAuditRecord> selectByExample(ProductAuditRecordExample example);

    ProductAuditRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductAuditRecord record, @Param("example") ProductAuditRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") ProductAuditRecord record, @Param("example") ProductAuditRecordExample example);

    int updateByExample(@Param("record") ProductAuditRecord record, @Param("example") ProductAuditRecordExample example);

    int updateByPrimaryKeySelective(ProductAuditRecord record);

    int updateByPrimaryKeyWithBLOBs(ProductAuditRecord record);

    int updateByPrimaryKey(ProductAuditRecord record);
}