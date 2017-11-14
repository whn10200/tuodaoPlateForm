package com.tuodao.bp.utils;




import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * @description: Map银行相关解析操作工具类
 * @author: 王艳兵
 * @date: 2017/8/28
 * @time: 16:28
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BankMapUtil {


    /**
     * 根据request对象,将请求参数以treeMap形式解析,完成排序
     * @param request
     * @return
     */
    public static TreeMap<String,String> getRequestTreeMap(HttpServletRequest request){
        Enumeration<String> names = request.getParameterNames();
        TreeMap<String,String> treeMap = new TreeMap<>();
        while (names.hasMoreElements()){
            String key = names.nextElement();
            treeMap.put(key,request.getParameter(key));
        }
        return treeMap;
    }

    /**
     * 将map以url参数的方式转为字符串格式 例如 user=zs&age=12&sex=1
     * @param params
     * @return
     */
    public static String mapToUrlParams(Map<String,String> params){
        if(params == null || params.size() == 0){
            return null;
        }
        StringBuffer buffer = new StringBuffer();

        for(String key : params.keySet()){
            String value = params.get(key);
            if(!Strings.isNullOrEmpty(value)){
                buffer.append("&").append(key).append("=").append(value.trim());
            }
        }
        return buffer.toString() != null ? buffer.substring(1): null;
    }


    /**
     * 将map以字符串形式输出,分隔符为 |
     * @param treeMap
     * @return 返回结果:2014|account|zs
     */
    public static String mapToString(Map<String,String> treeMap){
        return mapToString(treeMap,null);
    }

    /**
     * 将map的value以字符串形式输出,分隔符为 |
     * @param treeMap
     * @param filter treeMap参数过滤
     * @return
     */
    public static String mapToString(Map<String,String> treeMap,PreFilter filter){
        if(treeMap == null || treeMap.size() == 0){
            return null;
        }
        if(filter != null){
            filter.doFilter(treeMap);
        }
        StringBuffer buffer = new StringBuffer();
        for (String key : treeMap.keySet()){
            String value = treeMap.get(key);
            if(value != null){
                buffer.append("|").append(value);
            }
        }
        return buffer.toString() != null ? buffer.substring(1) : null;
    }


    /**
     * 序列化预处理
     */
    public interface PreFilter{
        /**
         * 过滤多余字段
         * @param treeMap 要过滤的map
         */
        void doFilter(Map<String, String> treeMap);
    }

}
