package com.example.weiboclone.service;

import com.example.weiboclone.dto.CommentsRequestsDto;
import com.example.weiboclone.dto.CommentsResponseDto;
import com.example.weiboclone.jwt.config.auth.PrincipalDetails;
import com.example.weiboclone.model.Comment;
import com.example.weiboclone.model.Posts;
import com.example.weiboclone.repository.CommentsRepository;
import com.example.weiboclone.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;

    // 댓글 조회
    public List<Comment> getComments(Long postid) {
        return commentsRepository.findAllByPostsIdOrderByCreatedAtDesc(postid);
    }

    // 댓글 작성
    @Transactional
    public CommentsResponseDto createComments(Long postid, CommentsRequestsDto requestsDto, PrincipalDetails principalDetails) {
        Posts posts = postRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("해당 게시물을 찾을 수 없습니다.")
        );
        // 요청 받은 dto 로  db 에 객체 만듦
        Comment comment = new Comment(requestsDto,principalDetails.getUsers(),posts);
        commentsRepository.save(comment);
        CommentsResponseDto responseDto = new CommentsResponseDto(comment.getContents(), comment.getId(), comment);
        return responseDto;
    }
}
