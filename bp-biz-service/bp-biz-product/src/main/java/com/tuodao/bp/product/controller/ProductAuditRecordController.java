package com.tuodao.bp.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.model.business.product.output.ProductAuditRecordOutput;
import com.tuodao.bp.product.service.IProductAuditRecordService;

/**
 * @description: 产品审核记录
 * @author: wuchengjie
 * @date: 2017/9/27
 * @time: 16:51
 * @copyright: 拓道金服 Copyright (c) 2017
 * */
@RestController
@RequestMapping("/productAuditRecord")
public class ProductAuditRecordController {

    @Autowired
    private IProductAuditRecordService service;


    @RequestMapping(value="/getAuditRecordList",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public   List<ProductAuditRecordOutput>  getBorrowPlanTransfer(List<Integer> id) {
         return service.getRecordOutput(id);
    }

}
