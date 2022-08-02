package com.example.weiboclone.jwt.controller;

import com.example.weiboclone.jwt.model.Users;
import com.example.weiboclone.jwt.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RestApiController {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @GetMapping("home")
    public String home() {
        return "<h1>home</h1>";
    }


    @PostMapping("token")
    public String token() {
        return "<h1>token</h1>";
    }


    // 회원가입
    @PostMapping("join")
    public String join(@RequestBody Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
//        users.setRoles("ROLE_USER");    // 롤은 기본으로 설정해준다. ROLE_USER로
        usersRepository.save(users);
        return "회원가입완료";
    }


//    // user, manager, admin 권한 접근 가능
//    @GetMapping("/api/v1/user")
//    public String user() {
//        return "user";
//    }
//
//    // manager, admin 권한 접근 가능
//    @GetMapping("/api/v1/manager")
//    public String manager() {
//        return "manager";
//    }
//
//    // admin 권한 접근 가능
//    @GetMapping("/api/v1/admin")
//    public String admin() {
//        return "admin";
//    }
//
}
