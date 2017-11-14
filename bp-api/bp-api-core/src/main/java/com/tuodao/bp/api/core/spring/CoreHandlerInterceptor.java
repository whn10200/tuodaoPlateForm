package com.tuodao.bp.api.core.spring;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;
import com.tuodao.bp.api.core.constant.RequestConstant;
import com.tuodao.bp.api.core.constant.ResponseConstant;
import com.tuodao.bp.api.core.log.SystemOperateLog;
import com.tuodao.bp.cache.basic.AccessCache;
import com.tuodao.bp.model.AccessInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.result.exception.FrameException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * 拦截器
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年5月25日
 */
public class CoreHandlerInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(CoreHandlerInterceptor.class);

    private static final String ENCODING = "utf-8";

    private static final String POST = "POST";

    private static final String SUCCESS = "/api/router";

    private static final ThreadLocal<Boolean> validate = new ThreadLocal<Boolean>() {
        protected Boolean initialValue() {
            return Boolean.TRUE;
        }
    };

    private static final ThreadLocal<Boolean> BANK = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    private static final ThreadLocal<BasePojo> INPUT = new ThreadLocal<BasePojo>() {
        @Override
        protected BasePojo initialValue() {
            return new BasePojo();
        }
    };

    @Autowired
    private AccessCache accessCache;

    /**
     * 用户操作日志组件
     */
    @Resource
    private SystemOperateLogComponent userOperateLogComponent;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        logger.info("请求开始...");

        // 必须从zuul api gateway 过来的请求才是正常请求
        String header = request.getHeader("x-forwarded-prefix");

        if (StringUtils.isBlank(header)) {
            logger.warn("不是从zuul请求过来，非法请求，不予响应..,header : {}", header);
            throw new FrameException(ResponseConstant.HEADER_ERROR);
        }

        if (!checkHeader(header)) {
            logger.info("请求头检查不合法..,header : {},it's must equals : {}", header, SUCCESS);
            throw new FrameException(ResponseConstant.HEADER_ERROR);
        }

        String methodName = request.getMethod();
        logger.info("请求方式：{}", methodName);

        if (!POST.equals(methodName)) {
            throw new FrameException(ResponseConstant.POST_SUPPORT);
        }

        String requestType = request.getHeader("requestType");
        logger.debug("方法拦截获取的requestType:{} ", requestType);

        // 记录操作日志
        SystemOperateLog userOperateLog = userOperateLogComponent.currentUserOperateLog();

        // 如果是银行请求
        if (isBank(handler,requestType)) {
            logger.info("银行回调请求..CoreHandlerInterceptor return true");
            BANK.set(Boolean.TRUE);
            return true;
        }

        String format = request.getHeader(RequestConstant.FORMAT);
        if (StringUtils.isBlank(format)) {
            UrlPathHelper urlPathHelper = new UrlPathHelper();
            String path = urlPathHelper.getLookupPathForRequest(request);
            String extension = UriUtils.extractFileExtension(path);
            if (StringUtils.isEmpty(extension)) {
                format = RequestConstant.DEFAULT_FORMAT;
            }
            logger.info("request path : {}", path);

        }
        request.setAttribute(RequestConstant.FORMAT, format);
        logger.info("format:{}", format);

        String content = IOUtils.toString(request.getInputStream(), ENCODING);
        if (StringUtils.isBlank(content)) {
            Map<String, Object> map = Maps.newHashMap();
            Enumeration<String> pns = request.getParameterNames();
            while (pns.hasMoreElements()) {
                String name = pns.nextElement();
                String value = request.getParameter(name);
                map.put(name, value);
            }
            validate.set(Boolean.FALSE);
            content = JSON.toJSONString(map);
            // 正则表达式
            content = content.replaceAll("\"\\[", "[").replaceAll("]\"", "]").replaceAll("\\\\", "");
        }
        logger.info("请求体内容：{}", content);
        // 记录请求内容
        userOperateLog.setContent(content);

        String contentHeader = request.getHeader("content-header");
        logger.info("contentHeader 加密值：{}", contentHeader);

        String sha256Content = Hashing.sha256().hashString(content, Charset.defaultCharset()).toString();
        logger.info("请求体加密值：{}", sha256Content);

        logger.info("客户端加密值 == 服务端加密值：{}", Objects.equal(sha256Content, contentHeader));

        request.setAttribute(RequestConstant.REQUEST_CONTENT, content);

        // 如果不符合拦截条件，如果是银行接口请求，没有相关的accessId,accessKey，所以也不拦截
        if (!isAccessHandled(handler)) {
            return true;
        }

        String accessId = request.getHeader("accessId");
        logger.debug("方法拦截获取的accessId:{} ", accessId);
        userOperateLog.setAccessid(accessId);

        String accessKey = request.getHeader("accessKey");
        logger.debug("方法拦截获取的accessKey:{} ", accessKey);

        //解决service为null无法注入问题
        if (accessCache == null) {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            accessCache = (AccessCache) factory.getBean("accessCache");
        }
        AccessInfo accessInfo = null;
        if (isApp(handler,requestType)) {
            String version = request.getHeader("version");
            logger.debug("方法拦截获取的version:{} ", version);
            // 获取accessInfo
            accessInfo = accessCache.getAccessInfoApp(accessId);
            // 检查相关访问
            checkAccess(accessId, accessKey, accessInfo);
            // 更新redis，如果是app默认30天
            accessCache.putAccessInfoApp(accessInfo);

            BasePojo input = new BasePojo();
            input.setRequestType(requestType);
            input.setVersion(version);
            INPUT.set(input);
        }
        // pc
        else {
            // 获取accessInfo
            accessInfo = accessCache.getAccessInfo(accessId);
            // 检查相关访问
            checkAccess(accessId, accessKey, accessInfo);
            // 更新redis，如果是pc默认30分
            accessCache.putAccessInfo(accessInfo);
        }
        return true;
    }

    private void checkAccess(String accessId, String accessKey, AccessInfo accessInfo) {
        if (null == accessInfo || StringUtils.isEmpty(accessInfo.getUserId())) {
            logger.info("accessId ={},超时，重新获取", accessId);
            throw new FrameException(ResponseConstant.SESSION_TIME_OUT);
        }
        if (!java.util.Objects.equals(accessKey, accessInfo.getAccessKey())) {
            logger.info("accessKey不匹配 ,accessKey ={} , accessInfo.getAccessKey()={}", accessKey, accessInfo.getAccessKey());
            throw new FrameException(ResponseConstant.ACCESS_NOT_MATCH);
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        logger.info("handler:{}", handler);
        super.afterCompletion(request, response, handler, ex);
    }

    public static boolean getContext() {
        return validate.get().booleanValue();
    }

    public static boolean getBankCurrent() {
        return BANK.get().booleanValue();
    }

    public static BasePojo getInputCurrent() {
        return INPUT.get();
    }

    /**
     * 请求头部检查
     *
     * @param header 请求头
     * @return true|false
     */
    private boolean checkHeader(String header) {

        return StringUtils.equals(SUCCESS, header);
    }


    /**
     * 判断是否要accessId拦截
     *
     * @param handler 控制器
     * @return 是否要拦截，true：拦截 / false：不拦截
     */
    private Boolean isAccessHandled(Object handler) {
        AccessToken annotation = getAccessToken((HandlerMethod) handler);

        if (annotation == null) {
            return false;
        }
        // 是否需要检查，校验ACCESSID,ACCESSKEY
        boolean checkAccess = annotation.checkAccess();
        return checkAccess;
    }


    /**
     * 是否符合请求类型的相关controller请求
     * @param handler 方法
     * @param requestType 请求类型
     * @return true/false
     */
    private boolean isMatchRequest(Object handler,String requestType){
        // 增加支持spring普通方式访问
        AccessToken annotation = getAccessToken((HandlerMethod) handler);
        if(null == annotation){
            return false;
        }
        AccessType[] access = annotation.access();
        return Arrays.stream(access).anyMatch(type -> java.util.Objects.equals(requestType,type.name()));
    }

    /**
     * 是否是APP请求
     *
     * @param requestSource
     * @return
     */
    private boolean isApp(Object handler,String requestType) {
        return isMatchRequest(handler,requestType) && java.util.Objects.equals(requestType,AccessType.APP.name());
    }

    /**
     * 是否是银行接口请求
     *
     * @param handler
     * @return
     */
    private boolean isBank(Object handler,String requestType) {
        return isMatchRequest(handler,requestType) && java.util.Objects.equals(requestType,AccessType.BANK.name());
    }


    /**
     * 获取 AccessToken
     *
     * @param handler
     * @return
     */
    private AccessToken getAccessToken(HandlerMethod handler) {
        // 增加支持spring普通方式访问
        HandlerMethod handlerMethod = handler;

        MethodParameter parameter = handlerMethod.getMethodParameters()[0];

        // 获取被请求方法的AccessToken注解
        return parameter.getMethodAnnotation(AccessToken.class);
    }
}
