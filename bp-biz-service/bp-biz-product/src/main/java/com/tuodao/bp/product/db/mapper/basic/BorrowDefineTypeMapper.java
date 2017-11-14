package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import com.tuodao.bp.product.db.model.basic.BorrowDefineTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowDefineTypeMapper {
    long countByExample(BorrowDefineTypeExample example);

    int deleteByExample(BorrowDefineTypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowDefineType record);

    int insertSelective(BorrowDefineType record);

    List<BorrowDefineType> selectByExample(BorrowDefineTypeExample example);

    BorrowDefineType selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowDefineType record, @Param("example") BorrowDefineTypeExample example);

    int updateByExample(@Param("record") BorrowDefineType record, @Param("example") BorrowDefineTypeExample example);

    int updateByPrimaryKeySelective(BorrowDefineType record);

    int updateByPrimaryKey(BorrowDefineType record);
}