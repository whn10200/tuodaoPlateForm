package com.tuodao.bp.api.router.filter;

import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问过滤
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年5月24日
 */
public class AccessFilter extends AbstractFilter {

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String method = request.getMethod();
        logger.info("{} request to {}", method, request.getRequestURL().toString());
        // 如果是银行请求
        if (isBankRequest()) {
            logger.info("accessId,accessKey验证默认通过，因为银行回调请求.....");
            setCtxSuccess(ctx);
            return null;
        }
        String acId = request.getHeader("accessId");
        String acKey = request.getHeader("accessKey");
        if (StringUtils.isBlank(acId) || StringUtils.isBlank(acKey)) {
            logger.info("accessId {},accessKey {} not right ", acId, acKey);
            setCtxFailed(ctx, FilterOrder.ACCESS_NOT_ALLOWED);
        } else {
            logger.info("accessId: {},accessKey: {} validate success ", acId, acKey);
            setCtxSuccess(ctx);
        }
        return null;
    }


    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterOrder.ACCESS_FILTER;
    }

}
