package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.output.UserOperationStatisOutput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.operation.service.IUserOperationDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户运营数据controller
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/userOperation")
public class UserOperationDataController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserOperationDataController.class);

    @Autowired
    private IUserOperationDataService userOperationDataService;

    /**
     * 交互文档_PC重构0914/index.html#g=1&p=我的帐户-账户总览
     * 获取用户运营数据
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/getUserOperationData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserOperationStatisOutput> getUserOperationData(BasePojo input) {
        logger.info("获取用户运营数据：[{}]", input);
        return userOperationDataService.getUserOperationDataList(input);
    }
}
