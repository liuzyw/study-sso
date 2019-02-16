package com.study.sso.api.util;

import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on 2019-02-12
 *
 * @author liuzhaoyuan
 */
public class CookieUtils implements Serializable {


    public static final int COOKIE_MAX_AGE = 7 * 24 * 3600;
    public static final int COOKIE_HALF_HOUR = 30 * 60;


    private static final Long serialVersionUID = 1L;


    /**
     * 添加一条新的Cookie，可以指定过期时间(单位：秒)
     *
     * @param response
     * @param name
     * @param value
     * @param maxValue
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxValue) {
        if (name == null || name.length() == 0) {
            return;
        }
        if (null == value) {
            value = "";
        }
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxValue != 0) {
            cookie.setMaxAge(maxValue);
        } else {
            cookie.setMaxAge(COOKIE_HALF_HOUR);
        }

        response.addCookie(cookie);
//        try {
//            response.flushBuffer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 设置cookie.
     *
     * @param response 响应
     * @param name cookie名字
     * @param value cookie值
     * @param timeOut 有效时间
     * @param cookieDomain
     * @param path 设置cookie路径
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int timeOut, String cookieDomain, String path) {

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(timeOut);
        if (cookieDomain != null) {
            cookie.setDomain(cookieDomain);
        }
        if (path != null) {
            cookie.setPath(path);
        }
        response.addCookie(cookie);
    }


    /**
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param cookieMaxage
     * @param encodeString
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxage, String encodeString) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeString);
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);
            if (cookieMaxage > 0) {
                cookie.setMaxAge(cookieMaxage);
            }
            if (null != request) {// 设置域名的cookie
                String domainName = getDomainName(request);
                System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param request
     *
     * @return
     */
    public static String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";

        } else {
            serverName = serverName.toLowerCase();

            if (serverName.startsWith("http://")) {
                serverName = serverName.substring(7);
            } else if (serverName.startsWith("https://")) {
                serverName = serverName.substring(8);
            }

            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];

            } else if (len <= 3 && len > 1) {
                domainName = "." + domains[len - 2] + "." + domains[len - 1];

            } else {
                domainName = serverName;
            }
        }
        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];

        }
        return domainName;

    }

    /**
     * 获取cookie信息
     *
     * @param request 请求对象
     * @param name cookie名称
     *
     * @return String cookie 值
     */
    public static String getCookieVal(HttpServletRequest request, String name) {
        Cookie cookie = getCookieObj(request, name);

        String val = cookie == null ? null : cookie.getValue();

        return val;
    }

    /**
     * 返回cookie对象
     *
     * @param request
     * @param name
     *
     * @return
     */
    public static Cookie getCookieObj(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (int idx = 0; idx < cookies.length; idx++) {
            if ((cookies[idx].getName()).equals(name)) {
                return cookies[idx];
            }
        }
        return null;
    }

    /**
     * 删除cookie
     *
     * @param request
     * @param name
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = getCookieObj(request, name);
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }


}
