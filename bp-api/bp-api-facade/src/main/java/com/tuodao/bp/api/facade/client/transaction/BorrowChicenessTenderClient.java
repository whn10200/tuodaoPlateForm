package com.tuodao.bp.api.facade.client.transaction;

import com.tuodao.bp.api.facade.client.DemoServiceFallback;
import com.tuodao.bp.model.business.traningcenter.output.ChicenessTenderOutput;
import com.tuodao.bp.model.traningcenter.input.ChoicenessTenderInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:客户端调用
 * @author: wuzf
 * @date: 2017/9/11 0011.
 * @time: 11:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value="BIZ-TRANING-CENTER")
public interface BorrowChicenessTenderClient {

    /**
     * 生成投资记录
     * @param choicenessTenderInput
     * @return int
     */
    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/insertChoiceness", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public int insertChoiceness(ChoicenessTenderInput choicenessTenderInput, @RequestParam("request") HttpServletRequest request);

    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/selectUnmatchingList", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    List<ChicenessTenderOutput> getUnmatchingList();
}
