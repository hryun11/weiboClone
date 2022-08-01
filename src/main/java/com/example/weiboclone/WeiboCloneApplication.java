package com.example.weiboclone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WeiboCloneApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application-aws.yml";
//            + "classpath:aws.yml";

    public static void main(String[] args) {
//        SpringApplication.run(WeiboCloneApplication.class, args);
        new SpringApplicationBuilder(WeiboCloneApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
