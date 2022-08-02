package com.example.weiboclone.jwt.config.auth;

import com.example.weiboclone.model.Users;
import com.example.weiboclone.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// http://localhost:8080/login => 여기서 동작을 안한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService : 진입");
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("없는 회원입니다."));


        // session.setAttribute("loginUser", user);
        return new PrincipalDetails(users);
    }
}

