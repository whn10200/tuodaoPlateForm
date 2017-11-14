package com.tuodao.bp.product.db.mapper.basic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.product.db.model.basic.BorrowRepayment;
import com.tuodao.bp.product.db.model.basic.BorrowRepaymentExample;

public interface BorrowRepaymentMapper {
    int countByExample(BorrowRepaymentExample example);

    int deleteByExample(BorrowRepaymentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowRepayment record);

    int insertSelective(BorrowRepayment record);

    List<BorrowRepayment> selectByExample(BorrowRepaymentExample example);

    BorrowRepayment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowRepayment record, @Param("example") BorrowRepaymentExample example);

    int updateByExample(@Param("record") BorrowRepayment record, @Param("example") BorrowRepaymentExample example);

    int updateByPrimaryKeySelective(BorrowRepayment record);

    int updateByPrimaryKey(BorrowRepayment record);

    /**
     * 根据输入的id 查询理财计划中未还款的最小期数信息
     * @param productId
     * @return
     */
    List<HashMap<String,String>> getMinUnpaidByPlanId (Integer productId);


    int updateDoRepay(@Param("record") BorrowRepayment record, @Param("example") BorrowRepaymentExample example);

    void doAdvanceRepay(@Param("borrowId")Integer borrowId);

    /**
     * 查询某一个标的未还款期数 上期还款日期  如果没还款过 那么就取复审时间
     */
    BorrowRepaymentInfoOutput getRepayInfoByBorrowId(@Param("borrowId")Integer borrowId);
    
    
    BorrowRepaymentOutput getRepayInfoByparam(BorrowRepaymentInput input);
    
    BorrowRepaymentInput getRepayInfoById(Integer id);
}