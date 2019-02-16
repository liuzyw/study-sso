package com.study.picture.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019-02-12
 *
 * @author liuzhaoyuan
 */
@Controller
public class PictureController implements Serializable {

    private static final Long serialVersionUID = 1L;


    @GetMapping(value = "/")
    @ResponseBody
    public String index() {
        return "sso-picture index " + LocalDateTime.now();
    }


    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "sso-picture hello " + LocalDateTime.now();
    }


    @GetMapping(value = "/picture/orange")
    public ModelAndView picture() {
        ModelAndView modelAndView = new ModelAndView();

        List<Integer> list = new ArrayList<>();
        list.add(44);
        modelAndView.addObject("myName", "orange");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("picture");
        return modelAndView;
    }

}
