package com.tuodao.bp.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 数字相关转换
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
public class NumberUtils {

    public static Long str2Long(String price) {
        return StringUtils.isBlank(price) ? 0L : Long.parseLong(price);
    }

    public static int str2Int(String value) {
        return StringUtils.isEmpty(value) ? 0 : Integer.valueOf(value);
    }

    public static double str2Double(String value) {
        return StringUtils.isEmpty(value) ? 0.0 : Double.valueOf(value);
    }
}
