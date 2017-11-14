package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.business.operation.input.InverstmentInput;
import com.tuodao.bp.model.business.operation.input.InverstmentQueryInput;
import com.tuodao.bp.model.business.operation.output.InverstmentOutput;
import com.tuodao.bp.model.business.operation.output.InverstmentQueryOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 抽奖client
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
@FeignClient(value = "BIZ-OPERATION")
public interface InverstmentClient {

    /**
     * 查询抽奖列表
     * @param input
     * @return 列表内容
     */
    @RequestMapping(value = "/inverst/getInverstQueryList",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    List<InverstmentQueryOutput> getInverstQueryList(InverstmentQueryInput input);


    /**
     * 抽奖
     * @param input
     * @return 抽奖结果
     */
    @RequestMapping(value = "/inverst/getInverstResult",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    InverstmentOutput getInverstResult(InverstmentInput input);
}
