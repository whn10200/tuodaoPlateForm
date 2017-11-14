package com.tuodao.bp.api.facade.controller.operation;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountTenderInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountQueryOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountTenderOutput;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 聚合-用户优惠券controller
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/router/op")
public class FacadeUserDiscountsController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(FacadeUserDiscountsController.class);

    @Autowired
    private UserDiscountsClient userDiscountsClient;

    /**
     * 查询我的优惠券分页列表
     * 对应页面：交互文档_PC重构0914/index.html#g=1&p=我的福利-我的优惠券-抵用券
     *
     * @param input 状态，类型等
     * @return PageInfo<UserDiscountOutput> 翻页信息
     */
    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getUserDiscountPagedList", method = RequestMethod.POST)
    public RespResult<PageInfo<UserDiscountOutput>> getUserDiscountPagedList(UserDiscountsInput input) {
        logger.info("聚合-优惠券 - 查询我的优惠券分页列表 - input : [{}]", input);
        PageInfo<UserDiscountOutput> userDiscountPagedList = userDiscountsClient.getUserDiscountPagedList(input);

        logger.info("分发到业务scoreMallClient成功，查询我的优惠券分页列表：[{}]", userDiscountPagedList);
        return RespResult.<PageInfo<UserDiscountOutput>>create().setContent(userDiscountPagedList);
    }

    /**
     * 优惠券详情
     *
     * @param idInput id
     * @return 详情
     */
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getUserDiscountById", method = RequestMethod.POST)
    public RespResult<UserDiscountOutput> getUserDiscountById(IdInput idInput) {
        logger.info("聚合层，根据优惠券Id获取详情，idInput={}", idInput);
        return RespResult.<UserDiscountOutput>create().setContent(userDiscountsClient.getUserDiscountById(idInput));
    }


    /**
     * 获取注册大礼包优惠券列表
     *
     * @param basePojo 基础基类
     * @return 优惠券列表
     */
    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getRegisterWelfareDiscounts", method = RequestMethod.POST)
    public RespResult<List<UserDiscountOutput>> getRegisterWelfareDiscounts(BasePojo basePojo) {
        logger.info("聚合层，获取注册大礼包优惠券列表，basePojo={}", basePojo);
        return RespResult.<List<UserDiscountOutput>>create().setContent(userDiscountsClient.getRegisterWelfareDiscounts(basePojo));
    }


    /**
     * 根据用户ID查询用户可用优惠券数量（加息券，抵用券）
     *
     * @param input 用户信息
     * @return 优惠券列表
     */
    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getUserDiscountQueryList", method = RequestMethod.POST)
    public RespResult<List<UserDiscountQueryOutput>> getUserDiscountQueryList(BasePojo input) {
        logger.info("聚合-优惠券 - 查询我的优惠券列表 - input : [{}]", input);
        List<UserDiscountQueryOutput> userDiscountQueryList = userDiscountsClient.getUserDiscountQueryList(input);

        logger.info("分发到业务userDiscountsClient成功，查询我的优惠券列表：[{}]", userDiscountQueryList);
        return RespResult.<List<UserDiscountQueryOutput>>create().setContent(userDiscountQueryList);
    }


    /**
     * 根据用户ID查询用户免费提现次数
     *
     * @param input 用户信息
     * @return 用户免费提现次数
     */
    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getUserFreeNumber", method = RequestMethod.POST)
    public RespResult<UserFreeNumberOutput> getUserFreeNumber(BasePojo input) {
        logger.info("聚合 - 根据用户ID查询用户免费提现次数 - input : [{}]", input);
        UserFreeNumberOutput userFreeNumber = userDiscountsClient.getUserFreeNumber(input);

        logger.info("分发到业务userDiscountsClient成功，查询我的免费提现次数：[{}]", userFreeNumber);
        return RespResult.<UserFreeNumberOutput>create().setContent(userFreeNumber);
    }

    /**
     * 用户投资
     * @param input 投资输入参数
     * @return 投资返回参数
     */
    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getUserDiscountTenderList", method = RequestMethod.POST)
    public RespResult<List<UserDiscountTenderOutput>> getUserDiscountTenderList(UserDiscountTenderInput input) {
        logger.info("聚合- 用户投资 - 查询我的优惠券列表 - input : [{}]", input);
        List<UserDiscountTenderOutput> getUserDiscountTenderList = userDiscountsClient.getUserDiscountTenderList(input);

        logger.info("分发到业务userDiscountsClient成功，查询我的投资优惠券列表：[{}]", getUserDiscountTenderList);
        return RespResult.<List<UserDiscountTenderOutput>>create().setContent(getUserDiscountTenderList);
    }

}
