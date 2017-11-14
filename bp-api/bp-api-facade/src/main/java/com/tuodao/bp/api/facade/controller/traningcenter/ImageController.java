package com.tuodao.bp.api.facade.controller.traningcenter;

import com.google.code.kaptcha.Producer;
import com.tuodao.bp.cache.basic.traningcenter.SessionCache;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.enums.AccessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @description: 图形验证码
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 13:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Controller
@RequestMapping("/router")
public class ImageController {

    @Autowired
    private Producer producer;

    @Autowired
    private SessionCache sessionCache;


    /**
     * 验证码,前后台均可以使用
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/captcha")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public String getCaptcha(HttpServletRequest request, HttpServletResponse response, BasePojo pojo) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("CacheCreate-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = producer.createText();
        System.out.println("******************投标验证码是: " + capText + "******************");

        String accessId = request.getHeader(TenderConstant.ACCESS_ID);

        sessionCache.putSession(accessId,capText);

        BufferedImage bi = producer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.flush();
        out.close();
        return null;
    }
}
