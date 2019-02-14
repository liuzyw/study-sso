package com.study.sso.login;

import com.study.sso.api.util.CookieUtils;
import com.study.sso.api.util.SSOUtils;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019-02-14
 *
 * @author liuzhaoyuan
 */
@Controller
public class LoginController implements Serializable {

    private static final Long serialVersionUID = 1L;


    @RequestMapping(value = "/aaa")
    public ModelAndView aaa() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("aaa");

        return modelAndView;
    }


    @RequestMapping(value = "/goLogin")
    public ModelAndView goLogin(HttpServletRequest request, @RequestParam("callback") String url) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("goUrl", url);

        return modelAndView;
    }

    @RequestMapping(value = "/sso/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String goUrl = request.getParameter("goUrl");

        boolean login = SSOUtils.isLogin(username, password);

        if (login) {
            CookieUtils.setCookie(request, response, "ssocookie", username, 3000);
            return "redirect:http://www.baidu.com/";

        }

        return "login";

    }


}
