package com.tuodao.bp.operation.persistence.mapper.biz;

import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户优惠券查询
 * author hechuan
 * <p>
 * created on 2017/9/22
 * <p>
 * since V1.0.0
 */
public interface BizOpUserDiscountMapper {

    /**
     * 分页查询我的优惠券
     *
     * @param input 输入参数，优惠券的相关参数
     * @return 我的优惠券
     */
    List<UserDiscountOutput> getBizUserDiscountPagedList(UserDiscountsInput input);

    /**
     * 根据活动CODE查询用户优惠券
     *
     * @param userId              用户ID
     * @param welfareActivityCode 活动Code
     * @return 优惠券列表
     */
    List<UserDiscountOutput> getUserDiscountListByWelfareActivityCode(@Param("userId") String userId, @Param("welfareActivityCode") String welfareActivityCode);
}
