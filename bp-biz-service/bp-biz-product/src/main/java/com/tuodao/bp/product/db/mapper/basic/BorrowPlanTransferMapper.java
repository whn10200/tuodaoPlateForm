package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.product.db.model.basic.BorrowPlanTransfer;
import com.tuodao.bp.product.db.model.basic.BorrowPlanTransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowPlanTransferMapper {
    long countByExample(BorrowPlanTransferExample example);

    int deleteByExample(BorrowPlanTransferExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowPlanTransfer record);

    int insertSelective(BorrowPlanTransfer record);

    List<BorrowPlanTransfer> selectByExample(BorrowPlanTransferExample example);

    BorrowPlanTransfer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowPlanTransfer record, @Param("example") BorrowPlanTransferExample example);

    int updateByExample(@Param("record") BorrowPlanTransfer record, @Param("example") BorrowPlanTransferExample example);

    int updateByPrimaryKeySelective(BorrowPlanTransfer record);

    int updateByPrimaryKey(BorrowPlanTransfer record);

    List<BorrowPlanTransfer> getPlanReverifyTransferList();
}