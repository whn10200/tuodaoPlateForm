package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.business.operation.input.OpContentInput;
import com.tuodao.bp.model.business.operation.input.OpContentTitleInput;
import com.tuodao.bp.model.facade.operation.output.OpContentManagelListOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description: 内容管理 FEIGN
 * Author: yinping
 * Date: 2017/11/3
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface OpContentClient {
    /**
     * 根据contentRemark查询标题列表
     * @param input
     * @return
     */
    @RequestMapping(value = "content/selectContentTitle", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OpContentManagelListOutput> selectContentTitleByContentRemark(OpContentTitleInput input);

    /**
     * 根据contentId查询内容管理
     * @param input
     * @return
     */
    @RequestMapping(value = "content/selectContent", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OpContentManagelListOutput> selectContentByContentId(OpContentInput input);
}
