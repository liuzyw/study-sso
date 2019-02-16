package com.study.sso.music.controller;

import com.study.sso.api.util.CookieUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019-02-12
 *
 * @author liuzhaoyuan
 */
@Controller
public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);


    @RequestMapping("/model1/view")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> list = new ArrayList<>();
        list.add(100);
        modelAndView.addObject("myName", "Tom");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("apple");
        return modelAndView;
    }

    @RequestMapping("/model2/view")
    public ModelAndView view2() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("myName", "Tom");
        modelAndView.setViewName("same-domain");
        return modelAndView;
    }


    @RequestMapping(value = "/test")
    public String test(HttpServletRequest request, HttpServletResponse response) {

        String cookieName = "test-one";

        String cookieValue = CookieUtils.getCookieVal(request, cookieName);

        if (StringUtils.isEmpty(cookieValue)) {
            LOGGER.info("没有可用的 cookie " + cookieName);
            cookieValue = UUID.randomUUID().toString();
        }

        CookieUtils.setCookie(response, cookieName, cookieValue, 3000);

        CookieUtils.getDomainName(request);

        return "success";
    }


}
