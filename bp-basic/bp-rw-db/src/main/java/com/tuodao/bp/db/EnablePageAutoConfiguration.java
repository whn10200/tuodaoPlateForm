package com.tuodao.bp.db;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.mysql.MySQLStatementInterceptorManagementBean;
import com.github.pagehelper.PageHelper;

/**
 * 分页插件
 * 
 * @author hechuan
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class EnablePageAutoConfiguration implements EnvironmentAware {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(EnablePageAutoConfiguration.class);

	public static String SERVICE_NAME = "db-support-service";

	@Bean
	public PageHelper pageHelper() {
		logger.info("注册MyBatis分页插件PageHelper------begin");

		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum", "true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);

		logger.info("注册MyBatis分页插件PageHelper------successfully");
		return pageHelper;
	}

	@Override
	public void setEnvironment(Environment environment) {
		RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "spring.application.");
		String property = propertyResolver.getProperty("name");
		SERVICE_NAME = property;
	}

	// @Bean(destroyMethod="close")
	public MySQLStatementInterceptorManagementBean mySQLStatementInterceptorManagementBean() {
		logger.info("call db service name is {}",SERVICE_NAME);
		Brave brave = new Brave.Builder(SERVICE_NAME).build();
		MySQLStatementInterceptorManagementBean mySQLStatementInterceptorManagementBean = new MySQLStatementInterceptorManagementBean(brave.clientTracer());
		return mySQLStatementInterceptorManagementBean;
	}
}