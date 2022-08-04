package com.example.weiboclone.service;

import com.example.weiboclone.dto.LIkeDto;
import com.example.weiboclone.model.Likes;
import com.example.weiboclone.repository.LIkeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LIkeRepository lIkeRepository;
    private final PostService postService;

    @Transactional
    public void likes(LIkeDto likeDto) {

        Likes likes = new Likes(likeDto);

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

