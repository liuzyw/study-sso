package com.study.sso.music.controller;

import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019-02-12
 *
 * @author liuzhaoyuan
 */
@RestController
public class MusicController implements Serializable {

    private static final Long serialVersionUID = 1L;


    @GetMapping(value = "/")
    public String index() {
        return "sso-music index " + LocalDateTime.now();
    }


    @GetMapping(value = "/hello")
    public String hello() {
        return "sso-music hello " + LocalDateTime.now();
    }


}
