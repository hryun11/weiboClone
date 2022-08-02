package com.example.weiboclone.dto;

import com.example.weiboclone.model.Posts;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsResponseDto {
    private Long id;

    private String username;

    private String userprofileimage;
    private String contents;

    public CommentsResponseDto(String username, String userprofileimage, String contents) {
        this.username = username;
        this.userprofileimage = userprofileimage;
        this.contents = contents;
    }
}
