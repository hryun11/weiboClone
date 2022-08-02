package com.example.weiboclone.repository;

import com.example.weiboclone.model.Posts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
    Slice<Posts> findAllBy(Pageable pageable);
}
