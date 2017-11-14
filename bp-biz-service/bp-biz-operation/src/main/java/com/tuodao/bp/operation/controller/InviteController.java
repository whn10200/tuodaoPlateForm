package com.tuodao.bp.operation.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.output.InviteRecordOutput;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecord;
import com.tuodao.bp.operation.service.IInviteService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description: 用户邀请控制类
 * @author: mif
 * @date: 2017/9/15
 * @time: 10:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "operation/invite")
public class InviteController {

    @Resource
    private IInviteService userInviteService;

    /**
     * 获取邀请记录
     *
     * @param pagePojo 基础分页对象
     * @return 分页数据
     */
    @RequestMapping(value = "getInviteRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<InviteRecordOutput> getInviteRecord(PagePojo pagePojo) {
        return userInviteService.getInviteRecord(pagePojo);
    }

    /**
     * 统计用户邀请奖励
     *
     * @param basePojo 基础对象
     * @return 奖励总额
     */
    @RequestMapping(value = "getTotalInviteAward", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BigDecimal getTotalInviteAward(BasePojo basePojo) {
        return userInviteService.getTotalInviteAward(basePojo.getUserId());
    }
}
