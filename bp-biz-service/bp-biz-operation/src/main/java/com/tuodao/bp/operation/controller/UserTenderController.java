package com.tuodao.bp.operation.controller;


import com.tuodao.bp.model.business.operation.input.TenderVoucherInput;
import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import com.tuodao.bp.operation.service.IUserTenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.integration.support.management.graph.LinkNode.Type.input;

/**
 * 用户投资-使用优惠券
 * author hechuan
 * <p>
 * creatd on 2017/10/18
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/userTender")
public class UserTenderController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserTenderController.class);

    @Autowired
    private IUserTenderService userTenderService;

    /**
     * 用户投资-使用优惠券
     * @param input
     * @return
     */
    @RequestMapping(value = "/getUserTender",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,TenderVoucherOutput> getUserTender(List<Map<String,Integer>> inputList){
        logger.info("用户投资-使用优惠券 - List<TenderVoucherInput> ： [{}]",inputList);
        return userTenderService.getUserTender(inputList);
    }
}
