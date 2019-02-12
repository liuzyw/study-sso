package com.study.sso.music.controller;

import java.util.ArrayList;
import java.util.List;
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


    @RequestMapping("/model/view")
    public ModelAndView view() {
        ModelAndView modelAndView = new ModelAndView();
        List<Integer> list = new ArrayList<>();
        list.add(100);
        modelAndView.addObject("myName", "Tom");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("model");
        return modelAndView;
    }


}
