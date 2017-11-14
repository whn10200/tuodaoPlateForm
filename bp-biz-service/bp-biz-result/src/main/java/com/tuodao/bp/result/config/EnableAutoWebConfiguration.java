package com.tuodao.bp.result.config;

import java.util.List;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.tuodao.bp.result.constant.EncodeConstant;
import com.tuodao.bp.result.interceptor.BizMethodArgumentInterceptor;

@Configuration
public class EnableAutoWebConfiguration extends WebMvcConfigurerAdapter{

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(EncodeConstant.ENCODING);
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public BizMethodArgumentInterceptor bizMethodArgumentInterceptor() {
		return new BizMethodArgumentInterceptor();
	}

	/**
	 * controller 参数解析
	 *
	 * @param argumentResolvers
	 *            参数解析列表
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(bizMethodArgumentInterceptor());
	}
}
