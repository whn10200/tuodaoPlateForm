package com.tuodao.bp.traningcenter.db.mapper.basic;


import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.business.traningcenter.output.RecoverListOutput;
import com.tuodao.bp.model.business.traningcenter.output.SelectClaimOutput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.traningcenter.input.BorrowCollectionInput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollectionCapital;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 王艳兵
 */
public interface BorrowCollectionMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insert(BorrowCollection record);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insertSelective(BorrowCollection record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    BorrowCollection selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BorrowCollection record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(BorrowCollection record);

    /**
     * 获取用户已经回款的总金额 包含利息等
     * @param userId 用户ID
     * @return
     */
    BigDecimal getTotalCollectionByUserId(@Param("userId") String userId);

    /**
     * 根据tenderId查询出该投资未回款的记录
     * @param tenderId
     * @return
     */
    List<BorrowCollection> selectByTenderIdAndNORecover(Integer tenderId);


    /**
     * 查询回款记录
     * @param tenderIdList 投资id列表
     * @param status 状态 0未回款 1已经回款 7已转让
     * @return
     */
    List<BorrowCollectionCapital> selectByTenderIdListAndStatus(@Param("tenderIdList") List<Integer> tenderIdList,
                                                                @Param("status") Integer status);

    /**
     * 查询已回款或已转让 还款激励
     * @param tenderId 投资id
     * @return
     */
    BorrowCollectionCapital selectBackByTenderId(@Param("tenderId") Integer tenderId);

    /**
     * 查询未回款 还款激励
     * @param tenderId 投资id
     * @return
     */
    BorrowCollectionCapital selectUnBackByTenderId(@Param("tenderId") Integer tenderId);

    /**
     * 查询回款计划
     * @param tenderId 投标id
     * @param status 状态
     * @return
     */
    List<BorrowCollection> selectByTenderIdAndStatus(@Param("tenderId") Integer tenderId,
                                                     @Param("status") Integer status);

    /**
     * 查询回款计划
     * @param userId 用户id
     * @param tenderId 投标id
     * @param showed 是否显示
     * @return
     */
    List<BorrowCollection> selectByTenderIdAndUserId(@Param("userId") String userId,
                                                     @Param("tenderId") Integer tenderId,
                                                     @Param("showed") Integer showed);

    /**
     * 批量插入数据库记录
     *
     * @param list
     * @param borrowId 标的id
     * @return
     */
    int insertBatch(@Param("list") List<BorrowCollectionInput> list,
                    @Param("borrowId")Integer borrowId);

    /**
     * 批量插入数据库记录
     *
     * @param list
     */
    void batchInsert(List<BorrowCollection> list);


    /**
     * 根据条件查询回款信息列表,如果提前还款则查询当期及之后 否则查询本期的
     * @param borrowId 投标id
     * @param period 期数
     * @param advance 是否为提前还款
     * @param userId   用户ID 可能为空
     * @return
     */
    List<BorrowCollection> getList(@Param("borrowId")Integer borrowId,@Param("period") Integer period,@Param("advance")Boolean advance,@Param("userId")String userId);


    /**
     * @param tenderId
     * @return
     */
    Double selectSumRecoverByTenderId(Integer tenderId);


    /**
     * @param tenderId
     * @return
     */
   List<RecoverListOutput> selectRecoverListBychioId(Integer tenderId);


    /**
     * 更新状态
     * @param tenderId 投资id
     * @param status 状态
     */
    @Update("update borrow_collection set status = #{status} where tender_id = #{tenderId}")
    @Options(useGeneratedKeys = false)
    void updateStatusByTenderIdList(@Param("tenderId") Integer tenderId, @Param("status") Integer status);

    /**
     * @param tenderId
     * @return
     */
    List<BorrowCollection> selectByTenderId(@Param("tenderId") Integer tenderId);

    /**
     * 获取用户某月或某天 已回款的资金
     * @param param
     * @return
     */
    Map<String,BigDecimal> getRealCollection(CollectionParam param);

    /**
     * 获取用户某月或某天 未回款的资金
     * @param param
     * @return
     */
    Map<String,BigDecimal> getPreCollection(CollectionParam param);

    /**
     * 查询用户对应某一天的回款信息
     * @param userId
     * @param day 天
     * @param type 查询类型0:按月 1:按天
     * @return
     */
    List<BorrowCollection> getByDay(@Param("userId") String userId,
                                    @Param("day") String day,
                                    @Param("type")Integer type);

    /**
     * 获取某一月所有的回款日期
     * @param userId
     * @param month yyyy-MM
     * @return
     */
    List<BorrowCollection> getCollectionDays(@Param("userId") String userId,
                                             @Param("month") String month);

    List<SelectClaimOutput> selectClaim(@Param("tenderId") Integer tenderId,@Param("borrowId") Integer borrowId);


    /**
     * 根据投标id获取精选计划最后一期回款
     * @param tenderId  投标id
     * @param borrowId 精选计划id
     * @return
     */
    BorrowCollection getChoicenessTenderCollection(@Param("tenderId") Integer tenderId,@Param("borrowId") Integer borrowId);

    /**
     * 获取精选计划投标 底层标的的回款信息 同时小于理财计划回款计划日期
     * @param tenderId 投标id
     * @param borrowId 精选计划id
     * @param startTime 查询回款时间限制 开始时间
     * @param endTime   查询回款时间限制 结束时间
     * @return
     */
    List<BorrowCollection> getBottomCollection(@Param("tenderId") Integer tenderId,
                                               @Param("borrowId") Integer borrowId,
                                               @Param("startTime")Date startTime,
                                               @Param("endTime")Date endTime);


    List<BorrowCollection> selectClaimInside(@Param("userId") String userId);


    /**
     * 根据精选计划投标id查询投标成功,且依旧未回款完成的投标信息
     * @param tenderId 精选计划投标id
     * @param borrowId 精选计划标的
     * @return
     */
    List<BorrowCollection> getTendingByChoicenessTenderId(@Param("tenderId")Integer tenderId, @Param("borrowId")Integer borrowId);


    /**
     * 批量更新回款记录状态
     * @param list
     * @return
     */
    int updateBatchStatus(@Param("list")List<BorrowCollection> list);

    /**
     * 获取用户回款收益
     * @param userId
     * @return
     */
    CollectionCountOutput getUserCollectionInterest(@Param("userId") String userId);
}