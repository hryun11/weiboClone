package com.example.weiboclone.service;

import com.example.weiboclone.dto.PostRequestDto;
import com.example.weiboclone.dto.PostResponseDto;
import com.example.weiboclone.model.Posts;
import com.example.weiboclone.model.Users;
import com.example.weiboclone.repository.PostRepository;
import com.example.weiboclone.repository.UserRepository;
import com.example.weiboclone.service.S3Uploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Component
public class PostService {

    private final PostRepository postRepository;
//    @Autowired
    private final UserRepository userRepository;
//    @Autowired
    private final S3Uploader s3Uploader;

    private final String POST_IMAGE_DIR = "static";

//    public PostService(@Lazy PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }

    // 게시글 작성
    @Transactional
    public void createPost(String username, PostRequestDto requestDto) throws IOException {
        Users users = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다."));

        if (requestDto.getData() != null) {
            String uploadImageUrl = s3Uploader.upload(requestDto.getData(), "static");

            Posts post = Posts.builder()
                    .users(users)
                    .content(requestDto.getContents())
                    .image(uploadImageUrl)
                    .build();

            postRepository.save(post);
        } else {
            Posts post = Posts.builder()
                    .users(users)
                    .content(requestDto.getContents())
//                    .image(uploadImageUrl)
                    .build();

            postRepository.save(post);
        }
    }


    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Posts postEntity = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("글이 존재하지 않습니다"));

        return new PostResponseDto(postEntity);
    }

        // 게시글 조회
        public List<PostResponseDto> getposts(int page, int size, String sortby) {
            // page 함수
            Sort.Direction direction = Sort.Direction.DESC;
            Sort sort = Sort.by(direction, sortby);
            page = 0;
            size = 10;
            Pageable pageable = PageRequest.of(page, size, sort);

            // db의 데이터를 정렬하여 slicelist 에 삽입 (정렬해야 이후 무한 스크롤 시 섞이지 않음)
            Slice<Posts> slicePostList = postRepository.findAllBy(pageable);
            List<PostResponseDto> responseDtoList = new ArrayList<>();
            // 정렬된 slicelist 을 post 에 순차적으로 대입
            for (Posts post : slicePostList) {
                // PostsResponseDto 에 전체 게시물을 순차적으로 대입 후 responseDtoList 에 추가
                PostResponseDto responseDto = findById(post.getId());
                responseDtoList.add(responseDto);
            }
            return responseDtoList;
        }
// Page 는 부모 인터페이스인 Slice를 상속되며, getTotalPages(), getTotalElements() 메소드를 추가로 가짐
    }