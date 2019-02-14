package com.study.sso.login;

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
public class SsoLoginApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoLoginApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SsoLoginApplication.class, args);
        System.out.println();
        LOGGER.info("=========== SsoLoginApplication Start Success ===========");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
