package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.traningcenter.service.CreditAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tookbra
 * @date 2017/10/25
 * @description
 */
@RestController
@RequestMapping("/mq")
public class MqTestController {

    @Autowired
    CreditAssignmentService creditAssignmentService;

    @PostMapping("/test")
    public void test(BasePojo basePojo) {
        creditAssignmentService.test();
    }
}
