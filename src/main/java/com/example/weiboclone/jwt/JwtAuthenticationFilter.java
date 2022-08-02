package com.example.weiboclone.jwt;

//  스프링 시큐리티에서 UsernamePasswordAuthenticationFilter가 있음.
//  /login 요청해서 username, password 전송하면 (POST)
//  UsernamePasswordAuthenticationFilter 동작함.


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.weiboclone.jwt.config.auth.PrincipalDetails;
import com.example.weiboclone.model.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

//  동작하려면 이 필터를 securityconfig에 등록해줘야함.
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    // /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter : 로그인 시도 중");

        // 1. username, password 받아서

        try {
//            BufferedReader br = request.getReader();
//
//            String input = null;
//            while (input == br.readLine()) != null) {
//                System.out.println(input);
//            }
            ObjectMapper om = new ObjectMapper();
            Users users = om.readValue(request.getInputStream(), Users.class);
            System.out.println(users);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword());

            // PrincipalDetailsService의 loadUserByUsername() 함수가 실행된 후 정상이면 authentication이 리턴됨.
            // db에 있는 username과 password가 일치한다.
            // authenticationManager에 토큰을 넣어서 실행해준다.
            // 인증이되면 authentication으로 받음.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료됨 : "+principalDetails.getUsers().getUsername()); // 값이 있다는건 로그인이 정상적으로 되었다.
            // authentication 객체가 session 영역에 저장되어야 하고 그 방법이 return 해주는 것.
            // 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는것.
            // 굳이 jwt 토큰을 사용하면서 세션 만들 이유가 없음. 단지 권한 처리때문에서 session에 넣어줌
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행.
    // JWT 토큰을 만들어서 request 요청한 사용자에게 jwt 토큰을 response해주면 됨.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다는 뜻임.");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        // RSA 방식이 아닌 HASH 암호방식
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))  // (60000*10) 10분
                .withClaim("id", principalDetails.getUsers().getId())
                .withClaim("username", principalDetails.getUsers().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        response.addHeader("Authorization", "Bearer "+jwtToken);

    }
}
