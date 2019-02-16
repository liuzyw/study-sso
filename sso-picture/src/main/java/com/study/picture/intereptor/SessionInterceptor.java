package com.study.picture.intereptor;

import com.study.sso.api.util.HttpUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019-02-16
 *
 * @author liuzhaoyuan
 */
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("login").equals("login")) {
            return true;
        }

        String token = request.getParameter("token");
        System.out.println("token: " + token);

        if (token != null) {
            String reqUrl = "http://sso.server.com:8081/checkToken";
            String content = "token=" + token;

            String doGet = HttpUtils.doGet(reqUrl + "?" + content);

            if ("correct".equals(doGet)) {
                request.getSession().setAttribute("login", "login");
                return true;
            }


        }

        response.sendRedirect("http://sso.server.com:8081/preLogin?url=http://study.zhu.com:8083/picture/orange");

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
