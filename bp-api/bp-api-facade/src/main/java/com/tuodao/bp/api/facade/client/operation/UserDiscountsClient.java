package com.tuodao.bp.api.facade.client.operation;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountTenderInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountQueryOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountTenderOutput;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 聚合-用户优惠券
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
@FeignClient(value = "BIZ-OPERATION")
public interface UserDiscountsClient {

    /**
     * 查询我的优惠券分页列表
     * 对应页面：交互文档_PC重构0914/index.html#g=1&p=我的福利-我的优惠券-抵用券
     *
     * @param input 状态，类型等
     * @return PageInfo<UserDiscountOutput> 翻页信息
     */
    @RequestMapping(value = "/userDiscounts/getUserDiscountPagedList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<UserDiscountOutput> getUserDiscountPagedList(UserDiscountsInput input);

    /**
     * 获取用户优惠券列表,不翻页
     * @param input
     * @return
     */
    @RequestMapping(value = "/userDiscounts/getUserDiscountList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDiscountOutput> getUserDiscountList(UserDiscountsInput input);

    /**
     * 根据ID 获取券详情
     *
     * @param idInput 券ID
     * @return 详情
     */
    @RequestMapping(value = "/userDiscounts/getUserDiscountById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDiscountOutput getUserDiscountById(IdInput idInput);

    /**
     * 获取注册大礼包优惠券列表
     *
     * @param basePojo 基础基类
     * @return 优惠券列表
     */
    @RequestMapping(value = "/userDiscounts/getRegisterWelfareDiscounts", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDiscountOutput> getRegisterWelfareDiscounts(BasePojo basePojo);


    /**
     * 根据用户ID查询用户可用优惠券数量（加息券，抵用券）
     * @param input 用户信息
     * @return 优惠券列表
     */
    @RequestMapping(value = "/userDiscounts/getUserDiscountQueryList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDiscountQueryOutput> getUserDiscountQueryList(BasePojo input);


    /**
     * 根据用户ID查询用户免费提现次数
     * @param input 用户信息
     * @return 用户免费提现次数
     */
    @RequestMapping(value = "/userDiscounts/getUserFreeNumber", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserFreeNumberOutput getUserFreeNumber(BasePojo input);

    /**
     * 用户投资
     * @param input 投资输入参数
     * @return 投资返回参数
     */
    @RequestMapping(value = "/userDiscounts/getUserDiscountTenderList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDiscountTenderOutput> getUserDiscountTenderList(UserDiscountTenderInput input);
}
