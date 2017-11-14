package com.tuodao.bp.operation.controller;


import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountTenderInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountQueryOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountTenderOutput;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.operation.service.IUserDiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 我的优惠券管理
 * author hechuan
 * <p>
 * created on 2017/9/20
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/userDiscounts")
public class UserDiscountsController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserDiscountsController.class);

    @Autowired
    private IUserDiscountService userDiscountService;

    /**
     * 查询我的优惠券分页列表
     * 对应页面：交互文档_PC重构0914/index.html#g=1&p=我的福利-我的优惠券-抵用券
     *
     * @param input 状态，类型等
     * @return PageInfo<UserDiscountOutput> 翻页信息
     */
    @RequestMapping(value = "/getUserDiscountPagedList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserDiscountOutput> getUserDiscountPagedList(UserDiscountsInput input) {
        logger.info("UserDiscountsInput : [{}]", input);
        PageInfo<UserDiscountOutput> userDiscountPagedList = userDiscountService.getUserDiscountPagedList(input);
        logger.debug("getUserDiscountPagedList return success...");
        return userDiscountPagedList;
    }

    /**
     * 查询我的优惠券列表
     * 对应页面：交互文档_PC重构0914/index.html#g=1&p=我的福利-我的优惠券-抵用券
     *
     * @param input 状态，类型等
     * @return List<UserDiscountOutput> 翻页信息
     */
    @RequestMapping(value = "/getUserDiscountList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDiscountOutput> getUserDiscountList(UserDiscountsInput input) {
        logger.info("UserDiscountsInput : [{}]", input);
        List<UserDiscountOutput> userDiscountList = userDiscountService.getUserDiscountList(input);
        logger.debug("userDiscountList return success...[{}]",userDiscountList);
        return userDiscountList;
    }

    /**
     * 根据用户ID查询用户可用优惠券数量（加息券，抵用券）
     * @param input 用户信息
     * @return 优惠券列表
     */
    @RequestMapping(value = "/getUserDiscountQueryList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDiscountQueryOutput> getUserDiscountQueryList(BasePojo input){
        logger.info("getUserDiscountQueryList.input :[{}]",input);
        return userDiscountService.getUserDiscountQueryList(input);
    }

    /**
     * 用户投资
     * @param input 投资输入参数
     * @return 投资返回参数
     */
    @RequestMapping(value = "/getUserDiscountTenderList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDiscountTenderOutput> getUserDiscountTenderList(UserDiscountTenderInput input){
        logger.info("用户投资查询优惠券,input:[{}]",input);
        return userDiscountService.getUserDiscountTenderList(input);
    }


    /**
     * 根据用户ID查询用户免费提现次数
     * @param input 用户信息
     * @return 用户免费提现次数
     */
    @RequestMapping(value = "/getUserFreeNumber", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserFreeNumberOutput getUserFreeNumber(BasePojo input){
        logger.info("getUserFreeNumber.input :[{}]",input);
        return userDiscountService.getUserFreeNumber(input);
    }

    /**
     * 根据ID 获取用户优惠券信息
     *
     * @param idInput id
     * @return 优惠券信息
     */
    @RequestMapping(value = "/getUserDiscountById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDiscountOutput getUserDiscountById(IdInput idInput) {
        return userDiscountService.getUserDiscountById(idInput);
    }

    /**
     * 获取注册大礼包优惠券列表
     *
     * @param basePojo 基础POJO
     * @return 优惠券列表
     */
    @RequestMapping(value = "/getRegisterWelfareDiscounts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDiscountOutput> getRegisterWelfareDiscounts(BasePojo basePojo) {
        return userDiscountService.getRegisterWelfareDiscounts(basePojo);
    }

}
