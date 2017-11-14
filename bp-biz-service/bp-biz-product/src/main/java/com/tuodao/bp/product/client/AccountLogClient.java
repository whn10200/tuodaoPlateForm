package com.tuodao.bp.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tuodao.bp.model.business.traningcenter.input.AccountLogExtInput;

/**
 * @description: 资金记录接口
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface AccountLogClient {
   
    /**
     * 其他服务调用 添加资金记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/addAccountLog",consumes = MediaType.APPLICATION_JSON_VALUE)
    void addAccountLog(AccountLogExtInput input);

}
