package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpWelfareCouponRef;
import com.tuodao.bp.operation.persistence.model.basic.OpWelfareCouponRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpWelfareCouponRefMapper {
    int countByExample(OpWelfareCouponRefExample example);

    int deleteByExample(OpWelfareCouponRefExample example);

    int insert(OpWelfareCouponRef record);

    int insertSelective(OpWelfareCouponRef record);

    List<OpWelfareCouponRef> selectByExample(OpWelfareCouponRefExample example);

    int updateByExampleSelective(@Param("record") OpWelfareCouponRef record, @Param("example") OpWelfareCouponRefExample example);

    int updateByExample(@Param("record") OpWelfareCouponRef record, @Param("example") OpWelfareCouponRefExample example);
}