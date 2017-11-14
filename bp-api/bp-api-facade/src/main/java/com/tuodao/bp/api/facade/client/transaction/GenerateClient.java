package com.tuodao.bp.api.facade.client.transaction;

import com.tuodao.bp.model.BasePojo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/13
 * @time: 18:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface GenerateClient {

    /**
     * 生成id
     * @param pojo
     * @return
     */
    @RequestMapping(value = "/getGenerateId",consumes = MediaType.APPLICATION_JSON_VALUE)
    String getGenerateId(BasePojo pojo);
}
