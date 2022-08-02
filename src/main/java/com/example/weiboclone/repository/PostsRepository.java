package com.example.weiboclone.repository;

import com.example.weiboclone.dto.PostsResponseDto;
import com.example.weiboclone.model.Posts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Slice<Posts> findAllById(Pageable pageable);

    PostsResponseDto findAllByOrderByCreatedAtDesc();
    PostsResponseDto findAllByOrderByPostIdDesc();
}
