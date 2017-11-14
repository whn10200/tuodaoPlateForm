package com.tuodao.bp.api.facade.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @description: 投标验证码
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 13:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Configuration
public class CaptchaConfig {


    @Bean(name = "producer")
    public DefaultKaptcha getCaptchaBean(){
        DefaultKaptcha defaultKaptcha=new DefaultKaptcha();
        Properties properties=new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "125");
        properties.setProperty("kaptcha.image.height", "45");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.char.string","abcdefhkmnprstwxy23456789ABCEFGHGKMNPRSTWXY");
        Config config = new Config(properties);

        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
