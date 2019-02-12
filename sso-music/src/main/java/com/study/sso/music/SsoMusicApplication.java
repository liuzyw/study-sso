package com.study.sso.music;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2019-02-12
 *
 * @author liuzhaoyuan
 */
@SpringBootApplication
public class SsoMusicApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoMusicApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SsoMusicApplication.class, args);
        System.out.println();
        LOGGER.info("=========== SsoMusicApplication Start Success ===========");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
