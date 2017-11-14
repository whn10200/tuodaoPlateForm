package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTransfer;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTransferBean;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTransferExample;

import java.util.Date;
import java.util.List;

import com.tuodao.bp.traningcenter.db.model.basic.CreditAssignmentBean;
import org.apache.ibatis.annotations.Param;

public interface BorrowTransferMapper {
    int countByExample(BorrowTransferExample example);

    int deleteByExample(BorrowTransferExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BorrowTransfer record);

    int insertSelective(BorrowTransfer record);

    List<BorrowTransfer> selectByExample(BorrowTransferExample example);

    BorrowTransfer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BorrowTransfer record, @Param("example") BorrowTransferExample example);

    int updateByExample(@Param("record") BorrowTransfer record, @Param("example") BorrowTransferExample example);

    int updateByPrimaryKeySelective(BorrowTransfer record);

    int updateByPrimaryKey(BorrowTransfer record);

    /**
     * 查询债权转让
     * @param tenderId 投标id
     * @return
     */
    BorrowTransfer selectByTenderId(@Param("tenderId") Integer tenderId);

    /**
     * 查询债权转让
     * @param beginPeriod
     * @param endPeriod
     * @return
     */
    List<BorrowTransfer> selectByPeriod(@Param("beginPeriod") int beginPeriod,
                                        @Param("endPeriod") int endPeriod);

    /**
     * 债转列表分页(可转让)
     * @param userId
     * @param dateTime
     * @param endTime
     */
    List<CreditAssignmentBean> pageByStatus(@Param("userId") String userId, @Param("beginTime") Date dateTime, @Param("endTime") Date endTime);

    /**
     * 查询债转列表(转让中、已经转让)
     * @param userId 用户状态
     * @param status 状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<BorrowTransferBean> selectByUserIdAndStatus(@Param("userId") String userId,
                                                     @Param("status") Integer status,
                                                     @Param("startTime") Date startTime,
                                                     @Param("endTime") Date endTime);

    /**
     * 查询满标未复审
     * @return
     */
    List<BorrowTransfer> selectFullAndNotReview();

}