package com.tuodao.bp.operation.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.output.InviteRecordOutput;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecord;

import java.math.BigDecimal;

/**
 * @description: 用户邀请相关接口
 * @author: mif
 * @date: 2017/9/15
 * @time: 10:53
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IInviteService {

    /**
     * 获取邀请记录
     *
     * @param pagePojo 基础对象
     * @return 分页数据
     */
    PageInfo<InviteRecordOutput> getInviteRecord(PagePojo pagePojo);

    /**
     * 统计用户邀请奖励
     *
     * @param userId 用户ID
     * @return 奖励总额
     */
    BigDecimal getTotalInviteAward(String userId);
}
