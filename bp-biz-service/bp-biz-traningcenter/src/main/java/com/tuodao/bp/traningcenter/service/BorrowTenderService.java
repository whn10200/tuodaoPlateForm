package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.BorrowParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderDetailParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderListParam;
import com.tuodao.bp.model.business.traningcenter.output.UnderTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.ProductTenderVo;
import com.tuodao.bp.model.traningcenter.input.RecheckInput;
import com.tuodao.bp.model.traningcenter.input.UserCollectionInput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;


import java.math.BigDecimal;
import java.util.List;

/**
 * @author 王艳兵
 */
public interface BorrowTenderService {


    /**
     * 写投标资金记录时 计算免费提现金额
     * @param userId 用户id
     * @param tenderMoney 实际投标金额
     * @return 为正数
     */
    BigDecimal getBalanceCash(String userId,double tenderMoney);

    /**
     * 投标 生产者
     * @param executor
     */
    void tenderProducer(TenderExecutor executor);


    /**
     * 投标消费者
     * @param executor
     */
    void tenderConsumer(TenderExecutor executor);

    /**
     * 添加标的投标预处理数据 投标预处理记录,投标冻结记录
     * @param executor
     * @return
     */
    BorrowTender borrowTenderHandler(TenderExecutor executor);

    /**
     * 添加投标信息 投标中
     * @param executor
     * @return
     */
    BorrowTender insertBorrowTender(TenderExecutor executor);

    /**
     * 投标结果放入缓存中
     * @param key 唯一查询key
     * @param msg 结果信息
     * @param status 结果状态
     */
     void putTenderResult(String key,String msg,int status);


    /**
     * 根据主键更新投标状态,增加资金日志
     * @param tenderId 投标ID
     * @param status 状态
     */
    void updateBorrowTenderStatus(Integer tenderId ,int status);


    /**
     * 获取投资信息
     * @param tenderId 投资id
     * @return
     */
    BorrowTender getTenderInfo(Integer tenderId);

    /**
     * 分页查询用户的投标列表
     * @param param
     * @return
     */
    PageInfo<BorrowTenderVo> getUserTenderByPage(TenderListParam param);

    /**
     * 分页获取用户自动投标子列表,即 一个自动投标会分配到多个标的上进行投资的列表
     * @param param
     * @return
     */
    PageInfo<BorrowTenderVo> getUserAutoTenderByPage(AutoTenderExtParam param);

    /**
     * 根据主键获取投标信息列表
     * @param param
     * @return
     */
    BorrowTenderOutput getUserBorrowTenderById(TenderDetailParam param);


    /**
     * 根据投标信息校验剩余可投金额
     * @param productId 产品信息
     * @param tenderMoney 投标金额
     */
    void verifyBorrow(Integer productId,double tenderMoney);

    /**
     * 根据产品id获取 获取投标信息 如果产品id为空则查询所有
     * @param productId 产品id
     * @param status 状态
     * @return
     */
    List<BorrowTenderOutput> getListByStatus(Integer productId,Integer status);

    /**
     * 满标复审
     * 1.更新投标状态<br/>
     * 2.添加更新资金记录
     * @param recheck
     */
    void updateRecheck(RecheckInput recheck);

    /**
     * 满标复审<br/>
     * 1.添加回款记录<br>
     * 2更新投资信息<br/>
     * 3更新资金记录<br/>
     * 4积分奖励<br/>
     * 5邀请奖励<br/>
     * @param input 回款列表
     */
    void borrowRecheck(RecheckInput input);


    PageInfo<UnderTenderOutput> selectTenderListByChoicenessTenderId(JustIdInput justIdInput);


    /**
     * 设置预计收益包含:总收益,加息券收益,平台加息收益
     * 总收益=平台加息+加息券收益+基础收益
     * @param tender 投标信息
     * @param product 加息券信息
     */
    void setInterest(BorrowTender tender, ProductOutput product);

    /**
     * 分页查询用户投标
     * @param input
     * @return
     */
    PageInfo<BorrowTenderOutput> getTenderList(TenderQueryInput input);

    /**
     * 根据标的Id获取投标信息
     * @param borrowId
     * @return
     */
    List<BorrowTenderOutput> getTenderListByBorrowId(Integer borrowId);

    /**
     * 投标信息撤回(后台系统撤回)
     * @param product
     */
    void withdrawTender(ProductOutput product);

    /**
     * 标的撤回 组装备注信息
     * @param productId 产品id
     * @param title 产品标的
     * @return
     */
    String getTenderCancelRemarks(Integer productId,String title);
    /**
     * 获取投资信息
     * @param borrowId
     * @param typeId
     * @return
     */
    List<BorrowTenderOutput> getByBorrowIdAndTypeId(Integer borrowId, Integer typeId);

    /**
     * 投标成功后置处理
     * @param orderNo   投标唯一订单号(可当做id查询使用)
     * @param tenderKey 投标结果查询唯一key
     */
    void tenderSuccessTask(String orderNo,String tenderKey);

    /**
     * 投标失败后置处理 如果投标时间过长而用户等待时间较短,用户看不到投标结果,需要短信通知失败 如果投标时间比较短 则用户可以直接查询到投标结果 不需要通知
     * @param orderNo  订单号
     * @param errorMsg 错误信息
     * @param tenderKey 投标查询结果唯一key
     */
    void tenderFailTask(String orderNo,String errorMsg,String tenderKey);

    /**
     * 更新用户投标信息为失败:更新状态 更新备注 更新时间
     * @param tender   投标信息
     * @param errorMsg 投标失败原因
     */
    void updateBorrowTenderFail(BorrowTender tender,String errorMsg);
    /**
     * 获取标的最大的一笔投标
     * @param borrowId
     * @return
     */
    BorrowTenderOutput getMaxBorrowTender(Integer borrowId);

    /**
     * 获取最后一个投标的人
     * @param borrowId
     * @return
     */
    BorrowTenderOutput getLastBorrowTender(Integer borrowId);

    /**
     * 查看投标结果值是否存在 如果不存在 则证明要么已经超过600s或者在请求投标结果最大次数后删除 针对该两种情况均需发送失败通知短信
     * 同时 如果key为空,则默认发送短信而不进行缓存插入 因此直接返回true
     * @param key key
     * @return
     */
    boolean isExists(String key);

    /**
     * 更新产品剩余金额 (减少剩余可投)
     * @param productId 产品id
     * @param tenderMoney 投标金额
     */
    void updateProductResidue(int productId,BigDecimal tenderMoney);

    List<PlanBorrowTenderOutput>  selectTenderListOnOrgReverify(Integer borrowId);

    List<PlanBorrowTenderOutput>  selectTenderListOnDevReverify(Integer borrowId);


    /**
     * 满标复审后奖励
     * 1.积分奖励
     * 2.邀请奖励
     * @param list   投标
     * @param product
     */
    void tenderAward(List<UserCollectionInput> list, BorrowRecheckInput product);



    /**
     * 扫尾并且最大投标 3倍积分
     * @param max 最大投标
     * @param last 最后投标
     * @param product 产品信息
     */
    void maxAndLastTender(BorrowTender max, BorrowTender last, BorrowRecheckInput product);

    /**
     * 扫尾投资 双倍积分
     * @param product
     * @return 减少查询次数
     */
    BorrowTender lastTender(BorrowRecheckInput product);

    /**
     * 投资最多积分 双倍投资积分
     * @param product
     * @return 减少查询次数
     */
    BorrowTender maxTender(BorrowRecheckInput product);

    /**
     * 获取本次投标的基础积分
     * 天标投资奖励 奖励：1积分/5000元投资额
     * 12月份之上的投标奖励 奖励：1积分/1000元投资额
     * 12月份之下的投标奖励 奖励：1积分/2000元投资额
     * @param tenderMoney
     * @param product
     * @return 积分数
     */
    int getBaseScore(double tenderMoney,BorrowRecheckInput product);

    /**
     * 天标投资奖励 奖励：1积分/5000元投资额
     * @param tender
     * @param product
     */
    void dayTender(BorrowTenderOutput tender, BorrowRecheckInput product);

    /**
     * 12月份之上的投标奖励 奖励：1积分/1000元投资额
     * @param tender
     * @param product
     */
    void above12MonthTender(BorrowTenderOutput tender, BorrowRecheckInput product);

    /**
     * 12月份之下的投标奖励 奖励：1积分/2000元投资额
     * @param tender
     * @param product
     */
    void under12MonthTender(BorrowTenderOutput tender, BorrowRecheckInput product);

    /**
     * 手机投资奖励
     * @param tender
     */
    void appTender(BorrowTenderOutput tender);

    /**
     * 首投积分奖励,
     * 首投等额本息奖励,
     * 第二次投标奖励
     * @param user
     * @param product
     */
    void firstOrSecondTender(UserCollectionInput user,BorrowRecheckInput product);

    /**
     * 根据用户id获取抵扣券总收益
     * @param userId
     * @return
     */
    BigDecimal getTenderVoucherCount(String userId);

    /**
     * 分页获取标的投标列表 募集中 回款中 已完结 已转让 转让申请中
     * @param param
     * @return
     */
    PageInfo<ProductTenderVo> getProductTenderByPage(BorrowParam param);

    /**
     * 获取用户所有投标冻结金额
     * @param userId
     * @return
     */
    BigDecimal getUserTenderFrost(String userId);

    public  List<PlanBorrowTenderOutput>  selectTenderListOnDevReverifyPlay(Integer borrowId);
}
