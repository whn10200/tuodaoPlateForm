package com.tuodao.bp.operation.persistence.mapper.biz;

import java.math.BigDecimal;

/**
 * @description: 自定义邀请相关
 * @author: mif
 * @date: 2017/11/10
 * @time: 11:27
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BizOpInviteMapper {
    /**
     * 统计用户邀请奖励
     *
     * @param userId 用户ID
     * @return 奖励总额
     */
    BigDecimal getTotalInviteAward(String userId);
}
