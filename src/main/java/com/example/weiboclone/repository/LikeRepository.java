package com.example.weiboclone.repository;

import com.example.weiboclone.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByPostidAndUserid(Long postid, Long userid);

    void deleteByPostidAndUserid(Long postid, Long userid);

}
