package com.example.weiboclone.controller;

import com.amazonaws.services.ec2.model.PricingDetail;
import com.example.weiboclone.dto.LIkeDto;
import com.example.weiboclone.jwt.config.auth.PrincipalDetails;
import com.example.weiboclone.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
public class likeController {

    private final LikeService likeService;

    @PostMapping("/api/likes/{postId}")
    public void likes(@PathVariable Long postId, @AuthenticationPrincipal PrincipalDetails userDetails){
        if (userDetails != null) {
            LIkeDto likeDto = new LIkeDto(postId, userDetails.getUsers().getId());
            likeService.likes(likeDto);
        }
    }
}
