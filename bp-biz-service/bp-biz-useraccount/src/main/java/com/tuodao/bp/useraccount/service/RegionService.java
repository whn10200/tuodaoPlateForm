package com.tuodao.bp.useraccount.service;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.useraccount.persistence.model.biz.RegionInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description: 地区工具类
 * @author: mif
 * @date: 2017/9/21
 * @time: 14:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class RegionService {

    private static Logger logger = LoggerFactory.getLogger(RegionService.class);

    private static final String REQUEST_URL = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=";

    @Resource
    private RestTemplate restTemplate;

    public RegionInfo getRegion(String ip) {

        String url = REQUEST_URL + ip;
        String result = restTemplate.getForEntity(url, String.class).getBody();
        if (StringUtils.isEmpty(result)) {
            return new RegionInfo();
        }
        logger.info("getRegion result={},ip={}", result, ip);
        try {
            return JSON.parseObject(result, RegionInfo.class);
        } catch (Exception e) {
            return new RegionInfo();
        }
    }

}
