package com.tuodao.bp.api.router.filter;

import com.netflix.zuul.context.RequestContext;
import com.tuodao.bp.utils.SignEncryptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 签名过滤
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年5月26日
 */
public class SignFilter extends AbstractFilter {

    private static final Logger logger = LoggerFactory.getLogger(SignFilter.class);


    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String sign = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");
        String accessId = request.getHeader("accessId");
        String accessKey = request.getHeader("accessKey");
        logger.info("sign = {},timestamp = {},accessId = {},accessKey = {}", sign, timestamp, accessId, accessKey);

        // 如果是银行请求
        if (isBankRequest()) {
            logger.info("签名验证默认通过，因为银行回调请求.....");
            setCtxSuccess(ctx);
            return null;
        }

        String calcSign = SignEncryptUtils.getDefaultSign(accessId + timestamp + accessKey);
        logger.info("calcSign = {}", calcSign);
        if ("NO".equals(sign) || calcSign.equals(sign)) {
            logger.info("sign equals calcSign,sign = {},calcSign = {}", sign, calcSign);
            setCtxSuccess(ctx);
        } else {
            logger.info("sign not the same,sign = {},calcSign = {}", sign, calcSign);
            setCtxFailed(ctx, FilterOrder.SIGN_NOT_ALLOWED);
        }

        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterOrder.SIGN_FILTER;
    }


}
