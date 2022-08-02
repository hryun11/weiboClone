package com.example.weiboclone.service;

import com.example.weiboclone.dto.CommentsResponseDto;
import com.example.weiboclone.dto.PostsResponseDto;
import com.example.weiboclone.model.Comment;
import com.example.weiboclone.model.Posts;
import com.example.weiboclone.repository.CommentsRepository;
import com.example.weiboclone.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@RestController
public class PostsService {

    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;

    // 게시글 전체 조회
    public PostsResponseDto getAllPost() {
//        List<PostsResponseDto> list = new ArrayList<>();
//        for (Posts post : postsRepository.findAll()) {
//            PostsResponseDto main = getPostOne(post.getId());
//            list.add(main);
//        }
//        return (PostsResponseDto) list;
        return postsRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 조회
    //
    public List<PostsResponseDto> getposts(int page, int size, String sortby) {
        // page 함수
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortby);
        page = 0;
        size = 10;
        Pageable pageable = PageRequest.of(page, size, sort);

        // db의 데이터를 정렬하여 slicelist 에 삽입 (정렬해야 이후 무한 스크롤 시 섞이지 않음)
        Slice<Posts> slicePostList = postsRepository.findAllById(pageable);
        List<PostsResponseDto> responseDtoList = new ArrayList<>();
        // 정렬된 slicelist 을 post 에 순차적으로 대입
        for (Posts post : slicePostList) {
            // PostsResponseDto 에 전체 게시물을 순차적으로 대입 후 responseDtoList 에 추가
            PostsResponseDto responseDto = getPostOne(post.getId());
            responseDtoList.add(responseDto);
    }
        return responseDtoList;
    }
// Page 는 부모 인터페이스인 Slice를 상속되며, getTotalPages(), getTotalElements() 메소드를 추가로 가짐
//    public Page<PostsResponseDto> getposts(Pageable pageable) {
//
//        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
//        Sort sort = Sort.by(direction, sortby);
//        pageable = PageRequest.of(page, size, sort);
//        Pageable pageable = PageRequest.of(page, size, sort);
//        Page<PostsResponseDto> postsList = postsRepository.findAll(pageable);
//        return postsList;
//    }

    // 게시글 상세 조회
    public PostsResponseDto getPostOne(Long postid) {
        Posts post = postsRepository.findById(postid).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다."));

        return new PostsResponseDto(postid, post);
    }
}
