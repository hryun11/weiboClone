package com.example.weiboclone.repository;

import com.example.weiboclone.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
