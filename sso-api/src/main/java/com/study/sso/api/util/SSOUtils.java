package com.study.sso.api.util;

import java.io.Serializable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019-02-14
 *
 * @author liuzhaoyuan
 */
public class SSOUtils implements Serializable {

    private static final Long serialVersionUID = 1L;


    public static String USERNAME = "liu";

    public static String PASSWORD = "123";


    public static boolean isLogin(String username, String password) {

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return true;
        }

        return false;
    }

    public static boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null || cookies.length > 0) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssocookie")
                    && cookie.getValue().equals("liu")) {
                    return true;
                }
            }
        }

        return false;
    }


}
