package com.study.picture;

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
public class SsoPictureApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SsoPictureApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SsoPictureApplication.class, args);
        System.out.println();
        LOGGER.info("=========== SsoPictureApplication Start Success ===========");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
