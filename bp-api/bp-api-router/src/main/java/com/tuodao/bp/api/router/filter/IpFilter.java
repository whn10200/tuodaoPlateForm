package com.tuodao.bp.api.router.filter;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.netflix.zuul.context.RequestContext;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.db.model.basic.SystemBasicData;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * ip 白名单 filter
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年5月24日
 */
public class IpFilter extends AbstractFilter {

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("{} request to {}", request.getMethod(), request.getRequestURL().toString());

        String clientIP = WebUtils.getClientIP(request);
        logger.info("clientIp {}", clientIP);

        String requestType = request.getHeader("requestType");
        if(StringUtils.isBlank(requestType)){
            logger.info("requestType：isEmpty，requestType must one of (APP,PC,BANK,MANAGER) ");
            setCtxFailed(ctx, FilterOrder.REQUEST_TYPE_ISBLANK);
            return null;
        }else {
            boolean b = Arrays.stream(AccessType.values()).anyMatch(t -> Objects.equal(t.name(), requestType));
            if(!b){
                logger.info("requestType:[{}] must one of (APP,PC,BANK,MANAGER) ",requestType);
                setCtxFailed(ctx, FilterOrder.REQUEST_TYPE_MUST_IN_APPPCBANK);
                return null;
            }
        }
        logger.info("requestType：[{}]",requestType);

        // 如果是APP请求，就不用检查iplimit了，APP是客户端
        if(Objects.equal(requestType, AccessType.APP.name())){
            logger.info("APP请求，就不用检查iplimit了");
            setCtxSuccess(ctx);
            return null;
        }

        SystemBasicData ipLimit = basicDataDao.getIplimit(RedisConstans.BASIC_DATA_MODULE_IPLIMIT);
        if (null == ipLimit) {
            logger.info("no ip limit");
            setCtxSuccess(ctx);
            return null;
        }


        List<String> ipCollection = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(ipLimit.getConfValue());

        boolean match = ipCollection.stream().anyMatch(input -> StringUtils.equals(clientIP, input));

        if (match) {
            logger.info("ip：{} validate success ", clientIP);
            setCtxSuccess(ctx);
        } else {
            logger.info("ip：{} not allowed to visit this server", clientIP);
            setCtxFailed(ctx, FilterOrder.IP_NOT_ALLOWED);
        }
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterOrder.IP_FILTER;
    }

}
