package com.example.weiboclone.repository;

import com.example.weiboclone.model.Comment;
import com.example.weiboclone.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostsIdOrderByCreatedAtDesc(Long postid);

    Collection<Object> findByPosts(Posts postEntity);
}
