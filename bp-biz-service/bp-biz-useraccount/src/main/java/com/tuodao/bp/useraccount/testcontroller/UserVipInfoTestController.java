package com.tuodao.bp.useraccount.testcontroller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.useraccount.service.IUserVipInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * User: zkai
 * Date: 2017/9/19
 * Time: 20:13
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/demo")
public class UserVipInfoTestController {

    @Autowired
    private IUserVipInfoService userVipInfoService;

    /**
     * 添加账户累计收益明细
     * @param input
     */
    @RequestMapping(value="/updateVipLevel",method= RequestMethod.POST)
    @ResponseBody
    public void updateVipLevel(BasePojo input) {
        userVipInfoService.updateVipLevel(input.getUserId());
    }
}
