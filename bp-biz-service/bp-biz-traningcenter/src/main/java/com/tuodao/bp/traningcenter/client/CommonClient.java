package com.tuodao.bp.traningcenter.client;

import com.tuodao.bp.model.business.common.input.EmailInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tookbra
 * @date 2017/11/8
 * @description
 */

@FeignClient("bp-common-ms")
public interface CommonClient {

    @Async
    @RequestMapping(value = "common/sendEmail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendEmail(EmailInput input);
}
