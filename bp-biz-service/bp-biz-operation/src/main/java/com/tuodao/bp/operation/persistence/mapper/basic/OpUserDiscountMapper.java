package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscount;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserDiscountMapper {
    int countByExample(OpUserDiscountExample example);

    int deleteByExample(OpUserDiscountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpUserDiscount record);

    int insertSelective(OpUserDiscount record);

    List<OpUserDiscount> selectByExample(OpUserDiscountExample example);

    OpUserDiscount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpUserDiscount record, @Param("example") OpUserDiscountExample example);

    int updateByExample(@Param("record") OpUserDiscount record, @Param("example") OpUserDiscountExample example);

    int updateByPrimaryKeySelective(OpUserDiscount record);

    int updateByPrimaryKey(OpUserDiscount record);
}