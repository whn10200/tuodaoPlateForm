package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpCoupon;
import com.tuodao.bp.operation.persistence.model.basic.OpCouponExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpCouponMapper {
    int countByExample(OpCouponExample example);

    int deleteByExample(OpCouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpCoupon record);

    int insertSelective(OpCoupon record);

    List<OpCoupon> selectByExample(OpCouponExample example);

    OpCoupon selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpCoupon record, @Param("example") OpCouponExample example);

    int updateByExample(@Param("record") OpCoupon record, @Param("example") OpCouponExample example);

    int updateByPrimaryKeySelective(OpCoupon record);

    int updateByPrimaryKey(OpCoupon record);
}