package com.tuodao.bp.api.facade.controller.operation;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.InviteClient;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.output.InviteRecordOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 聚合层邀请相关控制
 * @author: mif
 * @date: 2017/10/9
 * @time: 17:59
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router/op")
public class FacadeInviteController {
    private static Logger logger = LoggerFactory.getLogger(FacadeInviteController.class);

    @Resource
    private InviteClient inviteClient;


    /**
     * 获取邀请记录
     *
     * @param pagePojo 分页信息
     * @return 查询结果
     */
    @RequestMapping(value = "getInviteRecord", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<PageInfo<InviteRecordOutput>> getInviteRecord(PagePojo pagePojo) {
        logger.info("获取邀请记录 input={}", pagePojo);

        PageInfo<InviteRecordOutput> pageInfo = inviteClient.getInviteRecord(pagePojo);
        return RespResult.<PageInfo<InviteRecordOutput>>create().setContent(pageInfo);
    }
}
