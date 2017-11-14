package com.tuodao.bp.api.router.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.router.constant.RouterConstant;
import com.tuodao.bp.cache.basic.BasicDataDao;
import com.tuodao.bp.db.model.basic.SystemBasicData;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 过滤器
 * 
 * @author hechuan
 *
 * @created 2017年5月24日
 *
 * @version 1.0.0
 */
public abstract class AbstractFilter extends ZuulFilter {

	protected static final Logger logger = LoggerFactory.getLogger(AbstractFilter.class);

	protected static final String SUCCESS = "SUCCESS";
	
	protected static final String FAILED = "FAILED";
	
	protected static final int HTTP_SUCCESS_CODE = 200;

	@Autowired
	protected BasicDataDao basicDataDao;

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = getCurCxt();
		Object object = ctx.get(SUCCESS);
		if(null == object || object.equals(SUCCESS)) {
			return true;
		}
		return false;
	}

	/**
	 * 当前上下文
	 * 
	 * @return
	 */
	protected RequestContext getCurCxt() {
		RequestContext currentContext = RequestContext.getCurrentContext();
		return currentContext;
	}

	/**
	 * request
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return getCurCxt().getRequest();
	}

	/**
	 * response
	 * 
	 * @return
	 */
	protected HttpServletResponse getResponse() {
		return getCurCxt().getResponse();
	}

	/**
	 * 是否是银行端的请求
	 * @return
	 */
	protected boolean isBankRequest(){
		HttpServletRequest request = this.getRequest();
		boolean contains = StringUtils.contains(request.getRequestURL().toString(), RouterConstant.DEPOSITORY);
		return contains;
	}

	protected void setCtxSuccess(RequestContext ctx) {
		ctx.setSendZuulResponse(true);
		ctx.setResponseStatusCode(HTTP_SUCCESS_CODE);
		ctx.set(SUCCESS, SUCCESS);
	}

	protected void setCtxFailed(RequestContext ctx ,int errorCode) {
		ctx.setSendZuulResponse(false);
		ctx.setResponseStatusCode(errorCode);
		ctx.set(SUCCESS, FAILED);

		SystemBasicData business = basicDataDao.getBusiness(errorCode+ "");
		ctx.setResponseBody(JSON.toJSONString(new RespResult<String>(errorCode, business.getConfValue()).setContent(null), SerializerFeature.WriteMapNullValue));
		HttpServletResponse response = ctx.getResponse();
		response.setContentType("application/json;charset=utf-8");
		ctx.setResponse(ctx.getResponse());
	}
}
