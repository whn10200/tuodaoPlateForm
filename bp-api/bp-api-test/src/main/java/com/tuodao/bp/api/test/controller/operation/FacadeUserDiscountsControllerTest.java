package com.tuodao.bp.api.test.controller.operation;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.input.UserDiscountTenderInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.Test;

/**
 * 用户优惠券 controller api test
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
public class FacadeUserDiscountsControllerTest extends OperationTest {

    /**
     * 查询用户优惠券
     */
    @Test
    public void getUserDiscountPagedList(){
        UserDiscountsInput input = new UserDiscountsInput();
        input.setUserId(USER_ID);
        input.setMobile(MOBILE);

        input.setCurrentPage(1);
        input.setPageSize(10);

        /**
         * 是否锁定(1:正常，2：锁定)
         */
        input.setDiscountLock(1);

        /**
         * 优惠券状态（1：可使用，2：已使用，3：已过期）
         */
        input.setDiscountStatus(1);

        /**
         * 类型(1:抵用券,2:加息券)
         */
        input.setDiscountType(1);
        doServiceCalc("op/getUserDiscountPagedList", ContentType.JSON, input);
    }

    /**
     * 根据用户ID查询用户优惠券
     */
    @Test
    public void getUserDiscountQueryList(){
        BasePojo input = new BasePojo();
        input.setUserId("13429108343-miet5xbhj9jcvrtmtrxm");
        doServiceCalc("op/getUserDiscountQueryList", ContentType.JSON, input);
    }

    /**
     * 根据用户ID查询用户免费提现次数
     */
    @Test
    public void getUserFreeNumber(){
        BasePojo input = new BasePojo();
        input.setUserId("13429108343-miet5xbhj9jcvrtmtrxm");
        doServiceCalc("op/getUserFreeNumber", ContentType.JSON, input);
    }

    /**
     * 根据用户ID查询用户投资优惠券
     */
    @Test
    public void getUserDiscountTenderList(){
        UserDiscountTenderInput input = new UserDiscountTenderInput();
        input.setUserId("13429108343-miet5xbhj9jcvrtmtrxm");
        input.setNeedType(2);
        input.setScaleMoney(2000);
        input.setScaleTimeLimit(30);
        input.setRequestType("PC");
        doServiceCalc("op/getUserDiscountTenderList", ContentType.JSON, input);
    }
}
