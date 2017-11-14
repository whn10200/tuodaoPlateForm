package com.tuodao.bp.utils;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Objects;

public class WebUtils {
    private static final String CONTENT_ATTRIBUTE = "javax.servlet.content";
    private static final int BUFFERSIZE = 8196;
    private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

    private static final String EMPTY = "";


    public static String getRequestContent(HttpServletRequest request) throws IOException {
        String returnV = null;
        Object o = request.getAttribute(CONTENT_ATTRIBUTE);
        if (o == null) {
            // HttpInputMessage inputMessage = new
            // ServletServerHttpRequest(request);

            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();

            PushbackInputStream pbis = new PushbackInputStream(is);

            InputStreamReader isr = new InputStreamReader(pbis);
            BufferedReader br = new BufferedReader(isr);
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            returnV = sb.toString();
            request.setAttribute(CONTENT_ATTRIBUTE, returnV);
        } else {
            returnV = o.toString();
        }
        return returnV;
    }

    public static String getLocalHost() {

        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
        }

        return EMPTY;
    }

    public static String encode(String str, String enc) {

        String strEncode = EMPTY;

        try {
            if (str != null)
                strEncode = URLEncoder.encode(str, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return strEncode;
    }

    public static String decode(String str, String enc) {
        String result = null;
        if (StringUtils.isEmpty(str)) {
            result = EMPTY;
        } else {
            try {
                result = URLDecoder.decode(str, enc);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String getHeaderDecode(HttpServletRequest request, String headerName, String encoding) {
        String headerValue = request.getHeader(headerName);
        return decode(Optional.fromNullable(headerValue.trim()).or(EMPTY), Optional.fromNullable(encoding).or("UTF-8"));
    }

    public static String generateContent(InputStream inputStream, String encoding) {

        if (inputStream == null) {
            return null;
        }

        BufferedInputStream bufferedInputStream = null;

        StringBuffer content = new StringBuffer();

        try {
            byte[] buffer = new byte[BUFFERSIZE];
            int count = 0;

            bufferedInputStream = new BufferedInputStream(inputStream, BUFFERSIZE);

            while ((count = bufferedInputStream.read(buffer)) != -1) { // >0
                content.append(new String(buffer, 0, count, encoding));
            }

            buffer = null;

            return content.toString();
        } catch (Exception e) {
            return null;
        } finally {
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                }
                bufferedInputStream = null;
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
                inputStream = null;
            }
        }
    }


    /**
     * 获取客户端真实ip地址。
     *
     * @param request HttpServletRequest
     * @return ip地址
     */
    public static String getClientIP(HttpServletRequest request) {
        String UNKNOWN = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
                if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                    ip = request.getRemoteAddr();
                }
            }

            return ip;
        }

        String[] ips = ip.split(",");
        if (ips.length > 1) {
            String tempIP = EMPTY;
            for (int i = 0; i < ips.length; i++) {
                tempIP = Optional.fromNullable(ips[i].trim()).or(EMPTY);
                if (!Strings.isNullOrEmpty(tempIP) && UNKNOWN.equalsIgnoreCase(tempIP)) {
                    ip = tempIP;
                    break;
                }
            }
        }
        if (Objects.equals("0:0:0:0:0:0:0:1", ip)) {
            return "127.0.0.1";
        }

        return ip;
    }

    /**
     * 获取session中的值
     *
     * @param session
     * @param key
     * @return
     */
    public static String getString(HttpSession session, String key) {
        Object obj = get(session, key);
        if (obj != null) {
            return (String) obj;
        }
        return null;
    }


    /**
     * 获取session中的值
     *
     * @param session
     * @param key
     * @return
     */
    public static Object get(HttpSession session, String key) {
        return session.getAttribute(key);
    }

    /**
     * 获取本地IP
     *
     * @return IP地址
     */
    public static String getHostIP() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

}
