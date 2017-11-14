package com.tuodao.bp.api.test.controller.operation;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.input.ScoreExchangeInput;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.Test;

/**
 *
 * 积分专区
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
public class FacadeScoreMallControllerTest extends OperationTest {


    /**
     * 积分兑换专区分页查询
     */
    @Test
    public void getScoreMallPaged(){
        PagePojo input = new PagePojo();

        doServiceCalc("op/getScoreMallPaged", ContentType.JSON,input);
    }

    /**
     * 我的积分-统计
     */
    @Test
    public void getMyScoreStatistic(){
        BasePojo input = new BasePojo();
        input.setUserId(USER_ID);
        input.setMobile(MOBILE);
        doServiceCalc("op/getMyScoreStatistic", ContentType.JSON,input);
    }

    /**
     * 我的积分-兑换
     */
    @Test
    public void scoreExchange(){
        ScoreExchangeInput input = new ScoreExchangeInput();
        input.setUserId(USER_ID);
        input.setMobile(MOBILE);

        /**
         * 积分商城ID
         */
        input.setId(1L);
        /**
         *  兑换数量
         */
        input.setNum(1);
        /**
         * 所需要积分，前端算好，传给我
         * 比如 加息券1张需要100积分，如果传递数量为2，则这里是算好的200积分传到后端
         */
        input.setSumScore(100);
        /**
         * 类型(1:加息券,2:免费提现次数)
         */
        input.setType(1);

        doServiceCalc("op/scoreExchange", ContentType.JSON,input);
    }

    /**
     * 我的积分-明细
     */
    @Test
    public void getMyScoreDetailPaged(){
        PagePojo input = new PagePojo();
        input.setUserId(USER_ID);
        input.setMobile(MOBILE);

        doServiceCalc("op/getMyScoreDetailPaged", ContentType.JSON,input);
    }
}
