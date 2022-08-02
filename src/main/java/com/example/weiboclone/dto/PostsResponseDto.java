package com.example.weiboclone.dto;

import com.example.weiboclone.model.Posts;
import com.example.weiboclone.model.Users;

public class PostsResponseDto {
        private Long id;
        private String images;
        private String contents;
        private int commentCount;
        private int likeCount;

        // userinfo 차후 추가
        public PostsResponseDto(Long postid, Posts post) {
                this.id = postid;
                this.contents = post.getContent();
        }
}
