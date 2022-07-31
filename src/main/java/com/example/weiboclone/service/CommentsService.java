package com.example.weiboclone.service;

import com.example.weiboclone.dto.CommentsRequestsDto;
import com.example.weiboclone.dto.CommentsResponseDto;
import com.example.weiboclone.model.Comment;
import com.example.weiboclone.model.Posts;
import com.example.weiboclone.repository.CommentsRepository;
import com.example.weiboclone.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostsRepository postsRepository;

    // 댓글 조회
    public List<Comment> getComments(Long postid) {
        return commentsRepository.findAllBypostidOrOrderByCreateAtDesc(postid);
    }

    // 댓글 작성
    @Transactional
    public CommentsResponseDto createComments(Long postid, CommentsRequestsDto requestsDto) {
        Posts posts = postsRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다.")
        );
        // 요청 받은 dto 로  db 에 객체 만듦
        Comment comment = new Comment(requestsDto, posts);
        commentsRepository.save(comment);
        CommentsResponseDto responseDto = new CommentsResponseDto(comment.getId(), comment.getContents(),)
        return responseDto;
    }
}
