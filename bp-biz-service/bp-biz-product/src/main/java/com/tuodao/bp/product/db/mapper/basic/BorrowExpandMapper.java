package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.product.db.model.basic.BorrowExpand;
import com.tuodao.bp.product.db.model.basic.BorrowExpandExample;
import com.tuodao.bp.product.db.model.basic.BorrowExpandWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowExpandMapper {
    int countByExample(BorrowExpandExample example);

    int deleteByExample(BorrowExpandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowExpandWithBLOBs record);

    int insertSelective(BorrowExpandWithBLOBs record);

    List<BorrowExpandWithBLOBs> selectByExampleWithBLOBs(BorrowExpandExample example);

    List<BorrowExpand> selectByExample(BorrowExpandExample example);

    BorrowExpandWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowExpandWithBLOBs record, @Param("example") BorrowExpandExample example);

    int updateByExampleWithBLOBs(@Param("record") BorrowExpandWithBLOBs record, @Param("example") BorrowExpandExample example);

    int updateByExample(@Param("record") BorrowExpand record, @Param("example") BorrowExpandExample example);

    int updateByPrimaryKeySelective(BorrowExpandWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BorrowExpandWithBLOBs record);

    int updateByPrimaryKey(BorrowExpand record);
}