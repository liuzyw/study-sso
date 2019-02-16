package com.study.sso.login;

import com.study.sso.api.util.SSOUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created on 2019-02-15
 *
 * @author liuzhaoyuan
 */
@Controller
public class HelloController implements Serializable {

    private static final Long serialVersionUID = 1L;


    @RequestMapping("/model1/view")
    public ModelAndView view(HttpServletRequest request) {

        boolean checkCookie = SSOUtils.checkCookie(request);
        ModelAndView modelAndView = new ModelAndView();

        if (!checkCookie) {
            modelAndView.setViewName("redirect:/goLogin?" + "callback=/model1/view");
            return modelAndView;
        }

        List<Integer> list = new ArrayList<>();
        list.add(100);
        modelAndView.addObject("myName", "Tom");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("model");
        return modelAndView;
    }

    @RequestMapping("/model1/same/view")
    public ModelAndView view4(HttpServletRequest request) {

        boolean checkCookie = SSOUtils.checkCookie(request);
        ModelAndView modelAndView = new ModelAndView();

        if (!checkCookie) {
            modelAndView.setViewName("redirect:/goLogin?" + "callback=" + request.getRequestURI());
            return modelAndView;
        }

        List<Integer> list = new ArrayList<>();
        list.add(100);
        modelAndView.addObject("myName", "Tom");
        modelAndView.addObject("list", list);
        modelAndView.setViewName("model");
        return modelAndView;
    }

    @RequestMapping("/model2/view")
    public ModelAndView view2(HttpServletRequest request) {
        boolean checkCookie = SSOUtils.checkCookie(request);
        ModelAndView modelAndView = new ModelAndView();

        if (!checkCookie) {
            modelAndView.setViewName("redirect:/goLogin?" + "callback=/model2/view");
            return modelAndView;
        }
        modelAndView.addObject("myName", "Tom");
        modelAndView.setViewName("same-domain");
        return modelAndView;
    }


}
