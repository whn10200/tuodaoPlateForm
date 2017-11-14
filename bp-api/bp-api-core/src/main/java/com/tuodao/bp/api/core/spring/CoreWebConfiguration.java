package com.tuodao.bp.api.core.spring;

import com.tuodao.bp.api.core.constant.RequestConstant;
import com.tuodao.bp.api.core.handler.ExceptionHandler;
import com.tuodao.bp.api.core.log.DbLogRecordHandler;
import com.tuodao.bp.cache.basic.AccessCache;
import com.tuodao.bp.cache.basic.BasicDataDao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
public class CoreWebConfiguration extends WebMvcConfigurerAdapter {

	/*@Bean
	@Primary
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		// builder.serializationInclusion(JsonInclude.Include.NON_NULL);
		//builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE);
		builder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return builder;
	}*/

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding(RequestConstant.ENCODING);
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	@Bean
	public CoreHandlerInterceptor coreHandlerInterceptor() {
		return new CoreHandlerInterceptor();
	}

	@Bean
	public LogHandlerInterceptor logHandlerInterceptor() {
		return new LogHandlerInterceptor();
	}

	@Bean
	public CoreHandlerMethodArgumentResolver coreHandlerMethodArgumentResolver() {
		return new CoreHandlerMethodArgumentResolver();
	}
	
	@Bean
	public BasicDataDao baseDataDao() {
		return new BasicDataDao();
	}
	
	@Bean
	public ExceptionHandler exceptionHandler() {
		return new ExceptionHandler();
	}

	@Bean
	public AccessCache accessCache() {
		return new AccessCache();
	}

	/**
	 * 拦截器添加
	 *
	 * @param registry
	 *            拦截器注册
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(coreHandlerInterceptor());
		registry.addInterceptor(logHandlerInterceptor());
	}

	/**
	 * controller 参数解析
	 *
	 * @param argumentResolvers
	 *            参数解析列表
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(coreHandlerMethodArgumentResolver());
	}

	/**
	 * 用户操作日志组件
	 *
	 * @return 用户操作日志组件
	 */
	@Bean
	public SystemOperateLogComponent userOperateLogComponent() {
		return new SystemOperateLogComponent();
	}

	@Bean
	@ConditionalOnBean({ SystemOperateLogComponent.class })
	@Primary
	public SystemOperateLogComponent.DefaultLogRecordHandler defaultLogRecordHandler() {
		return new SystemOperateLogComponent.DefaultLogRecordHandler();
	}

	@Bean
	public DbLogRecordHandler dbLogRecordHandler() {
		return new DbLogRecordHandler();
	}

	/**
	 * {@link org.springframework.web.bind.annotation.ResponseBody}的请求结果拦截器
	 *
	 * @return 请求结果拦截器
	 */
	@Bean
	public RecordLogAdvice recordLogAdvice() {
		return new RecordLogAdvice();
	}

}
