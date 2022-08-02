package com.example.weiboclone.controller;

import com.example.weiboclone.dto.PostRequestDto;
import com.example.weiboclone.dto.PostResponseDto;
import com.example.weiboclone.security.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor

@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/post")
    public ResponseEntity<Void> createPost(PostRequestDto requestDto,
                                           @AuthenticationPrincipal UserDetails userDetails) throws IOException {

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

    // 게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<Long> updatePost(@PathVariable Long id,
                                           PostRequestDto requestDto,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            Long modifiedId = postService.updatePost(id, requestDto, userDetails.getUsers().getId());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(modifiedId);
        } else {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }

    @DeleteMapping("posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id,
                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            Long username = userDetails.getUsers().getId();
            postService.deletePost(id, username);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(null);
    }
}
