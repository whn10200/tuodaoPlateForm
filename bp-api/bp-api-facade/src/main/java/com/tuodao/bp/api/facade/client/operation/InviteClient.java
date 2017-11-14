package com.tuodao.bp.api.facade.client.operation;

import com.github.pagehelper.PageInfo;
import com.sun.xml.internal.rngom.parse.host.Base;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.output.InviteRecordOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

/**
 * @description: 邀请相关 FEIGN
 * @author: mif
 * @date: 2017/10/9
 * @time: 18:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface InviteClient {

    /**
     * 分页查询邀请记录
     *
     * @param pagePojo
     * @return
     */
    @RequestMapping(value = "operation/invite/getInviteRecord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<InviteRecordOutput> getInviteRecord(PagePojo pagePojo);

    /**
     * 统计用户邀请奖励
     *
     * @param basePojo 基础对象
     * @return 奖励总额(元)
     */
    @RequestMapping(value = "operation/invite/getTotalInviteAward", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BigDecimal getTotalInviteAward(BasePojo basePojo);
}
