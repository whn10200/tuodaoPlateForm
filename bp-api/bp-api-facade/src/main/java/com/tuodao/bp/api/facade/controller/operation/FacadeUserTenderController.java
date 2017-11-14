package com.tuodao.bp.api.facade.controller.operation;

import com.tuodao.bp.api.facade.client.operation.UserTenderClient;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * author hechuan
 * <p>
 * created on 2017/10/19
 * <p>
 * since V1.0.0
 */
@RequestMapping("/router/op")
@RestController
public class FacadeUserTenderController {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(FacadeUserTenderController.class);

    @Autowired
    private UserTenderClient userTenderClient;

    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    @RequestMapping(value = "/getUserTender", method = RequestMethod.POST)
    public Map<String,TenderVoucherOutput> getUserTender(List<Map<String,Integer>> input){
        logger.info("input : {}",input);
        Map<String, TenderVoucherOutput> userTender = userTenderClient.getUserTender(input);
        logger.info("userTender : {}",userTender);
        return userTender;
    }
}
