package com.example.weiboclone.service;

import com.example.weiboclone.dto.requestdto.LIkeDto;
import com.example.weiboclone.model.Likes;
import com.example.weiboclone.model.Posts;
import com.example.weiboclone.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository lIkeRepository;
    private final PostService postService;
    private Posts posts;

    @Transactional
    public void likes(LIkeDto likeDto) {

        Likes likes = new Likes(likeDto);

        posts.likestate(0);
        boolean exists = lIkeRepository.existsByPostidAndUserid(likeDto.getPostid(), likeDto.getUserid());
        if (exists) {
            postService.minuslikecnt(likeDto.getPostid()); // commentcount 처럼 size나 count 를 쓴다면 필요없음
            lIkeRepository.deleteByPostidAndUserid(likeDto.getPostid(), likeDto.getUserid());
        } else {
            postService.pluslikecnt(likeDto.getPostid());
            lIkeRepository.save(likes);
        }
    }
}

