package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.business.operation.input.OpBannerManagerInput;
import com.tuodao.bp.model.facade.operation.output.OpBannerManagelListOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description: banner管理 FEIGN
 * Author: yinping
 * Date: 2017/11/3
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface OpAPPBannerClient {
    /**
     * 根据bannerRemark查询PCbanner
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "banner/selectAPPBanner", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OpBannerManagelListOutput> selectBannerByParams(OpBannerManagerInput input);
}
