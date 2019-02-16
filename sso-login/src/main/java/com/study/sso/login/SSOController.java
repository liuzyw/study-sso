package com.study.sso.login;

import com.study.sso.api.util.SSOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created on 2019-02-16
 *
 * @author liuzhaoyuan
 */
@Controller
public class SSOController implements Serializable {


    private static List<String> tokens = new ArrayList<>();


    @RequestMapping(value = "/checkToken", method = RequestMethod.GET)
    @ResponseBody
    public String checkToken(@RequestParam("token") String token) {

//        if (tokens.contains(token) && JWTUtils.verifyJWT(token)) {
        if (tokens.contains(token)) {
            return "correct";

        }
        return "incorrect";
    }


    @RequestMapping(value = "/preLogin")
    public String preLogin(@RequestParam("url") String url, HttpServletRequest request, Model model) {

        System.out.println("preLogin: " + url);

        HttpSession session = request.getSession(false);

        if (session == null) {
            model.addAttribute("goUrl", url);
            return "login";
        } else {
            String token = (String) session.getAttribute("token");
            return "redirect:" + url + "?token=" + token;
        }

    }

    @RequestMapping(value = "/ssoLogin")
    public String ssoLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String goUrl = request.getParameter("goUrl");
        boolean login = SSOUtils.isLogin(username, password);

        if (login) {

//            String token = JWTUtils.createJWT("liu", "123");
            String token = UUID.randomUUID().toString();
            request.getSession().setAttribute("token", token);

            tokens.add(token);

            return "redirect:" + goUrl + "?token=" + token;

        }

        return "login";
    }


}
