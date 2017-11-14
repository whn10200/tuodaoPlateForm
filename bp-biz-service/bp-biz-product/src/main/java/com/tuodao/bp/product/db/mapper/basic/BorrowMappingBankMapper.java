package com.tuodao.bp.product.db.mapper.basic;

import com.tuodao.bp.product.db.model.basic.BorrowMappingBank;
import com.tuodao.bp.product.db.model.basic.BorrowMappingBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BorrowMappingBankMapper {
    long countByExample(BorrowMappingBankExample example);

    int deleteByExample(BorrowMappingBankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowMappingBank record);

    int insertSelective(BorrowMappingBank record);

    List<BorrowMappingBank> selectByExample(BorrowMappingBankExample example);

    BorrowMappingBank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowMappingBank record, @Param("example") BorrowMappingBankExample example);

    int updateByExample(@Param("record") BorrowMappingBank record, @Param("example") BorrowMappingBankExample example);

    int updateByPrimaryKeySelective(BorrowMappingBank record);

    int updateByPrimaryKey(BorrowMappingBank record);
    
    int deleteBorrowMappingBankByProductId(Integer productId);

    List<BorrowMappingBank> getCompensationList();
}