package com.tuodao.bp.useraccount.config;

import com.tuodao.bp.useraccount.properties.TomcatProperties;
import com.tuodao.bp.utils.StringUtil;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 定制专属tomcat配置
 * author hechuan
 * <p>
 * created on 2017/11/8
 * <p>
 * since V1.0.0
 */
@Configuration
@EnableConfigurationProperties(value = {TomcatProperties.class})
public class WebServerConfiguration {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(WebServerConfiguration.class);

    @Autowired
    private TomcatProperties tomcatProperties;

    @Bean
    public EmbeddedServletContainerFactory createEmbeddedServletContainerFactory(ConfigurableEnvironment env){
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setProtocol("org.apache.coyote.http11.Http11Nio2Protocol");
        tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer(){

            /**
             * Customize the connector.
             *
             * @param connector the connector to customize
             */
            @Override
            public void customize(Connector connector) {
                logger.info("定制tomcat http请求协议及相关的参数开始....");
                Http11Nio2Protocol protocol = (Http11Nio2Protocol)connector.getProtocolHandler();
                protocol.setMaxConnections(StringUtil.getValue(tomcatProperties.getMaxConnections(),int.class,20000));
                protocol.setMinSpareThreads(StringUtil.getValue(tomcatProperties.getMinSpareThreads(),int.class,50));
                protocol.setMaxThreads(StringUtil.getValue(tomcatProperties.getMaxThreads(),int.class,500));
                protocol.setAcceptorThreadCount(StringUtil.getValue(tomcatProperties.getAcceptCount(),int.class,500));
                protocol.setConnectionTimeout(StringUtil.getValue(tomcatProperties.getConnectionTimeout(),int.class,20000));
                logger.info("tomcatProperties : [{}]",tomcatProperties);
                logger.info("定制tomcat http请求协议及相关的参数结束....");
            }
        });
        return tomcatFactory;
    }

}
