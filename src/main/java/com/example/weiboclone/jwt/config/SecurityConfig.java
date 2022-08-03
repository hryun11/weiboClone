package com.example.weiboclone.jwt.config;

import com.example.weiboclone.jwt.JwtAuthenticationFilter;
import com.example.weiboclone.jwt.JwtAuthorizationFilter;
import com.example.weiboclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final CorsFilter corsFilter;
    private final UserRepository userRepository;


    @Bean
    @Override // Bean 에 등록
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());
        http.headers().frameOptions().disable();
        // session을 사용하지 않음. STATELESS 서버로 만들겠다는 뜻.
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 모든 요청이 이 필터를 거치면, 내 서버는 cors 정책에서 벗어남. cors 요청이 와도 다 허용됨.
                // @CrossOrigin (인증x), 시큐리티 필터에 등록 인증(O)
//                .addFilter(corsFilter)
                // formlogin, httpbasic, authorizerequests 고정
                .formLogin().disable()  // jwt 서버라 아이디, 비밀번호 formlogin 안함.
                .httpBasic().disable()  // 기본적인 http 로그인 방식 안 씀.
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))   // 로그인을 진행하는 필터이기 때문에 AuthenticationManager를 통해서 파라미터를 던져야함
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()  // 누구나 h2-console 접속 허용
                .antMatchers("/api/user/**").permitAll()
                .antMatchers("**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().permitAll();

                // ""로 주소가 들어오면
//                .antMatchers("/api/v1/user/**")
//                // USER, MANAGER, ADMIN ROLE 접근 가능
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/api/v1/manager/**")
//                // MANAGER, ADMIN ROLE 접근 가능
//                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/api/v1/admin/**")
//                // USER, MANAGER, ADMIN ROLE 접근 가능
//                .access("hasRole('ROLE_ADMIN')")
                // 다른 요청들은 권한 없이 들어갈 수 있음.

    }

    //CORS를 위한 Bean 등록
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // 수정 필요
        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.addExposedHeader("Authorization");
        configuration.setAllowCredentials(true) ;  //
        //  configuration.addAllowedOriginPattern("");
        // configuration.addAllowedOrigin("프론트 주소"); // 배포 시

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
