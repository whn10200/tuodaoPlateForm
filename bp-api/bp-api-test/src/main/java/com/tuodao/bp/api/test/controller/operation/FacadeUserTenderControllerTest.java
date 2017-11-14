package com.tuodao.bp.api.test.controller.operation;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tuodao.bp.model.business.operation.input.TenderVoucherInput;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 用户优惠券 - 投资 controller api test
 * author hechuan
 * <p>
 * created on 2017/10/19
 * <p>
 * since V1.0.0
 */
public class FacadeUserTenderControllerTest extends OperationTest {

    /**
     * 查询用户优惠券
     */
    @Test
    public void getUserTender(){

        List<TenderVoucherInput> inputList = Lists.newArrayList();

        TenderVoucherInput input = new TenderVoucherInput();
        input.setVoucherId(1);
        input.setTenderId(1);
        inputList.add(input);

        TenderVoucherInput input1 = new TenderVoucherInput();
        input1.setVoucherId(2);
        input1.setTenderId(2);
        inputList.add(input1);

        TenderVoucherInput input2 = new TenderVoucherInput();
        input2.setVoucherId(3);
        input2.setTenderId(3);
        inputList.add(input2);

        TenderVoucherInput input3 = new TenderVoucherInput();
        input3.setTenderId(3);
        inputList.add(input3);

        doServiceCalc("op/getUserTender", ContentType.JSON, inputList);
    }


    /**
     * 查询用户优惠券
     */
    @Test
    public void getUserTenderMap(){

        List<Map<String,Integer>> inputList = Lists.newArrayList();

        Map<String,Integer> input = Maps.newHashMap();
        input.put("voucherId",1);
        input.put("tenderId",1);
        inputList.add(input);

        Map<String,Integer> input1 = Maps.newHashMap();
        input1.put("voucherId",2);
        input1.put("tenderId",2);
        inputList.add(input1);

        Map<String,Integer> input2 = Maps.newHashMap();
        input2.put("voucherId",3);
        input2.put("tenderId",3);
        inputList.add(input2);

        Map<String,Integer> input3 = Maps.newHashMap();
        input3.put("voucherId",3);
        input3.put("tenderId",3);
        inputList.add(input3);

        doServiceCalc("op/getUserTender", ContentType.JSON, inputList);
    }
}
