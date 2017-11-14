package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.output.SelectNoMateOutput;
import com.tuodao.bp.model.business.traningcenter.output.SelectTenderOutput;
import com.tuodao.bp.model.business.traningcenter.output.TenderRecord;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTenderExample;
import java.util.List;

import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import org.apache.ibatis.annotations.Param;

public interface BorrowChoicenessTenderMapper {
    int countByExample(BorrowChoicenessTenderExample example);

    int deleteByExample(BorrowChoicenessTenderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowChoicenessTender record);

    int insertSelective(BorrowChoicenessTender record);

    List<BorrowChoicenessTender> selectByExample(BorrowChoicenessTenderExample example);

    BorrowChoicenessTender selectByPrimaryKey(Integer id);

    List<SelectNoMateOutput> selectNoMate();

    int updateByExampleSelective(@Param("record") BorrowChoicenessTender record, @Param("example") BorrowChoicenessTenderExample example);

    int updateByExample(@Param("record") BorrowChoicenessTender record, @Param("example") BorrowChoicenessTenderExample example);

    int updateByPrimaryKeySelective(BorrowChoicenessTender record);

    int updateByPrimaryKey(BorrowChoicenessTender record);

    List<TenderRecord> selectTenderListByBorrowId(Integer borrowId);

    TenderRecord selectMaxTenderByBorrowId(Integer borrowId);

    TenderRecord selectLastTenderByBorrowId(Integer borrowId);

    BorrowChoicenessTender selectMax(Integer borrowId);

    BorrowChoicenessTender selectLast(Integer borrowId);


    List<SelectTenderOutput> selectByStatusAndUserId(SelectTenderInput selectTenderInput);

    Double selectSumJoinByTenderId(Integer tenderId);

    /**
     * 查询释放资金的原始加入记录
     * @param tenderId
     * @return
     */
    BorrowChoicenessTender selectReleaseFunds(@Param("tenderId") Integer tenderId);


    List<BorrowChoicenessTender> selectOriginalListByBorrowId(@Param("borrowId") Integer borrowId);

    /**
     * 查询理财计划到期时候未匹配的资金
     * @param borrowId
     * @return
     */
    List<BorrowChoicenessTender> selectNoMateOver(@Param("borrowId") Integer borrowId);


    /**
     * 统计精选计划投标测试
     * @param userId
     * @return
     */
    long getUserTenderCount(@Param("userId")String userId);



}