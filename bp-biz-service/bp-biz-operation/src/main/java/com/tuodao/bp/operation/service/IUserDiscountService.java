package com.tuodao.bp.operation.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountTenderInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountQueryOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountTenderOutput;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.model.mq.CouponGrantMqInfo;
import com.tuodao.bp.model.mq.WithDrawTimesMqInfo;

import java.util.List;

/**
 * 我的优惠券接口
 * author hechuan
 * <p>
 * created on 2017/9/21
 * <p>
 * since V1.0.0
 */
public interface IUserDiscountService {
    /**
     * 我的优惠券
     *
     * @param input
     * @return
     */
    PageInfo<UserDiscountOutput> getUserDiscountPagedList(UserDiscountsInput input);

    /**
     * 我的优惠券-不分页
     *
     * @param input
     * @return
     */
    List<UserDiscountOutput> getUserDiscountList(UserDiscountsInput input);

    /**
     * 优惠券发放
     *
     * @param couponGrantMqInfo 消息内容体
     * @param grantCouponFlag   是否发放优惠券
     */
    void couponGrant(CouponGrantMqInfo couponGrantMqInfo, boolean grantCouponFlag);

    /**
     * 根据Id获取优惠券信息
     *
     * @param idInput ID
     * @return 优惠券详情
     */
    UserDiscountOutput getUserDiscountById(IdInput idInput);

    /**
     * 获取注册大礼包优惠券列表
     *
     * @param basePojo 基础信息
     * @return 优惠券列表
     */
    List<UserDiscountOutput> getRegisterWelfareDiscounts(BasePojo basePojo);

    /**
     * 消费免费提现次数
     * @param info
     */
    void consumerWithDrawTimes(WithDrawTimesMqInfo info);

    /**
     * 根据用户ID查询 用户优惠券
     * @param input 用户信息
     * @return 用户优惠券列表
     */
    List<UserDiscountQueryOutput> getUserDiscountQueryList(BasePojo input);

    /**
     * 根据用户ID查询用户免费提现次数
     * @param input 用户信息
     * @return 用户免费提现次数
     */
    UserFreeNumberOutput getUserFreeNumber(BasePojo input);

    /**
     * 用户投资
     * @param input 投资输入参数
     * @return 投资返回参数
     */
    List<UserDiscountTenderOutput> getUserDiscountTenderList(UserDiscountTenderInput input);
}
