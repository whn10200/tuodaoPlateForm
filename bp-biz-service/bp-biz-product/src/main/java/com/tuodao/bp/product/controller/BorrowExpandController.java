package com.tuodao.bp.product.controller;

import com.tuodao.bp.model.business.product.output.BorrowExpandOutput;
import com.tuodao.bp.product.db.model.basic.BorrowExpand;
import com.tuodao.bp.product.service.IBorrowExpandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

/**
 * @Author wuchengjie
 * @Date 2017/11/10 0010 16:01
 * @Introduction
 */

@RestController
@RequestMapping("/borrowExpand")
public class BorrowExpandController {


    @Autowired
    private IBorrowExpandService expandService;



    /**
     * app标的详细信息
     * @param Pcode
     * @return
     */
    @RequestMapping(value = "/getBorrowExpandByPcode" ,produces = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    public BorrowExpandOutput getBorrowExpandByPcode(String Pcode){
         return expandService.getBorrowExpandByPcode(Pcode);
    }

    @RequestMapping(value="/getBorrowExpandById",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowExpandOutput getBorrowExpandById(Integer id){
        return expandService.getBorrowExpandById(id);
    }

    @RequestMapping(value="/getExplaindTypeById",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer getExplaindTypeById(Integer id){
        /** 项目说明类型 0：抵押1:质押 2:4s店合作标 **/
        BorrowExpandOutput output = expandService.getBorrowExpandById(id);
        Integer type =  output.getExplaindType();
        return type;
    }



}
