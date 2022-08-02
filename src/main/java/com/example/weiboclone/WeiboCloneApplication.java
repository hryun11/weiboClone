package com.example.weiboclone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WeiboCloneApplication {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application-aws.yml";

    public static void main(String[] args) {
//        SpringApplication.run(WeiboCloneApplication.class, args);
        new SpringApplicationBuilder(WeiboCloneApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
