package com.example.weiboclone.controller;

import com.example.weiboclone.dto.CommentsRequestsDto;
import com.example.weiboclone.dto.CommentsResponseDto;
import com.example.weiboclone.model.Comment;
import com.example.weiboclone.service.CommentsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentsController {

    private final CommentsService commentsService;

    // 댓글 조회
    @GetMapping("/api/get/{postid}/comment")
    public List<Comment> getComments(@PathVariable Long postid) {
        return commentsService.getComments(postid);
    }

    // 댓글 작성
    @PostMapping("/api/post/{postid}/comment")
    public CommentsResponseDto createComments(@PathVariable Long postid, @RequestBody CommentsRequestsDto requestsDto) {
        return commentsService.createComments(postid, requestsDto);
    }
}
