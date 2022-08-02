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

    public CommentsResponseDto(String Contents, Long postid) {
        this.contents = Contents;
        this.id = postid;
    }
}
