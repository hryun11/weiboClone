package com.example.weiboclone.repository;

import com.example.weiboclone.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
//    Users findByUsername(String username);

    Optional<Users> findByUsername(String username);

}

