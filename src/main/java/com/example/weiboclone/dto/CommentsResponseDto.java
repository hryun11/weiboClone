package com.example.weiboclone.dto;

import com.example.weiboclone.model.Comment;
import com.example.weiboclone.model.Posts;
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
    private Long commentid;
    private LocalDateTime createdAt;

    public CommentsResponseDto(String Contents, Long commentid, Comment comment) {
        this.contents = Contents;
        this.postid = comment.getPosts().getId();
        this.commentid = commentid;
        this.createdAt = comment.getCreatedAt();
    }
}
