package com.study.sso.music.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019-02-12
 *
 * @author liuzhaoyuan
 */
@Controller
public class MusicController implements Serializable {

    private static final Long serialVersionUID = 1L;


    @GetMapping(value = "/")
    @ResponseBody
    public String index() {
        return "sso-music index " + LocalDateTime.now();
    }


    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        return "sso-music hello " + LocalDateTime.now();
    }

    @GetMapping(value = "/music/apple")
    public ModelAndView apple() {
        ModelAndView modelAndView = new ModelAndView();

        List<Integer> list = new ArrayList<>();
        list.add(33);
        modelAndView.addObject("myName", "apple");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("apple");
        return modelAndView;
    }


}
