package com.example.weiboclone.dto;

import com.example.weiboclone.model.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentsResponseDto {

    private String username;
    private String userprofileimage;
    private String contents;
    private Long postid;
    private Long id;
    private LocalDateTime createdAt;

    public CommentsResponseDto(String contents, Comment comment) {
        this.contents = contents;
        this.postid = comment.getPosts().getId();
        this.id = comment.getId();
        this.createdAt = comment.getCreatedAt();
    }
}
