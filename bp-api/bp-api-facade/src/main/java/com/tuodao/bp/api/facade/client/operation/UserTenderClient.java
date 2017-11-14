package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * 用户投资-使用优惠券
 * author hechuan
 * <p>
 * created on 2017/10/18
 * <p>
 * since V1.0.0
 */
@FeignClient(value = "BIZ-OPERATION")
public interface UserTenderClient {

    /**
     * 查找用户任务(新手任务,日常任务)
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/userTender/getUserTender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String,TenderVoucherOutput> getUserTender(List<Map<String,Integer>> input);
}
