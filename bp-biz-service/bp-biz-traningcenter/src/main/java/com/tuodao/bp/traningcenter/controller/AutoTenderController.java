package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.output.AutoTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.traningcenter.service.AutoTenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 14:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class AutoTenderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoTenderController.class);

    @Autowired
    private AutoTenderService autoTenderService;

    /**
     * 保存自动投标设置信息 新增自动投标日志信息
     * @param param
     */
    @RequestMapping(value = "/tender/saveAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveAutoTender(AutoTenderParam param){
        LOGGER.debug("保存自动投标设置信息入参:{}",param);
        autoTenderService.saveAutoTender(param);
    }


    /**
     * 根据用户ID获取自动投标设置信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tender/getUserAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AutoTenderVo getUserAutoTender(@RequestParam("userId")String userId){
        LOGGER.debug("根据用户ID获取自动投标设置信息入参:{}",userId);
        return autoTenderService.getUserAutoTender(userId);
    }

    /**
     * 关闭自动投标
     * @param userId
     */
    @RequestMapping(value = "/tender/closeAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void closeAutoTender(@RequestParam("userId")String userId){
        LOGGER.debug("关闭自动投标入参:{}",userId);
        autoTenderService.closeAutoTender(userId);
    }
    /**
     * 已开启自动投标的人数
     * @return
     */
    @RequestMapping(value = "/tender/getTotalAutoTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public long getTotalAutoTender(){
        long totalAutoTender = autoTenderService.getTotalAutoTender();
        LOGGER.debug("获取已开启自动投标的人数:{}",totalAutoTender);
        return totalAutoTender;
    }

    /**
     * 获取对应条数自动投标列表
     * @param limit 多少条
     * @return
     */
    @RequestMapping(value = "/tender/getAutoTenderList",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<AutoTenderOutput> getList(@RequestParam("limit") Integer limit){
        return autoTenderService.getList(limit);
    }

    /**
     * 分页获取自动投标成功列表
     * @param pagePojo
     * @return
     */
    @RequestMapping(value = "/tender/getAutoTenderListByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<AutoTenderListVo> getAutoTenderListByPage(PagePojo pagePojo){
        return autoTenderService.getAutoTenderListByPage(pagePojo);
    }

    /**
     * 获取自动投标的详情
     * @param userId
     * @param autoTenderId
     * @return
     */
    @RequestMapping(value = "/tender/getAutoTenderDetail",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AutoTenderListVo getAutoTenderDetail(@RequestParam("userId") String userId,@RequestParam("autoTenderId") Integer autoTenderId){
        LOGGER.debug("获取自动投标详情信息入参,userId:{},autoTenderId:{}",userId,autoTenderId);
        return autoTenderService.getAutoTenderDetail(userId,autoTenderId);
    }
}
