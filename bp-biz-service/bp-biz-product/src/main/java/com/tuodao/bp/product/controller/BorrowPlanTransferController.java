package com.tuodao.bp.product.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowPlanTransferInput;
import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.product.service.IBorrowPlanTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
  * com.tuodao.bp.product.controller.BorrowPlanTransferController
  * @类描述: 理财债券转让标的
  * @作者 lujing
  * @时间 2017/9/14 0014
  * ======================修改==================
  * @修改者
  * @修改内容: TODO
  * @修改时间 yyyy-MM-dd HH:mm:ss
  * ============================================
  **/
@RestController
@RequestMapping("/borrowPlanTransfer")
public class BorrowPlanTransferController {

    @Autowired
    private IBorrowPlanTransferService service;

    /**
      * com.tuodao.bp.product.controller.BorrowPlanTransferController#
      * @参数
      * @方法描述: 债转标列表-带分页
      * @作者 lujing
      * @时间 2017/9/14 0014
      * @拓道金服 Copyright (c) 2017
      * ======================修改==================
      * @修改者
      * @修改内容: TODO
      * @修改时间 yyyy-MM-dd HH:mm:ss
      * ============================================
      **/
    @RequestMapping(value="/getBorrowPlanTransPage",method= RequestMethod.POST)
    public PageInfo<BorrowPlanTransferOutput> getBorrowPlanTransPage(BorrowPlanTransferInput in) {
        return service.getBorrowPlanTransferPage(in);
    }
    /**
      * com.tuodao.bp.product.controller.BorrowPlanTransferController#
      * @参数
      * @方法描述: 债转标列表-无分页
      * @作者 lujing
      * @时间 2017/9/14 0014
      * @拓道金服 Copyright (c) 2017
      * ======================修改==================
      * @修改者
      * @修改内容: TODO
      * @修改时间 yyyy-MM-dd HH:mm:ss
      * ============================================
      **/
    @RequestMapping(value="/getBorrowPlanTransList",method= RequestMethod.POST)
    public List<BorrowPlanTransferOutput> getBorrowPlanTransList(BorrowPlanTransferInput in) {
        return service.getBorrowPlanTransferList(in);
    }


    @RequestMapping(value="/getBorrowPlanTransList",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public BorrowPlanTransferOutput getBorrowPlanTransfer( Integer id) {
        return service.getBorrowPlanTransfer(id);
    }



        /**
         * 查询超过48小时的理财计划债权 用于内部账号购买
         * @return
         */
    @RequestMapping(value="/getOvertimeTransferList",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BorrowPlanTransferOutput> getOvertimeTransferList() {
        return service.getOvertimeTransferList();
    }


    /**
     * 查询理财计划下需要复审转让标的
     */
    @RequestMapping(value = "/getPlanReverifyTransferList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    List<BorrowPlanTransferOutput> getPlanReverifyTransferList(){
        return service.getPlanReverifyTransferList();
    }
}
