package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import com.tuodao.bp.model.business.traningcenter.output.UnderTenderOutput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTenderQuery;
import com.tuodao.bp.traningcenter.db.model.basic.TransfereeBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 王艳兵
 */
public interface BorrowTenderMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insert(BorrowTender record);

    /**
     * 插入不为空的记录
     *
     * @param record
     * @return
     */
    int insertSelective(BorrowTender record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     * @return
     */
    BorrowTender selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(BorrowTender record);

    /**
     * 更新投标状态
     * @param tenderId 投标id
     * @param status 更新后的状态
     * @param remarks 要更新的备注新
     * @return
     */
    int updateTenderStatus(@Param("tenderId")int tenderId,@Param("status")int status,@Param("remarks")String remarks);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record 
     * @return
     */
    int updateByPrimaryKey(BorrowTender record);

    /**
     * 根据条件获取投标信息列表
     * @param query   投标查询参数
     * @return
     */
    List<BorrowTender> getList(BorrowTenderQuery query);


    /**
     * 理财计划到期查询还处于复审的投资成功的记录
     * @param borrowId   理财计划id
     * @return
     */
    List<BorrowTender> selectListByNoReverify(Integer borrowId);


    /**
     * 查询用户投标列表 查询条件 状态,开始时间 结束时间 投标信息包含散标与精选计划上层投标
     * @param query
     * @return
     */
    List<BorrowTender> getBorrowList(BorrowTenderQuery query);

    /**
     * 根据标的id获取投标列表 包含投标冻结与投标成功的记录
     * @param borrowId
     * @return
     */
    List<BorrowTender> getListByBorrowId(@Param("borrowId")Integer borrowId);

    /**
     * 根据主键批量更新投标状态
     * @param list
     * @param status 状态
     * @param isTransferred 是否可转让
     * @param transferTime 可转让时间
     * @return
     */
    int updateTenderStatusBatch(@Param("list") List<BorrowTender> list,
                                @Param("status")Integer status,
                                @Param("isTransferred")Integer isTransferred,
                                @Param("transferTime")Date transferTime);


    /**
     * 根据主键批量更新投标状态,与上面方法完全一样 方便调用
     * @param list
     * @param status 状态
     * @param isTransferred 是否可转让
     * @param transferTime 可转让时间
     * @return
     */
    int updateTenderStatusBatch2(@Param("list") List<Integer> list,
                                @Param("status")Integer status,
                                @Param("isTransferred")Integer isTransferred,
                                @Param("transferTime")Date transferTime);
    /**
     * 根据用户id查询用户投标信息 注意该方法不可乱用 仅仅为了获取第一次投标使用
     * @param userId 用户id
     * @return
     */
    List<BorrowTender> selectByUserId(@Param("userId") String userId);

    /**
     * 查询 受让记录
     * @param userId 用户id
     * @param status 状态列表
     * @return {@link BorrowTender}
     */
    List<BorrowTender> selectBorrowTenderByUserIdAndStatus(@Param("userId") String userId, @Param("status") Integer status);

    List<TransfereeBean> selectByUserIdAndStatus(@Param("userId") String userId, @Param("statusList") List<Integer> statusList);

    @Select("select user_id, pre_account, add_time, mobile from borrow_tender where borrow_id = #{borrowId} and tender_type = #{type}")
    List<BorrowTender> selectByBorrowIdAndType(@Param("borrowId") Integer borrowId, @Param("type") Integer type);

    Double selectSumTenderByTenderId(Integer tenderId);

    List<UnderTenderOutput> selectTenderListByChoicenessTenderId(Integer tenderId);


    /**
     * 查询该理财计划复审时所投资的记录
     * @param borrowId
     * @return
     */
    List<BorrowTender>  selectTenderListOnReverify(@Param("borrowId") Integer borrowId);


    /**
     * 查询理财计划下底层标的复审时所投资的记录
     * @param borrowId
     * @return
     */
    List<PlanBorrowTenderOutput> selectTenderListOnOrgReverify(@Param("borrowId") Integer borrowId);


    /**
     * 查询理财计划下转让标的复审时所投资的记录
     * @param borrowId
     * @return
     */
    List<PlanBorrowTenderOutput> selectTenderListOnDevReverify(@Param("borrowId") Integer borrowId);

    /**
     * 查询理财计划下转让标的复审时所投资的记录(陪玩账户)
     * @param borrowId
     * @return
     */
    List<PlanBorrowTenderOutput> selectTenderListOnDevReverifyPlay(@Param("borrowId") Integer borrowId);


    /**
     * 根据投标状态获取投标列表
     * @param status   投标状态
     * @param borrowId  标的ID
     * @param tenderType 投标类型 0散标投标 1债权投标 2:精选计划普通投标 3:精选计划再转投标
     * @return
     */
    List<BorrowTender> getListByStatus(@Param("status")Integer status,@Param("borrowId")Integer borrowId,@Param("tenderType")Integer tenderType);

    /**
     * 获取投标最多的人
     * @param borrowId
     * @return
     */
    BorrowTender getMaxBorrowTender(@Param("borrowId")Integer borrowId);

    /**
     * 获取最后投标的人
     * @param borrowId
     * @return
     */
    BorrowTender getLastBorrowTender(@Param("borrowId")Integer borrowId);

    /**
     * 根据用户id与标的编号获取投标次数 投标中或者投标成功
     * @param userId 用户id
     * @param borrowId 标的编号
     * @return
     */
    long getByUserIdAndBorrowId(@Param("userId")String userId,@Param("borrowId")Integer borrowId);

    /**
     * 获取用户投标成功的次数不包含债权投标
     * @param userId
     * @return
     */
    long getUserTenderCount(@Param("userId")String userId);
    /**
     * 更新订单号ID
     * @param oldOrderId
     * @param newOrderId
     * @return
     */
    int updateOrderId(@Param("oldOrderId") String oldOrderId,@Param("newOrderId") String newOrderId);

    /**
     * 根据投标订单号获取投标信息
     * @param orderId
     * @return
     */
    BorrowTender getByOrderId(@Param("orderId")String orderId);

    /**
     * 根据精选计划投标id查询投标冻结或者投标成功的投标信息
     * @param tenderId
     * @return
     */
    List<BorrowTender> getByChoicenessTenderId(@Param("tenderId")Integer tenderId);



    /**
     * 查询投资该理财计划的所有投标
     * @param borrowId
     * @return
     */
    List<BorrowTender> getBorrowTenderByProductId(@Param("borrowId")Integer borrowId);

    /**
     * 更新
     * @param list
     */
    void updateBatch(@Param("list") List<BorrowTender> list);

    /**
     * 根据用户id获取抵扣券总收益
     * @param userId
     * @return
     */
    BigDecimal getTenderVoucherCount(@Param("userId") String userId);

    /**
     * 根据产品id查询投标成功的投标信息 包含复审,及转让等状态
     * @param productId
     * @return
     */
    List<BorrowTender> getProductTender(@Param("productId")Integer productId);

    /**
     * 获取用户所有投标冻结金额
     * @param userId
     * @return
     */
    BigDecimal getUserTenderFrost(@Param("userId") String userId);
}