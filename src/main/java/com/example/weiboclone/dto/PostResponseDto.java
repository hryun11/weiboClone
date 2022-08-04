package com.example.weiboclone.dto;

import com.example.weiboclone.model.Posts;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseDto {

    private Long id;
    private String contents;
    private String username;
    private String imageFileName;

    private String uploadImageUrl;

    private LocalDateTime createdAt;

    private Long commentCount;

    private Long likesCount;

    public PostResponseDto(Posts posts) {

        this.id = posts.getId();
        this.contents = posts.getContent();
        this.username = posts.getUsers().getUsername();
        this.imageFileName = posts.getImage();
        this.uploadImageUrl = posts.getImage();
        this.createdAt = posts.getCreatedAt();
        this.commentCount = posts.getCommentCount();
        this.likesCount = posts.getLikesCount();
        this.username = posts.getUsers().getUsername();
    }

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    static class PostUserInfo {
//
//        private String username;
//        private String profile;
//    }
}
