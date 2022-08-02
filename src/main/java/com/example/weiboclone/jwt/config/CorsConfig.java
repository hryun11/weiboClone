package com.example.weiboclone.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정함. false이면 자바스크립트로 요청이 올 때 응답하지 않음.
        config.addAllowedOrigin("*"); // e.g. http://domain1.com // 모든 ip에 응답 허용.
        config.addAllowedHeader("*"); // 모든 header 응답 허용
        config.addAllowedMethod("*"); // 모든 요청(POST, GET, PUT, DELETE, PATCH) 허용

        source.registerCorsConfiguration("/api/**", config); // "" 주소에 들어오는 모든 설정은 config. 설정을 따라감
        return new CorsFilter(source); // source를 하나 만듦.

    }
}
