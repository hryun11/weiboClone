package com.example.weiboclone.dto.requestdto;

import lombok.Getter;

@Getter
public class LIkeDto {

    private Long postid;
    private Long userid;

    public LIkeDto(Long postid, Long userid) {
        this.postid = postid;
        this.userid = userid;
    }
}
