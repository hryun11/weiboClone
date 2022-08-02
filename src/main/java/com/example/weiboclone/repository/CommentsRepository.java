package com.example.weiboclone.repository;

import com.example.weiboclone.dto.CommentsResponseDto;
import com.example.weiboclone.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostsIdOrderByCreatedAtDesc(Long postid);
}
