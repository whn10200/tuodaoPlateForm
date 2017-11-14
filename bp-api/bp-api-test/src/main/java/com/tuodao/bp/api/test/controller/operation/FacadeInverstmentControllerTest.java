package com.tuodao.bp.api.test.controller.operation;

import com.tuodao.bp.model.business.operation.input.InverstmentInput;
import com.tuodao.bp.model.business.operation.input.InverstmentQueryInput;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.Test;

/**
 * 抽奖相关controller api test
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
public class    FacadeInverstmentControllerTest extends OperationTest {


    /**
     * 查询获奖列表
     */
    @Test
    public void getInverstQueryList(){
        InverstmentQueryInput input = new InverstmentQueryInput();

        input.setUserId(USER_ID);
        input.setMobile(MOBILE);

        // 抽奖种类（1：10积分，2：100积分）
        input.setInverstType(1);
        doServiceCalc("op/getInverstQueryList", ContentType.JSON,input);
    }

    /**
     * 抽奖
     */
    @Test
    public void getInverstResult(){
        InverstmentInput input = new InverstmentInput();

        input.setUserId(USER_ID);
        input.setMobile(MOBILE);

        // 抽奖种类（1：10积分，2：100积分）
        input.setInverstType(1);
        input.setScore(10);
        doServiceCalc("op/getInverstResult", ContentType.JSON,input);
    }

}
