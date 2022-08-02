package com.example.weiboclone.controller;

import com.example.weiboclone.dto.PostRequestDto;
import com.example.weiboclone.dto.PostResponseDto;
import com.example.weiboclone.jwt.config.auth.PrincipalDetails;
import com.example.weiboclone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    public PostController(@Lazy PostService postService) {
        this.postService = postService;
    }

    // 게시글 작성
    @PostMapping("/api/post")
    public ResponseEntity<Void> createPost(@RequestBody PostRequestDto requestDto,
                                           @AuthenticationPrincipal PrincipalDetails userDetails) throws IOException {

        postService.createPost(userDetails.getUsername(), requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(null);
    }

    // 개별 게시글 상세 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id) {
        PostResponseDto dto = postService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto);
    }



    @GetMapping("/api/posts")
    public List<PostResponseDto> getposts(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam String sortby
    ) {
        page -= page;
        return postService.getposts(page, size, sortby);
    }
}
